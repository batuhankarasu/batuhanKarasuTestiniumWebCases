package com.batuhanTestiniumWebCases.automation.base;

import com.batuhanTestiniumWebCases.automation.driver.LocalDriverContext;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;


import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

import static com.batuhanTestiniumWebCases.automation.driver.LocalDriverContext.getDriver;

public class BaseMethod {
    final Logger driverContextLogger = LogManager.getLogger(BaseMethod.class);
    int timeOutInSec = 60;

    public void assertVisible(WebElement webElement, String message, int... timeout) {
        int waitTime = (timeout.length > 0) ? timeout[0] : timeOutInSec;
        Assert.assertTrue(message, checkElementIsDisplayed(webElement, waitTime));
    }

    public void assertEquals(Object actual, Object expected, String message) {
        Assert.assertEquals(message, expected, actual);
    }

    public void assertFalse(boolean condition, String message) {
        Assert.assertFalse(message, condition);
    }

    public int getRandomIndex(int size) {
        return (int) (Math.random() * size);
    }


    public void selectDropdown(WebElement dropdownElement, String selectBy, String value) {
        try {
            Select select = new Select(dropdownElement);

            switch (selectBy.toLowerCase()) {
                case "text":
                    select.selectByVisibleText(value);
                    break;

                case "value":
                    select.selectByValue(value);
                    break;

                case "index":
                    select.selectByIndex(Integer.parseInt(value));
                    break;

                default:
                    fail("Invalid selection type: " + selectBy);
            }
        } catch (Exception e) {
            fail("Dropdown selection failed! Error: " + e.getMessage());
        }
    }

    public boolean checkElementIsDisplayed(WebElement element, Integer... timeoutSec) {
        int waitTime = (timeoutSec.length > 0) ? timeoutSec[0] : timeOutInSec;
        try {
            new WebDriverWait(getDriver(), java.time.Duration.ofSeconds(waitTime))
                    .until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickElement(WebElement element, Integer... timeoutSec) {
        int waitTime = (timeoutSec.length > 0) ? timeoutSec[0] : timeOutInSec;
        try {
            new WebDriverWait(getDriver(), java.time.Duration.ofSeconds(waitTime))
                    .until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            fail("Element could not be clicked: " + e.getMessage());
        }
    }

    public void waitUntilInvisible(WebElement element, int ...timeoutSec) {
        try {
            int waitTime = (timeoutSec.length > 0) ? timeoutSec[0] : timeOutInSec;

            WebDriverWait wait = new WebDriverWait(getDriver(), java.time.Duration.ofSeconds(waitTime));
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            fail("Element did not become invisible within the specified timeout: " + e.getMessage());
        }
    }
    public void waitForPageToLoad() {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriverContext.getDriver(), Duration.ofSeconds(20));
            JavascriptExecutor jsExecutor = (JavascriptExecutor) LocalDriverContext.getDriver();
            ExpectedCondition jsLoad = (webDriver) -> {
                return jsExecutor.executeScript("return document.readyState", new Object[0]).toString().equals("complete");
            };

            String jsReadyState;
            do {
                wait.until(jsLoad);
                jsReadyState = jsExecutor.executeScript("return document.readyState", new Object[0]).toString();
            } while (!jsReadyState.equalsIgnoreCase("complete"));

            ExpectedCondition jQueryLoad = (webDriver) -> {
                return (Boolean) jsExecutor.executeScript("return window.jQuery != undefined && jQuery.active === 0", new Object[0]);
            };

            String jQueryCount;
            do {
                wait.until(jQueryLoad);
                jQueryCount = jsExecutor.executeScript("return jQuery.active", new Object[0]).toString();
            } while (Integer.parseInt(jQueryCount) != 0);

        } catch (Throwable var6) {
            this.driverContextLogger.error(ExceptionUtils.getMessage(var6));
        }
    }

    public void sendKeys(WebElement element, String text) {
        try {
            element.sendKeys(text);
        } catch (Exception e) {
            fail("Failed to send text '" + text + "' to element " + element + ": " + e.getMessage());
        }
    }

    public void pressEnter() {
        Actions actions = new Actions(getDriver());
        actions.sendKeys(Keys.ENTER).perform();
    }


    public void clear(WebElement element) {
        try {
            element.clear();
        } catch (Exception e) {
            fail("Unable to clear the input field: " + e.getMessage());
        }
    }

    public String getText(WebElement element) {
        try {
            return element.getText();
        } catch (Exception e) {
            fail("Failed to get the element's text : " + e.getMessage());
            return "";
        }
    }

    public String getDomAttribute(WebElement element, String attributeName, int... timeoutSec) {
        try {
            return element.getDomAttribute(attributeName);
        } catch (Exception e) {
            fail("Failed to get the element's attribute '" + attributeName + " Message :" + e.getMessage());
            return "";
        }
    }

    public List<WebElement> findElements(By locator) {
        try {
            return getDriver().findElements(locator);
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public boolean checkUrlIsOpened(String url) {
        try {
            Iterator tabIterator = getDriver().getWindowHandles().iterator();

            do {
                if (!tabIterator.hasNext()) {
                    return false;
                }

                String eachTab = (String) tabIterator.next();
                getDriver().switchTo().window(eachTab);
            } while (!getDriver().getCurrentUrl().trim().equalsIgnoreCase(url.trim()));

            return true;
        } catch (Throwable var4) {
            this.driverContextLogger.error(ExceptionUtils.getMessage(var4));
            Assert.fail("URL: " + url + " ERROR occurred while opening the specified connection !!");
            return false;
        }
    }

    public void checkPageTitle(String expectedTitle) {
        String actualTitle = getDriver().getTitle();
        assertEquals(expectedTitle, actualTitle,
                "Page title is incorrect! Expected: " + expectedTitle + ", Found: " + actualTitle);
    }

    public void findScrollElementCenter(WebElement webElement) {
        try {
            String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                    + "var elementTop = arguments[0].getBoundingClientRect().top;"
                    + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
            ((JavascriptExecutor) getDriver()).executeScript(scrollElementIntoMiddle, webElement);
        } catch (Exception e) {
            fail("Problem occurred while scrolling element: " + webElement);
        }
    }

    public void waitUntilElementClickable(WebElement elementFindBy) {
        try {
            FluentWait<WebDriver> wait = (new WebDriverWait(LocalDriverContext.getDriver(), Duration.ofSeconds(10))).pollingEvery(Duration.ofSeconds(5L)).withTimeout(Duration.ofSeconds(10)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.elementToBeClickable(elementFindBy));
        } catch (Throwable var3) {
            this.driverContextLogger.error(ExceptionUtils.getMessage(var3));
            fail("Element: " + elementFindBy + " WebElement click edilebilir durumda degil !!");
        }
    }

    public void fail(String text) {
        driverContextLogger.error(text);
        Assert.fail(text);
    }

    public void info(String text) {
        driverContextLogger.info(text);
    }


}
