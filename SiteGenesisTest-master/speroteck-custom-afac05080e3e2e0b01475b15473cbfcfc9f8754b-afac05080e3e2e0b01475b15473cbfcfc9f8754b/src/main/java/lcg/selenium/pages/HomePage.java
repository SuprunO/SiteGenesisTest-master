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

import lcg.selenium.PageFactory;
import lcg.selenium.TestFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static lcg.selenium.TestFactory.getStartURL;
import static lcg.selenium.TestFactory.log;
import static lcg.selenium.TestFactory.testCaseName;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * A class, which contains implemented actions and verifications, which can be performed on Home page
 *
 * @author Speroteck QA Team (qa@speroteck.com)
 */
public class HomePage extends PageFactory {

    private static final Logger logger = Logger.getLogger(TestFactory.class);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Opens Home Page entering url into the address field.
     */
    public void open() {
        log("Starting test.... " + testCaseName);
        log("Starting test: Guest Visitors have ability to place order");
        log("Test description: ");
        log("@Given: I am on \"Shopping Bag\" page as Guest with random product in cart.");
        log("@When: I go to Checkout as Guest");
        log("Stating step: ");
        log("@Given: I am on \"Shopping Bag\" page as Guest with random product in cart.");


        //logger.info(getStartURL() + " Status Code is " + getStatusCode(getStartURL()));
        logger.info("Opening URL: " + getStartURL());
        try {
            driver.get(getStartURL());
        }
        catch (Exception e) {
            assertEquals("HOME PAGE WAS NOT OPENED", getStartURL(), driver.getCurrentUrl());
            assertFalse("404 PAGE IS OPENED! BUT EXPECTED: " + getStartURL(), is404Page());
        }

    }


    /** ---------------------------   START: EXECUTION METHODS   --------------------------- */


    /** ---------------------------   END: EXECUTION METHODS   --------------------------- */



    /** ---------------------------   START: EXPECTED RESULTS   --------------------------- */


    /** ---------------------------   END: EXPECTED RESULTS   --------------------------- */



    /** ---------------------------   START: Verifications methods    --------------------------- */

    /** ---------------------------   END: Verifications methods    --------------------------- */








    /** ---------------------------   START: LOCATORS UNUSED   --------------------------- */
    /* Home Page elements */
    public static final String PATH_TO_BESTSELLERS_LIST = "//ul[contains(@class,'products-grid')]";

    /** ---------------------------   END: LOCATORS UNUSED   --------------------------- */


    /** ---------------------------   START: METHODS THAT CAN BE USEFUL   --------------------------- */


    /** ---------------------------   END: METHODS THAT CAN BE USEFUL   --------------------------- */


}
