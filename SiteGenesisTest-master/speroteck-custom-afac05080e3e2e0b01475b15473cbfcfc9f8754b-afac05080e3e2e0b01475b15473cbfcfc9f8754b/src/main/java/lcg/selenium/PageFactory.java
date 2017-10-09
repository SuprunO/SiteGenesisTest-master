/*
 * Copyright (c) 2015, Speroteck Inc. (www.speroteck.com)
 * and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Speroteck or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package lcg.selenium;


import com.gargoylesoftware.htmlunit.*;
import lcg.selenium.pages.Header;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


/**
 * A class, which contains implemented common methods for all pages.
 *
 * @author Speroteck QA Team (qa@speroteck.com)
 */
public abstract class PageFactory {

    /**
     * Constructor
     *
     * @param webDriver WebDriver instance created in TestFactory.class
     */
    public PageFactory(WebDriver webDriver) {
        driver = webDriver;
    }

    protected static WebDriver driver;


    private static final WebClient htmlUnitClient = TestFactory.getHtmlUnitClient();
    private static final Logger logger = Logger.getLogger(PageFactory.class);
    public static final String START_URL = TestFactory.getStartURL();
    public static final int DEFAULT_WAIT_TIME = 60; //default time to patiently wait for finding elements


    //common unique web element identifiers
    public static final By PAGE_404_INDICATOR = By.xpath("//h2[@class='system-error']");
    public static final By QUICK_ACCESS_ACCOUNT = By.id("quick-access-account");

    /** ---------------------------   START: SELECT / CLICK ON / CHECK / UNCHECK METHODS   --------------------------- */

    /**
     * Method that finds element by locator with class "By" and clicks on it.
     *
     * @param locator     "By" class locator to search input field.
     * @param elementName String value that describes, which element will be clicked(just for pretty logs).
     */
    public void waitElementToBeClickableAndClick(By locator, String elementName) {
        try {
            logger.info("Clicking on: " + elementName);
            waitForElementIsClickable(locator, 200);
       //     assertTrue("COULD NOT FIND ELEMENT: " + elementName + " " + locator, isElementPresent(locator));
         //   assertTrue("COULD NOT CLICK ON INVISIBLE ELEMENT: " + elementName + locator, driver.findElement(locator).isDisplayed());
            driver.findElement(locator).click();
        }
        catch (Exception e){
            Assert.fail("Element: " + elementName + ": " + locator + " Element wait too long" + "\n" );
        }

    }


    /**
     * Checks the target Checkbox by specified locator if it's not already checked.
     *
     * @param locator      By Locator to search the checkbox
     * @param checkboxName String value that describes checkbox(just for pretty logs).
     */
    public void checkCheckbox(By locator, String checkboxName) {
        assertTrue("THE CHECKBOX: \"" + checkboxName + "\" IS NOT VISIBLE ON THE PAGE!", driver.findElement(locator).isDisplayed());
        WebElement targetCheckbox = driver.findElement(locator);
        if (targetCheckbox.isSelected()) {
            logger.info("Checkbox with locator: \"" + checkboxName + "\" is already checked");
        } else {
            targetCheckbox.click();
            logger.info("Checked the checkbox with locator: \"" + checkboxName + "\"");
        }
    }

    /**
     * Un-checks the target Checkbox by specified locator if it's not already un-checked.
     *
     * @param locator      By Locator to search the checkbox
     * @param checkboxName String value that describes checkbox(just for pretty logs).
     */
    public void uncheckCheckbox(By locator, String checkboxName) {
        assertTrue("THE CHECKBOX: \"" + checkboxName + "\" IS NOT VISIBLE ON THE PAGE!", driver.findElement(locator).isDisplayed());
        WebElement targetCheckbox = driver.findElement(locator);
        if (targetCheckbox.isSelected()) {
            targetCheckbox.click();
            logger.info("Un-checked the checkbox with locator: \"" + checkboxName + "\"");
        } else {
            logger.info("Checkbox with locator: \"" + checkboxName + "\" is already un-checked");
        }
    }

    /**
     * Method that finds input field by locator with class "By" and fills in specified value.
     *
     * @param locator "By" class locator to search input field.
     * @param value   String value that fills into the input.
     */
    public void fillInInput(By locator, String value) {
        assertTrue("COULD NOT FIND INPUT FIELD: " + locator.toString(), driver.findElement(locator).isDisplayed());
        logger.info("Typing in: \"" + value + "\" into the Field");
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
//        driver.findElement(locator).click();  // Should be moved to separate method: fillInInputAndClick()
    }


    /**
     * Method that finds input field by locator with class "By" and fills in specified value.
     *
     * @param locator "By" class locator to search Dropdown field.
     * @param value   String value that filles into the input.
     */
    public void selectValueInDropDown(By locator, String value) {
        try {
            assertTrue("COULD NOT FIND DROPDOWN FIELD: " + locator.toString(), driver.findElement(locator).isDisplayed());
            logger.info("Selecting: \"" + value + "\" in the DropDown: " + locator.toString());
            Select dropDown = new Select(driver.findElement(locator));
            dropDown.selectByVisibleText(value);
        }
        catch (Exception e){
            Assert.fail();
        }
    }

    /**
     * * Method that selects all input fields and DropDowns from the specified ArrayList
     * using FillInTextInput() and SelectDropDownValue() methods.
     *
     * @param fields List<Field> list with fields. Each field have type(String), locator(By) and value(String).
     */
    public void fillFieldsSet(List<Field> fields) {
        for (Field entry : fields) {
            if (entry.getType().equals(Field.INPUT)) {
                fillInInput(entry.getLocator(), entry.getValue());
            } else if (entry.getType().equals(Field.SELECT)) {
                selectValueInDropDown(entry.getLocator(), entry.getValue());
            }
        }
    }

    /** ---------------------------   END: SELECT / CLICK ON / CHECK / UNCHECK METHODS   --------------------------- */


    /** ---------------------------   START: WAIT METHODS   --------------------------- */

    /**
     * Waiting during specified time till the element will be visible for user.
     * If it isn't it throws an {@link AssertionError} with the given message.
     *
     * @param message          String, the identifying message for the {@link AssertionError}.
     * @param by               By, Locator of the element on page, which have type By.
     * @param timeOutInSeconds int Time(seconds) for waiting of the element
     */
    public void waitForElementIsVisible(String message, By by, int timeOutInSeconds) {
        try {
            waitForElementIsVisible(by, timeOutInSeconds);
        } catch (Exception ex) {
            logger.error(ex);
            fail(message);
        }
    }


    /**
     * This method is waiting during "DEFAULT_WAIT_TIME"(60 seconds) till the element will be visible for user.
     *
     * @param by Locator of the element on page, which have type By.
     */
    public void waitForElementIsVisible(By by) {

        waitForElementIsVisible(by, DEFAULT_WAIT_TIME);
    }

    /**
     * This method is waiting during "DEFAULT_WAIT_TIME"(60 seconds) till the element will be clickable for user.
     *
     * @param by Locator of the element on page, which have type By.
     */
    public void waitForElementToBeClickable(By by) {
        waitForElementIsClickable(by, DEFAULT_WAIT_TIME);
    }

    /**
     * This method is waiting during specified time till the element will be visible for user.
     *
     * @param by               Locator of the element on page, which have type By.
     * @param timeOutInSeconds int Time(seconds) for waiting of the element
     */
    public void waitForElementIsVisible(By by, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));

    }


    /**
     * Waiting during specified time till the element will be clickable for user.
     *
     * @param by               Locator of the element on page, which have type By.
     * @param timeOutInSeconds int Time(seconds) for waiting of the element
     */
    public void waitForElementIsClickable(By by, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * Waiting during specified time till the element will be invisible for user.
     * If it isn't it throws an {@link AssertionError} with the given message.
     *
     * @param message          String, the identifying message for the {@link AssertionError}.
     * @param by               By, Locator of the element on page, which have type By.
     * @param timeOutInSeconds int Time(seconds) for waiting of the element
     */
    public void waitForElementNotVisible(String message, By by, int timeOutInSeconds) {
        try {
            waitForElementNotVisible(by, timeOutInSeconds);
        } catch (Exception ex) {
            logger.error(ex);
            fail(message);
        }
    }

    /**
     * Waiting during "DEFAULT_WAIT_TIME" till the element will be invisible for user.
     *
     * @param by Locator of the element on page, which have type By.
     */
    public void waitForElementNotVisible(By by) {
        waitForElementNotVisible(by, DEFAULT_WAIT_TIME);
    }

    /**
     * Waiting during specified time till the element will be invisible for user.
     *
     * @param by               Locator of the element on page, which have type By.
     * @param timeOutInSeconds Time for waiting of the element
     */
    public void waitForElementNotVisible(By by, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withTimeout(timeOutInSeconds, TimeUnit.SECONDS).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }


//    public boolean isElementPresent(By elementLocator) {
//        return !driver.findElements(elementLocator).isEmpty();
//    }


    /**
     * Moving out nasty try/catch form tests' body.
     * TODO: Method should be removed after all tests will use fluent wait.
     *
     * @param timeInMilliseconds Int Time to wait inMilliseconds
     */
    public void sleep(int timeInMilliseconds) {
        try {
            Thread.sleep(timeInMilliseconds);
        } catch (InterruptedException ex) {
            logger.error("Thread Sleep Exception! " + ex);
        }

    }


    /**
     * This method is waiting during specified time till the element will be present on the current page.
     *
     * @param by               Locator of the element on page, which have type By.
     * @param timeOutInSeconds int Time(seconds) for waiting of the element
     */
    public void waitForElementIsPresent(By by, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /** ---------------------------   END: WAIT METHODS   --------------------------- */


    /** ---------------------------   START: Verifications methods    --------------------------- */

    /**
     * Checks if specified an element is present on the current the page.
     * Note: for Ajax or elements with delayed appearing: "wait"|fluent-wait methods should be used.
     *
     * @param locator By locator of target element
     * @return boolean  Returns true is element exists in DOM, false, if not
     */

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementVisible(By locator){
        return driver.findElement((locator)).isDisplayed();
    }



    /**
     * This method verifies that saved billing and shipping information and credit card is displayed on checkout page.
     * If any block is not visible the Exception appears.
     *
     * @param locator     Locator of the element on page, which have type By.
     * @param elementName
     */

    public void verifyLocatorsArePresent(By locator, String elementName) {
      //  logger.info("Verifying, that field locators for shipping page are correct");
        try {
            isElementPresent(locator);
            isElementVisible(locator);
        } catch (Exception e) {
            Assert.fail("Element: " + elementName + ": " + locator + " is missing or wrong" + "\n" );
        }

    }

    /** ---------------------------   END: Verifications methods    --------------------------- */






















    /**
     * Converts text extracted from the page to float number. For further work with cost, taxes, prices calculations.
     *
     * @param extractedText String Price text extracted from the page
     * @return Float
     */
    public float convertPriceTextToFloatNumber(String extractedText) {
        return Float.parseFloat(extractedText.replace("$", ""));
    }


    /**
     * This method returns true if current page is 404 page.
     *
     * @return boolean
     */
    public boolean is404Page() {
        return !driver.findElements(PAGE_404_INDICATOR).isEmpty();
    }

    /**
     * This method will open specified page using additional part of url with base url.
     *
     * @param pagePath additional string to base url
     * @param pageName name of the page
     */
    public void openPage(String pagePath, String pageName) {
        logger.info("Opening URL: " + START_URL + pagePath);
        driver.get(START_URL + pagePath);
        assertEquals(pageName + " WAS NOT OPENED!", START_URL + pagePath, driver.getCurrentUrl());
        assertFalse("404 PAGE IS OPENED! BUT EXPECTED: " + START_URL + pagePath, is404Page());
    }

    /**
     * Check if a user is logged in
     *
     * @return Boolean
     */
    public boolean isLoggedIn() {
        //make sure the quick access menu exists
        if (!isElementPresent(Header.QUICK_ACCESS_ACCOUNT)) {
            return false;
        }
        //check quick access menu to signal logged in
        return driver.findElement(Header.QUICK_ACCESS_ACCOUNT).isDisplayed();
    }


    /**
     * Method that makes customer not logged in status for Magento
     * It use the approach directly opening URL, which send request to log out the customer
     */
    public void logout() {
        logger.info("Making log out...");
        driver.get(START_URL + "customer/account/logout/");
    }


    /**
     * Clicking "OK" on JS Alerts with printing Alert's text.
     */
    public void acceptJSAlert() {
        Alert alert = driver.switchTo().alert();
        logger.info("Alert: '" + alert.getText() + "'. Clicking 'OK'");
        alert.accept();
    }

    /**
     * <p>Indicates that {@link TestFactory#htmlUnitClient } was created and passed to {@link #htmlUnitClient}.</p>
     *
     * @return true|false
     */
    private static boolean isHtmlUnitPresent() {
        return htmlUnitClient != null;
    }

    /**
     * <p>Use headless HtmlUnit to establish connection and retrieve returned by Server Status Code.</p>
     * <p>Throws a {@link FailingHttpStatusCodeException} if the request's status code indicates a request
     * failure and {@link WebClientOptions#isThrowExceptionOnFailingStatusCode()} returns <tt>true</tt>.
     *
     * @param url Target Web page address, Resource address applicable too.
     * @return Status Code number
     */
    public static int getStatusCode(String url) {
        if (!isHtmlUnitPresent()) {
            logger.info("StatusCode validation is skipped");
            return 0;
        }
        long start = System.currentTimeMillis();
        logger.info("Validating StatusCode for: " + url);
        try {
            WebRequest webRequest = new WebRequest(new URL(url));
            WebResponse response = htmlUnitClient.loadWebResponse(webRequest);
            htmlUnitClient.throwFailingHttpStatusCodeExceptionIfNecessary(response);
            return response.getStatusCode();
        } catch (IOException ioe) {
            throw new TestFrameworkRuntimeException(ioe);
        } finally {
            long duration = System.currentTimeMillis() - start;
            logger.info("Validating statusCode for: " + url + " (done) | time=" + duration + "ms");
        }
    }

}
