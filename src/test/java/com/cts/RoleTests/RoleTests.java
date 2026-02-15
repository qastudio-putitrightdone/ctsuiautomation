package com.cts.RoleTests;

import com.base.BaseTests;
import com.cts.api.CtsApiClient;
import com.cts.pages.CtsDashboardPage;
import com.cts.pages.LoginPage;
import com.cts.pages.RolePage;
import com.cts.utils.PagenameEnums;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.cts.utils.TestData.generateRandomValue;

public class RoleTests extends BaseTests {

    private ThreadLocal<String> loginAccess = new ThreadLocal<>();
    private ThreadLocal<Playwright> getPlaywright = new ThreadLocal<>();

    @Epic("Access Management")
    @Feature("Roles")
    @Story("Add New Role")
    @Test(dataProviderClass = AddRoleData.class, dataProvider = "addRoleData",
            description = "Verify that a new role can be added successfully")
    public void checkAddNewRole(String userId, String password) {
        getPlaywright.set(playwright);
        LoginPage loginPage = new LoginPage(page);
        loginAccess.set(loginPage.loginToCTS(userId, password));
        CtsDashboardPage dashboardPage = new CtsDashboardPage(page);
        dashboardPage
                .verifyDashboardPageNavigation();
        navigateTo(String.valueOf(PagenameEnums.user_role));
        RolePage rolePage = new RolePage(page);
        String newRoleName = generateRandomValue(6);
        rolePage
                .addNewRole(newRoleName);
        rolePage
                .verifyRoleAdded(newRoleName);
    }

    @Epic("Access Management")
    @Feature("Roles")
    @Story("Add New Role")
    @Test(dataProviderClass = AddRoleData.class, dataProvider = "addRoleData",
            description = "Verify that a new role can be added successfully")
    public void checkMandatoryFieldErrorMessage(String userId, String password) {
        getPlaywright.set(playwright);
        LoginPage loginPage = new LoginPage(page);
        loginAccess.set(loginPage.loginToCTS(userId, password));
        CtsDashboardPage dashboardPage = new CtsDashboardPage(page);
        dashboardPage
                .verifyDashboardPageNavigation();
        navigateTo(String.valueOf(PagenameEnums.user_role));
        RolePage rolePage = new RolePage(page);
        rolePage.clickAddRoleButtonInHeader();
        rolePage.clickAddRoleButton();
        rolePage.verifyMandatoryFieldErrorMessage();
    }

    @Epic("Access Management")
    @Feature("Roles")
    @Story("Add New Role")
    @Test(dataProviderClass = AddRoleData.class, dataProvider = "addRoleData",
            description = "Verify that a new role addition can be cancelled successfully")
    public void checkCancelAddNewRole(String userId, String password) {
        getPlaywright.set(playwright);
        LoginPage loginPage = new LoginPage(page);
        loginAccess.set(loginPage.loginToCTS(userId, password));
        CtsDashboardPage dashboardPage = new CtsDashboardPage(page);
        dashboardPage
                .verifyDashboardPageNavigation();
        navigateTo(String.valueOf(PagenameEnums.user_role));
        RolePage rolePage = new RolePage(page);
        String newRoleName = generateRandomValue(6);
        rolePage
                .cancelNewRole(newRoleName);
        rolePage
                .verifyRoleNotAdded(newRoleName);
    }

    @AfterMethod(alwaysRun = true)
    public void clean() {
        CtsApiClient ctsApiClient = new CtsApiClient(getPlaywright.get(), loginAccess.get());
        ctsApiClient.clearCtsContextAndLogout();
    }
}
