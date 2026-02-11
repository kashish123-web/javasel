package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties props = new Properties();

    static {
        try {
            FileInputStream fis =
                    new FileInputStream("src/test/resources/config.properties");
            props.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
    
//    public static String get(String key) {
//
//        // 1️⃣ Pehle ENV variable se uthao (GitHub Actions)
//        String value = System.getenv(key.toUpperCase());
//
//        // 2️⃣ Agar ENV mein nahi mila, tab local config.properties se uthao
//        if (value == null || value.isEmpty()) {
//            value = props.getProperty(key);
//        }
//
//        return value;
//    }

}
