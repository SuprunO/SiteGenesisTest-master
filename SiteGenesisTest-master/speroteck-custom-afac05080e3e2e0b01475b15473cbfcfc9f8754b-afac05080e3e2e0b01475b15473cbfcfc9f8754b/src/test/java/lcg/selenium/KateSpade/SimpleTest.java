package lcg.selenium.KateSpade;

import lcg.selenium.TestFactory;
import org.junit.Test;

/**
 * Created by alex on 04.10.2017.
 */
public class SimpleTest extends TestFactory {

    @Test
    public void testGuestCanPlaceOrderPassed() {
        homePage().open();
        productDetailsPage().openById("vizio-vp503");
        productDetailsPage().clickButtonAddToCart();
        header().clickCheckoutButtonInMiniCart();
        shoppingCartPage().clickProceedToCheckoutButton();
        checkoutLoginPage().clickButtonContinueAsGuest();
        checkoutShippingPage().fillShippingAddressForm_AddressSameAsBilling("Illinois",true);
        checkoutBillingPage().fillBillingCCForm();
        checkoutBillingPage().clickReviewYourOrder();
        checkoutOrderReviewPage().clickPlaceOrderButton();
        checkoutSuccessPage().assertOrderConfirmationIsOpened();
    }

    //  @Test
//    public void testGuestCanPlaceOrderFailed() {
//        log("Starting test.... " + testCaseName);
//        log("Starting test: Guest Visitors have ability to place order");
//        log("Test description: ");
//        log("@Given: I am on \"Shopping Bag\" page as Guest with random product in cart.");
//        log("@When: I go to Checkout as Guest");
//        log("@Then: I can fill all correct information and place order.");
//        log("Stating step: ");
//        log("@Given: I am on \"Shopping Bag\" page as Guest with random product in cart.");
//        this.homePage().open();
//        this.productDetailsPage().openById("888445608001");
//        this.productDetailsPage().clickButtonAddToCart();
//        this.header().clickCheckoutButtonInMiniCart();
//        log("@Given step: PASSED!");
//        log("Stating step: ");
//        log("@When: I go to Checkout as Guest");
//        this.shoppingCartPage().clickProceedToCheckoutButton();
//        this.checkoutLoginPage().clickButtonContinueAsGuest();
//        log("@When step: PASSED!");
//        log("Stating step: ");
//        log("@Then: I can fill all correct information and place order.");
//        this.checkoutShippingPage().fillShippingAddressForm("IL - Illinois");
//        this.checkoutBillingPage().fillBillingCCFormSameAsShipping(true);
//        this.checkoutOrderReviewPage().verifyShippingAddressHeaderisPresent();
//        log("@Then step: PASSED!");
//    }

}
