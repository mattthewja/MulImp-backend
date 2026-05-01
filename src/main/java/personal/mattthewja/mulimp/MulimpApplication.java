package personal.mattthewja.mulimp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MulimpApplication {

	public static void main(String[] args) {
		SpringApplication.run(MulimpApplication.class, args);
	}

}
