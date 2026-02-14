package com.cts.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.athena.BasePage;

public class CtsDashboardPage extends BasePage {

    private Page page;

    private Locator logoutButton;

    public CtsDashboardPage(Page page) {
        super(page);
        this.page = page;
        this.logoutButton = page.locator(".header svg").nth(2);
    }

    public CtsDashboardPage verifyDashboardPageNavigation() {
        page.waitForURL("**/dashboard");

        return this;
    }

    public LoginPage logoutFromCts() {
        logoutButton.click();
        page.waitForURL("**/sign-in");

        return new LoginPage(page);
    }
}
