package io.spring.waterlevel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class WaterlevelApplicationTests {
	ApplicationModules modules = ApplicationModules.of(WaterlevelApplication.class);


	@Test
	void contextLoads() {
		modules.forEach(System.out::println);
	}

	@Test
	void verifiesModularStructure() {
		modules.verify();
	}



}
