package project.homelearn.repository.vote.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.homelearn.dto.teacher.vote.VoteTabDto;
import project.homelearn.entity.curriculum.Curriculum;

@RequiredArgsConstructor
public class VoteRepositoryImpl implements VoteRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<VoteTabDto> findVotes(Curriculum curriculum, Pageable pageable, String status) {



        return null;
    }
}