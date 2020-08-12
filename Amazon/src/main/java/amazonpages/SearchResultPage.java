package amazonpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {

    private final WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="div[cel_widget_id='MultiCardCreativeDesktop']>div:nth-of-type(2)>div:nth-of-type(1)>a")
    public static WebElement firstPictureSponsor;

    @FindBy(css=".s-main-slot>div:nth-of-type(2)>div>span>div>div>span>a>div")
    public static WebElement firstPictureNoSponsor;

    @FindBy(css="#productTitle")
    public static WebElement productTitle;

    @FindBy(css="#acrPopover>span:first-child>a>i:first-child>span")
    public static WebElement productStarRating;

    public String getTitleSearchPage(){
        return driver.getTitle();
    }
    public void clickFirstPicture(){
        try {
            firstPictureSponsor.click();
        }catch(Exception ex) {
            firstPictureNoSponsor.click();
        }
    }

    public void getProductTitle(){
        System.out.println(productTitle.getText());
    }

    public String getProductRating(){
        String rating=productStarRating.getAttribute("innerHTML");
        System.out.println(rating);
        return rating;
    }

}
