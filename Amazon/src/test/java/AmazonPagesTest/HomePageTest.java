package AmazonPagesTest;


import AmazonPages.HomePage;
import AmazonPages.SearchResultPage;
import Base.CommonAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;


public class HomePageTest extends CommonAPI{
    HomePage homePage;

    @BeforeMethod
    public void initializePageObjects(){

        homePage=PageFactory.initElements(driver,HomePage.class);
    }

    @Test
    public void getAllDropDownMenuTest() {
        List dropDownMenu=homePage.getAllDropDownMenu();
        String lastDropDownText = (String) dropDownMenu.get(dropDownMenu.size() - 1);
        System.out.println("The last Drop-Down text is: " + lastDropDownText);
        String expectedText = "Whole Foods Market";
        Assert.assertEquals(expectedText, lastDropDownText);
    }

    @Test
    public void getTitle() {
        String expectedTitle = "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more";
        String actualTitle=homePage.getTitle();
        System.out.println(actualTitle);
        Assert.assertEquals(expectedTitle, actualTitle);

    }

    @Test
    public void typeSearchBox()  {
        SearchResultPage searchResultPage=homePage.search();
        String innerHTML = searchResultPage.forText.getAttribute("innerHTML");
        String expectedInnerHTML = "\"" + homePage.typedValueInSearch + "\"";
        Assert.assertEquals(expectedInnerHTML, innerHTML);
    }

    @Test
    public void totalLinks() {
        homePage.getNumberOfLinks();
//        int expectedAmount=342;   Amount of links changes, dynamic.
//        Assert.assertEquals(expectedAmount,linksAmount);
//        return expectedAmount;
        //

    }
}























// homePage= PageFactory.initElements(driver,HomePage.class);


//    @BeforeMethod
//    public void initializePageObjects() {
//        homePage = new HomePage(driver);
//    }