package com.batuhanTestiniumWebCases.automation.driver;

import com.batuhanTestiniumWebCases.automation.base.BaseMethod;
import org.openqa.selenium.support.PageFactory;

public class PageGenarator extends BaseMethod {

    public PageGenarator() {
    }

    public <TPage> TPage GetInstance(Class<TPage> page) {
        Object obj = PageFactory.initElements(LocalDriverContext.getDriver(), page);
        return page.cast(obj);
    }

    public <TPage> TPage As(Class<TPage> pageInstance) {

        try {
            return (TPage) this;
        } catch (Exception var3) {
            var3.getStackTrace();
            return null;
        }
    }
}
