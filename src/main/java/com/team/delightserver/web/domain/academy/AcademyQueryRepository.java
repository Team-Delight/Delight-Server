package com.team.delightserver.web.domain.academy;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.team.delightserver.web.domain.academy.QAcademy.academy;

@RequiredArgsConstructor
@Repository
public class AcademyQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Academy> findByName(String name) {
        return queryFactory.selectFrom(academy)
                .where(academy.name.eq(name))
                .fetch();
    }
}
