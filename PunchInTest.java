package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class PunchInTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 🔹 Precondition: Login before PunchIn
        driver.get("https://testffc.nimapinfotech.com/");

        driver.findElement(By.id("username")).sendKeys("validUser");
        driver.findElement(By.id("password")).sendKeys("validPass");
        driver.findElement(By.id("loginBtn")).click();

        // wait until dashboard loads
        wait.until(ExpectedConditions.urlContains("dashboard"));
    }

    @Test
    public void verifyPunchInToast() {
        // 🔹 Click PunchIn button
        WebElement punchInBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("punchInBtn")));
        punchInBtn.click();

        // 🔹 Wait for Toast/Popup message
        WebElement toast = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-message")));

        String toastMessage = toast.getText();
        System.out.println("Toast Message: " + toastMessage);

        // 🔹 Validation
        Assert.assertTrue(toastMessage.contains("Punch In Successful"),
                "Expected toast message not displayed!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
