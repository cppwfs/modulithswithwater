package io.spring.waterlevel;

import io.spring.waterlevel.configuration.WaterLevelService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
public class WaterlevelApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaterlevelApplication.class, args);
	}


//	@Bean
//	ApplicationRunner applicationRunner(WaterLevelService service) {
//		return new ApplicationRunner() {
//			@Override
//			public void run(ApplicationArguments args) throws Exception {
//				System.out.println("Sensor Result is: " + service.getAlertForSensor("789"));
//				System.out.println("FINISHED ALERT REQUEST");
//			}
//		};
//	}

}
