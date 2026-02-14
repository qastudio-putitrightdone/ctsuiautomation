package com.cts.UserTests;

import com.base.BaseTests;
import com.cts.api.CtsApiClient;
import com.cts.pages.CtsDashboardPage;
import com.cts.pages.LoginPage;
import com.cts.pages.UsersPage;
import com.cts.utils.PagenameEnums;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class UserTests extends BaseTests {

    private ThreadLocal<String> loginAccess = new ThreadLocal<>();
    private ThreadLocal<Playwright> getPlaywright = new ThreadLocal<>();

    @Epic("Access Management")
    @Feature("Users")
    @Story("Add New User")
    @Test(dataProviderClass = AddUserData.class, dataProvider = "addUserData")
    public void checkAddNewAdminUser(String userId, String password) {
        getPlaywright.set(playwright);
        LoginPage loginPage = new LoginPage(page);
        loginAccess.set(loginPage.loginToCTS(userId, password));
        CtsDashboardPage dashboardPage = new CtsDashboardPage(page);
        dashboardPage
                .verifyDashboardPageNavigation();
        navigateTo(String.valueOf(PagenameEnums.users));
        UsersPage usersPage = new UsersPage(page);
        String addedUserId = usersPage
                .createRandomAdminUser();
        usersPage
                .verifyUserAdded(addedUserId);
    }

    @AfterMethod(alwaysRun = true)
    public void clean() {
        CtsApiClient ctsApiClient = new CtsApiClient(getPlaywright.get(), loginAccess.get());
        ctsApiClient.clearCtsContextAndLogout();
    }
}
