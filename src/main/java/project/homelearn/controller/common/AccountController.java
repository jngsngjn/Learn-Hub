package project.homelearn.controller.common;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.common.account.EmailCodeDto;
import project.homelearn.dto.common.account.EmailDto;
import project.homelearn.dto.common.account.PasswordResetDto;
import project.homelearn.service.common.UserService;

/**
 * Author : 정성진
 */
@Slf4j
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final UserService userService;

    // 아이디 찾기
    @PostMapping("/find-id")
    public ResponseEntity<?> findId(@Valid @RequestBody EmailDto emailDto) {
        String result = userService.findIdService(emailDto);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 비밀번호 재설정 전 이메일로 코드 전송
    @PostMapping("/send-code")
    public ResponseEntity<?> sendCode(@Valid @RequestBody EmailDto emailDto) {
        boolean result = userService.sendCodeForResetPassword(emailDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 비밀번호 재설정 전 코드 인증
    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@Valid @RequestBody EmailCodeDto emailCodeDto) {
        String username = userService.verifyCodeForResetPassword(emailCodeDto);
        if (username != null) {
            return new ResponseEntity<>(username, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 비밀번호 재설정
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@Valid @RequestBody PasswordResetDto passwordResetDto) {
        boolean result = userService.resetPassword(passwordResetDto);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}