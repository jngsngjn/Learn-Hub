package project.homelearn.controller.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.homelearn.dto.manager.header.NotificationDto;
import project.homelearn.service.manager.ManagerHeaderService;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping("/managers/header")
@RequiredArgsConstructor
public class ManagerHeaderController {

    private final ManagerHeaderService headerService;

    // 알림 정보 업데이트
    @GetMapping("/notifications")
    public NotificationDto viewNotification() {
        return headerService.getNotification();
    }

    // 알림 단 건 삭제
    @DeleteMapping("/notifications/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable("id") Long id) {
        boolean result = headerService.deleteNotification(id);
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // 알림 모두 삭제
    @DeleteMapping("/notifications")
    public ResponseEntity<?> deleteAllNotifications() {
        boolean result = headerService.deleteAllNotifications();
        if (result) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}