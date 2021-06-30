package com.daxton.page.main;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.controller.main.GUIMenu;
import com.daxton.function.Manager;

public class GUIMenuPage {

    //打開GUI編輯介面
    public static void display(){
        GUIMenu guiMenu = FxmlLoader.display("/page/main/GUIMenu.fxml", Main.mainWindow);
        Manager.controller_Map.put("GUIMenu", guiMenu);
        Main.mainMenuName = "GUIMenu";
    }

}
