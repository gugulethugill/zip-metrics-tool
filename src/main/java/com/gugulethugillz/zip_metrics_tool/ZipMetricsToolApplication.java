package com.gugulethugillz.zip_metrics_tool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication//(scanBasePackages = { "com.gugulethugillz.zip_metrics_tool" })
@RestController
@EnableJpaRepositories
//@Slf4j
public class ZipMetricsToolApplication {


//test
	@GetMapping("/")
	public String home() {
		return "Spring is here!";
	}
//	@Bean
//	public ModelMapper modelMapper(){
//		return new ModelMapper();
//	}

	public static void main(String[] args) {
		SpringApplication.run(ZipMetricsToolApplication.class, args);
	}
}
