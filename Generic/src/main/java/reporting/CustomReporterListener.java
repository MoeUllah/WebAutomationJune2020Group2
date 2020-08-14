package reporting;

import base.CommonAPI;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.*;

public class CustomReporterListener implements IReporter {

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        for (ISuite ist : suites) {

            String suitName=ist.getName();
            Map<String, ISuiteResult> resultMap = ist.getResults();
            Set<String> key = resultMap.keySet();

            for (String k : key) {

                ITestContext cntx = resultMap.get(k).getTestContext();

                System.out.println("Test Suite Name- " + cntx.getSuite().getName()
                        + "\n Report Directory- " + cntx.getOutputDirectory()
                        + "\n Test Name- " + cntx.getName()
                        + "\n \n Start Date and Time of Execution- " + cntx.getStartDate()
                        + "\n End Date and Time of Execution- " + cntx.getEndDate()
                        + "\n \n Passed Tests for suite '"+suitName+"' is: "+cntx.getPassedTests().getAllResults().size()
                        + "\n Failed Tests for suite '"+suitName+"' is: "+cntx.getFailedTests().getAllResults().size()
                        + "\n Skipped Tests for suite '"+suitName+"' is: "+cntx.getSkippedTests().getAllResults().size());


                IResultMap failedTest = cntx.getFailedTests();

                Collection<ITestNGMethod> failedMethods = failedTest.getAllMethods();

                System.out.println("\n ------Failed Test Case-----");

                for (ITestNGMethod imd : failedMethods) {
                    System.out.println("Test Case Name- " + imd.getMethodName());
//                            + "\n Description- " + imd.getDescription()
//                            + "\n Priority- " + imd.getPriority()
//                            + "\n Date- " + new Date(imd.getDate()));
                }

                IResultMap passedTest = cntx.getPassedTests();

                Collection<ITestNGMethod> passedMethods = passedTest.getAllMethods();

                System.out.println("\n------Passed Test Case-----");

                for (ITestNGMethod imd1 : passedMethods) {
                    System.out.println("Test Case Name- " + imd1.getMethodName());
//                            + "\n Description- " + imd1.getDescription()
//                            + "\n Priority- " + imd1.getPriority()
//                            + "\n Date- " + new Date(imd1.getDate()));
                }
            }
        }

    }
}
