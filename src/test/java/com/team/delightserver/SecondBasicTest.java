package com.team.delightserver;

import com.team.delightserver.web.domain.academy.Academy;
import com.team.delightserver.web.domain.academy.AcademyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class SecondBasicTest {

    @Autowired
    private AcademyRepository academyRepository;

    @Test
    public void querydsl_Custom설정_기능_확인() { // Impl, Custom 두개 만들고 repo1개만 상속
        //given
        String name = "jojoldu";
        String address = "jojoldu@gmail.com";
        academyRepository.save(new Academy(name, address));

        //when
        List<Academy> result = academyRepository.findByName(name);

        //then
        assertThat(result.size(), is(1));
        assertThat(result.get(0).getAddress(), is(address));
    }
}