package com.daxton.page.main;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.controller.main.MobsMenu;
import com.daxton.function.Manager;

public class MobsMenuPage {

    //打開怪物編輯介面
    public static void display(){
        MobsMenu mobsMenu = FxmlLoader.display("/page/main/MobsMenu.fxml", Main.mainWindow);
        Manager.controller_Map.put("MobsMenu", mobsMenu);
        Main.mainMenuName = "MobsMenu";
    }

}
