package com.base;

import com.cts.utils.PagenameEnums;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.athena.LaunchBrowser;
import org.athena.ReadConfigData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.Properties;

import static com.cts.utils.NavigateUtils.*;

public class BaseTests {

    LaunchBrowser launchBrowser = new LaunchBrowser();

    protected Page page;
    protected BrowserContext browserContext;
    protected Playwright playwright;

    @BeforeMethod
    public void beforeMethod() {
        List<Object> playwrightObjects = launchBrowser
                .initiateBrowserAndApplication(fetchBaseConfigData().getProperty("browser"),
                        fetchBaseConfigData().getProperty("url"));
        page = (Page) playwrightObjects.get(2);
        browserContext = (BrowserContext) playwrightObjects.get(1);
        playwright = (Playwright) playwrightObjects.get(0);
        page.setDefaultTimeout(30000);
        PlaywrightAssertions.setDefaultAssertionTimeout(15000);
    }

    private Properties fetchBaseConfigData() {
        String filePath = System.getProperty("user.dir") + "/src/main/resources/config.properties";
        ReadConfigData readConfigData = new ReadConfigData();
        return readConfigData.fetchConfigData(filePath);
    }

    public void navigateTo(String pageName) {
        if (pageName.equals(String.valueOf(PagenameEnums.user_role))) {
            page.navigate(user_role_page_url);
        } else if (pageName.equals(String.valueOf(PagenameEnums.users))) {
            page.navigate(users_page_url);
        } else if (pageName.equals(String.valueOf(PagenameEnums.return_reason))) {
            page.navigate(return_reason_page_url);
        }
    }

    @AfterMethod
    public void closeContext() {
        if (page != null) {
            page.close();
        }
    }
}
