package amazonpages;

import Base.CommonAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;

public class SignInPage {

    private final WebDriver driver;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how= How.ID,using = "ap_email")
    public static WebElement emailInputBox;

    @FindBy(how=How.ID,using="ap_password")
    public static WebElement passwordInputBox;

    public void enterEmailAddress(String email){
        CommonAPI.typeOnElement(emailInputBox,email);
    }

    public void enterPassword(String password){
        CommonAPI.typeOnElement(passwordInputBox,password);
    }


}
