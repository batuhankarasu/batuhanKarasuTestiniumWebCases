package com.batuhanTestiniumWebCases.automation.pages;

import com.batuhanTestiniumWebCases.automation.driver.PageGenarator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CommonPage extends PageGenarator {
    @FindBy(how = How.CSS, using = "[title='Sepetim']")
    private static WebElement basketBtn;
    @FindBy(how = How.CSS, using = "[title='Favorilerim']")
    private static WebElement favoriteBtn;


    public void clickHeaderMenuBtn(String menuName) {
        switch (menuName) {
            case "favori":
                findScrollElementCenter(favoriteBtn);
                clickElement(favoriteBtn);
                break;

            case "basket":
                findScrollElementCenter(basketBtn);
                clickElement(basketBtn);
                break;
            default:
                fail("Unknown menuName: " + menuName);
        }
        info("Clicked on the " + menuName + " menu.");
    }
}
