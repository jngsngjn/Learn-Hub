package project.homelearn.controller.common;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.common.register.RegisterDto;
import project.homelearn.dto.common.register.UsernameDto;
import project.homelearn.service.common.RegisterService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @ModelAttribute RegisterDto registerDto) {
        boolean result = registerService.registerProcess(registerDto);

        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // 실시간 아이디 중복 여부 체크
    @PostMapping("/register/id-duplicate-check")
    public ResponseEntity<?> checkId(@Valid @RequestBody UsernameDto usernameDto) {
        String username = usernameDto.getUsername();
        boolean duplicated = registerService.duplicateId(username);

        if (!duplicated) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}