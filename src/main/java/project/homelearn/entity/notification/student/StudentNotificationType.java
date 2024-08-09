package project.homelearn.entity.notification.student;

public enum StudentNotificationType {
    HOMEWORK_UPLOADED, // 과제 업로드 ✅
    REPLY_TO_QUESTION, // 질문에 답변 ✅
    MANAGER_REPLY_TO_INQUIRY, // 매니저 1:1 문의 답변 ✅
    TEACHER_REPLY_TO_INQUIRY, // 강사 1:1 문의 답변 ✅
    SURVEY, // 설문 등록 ✅
    BADGE // 뱃지 획득
}