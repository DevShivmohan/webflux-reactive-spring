package reactive.api.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReactiveApiSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApiSpringApplication.class, args);
	}

}
