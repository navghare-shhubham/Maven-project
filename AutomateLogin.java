package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"validUser", "validPass", true},
                {"invalidUser", "invalidPass", false}
        };
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, boolean expectedResult) {
        driver.get("https://testffc.nimapinfotech.com/");

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("loginBtn")).click();

        boolean loginSuccess = driver.getCurrentUrl().contains("dashboard");

        Assert.assertEquals(loginSuccess, expectedResult,
                "Login validation failed for: " + username);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
