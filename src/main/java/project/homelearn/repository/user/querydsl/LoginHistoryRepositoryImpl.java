package project.homelearn.repository.user.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.homelearn.entity.user.User;

import java.time.LocalDateTime;

import static project.homelearn.entity.user.QLoginHistory.loginHistory;

@RequiredArgsConstructor
public class LoginHistoryRepositoryImpl implements LoginHistoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public LocalDateTime findUserLoginDateTime(User user) {
        return queryFactory
                .select(loginHistory.loginDateTime).distinct()
                .from(loginHistory)
                .where(loginHistory.user.eq(user))
                .orderBy(loginHistory.loginDateTime.asc())
                .fetchOne();
    }
}