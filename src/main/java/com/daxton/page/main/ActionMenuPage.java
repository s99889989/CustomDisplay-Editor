package com.daxton.page.main;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.controller.main.ServerMenu;
import com.daxton.function.Manager;


public class ActionMenuPage {

    //打開動作編輯介面
    public static void display(){
        ServerMenu serverMenu = FxmlLoader.display("/page/main/ActionMenu.fxml", Main.mainWindow);
        Manager.controller_Map.put("TerminalMenu", serverMenu);
        Main.mainMenuName = "ActionMenu";

    }

}
