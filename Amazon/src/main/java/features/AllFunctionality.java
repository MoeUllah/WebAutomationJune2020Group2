package features;


import Base.CommonAPI;
import amazonpages.*;
import amazonpages.AllDropDownMenu.ComputerPage;
import com.mongodb.client.FindIterable;
import datasources.ConnectToExcelFile;
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

    //HomePage test cases

    public void getListAllDropDownMenu(WebDriver driver) {
        homePage = PageFactory.initElements(driver, HomePage.class);
        List<String> allMenuList = CommonAPI.getListOfStringText(HomePage.allDropDownMenu, "All-Drop-Down");
        for (String st : allMenuList) {
            System.out.println(st);
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
        signInPage.enterEmailAddress("automationJune2020@gmail.com");
        signInPage.enterPassword("automation123");
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

    public void amazonSearchExcel(WebDriver driver) throws IOException {
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
            case "buyLaptopUsingAllMenu":
                buyLaptopUsingAllMenu(driver);
                break;
            case "amazonSearchExcel":
                amazonSearchExcel(driver);
                break;
            case "editCart":
                editCart(driver);
                break;
            case "createAccountsDB":
                createAccountsDB(driver);
                break;
            default:
                throw new InvalidArgumentException("Invalid features choice");
        }
    }
}
