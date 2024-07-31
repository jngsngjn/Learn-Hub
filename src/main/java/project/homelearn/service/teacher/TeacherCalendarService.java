package project.homelearn.service.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.homelearn.repository.calendar.TeacherCalendarRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TeacherCalendarService {

    private final TeacherCalendarRepository calendarRepository;

}