package reporting;

import base.CommonAPI;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class ExtentReportListener extends CommonAPI implements ITestListener {

    ExtentReports extent =ExtentReportManager.extentReportGenerator();
    ExtentTest test;
    private static ThreadLocal<ExtentTest> extentTest= new ThreadLocal<>();

    public void onTestStart(ITestResult result) {
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
        extentTest.get().log(Status.PASS,"The test was successful.");
    }

    public void onTestFailure(ITestResult result) {

        String className= result.getTestClass().getRealClass().getSimpleName();
        String methodName=result.getMethod().getMethodName();
        String exceptionMessage= Arrays.toString(result.getThrowable().getStackTrace());
        extentTest.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
                + "</font>" + "</b >" + "</summary>" +exceptionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
        String failureLogg="<b>"+className + ": " + convertToString(methodName.toLowerCase())+" has failed."+"</b>";
        Markup markup = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
        extentTest.get().log(Status.FAIL, markup);
//        extentTest.get().fail(result.getThrowable());
//        Object testObject= result.getInstance();
//        Class testClass=result.getTestClass().getRealClass();
//        WebDriver driver = null;
//        try {
//            driver=(WebDriver)testClass.getDeclaredField("driver").get(testObject);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        extentTest.get().addScreenCaptureFromPath(captureScreenshot(methodName,driver),className + ": " + convertToString(methodName));

    }

    public void onTestSkipped(ITestResult result) {
        String className= result.getTestClass().getRealClass().getSimpleName();
        String methodName=result.getMethod().getMethodName();
        String logText="<b>"+className + ": " + convertToString(methodName.toLowerCase())+" was skipped."+"</b>";
        Markup markup=MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
        extentTest.get().skip(markup);
        extentTest.get().log(Status.SKIP,"The test was skipped");
        extentTest.get().skip((Markup) result.getSkipCausedBy());
        extentTest.get().skip(result.getThrowable());
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
}
