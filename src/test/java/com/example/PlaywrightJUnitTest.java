package com.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Playwright test using JUnit 5.
 * You can right-click this file in Eclipse and select: Run As -> JUnit Test
 */
public class PlaywrightJUnitTest {

    // Shared infrastructure across tests
    private static Playwright playwright;
    private static Browser browser;

    // Fresh isolated context per test
    private BrowserContext context;
    private Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void closeBrowser() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        if (context != null) context.close();
    }

    @Test
    void testSearchAndNavigation() {
        // Navigate to website
        page.navigate("https://playwright.dev/java/");

        // Assert page title contains Playwright using Playwright Assertions
        assertThat(page).hasTitle("Fast and reliable end-to-end testing for modern web apps | Playwright Java");

        // Click the 'Get started' link using Playwright role locator
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Get started")).click();

        // Verify URL contains 'docs/intro'
        assertTrue(page.url().contains("docs/intro"), "URL should navigate to documentation intro");
    }
}
