package features;


import datasupply.FetchTheSteps;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class AllFunctionality {

    //Need to use this class to implement all features, basically run all test cases from pages class under java package.
    //Look at Mafi bhai NYPost module.

    public void runAllTheFeatureTest(WebDriver driver) throws InterruptedException, IOException {
        FetchTheSteps fetchTheSteps = new FetchTheSteps();
        String [] featureSteps = fetchTheSteps.getDataFromExcelFile();
        for(int i=1; i<featureSteps.length; i++){
            select(featureSteps[i],driver);
        }
    }

    public void select(String featureName, WebDriver driver)throws InterruptedException,IOException{
        switch(featureName){
            //your case statements for your methods(steps from excel file) goes here
            default:
                throw new InvalidArgumentException("Invalid features choice");
        }
    }
}
