package amazonpages;

import Base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LaptopsResultPage {

    private final WebDriver driver;

    public LaptopsResultPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="input#add-to-cart-button")
    public static WebElement addToCartButton;

    @FindBy(css="#attachSiNoCoverage-announce")
    public static WebElement noThanksWarrantyButton;

    @FindBy(css="#attach-sidesheet-checkout-button > span > input")
    public static WebElement proceedToCheckout;

    @FindBy(css="#hlb-ptc-btn-native")
    public static WebElement proceedToCheckout2;

    private void clickAddToCart(){
        addToCartButton.click();
    }

    public void goToCheckOut(){
        clickAddToCart();
//        if(noThanksWarrantyButton.isDisplayed()){
//            CommonAPI.waitUntilClickAble(noThanksWarrantyButton);
//            noThanksWarrantyButton.click();
//            proceedToCheckout.click();
//        }
//        else
            proceedToCheckout2.click();

    }
}
