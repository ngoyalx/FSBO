import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ngoyal on 21/09/17.
 */
public class DriverFactory {

    public static DesiredCapabilities capabilities=null;
    public static GlobalProperties globalProperties = null;
    public static PropertiesFileReader propertiesFileReader = null;
    public static AppiumDriver driver;
    public static URL url;
    public File androidNativeappPath;
    public File IOSNativeappPath;
    public String isRemoteExecution;


    public DriverFactory(){
        globalProperties = new GlobalProperties();
        propertiesFileReader = new PropertiesFileReader();
        loadHashMapWithGlobalProperties();
        capabilities = new DesiredCapabilities();
    }


    public AppiumDriver getDriver() throws MalformedURLException {
        setAppPath();
        if(GlobalProperties.getPropertyMap().get("AUT").equalsIgnoreCase("androidnative")){
            driver = new AndroidDriver(url,getAndroidNativeCapabilities(capabilities));
        }else if(GlobalProperties.getPropertyMap().get("AUT").equalsIgnoreCase("androidweb")){
            driver = new AndroidDriver(url,getAndroidWebCapabilities(capabilities));
        }else if(GlobalProperties.getPropertyMap().get("AUT").equalsIgnoreCase("iosnative")){
            driver = new IOSDriver(url,getIOSNativeCapabilities(capabilities));
        }else if(GlobalProperties.getPropertyMap().get("AUT").equalsIgnoreCase("iosweb")){
            driver = new IOSDriver(url,getIOSWebCapabilities(capabilities));
        }
        return driver;
    }


    private void setAppPath() throws MalformedURLException {
        this.isRemoteExecution = GlobalProperties.getPropertyMap().get("isRemoteExecution");
        if(this.isRemoteExecution.equalsIgnoreCase("true")){
            androidNativeappPath = new File(GlobalProperties.remoteAppDir + File.separator + GlobalProperties.getPropertyMap().get("androidAppName"));
            IOSNativeappPath = new File(GlobalProperties.remoteAppDir + File.separator + GlobalProperties.getPropertyMap().get("IOSAppName"));
            url = new URL(GlobalProperties.getPropertyMap().get("remoteUrl") + "/wd/hub");
        }else{
            androidNativeappPath = new File(GlobalProperties.localAppDir + File.separator + GlobalProperties.getPropertyMap().get("androidAppName"));
            IOSNativeappPath = new File(GlobalProperties.localAppDir + File.separator + GlobalProperties.getPropertyMap().get("IOSAppName"));
            url = new URL(GlobalProperties.getPropertyMap().get("localUrl") + "/wd/hub");
        }
    }



    private void loadHashMapWithGlobalProperties(){
        propertiesFileReader.loadHashMapWithConfigFile(GlobalProperties.getPropertyMap());
        propertiesFileReader.reloadMapWithSystemProperties(GlobalProperties.getPropertyMap());
    }


    private DesiredCapabilities getAndroidNativeCapabilities(DesiredCapabilities capabilities){
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalProperties.getPropertyMap().get("androidPlatformVersion"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.APP,androidNativeappPath.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Emulator");
        return capabilities;
    }

    private DesiredCapabilities getAndroidWebCapabilities(DesiredCapabilities capabilities){
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,GlobalProperties.getPropertyMap().get("androidPlatformVersion"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Emulator");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        return capabilities;
    }

    private DesiredCapabilities getIOSNativeCapabilities(DesiredCapabilities capabilities){
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalProperties.getPropertyMap().get("iosPlatformVersion"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
        capabilities.setCapability(MobileCapabilityType.APP, IOSNativeappPath.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, GlobalProperties.getPropertyMap().get("iosDeviceName"));
        //capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");
        return capabilities;
    }

    private DesiredCapabilities getIOSWebCapabilities(DesiredCapabilities capabilities){
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalProperties.getPropertyMap().get("iosPlatformVersion"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, GlobalProperties.getPropertyMap().get("iosDeviceName"));
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"Safari");
        return capabilities;
    }

    public void tearDown(){
        driver.quit();
    }


}
