package project.homelearn.repository.board.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.teacher.board.TeacherBoardDto;
import project.homelearn.dto.teacher.dashboard.ManagerBoardDto;

import java.util.ArrayList;
import java.util.List;

import static project.homelearn.entity.manager.QManagerBoard.managerBoard;
import static project.homelearn.entity.teacher.QTeacherBoard.teacherBoard;

@RequiredArgsConstructor
public class TeacherBoardRepositoryImpl implements TeacherBoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<TeacherBoardDto> findTeacherBoardRecent4(){

        List<Tuple> tuples = queryFactory
                .select(teacherBoard.id, teacherBoard.emergency, teacherBoard.title, teacherBoard.createdDate)
                .from(teacherBoard)
                .orderBy(teacherBoard.createdDate.desc())
                .limit(4)
                .fetch();

        List<TeacherBoardDto> result = new ArrayList<>();

        for(Tuple tuple : tuples){
            TeacherBoardDto dto = new TeacherBoardDto();
            dto.setBoardId(tuple.get(teacherBoard.id));
            dto.setIsEmergency(tuple.get(teacherBoard.emergency));
            dto.setTitle(tuple.get(teacherBoard.title));
            dto.setCreatedDate(tuple.get(teacherBoard.createdDate).toLocalDate());
            result.add(dto);
        }

        return result;
    }
}
