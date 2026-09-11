package TestDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExplicitWait {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/login.php/");

        // Explicit wait of 10 seconds
        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
        );

        // Wait until email field is visible
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));

        email.sendKeys("Test@gmail.com");

        // Wait until password field is visible
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("pass")));

        password.sendKeys("12345678");

        // Wait until Login button is clickable
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Log in']")));

        loginButton.click();

        driver.quit();
    }
}