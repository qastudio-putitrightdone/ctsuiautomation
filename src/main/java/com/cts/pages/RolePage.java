package com.cts.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.athena.BasePage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RolePage extends BasePage {

    private Page page;

    private Locator addRoleButton;
    private Locator roleNameInput;
    private Locator addButton;
    private Locator roleList;

    public RolePage(Page page) {
        super(page);
        this.page = page;
        this.addRoleButton = page.locator("header button");
        this.roleNameInput = page.locator("input[name='roleName']");
        this.addButton = page.locator("button[type='submit']");
        this.roleList = page.locator("tbody tr");
    }

    @Step("Click on Add Role button in header")
    private void clickAddRoleButtonInHeader() {
        addRoleButton.click();
    }

    @Step("Entering role name: {roleName}")
    private void enterRoleName(String roleName) {
        roleNameInput.clear();
        roleNameInput.fill(roleName);
    }

    @Step("Click on Add button to add new role")
    private void clickAddRoleButton() {
        addButton.click();
    }

    @Step("Adding new role with name: {roleName}")
    public RolePage addNewRole(String roleName) {
        clickAddRoleButtonInHeader();
        enterRoleName(roleName);
        clickAddRoleButton();
        page.waitForCondition(() -> !addButton.isVisible());
        return this;
    }

    @Step("Verifying that role with name: {roleName} is added to the list")
    public void verifyRoleAdded(String roleName) {
        assertThat(roleList.filter(new Locator.FilterOptions().setHasText(roleName))).isVisible();
    }
}
