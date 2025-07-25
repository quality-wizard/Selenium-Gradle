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
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports")

public class TestRunner {

    // This class is used to run the Cucumber tests
    // It sets up the test suite and specifies the features and steps to be used
    // The @AfterSuite annotation is used to close the WebDriver after all tests
    // have run
    // This ensures that the browser is closed and resources are released
    // The BasePage.closeDriver() method is called to close the WebDriver instance
    // This is important for preventing memory leaks and ensuring that the tests run
    // smoothly
    @AfterSuite
    public static void tearDown() {
        BasePage.closeDriver();
    }

}
