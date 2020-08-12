package amazonpages.AllDropDownMenu;

import base.CommonAPI;
import amazonpages.LaptopsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ComputerPage {

    private final WebDriver driver;

    public ComputerPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//img[@alt='Laptops']/parent::div/div/a")
    public static WebElement laptopPicture;

    private void clickLaptopPicture(){
        CommonAPI.waitUntilClickAble(laptopPicture);
        laptopPicture.click();
    }

    public LaptopsPage goToLaptopPage(WebDriver driver){
        clickLaptopPicture();
        return new LaptopsPage(driver);

    }
}
