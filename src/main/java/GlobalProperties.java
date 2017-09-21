import java.io.File;
import java.util.HashMap;

/**
 * Created by ngoyal on 21/09/17.
 */
public class GlobalProperties {
    public static ThreadLocal<String> FileSeperator;
    public static String ConfigFilePath;
    public static String LogFilePath;
    public static String ChromeDriverPathForWindows;
    public static String ChromeDriverPathForMac;
    public static String FirefoxDriverPathForWindows;
    public static String FirefoxDriverPathForMac;
    public static HashMap<String,String> propertyMap;
    public static File remoteAppDir;
    public static File localAppDir;



    public GlobalProperties() {

        FileSeperator = new ThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return System.getProperty("file.separator");
            }
        };

        ChromeDriverPathForWindows = System.getProperty("user.dir") + FileSeperator.get() + "src" + FileSeperator.get()
                + "main" + FileSeperator.get() + "resources" + FileSeperator.get() + "drivers" + FileSeperator.get()
                + "chromedriver.exe";

        ChromeDriverPathForMac = System.getProperty("user.dir") + FileSeperator.get() + "src" + FileSeperator.get()
                + "main" + FileSeperator.get() + "resources" + FileSeperator.get() + "drivers" + FileSeperator.get()
                + "chromedriver";

        FirefoxDriverPathForWindows = System.getProperty("user.dir") + FileSeperator.get() + "src" + FileSeperator.get()
                + "main" + FileSeperator.get() + "resources" + FileSeperator.get() + "drivers" + FileSeperator.get()
                + "geckodriver.exe";

        FirefoxDriverPathForMac = System.getProperty("user.dir") + FileSeperator.get() + "src" + FileSeperator.get()
                + "main" + FileSeperator.get() + "resources" + FileSeperator.get() + "drivers" + FileSeperator.get()
                + "geckodriver";

        ConfigFilePath = System.getProperty("user.dir") + FileSeperator.get() + "config.properties";

        LogFilePath = System.getProperty("user.dir") + FileSeperator.get() + "src" + FileSeperator.get()
                + "test" + FileSeperator.get() + "resources" + FileSeperator.get() + "log4j.properties";

        propertyMap = new HashMap<String, String>();

        remoteAppDir = new File(FileSeperator.get() + "Users" + FileSeperator.get() + "Shared" + FileSeperator.get() + "testApps" + FileSeperator.get());

        localAppDir = new File(System.getProperty("user.dir") + FileSeperator.get() + "src"
                + FileSeperator.get() + "main" + FileSeperator.get() + "resources" + FileSeperator.get()
                + "apps" + FileSeperator.get());

    }


    public static HashMap<String,String> getPropertyMap(){
        return propertyMap;
    }




}
