package com.batuhanTestiniumWebCases.automation.pages;

import com.batuhanTestiniumWebCases.automation.driver.PageGenarator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class HomePage extends PageGenarator {

    @FindBy(how = How.ID, using = "onetrust-accept-btn-handler")
    private static WebElement cookieAccept;
    @FindBy(how = How.CSS, using = "button.o-modal__closeButton")
    private static WebElement genderPopupClose;


    public void acceptCookiesIfPresent() {
        if (checkElementIsDisplayed(cookieAccept, 5)) {
            cookieAccept.click();
            info("Cookie has been accepted.");
        } else {
            info("Cookie message was not displayed.");
        }
    }

    public void closeGenderPopupIfPresent() {
        if (checkElementIsDisplayed(genderPopupClose, 5)) {
            genderPopupClose.click();
            info("Gender selection popup has been closed.");
        } else {
            info("Gender selection popup was not displayed.");
        }
    }
}
