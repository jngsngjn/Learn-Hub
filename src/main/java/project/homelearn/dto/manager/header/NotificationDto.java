package project.homelearn.dto.manager.header;

import lombok.Data;

import java.util.List;

// response
@Data
public class NotificationDto {

    private int count;
    private List<NotificationSubDto> notifications;

    @Data
    public static class NotificationSubDto {
        private String url;
        private String message;
    }
}

/*
{
    "count": 1,
    "notifications": [
        {
            "url": "/managers/teacher-inquiries?id=3",
            "message": "강사 1:1 문의가 등록되었습니다."
        }
    ]
}
 */