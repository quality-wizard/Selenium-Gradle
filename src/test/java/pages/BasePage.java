package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * BasePage class serves as a base for all page objects in the application.
 * It provides common methods for interacting with web elements and managing the
 * WebDriver instance.
 */
public class BasePage {

    // Static WebDriver instance to be shared across all page objects
    protected static WebDriver driver;

    // WebDriverWait instance for handling waits
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // Static block to initialize WebDriver
    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    // Method to close the driver
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Clear the reference to the driver
        }
    }

    // Method to get the WebDriver instance
    public BasePage(WebDriver driver) {
        // Constructor can be used for additional setup if needed
        BasePage.driver = driver;
    }

    // Method to navigate to a specific URL
    public static void navigateTo(String url) {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        driver.get(url);
    }

    // Method to find elements with explicit wait
    private WebElement findElement(String locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    // Method to click on an element
    public void clickElement(String locator) {
        findElement(locator).click();
    }

    // Method to get the text of an element
    public void write(String locator, String keysToSend) {
        findElement(locator).clear();
        findElement(locator).sendKeys(keysToSend);
    }

    // Method to select an option from a dropdown by visible text
    public void selectDropdownByValue(String locator, String value) {
        Select dropdown = new Select(findElement(locator));
        dropdown.selectByValue(value);
    }

    // Method to select an option from a dropdown by index
    public void selectDropdownByIndex(String locator, Integer index) {
        Select dropdown = new Select(findElement(locator));
        dropdown.selectByIndex(index);
    }

    // Method to get the size of a dropdown
    public int dropdownSize(String locator) {
        Select dropdown = new Select(findElement(locator));
        List<WebElement> dropdownOptions = dropdown.getOptions();
        return dropdownOptions.size();
    }
}
