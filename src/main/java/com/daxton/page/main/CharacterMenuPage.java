package com.daxton.page.main;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.controller.main.ServerMenu;
import com.daxton.function.Manager;

public class CharacterMenuPage {

    //打開字符編輯介面
    public static void display(){
        ServerMenu serverMenu = FxmlLoader.display("/page/main/CharacterMenu.fxml", Main.mainWindow);
        Manager.controller_Map.put("TerminalMenu", serverMenu);

        Main.mainMenuName = "CharacterMenu";

    }

}
