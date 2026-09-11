package PlaywrightWaits;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightWaitExample {

	public static void main(String[] args) {

		// Create Playwright
		Playwright playwright = Playwright.create();

		// Open Chrome
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

		// Create page
		Page page = browser.newPage();

		// Open website
		page.navigate("https://www.saucedemo.com/");

		System.out.println("Website opened successfully");

		// Wait for username field
		page.waitForSelector("#user-name");

		// Enter username
		page.locator("#user-name").fill("standard_user");

		// Wait for password field
		page.waitForSelector("#password");

		// Enter password
		page.locator("#password").fill("secret_sauce");

		// Wait for login button
		page.waitForSelector("#login-button");

		// Click login
		page.locator("#login-button").click();

		// Wait until URL changes to inventory page
		page.waitForURL("**/inventory.html");

		System.out.println("Login successful");

		// Wait for products page
		page.waitForSelector(".title");

		// Verify page title
		String title = page.locator(".title").innerText();

		System.out.println("Page Title: " + title);

		// Wait for menu button
		page.waitForSelector("#react-burger-menu-btn");

		// Click menu
		page.locator("#react-burger-menu-btn").click();

		// Wait for logout link
		page.waitForSelector("#logout_sidebar_link");

		System.out.println("Menu opened successfully");

		// Click logout
		page.locator("#logout_sidebar_link").click();

		// Wait for login page
		page.waitForSelector("#login-button");

		System.out.println("Logout successful");

		// Close browser
		browser.close();

		// Close Playwright
		playwright.close();
	}
}