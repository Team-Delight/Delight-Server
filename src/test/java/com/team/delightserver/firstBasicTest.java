package com.team.delightserver;

import com.team.delightserver.web.domain.academy.Academy;
import com.team.delightserver.web.domain.academy.AcademyRepository;
import com.team.delightserver.web.domain.academy.AcademyRepositorySupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class firstBasicTest {

    @Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private AcademyRepositorySupport academyRepositorySupport;

    @Test
    public void querydsl_기본_기능_확인() { //Repository 2개 상속 Bad방식
        //given
        String name = "jojoldu";
        String address = "jojoldu@gmail.com";
        academyRepository.save(new Academy(name, address));

        //when
        List<Academy> result = academyRepositorySupport.findByName(name);

        //then
        System.out.println("result.get(0).getAddress() = " + result.get(0).getAddress());
        System.out.println("result.get(0).getAddress() = " + result.get(0).getName());

        assertThat(result.size(), is(1));
        assertThat(result.get(0).getAddress(), is(address));
    }
}
