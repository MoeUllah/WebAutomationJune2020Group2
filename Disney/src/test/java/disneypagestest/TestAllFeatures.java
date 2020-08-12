package disneypagestest;

import base.CommonAPI;
import features.AllFunctionality;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestAllFeatures extends CommonAPI {

    @Test
    public void allFeatures() throws InterruptedException, IOException {
        AllFunctionality functionality = PageFactory.initElements(driver, AllFunctionality.class);
        functionality.runAllTheFeatureTest(driver);
    }
}




