package project.homelearn.entity.notification.student;

public enum StudentNotificationType {
    HOMEWORK_UPLOADED, // 과제 업로드
    REPLY_TO_QUESTION, // 질문에 답변
    INQUIRY_REPLY_FROM_MANAGER, // 매니저 1:1 문의 답변
    INQUIRY_REPLY_FROM_TEACHER, // 강사 1:1 문의 답변
    SURVEY, // 설문 등록
    COMMENT_ON_MY_FREE_BOARD, // 내 글에 댓글
}