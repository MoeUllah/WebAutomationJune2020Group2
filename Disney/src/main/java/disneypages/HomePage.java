package disneypages;

import Base.CommonAPI;
import datasources.ConnectToExcelFiles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class HomePage extends CommonAPI {

    @FindBy(xpath = "//iframe[contains(@id,'google_ads')]") public static WebElement promotionIframe;
    @FindBy(xpath = "//span[@id = 'goc-bar-left']/li[contains(@class, 'bar-dropdown')]") public static List<WebElement> allDropDownMenu;
    @FindBy(id ="goc-instant-search-input") public static WebElement searchBar;
    //*[@id="overlaybg"]/p/a

    @BeforeMethod
    public void closePromotionWindow() {
        WebElement frame = driver.findElement(By.xpath("//iframe[contains(@id,'google_ads')]"));
        driver.switchTo().frame(frame);

        WebElement closeOverlayButton = driver.findElement(By.xpath("//a[contains(@class, 'sprite close')]"));
        closeOverlayButton.click();
        driver.switchTo().defaultContent();
    }

    public void search(String typedValueInSearch) {
        searchBar.click();
        typeOnElement(searchBar,typedValueInSearch);
    }


    public List getAllDropDownMenu(){
        return getListOfStringText(allDropDownMenu,"Drop-Down");
    }

    public void getNumberOfLinks(){
        numberOfLinks();
    }

    public String getTitle(WebDriver driver){
        return driver.getTitle();
    }

    public void searchItemsWithExcelData(){
        List<String> items;
        try {
             items = ConnectToExcelFiles.getItems();
            for (String item : items
                 ) {
                System.out.println(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


