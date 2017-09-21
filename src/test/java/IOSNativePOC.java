import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

/**
 * Created by ngoyal on 21/09/17.
 */
public class IOSNativePOC {


    DriverFactory driverFactory;
    public static AppiumDriver<MobileElement> driver=null;
    public WebDriverWait mywait;


    public IOSNativePOC(){
        driverFactory = new DriverFactory();
    }

    @BeforeMethod
    public void configureDriver() throws MalformedURLException {
        if(this.driver==null){
            this.driver = driverFactory.getDriver();
        }
    }

    @Test
    public void sampleTestCase() throws InterruptedException {
        System.out.println("Inside IOSNativeTestCase");
        driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"UICatalog\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[3]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Simple\"]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"OK\"]")).click();
        Thread.sleep(1000);
    }

    @AfterMethod
    public void killDriver(){
        driverFactory.tearDown();
    }

}
