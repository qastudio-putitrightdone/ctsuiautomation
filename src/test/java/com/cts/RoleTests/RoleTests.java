package com.cts.RoleTests;

import com.base.BaseTests;
import com.cts.pages.CtsDashboardPage;
import com.cts.pages.LoginPage;
import com.cts.pages.RolePage;
import com.cts.utils.PagenameEnums;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.cts.utils.TestData.generateRandomValue;

public class RoleTests extends BaseTests {
    CtsDashboardPage dashboardPage = new CtsDashboardPage(page);

    @Test(dataProviderClass = AddRoleData.class, dataProvider = "addRoleData")
    public void checkAddNewRole(String userId, String password) {
        LoginPage loginPage = new LoginPage(page);
        dashboardPage =
                loginPage
                        .loginToCTS(userId, password)
                        .verifyDashboardPageNavigation();
        navigateTo(String.valueOf(PagenameEnums.user_role));
        RolePage rolePage = new RolePage(page);
        String newRoleName = generateRandomValue(6);
        rolePage
                .addNewRole(newRoleName);
        rolePage.verifyRoleAdded(newRoleName);
    }

    @AfterMethod
    public void clean() {
        dashboardPage.logoutFromCts();
    }
}
