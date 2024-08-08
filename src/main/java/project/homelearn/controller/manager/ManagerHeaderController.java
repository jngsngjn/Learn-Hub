package project.homelearn.controller.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}