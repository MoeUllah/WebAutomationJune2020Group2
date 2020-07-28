package amazonpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LaptopsPage {

    private final WebDriver driver;

    public LaptopsPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="img[alt='Essential laptop']")
    public WebElement essentialLaptopPicture;

    @FindBy(css="img[alt='Everyday laptop']")
    public WebElement everydayLaptopPicture;

    private void clickEverydayLaptopPicture(){
        everydayLaptopPicture.click();
    }

    public LaptopsResultPage goToLaptopsResultPage(WebDriver driver){
        clickEverydayLaptopPicture();
        return new LaptopsResultPage(driver);
    }

    public LaptopsResultPage clickEssentialLaptopPicture(){
        essentialLaptopPicture.click();
        return new LaptopsResultPage(driver);
    }

}
