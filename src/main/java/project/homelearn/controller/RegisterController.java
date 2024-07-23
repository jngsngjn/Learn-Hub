package project.homelearn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.RegisterDto;
import project.homelearn.service.RegisterService;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterDto registerDto) {
        registerService.registerProcess(registerDto);
        return "ok";
    }
}