package project.homelearn.repository.homework.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import project.homelearn.dto.teacher.dashboard.HomeworkStateDto;
import project.homelearn.dto.teacher.homework.HomeworkTabDto;
import project.homelearn.entity.curriculum.Curriculum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static project.homelearn.entity.homework.QHomework.homework;
import static project.homelearn.entity.homework.QStudentHomework.studentHomework;

@RequiredArgsConstructor
public class HomeworkRepositoryImpl implements HomeworkRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public HomeworkStateDto findHomeworkStateDto(Curriculum curriculum, Integer totalCount) {
        Tuple tuple = queryFactory
                .select(homework.id, homework.title)
                .from(homework)
                .where(homework.curriculum.eq(curriculum))
                .orderBy(homework.createdDate.desc())
                .limit(1)
                .fetchOne();

        if (tuple == null) {
            return null;
        }

        Long homeworkId = tuple.get(homework.id);
        String title = tuple.get(homework.title);

        Long submitCount = queryFactory
                .select(studentHomework.count())
                .from(studentHomework)
                .where(studentHomework.homework.id.eq(homeworkId))
                .fetchOne();

        // 제출률 계산 (정수로 변환)
        int submitRate = 0;
        if (submitCount != null && totalCount != null && totalCount > 0) {
            submitRate = Math.toIntExact((submitCount * 100) / totalCount);
        }

        return HomeworkStateDto
                .builder()
                .title(title)
                .submitRate(submitRate)
                .build();
    }

    @Override
    public Page<HomeworkTabDto> findHomeworks(Curriculum curriculum, Pageable pageable, String status) {
        List<Tuple> tuples = new ArrayList<>();

        if (status.equals("진행")) {
            tuples = queryFactory
                    .select(homework.id, homework.title, homework.description, homework.deadline)
                    .from(homework)
                    .where(homework.curriculum.eq(curriculum), homework.deadline.after(LocalDateTime.now()))
                    .orderBy(homework.createdDate.desc())
                    .fetch();
        }

        if (status.equals("마감")) {
            tuples = queryFactory
                    .select(homework.id, homework.title, homework.description, homework.deadline)
                    .from(homework)
                    .where(homework.curriculum.eq(curriculum), homework.deadline.before(LocalDateTime.now()))
                    .orderBy(homework.createdDate.desc())
                    .fetch();
        }

        List<HomeworkTabDto> homeworkTabDtos = new ArrayList<>();
        for (Tuple tuple : tuples) {
            Long homeworkId = tuple.get(homework.id);
            String title = tuple.get(homework.title);
            String description = tuple.get(homework.description);
            LocalDateTime deadline = tuple.get(homework.deadline);
            Long submitCount = findCompletedCount(homeworkId).get(0);

            HomeworkTabDto dto = new HomeworkTabDto();
            dto.setHomeworkId(homeworkId);
            dto.setTitle(title);
            dto.setDescription(description);
            dto.setDeadLine(deadline);
            dto.setSubmitCount(submitCount);

            homeworkTabDtos.add(dto);
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), homeworkTabDtos.size());

        return new PageImpl<>(homeworkTabDtos.subList(start, end), pageable, homeworkTabDtos.size());
    }

    @Override
    public List<Long> findCompletedCount(Long homeworkId) {
        return queryFactory
                .select(studentHomework.count())
                .from(studentHomework)
                .where(studentHomework.homework.id.eq(homeworkId))
                .fetch();
    }
}