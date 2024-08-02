package project.homelearn.repository.board.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.dto.teacher.dashboard.ManagerBoardDto;

import java.util.ArrayList;
import java.util.List;

import static project.homelearn.entity.manager.QManagerBoard.managerBoard;

@RequiredArgsConstructor
public class ManagerBoardRepositoryImpl implements ManagerBoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ManagerBoardDto> findManagerBoardRecent4() {

        List<Tuple> tuples = queryFactory
                .select(managerBoard.id, managerBoard.emergency, managerBoard.title, managerBoard.createdDate)
                .from(managerBoard)
                .orderBy(managerBoard.createdDate.desc())
                .limit(4)
                .fetch();

        List<ManagerBoardDto> result = new ArrayList<>();

        for (Tuple tuple : tuples) {
            ManagerBoardDto dto = new ManagerBoardDto();
            dto.setId(tuple.get(managerBoard.id));
            dto.setIsEmergency(tuple.get(managerBoard.emergency));
            dto.setTitle(tuple.get(managerBoard.title));
            dto.setCreatedDate(tuple.get(managerBoard.createdDate).toLocalDate());
            result.add(dto);
        }

        return result;
    }
}