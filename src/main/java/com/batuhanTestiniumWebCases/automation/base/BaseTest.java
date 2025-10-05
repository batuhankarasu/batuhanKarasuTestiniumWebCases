package com.batuhanTestiniumWebCases.automation.base;

import com.batuhanTestiniumWebCases.automation.config.ConfigReader;
import com.batuhanTestiniumWebCases.automation.config.Settings;
import com.batuhanTestiniumWebCases.automation.driver.CurrentPageContext;
import com.batuhanTestiniumWebCases.automation.driver.DriverFactory;
import com.batuhanTestiniumWebCases.automation.driver.LocalDriverContext;

import com.batuhanTestiniumWebCases.automation.driver.PageGenarator;
import com.batuhanTestiniumWebCases.automation.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;
import java.time.Duration;

public abstract class BaseTest {
    PageGenarator base = new PageGenarator();
    @BeforeAll
    public static void initializeSuite() throws IOException {
        ConfigReader.readBrowserConfig();
    }

    @BeforeEach
    void beforeEachTest() {
        LocalDriverContext.setDriver(DriverFactory.InitializeBrowser());
        LocalDriverContext.getDriver().get(Settings.BaseURL);
        LocalDriverContext.getDriver().manage().window().maximize();
        LocalDriverContext.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
    }

    @AfterEach
    void afterEachTest() {
        if (LocalDriverContext.getDriver() != null) {
            LocalDriverContext.getDriver().quit();
        }
        CurrentPageContext.clearCurrentPage();
    }

    public HomePage homePage() {
        CurrentPageContext.setCurrentPage(base.GetInstance(HomePage.class));
        return CurrentPageContext.getCurrentPage().As(HomePage.class);
    }

    public SearchPage searchPage() {
        CurrentPageContext.setCurrentPage(base.GetInstance(SearchPage.class));
        return CurrentPageContext.getCurrentPage().As(SearchPage.class);
    }

    public ProductPage productPage() {
        CurrentPageContext.setCurrentPage(base.GetInstance(ProductPage.class));
        return CurrentPageContext.getCurrentPage().As(ProductPage.class);
    }
    public CommonPage commonPage() {
        CurrentPageContext.setCurrentPage(base.GetInstance(CommonPage.class));
        return CurrentPageContext.getCurrentPage().As(CommonPage.class);
    }
    public BasketPage basketPage() {
        CurrentPageContext.setCurrentPage(base.GetInstance(BasketPage.class));
        return CurrentPageContext.getCurrentPage().As(BasketPage.class);
    }

}
