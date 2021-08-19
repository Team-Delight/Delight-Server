package com.team.delightserver;

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
	}

	public static void main(String[] args) {
		SpringApplication.run(DelightserverApplication.class, args);
	}
}
