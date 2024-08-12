package project.homelearn.controller.common;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.common.HeaderCommonDto;
import project.homelearn.dto.manager.header.NotificationDto;
import project.homelearn.service.common.HeaderService;

import java.security.Principal;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping("/header")
@RequiredArgsConstructor
public class HeaderController {

    private final HeaderService headerService;

    @GetMapping("/common")
    public HeaderCommonDto viewHeaderCommon(Principal principal) {
        String username = principal.getName();
        return headerService.getHeaderCommon(username);
    }

    // 알림 정보 업데이트
    @GetMapping("/notifications")
    public NotificationDto viewNotification(Principal principal) {
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority();
        if (role.equals("ROLE_MANAGER")) {
            return headerService.getManagerNotification();
        }

        if (role.equals("ROLE_TEACHER")) {
            return headerService.getTeacherNotification(principal.getName());
        }
        return headerService.getStudentNotification(principal.getName());
    }

    // 알림 단 건 삭제
    @DeleteMapping("/notifications/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable("id") Long id, Principal principal) {
        boolean result = headerService.deleteNotification(id, principal.getName());
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 알림 모두 삭제
    @DeleteMapping("/notifications")
    public ResponseEntity<?> deleteAllNotifications(Principal principal) {
        boolean result = headerService.deleteAllNotifications(principal.getName());
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}