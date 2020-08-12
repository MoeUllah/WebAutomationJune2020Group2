package retryanalyzer;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestCases implements IRetryAnalyzer {

    private int retryCount=0;
    private int maxRetryCount=2;

    public boolean retry(ITestResult result) {
        if(retryCount<maxRetryCount){

            String className= result.getTestClass().getRealClass().getSimpleName();
            String methodName=result.getMethod().getMethodName();
            System.out.println("Retrying method named " +methodName+ " in class "+className+ " again and the count is " + (retryCount+1));
            retryCount++;
            return true;
        }
        return false;
    }
}
