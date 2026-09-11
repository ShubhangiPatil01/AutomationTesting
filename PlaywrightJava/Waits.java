package com.example;

import com.microsoft.playwright.*;

public class Waits {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        
        // Open Facebook login page
        page.navigate("https://www.facebook.com/login.php/");
        
        // Email / Phone
        page.locator("#_R_1h6kqsqppb6amH1_").fill("test@gmail.com");

        // Password
        page.locator("#_R_1hmkqsqppb6amH1_").fill("test123");

        // Login button
        //page.locator("span[name='login']").click();  
        //page.getByText("Log in").click();

        // Forgotten password
        page.getByText("Forgotten password?").click();
        
        browser.close();
        playwright.close();
    }
}
