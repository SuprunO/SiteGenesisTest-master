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

import java.util.ArrayList;

import static lcg.selenium.UserCredentials.*;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

/**
 * A class, which contains implemented actions and verifications, which can be performed on Checkout page
 *
 * @author Speroteck QA Team (qa@speroteck.com)
 */
public class CheckoutBillingPage extends PageFactory {

    public Logger logger = Logger.getLogger(CheckoutBillingPage.class);

    public CheckoutBillingPage(WebDriver driver) {
        super(driver);
    }

    /**
     * ---------------------------   START: LOCATORS   ---------------------------
     */
    public static final By FIELD_BILLING_ADDRESS_EMAIL = By.cssSelector("#dwfrm_billing_billingAddress_email_emailAddress");
    public static final By BUTTON_REVIEW_YOUR_ORDER = By.cssSelector(".button-fancy-large");
    /* Make Billing Address as Shipping Locator */
    public static final String ID_BILLING_ADDRESS_SAME_AS_SHIPPING_CHECKBOX = ".form-row.sameshipping.label-inline.form-indent.notselect>label";
    public static final By CHECKBOX_BILLING_ADDRESS_SAME_AS_SHIPPING = By.cssSelector(ID_BILLING_ADDRESS_SAME_AS_SHIPPING_CHECKBOX);

    /* Billing Credit Card(CC) Step */

    public static final By FIELD_BILLING_CC_NAMEONCARD = By.cssSelector("#dwfrm_billing_paymentMethods_creditCard_owner");
    public static final By FIELD_BILLING_CC_NUMBER = By.xpath(".//div[@class='payment-method payment-method-expanded']/div[3]//input");

    public static final By FIELD_BILLING_CC_EXPIRATION_MONTHYEAR = By.cssSelector("#dwfrm_billing_paymentMethods_creditCard_expirationdate");
//For SiteGenesis
    public static final By SELECT_BILLING_CART_TYPE =By.xpath(".//select[@id='dwfrm_billing_paymentMethods_creditCard_type']");
    public static final By FIELD_BILLING_CC_EXPIRATION_MONTH= By.cssSelector("#dwfrm_billing_paymentMethods_creditCard_expiration_month");
    public static final By FIELD_BILLING_CC_EXPIRATION_YEAR= By.cssSelector("#dwfrm_billing_paymentMethods_creditCard_expiration_year");

    public static final By FIELD_BILLING_CC_CVV = By.xpath(".//div[@class='payment-method payment-method-expanded']/div[5]//input");

    /** ---------------------------   END: LOCATORS   --------------------------- */


    /** ---------------------------   START: EXECUTION METHODS   --------------------------- */
    /**
     * Fills in Billing Credit Card Form
     *
     * @param //sameAsShipping in case of selecting checkbox on billing page
     */
    public void fillBillingCCForm() {
        logger.info("Fill out Billing Credit Card Form");
        verifyInputFieldsLocatorsArePresentOnBillingPage();
        fillInInput(FIELD_BILLING_ADDRESS_EMAIL,IL_EMAIL);
        fillInInput(FIELD_BILLING_CC_NAMEONCARD, TEST_CARD_NAME);
        fillInInput(FIELD_BILLING_CC_NUMBER, TEST_CARD_NUMBER_VISA);
        selectValueInDropDown(SELECT_BILLING_CART_TYPE,TEST_CARD_TYPE);
        selectValueInDropDown(FIELD_BILLING_CC_EXPIRATION_MONTH, TEST_CARD_MONTH);
        selectValueInDropDown(FIELD_BILLING_CC_EXPIRATION_YEAR, TEST_CARD_YEAR);
        fillInInput(FIELD_BILLING_CC_CVV, TEST_CARD_CVV_VISA);

//        if (sameAsShipping){
//            waitElementToBeClickableAndClick(CHECKBOX_BILLING_ADDRESS_SAME_AS_SHIPPING, "same as shipping");
//        }
    }


    public void clickReviewYourOrder() {
        waitElementToBeClickableAndClick(BUTTON_REVIEW_YOUR_ORDER, "ReviewYourOrder");
    }
    /** ---------------------------   END: EXECUTION METHODS   --------------------------- */

    /**
     * Fills in Billing Address Form with values based on specified state name.
     * Note: If argument is not recognized fills in form with default values.
     *
     * @param stateName String "NEW_JERSEY"|"NEW_YORK"|"WASHINGTON"|"COLORADO"|any string
     */
    public void fillBillingAddressForm(String stateName) {
        // Get all fields and dropdowns to fill by specified state
        ArrayList<Field> billingAddressFields = getBillingFieldSet(stateName);
        assertThat("FIELD SET IS BLANK!", billingAddressFields.size(), not(0));
        logger.info("Fill out Billing Address Form");
        fillFieldsSet(billingAddressFields);
    }

    /**
     * Creates and returns Billing address fields to fill on checkout with values based on specified state name.
     * Note: If argument is not recognized fields with default values will be returned
     *
     * @param stateName String "NEW_JERSEY"|"NEW_YORK"|"WASHINGTON"|"COLORADO"|any string
     * @return ArrayList<Field> Fields set which is used in fillFieldsSet() method as argument
     */
    private ArrayList<Field> getBillingFieldSet(String stateName) {
        ArrayList<Field> resultFieldSet = new ArrayList<Field>();
        // create billing field sets for every declared state
        resultFieldSet.add(new Field("input", FIELD_BILLING_ADDRESS_FIRST_NAME, TEST_FIRST_NAME));
        resultFieldSet.add(new Field("input", FIELD_BILLING_ADDRESS_LAST_NAME, TEST_LAST_NAME));
        if (stateName.equals("NEW_JERSEY")) {
//
        }
        if (stateName.equals("IL - Illinois")) {
            resultFieldSet.add(new Field("input", FIELD_BILLING_ADDRESS_STREET_1, IL_STREET_1));
            resultFieldSet.add(new Field("input", FIELD_BILLING_ADDRESS_STREET_2, IL_STREET_2));
            resultFieldSet.add(new Field("input", FIELD_BILLING_ADDRESS_CITY, IL_CITY));
            resultFieldSet.add(new Field("dropdown", DROPDOWN_BILLING_ADDRESS_STATE, IL_STATE));
            resultFieldSet.add(new Field("input", FIELD_BILLING_ADDRESS_ZIP_CODE, IL_ZIP));
            resultFieldSet.add(new Field("dropdown", DROPDOWN_BILLING_ADDRESS_COUNTRY, IL_COUNTRY));
            resultFieldSet.add(new Field("input", FIELD_BILLING_ADDRESS_TELEPHONE, IL_PHONE));

        } else if (stateName.equals("NY - New York")) {
            resultFieldSet.add(new Field("input", FIELD_BILLING_ADDRESS_STREET_1, NY_STREET_1));
            resultFieldSet.add(new Field("input", FIELD_BILLING_ADDRESS_STREET_2, NY_STREET_2));
            resultFieldSet.add(new Field("input", FIELD_BILLING_ADDRESS_CITY, NY_CITY));
            resultFieldSet.add(new Field("dropdown", DROPDOWN_BILLING_ADDRESS_STATE, NY_STATE));
            resultFieldSet.add(new Field("input", FIELD_BILLING_ADDRESS_ZIP_CODE, NY_ZIP));
            resultFieldSet.add(new Field("dropdown", DROPDOWN_BILLING_ADDRESS_COUNTRY, NY_COUNTRY));
            resultFieldSet.add(new Field("input", FIELD_BILLING_ADDRESS_TELEPHONE, NY_PHONE));
        }
        return resultFieldSet;
    }
    /** ---------------------------   START: EXPECTED RESULTS   --------------------------- */


    /** ---------------------------   END: EXPECTED RESULTS   --------------------------- */


    /**
     * ---------------------------   START: Verifications methods    ---------------------------
     */
    public void verifyInputFieldsLocatorsArePresentOnBillingPage() {
        verifyLocatorsArePresent(FIELD_BILLING_CC_NAMEONCARD, "FIELD_BILLING_CC_NAMEONCARD");
        verifyLocatorsArePresent(FIELD_BILLING_CC_NUMBER, "FIELD_BILLING_CC_NUMBER");
     //FOR KATESPADE
        //   verifyLocatorsArePresent(FIELD_BILLING_CC_EXPIRATION_MONTHYEAR, "FIELD_BILLING_CC_EXPIRATION_MONTHYEAR");
        verifyLocatorsArePresent(FIELD_BILLING_CC_EXPIRATION_MONTH,"FIELD_BILLING_CC_EXPIRATION_MONTH");
        verifyLocatorsArePresent(FIELD_BILLING_CC_EXPIRATION_YEAR,"FIELD_BILLING_CC_EXPIRATION_YEAR");
        verifyLocatorsArePresent(FIELD_BILLING_CC_CVV, "FIELD_BILLING_CC_CVV ");
    }

    /** ---------------------------   END: Verifications methods    --------------------------- */


    /**
     * ---------------------------   START: LOCATORS UNUSED   ---------------------------
     */
      /* Billing Address Step */
    public static final By DROPDOWN_BILLING_INFO = By.id("billing-address-select");
    public static final By TEXT_BILLING_INFO_DISPLAYED_INFO = By.id("current-billing-customer-address");
    public static final By FORM_BILLING_INFO_NEW_ADDRESS = By.id("billing-new-address-form");
    public static final By FIELD_BILLING_ADDRESS_FIRST_NAME = By.id("dwfrm_billing_billingAddress_addressFields_firstName");
    public static final By FIELD_BILLING_ADDRESS_LAST_NAME = By.id("dwfrm_billing_billingAddress_addressFields_lastName");
    public static final By FIELD_BILLING_ADDRESS_STREET_1 = By.id("dwfrm_billing_billingAddress_addressFields_address1");
    public static final By FIELD_BILLING_ADDRESS_STREET_2 = By.id("dwfrm_billing_billingAddress_addressFields_address2");
    public static final By FIELD_BILLING_ADDRESS_CITY = By.id("dwfrm_billing_billingAddress_addressFields_city");
    public static final By DROPDOWN_BILLING_ADDRESS_STATE = By.id("dwfrm_billing_billingAddress_addressFields_states_state");
    public static final By DROPDOWN_BILLING_ADDRESS_COUNTRY = By.id("dwfrm_billing_billingAddress_addressFields_country");
    public static final By FIELD_BILLING_ADDRESS_ZIP_CODE = By.id("dwfrm_billing_billingAddress_addressFields_zip");
    public static final By FIELD_BILLING_ADDRESS_TELEPHONE = By.id("dwfrm_billing_billingAddress_addressFields_phone");

    /* Billing Credit Card(CC) Step */
    public static final By DROPDOWN_BILLING_CC_TYPE = By.id("creditcard_cc_type");     // investigate usage! no such dropdown...
    public static final By DROPDOWN_BILLING_CC_EXPIRATION_YEAR = By.id("creditcard_expiration_yr");

    /** ---------------------------   END: LOCATORS UNUSED   --------------------------- */


    /** ---------------------------   START: METHODS THAT CAN BE USEFUL   --------------------------- */




    /**
     * Creates and returns Billing address fields to fill on checkout with default values.
     *
     * @return ArrayList<Field> Fields set which is used in fillFieldsSet() method as argument
     */
    private ArrayList<Field> getBillingFieldSet() {
        return getBillingFieldSet("");
    }


    /**
     * Checks "Billing address same as shipping" checkbox if it is not already checked.
     */
    public void checkCheckboxBillingSameAsShipping() {
        checkCheckbox(CHECKBOX_BILLING_ADDRESS_SAME_AS_SHIPPING, "Billing address same as shipping");
    }

    /**
     * Un-checks "Billing address same as shipping" checkbox if it is not already un-checked.
     */
    public void uncheckSameAsShippingCheckbox() {
        uncheckCheckbox(CHECKBOX_BILLING_ADDRESS_SAME_AS_SHIPPING, "Billing address same as shipping");
    }

    /**
     * Fills in Billing Address Form with default values.
     */
    public void fillBillingAddressForm() {
        fillBillingAddressForm("");
    }



}
