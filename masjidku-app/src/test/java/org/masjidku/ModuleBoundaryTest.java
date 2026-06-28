package org.masjidku;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "org.masjidku")
public class ModuleBoundaryTest {

    @ArchTest
    public static final ArchRule app_should_not_access_accounting_core_directly =
            noClasses().that().resideInAPackage("org.masjidku..")
                    .and().resideOutsideOfPackage("org.masjidku.accounting..")
                    .should().accessClassesThat().resideInAnyPackage(
                            "org.masjidku.accounting.dao..",
                            "org.masjidku.accounting.service.impl.."
                    )
                    .because("Modules should only communicate with Accounting via its client package (org.masjidku.accounting.client..).");

    @ArchTest
    public static final ArchRule app_should_not_access_events_core_directly =
            noClasses().that().resideInAPackage("org.masjidku..")
                    .and().resideOutsideOfPackage("org.masjidku.events..")
                    .should().accessClassesThat().resideInAnyPackage(
                            "org.masjidku.events.dao..",
                            "org.masjidku.events.service.impl.."
                    )
                    .because("Modules should only communicate with Events via its client package (org.masjidku.events.client..).");

    @ArchTest
    public static final ArchRule app_should_not_access_reporting_core_directly =
            noClasses().that().resideInAPackage("org.masjidku..")
                    .and().resideOutsideOfPackage("org.masjidku.reporting..")
                    .should().accessClassesThat().resideInAnyPackage(
                            "org.masjidku.reporting.dao..",
                            "org.masjidku.reporting.service.impl.."
                    )
                    .because("Modules should only communicate with Reporting via its client package (org.masjidku.reporting.client..).");

}
