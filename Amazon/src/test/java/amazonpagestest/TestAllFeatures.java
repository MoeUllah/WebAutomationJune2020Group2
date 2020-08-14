package amazonpagestest;

import base.CommonAPI;
import features.AllFunctionality;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestAllFeatures extends CommonAPI {

    @Test
    public void allFeatures() throws InterruptedException, IOException {
        AllFunctionality functionality = PageFactory.initElements(driver, AllFunctionality.class);
        functionality.runAllTheFeatureTest(driver);
    }
    @Test
    public void pass(){
        Assert.assertTrue(true);
    }

    @Test
    public void fail(){
        Assert.assertTrue(false);
    }
}




