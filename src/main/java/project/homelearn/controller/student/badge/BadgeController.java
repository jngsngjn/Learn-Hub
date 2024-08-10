package project.homelearn.controller.student.badge;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.service.student.BadgeService;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
public class BadgeController {

    private final BadgeService badgeService;


}