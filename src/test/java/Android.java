import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by ngoyal on 20/09/17.
 */
public class Android {

    File app;
    public static WebDriver driver;
    WebDriverWait mywait;



    @Test(enabled=true)
    public void setupAndroidNative(){
        try {
            app = new File("/src/main/resources/apps/cheapoair.apk");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"6.0");
            capabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "192.168.56.101:5555");
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AndroidDriver<MobileElement>(url, capabilities);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Test(enabled = false)
    public void setupAndroidWeb(){
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"6.0");
            capabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "192.168.56.101:5555");
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AndroidDriver<MobileElement>(url, capabilities);
            driver.get("https://www.forsalebyowner.com/homes-for-sale/");

            //driver.get("https://www.google.com");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            mywait = new WebDriverWait(driver,10);
            mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lst-ib")));
            driver.findElement(By.id("lst-ib")).sendKeys("fsbo");
            Thread.sleep(5000);
            driver.findElement(By.id("tsbb")).click();
            Thread.sleep(3000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


   @Test(enabled = false)
   public void setupIOSNative(){
       try{
           app = new File("./src/main/resources/apps/UICatalog.app");
           DesiredCapabilities capabilities = new DesiredCapabilities();
           capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.2");
           capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
           capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
           capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
           URL url = new URL("http://127.0.0.1:4723/wd/hub");
           driver = new AppiumDriver(url, capabilities);
           driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"UICatalog\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[3]")).click();
           driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Simple\"]")).click();
           driver.findElement(By.xpath("//XCUIElementTypeButton[@name=\"OK\"]")).click();
       }catch (Exception e){
           e.printStackTrace();
       }
   }

    @Test(enabled = false)
    public void setupIOSSafari() {
        try {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.2");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"Safari");

            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new IOSDriver<MobileElement>(url, capabilities);
            /*driver.get("https://www.forsalebyowner.com/homes-for-sale");
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            mywait = new WebDriverWait(driver,20);
            mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa fa-bars']")));
            driver.findElement(By.xpath("//i[@class='fa fa-bars']")).click();
            driver.findElement(By.xpath("//ul[@class='responsive-menu animated open']/li[2]/a")).click();
            driver.findElement(By.xpath("ul[@class='dropdown-menu']/li[2]/a")).click();*/
            driver.get("https://www.google.com");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            mywait = new WebDriverWait(driver,10);
            mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lst-ib")));
            driver.findElement(By.id("lst-ib")).sendKeys("for sale by owner");
            Thread.sleep(5000);
            driver.findElement(By.id("tsbb")).click();
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void kill(){
        driver.quit();
    }




}
