package com.daxton.page.main;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.controller.main.ItemsMenu;
import com.daxton.function.Manager;

public class ItemsMenuPage {

    //打開物品編輯介面
    public static void display(){
        ItemsMenu itemsMenu = FxmlLoader.display("/page/main/ItemsMenu.fxml", Main.mainWindow);
        Manager.controller_Map.put("ItemsMenu", itemsMenu);
        Main.mainMenuName = "ItemsMenu";
    }

}
