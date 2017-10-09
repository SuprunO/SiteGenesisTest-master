package lcg.selenium;

import org.openqa.selenium.WebDriver;

/**
 * Created by alex on 07.10.2017.
 */
public class UserCredentials extends PageFactory{
    public UserCredentials(WebDriver driver) {
        super(driver);
    }

    /** SHIPPING PAGE Test User Information*/
    public static final String TEST_FIRST_NAME = "AutoFirstName";
    public static final String TEST_LAST_NAME = "AutoLastName";

    //Test Address information #1
    public static final String IL_STREET_1 = "666 Dundee Road";
    public static final String IL_STREET_2 = "666 Dundee Road";
    public static final String IL_CITY = "Northbrook";
    public static final String IL_STATE = "Illinois";
    public static final String IL_COUNTRY = "United States";
    public static final String IL_ZIP = "60062";
    public static final String IL_PHONE = "333-333-3333";
    public static final String IL_EMAIL = "dmazepa@lyonscg.com";

    //Test Address information #2
    public static final String NY_STREET_1 = "4183 Hoffman Avenue";
    public static final String NY_STREET_2 = "4183 Hoffman Avenue";
    public static final String NY_CITY = "New York";
    public static final String NY_STATE = "NY - New York";
    public static final String NY_COUNTRY = "United States";
    public static final String NY_ZIP = "10013";
    public static final String NY_PHONE = "9179156439";
    public static final String NY_EMAIL = "dmazepa@lyonscg.com";



    /** BILLING PAGE Test User Information*/
    /* Test credit card information */
    public static final String TEST_CARD_NAME = "TestName";
    public static final String TEST_CARD_NUMBER_VISA = "4111111111111111";

    public static final String TEST_CARD_TYPE = "Visa";
    public static final String TEST_CARD_MONTHYEAR = "1222";

    //FOR SITEGENESIS
    public static final String TEST_CARD_MONTH = "January";
    public static final String TEST_CARD_YEAR = "2019";

    public static final String TEST_CARD_CVV_VISA = "123";



    //not used
    public static final String CUSTOMER_EMAIL = "test1@speroteck.com";
    public static final String CUSTOMER_PASSWORD = "testthis";




}
