package amazonpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TryPrimePage {

    private final WebDriver driver;

    public TryPrimePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
