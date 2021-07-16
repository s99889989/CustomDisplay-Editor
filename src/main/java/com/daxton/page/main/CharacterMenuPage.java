package com.daxton.page.main;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.api.StringControl;
import com.daxton.controller.main.CharacterMenu;
import com.daxton.function.Manager;
import javafx.scene.control.ChoiceBox;
import org.bukkit.configuration.file.FileConfiguration;

public class CharacterMenuPage {

    public static FileConfiguration charaterConfig;

    public static String patch;

    //設置字符設定檔
    public static void setCharaterConfig(String patch){
        if(Manager.file_Config_Map.get(patch) != null){
            charaterConfig = Manager.file_Config_Map.get(patch);
        }
    }
    //設置內容路徑
    public static void setPatch(String inputPatch){
        patch = inputPatch;
    }

    //設置字符設定
    public static void setValue(Object value){
        if(charaterConfig != null && patch != null){
            charaterConfig.set(patch, value);
        }
    }

    //打開字符編輯介面
    public static void display(){
        CharacterMenu characterMenu = FxmlLoader.display("/page/main/CharacterMenu.fxml", Main.mainWindow);
        Manager.controller_Map.put("CharacterMenu", characterMenu);

        Main.mainMenuName = "CharacterMenu";

    }
    //選定字符功能選項
    public static void setFunction(String charFunction, ChoiceBox<String> characterFunction){
        switch (charFunction.toLowerCase()){
            case "contain":
                StringControl.setValue(characterFunction, "Contain");
                break;
            case "containall":
                StringControl.setValue(characterFunction, "ContainAll");
                break;
            case "exsame":
                StringControl.setValue(characterFunction, "ExSame");
                break;
            case "converhead":
                StringControl.setValue(characterFunction, "ConverHead");
                break;
            case "converunits":
                StringControl.setValue(characterFunction, "ConverUnits");
                break;
            case "converdouble":
                StringControl.setValue(characterFunction, "ConverDouble");
                break;
            case "converaddrl":
                StringControl.setValue(characterFunction, "ConverAddRL");
                break;
            case "arithmetic":
            case "arith":
                StringControl.setValue(characterFunction, "Arithmetic");
                break;
            case "accumulate":
            case "acc":
                StringControl.setValue(characterFunction, "Accumulate");
                break;
            case "decimal":
            case "dec":
                StringControl.setValue(characterFunction, "Decimal");
                break;
            case "greater":
                StringControl.setValue(characterFunction, "Greater");
                break;
            case "less":
                StringControl.setValue(characterFunction, "Less");
                break;
            case "format":
                StringControl.setValue(characterFunction, "Format");
                break;
        }
    }

}
