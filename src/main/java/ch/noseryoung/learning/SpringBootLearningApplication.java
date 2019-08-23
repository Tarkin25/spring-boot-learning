package ch.noseryoung.learning;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SpringBootLearningApplication implements CommandLineRunner {

	private Logger logger;

	@Autowired
	public SpringBootLearningApplication(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("123 test, 123 test");
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLearningApplication.class, args);
	}

}
