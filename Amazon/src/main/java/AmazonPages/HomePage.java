package AmazonPages;


import Base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class HomePage extends CommonAPI {

   public String typedValueInSearch="Comic Book";

    public HomePage(WebDriver driver){
        this.driver=driver;
   }

    @FindBy(css = "#searchDropdownBox option")
    public List<WebElement> allDropDownMenu;

    @FindBy(how= How.ID, using="twotabsearchtextbox")
    public WebElement searchTextBox;


    public SearchResultPage search() {
        typeOnElement(searchTextBox,typedValueInSearch);
        return PageFactory.initElements(driver,SearchResultPage.class);
    }

    public List getAllDropDownMenu(){
        return getListOfStringText(allDropDownMenu,"Drop-Down");
    }

    public void getNumberOfLinks(){
        numberOfLinks();
    }

    public String getTitle(){
        return driver.getTitle();
    }

}

































//    public HomePage(WebDriver driver){
//       PageFactory.initElements(driver,this);
//    }



//WebDriver driver;
//    public List<WebElement> allDropDownMenu=driver.findElements(By.cssSelector("#searchDropdownBox option"));
//
//    public WebElement searchTextBox=driver.findElement(By.id("twotabsearchtextbox"));