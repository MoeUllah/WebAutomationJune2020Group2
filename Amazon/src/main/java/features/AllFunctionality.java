package features;


import base.CommonAPI;
import amazonpages.*;
import amazonpages.AllDropDownMenu.ComputerPage;
import com.mongodb.client.FindIterable;
import datasources.ConnectToMongoDB;
import datasupply.FetchAccountsList;
import datasupply.FetchGroceryList;
import datasupply.FetchTheSteps;
import org.bson.Document;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;

public class AllFunctionality {

    //Need to use this class to implement all features, basically run all test cases from pages class under java package.
    //Look at Mafi bhai NYPost module.

    HomePage homePage;
    SearchResultPage searchResultPage;
    SignInPage signInPage;
    CreateAccountPage createAccountPage;
    AllsDropDownPage allsDropDownPage;
    ComputerPage computerPage;
    LaptopsPage laptopsPage;
    LaptopsResultPage laptopsResultPage;
    ShippingPaymentPage shippingPaymentPage;
    CartPage cartPage;
    String allSelectionValue="   Baby";
    String selectAddressZipCodeHomePage="11230";
    String selectCountryShipHomePage="Australia";
    String preferredCountry="Australia";

    //HomePage test cases
    public void verifyFullTitle(WebDriver driver){
        String actualTitle=driver.getTitle();
        String expectedTitle="Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
        Assert.assertEquals(expectedTitle,actualTitle);
        System.out.println(actualTitle);
    }

    public void verifyPartialTitle(WebDriver driver){
        Boolean actual=driver.getTitle().contains("Amazon.com: Online");
        Boolean expected=true;
        Assert.assertEquals(expected,actual);
    }

    public void getHomePageURL(WebDriver driver){
        String actualHomeURL=driver.getCurrentUrl();
        String expectedHomeURL="https://www.amazon.com/";
        Assert.assertEquals(expectedHomeURL,actualHomeURL);
        System.out.println(actualHomeURL);
    }

    public void getPageSourceHomePage(WebDriver driver){
        String pageSource=driver.getPageSource();
        boolean actual=pageSource.contains("<!doctype html>");
        boolean expected=true;
        System.out.println(pageSource);
    }
    public void getListAllDropDownMenu(WebDriver driver) {
        homePage = PageFactory.initElements(driver, HomePage.class);
        List<String> allMenuList = CommonAPI.getListOfStringText(HomePage.allDropDownMenu, "All-Drop-Down");
        String lastAllDropDownText=allMenuList.get(allMenuList.size()-1);
        System.out.println("The last drop-down text is " + lastAllDropDownText);
        String expectedText="Whole Foods Market";
        Assert.assertEquals(expectedText,lastAllDropDownText);
    }

    public void DescendingListAllDropDownMenu(WebDriver driver){
        homePage = PageFactory.initElements(driver, HomePage.class);
        List<String> descendingAllMenu=CommonAPI.descendingListOfText(HomePage.allDropDownMenu);
        String firstAllDropDownText=descendingAllMenu.get(0);
        System.out.println("The first drop-down text in descending order is " + firstAllDropDownText);
        String expectedText="Women";
        Assert.assertEquals(expectedText,firstAllDropDownText);
    }

    public void AscendingListAllDropDownMenu(WebDriver driver){
        homePage = PageFactory.initElements(driver, HomePage.class);
        List<String> ascendingAllMenu=CommonAPI.ascendingListOfText(HomePage.allDropDownMenu);
        String firstAllDropDownText=ascendingAllMenu.get(0);
        System.out.println("The first drop-down text in ascending order is " + firstAllDropDownText);
        String expectedText="Alexa Skills";
        Assert.assertEquals(expectedText,firstAllDropDownText);
    }

    public void getAllDropDownMenuCount(WebDriver driver){
        homePage = PageFactory.initElements(driver, HomePage.class);
        int actualSize=HomePage.allDropDownMenu.size();
        System.out.println("The All-Drop-Down menu has a size of " + actualSize);
        int expectedSize=59;
        Assert.assertEquals(expectedSize,actualSize);
    }

    public void selectAValueAllDropDown(WebDriver driver){
        homePage = PageFactory.initElements(driver, HomePage.class);
        CommonAPI.selectAValue(HomePage.allDropDownWebElement, allSelectionValue);
        String actual=HomePage.allDropDownSearchLabel.getText();
        String expected=allSelectionValue;
        Assert.assertEquals(expected,actual);
    }

    public void amazonSearchExcel(WebDriver driver) throws IOException {
        FetchGroceryList fetchGroceryList=new FetchGroceryList();
        String [] groceries=fetchGroceryList.getDataFromExcelFile();
        homePage= PageFactory.initElements(driver,HomePage.class);
        for(int i=1;i<groceries.length;i++) {
            String grocery=groceries[i];
            HomePage.searchBarWebElement.clear();
            searchResultPage= homePage.enterSearchQueryOnSearchBar(driver,grocery);
            System.out.println(searchResultPage.getTitleSearchPage());
            Boolean actual=searchResultPage.getTitleSearchPage().contains(groceries[i]);
            Boolean expected=true;
            Assert.assertEquals(expected,actual);
            driver.navigate().back();
        }
    }

    public boolean verifyAmazonLogoDisplayed(WebDriver driver){
        homePage= PageFactory.initElements(driver,HomePage.class);
        boolean isDisplayedActual=HomePage.amazonLogo.isDisplayed();
        boolean isDisplayedExpected=true;
        Assert.assertEquals(isDisplayedExpected,isDisplayedActual);
        return isDisplayedActual;
    }

    public void signInToAmazon(WebDriver driver){
        homePage= PageFactory.initElements(driver,HomePage.class);
        signInPage=homePage.goToSignUpPage(driver);
        signInPage.enterEmailAddress("yourEmail");
        signInPage.enterPassword("yourPassword");
        boolean amazonLogoIsDisplyed=verifyAmazonLogoDisplayed(driver);
        boolean expectingTrue=true;
        Assert.assertEquals(amazonLogoIsDisplyed,expectingTrue);
    }

    public void getHamburgerDropDownLinks(WebDriver driver) {
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.clickOnHamburgerIconMenu();
        List<String> hamburgerMenuLinksList = CommonAPI.getListOfStringText(HomePage.hamburgerDropDownLinksMenu, "Hamburger-Drop-Down links");
        System.out.println(hamburgerMenuLinksList.size());
        String lastHamburgerDropDownLink=hamburgerMenuLinksList.get(hamburgerMenuLinksList.size()-1);
        System.out.println("The last Hamburger-Drop-Down link is " + lastHamburgerDropDownLink);
        String expectedText="Sign In";
        Assert.assertEquals(expectedText,lastHamburgerDropDownLink);
    }

    public void DescListHamburgerDDLinks(WebDriver driver){
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.clickOnHamburgerIconMenu();
        List<String> descHamburgerLinks=CommonAPI.descendingListOfText(HomePage.hamburgerDropDownLinksMenu);
        String firstHamburgerDDLink=descHamburgerLinks.get(0);
        System.out.println("The first drop-down text in descending order is " + firstHamburgerDDLink);
        String expectedText="Your Account";
        Assert.assertEquals(expectedText,firstHamburgerDDLink);
    }

    public void AscListHamburgerDDLinks(WebDriver driver){
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.clickOnHamburgerIconMenu();
        List<String> AscHamburgerLinks=CommonAPI.ascendingListOfText(HomePage.hamburgerDropDownLinksMenu);
        String firstHamburgerDDLink=AscHamburgerLinks.get(0);
        System.out.println("The first drop-down text in ascending order is " + firstHamburgerDDLink);
        String expectedText="#FoundItOnAmazon";
        Assert.assertEquals(expectedText,firstHamburgerDDLink);
    }

    public void getHamburgerDDLinksCount(WebDriver driver){
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.clickOnHamburgerIconMenu();
        int actualSize=HomePage.hamburgerDropDownLinksMenu.size();
        System.out.println("The Hamburger-Drop-Down menu has " + actualSize + " links.");
        try{
            int expectedSize=46;
            Assert.assertEquals(expectedSize,actualSize);
        }catch(Throwable ex) {
            try {
                int expectedSize = 47;
                Assert.assertEquals(expectedSize, actualSize);
        }catch(Throwable ex1){
                   int expectedSize = 48;
                   Assert.assertEquals(expectedSize, actualSize);
            }
        }

    }

    public void selectYourUSAddressZip(WebDriver driver) throws InterruptedException {
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.clickSelectAddressButton();
        homePage.typeUSZipCode(selectAddressZipCodeHomePage);
        homePage.clickApplyButton();
        homePage.clickDoneButton();
        Thread.sleep(2000);
        boolean containsZip=HomePage.selectAddressButton.getText().contains(selectAddressZipCodeHomePage);
        boolean expected=true;
        System.out.println(HomePage.selectAddressButton.getText());
        Assert.assertEquals(expected,containsZip);
    }

    public void selectAddressShippingOutside(WebDriver driver) throws InterruptedException {
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.clickSelectAddressButton();
        homePage.clickShipOutsideUS(selectCountryShipHomePage);
        homePage.clickDoneButton();
        Thread.sleep(2000);
        boolean containsZip=HomePage.selectAddressButton.getText().contains(selectCountryShipHomePage);
        boolean expected=true;
        System.out.println(HomePage.selectAddressButton.getText());
        Assert.assertEquals(expected,containsZip);
    }

    public void switchTabsToAmazonFooterLinks(WebDriver driver){
        homePage = PageFactory.initElements(driver, HomePage.class);
        CommonAPI.switchToTabsOfDiffLinks(HomePage.amazonFooterTableUnderLogo);
        verifyFullTitle(driver);
    }

    public void getAmazonFooterLinkText(WebDriver driver){
        homePage = PageFactory.initElements(driver, HomePage.class);
        List<String> footerLinksText=CommonAPI.getListOfStringText(HomePage.amazonFooterTableUnderLogo,"Amazon-Bottom-Footer links");
        String actualLastFooterLinkText=footerLinksText.get(footerLinksText.size()-1);
        String expectedtext="Amazon Second Chance" +"\n" + "Pass it on, trade it in," + "\n" + "give it a second life";
        Assert.assertEquals(expectedtext,actualLastFooterLinkText);
    }

    public void click6pmFooterLink(WebDriver driver){
        homePage = PageFactory.initElements(driver, HomePage.class);
        CommonAPI.clickByAttributeValue(HomePage.amazonFooterTableUnderLogo,"innerHTML","6pm");
        String actual6pmLinkURL=driver.getCurrentUrl();
        String expected6pmURL="https://www.6pm.com/";
        Assert.assertEquals(expected6pmURL,actual6pmLinkURL);
        System.out.println(driver.getCurrentUrl());
    }

    //not working
    public void changeLanguageFooter(WebDriver driver) throws InterruptedException {
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.clickLanguageFooter();
        homePage.clickEspanolRadioButton();
        //boolean isSelected=HomePage.espanolRadioButton.isSelected();
        homePage.clickSaveChangesButton();
        //boolean expected=true;
        Thread.sleep(5000);
        String actualText=HomePage.pickLanguageFooter.getText();
        String expectedText="Espanol";
        Assert.assertEquals(actualText,expectedText);
    }

    public void changeCountryFooter(WebDriver driver) throws InterruptedException {
        homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.clickCountryFooter();
        homePage.pickAustraliaPreferredCountry(preferredCountry);
        homePage.clickGoToWebsite();
//        String actualURL=driver.getCurrentUrl();
        String actualURL=CommonAPI.switchWindowGetTitle();
        String expectedURL="https://www.amazon.com.au/?ref=icp_country_us_t1";
        Assert.assertEquals(actualURL,expectedURL);
    }

    public void amazonSearchExcel1(WebDriver driver) throws IOException {
        FetchGroceryList fetchGroceryList=new FetchGroceryList();
        String [] groceries=fetchGroceryList.getDataFromExcelFile();
        homePage= PageFactory.initElements(driver,HomePage.class);
        searchResultPage=PageFactory.initElements(driver,SearchResultPage.class);
        for(int i=1;i<groceries.length;i++) {
            String grocery=groceries[i];
            HomePage.searchBarWebElement.clear();
            homePage.enterSearchQueryOnSearchBar(driver,grocery);
            System.out.println(driver.getTitle());
            Boolean actual=driver.getTitle().contains(grocery);
            Boolean expected=true;
            Assert.assertEquals(expected,actual);
            searchResultPage.clickFirstPicture();
            searchResultPage.getProductTitle();
            String rating=searchResultPage.getProductRating();
            Boolean actual1=rating.contains("stars");
            Boolean expected1=true;
            Assert.assertEquals(actual1,expected1);
            driver.navigate().back();
            driver.navigate().back();
        }
    }


    public void buyLaptopUsingAllMenu(WebDriver driver){
        homePage= PageFactory.initElements(driver,HomePage.class);
        allsDropDownPage=PageFactory.initElements(driver,AllsDropDownPage.class);
        computerPage=PageFactory.initElements(driver,ComputerPage.class);
        laptopsPage=PageFactory.initElements(driver,LaptopsPage.class);
        laptopsResultPage=PageFactory.initElements(driver,LaptopsResultPage.class);
        signInPage=PageFactory.initElements(driver,SignInPage.class);
        shippingPaymentPage=PageFactory.initElements(driver,ShippingPaymentPage.class);
        homePage.goToSignUpPage(driver);
        signInPage.enterEmailAddress("yourEmail");
        signInPage.enterPassword("yourPassword");
        homePage.clickOnAllDropDownMenu();
        allsDropDownPage.goToComputerPage(driver);
        computerPage.goToLaptopPage(driver);
        laptopsPage.goToLaptopsResultPage(driver);
        laptopsResultPage.goToCheckOut();
        shippingPaymentPage.setShippingAddress();
        shippingPaymentPage.pickDeliveryOption();
        shippingPaymentPage.addNewCard();
    }

    public void editCart(WebDriver driver){
        homePage= PageFactory.initElements(driver,HomePage.class);
        allsDropDownPage=PageFactory.initElements(driver,AllsDropDownPage.class);
        computerPage=PageFactory.initElements(driver,ComputerPage.class);
        laptopsPage=PageFactory.initElements(driver,LaptopsPage.class);
        laptopsResultPage=PageFactory.initElements(driver,LaptopsResultPage.class);
        cartPage=PageFactory.initElements(driver,CartPage.class);
        homePage.clickOnAllDropDownMenu();
        allsDropDownPage.goToComputerPage(driver);
        computerPage.goToLaptopPage(driver);
        laptopsPage.clickEssentialLaptopPicture();
        laptopsResultPage.clickAddToCart();
        laptopsResultPage.goToCartPage(driver);
        cartPage.deleteFromCart();
        driver.navigate().to(CommonAPI.urlHome);
    }

    public void createAccountsDB(WebDriver driver) throws IOException {
        homePage=PageFactory.initElements(driver,HomePage.class);
        createAccountPage=PageFactory.initElements(driver,CreateAccountPage.class);
        signInPage=PageFactory.initElements(driver,SignInPage.class);
        FetchAccountsList fetchAccountsList=new FetchAccountsList();
        fetchAccountsList.insertAccountsMongoDB();
        ConnectToMongoDB.connectToMongoDB();
        FindIterable<Document> iterable= ConnectToMongoDB.readAccountProfileFromMongoDB("Accounts");
        for(Document doc:iterable){
            String name = (String) doc.get("Name");
            String email = (String) doc.get("Email");
            String password = (String) doc.get("Password");
            homePage.goToSignUpPage(driver);
            signInPage.clickCreateAccountButton(driver);
            createAccountPage.enterName(name);
            createAccountPage.enterEmail(email);
            createAccountPage.enterPassword(password);
            createAccountPage.enterPasswordAgain(password);
            createAccountPage.clickContinue();
            driver.navigate().back();
            driver.navigate().back();
            driver.navigate().back();
       }
    }





    public void runAllTheFeatureTest(WebDriver driver) throws InterruptedException, IOException {
        FetchTheSteps fetchTheSteps = new FetchTheSteps();
        String [] featureSteps = fetchTheSteps.getDataFromExcelFile();
        for(int i=1; i<featureSteps.length; i++){
            select(featureSteps[i],driver);
        }
    }

    public void select(String featureName, WebDriver driver)throws InterruptedException,IOException{
        switch(featureName){
            //your case statements for your methods(steps from excel file) goes here
            case "verifyFullTitle":
                verifyFullTitle(driver);
                break;
            case "verifyPartialTitle":
                verifyPartialTitle(driver);
                break;
            case "getHomePageURL":
                getHomePageURL(driver);
                break;
            case "getPageSourceHomePage":
                getPageSourceHomePage(driver);
                break;
            case "getListAllDropDownMenu":
                getListAllDropDownMenu(driver);
                break;
            case "DescendingListAllDropDownMenu":
                DescendingListAllDropDownMenu(driver);
                break;
            case "AscendingListAllDropDownMenu":
                AscendingListAllDropDownMenu(driver);
                break;
            case "getAllDropDownMenuCount":
                getAllDropDownMenuCount(driver);
                break;
            case "selectAValueAllDropDown":
                selectAValueAllDropDown(driver);
                break;
            case "amazonSearchExcel":
                amazonSearchExcel(driver);
                break;
            case "verifyAmazonLogoDisplayed":
                verifyAmazonLogoDisplayed(driver);
                break;
            case "signInToAmazon":
                signInToAmazon(driver);
                break;
            case "getHamburgerDropDownLinks":
                getHamburgerDropDownLinks(driver);
                break;
            case "DescListHamburgerDDLinks":
                DescListHamburgerDDLinks(driver);
                break;
            case "AscListHamburgerDDLinks":
                AscListHamburgerDDLinks(driver);
                break;
            case "getHamburgerDDLinksCount":
                getHamburgerDDLinksCount(driver);
                break;
            case "selectYourUSAddressZip":
                selectYourUSAddressZip(driver);
                break;
            case "selectAddressShippingOutside":
                selectAddressShippingOutside(driver);
                break;
            case "switchTabsToAmazonFooterLinks":
                switchTabsToAmazonFooterLinks(driver);
                break;
            case "getAmazonFooterLinkText":
                getAmazonFooterLinkText(driver);
                break;
            case "click6pmFooterLink":
                click6pmFooterLink(driver);
                break;
            case "changeLanguageFooter":
                changeLanguageFooter(driver);
                break;
            case "changeCountryFooter":
                changeCountryFooter(driver);
                break;
            case "createAccountsDB":
                createAccountsDB(driver);
                break;
            case "buyLaptopUsingAllMenu":
                buyLaptopUsingAllMenu(driver);
                break;
            case "editCart":
                editCart(driver);
                break;
            default:
                throw new InvalidArgumentException("Invalid features choice");
        }
    }
}
