package com.daxton.function;

import javafx.scene.control.TextArea;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

public class Manager {

    public static Map<String, FileConfiguration> file_Config_Map = new HashMap<>();

    public static Map<String, FileConfiguration> settings_Config_Map = new HashMap<>();

    public static Map<String, String> file_Name_Map = new HashMap<>();

    public static Map<String, Object> controller_Map = new HashMap<>();

    public static TextArea message_Map = new TextArea();

}
