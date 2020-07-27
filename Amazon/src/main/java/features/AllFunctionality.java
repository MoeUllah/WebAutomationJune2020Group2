package features;


import amazonpages.*;
import amazonpages.AllDropDownMenu.ComputerPage;
import datasupply.FetchTheSteps;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;

import java.io.IOException;

public class AllFunctionality {

    //Need to use this class to implement all features, basically run all test cases from pages class under java package.
    //Look at Mafi bhai NYPost module.

    public void buyLaptopUsingAllMenu(WebDriver driver){
        HomePage homePage= PageFactory.initElements(driver,HomePage.class);
        AllsDropDownPage allsDropDownPage=PageFactory.initElements(driver,AllsDropDownPage.class);
        ComputerPage computerPage=PageFactory.initElements(driver,ComputerPage.class);
        LaptopsPage laptopsPage=PageFactory.initElements(driver,LaptopsPage.class);
        LaptopsResultPage laptopsResultPage=PageFactory.initElements(driver,LaptopsResultPage.class);
        SignInPage signInPage=PageFactory.initElements(driver,SignInPage.class);
        homePage.clickOnAllDropDownMenu();
        allsDropDownPage.goToComputerPage(driver);
        computerPage.goToLaptopPage(driver);
        laptopsPage.goToLaptopsResultPage(driver);
        laptopsResultPage.goToCheckOut();
        signInPage.enterEmailAddress("automationJune2020@gmail.com");
        signInPage.enterPassword("automation123");

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
            default:
                throw new InvalidArgumentException("Invalid features choice");
        }
    }
}
