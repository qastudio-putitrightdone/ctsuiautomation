package com.cts.UserTests;

import org.testng.annotations.DataProvider;

public class AddUserData {

    @DataProvider(name = "addUserData")
    public Object[][] addRoleCredentials() {
        return new Object[][] {{"1001", "Bank@123"}};
    }
}
