package com.team.delightserver;

import java.util.Date;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class DelightserverApplication {

	/**
	 * Delight Server 시간설정을 Asia/Seoul 로 지정 합니다.
	 */
	@PostConstruct
	void setTimeZonAsiaSeoul() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		System.out.println("서버 시작 시간: " + new Date());
	}

	public static void main(String[] args) {
		SpringApplication.run(DelightserverApplication.class, args);
	}
}
