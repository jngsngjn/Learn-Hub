package project.homelearn.service.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.teacher.lecture.LectureEnrollDto;
import project.homelearn.entity.curriculum.Curriculum;
import project.homelearn.entity.curriculum.Lecture;
import project.homelearn.entity.curriculum.Subject;
import project.homelearn.repository.curriculum.LectureRepository;
import project.homelearn.repository.curriculum.SubjectRepository;
import project.homelearn.repository.user.TeacherRepository;

/**
 * Author : 정성진
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    public void enrollLecture(String username, LectureEnrollDto lectureDto) {
        Curriculum curriculum = teacherRepository.findByUsernameAndCurriculum(username).getCurriculum();

        Lecture lecture = new Lecture();
        lecture.setTitle(lectureDto.getTitle());
        lecture.setDescription(lectureDto.getDescription());
        lecture.setYoutubeLink(lectureDto.getYoutubeLink());
        lecture.setCurriculum(curriculum);

        Long subjectId = lectureDto.getSubjectId();
        if (subjectId != null) {
            Subject subject = subjectRepository.findById(subjectId).orElseThrow();
            lecture.setSubject(subject);
        }
        lectureRepository.save(lecture);
    }

    public boolean modifyLecture(String username, Long lectureId, LectureEnrollDto lectureDto) {
        Lecture lecture = lectureRepository.findLectureAndCurriculum(lectureId);
        Curriculum curriculum = lecture.getCurriculum();

        String writer = teacherRepository.findUsernameByCurriculum(curriculum);
        if (!writer.equals(username)) {
            return false;
        }

        lecture.setTitle(lectureDto.getTitle());
        lecture.setDescription(lectureDto.getDescription());
        lecture.setYoutubeLink(lectureDto.getYoutubeLink());

        Long subjectId = lectureDto.getSubjectId();
        if (subjectId != null) {
            Subject subject = subjectRepository.findById(subjectId).orElseThrow();
            lecture.setSubject(subject);
        }
        return true;
    }

    public boolean deleteLecture(String username, Long lectureId) {
        Lecture lecture = lectureRepository.findLectureAndCurriculum(lectureId);
        Curriculum curriculum = lecture.getCurriculum();

        String writer = teacherRepository.findUsernameByCurriculum(curriculum);
        if (!writer.equals(username)) {
            return false;
        }

        lectureRepository.deleteById(lectureId);
        return true;
    }
}