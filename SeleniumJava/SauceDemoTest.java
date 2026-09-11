package com.saucedemo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class SauceDemoTest {
    public static void main(String[] args) throws InterruptedException {
         
        ChromeOptions options = new ChromeOptions();
        
       // Incognito mode disables password saving & leak popups automatically
        options.addArguments("--incognito");
       
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(2000);
        
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
        
       
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(2000);
        
        driver.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
        
        driver.findElement(By.id("checkout")).click();
        Thread.sleep(2000);
        
        driver.findElement(By.id("first-name")).sendKeys("Shubhangi");
        driver.findElement(By.id("last-name")).sendKeys("Patil");
        driver.findElement(By.name("postalCode")).sendKeys("001234");
        Thread.sleep(2000);
        
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
       
        driver.findElement(By.id("finish")).click();
        Thread.sleep(2000);
        
        driver.findElement(By.id("back-to-products")).click();
        Thread.sleep(2000);
        
        // Close browser session
        driver.quit();
    }
}