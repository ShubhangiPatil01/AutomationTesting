package com.example;

import com.microsoft.playwright.*;

public class SauceDemoPlaywright {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();

        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();

        // Open SauceDemo
        page.navigate("https://www.saucedemo.com/");

        System.out.println("Website opened");

        // Enter username
        page.locator("#user-name").fill("standard_user");
        System.out.println("Username entered");
        page.waitForTimeout(1000);
        // Enter password
        page.locator("#password").fill("secret_sauce");
        System.out.println("Password entered");
        page.waitForTimeout(1000);
        // Click Login
        page.locator("#login-button").click();
        System.out.println("Login button clicked");
        page.waitForTimeout(1000);
        // Verify Products page
        String title = page.locator(".title").textContent();
        System.out.println("Page title: " + title);

        // Keep browser open for 2 seconds
        page.waitForTimeout(2000);

        // Close browser
        browser.close();
        playwright.close();
    }
}