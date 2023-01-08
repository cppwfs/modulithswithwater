package io.spring.waterlevel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.model.ApplicationModules;

@SpringBootTest
public class DocumenterTests {

    ApplicationModules modules = ApplicationModules.of(WaterlevelApplication.class);

    @Test
    void createModuleDocumentation() {
        new Documenter(modules).writeDocumentation();
    }

    @Test
    void writeDocumentationSnippets() {
        new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }
}
