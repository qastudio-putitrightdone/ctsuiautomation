package com.cts.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.athena.BasePage;

public class LoginPage extends BasePage {

    private Page page;

    private Locator userIdInput;
    private Locator passwordInput;
    private Locator loginButton;

    public LoginPage(Page page) {
        super(page);
        this.page = page;
        this.userIdInput = page.locator("input[name='userId']");
        this.passwordInput = page.locator("input[name='password']");
        this.loginButton = page.locator("button[type='submit']");
    }

    @Step("Entering user ID: {userId}")
    private void enterUserId(String userId) {
        userIdInput.clear();
        userIdInput.fill(userId);
    }

    @Step("Entering password")
    private void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.fill(password);
    }

    @Step("Clicking login button")
    private void clickLogin() {
        loginButton.click();
    }

    @Step("Logging in to CTS with user ID: {userId}")
    public CtsDashboardPage loginToCTS(String userId, String password) {
        enterUserId(userId);
        enterPassword(password);
        clickLogin();
        return new CtsDashboardPage(page);
    }
}
