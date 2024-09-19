package com.timesaver.timesaver;

import com.timesaver.timesaver.config.SureAuditing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class TimesaverApplication  extends SpringBootServletInitializer {

	@Bean
	public AuditorAware<String> auditorAware() {
		return new SureAuditing();
	}

	public static void main(String[] args) {
		SpringApplication.run(TimesaverApplication.class, args);
	}

}
