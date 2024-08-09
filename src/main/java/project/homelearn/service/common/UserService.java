package project.homelearn.service.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.common.account.EmailCodeDto;
import project.homelearn.dto.common.account.EmailDto;
import project.homelearn.dto.common.account.PasswordResetDto;
import project.homelearn.entity.user.EnrollList;
import project.homelearn.entity.user.User;
import project.homelearn.repository.user.EnrollListRepository;
import project.homelearn.repository.user.UserRepository;
import project.homelearn.service.student.BadgeService;

import java.time.Duration;

import static project.homelearn.config.common.MailType.RESET_PW;
import static project.homelearn.entity.student.badge.BadgeConstants.SECURITY;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final RedisService redisService;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EnrollListRepository enrollListRepository;
    private final BadgeService badgeService;

    // 회원가입 전 코드 인증
    public boolean verifyCodeBeforeRegister(EmailCodeDto emailCodeDto) {
        String email = emailCodeDto.getEmail();
        String code = emailCodeDto.getCode();

        EnrollList enrollList = enrollListRepository.findByEmail(email);
        if (enrollList == null) {
            return false;
        }

        return enrollList.getEmail().equals(email) && enrollList.getCode().equals(code);
    }

    // 아이디 찾기
    public String findIdService(EmailDto emailDto) {
        String email = emailDto.getEmail();

        String username = userRepository.findUsernameByEmail(email);
        if (username == null) {
            return null;
        }

        // 아이디 뒤 3자리를 '*'로 표시
        int length = username.length();
        return username.substring(0, length - 3) + "***";
    }

    // 비밀번호 재설정 전 이메일로 코드 전송
    public boolean sendCodeForResetPassword(EmailDto emailDto) {
        String email = emailDto.getEmail();
        String username = userRepository.findUsernameByEmail(email);
        if (username == null) {
            return false;
        }

        String code = emailService.sendCode(email, RESET_PW);
        Duration duration = Duration.ofMinutes(5);
        redisService.saveEmailCode(email, code, duration);
        return true;
    }

    // 비밀번호 재설정 전 코드 확인
    public String verifyCodeForResetPassword(EmailCodeDto emailCodeDto) {
        String email = emailCodeDto.getEmail();
        String inputCode = emailCodeDto.getCode();

        String realCode = redisService.getCodeByEmail(email);
        if (!inputCode.equals(realCode)) {
            return null;
        }

        redisService.deleteEmailCode(email);

        String username = userRepository.findUsernameByEmail(email);
        redisService.markUserAsVerified(username, Duration.ofMinutes(5));
        return username;
    }

    // 비밀번호 재설정
    public boolean resetPassword(PasswordResetDto passwordResetDto) {
        String username = passwordResetDto.getUsername();
        String password = passwordResetDto.getPassword();

        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }

        if (redisService.isUserVerified(username)) {
            user.setPassword(passwordEncoder.encode(password));
            redisService.deleteVerification(username);

            int count = user.getPasswordChangeCount() + 1;
            user.setPasswordChangeCount(count);

            if (count == 3) {
                badgeService.getBadge(user, SECURITY);
            }
            return true;
        }
        return false;
    }
}