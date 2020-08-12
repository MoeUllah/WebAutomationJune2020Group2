package amazonpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import base.CommonAPI;

import java.util.List;

public class HomePage {

    //Start from HomePage add the WebElement(s) for the page with the methods of those elements. Add more pages as your
    //module needs along the way like Mafi bhai. Try to connect one page to the next as well.

    //Very important:
    //1) Go to every module and change the OS if your mac to "OS X", if windows to "Windows" under the
    // runner.xml file.
    //2) For mac users the driver path in CommonAPI works with one dot, for Windows it works with 2 dots(Windows can
    //leave as is since I'm Windows. Mac change to one dot. Also you need to go to the connectoToSQLDatabase
    //class and also change to one dot for the path for mac users. For windows its set to two already, no worries.
    //The chromedriver is for chrome 84 if you got compatiblity issues follow and get the correct
    // browser-to-driver compatibility.
    //3) Under secret.properties file put your mySQL credentials: user, password and name of your database.
    //4) Continue to build your pages and connect some to others, test with assertions under AllFunctionality
    // class(just
    //add to it). Once done run the xmlrunner.
    //5)For mysql you can have compatibility issues, make sure if you have mySQL 8.0 you havew the connector jar to
    // 8.0 as well,
    //if you clone this it should be good.
    //6)Ill fix up the database part and excel. But for database to retrieve from database you need to make your
    //own methods based on the object you are trying to get if any.

//    @FindBy(css=".nav-search-scope.nav-sprite")
//    public static WebElement allDropDownWebElement;

    @FindBy(css="#searchDropdownBox")
    public static WebElement allDropDownWebElement;

    @FindBy(css="span.nav-search-label")
    public static WebElement allDropDownSearchLabel;

    @FindAll({@FindBy( css="#searchDropdownBox option")})
    public static List<WebElement> allDropDownMenu;

    @FindBy(how= How.ID,using = "twotabsearchtextbox")
    public static WebElement searchBarWebElement;

    @FindBy(how= How.CSS,using = "#nav-link-accountList > div > span")
    public static WebElement signInWebElement;

    @FindBy(how=How.CSS,using = ".hm-icon.nav-sprite")
    public static WebElement hamburgerIconDropDown;

    @FindAll({@FindBy(css=".hmenu.hmenu-visible a")})
    public static List<WebElement> hamburgerDropDownLinksMenu;

    @FindBy(css=".hmenu.hmenu-visible>li:nth-of-type(2)")
    public static WebElement primeVideoOptionHamburgerDD;

    @FindBy(css="#nav-search > form > div.nav-right > div > input")
    public static WebElement submitMagnifyingGlassButton;

    @FindBy(how=How.CSS,using = ".nav-sprite.nav-logo-base")
    public static WebElement amazonLogo;

    @FindBy(how=How.ID,using = "nav-link-prime")
    public static WebElement tryPrimeButton;

    @FindBy(how=How.CSS,using = "#glow-ingress-block>span:last-child")
    public static WebElement selectAddressButton;

    @FindBy(how=How.CSS,using = "#GLUXCountryList")
    public static WebElement shipOutsideUSPullDown;

    @FindBy(how=How.CSS,using = "ul.a-list-link>li:first-child>a")
    public static WebElement australiaOption;

    @FindBy(how=How.ID,using = "GLUXZipUpdateInput")
    public static WebElement zipCodeUSBox;

    @FindBy(how=How.CSS,using = "#GLUXZipUpdate > span > input")
    public static WebElement applyButton;

    @FindBy(how=How.CSS,using = "button.a-button-text")
    public static WebElement doneButton;

    @FindAll({@FindBy(how=How.CSS,using = ".navFooterMoreOnAmazon a")})
    public static List<WebElement> amazonFooterTableUnderLogo;

    @FindBy(how=How.CSS,using = ".icp-container-desktop a:nth-of-type(1)")
    public static WebElement pickLanguageFooter;

    @FindBy(how=How.CSS,using = ".a-column>div>div>div:nth-child(4)>div>label>i")
    public static WebElement espanolRadioButton;

    @FindBy(how=How.CSS,using = "#icp-btn-save>span")
    public static WebElement saveChangesButton;

    @FindBy(how=How.CSS,using = ".icp-container-desktop a:nth-of-type(2)")
    public static WebElement pickCountryFooter;

    @FindBy(how=How.CSS,using = "#icp-selected-country")
    public static WebElement preferredCountryDropDown;

    @FindBy(how=How.CSS,using = ".a-button.a-spacing-top-mini.a-button-primary input")
    public static WebElement goToWebsiteButton;

    @FindBy(how=How.CLASS_NAME,using = "navFooterBackToTopText")
    public static WebElement backToTop;


    public static WebElement getAllDropDownWebElement() {
        return allDropDownWebElement;
    }

    public static WebElement getSearchBarWebElement() {
        return searchBarWebElement;
    }

    public static WebElement getSignInWebElement() {
        return signInWebElement;
    }

    public static WebElement getHamburgerIconDropDown() {
        return hamburgerIconDropDown;
    }

    public SignInPage goToSignUpPage(WebDriver driver){
        signInWebElement.click();
        return new SignInPage(driver);
    }

    public SearchResultPage enterSearchQueryOnSearchBar(WebDriver driver,String valueToSearch){
        CommonAPI.typeOnElement(searchBarWebElement,valueToSearch);
        return new SearchResultPage(driver);
    }

    public void clickOnAllDropDownMenu(){
        allDropDownWebElement.click();
    }

    public void clickOnHamburgerIconMenu(){
        hamburgerIconDropDown.click();
        CommonAPI.waitUntilVisible(HomePage.primeVideoOptionHamburgerDD);
    }

    public static void clickSubmit(){
        submitMagnifyingGlassButton.click();
    }

    public TryPrimePage goToTryPrimePage(WebDriver driver){
        tryPrimeButton.click();
        return new TryPrimePage(driver);
    }

    public void clickSelectAddressButton(){
        selectAddressButton.click();
    }

    public void typeUSZipCode(String zipCode){
        zipCodeUSBox.sendKeys(zipCode);
    }

    public void clickDoneButton(){
        doneButton.click();
    }

    public void clickApplyButton(){
        applyButton.click();
    }

    public void clickShipOutsideUS(String country){
        CommonAPI.selectAValueGeneric(shipOutsideUSPullDown,country);
    }

    public void clickAustralia(){
        australiaOption.click();
    }

    public void clickLanguageFooter(){
        pickLanguageFooter.click();
    }

    public void clickEspanolRadioButton(){
        espanolRadioButton.click();
    }

    public void clickSaveChangesButton(){
        saveChangesButton.click();
    }

    public void clickCountryFooter(){
        pickCountryFooter.click();
    }

    public void pickAustraliaPreferredCountry(String country){
        CommonAPI.selectAValueGeneric(preferredCountryDropDown,country);
    }

    public void clickGoToWebsite(){
        goToWebsiteButton.click();
    }

}




















//    @FindBy(how=How.CSS,using = "span.a-button-text.a-declarative")
//    public static WebElement countryMenuDD;
//
//    @FindBy(how=How.CSS,using = ".a-nostyle.a-list-link>li:first-child")
//    public static WebElement countryMenuAustraliaOption;
