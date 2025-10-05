package com.batuhanTestiniumWebCases.automation.pages;

import com.batuhanTestiniumWebCases.automation.driver.PageGenarator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BasketPage extends PageGenarator {
    @FindBy(how = How.CSS, using = ".priceBox__salePrice")
    private static WebElement basketPrice;
    @FindBy(how = How.ID, using = "quantitySelect0-key-0")
    private static WebElement selectProductQuentityDropdown;
    @FindBy(how = How.ID, using = "removeCartItemBtn0-key-0")
    private static WebElement deleteBasketBtn;
    @FindBy(how = How.CSS, using = "#emtyCart .m-empty__messageTitle")
    private static WebElement emptyBasketMessage;

    public void verifyProductPrice(Double price) {
        assertVisible(basketPrice, "Basket price element not visible");
        double basketPriceValue = Double.parseDouble(
                getText(basketPrice).replace(" TL", "").replace(".", "").replace(",", ".")
        );
        assertEquals(price, basketPriceValue, "The price of the product added to the cart is incorrect!");
        info("The price of the product added to the cart has been verified.");
    }

    public void selectsProductQuentityDropdown(String dropdown) {
        selectDropdown(selectProductQuentityDropdown, "value", dropdown);
        info("Product quantity selection has been made. Select value : " + dropdown);
    }

    public void verifyBasketProductQuentity(String value) {
        assertVisible(selectProductQuentityDropdown, "Product basket quantity element not visible");
        assertEquals(getDomAttribute(selectProductQuentityDropdown, "aria-label"), value + " adet", "The selected product quantity is incorrect.");
        info("Product basket quantity verification completed.");
    }

    public void clickBasketDeleteBtn() {
        clickElement(deleteBasketBtn);
        info("The product has been removed from the basket.");
    }

    public void verifyEmptyBasket() {
        assertVisible(emptyBasketMessage, "Empty basket element not visible");
        assertEquals(getText(emptyBasketMessage), "SEPETINIZDE ÜRÜN BULUNMAMAKTADIR", "The empty basket message is incorrect.");
        info("Empty basket verification completed.");
    }
}
