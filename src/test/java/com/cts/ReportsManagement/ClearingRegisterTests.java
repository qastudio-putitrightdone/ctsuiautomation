package com.cts.ReportsManagement;

import com.base.BaseTests;
import com.cts.api.CtsApiClient;
import com.cts.pages.ClearingRegisterPage;
import com.cts.pages.CtsDashboardPage;
import com.cts.pages.LoginPage;
import com.cts.utils.PDFComparisonUtil;
import com.cts.utils.PagenameEnums;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static com.cts.constants.ReportsConstant.PDF;

public class ClearingRegisterTests extends BaseTests {

    private ThreadLocal<String> loginAccess = new ThreadLocal<>();
    private ThreadLocal<Playwright> getPlaywright = new ThreadLocal<>();

    @Test(dataProviderClass = ReportsData.class, dataProvider = "reportsData")
    public void checkClearingReportExport(String userId, String password) {
        getPlaywright.set(playwright);
        LoginPage loginPage = new LoginPage(page);
        loginAccess.set(loginPage.loginToCTS(userId, password));
        CtsDashboardPage dashboardPage = new CtsDashboardPage(page);
        dashboardPage
                .verifyDashboardPageNavigation();
        navigateTo(String.valueOf(PagenameEnums.inward_clering_register));
        ClearingRegisterPage clearingRegister = new ClearingRegisterPage(page);
        clearingRegister
                .setFromDate()
                .setToDate()
                .clickSubmitButton()
                .clickExportButton()
                .selectExportFormat(PDF)
                .clickOkToInitiateExport();
        ArrayList<String> firstRow = clearingRegister.getFirstRowData();
        PDFComparisonUtil pdfComparisonUtil = new PDFComparisonUtil();
        Assert.assertTrue(pdfComparisonUtil.verifyPDFDataAvailableInFile(firstRow));
    }

    @AfterMethod(alwaysRun = true)
    public void clean() {
        CtsApiClient ctsApiClient = new CtsApiClient(getPlaywright.get(), loginAccess.get());
        ctsApiClient.clearCtsContextAndLogout();
    }
}
