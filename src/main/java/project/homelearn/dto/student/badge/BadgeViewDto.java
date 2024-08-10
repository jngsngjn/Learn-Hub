package project.homelearn.dto.student.badge;

import lombok.Data;

import java.time.LocalDate;

// response
@Data
public class BadgeViewDto {

    private Long badgeId;

    private String name;

    private String description;

    private String imagePath;

    private LocalDate obtainDate;

    private Long obtainCount;
}

/*
[
    {
        "badgeId": 1,
        "name": "발자취",
        "description": "시작이 반이다.",
        "imagePath": "badge/foot_print.png",
        "obtainDate": "2024-08-09",
        "obtainCount": 2
    },
    {
        "badgeId": 2,
        "name": "설명충",
        "description": "쉿, 설명 그만!",
        "imagePath": "badge/explain.png",
        "obtainDate": "2024-08-09",
        "obtainCount": 1
    },
    {
        "badgeId": 3,
        "name": "과제왕",
        "description": "오.. 상당히 빠르시군요?",
        "imagePath": "badge/homework.png",
        "obtainDate": "2024-08-09",
        "obtainCount": 1
    },
    {
        "badgeId": 4,
        "name": "???",
        "description": "언젠간 도움이 될 거예요!",
        "imagePath": "",
        "obtainDate": null,
        "obtainCount": 0
    },
    {
        "badgeId": 5,
        "name": "???",
        "description": "이 정도는 가뿐하죠?",
        "imagePath": "",
        "obtainDate": null,
        "obtainCount": 0
    },
    {
        "badgeId": 6,
        "name": "???",
        "description": "쉬엄쉬엄 하세요..",
        "imagePath": "",
        "obtainDate": null,
        "obtainCount": 0
    },
    {
        "badgeId": 7,
        "name": "???",
        "description": "새벽에 같이 공부할 사람!",
        "imagePath": "",
        "obtainDate": null,
        "obtainCount": 0
    },
    {
        "badgeId": 8,
        "name": "???",
        "description": "뭐였더라?",
        "imagePath": "",
        "obtainDate": null,
        "obtainCount": 0
    },
    {
        "badgeId": 9,
        "name": "???",
        "description": "배움에는 끝이 없답니다.",
        "imagePath": "",
        "obtainDate": null,
        "obtainCount": 0
    },
    {
        "badgeId": 10,
        "name": "???",
        "description": "보안에 관심이 많으시군요!",
        "imagePath": "",
        "obtainDate": null,
        "obtainCount": 0
    },
    {
        "badgeId": 11,
        "name": "???",
        "description": "이제 공부합시다!",
        "imagePath": "",
        "obtainDate": null,
        "obtainCount": 0
    }
]
 */