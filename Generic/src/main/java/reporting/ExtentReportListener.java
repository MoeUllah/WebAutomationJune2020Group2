package reporting;

import base.CommonAPI;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.Arrays;


public class ExtentReportListener extends CommonAPI implements ITestListener {

    ExtentReports extent =ExtentReportManager.extentReportGenerator();
    ExtentTest test;
    private static ThreadLocal<ExtentTest> extentTest= new ThreadLocal<>();
    private ITestResult result;

    public void onTestStart(ITestResult result) {
        this.result=result;
        String className= result.getTestClass().getRealClass().getSimpleName();
        String methodName=result.getMethod().getMethodName();
        test=extent.createTest(className + ": " + convertToString(methodName));
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        String className= result.getTestClass().getRealClass().getSimpleName();
        String methodName=result.getMethod().getMethodName();
        String logText="<b>"+className + ": " + convertToString(methodName.toLowerCase())+" was passed."+"</b>";
        Markup markup=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().pass(markup);
    }

    public void onTestFailure(ITestResult result) {

        String className= result.getTestClass().getRealClass().getSimpleName();
        String methodName=result.getMethod().getMethodName();
        String screenshotPath=captureScreenshot(methodName,driver);
        String exceptionMessage= result.getThrowable().toString() +", "+ Arrays.toString(result.getThrowable().getStackTrace());

        extentTest.get().fail("<details>" + "<summary>" + "<b>" + "<mark1>" + className + ": " + convertToString(methodName.toLowerCase()) + " has failed" + "<br>" +
                        "Exception/Error Occured: Click to see" + "</mark1>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") +
                        "</details>" + " \n" + "<br>", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPath).build());


//        Object testObject= result.getInstance();
//        Class testClass=result.getTestClass().getRealClass();
//        WebDriver driver = null;
//        try {
//            driver=(WebDriver)testClass.getDeclaredField("driver").get(testObject);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void onTestSkipped(ITestResult result) {
        String className= result.getTestClass().getRealClass().getSimpleName();
        String methodName=result.getMethod().getMethodName();
        String skipCause=result.getSkipCausedBy().toString();
        String exceptionMessage= result.getThrowable().toString() +", "+ Arrays.toString(result.getThrowable().getStackTrace());
        String logText="<details>" + "<summary>" + "<b>" +className + ": " + convertToString(methodName.toLowerCase())+" was skipped"+"<br>"
                + "</b >" + "</summary>" +exceptionMessage.replaceAll(",", "<br>")+"</details>"+"<br>"+skipCause+ "\n";
        Markup markup=MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
        extentTest.get().skip(markup);

;    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onTestFailedWithTimeout(ITestResult result) {

    }

    public void onStart(ITestContext context) {

    }

    public void onFinish(ITestContext context) {
        if(extent!=null){
        extent.flush();
        }
    }

    public static void log(final String message){
        Reporter.log(message,true);
        extentTest.get().log(Status.INFO.INFO, message + "<br>");

    }
    public static void log(final StringUtils message){
        Reporter.log(message + "<br>",true);
        extentTest.get().log(Status.INFO.INFO, message + "<br>");
    }
    public static void log(final String message, WebDriver driver){
        Reporter.log(message,true);
        extentTest.get().log(Status.INFO.INFO, message + "<br>");
    }

}

//        extentTest.get().pass(String.valueOf(result.getMethod().getSuccessPercentage()));
//        extentTest.get().info(String.valueOf(CommonAPI.getTime(result.getStartMillis())));
//        extentTest.get().info(String.valueOf(CommonAPI.getTime(result.getEndMillis())));
//        extentTest.get().getExtent().addTestRunnerOutput(String.valueOf(result.getMethod().getSuccessPercentage()));

//TestLogger.log(getClass().getSimpleName() + ": " + CommonAPI.convertToString(new Object(){}.getClass().getEnclosingMethod().getName()));