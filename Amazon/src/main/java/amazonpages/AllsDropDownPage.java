package amazonpages;

import amazonpages.AllDropDownMenu.AppsGamesPage;
import amazonpages.AllDropDownMenu.ComputerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AllsDropDownPage {

    @FindBy(css="#searchDropdownBox > option:nth-child(26)")
    public static WebElement computerMenuWebElement;

    @FindBy(css="#searchDropdownBox > option:nth-child(9)")
    public static WebElement AppsGamesMenuWebElement;

   public ComputerPage goToComputerPage(WebDriver driver){
        computerMenuWebElement.click();
        HomePage.clickSubmit();
        return new ComputerPage(driver);
   }

   public AppsGamesPage goToAppsGamesPage(WebDriver driver){
       AppsGamesMenuWebElement.click();
       HomePage.clickSubmit();
       return new AppsGamesPage(driver);
   }


}
