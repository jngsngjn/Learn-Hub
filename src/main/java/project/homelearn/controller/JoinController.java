package project.homelearn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.JoinDto;
import project.homelearn.service.JoinService;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public String join(@ModelAttribute JoinDto joinDto) {
        joinService.joinProcess(joinDto);
        return "ok";
    }
}