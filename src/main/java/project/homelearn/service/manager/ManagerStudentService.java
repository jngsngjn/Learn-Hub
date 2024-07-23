package project.homelearn.service.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.dto.manager.ManagerStudentDto;
import project.homelearn.entity.student.Student;
import project.homelearn.repository.user.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ManagerStudentService {

    private final StudentRepository studentRepository;

    public Page<ManagerStudentDto> getStudents(int size, int page){
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentRepository.findAllByOrderByCreatedDateDesc(pageable);

        List<ManagerStudentDto> studentDtos = studentPage.stream()
                .map(student -> new ManagerStudentDto(
                        student.getName(),
                        student.getCurriculum().getTh(),
                        student.getCurriculum().getName(),
                        student.getPhone(),
                        student.getEmail()))
                .collect(Collectors.toList());

        return new PageImpl<>(studentDtos, pageable, studentPage.getTotalElements());
    }
}
