package project.homelearn.controller.common;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.common.HeaderCommonDto;
import project.homelearn.service.common.HeaderCommonService;

import java.security.Principal;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping("/header")
@RequiredArgsConstructor
public class HeaderCommonController {

    private final HeaderCommonService headerService;

    @GetMapping("/basic")
    public HeaderCommonDto viewHeaderCommon(Principal principal) {
        String username = principal.getName();
        return headerService.getHeaderCommon(username);
    }


}