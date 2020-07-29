package amazonpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import Base.CommonAPI;

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

    @FindBy(css=".nav-search-scope.nav-sprite")
    public static WebElement allDropDownWebElement;

    @FindAll({@FindBy( css="#searchDropdownBox option")})
    public static List<WebElement> allDropDownMenu;

    @FindBy(how= How.ID,using = "twotabsearchtextbox")
    public static WebElement searchBarWebElement;

    @FindBy(how= How.CSS,using = "#nav-link-accountList > div > span")
    public static WebElement signInWebElement;

    @FindBy(how=How.CSS,using = ".hm-icon.nav-sprite")
    public static WebElement hamburgerIconDropDown;

    @FindBy(css="#nav-search > form > div.nav-right > div > input")
    public static WebElement submitMagnifyingGlassButton;

    @FindBy(how=How.CLASS_NAME,using = "nav-sprite nav-logo-base")
    public static WebElement amazonLogo;

    @FindBy(how=How.ID,using = "nav-link-prime")
    public static WebElement tryPrimeButton;

    @FindBy(how=How.CLASS_NAME,using = "a-popover-trigger")
    public static WebElement deliverToButton;

    @FindBy(how=How.CSS,using = "navFooterMoreOnAmazon a")
    public static WebElement amazonFooterTableUnderLogo;

    @FindBy(how=How.CSS,using = ".icp-container-desktop a:nth-of-type(1)")
    public static WebElement pickLanguageFooter;

    @FindBy(how=How.CSS,using = ".icp-container-desktop a:nth-of-type(2)")
    public static WebElement pickCountryFooter;

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
    }

    public static void clickSubmit(){
        submitMagnifyingGlassButton.click();
    }
}


