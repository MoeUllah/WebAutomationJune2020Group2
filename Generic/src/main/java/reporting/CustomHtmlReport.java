package reporting;

import org.testng.ITestResult;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class CustomHtmlReport {

    public static int indexSI=0;

    public static void createCustomHtmlReport(ITestResult result) throws IOException {
        indexSI++;
        String suitName=result.getTestContext().getCurrentXmlTest().getSuite().getName();

        if(result.getStatus()==ITestResult.FAILURE){
            updateResult(result.getTestClass().getRealClass().getSimpleName(),indexSI,result.getMethod().getMethodName().toString(),"Fail",suitName);
        }
        else if(result.getStatus()==ITestResult.SUCCESS){
            updateResult(result.getTestClass().getRealClass().getSimpleName(),indexSI,result.getMethod().getMethodName().toString(),"Pass",suitName);
        }
        else if (result.getStatus()==ITestResult.SKIP){
            updateResult(result.getTestClass().getRealClass().getSimpleName(),indexSI,result.getMethod().getMethodName().toString(),"Skipped",suitName);
        }
        else{
            updateResult(result.getTestClass().getRealClass().getSimpleName(),indexSI,result.getMethod().getMethodName().toString(),"Other",suitName);
        }
    }


    public static void updateResult(String className,int indexSI, String methodName,String response,String suitName) throws IOException {
        String startDateTime=new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss").format(new GregorianCalendar().getTime());
//        System.out.println("startDateTime---"+startDateTime);

        String resultFile=System.getProperty("user.dir") + "\\customreport\\TestReport.html";

        File file=new File(resultFile);
//        System.out.println(file.exists());

        if(!file.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true))) {
                bw.write("<html>" + "\n");
                bw.write("<head><title>" + "Test execution report" + "</title>" + "\n");
                bw.write("</head>" + "\n");
                bw.write("<body>");
                bw.write("<font face='Tahoma'size='2'>" + "\n");
                bw.write("<u><h1 align='center'>" + "Test execution report" + "</h1></u>" + "\n");
            }
        }
        try(BufferedWriter bw1=new BufferedWriter(new FileWriter(file,true))){
            if(indexSI==1){
                bw1.write("<table align='center' border='0' width='70%' height='10'>");
                bw1.write("<tr><td width='70%' </td></tr>");
                bw1.write("<table align='center' border='1' width='70%' height='47'>");
                bw1.write("<tr>");
                bw1.write("<td colspan='1' allign='center'><b><font color='#000000' face='Tahoma' size='2'>ScriptName :&nbsp;&nbsp;&nbsp;</font><font color='#0000FF' face='Tahoma' size='2'>"+suitName+"</font></b></td>");
                bw1.write("<td colspan='2' align='left'><b><font color=#000000 face='Tahoma' size='2'>Start Time :&nbsp;</font><font color='#0000FF' face='Tahoma' size='2'>"+startDateTime+"</font></b></td>");
                bw1.write("</tr>");
                bw1.write("</tr>");
                bw1.write("<td bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>S.No</font></b></td>");
                bw1.write("<td bgcolor='#CCCCFF' align='left'><b><font color='#000000' face='Tahoma' size='2'>Class Name</font></b></td>");
                bw1.write("<td bgcolor='#CCCCFF' align='left'><b><font color='#000000' face='Tahoma' size='2'>Method Name</font></b></td>");
                bw1.write("<td bgcolor='#CCCCFF' align='center'><b><font color='#000000' face='Tahoma' size='2'>Response</font></b></td>");
                bw1.write("</tr>");
            }

            bw1.write("<tr>"+"\n");
            bw1.write("<td bgcolor='#FFFFDC' align='Center'><font color='#000000' face='Tahoma' size='2'>"+indexSI+"</font></td>"+"\n");
            bw1.write("<td bgcolor='#FFFFDC' vaalign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>"+className+"</font></td>");
            bw1.write("<td bgcolor='#FFFFDC' vaalign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>"+methodName+"</font></td>");
            bw1.write("<td bgcolor='#FFFFDC' vaalign='middle' align='left'><b><font color='#000000' face='Tahoma' size='2'>"+response+"</font></td>");
            bw1.write("</tr>"+"\n");
            bw1.write("</body>"+"\n");
            bw1.write("</html>");
        }

    }



}
