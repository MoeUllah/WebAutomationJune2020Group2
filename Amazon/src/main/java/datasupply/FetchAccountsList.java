package datasupply;

import datasources.ConnectToExcelFile;
import datasources.ConnectToMongoDB;

import java.io.IOException;

public class FetchAccountsList {

    ConnectToExcelFile excelFile = new ConnectToExcelFile();

    public String[][] getDataFromExcelFile() throws IOException {
        String path = System.getProperty("user.dir")+"/data/Grocery-List.xls";
        String [][] data = excelFile.fileReader1(path,1);
        return data;
    }

    public void insertAccountsMongoDB() throws IOException {
        String [][] accountsList=getDataFromExcelFile();
        ConnectToMongoDB.connectToMongoDB();
        ConnectToMongoDB.insertIntoMongoDB1(accountsList,"Accounts");
    }

}
