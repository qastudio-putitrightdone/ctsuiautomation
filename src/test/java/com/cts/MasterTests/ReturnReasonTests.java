package com.cts.MasterTests;

import com.base.BaseTests;
import com.cts.api.CtsApiClient;
import com.cts.pages.CtsDashboardPage;
import com.cts.pages.LoginPage;
import com.cts.pages.ReturnReasonPage;
import com.cts.utils.PagenameEnums;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.cts.constants.MasterConstants.instrument_post_dated;
import static com.cts.utils.TestData.generateRandomNumber;

public class ReturnReasonTests extends BaseTests {

    private ThreadLocal<String> loginAccess = new ThreadLocal<>();
    private ThreadLocal<Playwright> getPlaywright = new ThreadLocal<>();

    @Epic("Master Management")
    @Feature("Return Reason Master")
    @Story("Reason Availability")
    @Test(dataProviderClass = MasterData.class, dataProvider = "addMasterData")
    public void checkReturnReasonAvailable(String userId, String password) {
        getPlaywright.set(playwright);
        LoginPage loginPage = new LoginPage(page);
        loginAccess.set(loginPage.loginToCTS(userId, password));
        CtsDashboardPage dashboardPage = new CtsDashboardPage(page);
        dashboardPage
                .verifyDashboardPageNavigation();
        navigateTo(String.valueOf(PagenameEnums.return_reason));
        ReturnReasonPage returnReasonPage = new ReturnReasonPage(page);
        Assert.assertTrue(returnReasonPage.checkReturnReasonDisplayed(instrument_post_dated));
    }

    @Epic("Master Management")
    @Feature("Return Reason Master")
    @Story("Reason Update")
    @Test(dataProviderClass = MasterData.class, dataProvider = "addMasterData")
    public void checkChargeAmountChange(String userId, String password) {
        getPlaywright.set(playwright);
        LoginPage loginPage = new LoginPage(page);
        loginAccess.set(loginPage.loginToCTS(userId, password));
        CtsDashboardPage dashboardPage = new CtsDashboardPage(page);
        dashboardPage
                .verifyDashboardPageNavigation();
        navigateTo(String.valueOf(PagenameEnums.return_reason));
        ReturnReasonPage returnReasonPage = new ReturnReasonPage(page);
        returnReasonPage.selectSpecificReason(instrument_post_dated);
        String updatedAmount = generateRandomNumber(4);
        returnReasonPage
                .updateChargeAmount(updatedAmount)
                .clickUpdateButton()
                .verifyChargeAmountUpdated(updatedAmount);
    }

    @AfterMethod(alwaysRun = true)
    public void clean() {
        CtsApiClient ctsApiClient = new CtsApiClient(getPlaywright.get(), loginAccess.get());
        ctsApiClient.clearCtsContextAndLogout();
    }
}
