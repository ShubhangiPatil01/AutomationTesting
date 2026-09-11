package com.example; 
import java.util.regex.Pattern; 
import org.testng.Assert; 
import org.testng.annotations.Test; 
import com.microsoft.playwright.Browser; 
import com.microsoft.playwright.BrowserType; 
import com.microsoft.playwright.Page; 
import com.microsoft.playwright.Playwright; 
import com.microsoft.playwright.assertions.PlaywrightAssertions; 
public class LoginLogout { 
@Test 
public void loginTest() { 
Page page = null; 
Browser browser = null; 
try  
{ 
Playwright pw = Playwright.create(); 
BrowserType browserType = pw.chromium(); 
browser = browserType.launch(new 
BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000)); 
page = browser.newPage(); 
page.navigate("https://freelance-learn-automation.vercel.app/login"); 
System.out.println("Link"); 
Assert.assertTrue(page.title().contains("Learn Automation Courses")); 
//PlaywrightAssertions.assertThat(page).hasTitle("Learn Automation Courses"); 
System.out.println("url open"); 
//Login 
page.locator("#email1").fill("admin@email.com"); 
page.getByPlaceholder("Enter Password").fill("admin@123"); 
//page.getByText("Sign in").last().click(); 
page.locator("xpath = //button[@class='submit-btn']").click(); 
page.getByAltText("menu").click(); 
page.getByText("Sign out").click(); 
PlaywrightAssertions.assertThat(page).hasURL(Pattern.compile("login")); 
} 
finally 
{ 
//page.close(); 
//browser.close(); 
} 
} 
}