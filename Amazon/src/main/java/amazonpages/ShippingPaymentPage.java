package amazonpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ShippingPaymentPage {

    @FindBy(how= How.ID,using = "#enterAddressFullName")
    public static WebElement fullNameInputBox;

    @FindBy(css="#identity-add-new-address > div:nth-of-type(3)")
    public static WebElement address1InputBox;

    @FindBy(how= How.CSS, using = "#enterAddressCity")
    public static WebElement cityInputBox;

    @FindBy(how= How.CSS,using = "#enterAddressStateOrRegion")
    public static WebElement stateInputBox;

    @FindBy(how=How.CSS,using = "#identity-add-new-address > div:nth-of-type(7)")
    public static WebElement zipInputBox;

    @FindBy(how= How.CSS,using = "#enterAddressCountryCode > option:nth-child(233)")
    public static WebElement countryInputBox;

    @FindBy(how=How.ID,using = "#enterAddressPhoneNumber")
    public static WebElement phoneNumberInputBox;

    @FindBy(how= How.CSS, using = "textArea.validateInputCharactersOnBlur")
    public static WebElement textBox;

    @FindBy(how=How.CSS, using = "#GateCode")
    public static WebElement securityCodeInputBox;

    @FindBy(className = "sat-sun-expander-heading-text")
    public static WebElement weekendDeliveryExpander;

    @FindBy(css="#sat-sun-availability-checkbox-sat")
    public static WebElement saturdayCheckBox;

    @FindBy(css="#sat-sun-availability-checkbox-sun")
    public static WebElement sundayCheckBox;

    @FindBy(css=".a-spacing-base >label:nth-of-type(1) >span")
    public static WebElement yesRadioButton;

    @FindBy(name="shipToThisAddress")
    public static WebElement continueButton;

    @FindBy(css=".a-last.selected>div>div>div.radio-container")
    public static WebElement deliveryOptionRadioButton;

    @FindBy(css=".a-row>div>div>span:first-child>span>.a-button-text")
    public static WebElement continueButtonTop;

    @FindBy(how= How.CSS,using = ".apx-secure-registration-content-trigger-js >span>input")
    public static WebElement addNewCard;

    @FindBy(name="ApxSecureIframe")
    public static WebElement addCardFrame;

    @FindBy(how=How.CSS,using ="input[name='addCreditCardNumber']")
    public static WebElement cardNumberInputBox;

    @FindBy(name="ppw-accountHolderName")
    public static WebElement nameOnCardInputBox;

    @FindBy(css=".pmts-credit-card-expiration-date-input-box>span:first-child>span>span>i")
    public static WebElement expirationMonthArrowDown;

    @FindBy(css=".a-list-link>li:nth-of-type(10)")
    public static WebElement month10;

    @FindBy(css=".pmts-credit-card-expiration-date-input-box>span:last-child>span>span>i")
    public static WebElement expirationYearArrowDown;

    @FindBy(xpath="//a[contains(text(),'2024')]")
    public static WebElement year2024;

    @FindBy(css="input[name='ppw-updateEverywhereAddCreditCard']")
    public static WebElement defaultPaymentCheckBox;

    @FindBy(css="input[name*='AddCreditCardEvent']")
    public WebElement addCardButton;

    @FindBy(css=".a-button-close")
    public WebElement xButtonTopRight;
    
}
