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

package lcg.selenium.KateSpade;

/**
 * Simple login tests.  Can be run as load tests using JMeter or other load tool.
 *
 * @author Speroteck QA Team (qa@speroteck.com)
 */
//public class Checkout extends TestFactory {

    /**
     * Test requirement: White Glove product + SR Eligable product,	Split Shipping order, Payment-Visa, Shipping methods:
     * ShopRunner 2 days + White glove delivery. 	Billing different with Shipping.
     * NOT COMPLETED!!!!!!!!!
     */
//    @Test
//    public void testWhiteGloveProductShopRunnerEligibleProductSplitShipmentVisaShippingMethodsWhiteGloveAndShopRunnerBillingSameWithShippingGuest() {
//        log("Starting test.... " + testCaseName);
//        log("Starting test: Test requirement: White Glove product + SR Eligable product,Split Shipping order," +
//                " Payment-Visa, Shipping methods: ShopRunner 2 days + White glove delivery. Billing same with Shipping. Guest");
//        log("Test description: ");
//        log("@Given: I am on \"Shopping Bag\" page as Guest White Glove product + SR Eligable product");
//        log("@When: I go to Checkout as Guest");
//        log("@Then: I can fill all correct information and place order.");
//        log("Stating step: ");
//        log("\"@Given: I am on \"Shopping Bag\" page as Guest White Glove product 695895336404 + SR Eligable product 888445616327");
//        homePage().open();
//        productDetailsPage().openById("695895336404");
//        productDetailsPage().clickButtonAddToCart();
//        productDetailsPage().openById("888445616327");
//        productDetailsPage().clickButtonAddToCart();
//        log("@Given step: PASSED!");
//        log("Stating step: ");
//        log("@When: I go to Checkout as Guest");
//        shoppingCartPage().open();
//        shoppingCartPage().clickProceedToCheckoutButton();
//        checkoutLoginPage().clickButtonContinueAsGuest();
//        log("@When step: PASSED!");
//        log("Stating step: ");
//        log("@Then: I can fill all correct information and place order.");
//        checkoutShippingPage().fillShippingAddressForm("IL - Illinois");
//        checkoutShippingPage().clickContinueToPayment();
//        checkoutBillingPage().fillBillingCCFormSameAsShipping(true);
//        checkoutBillingPage().clickReviewYourOrder();
//        checkoutOrderReviewPage().clickPlaceOrderButton();
//        checkoutSuccessPage().assertOrderConfirmationIsOpened();
//        log("@Then step: PASSED!");
//    }
//}