package AmazonPages;

import Base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends CommonAPI {

    public SearchResultPage(WebDriver driver){
        this.driver=driver;
    }

    @FindBy(css="span.a-color-state.a-text-bold")
    public WebElement forText;
}
