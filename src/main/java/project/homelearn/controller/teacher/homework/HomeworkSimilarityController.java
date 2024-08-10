package project.homelearn.controller.teacher.homework;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.homelearn.entity.homework.AcceptFile;
import project.homelearn.entity.homework.Homework;
import project.homelearn.entity.homework.StudentHomework;
import project.homelearn.service.teacher.homework.TeacherHomeworkService;
import project.homelearn.service.teacher.homework.strategy.TextFileSimilarityStrategy;

import java.io.IOException;
import java.util.List;

import static project.homelearn.entity.homework.AcceptFile.*;

/**
 * Author : 정성진
 */
@RestController
@RequestMapping("/teachers/homeworks/{homeworkId}/similarity")
@RequiredArgsConstructor
public class HomeworkSimilarityController {

    private final TeacherHomeworkService homeworkService;
    private final TextFileSimilarityStrategy textFileSimilarityStrategy;

    @GetMapping
    public ResponseEntity<?> calculateSimilarity(@PathVariable Long homeworkId) throws IOException {
        Homework homework = homeworkService.getHomeworkById(homeworkId);
        Boolean requiredFile = homework.getRequiredFile();

        if (!requiredFile) {
            // 파일 첨부가 필수가 아닌 경우
        }

        AcceptFile acceptFile = homework.getAcceptFile();
        if (acceptFile.equals(ETC)) {
            return new ResponseEntity<>("유사성 검사를 진행할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }

        if (acceptFile.equals(JAVA) || acceptFile.equals(SQL) || acceptFile.equals(HTML) || acceptFile.equals(JS)) {
            List<StudentHomework> studentHomeworks = homeworkService.getAllStudentHomeworksByHomework(homework);

            if (studentHomeworks.size() < 2) {
                return new ResponseEntity<>("비교 대상이 2개 미만입니다.", HttpStatus.BAD_REQUEST);
            }

            List<List<String>> result = textFileSimilarityStrategy.similarityCheckProcess(studentHomeworks);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        if (acceptFile.equals(PDF)) {

        }

        if (acceptFile.equals(ZIP)) {

        }

        return new ResponseEntity<>("유사성 검사를 진행할 수 없습니다.", HttpStatus.BAD_REQUEST);
    }
}