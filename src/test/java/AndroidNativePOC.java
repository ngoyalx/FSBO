import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import jdk.nashorn.internal.objects.Global;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by ngoyal on 21/09/17.
 */
public class AndroidNativePOC {

    DriverFactory driverFactory;
    public AppiumDriver<MobileElement> driver=null;
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
    public void sampleTestCase() throws MalformedURLException {
       // this.driver = driverFactory.getDriver();

        System.out.println("Inside AndroidNativeTestCase");
    }

    @AfterMethod
    public void killDriver(){
        driverFactory.tearDown();
    }

}
