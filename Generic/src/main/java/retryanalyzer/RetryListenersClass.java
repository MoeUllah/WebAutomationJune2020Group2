package retryanalyzer;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListenersClass implements IAnnotationTransformer {

    public void transform(ITestAnnotation testannotation, Class testClass, Constructor testConstructor, Method testMethod) {
        testannotation.setRetryAnalyzer(RetryFailedTestCases.class);
    }
}
