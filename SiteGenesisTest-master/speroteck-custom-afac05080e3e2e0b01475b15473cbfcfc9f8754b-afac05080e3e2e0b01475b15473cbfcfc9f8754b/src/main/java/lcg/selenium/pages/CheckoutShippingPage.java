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


package lcg.selenium.pages;

import lcg.selenium.Field;
import lcg.selenium.PageFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lcg.selenium.TestFactory.getStartURL;
import static lcg.selenium.UserCredentials.*;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * A class, which contains implemented actions and verifications, which can be performed on Checkout page
 *
 * @author Speroteck QA Team (qa@speroteck.com)
 */
public class CheckoutShippingPage extends PageFactory {

    public Logger logger = Logger.getLogger(CheckoutShippingPage.class);

    public CheckoutShippingPage(WebDriver driver) {
        super(driver);
    }

    /**
     * ---------------------------   START: LOCATORS   ---------------------------
     */

    /* Shipping Step */
    public static final By FIELD_SHIPPING_ADDRESS_FIRST_NAME = By.cssSelector("#dwfrm_singleshipping_shippingAddress_addressFields_firstName");
    public static final By FIELD_SHIPPING_ADDRESS_LAST_NAME = By.cssSelector("#dwfrm_singleshipping_shippingAddress_addressFields_lastName");
    public static final By FIELD_SHIPPING_ADDRESS_STREET_1 = By.cssSelector("#dwfrm_singleshipping_shippingAddress_addressFields_address1");
    public static final By FIELD_SHIPPING_ADDRESS_STREET_2 = By.id("dwfrm_singleshipping_shippingAddress_addressFields_address2");
    public static final By FIELD_SHIPPING_ADDRESS_CITY = By.cssSelector("#dwfrm_singleshipping_shippingAddress_addressFields_city");
    public static final By DROPDOWN_SHIPPING_ADDRESS_STATE = By.cssSelector("#dwfrm_singleshipping_shippingAddress_addressFields_states_state");
    public static final By DROPDOWN_SHIPPING_ADDRESS_COUNTRY = By.cssSelector("#dwfrm_singleshipping_shippingAddress_addressFields_country");
    public static final By FIELD_SHIPPING_ADDRESS_ZIP_CODE = By.cssSelector("#dwfrm_singleshipping_shippingAddress_addressFields_postal");
    public static final By FIELD_SHIPPING_ADDRESS_TELEPHONE = By.cssSelector("#dwfrm_singleshipping_shippingAddress_addressFields_phone");
    public static final By FIELD_SHIPPING_ADDRESS_EMAIL = By.cssSelector("#dwfrm_singleshipping_shippingAddress_addressFields_email");
    public static final By BUTTON_CONTINUE_TO_PAYMENT = By.name("dwfrm_multishipping_shippingOptions_save");


    public static final By BLOCK_SHIPPING_STEP = By.id("checkout-step-shipping");
    public static final By DROPDOWN_SHIPPING_INFO = By.id("shipping-address-select");
    public static final By TEXT_SHIPPING_INFO_DISPLAYED_INFO = By.id("current-shipping-customer-address");
    public static final By FORM_SHIPPING_INFO_NEW_ADDRESS = By.id("shipping-new-address-form");
    /** ---------------------------   END: LOCATORS   --------------------------- */


    /** ---------------------------   START: EXECUTION METHODS   --------------------------- */

    /**
     * Fills in Shipping Address Form with values based on specified state name.
     * Note: If argument is not recognized fills in form with default values.
     *
     * @param stateName String "NEW_JERSEY"|"NEW_YORK"|"WASHINGTON"|"COLORADO"|"OHIO"|any string
     */
    public void fillShippingAddressForm_AddressSameAsBilling(String stateName, boolean sameAsShipping) {
            ArrayList<Field> shippingAddressFields = getShippingFieldSet(stateName);
            assertThat("FIELD SET IS BLANK!", shippingAddressFields.size(), not(0));
            logger.info("Fill out Shipping Address Form");
            verifyInputFieldsLocatorsAreCorrectOnShippingPage();
            fillFieldsSet(shippingAddressFields);
        if (sameAsShipping){
            waitElementToBeClickableAndClick(CHECKBOX_BILLING_ADDRESS_SAME_AS_SHIPPING, "same as shipping");
        }
            waitElementToBeClickableAndClick(CONTINUE_TO_PAYMENT, "ContinueToPayment");
    }

    /**
     * Creates and returns Shipping address fields to fill on checkout with values based on specified state name.
     * Note: If argument is not recognized fields with default values will be returned
     *
     * @param stateName String "NEW_JERSEY"|"NEW_YORK"|"WASHINGTON"|"COLORADO"|any string
     * @return ArrayList<Field> Fields set which is used in fillFieldsSet() method as argument
     */
    private ArrayList<Field> getShippingFieldSet(String stateName) {
        ArrayList<Field> resultFieldSet = new ArrayList<Field>();
        // create shipping field sets for every declared state
        resultFieldSet.add(new Field("input", FIELD_SHIPPING_ADDRESS_FIRST_NAME, TEST_FIRST_NAME));
        resultFieldSet.add(new Field("input", FIELD_SHIPPING_ADDRESS_LAST_NAME, TEST_LAST_NAME));
        if (stateName.equals("Illinois")) {
            resultFieldSet.add(new Field("input", FIELD_SHIPPING_ADDRESS_STREET_1, IL_STREET_1));
            resultFieldSet.add(new Field("input", FIELD_SHIPPING_ADDRESS_STREET_2, IL_STREET_2));
            resultFieldSet.add(new Field("input", FIELD_SHIPPING_ADDRESS_CITY, IL_CITY));
            resultFieldSet.add(new Field("dropdown", DROPDOWN_SHIPPING_ADDRESS_STATE, IL_STATE));
            resultFieldSet.add(new Field("input", FIELD_SHIPPING_ADDRESS_ZIP_CODE, IL_ZIP));
            resultFieldSet.add(new Field("dropdown", DROPDOWN_SHIPPING_ADDRESS_COUNTRY, IL_COUNTRY));
            resultFieldSet.add(new Field("input", FIELD_SHIPPING_ADDRESS_TELEPHONE, IL_PHONE));
         //   resultFieldSet.add(new Field("input", FIELD_SHIPPING_ADDRESS_EMAIL, IL_EMAIL));

        } else if (stateName.equals("COLORADO")) {
//            resultFieldSet.add(new Field("input", FIELD_SHIPPING_ADDRESS_STREET_1, COL_GUEST_STREET_1));
//            resultFieldSet.add(new Field("input", FIELD_SHIPPING_ADDRESS_STREET_2, COL_GUEST_STREET_2));
//            resultFieldSet.add(new Field("input", FIELD_SHIPPING_ADDRESS_CITY, COL_GUEST_CITY));
//            resultFieldSet.add(new Field("dropdown", DROPDOWN_SHIPPING_ADDRESS_STATE, COL_GUEST_STATE));
//            resultFieldSet.add(new Field("input", FIELD_SHIPPING_ADDRESS_ZIP_CODE, COL_GUEST_ZIP));
//            resultFieldSet.add(new Field("dropdown", DROPDOWN_SHIPPING_ADDRESS_COUNTRY, COL_GUEST_COUNTRY));
//            resultFieldSet.add(new Field("input", FIELD_SHIPPING_ADDRESS_TELEPHONE, COL_GUEST_PHONE));
        }
        return resultFieldSet;
    }

    /**
     * Click on  button Continue to Payment
     */
    public void clickContinueToPayment() {
        waitForElementIsVisible(BUTTON_CONTINUE_TO_PAYMENT);
        waitElementToBeClickableAndClick(BUTTON_CONTINUE_TO_PAYMENT, "Button Continue to Payment");
    }
    /** ---------------------------   END: EXECUTION METHODS   --------------------------- */


    /** ---------------------------   START: EXPECTED RESULTS   --------------------------- */


    /** ---------------------------   END: EXPECTED RESULTS   --------------------------- */


    /**
     * ---------------------------   START: Verification methods   ---------------------------
     */
    public void verifyInputFieldsLocatorsAreCorrectOnShippingPage() {
        verifyLocatorsArePresent(FIELD_SHIPPING_ADDRESS_FIRST_NAME, "FIRST_NAME");
        verifyLocatorsArePresent(FIELD_SHIPPING_ADDRESS_LAST_NAME, "LAST_NAME");
        verifyLocatorsArePresent(FIELD_SHIPPING_ADDRESS_STREET_1, "FIELD_SHIPPING_ADDRESS_STREET");
        verifyLocatorsArePresent(FIELD_SHIPPING_ADDRESS_STREET_2, "FIELD_SHIPPING_ADDRESS_STREET");
        verifyLocatorsArePresent(FIELD_SHIPPING_ADDRESS_CITY, "FIELD_SHIPPING_ADDRESS_CITY");
        verifyLocatorsArePresent(DROPDOWN_SHIPPING_ADDRESS_STATE, "DROPDOWN_SHIPPING_ADDRESS_STATE");
        verifyLocatorsArePresent(DROPDOWN_SHIPPING_ADDRESS_COUNTRY, "DROPDOWN_SHIPPING_ADDRESS_COUNTRY ");
        verifyLocatorsArePresent(FIELD_SHIPPING_ADDRESS_ZIP_CODE, "FIELD_SHIPPING_ADDRESS_ZIP_CODE");
        verifyLocatorsArePresent(FIELD_SHIPPING_ADDRESS_TELEPHONE, "FIELD_SHIPPING_ADDRESS_TELEPHONE");
      //  verifyLocatorsArePresent(FIELD_SHIPPING_ADDRESS_EMAIL, "FIELD_SHIPPING_ADDRESS_EMAIL");
    }
    /** ---------------------------  END: Verification methods   --------------------------- */


    /**
     * ---------------------------   START: LOCATORS UNUSED   ---------------------------
     */


    public static final String PAGE_CHECKOUT = "checkout/onepage/";
    public static final By WELCOME_ELEMENT = By.id("customer-welcome");
    public static final By BUTTON_PLACE_ORDER_DISABLED = By.xpath("//button[@class='btn-cart btn-checkout pink-button disabled']");
    public static final By BUTTON_PLACE_ORDER = By.xpath("//button[@class='btn-cart btn-checkout pink-button']");
    public static final By LOADING_MASK_PLACING_ORDER = By.xpath("//div[@class='jcheckout-checkout-loading']");
    public static final By LOADING_MASK_LOADING_STEP_DATA = By.xpath("//div[@id='checkout-step-shipping_method']/div[@class='step-loading']");
    public static final By LINK_TERMS_AND_CONDITIONS = By.xpath("//a[contains(text(), 'Terms & Conditions')]");
    public static final By LINK_PRIVACY_POLICY = By.xpath("//a[@name='trustlink']");


    /* Billing Address Step */
    public static final String ID_BILLING_ADDRESS_SAME_AS_SHIPPING_CHECKBOX = "#dwfrm_singleshipping_shippingAddress_useAsBillingAddress";
    public static final By CHECKBOX_BILLING_ADDRESS_SAME_AS_SHIPPING = By.cssSelector(ID_BILLING_ADDRESS_SAME_AS_SHIPPING_CHECKBOX);
    public static final By DROPDOWN_BILLING_INFO = By.id("billing-address-select");
    public static final By TEXT_BILLING_INFO_DISPLAYED_INFO = By.id("current-billing-customer-address");
    public static final By FORM_BILLING_INFO_NEW_ADDRESS = By.id("billing-new-address-form");
    public static final By FIELD_BILLING_ADDRESS_FIRST_NAME = By.id("billing:firstname");
    public static final By FIELD_BILLING_ADDRESS_LAST_NAME = By.id("billing:lastname");
    public static final By FIELD_BILLING_ADDRESS_STREET_1 = By.id("billing:street1");
    public static final By FIELD_BILLING_ADDRESS_STREET_2 = By.id("billing:street2");
    public static final By FIELD_BILLING_ADDRESS_CITY = By.id("billing:city");
    public static final By DROPDOWN_BILLING_ADDRESS_STATE = By.id("billing:region_id");
    public static final By DROPDOWN_BILLING_ADDRESS_COUNTRY = By.id("billing:country_id");
    public static final By FIELD_BILLING_ADDRESS_ZIP_CODE = By.id("billing:postcode");
    public static final By FIELD_BILLING_ADDRESS_TELEPHONE = By.id("billing:telephone");

    /* Billing Credit Card(CC) Step */
    public static final By DROPDOWN_BILLING_CC = By.id("tokens");
    public static final By FORM_BILLING_CC_NEW_CARD = By.id("new_card_fields");
    public static final By CHECKBOX_BILLING_CC_SET_NEW_CC_AS_DEFAULT = By.id("set_as_default");
    public static final By FIELD_BILLING_CC_NUMBER = By.id("creditcard_cc_number");
    public static final By DROPDOWN_BILLING_CC_TYPE = By.id("creditcard_cc_type");     // investigate usage! no such dropdown...
    public static final By DROPDOWN_BILLING_CC_EXPIRATION_MONTH = By.id("creditcard_expiration");
    public static final By DROPDOWN_BILLING_CC_EXPIRATION_YEAR = By.id("creditcard_expiration_yr");

    /* Create Account Step */
    public static final By BLOCK_CREATE_ACCOUNT = By.id("co-registration-form");
    public static final By FIELD_CREATE_ACCOUNT_EMAIL = By.id("email_address");
    public static final By FIELD_CREATE_ACCOUNT_PASSWORD = By.id("password");
    public static final By ERROR_MESSAGE_PASSWORD_REQUIRED_6_Scharacters = By.id("advice-validate-password-password");

    /* Login Block */
    public static final By BUTTON_LOGIN = By.id("jcheckout-link-to-login-form");
    public static final By FIELD_LOGIN_FORM_CHECKOUT_EMAIL = By.id("login-email");
    public static final By FIELD_LOGIN_FORM_CHECKOUT_PASSWORD = By.id("login-password");
    public static final By BUTTON_LOGIN_FORM_CHECKOUT_LOGIN = By.id("jcheckout-login-button");
    public static final By LINK_LOG_OUT = By.xpath("//div[@id='customer-welcome']//a");

    /* Order Summary Block */
    public static final By TITLE_GIFT_BOX_PRODUCT_NAME = By.xpath("//h2[contains(@class, 'product-name') and contains(., 'Gift Box')]");
    public static final By PRODUCT_LINE_ORDER_SUMMURY = By.xpath("//table[@id='checkout-review-table']/tbody/tr");
    public static final By CONTINUE_TO_PAYMENT = By.cssSelector(".button-fancy-large");
    public static final By GRAND_TOTAL_AMOUNT = By.xpath("//span[@class='grand-total']/span[@class = 'price']");
    public static final By DROPDOWN_SHIPPING_TYPE = By.xpath("//td[@class='shipping_method_column']/../td[2]/span");
    //select[@name="shipping_method"]
    public static final By DROPDOWN_SHIPPING_METHODS = By.id("rate-table");
    public static final By SHIPPING_AMOUNT = By.xpath("//td[@class='shipping_method_column']/../td[2]/span");
    public static final By TITLE_TAX = By.xpath("//td[contains(text(),'Tax')]");
    public static final By TAX_AMOUNT = By.xpath("//td[contains(text(),'Tax')]/../td[2]/span");
    public static final By REWARDS_REFERRAL_INPUT_ON_CHECKOUT = By.id("rewards_referral");
    public static final By REFERRAL_CODE_LINK_ON_CHECKOUT = By.className("referral_code");
    public static final By EDIT_CART_LINK_ON_CHECKOUT = By.className("edit-cart-link");
    public static final By TITLE_PROMO_CODE = By.className("promocode");

    private static final double DELTA = 1e-2;

    /** ---------------------------   END: LOCATORS UNUSED   --------------------------- */


    /**
     * ---------------------------   START: METHODS THAT CAN BE USEFUL   ---------------------------
     */


    /*
    * expected list of options in the country dropdown
    * see testCountryDropDown method in this class
    */
    private final static String[] COUNTRY_LIST = new String[]{"Canada", "United States", "U.S. Minor Outlying Islands", "U.S. Virgin Islands"};


    /**
     * This method will open Checkout page entering url into the address field.
     */
    public void open() {
        logger.info("Opening URL: " + getStartURL() + PAGE_CHECKOUT);
        driver.get(getStartURL() + PAGE_CHECKOUT);
        assertEquals("CHECKOUT PAGE WAS NOT OPENED", getStartURL() + PAGE_CHECKOUT, driver.getCurrentUrl());
        assertFalse("404 PAGE IS OPENED! BUT EXPECTED: " + getStartURL() + PAGE_CHECKOUT, is404Page());
    }

    /**
     * Verifies that current page is One Page Checkout Page and returns "true" if so.
     *
     * @return Bool
     */
    public boolean isOpened() {
        return driver.getCurrentUrl().equals(getStartURL() + PAGE_CHECKOUT);
    }

    /**
     * This method waits for "Place Order" button get disabled and enabled again and click it.
     * The checkout page must be already opened.
     */
    public void clickPlaceOrderButton() {
        logger.info("Waiting till the \"Place Order button\" will be disabled because of ajax calls");
        // Hack to prevent test framework click on Place order button before the ajax starts(ajax has a delay sometime)
        try {
            waitForElementIsVisible(BUTTON_PLACE_ORDER_DISABLED, 10);
        } catch (Exception ex) {
            //do nothing, just moving on
        }
        logger.info("Waiting till the \"Place Order button\" will be enabled - ajax calls finished");
        waitForElementIsVisible(BUTTON_PLACE_ORDER);
        waitElementToBeClickableAndClick(BUTTON_PLACE_ORDER, "Place Order button");
    }

    /**
     * clicks Place order button and waits for order is placed, measure the time oaf placing the order
     *
     * @return String, a time needed to place order
     */
    public String placeOrderWithTimer() {
        // Find out how long it takes to get success page!
        long startTime = System.currentTimeMillis();
        clickPlaceOrderButton();
        waitForOrderIsPlaced();
        long endTime = System.currentTimeMillis();
        double totalTime = (endTime - startTime) / 1000.0;
        logger.info("TIME TO COMPLETE TRANSACTION: " + String.valueOf(totalTime));
        return String.valueOf(totalTime);
    }

    /**
     * Waits for JS to calculate shipping amount and returns true if the shipping is free($0.00)
     *
     * @return bool
     */
    public boolean isShippingFree() {
        try {
            waitForElementIsVisible(BUTTON_PLACE_ORDER_DISABLED, 30);
        } catch (Exception ex) {
            // This is AJAX stability fix. Do noting, just moving on...
        }
        waitForElementIsVisible(BUTTON_PLACE_ORDER);
        assertTrue("COULD NOT FIND SHIPPING AMOUNT", driver.findElement(SHIPPING_AMOUNT).isDisplayed());
        return driver.findElement(SHIPPING_AMOUNT).getText().equals("$0.00");
    }

    /**
     * Selects the 1st saved shipping address or selects "New address" in the Sipping Address DropDown on Checkout Page  based on argument
     *
     * @param type String "new" or "pre-saved" specifies what to select in the DropDown
     */
    public void selectShippingAddressDropdown(String type) {
        logger.info("Verify, that Dropdown for selecting pre-saved shipping information is displayed on checkout page");
        assertTrue(driver.findElement(DROPDOWN_SHIPPING_INFO).isDisplayed());
        String valueToClick = null;     // Value that is going to be selected
        Select shippingSelect = new Select(driver.findElement(DROPDOWN_SHIPPING_INFO));
        if (type.equals("new")) {
            valueToClick = "New Address";
        } else if (type.equals("pre-saved")) {
            List<WebElement> options = shippingSelect.getOptions();
            // Get first pre-saved address option to click
            for (WebElement entry : options) {
                if (!entry.getText().equals("New Address")) {
                    valueToClick = entry.getText();
                    break;
                }
            }
        } else {
            fail("SELECT SHIPPING ADDRESS: Argument is not recognized, sorry. Please, read method's Documentation.");
        }
        // Select received option
        logger.info("Selecting the following value: " + valueToClick);
        shippingSelect.selectByVisibleText(valueToClick);
    }

    /**
     * Selects the 1st saved billing address or selects "New address" in the Billing Address DropDown on Checkout Page based on argument
     *
     * @param type String "new" or "pre-saved" specifies what to select in the DropDown
     */
    public void selectBillingAddressDropdown(String type) {
        logger.info("Verify, that Dropdown for selecting pre-saved shipping information is displayed on checkout page");
        assertTrue("BILLING ADDRESS DROPDOWN IS NOT DISPLAYED!", driver.findElement(DROPDOWN_BILLING_INFO).isDisplayed());
        String valueToClick = null;     // Value that is going to be selected
        Select billingSelect = new Select(driver.findElement(DROPDOWN_BILLING_INFO));     //Store the DropDown
        if (type.equals("new")) {
            valueToClick = "New Address";
        } else if (type.equals("pre-saved")) {
            List<WebElement> options = billingSelect.getOptions();
            // Get first pre-saved address option to click
            for (WebElement entry : options) {
                if (!entry.getText().equals("New Address")) {
                    valueToClick = entry.getText();
                    break;
                }
            }
        } else {
            fail("SELECT BILLING ADDRESS: Argument is not recognized, sorry. Please, read method's Documentation.");
        }
        // Select received option
        logger.info("Selecting the following value: " + valueToClick);
        billingSelect.selectByVisibleText(valueToClick);
    }

    /**
     * Selects the 1st saved Credit Card or selects "New Credit Card"
     * in the "Choose or add a card" DropDown on Checkout Page based on argument.
     *
     * @param possibleValue String "new" or "pre-saved" specifies what to select in the DropDown
     */
    public void selectCreditCardDropdown(String possibleValue) {
        logger.info("Verify Dropdown for selecting pre-saved Credit Card is displayed on checkout page");
        assertTrue("CREDIT CARD DROPDOWN IS NOT DISPLAYED!", driver.findElement(DROPDOWN_BILLING_CC).isDisplayed());
        String valueToClick = null;     // Value that is going to be selected
        Select ccSelect = new Select(driver.findElement(DROPDOWN_BILLING_CC));     //Store the DropDown
        if (possibleValue.equals("new")) {
            valueToClick = "New Credit Card";
        } else if (possibleValue.equals("pre-saved")) {
            List<WebElement> options = ccSelect.getOptions();
            // Get first pre-saved address option to click
            for (WebElement entry : options) {
                if (!entry.getText().equals("New Credit Card") && !entry.getText().equals("--Please Select a Stored Credit Card--")) {
                    valueToClick = entry.getText().trim();
                    break;
                }
            }
        } else {
            fail("SELECT CREDIT CART: Argument is not recognized, sorry. Please, read method's Documentation.");
        }
        // Select received option
        logger.info("Selecting the following value: " + valueToClick);
        ccSelect.selectByVisibleText(valueToClick);
    }

    /**
     * Fills in Create Account form on Checkout page.
     *
     * @param username email address
     * @param password password, which will be used for created account
     */
    public void fillCreateAccountForm(String username, String password) {
        waitForElementIsVisible(FIELD_CREATE_ACCOUNT_EMAIL);
        logger.info("Entering email address");
        fillInInput(FIELD_CREATE_ACCOUNT_EMAIL, username);
        logger.info("Entering password");
        fillInInput(FIELD_CREATE_ACCOUNT_PASSWORD, password);
    }

    /**
     * Makes customer logged in on checkout page with specified Email and Password.
     * Emulates actions on frontend which user have to perform to log in on checkout page.
     *
     * @param email    String, email address that will be used as login/username to log in.
     * @param password String, password that will be used as password to log in.
     */
    public void loginOnCheckoutPage(String email, String password) {
        waitElementToBeClickableAndClick(BUTTON_LOGIN, "Log In button");
        logger.info("Waiting till the form for login will open");
        waitForElementIsVisible(FIELD_LOGIN_FORM_CHECKOUT_EMAIL);
        logger.info("Fill out Email and Password fields and clicking \"Login\" button");
        fillInInput(FIELD_LOGIN_FORM_CHECKOUT_EMAIL, email);
        fillInInput(FIELD_LOGIN_FORM_CHECKOUT_PASSWORD, password);
        waitElementToBeClickableAndClick(BUTTON_LOGIN_FORM_CHECKOUT_LOGIN, "Login(on the login Pop-up) button");
        logger.info("Waiting for success message");
        waitForElementIsVisible(WELCOME_ELEMENT);
    }

    /**
     * Checks "Billing address same as shipping" checkbox if it is not already checked.
     */
    public void checkCheckboxBillingSameAsShipping() {
        waitElementToBeClickableAndClick(CHECKBOX_BILLING_ADDRESS_SAME_AS_SHIPPING, "this is also my billing address");
        // checkCheckbox(CHECKBOX_BILLING_ADDRESS_SAME_AS_SHIPPING, "Billing address same as shipping");
    }

    /**
     * Un-checks "Billing address same as shipping" checkbox if it is not already un-checked.
     */
    public void uncheckSameAsShippingCheckbox() {
        uncheckCheckbox(CHECKBOX_BILLING_ADDRESS_SAME_AS_SHIPPING, "Billing address same as shipping");
    }




    /**
     * fills in Billing Credit Card Form
     */
    public void fillBillingCCForm() {
        logger.info("Fill out Billing Credit Card Form");
        waitForElementIsVisible(FIELD_BILLING_CC_NUMBER);
        fillInInput(FIELD_BILLING_CC_NUMBER, TEST_CARD_NUMBER_VISA);
        //  selectValueInDropDown(DROPDOWN_BILLING_CC_EXPIRATION_MONTH, TEST_CARD_MONTH);
        // selectValueInDropDown(DROPDOWN_BILLING_CC_EXPIRATION_YEAR, TEST_CARD_YEAR);
    }

    /**
     * Creates and returns Shipping address fields to fill on checkout with default values.
     *
     * @return ArrayList<Field> Fields set which is used in fillFieldsSet() method as argument
     */
    private ArrayList<Field> getShippingFieldSet() {
        return getShippingFieldSet("");
    }


    /**
     * Gets Subtotal value in Totals sections and return it
     *
     * @return String
     */
//    public String getSubtotalAmount() {
//        assertTrue("COULD NOT FIND SUBTOTAL AMOUNT", isElementPresent(SUBTOTAL_AMOUNT));
//        return driver.findElement(SUBTOTAL_AMOUNT).getText();
//    }

    /**
     * Gets Tax value in Totals sections if it is exist and return it
     *
     * @return String
     */

    // We Don't need this
    public String getTaxAmount() {
        if (isElementPresent(TITLE_TAX)) {
            return driver.findElement(TAX_AMOUNT).getText();
        } else {
            return "0";
        }
    }

    /**
     * Gets Shipping value in Totals sections and return it
     *
     * @return String
     */

    //This also
    public String getShippingAmount() {
        assertTrue("COULD NOT FIND SHIPPING AMOUNT", isElementPresent(SHIPPING_AMOUNT));
        return driver.findElement(SHIPPING_AMOUNT).getText();
    }

    /**
     * Gets Grand Total value in Totals sections and return it
     *
     * @return String
     */
    public String getGrandTotal() {
        assertTrue("COULD NOT FIND GRAND TOTAL AMOUNT", isElementPresent(GRAND_TOTAL_AMOUNT));
        return driver.findElement(GRAND_TOTAL_AMOUNT).getText();
    }

    public List<String> getShippingOptions() {
        assertTrue("NO SHIPPING METHODS DROPDOWN ON PAGE", isElementPresent(DROPDOWN_SHIPPING_METHODS));
        List<String> result = new ArrayList<String>();
        Select dropDown = new Select(driver.findElement(DROPDOWN_SHIPPING_METHODS));
        List<WebElement> optionsList = dropDown.getOptions();
        assertTrue("SHIPPING METHODS DROPDOWN HAS NO OPTIONS", optionsList.size() > 0);
        for (WebElement option : optionsList) {
            result.add(option.getText().trim());
        }
        return result;
    }

    /**
     * This method returns current Shipping Method name from select field
     *
     * @return String
     */
    public String getSelectedShippingOption() {
        assertTrue("NO SHIPPING METHODS DROPDOWN ON PAGE", isElementPresent(DROPDOWN_SHIPPING_METHODS));
        Select dropDown = new Select(driver.findElement(DROPDOWN_SHIPPING_METHODS));
        return dropDown.getFirstSelectedOption().getText().trim();
    }

            /* ---------------------------   START: Wait methods    --------------------------- */

    /**
     * Waits until Ajax mask is disappeared and Success page opens after submitting order. No Checks just waiting.
     */
    public void waitForOrderIsPlaced() {
        waitForElementNotVisible("PLACING ORDER MASK DID NOT DISAPPEAR AFTER 120 SECONDS", LOADING_MASK_PLACING_ORDER, 120);
    }

    /**
     * Waits till Shipment variants are loaded
     */
    public void waitTillShipmentIsLoaded() {
        waitForElementIsVisible(SHIPPING_AMOUNT);
    }

    /**
     * Waits till loading step data mask will appear and disappear
     */
    public void waitForLoadStepData() {
        try {
            waitForElementIsVisible(LOADING_MASK_LOADING_STEP_DATA, 30);
            waitForElementNotVisible(LOADING_MASK_LOADING_STEP_DATA, 30);
        } catch (Exception ex) {
            logger.warn("Looks like shipping already loaded");
        }
    }

    /**
     * This method will wait till Tax will change
     */
    public void waitTillTaxWillChange() {
        waitForElementIsVisible(TAX_AMOUNT);
        waitForLoadStepData();
    }

            /* ---------------------------   END: Wait methods    --------------------------- */

            /* ---------------------------   START: Verifications methods    --------------------------- */

    /**
     * This method verify the logged in status of customer on Checkout page.
     * The approach for that is verifying, that required element Welcome message is present on page
     */
    public void verifyLoggedInOnCheckoutPage() {
        waitForElementIsVisible(BUTTON_PLACE_ORDER);
        logger.info("Verifying logged in status on Checkout page");
        assertTrue(driver.findElement(WELCOME_ELEMENT).isDisplayed());
    }


    /**
     * This method verifies that saved shipping address is displayed in the Sipping Address Section on Checkout Page
     * And New Sipping Address Form is hidden.
     */
    public void verifyDropDownCheckoutPagePreSavedShippingIsDisplayed() {
        logger.info("Verify, that Dropdown for selecting pre-saved shipping information is displayed on checkout page");
        assertTrue(driver.findElement(DROPDOWN_SHIPPING_INFO).isDisplayed());
        assertTrue(driver.findElement(TEXT_SHIPPING_INFO_DISPLAYED_INFO).isDisplayed());
        assertFalse(driver.findElement(FORM_SHIPPING_INFO_NEW_ADDRESS).isDisplayed());
    }

    /**
     * This method verifies that saved billing address is displayed in the Billing Address Section on Checkout Page
     * And New Sipping Address Form is hidden.
     */
    public void verifyCheckoutPagePreSavedBillingIsDisplayed() {
        logger.info("Verifying Dropdown for selecting pre-saved shipping information is displayed on checkout page");
        assertTrue(driver.findElement(DROPDOWN_BILLING_INFO).isDisplayed());
        assertTrue(driver.findElement(TEXT_BILLING_INFO_DISPLAYED_INFO).isDisplayed());
        assertFalse(driver.findElement(FORM_BILLING_INFO_NEW_ADDRESS).isDisplayed());
    }

    /**
     * This method checks "New Billing Address Form" is displayed on checkout page and Throws Exception if not.
     */
    public void verifyBillingNewAddressFormDisplayed() {
        logger.info("Verifying the Form for adding new billing address is displayed on checkout page");
        assertTrue(driver.findElement(FORM_BILLING_INFO_NEW_ADDRESS).isDisplayed());
    }

    /**
     * This method checks "New Shipping Address Form" is displayed on checkout page and Throws Exception if not.
     */
    public void verifyShippingNewAddressFormDisplayed() {
        logger.info("Verifying the Form for adding new shipping address is displayed on checkout page");
        assertTrue(driver.findElement(FORM_SHIPPING_INFO_NEW_ADDRESS).isDisplayed());
    }

    /**
     * This method checks "Shipping Step" block is not present on checkout page and Throws Exception if present.
     */
    public void verifyShippingStepIsNotPresent() {
        logger.info("Verifying the Shipping Step is not present on checkout page");
        assertFalse("SHIPPING IS NOT ABSENT", isElementPresent(BLOCK_SHIPPING_STEP));
    }

    /**
     * Method verified, that link Log Out is visible on Checkout page
     */
    public void verifyLogOutLinkOnCheckoutPage() {
        logger.info("Verification, that logout link is displayed on checkout page");
        assertTrue(driver.findElement(LINK_LOG_OUT).isDisplayed());
    }

    /**
     * This method verify the logged out status of customer on Checkout page.
     * The approach for that is verifying, that required element Log in button is present on page
     */
    public void verifyYouGotGuestCheckout() {
        logger.info("VERIFICATION, THAT USER HAS LOGGED OUT STATUS ON CHECKOUT PAGE");
        assertTrue(driver.findElement(BUTTON_LOGIN).isDisplayed());
    }

    /**
     * Verifies that specified Gifting Message is present on the Checkout Page
     *
     * @param textToVerify String text message that should be present on the page
     */
    public void verifyGiftingTextIsPresent(String textToVerify) {
        waitForElementIsVisible(BUTTON_PLACE_ORDER);
        assertTrue("COULD NOT FIND MESSAGE's TEXT",
                isElementPresent(By.xpath("//tr[contains(@class, 'giftMessage') and contains(., '" + textToVerify + "')]")));
    }

    /**
     * Verifies that specified Gift Box is present on the Checkout Page
     */
    public void verifyGiftBoxIsPresent() {
        waitForElementIsVisible(BUTTON_PLACE_ORDER);
        assertTrue("COULD NOT FIND GIFT BOX TITLE!", isElementPresent(TITLE_GIFT_BOX_PRODUCT_NAME));
    }

    /**
     * Verifies that specified Gifting Message is present on the Checkout Page
     *
     * @param textToVerify String text message that should be present on the page
     */
    public void verifyGiftBoxTextIsPresent(String textToVerify) {
        assertTrue("COULD NOT FIND MESSAGE's TEXT",
                isElementPresent(By.xpath("//div[contains(@class, 'giftMessage') and contains(., '" + textToVerify + "')]")));
    }


    /**
     * Verifies that list of countries is default
     */
    public void verifyListOfCountries() {
        assertTrue("SHIPPING COUNTRY DROP DOWN IS NOT THERE!", driver.findElement(DROPDOWN_SHIPPING_ADDRESS_COUNTRY).isDisplayed());
        Select select = new Select(driver.findElement(DROPDOWN_SHIPPING_ADDRESS_COUNTRY));
        List<WebElement> options = select.getOptions();
        int length = 0;
        for (WebElement option : options) {
            if (Arrays.asList(COUNTRY_LIST).contains(option.getText())) {
                length += 1;
            }
        }
        assertTrue(length == COUNTRY_LIST.length);
    }

    /**
     * Verifies that all fields in Shipping form are present
     */
    public void verifyNewShippingFormHasAllInputs() {
        assertTrue("SHIPPING FIRST NAME NOT FOUND", isElementPresent(FIELD_SHIPPING_ADDRESS_FIRST_NAME));
        assertTrue("SHIPPING LAST NAME NOT FOUND", isElementPresent(FIELD_SHIPPING_ADDRESS_LAST_NAME));
        assertTrue("SHIPPING STREET LINE 1 NOT FOUND", isElementPresent(FIELD_SHIPPING_ADDRESS_STREET_1));
        assertTrue("SHIPPING STREET LINE 2 NOT FOUND", isElementPresent(FIELD_SHIPPING_ADDRESS_STREET_2));
        assertTrue("SHIPPING CITY NOT FOUND", isElementPresent(FIELD_SHIPPING_ADDRESS_CITY));
        assertTrue("SHIPPING STATE NOT FOUND", isElementPresent(DROPDOWN_SHIPPING_ADDRESS_STATE));
        assertTrue("SHIPPING COUNTRY NOT FOUND", isElementPresent(DROPDOWN_SHIPPING_ADDRESS_COUNTRY));
        assertTrue("SHIPPING ZIP CODE NOT FOUND", isElementPresent(FIELD_SHIPPING_ADDRESS_ZIP_CODE));
        assertTrue("SHIPPING PHONE NOT FOUND", isElementPresent(FIELD_SHIPPING_ADDRESS_TELEPHONE));
    }

    /**
     * Verifies that all fields in Payment form are present
     */
    public void verifyNewPaymentFormHasAllInputs() {
        assertTrue("NO INPUT FOR CREDIT CARD FOUND", isElementPresent(FIELD_BILLING_CC_NUMBER));
        assertTrue("NO SELECT FOR CREDIT CARD EXPIRATION MONTH FOUND", isElementPresent(DROPDOWN_BILLING_CC_EXPIRATION_MONTH));
        assertTrue("NO SELECT FOR CREDIT CARD EXPIRATION YEAR FOUND", isElementPresent(DROPDOWN_BILLING_CC_EXPIRATION_YEAR));
    }

    /**
     * Verifies that all fields in Create Account form are present
     */
    public void verifyCreateAccountForm() {
        assertTrue("FIELD EMAIL IS ABSENT", isElementPresent(FIELD_CREATE_ACCOUNT_EMAIL));
        assertTrue("FIELD EMAIL IS ABSENT", isElementPresent(FIELD_CREATE_ACCOUNT_PASSWORD));
    }
                /* ---------------------------   END: Verifications methods    --------------------------- */

    /**
     * Searches for validation error message for target element, returns "true" any known error is displayed
     *
     * @param locator By is a locator of the element
     * @return Boolean true if validation error message is displayed
     */
    public boolean isValidationErrorDisplayedForElement(By locator) {
        assertTrue("CANNOT FIND ELEMENT WITH LOCATOR: " + locator, isElementPresent(locator));
        String foundElemClass = driver.findElement(locator).getAttribute("class");
        if (foundElemClass.contains("select")) {
            String foundElemId = driver.findElement(locator).getAttribute("id");
            return driver.findElement(By.id("advice-validate-select-" + foundElemId)).isDisplayed();
        } else if (foundElemClass.contains("email") & foundElemClass.contains("validation-failed")) {
            String foundElemId = driver.findElement(locator).getAttribute("id");
            return driver.findElement(By.id("advice-validate-email-" + foundElemId)).isDisplayed();
        } else if (foundElemClass.contains("maxlength")) {
            String foundElemId = driver.findElement(locator).getAttribute("id");
            return driver.findElement(By.id("advice-required-entry-" + foundElemId)).isDisplayed();
        } else if (foundElemClass.contains("password")) {
            String foundElemId = driver.findElement(locator).getAttribute("id");
            return driver.findElement(By.id("advice-required-entry-" + foundElemId)).isDisplayed();
        } else {
            return false;
        }
    }


    /**
     * Check if specified shipping method is available in DropDown
     *
     * @return boolean
     */
    public boolean isShippingMethodPresentInDropDownByText(String shippingMethodName) {
        List<String> optionsList = getShippingOptions();
        return optionsList.contains(shippingMethodName);
//        for (String option: optionsList) {
//            if (option.equals(shippingMethodName)) {
//                return true;
//            }
//        }
//        return false;
    }
}
