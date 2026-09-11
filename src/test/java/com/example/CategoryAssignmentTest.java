package com.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Playwright Java Assignment Automation
 * Executes all 29 steps specified in the assignment instructions cleanly.
 */
public class CategoryAssignmentTest {

    @Test
    public void runAssignmentTest() {
        main(new String[]{});
    }

    private static Page navigateToManageCategory(BrowserContext context, Page currentPage) {
        currentPage.locator(".nav-menu-item-manage").first().hover();
        currentPage.waitForTimeout(500);
        
        Page categoryPage = context.waitForPage(() -> {
            currentPage.locator("a[href='/category/manage']").first().click();
        });
        categoryPage.waitForLoadState();
        categoryPage.waitForTimeout(1000);
        
        // Close previous page if a new popup opened
        if (currentPage != categoryPage && !currentPage.isClosed()) {
            currentPage.close();
        }
        return categoryPage;
    }

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println(" Starting Playwright Java Assignment Execution   ");
        System.out.println("=================================================");

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(300)
            );
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            // STEP 1: Open URL
            System.out.println("\n[Step 1] Navigating to login page...");
            page.navigate("https://freelance-learn-automation.vercel.app/login");

            // STEP 2: Login using credentials
            System.out.println("[Step 2] Logging in as admin@email.com...");
            page.locator("#email1, input[type='email']").first().fill("admin@email.com");
            page.locator("#password1, input[type='password']").first().fill("admin@123");
            page.locator("button:has-text('Sign in'), button:has-text('Login')").first().click();
            page.waitForTimeout(2000);

            // STEP 3 & 4: Mouse hover on "Manage" and Click "Manage Category"
            System.out.println("[Step 3 & 4] Hovering Manage and clicking Manage Categories...");
            page = navigateToManageCategory(context, page);

            // STEP 5: Add a new category with name AWS
            System.out.println("[Step 5] Adding a new category with name 'AWS'...");
            page.onceDialog(dialog -> {
                System.out.println("   -> Prompt dialog detected: " + dialog.message());
                dialog.accept("AWS");
            });
            page.locator("button:has-text('Add New Category')").click();
            page.waitForTimeout(2000);

            // STEP 6: Verify AWS appears in table
            System.out.println("[Step 6] Verifying that 'AWS' appears in table...");
            Locator awsRow = page.locator("tr").filter(new Locator.FilterOptions().setHasText("AWS"));
            awsRow.first().waitFor();
            assertTrue(awsRow.count() > 0, "AWS category should appear in category table");
            System.out.println("   -> PASSED: AWS appears in category table!");

            // STEP 7: Click Menu and select Signout
            System.out.println("[Step 7] Clicking on Menu and selecting Signout...");
            page.locator("img[alt='menu']").first().click();
            page.locator("button:has-text('Sign out')").first().click();
            page.waitForTimeout(1500);

            // STEP 8: Click on New user? Signup
            System.out.println("[Step 8] Clicking on 'New user? Signup'...");
            page.locator("a:has-text('New user? Signup')").first().click();
            page.waitForTimeout(1500);

            // STEP 9: Verify AWS present under "Category" section
            System.out.println("[Step 9] Verifying that 'AWS' is present under Category section on Signup page...");
            page.locator("body").waitFor();
            boolean isAwsOnSignup = page.locator("body").innerText().contains("AWS");
            assertTrue(isAwsOnSignup, "AWS category should be present under Category section");
            System.out.println("   -> PASSED: AWS present under Category section!");

            // STEP 10: Navigate back to login page
            System.out.println("[Step 10] Navigating back to login page...");
            page.navigate("https://freelance-learn-automation.vercel.app/login");
            page.waitForTimeout(1500);

            // STEP 11: Login again using credentials
            System.out.println("[Step 11] Logging in again as admin@email.com...");
            page.locator("#email1, input[type='email']").first().fill("admin@email.com");
            page.locator("#password1, input[type='password']").first().fill("admin@123");
            page.locator("button:has-text('Sign in'), button:has-text('Login')").first().click();
            page.waitForTimeout(2000);

            // STEP 12 & 13: Mouse hover on "Manage" and Click "Manage Category"
            System.out.println("[Step 12 & 13] Hovering Manage and clicking Manage Categories...");
            page = navigateToManageCategory(context, page);

            // STEP 14 & 15: Click Update next to AWS category & update category name from AWS to Azure
            System.out.println("[Step 14 & 15] Clicking Update next to AWS and updating name to 'Azure'...");
            page.onceDialog(dialog -> {
                System.out.println("   -> Prompt dialog detected: " + dialog.message());
                dialog.accept("Azure");
            });
            page.locator("tr").filter(new Locator.FilterOptions().setHasText("AWS")).first()
                .locator("button:has-text('Update')").click();
            page.waitForTimeout(2000);

            // STEP 16: Verify Azure is updated in table
            System.out.println("[Step 16] Verifying that 'Azure' is updated in table...");
            Locator azureRow = page.locator("tr").filter(new Locator.FilterOptions().setHasText("Azure"));
            azureRow.first().waitFor();
            assertTrue(azureRow.count() > 0, "Azure category should be present in table");
            System.out.println("   -> PASSED: Azure updated in table!");

            // STEP 17: Click Menu and select Signout
            System.out.println("[Step 17] Clicking on Menu and selecting Signout...");
            page.locator("img[alt='menu']").first().click();
            page.locator("button:has-text('Sign out')").first().click();
            page.waitForTimeout(1500);

            // STEP 18: Click New user? Signup
            System.out.println("[Step 18] Clicking on 'New user? Signup'...");
            page.locator("a:has-text('New user? Signup')").first().click();
            page.waitForTimeout(1500);

            // STEP 19: Verify Azure is present under "Category" section
            System.out.println("[Step 19] Verifying that 'Azure' is present under Category section on Signup page...");
            page.locator("body").waitFor();
            boolean isAzureOnSignup = page.locator("body").innerText().contains("Azure");
            assertTrue(isAzureOnSignup, "Azure category should be present under Category section");
            System.out.println("   -> PASSED: Azure present under Category section!");

            // STEP 20: Navigate back to login page
            System.out.println("[Step 20] Navigating back to login page...");
            page.navigate("https://freelance-learn-automation.vercel.app/login");
            page.waitForTimeout(1500);

            // STEP 21: Login again using credentials
            System.out.println("[Step 21] Logging in again as admin@email.com...");
            page.locator("#email1, input[type='email']").first().fill("admin@email.com");
            page.locator("#password1, input[type='password']").first().fill("admin@123");
            page.locator("button:has-text('Sign in'), button:has-text('Login')").first().click();
            page.waitForTimeout(2000);

            // STEP 22 & 23: Mouse hover on "Manage" and Click "Manage Category"
            System.out.println("[Step 22 & 23] Hovering Manage and clicking Manage Categories...");
            page = navigateToManageCategory(context, page);

            // STEP 24 & 25: Click Delete next to Azure category & Delete category
            System.out.println("[Step 24 & 25] Clicking Delete next to Azure and confirming deletion...");
            page.locator("tr").filter(new Locator.FilterOptions().setHasText("Azure")).first()
                .locator("button.delete-btn").click();
            page.waitForTimeout(1000);

            // Confirm delete in React Modal
            page.locator(".modal-footer button:has-text('Delete')").click();
            page.waitForTimeout(2000);

            // Verify Azure is deleted from table
            boolean isAzureInTable = page.locator("tr").filter(new Locator.FilterOptions().setHasText("Azure")).count() > 0;
            assertFalse(isAzureInTable, "Azure category should be deleted from table");
            System.out.println("   -> PASSED: Azure deleted from table!");

            // STEP 26: Click Menu and select Signout
            System.out.println("[Step 26] Clicking on Menu and selecting Signout...");
            page.locator("img[alt='menu']").first().click();
            page.locator("button:has-text('Sign out')").first().click();
            page.waitForTimeout(1500);

            // STEP 27: Click New user? Signup
            System.out.println("[Step 27] Clicking on 'New user? Signup'...");
            page.locator("a:has-text('New user? Signup')").first().click();
            page.waitForTimeout(1500);

            // STEP 28: Verify Azure is no longer present under "Category" section
            System.out.println("[Step 28] Verifying that 'Azure' is NO LONGER present under Category section on Signup page...");
            boolean isAzureStillOnSignup = page.locator("body").innerText().contains("Azure");
            assertFalse(isAzureStillOnSignup, "Azure category should no longer be present under Category section");
            System.out.println("   -> PASSED: Azure successfully verified absent from Signup page!");

            // STEP 29: Close browser
            System.out.println("[Step 29] Closing browser...");
            context.close();
            browser.close();

            System.out.println("\n=================================================");
            System.out.println(" ASSIGNMENT EXECUTED SUCCESSFULLY! (All 29 Steps)");
            System.out.println("=================================================");
        }
    }
}
