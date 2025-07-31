package utils;

import io.qameta.allure.Allure;
import pages.BasePage;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

/**
 * AllureAttachments class provides methods to attach various types of files to
 * Allure reports.
 * It extends BasePage to inherit common methods for interacting with web
 * elements.
 */
public class AllureAttachments extends BasePage {

    /**
     * Attach a screenshot to the Allure report.
     * This method captures a screenshot of the current state of the web driver
     * and attaches it to the report.
     */
    public static void attachScreenshot() {
        WebDriver driver = BasePage.driver;
        if (driver == null)
            return;
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
    }

    /**
     * Attach a text file to the Allure report.
     *
     * @param name    The name of the attachment.
     * @param message The text content to attach.
     */
    public static void attachText(String name, String message) {
        Allure.addAttachment(name, "text/plain", message, ".txt");
    }

    /**
     * Attach a JSON file to the Allure report.
     *
     * @param name The name of the attachment.
     * @param json The JSON content to attach.
     */
    public static void attachJson(String name, String json) {
        Allure.addAttachment(name, "application/json", json, ".json");
    }

    /**
     * Attach an HTML file to the Allure report.
     *
     * @param name The name of the attachment.
     * @param html The HTML content to attach.
     */
    public static void attachHtml(String name, String html) {
        Allure.addAttachment(name, "text/html", html, ".html");
    }
}