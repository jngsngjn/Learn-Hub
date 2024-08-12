package project.homelearn.controller.common;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.common.SubjectIdAndNameDto;
import project.homelearn.service.common.SidebarService;

import java.security.Principal;
import java.util.List;

/**
 * Author : 정성진
 */
@RestController
@RequiredArgsConstructor
public class SidebarController {

    private final SidebarService sidebarService;

    @GetMapping("/side-bar")
    public List<SubjectIdAndNameDto> viewSubjectIdAndName(Principal principal) {
        return sidebarService.getSubjectIdAndName(principal.getName());
    }
}