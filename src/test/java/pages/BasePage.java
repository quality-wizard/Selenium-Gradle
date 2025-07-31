package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    // WebDriver instance used throughout the application
    protected static WebDriver driver;
    WebDriverWait wait;

    // Static block to initialize the WebDriver
    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    // Constructor for BasePage
    // Initializes the WebDriverWait with a timeout of 10 seconds
    // Throws an exception if the WebDriver is not initialized
    public BasePage() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized.");
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to close the driver
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
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

    // Method to get all options from a dropdown as a list of strings
    public List<String> getDropdownOptions(String locator) {
        Select dropdown = new Select(findElement(locator));

        // Get all options from the dropdown and return their text as a list of strings
        List<WebElement> dropdownOptions = dropdown.getOptions();
        // Create a list to hold the text of each option
        List<String> values = new ArrayList<>();
        // Iterate through each option and add its text to the list
        for (WebElement option : dropdownOptions) {
            values.add(option.getText());
        }

        return values;
    }

    // Method to get the text of all radio buttons within a form
    public List<String> getRadioButtonTexts(String locator) {
        // Find the form element using the provided locator
        WebElement formElement = findElement(locator);
        // Find all radio buttons within the form
        List<WebElement> radioButtons = formElement.findElements(By.xpath(".//input[@type='radio']"));
        // Create a list to hold the text of each radio button
        List<String> radioButtonTexts = new ArrayList<>();
        // Iterate through each radio button and add its label text to the list
        for (WebElement radioButton : radioButtons) {
            // Find the label associated with the radio button
            WebElement label = formElement
                    .findElement(By.xpath(".//label[@for='" + radioButton.getAttribute("id") + "']"));
            // Add the label text to the list
            radioButtonTexts.add(label.getText());
        }
        return radioButtonTexts;
    }

    // Method to handle radio buttons
    public void selectRadioButton(String locator) {
        // Find the radio button element and click it
        WebElement radioButton = findElement(locator);
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
    }

    public void highlightRedElement(WebElement elemento) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid red;')", elemento);
    }

    public void highlightGreenElement(WebElement elemento) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid green;')", elemento);
    }

    public void highlightWebElement(By locator, boolean exitoso) {
        WebElement element = driver.findElement(locator);
        if (exitoso) {
            highlightGreenElement(element);
        } else {
            highlightRedElement(element);
        }
    }
}
