package com.cts.ReportsManagement;

import org.testng.annotations.DataProvider;

public class ReportsData {

    @DataProvider(name = "reportsData")
    public Object[][] addRoleCredentials() {
        return new Object[][] {{"1001", "Bank@123"}};
    }
}
