package test;

import com.batuhanTestiniumWebCases.automation.base.BaseTest;
import com.batuhanTestiniumWebCases.automation.model.ProductModel;
import com.batuhanTestiniumWebCases.automation.utils.ExcelUtils;
import com.batuhanTestiniumWebCases.automation.utils.TxtUtils;
import org.junit.jupiter.api.Test;


public class beymenSearch extends BaseTest {


    @Test
    public void beymenTestCases() {
        homePage().checkUrlIsOpened("https://www.beymen.com/tr");
        homePage().checkPageTitle("Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu");
        homePage().acceptCookiesIfPresent();
        homePage().closeGenderPopupIfPresent();
        searchPage().openSearchBox();
        String firstData = ExcelUtils.readCell("searchData.xlsx", 0, 0, 0);
        searchPage().sendkeySearchBox(firstData);
        searchPage().clearSearchBox();
        String secondData = ExcelUtils.readCell("searchData.xlsx", 0, 1, 0);
        searchPage().sendkeySearchBox(secondData);
        searchPage().pressEnter();
        searchPage().verifySearchText(secondData);
        productPage().clickRandomProduct();
        ProductModel productModel = productPage().setProductDetail();
        TxtUtils.write("src/main/resources/product.txt", "Brand: " + productModel.getBrand(), false);
        TxtUtils.write("src/main/resources/product.txt", "Description: " + productModel.getDescription(), true);
        TxtUtils.write("src/main/resources/product.txt", "Price: " + productModel.getPrice(), true);
        productPage().selectRandomSize();
        productPage().clickAddToBasket();
        productPage().verifyAddToBasketNotificationButton();
        commonPage().clickHeaderMenuBtn("basket");
        basketPage().verifyProductPrice(productModel.getPrice());
        basketPage().selectsProductQuentityDropdown("2");
        basketPage().verifyBasketProductQuentity("2");
        basketPage().clickBasketDeleteBtn();
        basketPage().verifyEmptyBasket();

    }
}
