package io.spring.waterlevel;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class ModularityTests {
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
