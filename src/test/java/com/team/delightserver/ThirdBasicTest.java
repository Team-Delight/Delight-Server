package com.team.delightserver;

import com.team.delightserver.web.domain.academy.Academy;
import com.team.delightserver.web.domain.academy.AcademyQueryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ThirdBasicTest {

    @Autowired
    private AcademyQueryRepository academyQueryRepository;

    @Test
    public void querydsl_기본_기능_확인2() {
        //given
        String name = "jojoldu";
        String address = "jojoldu@gmail.com";
//        academyRepository.save(new Academy(name, address));

        //when
        List<Academy> result = academyQueryRepository.findByName(name);

        //then
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getAddress(), is(address));
    }
}
