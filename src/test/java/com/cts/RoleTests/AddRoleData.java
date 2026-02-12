package com.cts.RoleTests;

import org.testng.annotations.DataProvider;

public class AddRoleData {

    @DataProvider(name = "addRoleData")
    public Object[][] addRoleCredentials() {
        return new Object[][] {{"7777", "Bank@123"}};
    }
}
