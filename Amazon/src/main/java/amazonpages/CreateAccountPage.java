package amazonpages;

import base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class CreateAccountPage {

    private final WebDriver driver;

    public CreateAccountPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(how= How.CSS,using = "#ap_customer_name")
    public static WebElement customerName;

    @FindBy(how= How.CSS,using = "#ap_email")
    public static WebElement customerEmail;

    @FindBy(css="#ap_password")
    public static WebElement customerPassword;

    @FindBy(css="#ap_password_check")
    public static WebElement passwordCheckAgain;

    @FindBy(className = "a-button-input")
    public static WebElement continueButton;

    public void enterName(String name){
        CommonAPI.waitUntilClickAble(customerName);
        CommonAPI.typeOnElement(customerName,name);
    }

    public void enterEmail(String email){
        CommonAPI.typeOnElement(customerEmail,email);
    }

    public void enterPassword(String password){
        CommonAPI.typeOnElement(customerPassword,password);
    }

    public void enterPasswordAgain(String password){
        CommonAPI.typeOnElement(passwordCheckAgain,password);
    }

    public void clickContinue(){
        continueButton.click();
    }
}
