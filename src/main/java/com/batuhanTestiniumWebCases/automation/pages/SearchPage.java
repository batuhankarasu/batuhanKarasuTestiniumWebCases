package com.batuhanTestiniumWebCases.automation.pages;

import com.batuhanTestiniumWebCases.automation.driver.PageGenarator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SearchPage extends PageGenarator {
    @FindBy(how = How.CSS, using = "input.o-header__search--input")
    private WebElement searchInput;
    @FindBy(how = How.ID, using = "o-searchSuggestion__input")
    private WebElement searchInputActive;
    @FindBy(how = How.ID, using = "productListTitle")
    private WebElement productListTitle;


    public void openSearchBox() {
        waitUntilElementClickable(searchInput);
        searchInput.click();
        assertVisible(searchInputActive, "Search input did not become active after clicking.");
        info("Search box opened.");
    }

    public void sendkeySearchBox(String text) {
        sendKeys(searchInputActive, text);
        info(text + "Text has been entered into the search box.");
    }

    public void clearSearchBox() {
        clear(searchInputActive);
        info("Search box has been cleared.");
    }

    public void verifySearchText(String searchText) {
        assertEquals(getText(productListTitle).replaceAll("\"| araması için \\(.*\\) bulundu\\.", "").trim(), searchText, "");
        info("sepet aratılan text kontrolu yapıldı.");
    }
}
