package com.daxton.controller.actionmenu.entity;

import com.daxton.api.StringConversion;
import com.daxton.config.FileSearch;
import com.daxton.controller.main.ActionMenu;
import com.daxton.function.Manager;
import com.daxton.page.main.ActionMenuPage;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class Action_DCMessage {

    @FXML public TextField messageContent;
    @FXML public TextField channelContent;

    public void onMessageReleased(){
        changeContent();
    }

    public void onChannelReleasd(){
        changeContent();
    }

    public void changeContent(){

        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String output = "DCMessage[";

            String message = messageContent.getText();
            if(!message.isEmpty()){
                output += "Message="+message;
            }

            String channel = channelContent.getText();
            if(!channel.isEmpty()){
                if(!message.isEmpty()){
                    output += ";";
                }

                output += "Channel="+channel;
            }

            output += "]";

            ActionMenuPage.actionContent = output;

            ActionMenuPage.setSelectActionContent();
        }


    }

    //獲得初始內容
    public void getFirstContent(){
        ActionMenu actionMenu = (ActionMenu) Manager.controller_Map.get("ActionMenu");
        if(actionMenu != null){
            String input = actionMenu.selectActionContnet.getText();

            //messageText.setText(input);

            String message = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"m","message"});
            if(!message.isEmpty()){
                messageContent.setText(message);
            }
            String channel = StringConversion.getActionKey(FileSearch.setClassAction(input), new String[]{"c","channel"});
            if(!channel.isEmpty()){
                channelContent.setText(channel);
            }

        }
    }

}
