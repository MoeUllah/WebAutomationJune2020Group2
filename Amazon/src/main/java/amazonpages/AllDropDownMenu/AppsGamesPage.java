package amazonpages.AllDropDownMenu;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AppsGamesPage {

    private final WebDriver driver;

    public AppsGamesPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
