package base;

import org.testng.ITestResult;
import reporting.CustomHtmlReport;
import reporting.ExtentReportListener;
import utilities.TimeOutSettings;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CommonAPI {
    public String browserStackUserName = "";
    public String browserStackAccessKey = "";
    public String sauceLabsUserName = "";
    public String sauceLabsAccessKey = "";
    public static WebDriver driver = null;
    public static String urlHome;
    public int indexSI=0;

    @Parameters({"useCloudEnv","cloudEnvName","url","os","os_version","browserName","browserVersion"})
    @BeforeMethod
    public void startBrowser(String useCloudEnv,String cloudEnvName, String url,String os, String os_version, String browserName,
                             String browserVersion) throws IOException {

        if (useCloudEnv.equalsIgnoreCase("true")) {
            getCloudDriver(cloudEnvName,browserStackUserName,browserStackAccessKey,os,os_version,browserName,browserVersion);
        } else if (useCloudEnv.equalsIgnoreCase("false")){
            getLocalDriver(os,browserName);
        }

        driver.manage().timeouts().pageLoadTimeout(TimeOutSettings.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TimeOutSettings.IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.navigate().to(url);
        driver.manage().window().maximize();
        urlHome=driver.getCurrentUrl();
    }

    public WebDriver getLocalDriver(String os, String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            if(os.equalsIgnoreCase("OS X")){
                System.setProperty("webdriver.chrome.driver", "../Generic/driver/chromedriver");
                driver = new ChromeDriver();
            }else if(os.equalsIgnoreCase("Windows")){
                System.setProperty("webdriver.chrome.driver", "../Generic/driver/chromedriver.exe");
                driver = new ChromeDriver();
            }
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            if(os.equalsIgnoreCase("OS X")) {
                System.setProperty("webdriver.gecko.driver", "../Generic/driver/geckodriver");
                driver = new FirefoxDriver();
            } else if (os.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.gecko.driver", "../Generic/driver/geckodriver.exe");
                driver = new FirefoxDriver();
            }
        }
        else if (browserName.equalsIgnoreCase("edge")) {
            if (os.equalsIgnoreCase("OS X")) {
                System.setProperty("webdriver.edge.driver", "../Generic/driver/msedgedriver");
                driver = new EdgeDriver();
            } else if (os.equalsIgnoreCase("Windows")) {
                System.setProperty("webdriver.edge.driver", "../Generic/driver/MicrosoftWebDriver.exe");
                driver = new EdgeDriver();
            }
        }
        else {
            System.out.println("Sorry we don't support that browser.");
        }
        return driver;
    }

    public WebDriver getCloudDriver(String envName, String envUserName, String envAccessKey, String os,
                                    String os_version, String browserName, String browserVersion)throws IOException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browser", browserName);
        cap.setCapability("browserVersion", browserVersion);
        cap.setCapability("os", os);
        cap.setCapability("os_version", os_version);
        if(envName.equalsIgnoreCase("saucelabs")){
            driver = new RemoteWebDriver(new URL("http://"+envUserName+":"+envAccessKey+
                    "@ondemand.saucelabs.com:80/wd/hub"), cap);
        }else if(envName.equalsIgnoreCase("browserstack")) {
            driver = new RemoteWebDriver(new URL("http://" + envUserName + ":" + envAccessKey +
                    "@hub-cloud.browserstack.com/wd/hub"), cap);
        }

        return driver;
    }


    @AfterMethod
    public void closeBrowser(ITestResult result) throws IOException {
        CustomHtmlReport.createCustomHtmlReport(result);
        driver.quit();
    }

    public static void clickOnElement(WebElement element) {
        element.click();
    }


    public static void typeOnElement(WebElement element,String valueToType) {
        element.sendKeys(valueToType, Keys.ENTER);
    }

    public static void clickByAttributeValue(List<WebElement> elementList, String attribute, String myAttributeValue) {

        for (WebElement element : elementList) {
            String value = element.getAttribute(attribute);
            if(value.contains(myAttributeValue)) {
                    element.click();
                    break;
            }
        }
    }

    public static List getListOfStringText(List<WebElement> elementList, String nameOfList) {
        List elementTextList = new ArrayList();
        for (WebElement element : elementList) {
            String elementText = element.getText();
            System.out.println(elementText);
            System.out.println();
            elementTextList.add(elementText);
        }
        return elementTextList;
    }

    public static void selectAValue(WebElement element,String mySelectionValue) {
        Select select = new Select(element);
        List<WebElement> selectList = select.getOptions();
        for (WebElement selectElement : selectList) {
            String selectValue = selectElement.getText();
                if(selectValue.equalsIgnoreCase(mySelectionValue)){
                    selectElement.click();
                    break;
                }
        }
    }

    public static void selectAValueGeneric(WebElement element,String mySelectionValue) {
        Select select = new Select(element);
        List<WebElement> selectList = select.getOptions();
        for (WebElement selectElement : selectList) {
            String selectValue = selectElement.getText().trim();
            if(selectValue.equalsIgnoreCase(mySelectionValue)){
                selectElement.click();
                break;
            }
        }
    }

    public static List ascendingListOfText(List<WebElement> elementList) {
        List actualList = new ArrayList();
        for (WebElement element : elementList) {
            String text = element.getText().trim();
            actualList.add(text);
        }
        List temp = new ArrayList();
        temp.addAll(actualList);
        Collections.sort(temp);
        for(Object text:temp){
            System.out.println(text);
        }
        return temp;
    }

    public static List descendingListOfText(List<WebElement> elementList) {
        List actualList = new ArrayList();
        for (WebElement element : elementList) {
            String text = element.getText().trim();
            actualList.add(text);
        }
        List temp = new ArrayList();
        temp.addAll(actualList);
        Collections.sort(temp, Collections.reverseOrder());
        for(Object text:temp){
            System.out.println(text);
        }
        return temp;
    }

    public static String acceptAlert() {
        Alert alert = driver.switchTo().alert();
        String actualAlertText = alert.getText();
        alert.accept();
        return actualAlertText;
    }

    public static void frameSwitch(WebElement element) {
        driver.switchTo().frame(element);

    }

    public static void numberOfLinks() {
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        int linkCount = allLinks.size();
        System.out.println("Total amount of links are " + linkCount);
    }

    public static void closePopupWindows() {
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        int windowsCount = allWindows.size();
        System.out.println("Total amount of windows are " + windowsCount + ".");
        for (String childWindow : allWindows) {
            if (!parentWindow.equalsIgnoreCase(childWindow)) {
                driver.switchTo().window(childWindow);
                System.out.println(driver.getTitle());
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }

    public static String switchWindowGetTitle(){
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        String childWindowURL="";
        int windowsCount = allWindows.size();
        System.out.println("Total amount of windows are " + windowsCount + ".");
        for (String childWindow : allWindows) {
            if (!parentWindow.equalsIgnoreCase(childWindow)) {
                driver.switchTo().window(childWindow);
                childWindowURL=driver.getCurrentUrl();
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        return childWindowURL;
    }

    public static void clearInputField(WebElement element){
        element.clear();
    }

    public static void navigateBack(){
        driver.navigate().back();
    }

    //Synchronization
    public static void waitUntilClickAble(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitUntilVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitUntilSelectable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));
    }

    public static void waitUntilClickAble(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitUntilVisible(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public static void waitUntilSelectable(WebElement locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        boolean element = wait.until(ExpectedConditions.elementToBeSelected(locator));
    }

    public static void clickWithJS(WebElement element){
        JavascriptExecutor executor=(JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();",element);
    }

    public static void switchToTabsOfDiffLinks(List<WebElement> allLinks) {
        for (WebElement newLink : allLinks) {
            String openTabs = Keys.chord(Keys.CONTROL, Keys.ENTER);
            newLink.sendKeys(openTabs);;
        }
        closePopupWindows();
    }

    public static boolean isElementSelected(WebElement element){
       return element.isSelected();
    }

    public static boolean isElementEnabled(WebElement element){
        return element.isEnabled();
    }

    public static boolean isElementDisplayed(WebElement element){
        return element.isDisplayed();
    }

    public static String convertToString(String st) {
        String splitString = "";
        splitString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(st), ' ');
        return splitString;
    }

    public static String captureScreenshot(String screenshotName, WebDriver driver)  {
        DateFormat df = new SimpleDateFormat("-yyyy.MM.dd.HH.mm.ss");
        Date date = new Date();
        df.format(date);

        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        byte[] fileContent = new byte[0];
//        try {
//            fileContent = FileUtils.readFileToByteArray(source);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        String Base64StringofScreenshot = "data:image/png;base64,"+Base64.getEncoder().encodeToString(fileContent);

        String destPath=System.getProperty("user.dir") + "\\screenshots\\" + screenshotName + df.format(date) + ".png";
        try {
            FileUtils.copyFile(source, new File(destPath));
            System.out.println("Screenshot captured");
            return  destPath;
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }
        return destPath;
    }

    public static Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}





































//    public static List<WebElement> selectList(WebElement element) {
//        Select select = new Select(element);
//        List<WebElement> selectList = select.getOptions();
//        return selectList;
//    }

//        Actions act = new Actions(driver);

//act.moveToElement(newLink).sendKeys(openTabs).build().perform();