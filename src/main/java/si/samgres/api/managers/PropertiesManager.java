package si.samgres.api.managers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    private static Properties prop;

    public static void initialize() {
        if (prop != null) { //ensure singleton
            return;
        }

        //initialize properties variable
        prop = new Properties();

        //create input stream
        InputStream inputStream = PropertiesManager.class.getClassLoader().getResourceAsStream("application.properties");
        if (inputStream == null) { //flag
            return;
        }

        //try loading
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        initialize(); //ensure init

        // try getting the property value
        String value = null;
        try {
            value = prop.getProperty(key);
        } catch (Exception e) { }

        return value;
    }
}
