package runner;

import org.junit.platform.suite.api.AfterSuite;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import pages.BasePage;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@SelectPackages("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
// @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,
// html:target/cucumber-reports.html,
// json:target/cucumber-reports/cucumber.json")
/**
 * TestRunner class is used to run the Cucumber tests.
 * It sets up the test suite and specifies the features and steps to be used.
 * The @AfterSuite annotation is used to close the WebDriver after all tests
 * have run.
 * This ensures that the browser is closed and resources are released.
 */
public class TestRunner {

    @AfterSuite
    public static void tearDown() {
        BasePage.closeDriver();
    }

}
