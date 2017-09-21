
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by ngoyal on 5/13/2017.
 */
public class PropertiesFileReader {

    Properties properties = new Properties();

    public void loadHashMapWithConfigFile(HashMap<String, String> propMap) {
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(GlobalProperties.ConfigFilePath);
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Java - 8
        // propMap.putAll(properties.entrySet().stream()
        // .collect(Collectors.toMap(e -> e.getKey().toString(), e ->
        // e.getValue().toString())));
        // Java - 7
        for (final Map.Entry<Object, Object> entry : properties.entrySet()) {
            propMap.put((String) entry.getKey(), (String) entry.getValue());
        }
    }


    /**
     * This method will read properties from command line and replace Map
     * already existing entries Command line argument will be given preference
     */
    public void reloadMapWithSystemProperties(HashMap<String, String> propMap) {
        try{
            Properties objSystemProperties = System.getProperties();
            for (final Map.Entry<Object, Object> entry : objSystemProperties.entrySet()) {
                propMap.put((String) entry.getKey(), (String) entry.getValue());
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
