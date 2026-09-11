package com.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import java.nio.file.Paths;

/**
 * First Playwright test running as a standard Java application (main method).
 * You can right-click this file in Eclipse and select: Run As -> Java Application
 */
public class FirstPlaywrightTest {
    public static void main(String[] args) {
        System.out.println("Starting Playwright Java execution...");

        // Step 1: Launch Playwright engine
        try (Playwright playwright = Playwright.create()) {
            
            // Step 2: Launch browser (Chromium in headed mode so you can see it run)
            Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(500)
            );

            // Step 3: Open a new page/tab
            Page page = browser.newPage();

            // Step 4: Navigate to a website
            System.out.println("Navigating to example.com...");
            page.navigate("https://example.com");

            // Step 5: Extract and print page title and content
            String title = page.title();
            System.out.println("Page Title: " + title);

            String heading = page.locator("h1").innerText();
            System.out.println("Heading on Page: " + heading);

            // Step 6: Take a screenshot
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")));
            System.out.println("Saved screenshot to screenshot.png");

            // Step 7: Close browser
            browser.close();
            System.out.println("Playwright test completed successfully!");
        }
    }
}
