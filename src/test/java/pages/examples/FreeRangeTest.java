// package pages.examples;

// import static org.junit.jupiter.api.Assertions.assertTrue;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.chrome.ChromeDriver;
// import io.github.bonigarcia.wdm.WebDriverManager;

// class FreeRangeTest {

// private WebDriver driver;

// @BeforeEach
// void setUp() {
// WebDriverManager.chromedriver().setup();
// driver = new ChromeDriver();
// }

// @Test
// void test1() {
// driver.get("https://www.freerangetesters.com/");
// WebElement elementById = driver.findElement(By.id("ElementoLoco"));
// elementById.click();
// // Add assertions or further interactions as needed
// // For example, you can check if the element is displayed or if the page has
// // changed
// // Assertions can be added here to validate the test case
// assertTrue(elementById.isDisplayed());
// }

// @Test
// void test2() {
// driver.get("https://www.freerangetesters.com/");
// WebElement elementById = driver.findElement(By.id("idDelElemento"));
// elementById.click();
// // Add assertions or further interactions as needed
// // For example, you can check if the element is displayed or if the page has
// // changed
// // Assertions can be added here to validate the test case
// assertTrue(elementById.isDisplayed());
// }

// @Test
// void test3() {
// driver.get("https://www.freerangetesters.com/");
// WebElement elementById = driver.findElement(By.id("idDelElemento2"));
// elementById.click();
// // Add assertions or further interactions as needed
// // For example, you can check if the element is displayed or if the page has
// // changed
// // Assertions can be added here to validate the test case
// assertTrue(elementById.isDisplayed());
// }

// @AfterEach
// void tearDown() {
// if (driver != null) {
// driver.quit();
// }
// }
// }
