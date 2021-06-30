package com.daxton.page;

import com.daxton.config.FileControl;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class TopMenu {

    //打開CustomDisplay設定檔
    public static boolean openCDSetting() {
        try {
            Stage selectWindows = new Stage();
            DirectoryChooser dc = new DirectoryChooser();
            //File desktop = new File(System.getProperty("user.home") + File.separator + "Desktop");
            File desktop = new File(System.getProperty("user.dir"));
            dc.setInitialDirectory(desktop);
            File selectedFile = dc.showDialog(selectWindows);
            if(selectedFile != null){
                FileControl.loadCustomDisplaySettings(selectedFile);
                return true;
            }else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

    }

}
