package edu.io;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    private static AppConfig appConfig = new AppConfig();
    private Map<String, Object> mode = new HashMap<>();


    public static AppConfig getInstance() {
        return appConfig;
    }

    private AppConfig() {
    }

    public void set(String name, Object value) {
        mode.put(name, value);
    }

    public Object get(String name) {
        return mode.get(name);
    }
}
