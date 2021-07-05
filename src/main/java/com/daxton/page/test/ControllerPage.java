package com.daxton.page.test;

import com.daxton.Main;
import com.daxton.api.FxmlLoader;
import com.daxton.controller.test.Controller;
import com.daxton.function.Manager;

public class ControllerPage {

    //打開動作編輯介面
    public static void display(){
        Controller controller = FxmlLoader.display("/page/test/sample.fxml", Main.mainWindow);

    }

}
