package steps;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import pages.BasePage;

/**
 * Hooks class contains the setup and teardown methods for Cucumber scenarios.
 * It extends BasePage to inherit common methods for interacting with web
 * elements.
 */
public class Hooks extends BasePage {

    /**
     * This method is executed after each scenario.
     * It captures a screenshot if the scenario fails and attaches it to the report.
     *
     * @param scenario The Cucumber scenario that was executed.
     */
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            scenario.log("Scenario failing, please check the ScreenShot for more details.");
            byte[] screenshot = ((TakesScreenshot) BasePage.driver)
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }
    }
}
