package project.homelearn.entity.notification.teacher;

public enum TeacherNotificationType {
    QUESTION_POSTED, // 질문 등록 ✅
    REPLY_TO_COMMENT, // 질문 답변에 댓글 ✅
    STUDENT_INQUIRY_TO_TEACHER, // 학생 1:1 문의
    MANAGER_REPLY_TO_INQUIRY // 매니저가 1:1 문의에 응답 ✅
}