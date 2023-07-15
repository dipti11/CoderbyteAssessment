package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonUtils {

    // Read properties file
    public String readProperties(String key) throws IOException {
        String value = null;
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream("src/main/resources/configs/env.properties");
            // Load the properties file
            properties.load(inputStream);
            // Get the value of the property
            value = properties.getProperty(key);
        } finally {  // Close the InputStream
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
}
