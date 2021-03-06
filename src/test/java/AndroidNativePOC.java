import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Created by ngoyal on 21/09/17.
 */
public class AndroidNativePOC {

    DriverFactory driverFactory;
    public AppiumDriver driver=null;
    public WebDriverWait mywait;


    public AndroidNativePOC(){
        driverFactory = new DriverFactory();
    }


    @BeforeMethod
    public void configureDriver() throws MalformedURLException {
        if(this.driver==null){
            this.driver = driverFactory.getDriver();
        }
    }

    @Test
    public void sampleTestCase() throws MalformedURLException, InterruptedException {
       // this.driver = driverFactory.getDriver();
        driver.findElementByXPath("//*[@text='Views']").click();
        Thread.sleep(2000);
        driver.findElementByXPath("//*[@text='Buttons']").click();
        Thread.sleep(4000);
        driver.findElementByXPath("//*[@text='OFF']").click();
        Thread.sleep(2000);
        //driver.findElementById("com.example.android.apis:id/stop").click();
        //Thread.sleep(1000);

        System.out.println("Inside AndroidNativeTestCase");
    }

    @AfterMethod
    public void killDriver(){
        driverFactory.tearDown();
    }

}
