// package pages;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.chrome.ChromeDriver;
// import io.github.bonigarcia.wdm.WebDriverManager;

// class FreeRangeTest {

//     private WebDriver driver;

//     @BeforeEach
//     void setUp() {
//         WebDriverManager.chromedriver().setup();
//         driver = new ChromeDriver();
//     }

//     @Test
//     void test1() {
//         driver.get("https://www.freerangetesters.com/");
//         WebElement elementById = driver.findElement(By.id("ElementoLoco"));
//         elementById.click();
//     }

//     @Test
//     void test2() {
//         driver.get("https://www.freerangetesters.com/");
//         WebElement elementById = driver.findElement(By.id("idDelElemento"));
//         elementById.click();
//     }

//     @Test
//     void test3() {
//         driver.get("https://www.freerangetesters.com/");
//         WebElement elementById = driver.findElement(By.id("idDelElemento2"));
//         elementById.click();
//     }

//     @AfterEach
//     void tearDown() {
//         if (driver != null) {
//             driver.quit();
//         }
//     }
// }