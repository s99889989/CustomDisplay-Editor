package com.daxton.page.main;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.controller.main.TextureMenu;
import com.daxton.function.Manager;

public class TextureMenuPage {

    //打開材質包編輯介面
    public static void display(){
        TextureMenu textureMenu = FxmlLoader.display("/page/main/TextureMenu.fxml", Main.mainWindow);
        Manager.controller_Map.put("TextureMenu", textureMenu);
        Main.mainMenuName = "TextureMenu";


    }

}
