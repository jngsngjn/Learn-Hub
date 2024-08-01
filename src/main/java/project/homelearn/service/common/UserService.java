package project.homelearn.service.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.common.EmailCodeDto;
import project.homelearn.dto.common.FindIdRequestDto;
import project.homelearn.entity.user.EnrollList;
import project.homelearn.repository.user.EnrollListRepository;
import project.homelearn.repository.user.UserRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EnrollListRepository enrollListRepository;

    // 회원가입 전 코드 인증
    public boolean verifyCode(EmailCodeDto emailCodeDto) {
        String email = emailCodeDto.getEmail();
        String code = emailCodeDto.getCode();

        EnrollList enrollList = enrollListRepository.findByEmail(email);
        if (enrollList == null) {
            return false;
        }

        return enrollList.getEmail().equals(email) && enrollList.getCode().equals(code);
    }

    // 아이디 찾기
    public String findIdService(FindIdRequestDto findIdDto) {
        String email = findIdDto.getEmail();

        String username = userRepository.findUsernameByEmail(email);
        if (username == null) {
            return null;
        }

        // 아이디 뒤 3자리를 '*'로 표시
        int length = username.length();
        return username.substring(0, length - 3) + "***";
    }
}