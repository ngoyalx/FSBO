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
    public String executionType;


    public DriverFactory(){
        globalProperties = new GlobalProperties();
        propertiesFileReader = new PropertiesFileReader();
        loadHashMapWithGlobalProperties();
        capabilities = new DesiredCapabilities();
    }


    public AppiumDriver getDriver() throws MalformedURLException {
        setAppPath();
        if(this.executionType.equalsIgnoreCase("saucelabs")){
            if(GlobalProperties.getPropertyMap().get("AUT").equalsIgnoreCase("androidnative")){
                driver = new AndroidDriver(url,getAndroidNativeSauceLabCapabilities(capabilities));
            }else if(GlobalProperties.getPropertyMap().get("AUT").equalsIgnoreCase("androidweb")){
                driver = new AndroidDriver(url,getAndroidWebSauceLabCapabilities(capabilities));
            }else if(GlobalProperties.getPropertyMap().get("AUT").equalsIgnoreCase("iosnative")){
                driver = new IOSDriver(url,getIOSNativeSauceLabCapabilities(capabilities));
            }else if(GlobalProperties.getPropertyMap().get("AUT").equalsIgnoreCase("iosweb")){
                driver = new IOSDriver(url,getIOSWebSauceLabCapabilities(capabilities));
            }
        }else{
            if(GlobalProperties.getPropertyMap().get("AUT").equalsIgnoreCase("androidnative")){
                driver = new AndroidDriver(url,getAndroidNativeCapabilities(capabilities));
            }else if(GlobalProperties.getPropertyMap().get("AUT").equalsIgnoreCase("androidweb")){
                driver = new AndroidDriver(url,getAndroidWebCapabilities(capabilities));
            }else if(GlobalProperties.getPropertyMap().get("AUT").equalsIgnoreCase("iosnative")){
                driver = new IOSDriver(url,getIOSNativeCapabilities(capabilities));
            }else if(GlobalProperties.getPropertyMap().get("AUT").equalsIgnoreCase("iosweb")){
                driver = new IOSDriver(url,getIOSWebCapabilities(capabilities));
            }
        }
        return driver;
    }


    private void setAppPath() throws MalformedURLException {
        this.executionType = GlobalProperties.getPropertyMap().get("executionType");
        if(this.executionType.equalsIgnoreCase("remote")){
            androidNativeappPath = new File(GlobalProperties.remoteAppDir + File.separator + GlobalProperties.getPropertyMap().get("androidAppName"));
            IOSNativeappPath = new File(GlobalProperties.remoteAppDir + File.separator + GlobalProperties.getPropertyMap().get("IOSAppName"));
            url = new URL(GlobalProperties.getPropertyMap().get("remoteUrl") + "/wd/hub");
        }else if(this.executionType.equalsIgnoreCase("local")){
            androidNativeappPath = new File(GlobalProperties.localAppDir + File.separator + GlobalProperties.getPropertyMap().get("androidAppName"));
            IOSNativeappPath = new File(GlobalProperties.localAppDir + File.separator + GlobalProperties.getPropertyMap().get("IOSAppName"));
            url = new URL(GlobalProperties.getPropertyMap().get("localUrl") + "/wd/hub");
        }else if(this.executionType.equalsIgnoreCase("saucelabs")){
            url = new URL(GlobalProperties.getPropertyMap().get("sauceLabUrl") + "/wd/hub");
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
        return capabilities;
    }

    private DesiredCapabilities getIOSWebCapabilities(DesiredCapabilities capabilities){
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalProperties.getPropertyMap().get("iosPlatformVersion"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, GlobalProperties.getPropertyMap().get("iosDeviceName"));
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"Safari");
        return capabilities;
    }

    private DesiredCapabilities getAndroidNativeSauceLabCapabilities(DesiredCapabilities capabilities){
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalProperties.getPropertyMap().get("androidPlatformVersion"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.APP,"sauce-storage:ApiDemos-debug.apk");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        return capabilities;
    }

    private DesiredCapabilities getIOSNativeSauceLabCapabilities(DesiredCapabilities capabilities){
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalProperties.getPropertyMap().get("iosPlatformVersion"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ios");
        capabilities.setCapability(MobileCapabilityType.APP,"sauce-storage:UICatalogSauce.zip");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6 Simulator");
        return capabilities;
    }

    private DesiredCapabilities getAndroidWebSauceLabCapabilities(DesiredCapabilities capabilities){
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalProperties.getPropertyMap().get("androidPlatformVersion"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        return capabilities;
    }

    private DesiredCapabilities getIOSWebSauceLabCapabilities(DesiredCapabilities capabilities){
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, GlobalProperties.getPropertyMap().get("iosPlatformVersion"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "ios");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone 6 Simulator");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"Safari");
        return capabilities;
    }

    public void tearDown(){
        driver.quit();
    }


}
