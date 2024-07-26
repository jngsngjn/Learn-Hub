package project.homelearn.service.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.common.EmailCodeDto;
import project.homelearn.entity.user.EnrollList;
import project.homelearn.repository.user.EnrollListRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final EnrollListRepository enrollListRepository;

    public boolean verifyCode(EmailCodeDto emailCodeDto) {
        String email = emailCodeDto.getEmail();
        String code = emailCodeDto.getCode();

        EnrollList enrollList = enrollListRepository.findByEmail(email);
        if (enrollList == null) {
            return false;
        }

        return enrollList.getEmail().equals(email) && enrollList.getCode().equals(code);
    }
}