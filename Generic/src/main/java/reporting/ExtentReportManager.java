package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports extentReportGenerator()  {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportPath=System.getProperty("user.dir")+ "/Extent-Report/ExtentReport-"+timeStamp+".html";
        ExtentSparkReporter reporter=new ExtentSparkReporter(reportPath);
        try {
            reporter.loadXMLConfig(System.getProperty("user.dir")+ "/Extent-Report/extent-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        reporter.config().setDocumentTitle("Web Automation June2020"); // Tile of report
        reporter.config().setReportName("Web Automation Report"); // name of the report
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setEncoding("utf-8");

        extent=new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Host name","localhost");
        extent.setSystemInfo("Environemnt","QA");
        extent.setSystemInfo("user","Shakat");
        extent.setSystemInfo("Browser","Chrome");
        extent.setSystemInfo("OS","Windows 10");

        return extent;
    }
}
