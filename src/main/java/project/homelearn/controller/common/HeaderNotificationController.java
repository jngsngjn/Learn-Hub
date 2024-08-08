package project.homelearn.controller.common;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.service.common.HeaderCommonService;

import java.security.Principal;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping("/header")
@RequiredArgsConstructor
public class HeaderNotificationController {

    private final HeaderCommonService headerService;

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