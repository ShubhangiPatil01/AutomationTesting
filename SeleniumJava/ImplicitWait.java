package TestDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ImplicitWait {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
		
        // Implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.facebook.com/login.php/");

        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("Test@gmail.com");
		driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("12345678");
		driver.findElement(By.xpath("//span[text()='Log in']")).click();
       
        driver.quit();
    }
}