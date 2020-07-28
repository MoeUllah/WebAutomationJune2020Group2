package amazonpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(className = "span.a-button-inner>span>span:first-child")
    public WebElement qtyArrowDown;

    @FindBy(css=".a-list-link>li:nth-of-type(6)>a")
    public WebElement qty5;

    @FindBy(css=".a-list-link>li:nth-of-type(1)>a")
    public WebElement qty0;

    @FindBy(css="input[data-action='delete']")
    public WebElement delete;

    public void clickQty5ArrowDown(){
        qty5.click();
    }

    public void clickQty0ArrowDown(){
        qty0.click();
    }

    public void clickQtyArrowDown(){
        qtyArrowDown.click();
    }

    public void deleteFromCart(){

    }
    public void modifyCart(){
        clickQtyArrowDown();
        clickQty5ArrowDown();
        clickQtyArrowDown();
        clickQty0ArrowDown();
    }
}
