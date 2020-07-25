package datasupply;

import datasources.ConnectToExcelFile;

import java.io.IOException;

public class FetchTheSteps {
    ConnectToExcelFile excelFile = new ConnectToExcelFile();
    public String[] getDataFromExcelFile() throws IOException {
        String path = System.getProperty("user.dir")+"/Amazon/data/Amazon-test-steps.xls";
        String [] data = excelFile.fileReader2(path,0);
        return data;
    }

}

//At the end you'll create steps in the excel file under data package of your module to run the test like Mafi bhai using
// only by typing into excel.