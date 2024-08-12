package project.homelearn.controller.student.badge;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.dto.student.badge.BadgeViewDto;
import project.homelearn.service.student.BadgeService;

import java.security.Principal;
import java.util.List;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping("/students/badges")
@RequiredArgsConstructor
public class BadgeViewController {

    private final BadgeService badgeService;

    // all, earn, no-earn
    @GetMapping
    public ResponseEntity<?> viewBadges(Principal principal,
                                     @RequestParam(name = "option", required = false, defaultValue = "all") String option) {
        String username = principal.getName();

        if (option.equals("all")) {
            List<BadgeViewDto> result = badgeService.getAllBadges(username);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } else if (option.equals("earn")) {
            List<BadgeViewDto> result = badgeService.getEarnBadges(username);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } else if (option.equals("no-earn")) {
            List<BadgeViewDto> result = badgeService.getNoEarnBadges(username);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}