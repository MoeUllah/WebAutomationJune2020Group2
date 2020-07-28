package datasupply;

import datasources.ConnectToExcelFile;

import java.io.IOException;

public class FetchGroceryList {

    ConnectToExcelFile excelFile = new ConnectToExcelFile();
    public String[] getDataFromExcelFile() throws IOException {
        String path = System.getProperty("user.dir")+"/data/Grocery-List.xls";
        String [] data = excelFile.fileReader2(path,0);
        return data;
    }
}
