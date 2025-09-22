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

public class CustomerTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 🔹 Login (precondition)
        driver.get("https://testffc.nimapinfotech.com/");

        driver.findElement(By.id("username")).sendKeys("validUser");
        driver.findElement(By.id("password")).sendKeys("validPass");
        driver.findElement(By.id("loginBtn")).click();

        wait.until(ExpectedConditions.urlContains("dashboard"));
    }

    @DataProvider(name = "customerData")
    public Object[][] customerData() {
        return new Object[][]{
                {"Alice", "alice@test.com"},
                {"Bob", "bob@test.com"},
                {"Charlie", "charlie@test.com"}
        };
    }

    @Test(dataProvider = "customerData")
    public void addCustomer(String custName, String custEmail) {
        // 🔹 Click Add Customer
        wait.until(ExpectedConditions.elementToBeClickable(By.id("addCustomerBtn"))).click();

        // 🔹 Fill form
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("custName"))).sendKeys(custName);
        driver.findElement(By.id("custEmail")).sendKeys(custEmail);

        driver.findElement(By.id("saveBtn")).click();

        // 🔹 Validate toast
        WebElement toast = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-message")));

        String toastMessage = toast.getText();
        System.out.println("Toast for " + custName + ": " + toastMessage);

        Assert.assertTrue(toastMessage.contains("Customer added"),
                "Customer addition failed for: " + custName);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
