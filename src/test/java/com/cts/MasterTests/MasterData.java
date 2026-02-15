package com.cts.MasterTests;

import org.testng.annotations.DataProvider;

public class MasterData {

    @DataProvider(name = "addMasterData")
    public Object[][] addRoleCredentials() {
        return new Object[][] {{"1001", "Bank@123"}};
    }
}
