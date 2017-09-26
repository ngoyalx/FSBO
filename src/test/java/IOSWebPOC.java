import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by ngoyal on 21/09/17.
 */
public class IOSWebPOC {

    DriverFactory driverFactory;
    public static AppiumDriver driver=null;
    public WebDriverWait mywait;


    public IOSWebPOC(){
        driverFactory = new DriverFactory();
    }

    @BeforeMethod
    public void configureDriver() throws MalformedURLException {
        if(this.driver==null){
            this.driver = driverFactory.getDriver();
        }
        mywait = new WebDriverWait(driver,30);
    }

    @Test
    public void sampleTestCase() throws InterruptedException {
        System.out.println("Inside IOSWebTestCase");
        driver.get("https://www.google.com");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lst-ib")));
        driver.findElement(By.id("lst-ib")).sendKeys("for sale by owner");
        Thread.sleep(5000);
        driver.findElement(By.id("tsbb")).click();
        Thread.sleep(3000);
    }

    @AfterMethod
    public void killDriver(){
        driverFactory.tearDown();
    }
}
