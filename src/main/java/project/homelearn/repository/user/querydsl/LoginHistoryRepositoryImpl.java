package project.homelearn.repository.user.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project.homelearn.entity.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static project.homelearn.entity.user.QLoginHistory.loginHistory;

@RequiredArgsConstructor
public class LoginHistoryRepositoryImpl implements LoginHistoryRepositoryCustom {

    private static final Logger log = LoggerFactory.getLogger(LoginHistoryRepositoryImpl.class);
    private final JPAQueryFactory queryFactory;

    @Override
    public LocalDateTime findUserLoginDateTime(User user) {
        return queryFactory
                .select(loginHistory.loginDateTime)
                .from(loginHistory)
                .where(loginHistory.user.eq(user))
                .orderBy(loginHistory.loginDateTime.desc())
                .limit(1)
                .fetchOne();
    }

    @Override
    public long countConsecutiveLoginDatesByUser(User user) {

        // 로그인 날짜만 가져오고, 중복된 날짜는 제거
        List<LocalDate> distinctLoginDates = queryFactory
                .select(loginHistory.loginDateTime)
                .from(loginHistory)
                .where(loginHistory.user.eq(user))
                .orderBy(loginHistory.loginDateTime.asc())
                .fetch()
                .stream()
                .map(LocalDateTime::toLocalDate) // 날짜 부분만 추출
                .distinct() // 중복 제거
                .collect(Collectors.toList());

        // 연속된 날짜의 최대 길이 계산
        long maxConsecutive = 0;
        long currentConsecutive = 0;
        LocalDate previousDate = null;

        for (LocalDate date : distinctLoginDates) {
            if (previousDate == null || date.isEqual(previousDate.plusDays(1))) {
                currentConsecutive++;
            } else {
                // 연속이 끊겼을 때 최대값 갱신
                maxConsecutive = Math.max(maxConsecutive, currentConsecutive);
                currentConsecutive = 1; // 초기화 후 현재 날짜를 포함하여 다시 시작
            }
            previousDate = date;
        }

        // 마지막 연속된 그룹을 최대값과 비교하여 갱신
        maxConsecutive = Math.max(maxConsecutive, currentConsecutive);
        return maxConsecutive;
    }
}