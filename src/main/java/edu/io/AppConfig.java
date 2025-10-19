package edu.io;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

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

    public <T> T get(String name, Class<T> secondClass) {
        Object value = mode.get(name);
        if (value == null) {
            return null;
        }
        if (!secondClass.isInstance(value)) {
            throw new ClassCastException("Błąd");
        }
        return secondClass.cast(value);
    }

    public void load(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("Nie ma takiego pliku");
        }


        try (FileInputStream fis = new FileInputStream(file)) {
            Yaml yaml = new Yaml();
            Map<String, Object> x = yaml.load(fis);
            if (x != null) {
                mode.clear();
                mode.putAll(x);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(String path) {
        Yaml yaml = new Yaml();
        try (FileWriter writer = new FileWriter(path)) {
            yaml.dump(mode, writer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
