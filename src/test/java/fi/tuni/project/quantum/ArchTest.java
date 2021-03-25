package fi.tuni.project.quantum;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("fi.tuni.project.quantum");

        noClasses()
            .that()
            .resideInAnyPackage("fi.tuni.project.quantum.service..")
            .or()
            .resideInAnyPackage("fi.tuni.project.quantum.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..fi.tuni.project.quantum.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
