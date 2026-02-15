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

import static com.cts.constants.UserConstants.*;

public class UserTests extends BaseTests {

    private ThreadLocal<String> loginAccess = new ThreadLocal<>();
    private ThreadLocal<Playwright> getPlaywright = new ThreadLocal<>();

    @Epic("Access Management")
    @Feature("Users")
    @Story("Add New User")
    @Test(dataProviderClass = AddUserData.class, dataProvider = "addUserData",
            description = "Verify that a new user can be added successfully")
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

    @Epic("Access Management")
    @Feature("Users")
    @Story("Add New User")
    @Test(dataProviderClass = AddUserData.class, dataProvider = "addUserData",
            description = "Verify that user creation can be cancelled and the user is not added to the list")
    public void checkCancelUserCreation(String userId, String password) {
        getPlaywright.set(playwright);
        LoginPage loginPage = new LoginPage(page);
        loginAccess.set(loginPage.loginToCTS(userId, password));
        CtsDashboardPage dashboardPage = new CtsDashboardPage(page);
        dashboardPage
                .verifyDashboardPageNavigation();
        navigateTo(String.valueOf(PagenameEnums.users));
        UsersPage usersPage = new UsersPage(page);
        String addedUserId = usersPage
                .cancelUserCreation();
        usersPage
                .verifyUserNotAdded(addedUserId);

    }

    @Epic("Access Management")
    @Feature("Users")
    @Story("Add New User")
    @Test(dataProviderClass = AddUserData.class, dataProvider = "addUserData",
            description = "Verify that appropriate error message is displayed when role ID is not selected")
    public void checkRoleIdErrorMessage(String userId, String password) {
        getPlaywright.set(playwright);
        LoginPage loginPage = new LoginPage(page);
        loginAccess.set(loginPage.loginToCTS(userId, password));
        CtsDashboardPage dashboardPage = new CtsDashboardPage(page);
        dashboardPage
                .verifyDashboardPageNavigation();
        navigateTo(String.valueOf(PagenameEnums.users));
        UsersPage usersPage = new UsersPage(page);
        usersPage
                .clickAddUserButtonInHeader()
                .clickAddUserButton();
        usersPage.verifyMandatoryFieldErrorMessage(ROLE_ID_ERROR_MESSAGE);
    }

    @Epic("Access Management")
    @Feature("Users")
    @Story("Add New User")
    @Test(dataProviderClass = AddUserData.class, dataProvider = "addUserData",
            description = "Verify that appropriate error message is displayed when User ID is not Entered")
    public void checkUserIdErrorMessage(String userId, String password) {
        getPlaywright.set(playwright);
        LoginPage loginPage = new LoginPage(page);
        loginAccess.set(loginPage.loginToCTS(userId, password));
        CtsDashboardPage dashboardPage = new CtsDashboardPage(page);
        dashboardPage
                .verifyDashboardPageNavigation();
        navigateTo(String.valueOf(PagenameEnums.users));
        UsersPage usersPage = new UsersPage(page);
        usersPage
                .clickAddUserButtonInHeader()
                .clickAddUserButton();
        usersPage.verifyMandatoryFieldErrorMessage(USER_ID_ERROR_MESSAGE);
    }

    @Epic("Access Management")
    @Feature("Users")
    @Story("Add New User")
    @Test(dataProviderClass = AddUserData.class, dataProvider = "addUserData",
            description = "Verify that appropriate error message is displayed when User Name is not Entered")
    public void checkUserNameErrorMessage(String userId, String password) {
        getPlaywright.set(playwright);
        LoginPage loginPage = new LoginPage(page);
        loginAccess.set(loginPage.loginToCTS(userId, password));
        CtsDashboardPage dashboardPage = new CtsDashboardPage(page);
        dashboardPage
                .verifyDashboardPageNavigation();
        navigateTo(String.valueOf(PagenameEnums.users));
        UsersPage usersPage = new UsersPage(page);
        usersPage
                .clickAddUserButtonInHeader()
                .clickAddUserButton();
        usersPage.verifyMandatoryFieldErrorMessage(USER_NAME_ERROR_MESSAGE);
    }

    @Epic("Access Management")
    @Feature("Users")
    @Story("Add New User")
    @Test(dataProviderClass = AddUserData.class, dataProvider = "addUserData",
            description = "Verify that appropriate error message is displayed when Password is not Entered")
    public void checkPasswordErrorMessage(String userId, String password) {
        getPlaywright.set(playwright);
        LoginPage loginPage = new LoginPage(page);
        loginAccess.set(loginPage.loginToCTS(userId, password));
        CtsDashboardPage dashboardPage = new CtsDashboardPage(page);
        dashboardPage
                .verifyDashboardPageNavigation();
        navigateTo(String.valueOf(PagenameEnums.users));
        UsersPage usersPage = new UsersPage(page);
        usersPage
                .clickAddUserButtonInHeader()
                .clickAddUserButton();
        usersPage.verifyMandatoryFieldErrorMessage(PASSWORD_ERROR_MESSAGE);
    }

    @Epic("Access Management")
    @Feature("Users")
    @Story("Add New User")
    @Test(dataProviderClass = AddUserData.class, dataProvider = "addUserData",
            description = "Verify that appropriate error message is displayed when Email ID is not Entered")
    public void checkEmailIdErrorMessage(String userId, String password) {
        getPlaywright.set(playwright);
        LoginPage loginPage = new LoginPage(page);
        loginAccess.set(loginPage.loginToCTS(userId, password));
        CtsDashboardPage dashboardPage = new CtsDashboardPage(page);
        dashboardPage
                .verifyDashboardPageNavigation();
        navigateTo(String.valueOf(PagenameEnums.users));
        UsersPage usersPage = new UsersPage(page);
        usersPage
                .clickAddUserButtonInHeader()
                .clickAddUserButton();
        usersPage.verifyMandatoryFieldErrorMessage(EMAIL_ID_ERROR_MESSAGE);
    }

    @Epic("Access Management")
    @Feature("Users")
    @Story("Add New User")
    @Test(dataProviderClass = AddUserData.class, dataProvider = "addUserData",
            description = "Verify that appropriate error message is displayed when Contact Number is not Entered")
    public void checkContactNumberErrorMessage(String userId, String password) {
        getPlaywright.set(playwright);
        LoginPage loginPage = new LoginPage(page);
        loginAccess.set(loginPage.loginToCTS(userId, password));
        CtsDashboardPage dashboardPage = new CtsDashboardPage(page);
        dashboardPage
                .verifyDashboardPageNavigation();
        navigateTo(String.valueOf(PagenameEnums.users));
        UsersPage usersPage = new UsersPage(page);
        usersPage
                .clickAddUserButtonInHeader()
                .clickAddUserButton();
        usersPage.verifyMandatoryFieldErrorMessage(CONTACT_NUMBER_ERROR_MESSAGE);
    }

    @AfterMethod(alwaysRun = true)
    public void clean() {
        CtsApiClient ctsApiClient = new CtsApiClient(getPlaywright.get(), loginAccess.get());
        ctsApiClient.clearCtsContextAndLogout();
    }
}
