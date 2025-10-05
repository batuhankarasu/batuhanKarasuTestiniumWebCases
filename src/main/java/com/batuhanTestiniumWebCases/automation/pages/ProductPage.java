package com.batuhanTestiniumWebCases.automation.pages;

import com.batuhanTestiniumWebCases.automation.driver.PageGenarator;
import com.batuhanTestiniumWebCases.automation.model.ProductModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ProductPage extends PageGenarator {
    private static final By productList = By.cssSelector(".o-productList__itemWrapper");
    private static final By activeSizeList  = By.xpath("//span[@class='m-variation__item' or @class='m-variation__item -active -criticalStock']");

    @FindBy(how = How.CSS, using = ".o-productDetail__brandLink")
    private static WebElement productDetailBrand;
    @FindBy(how = How.CSS, using = ".o-productDetail__description")
    private static WebElement productDetailDesc;
    @FindBy(how = How.ID, using = "priceNew")
    private static WebElement productNewPrice;
    @FindBy(how = How.ID, using = "addBasket")
    private static WebElement addBasket;
    @FindBy(how = How.CSS, using = ".m-notification__button.btn")
    private static WebElement addToBasketNotification;


    public void clickRandomProduct(){
        List<WebElement> products = findElements(productList);
        assertFalse(products.isEmpty(), "Product list is empty.");
        int randomIndex = getRandomIndex(products.size());
        findScrollElementCenter(products.get(randomIndex));
        clickElement(products.get(randomIndex));
        waitForPageToLoad();
        info("Clicked on a random product.");
    }

    public ProductModel setProductDetail(){
        ProductModel selectedProduct = new ProductModel();
        selectedProduct.setBrand(getText(productDetailBrand));
        selectedProduct.setDescription(getText(productDetailDesc));
        selectedProduct.setPrice(Double.parseDouble(getText(productNewPrice).replace(" TL", "")
                .replace(".", "")
                .replace(",", ".")));
        info("Product detail information has been set.");
        return selectedProduct;
    }

    public void selectRandomSize(){
        List<WebElement> sizeList = findElements(activeSizeList);
        assertFalse(sizeList.isEmpty(), "Size list is empty.");
        int randomIndex = getRandomIndex(sizeList.size());
        findScrollElementCenter(sizeList.get(randomIndex));
        clickElement(sizeList.get(randomIndex));
        waitForPageToLoad();
        info("Clicked on a random size.");
    }

    public void clickAddToBasket(){
        clickElement(addBasket);
        waitForPageToLoad();
        info("Added to the cart.");
    }

    public void verifyAddToBasketNotificationButton(){
        assertVisible(addToBasketNotification,"Add to basket notification message is not visible.");
        assertEquals(getText(addToBasketNotification).trim(),"SEPETE GIT","Add to basket notification button verification completed.");
        waitUntilInvisible(addToBasketNotification,10);
        info("Add to basket notification button verification completed.");
    }

}
