package com.daxton.controller.main;

import com.daxton.Main;
import com.daxton.api.StringControl;
import com.daxton.api.StringConversion;
import com.daxton.config.FileConfig;
import com.daxton.config.FileControl;
import com.daxton.config.FileSearch;
import com.daxton.function.Manager;
import com.daxton.page.*;
import com.daxton.page.main.*;
import com.daxton.page.topmenu.SettingsPage;
import com.daxton.terminal.CmdMain;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CharacterMenu {

    @FXML public ListView<String> characterFileList;
    @FXML public TextField characterFile;
    @FXML public ListView<String> characterSelectList;
    @FXML public TextField characterSelect;
    @FXML public TextField characterContent;
    @FXML public ListView<String> characterContentList;

    @FXML ChoiceBox<String> targetChoice;
    @FXML CheckBox placeholderAPIEnable;
    @FXML TextField placeholderTypeFind;
    @FXML ListView<String> placeholderTypeList;
    @FXML TextField placeholderFind;
    @FXML ListView<String> placeholderList;
    @FXML TextField placeholderContent;
    @FXML Text description;

    @FXML ChoiceBox<String> characterType;
    @FXML ChoiceBox<String> characterFunction;

    @FXML
    void initialize() {
        FileSearch.getTypeFileKey("Character/").forEach(s -> characterFileList.getItems().add(s.replace("Character/","")));

        targetChoice.getItems().add("target");
        targetChoice.getItems().add("self");
        targetChoice.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            targetChoice.getSelectionModel().select(targetChoice.getItems().get(newValue.intValue()));
            joinContent();
        });
        if(FileConfig.languageConfig != null && FileConfig.languageConfig.getConfigurationSection("Placeholders") != null){
            FileConfig.languageConfig.getConfigurationSection("Placeholders").getKeys(false).forEach(s -> placeholderTypeList.getItems().add(s));
        }

        if(FileConfig.characterConfig != null && FileConfig.characterConfig.getConfigurationSection("CustomCharacter") != null){
            FileConfig.characterConfig.getConfigurationSection("CustomCharacter").getKeys(false).forEach(s -> characterType.getItems().add(s));
        }

        characterType.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            characterType.getSelectionModel().select(characterType.getItems().get(newValue.intValue()));
            functionSet();
            joinContent();
        });
        characterFunction.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            characterFunction.getSelectionModel().select(characterFunction.getItems().get(newValue.intValue()));
        });
    }
    //改變字元類型(用內建還是PlaceholdersAPI)
    public void changePlaceholdersType(){
        boolean b = placeholderAPIEnable.isSelected();
        if(b){
            targetChoice.getItems().clear();

            placeholderTypeList.getItems().clear();
            if(FileConfig.placeholderAPIConfig != null && FileConfig.placeholderAPIConfig.getConfigurationSection("") != null){
                FileConfig.placeholderAPIConfig.getConfigurationSection("").getKeys(false).forEach(s -> placeholderTypeList.getItems().add(s));
            }

            placeholderList.getItems().clear();

            placeholderContent.clear();
        }else {
            targetChoice.getItems().clear();
            targetChoice.getItems().add("target");
            targetChoice.getItems().add("self");

            placeholderTypeList.getItems().clear();
            if(FileConfig.languageConfig != null && FileConfig.languageConfig.getConfigurationSection("Placeholders") != null){
                FileConfig.languageConfig.getConfigurationSection("Placeholders").getKeys(false).forEach(s -> placeholderTypeList.getItems().add(s));
            }

            placeholderList.getItems().clear();

            placeholderContent.clear();
        }
    }

    //功能設定
    public void functionSet(){
        String characterTypeString = StringControl.getValue(characterType);
        if(characterTypeString.equals("Content")){
            characterFunction.getItems().clear();
        }
        if(characterTypeString.equals("Conver")){
            characterFunction.getItems().clear();
            if(FileConfig.characterConfig != null && FileConfig.characterConfig.getConfigurationSection("ConverFunction") != null){
                FileConfig.characterConfig.getConfigurationSection("ConverFunction").getKeys(false).forEach(s -> characterFunction.getItems().add(s));
            }
        }
        if(characterTypeString.equals("Math")){
            characterFunction.getItems().clear();
            if(FileConfig.characterConfig != null && FileConfig.characterConfig.getConfigurationSection("MathFunction") != null){
                FileConfig.characterConfig.getConfigurationSection("MathFunction").getKeys(false).forEach(s -> characterFunction.getItems().add(s));
            }
        }
    }

    //要加入的內容
    public void joinContent(){
        String targetString = StringControl.getValue(targetChoice);
        String placeString = StringControl.getValue(placeholderList);
        if(!targetString.isEmpty() && !placeString.isEmpty())
        placeholderContent.setText("<"+targetString+"_"+placeString+">");
    }
    //確定加入
    public void defineJoin(){
        if(!placeholderContent.getText().isEmpty()){
            characterContent.setText(characterContent.getText()+placeholderContent.getText());
        }
    }
    //清除內容
    public void clearContnt(){
        characterContent.clear();
    }

    //選擇變量
    public void choicePlaceholder(){
        String placeholderType = placeholderTypeList.getSelectionModel().getSelectedItem();
        String selectPlaceholder = placeholderList.getSelectionModel().getSelectedItem();
        boolean b = placeholderAPIEnable.isSelected();
        if(b){
            placeholderContent.setText(selectPlaceholder);
            if(FileConfig.placeholderAPIConfig != null){
                String dd = FileConfig.placeholderAPIConfig.getString(placeholderType+"."+selectPlaceholder);
                description.setText(dd);
            }
        }else {
            if(FileConfig.characterConfig != null){
                String dd = FileConfig.characterConfig.getString(placeholderType+"."+selectPlaceholder);
                description.setText(dd);
            }
            joinContent();
        }

    }

    //選擇變量類別
    public void choicePlaceholderTypeList(){
        boolean b = placeholderAPIEnable.isSelected();
        String placeholderType = placeholderTypeList.getSelectionModel().getSelectedItem();
        if(b){
            if(FileConfig.placeholderAPIConfig != null && FileConfig.placeholderAPIConfig.getConfigurationSection(placeholderType) != null){
                placeholderList.getItems().clear();
                FileConfig.placeholderAPIConfig.getConfigurationSection(placeholderType).getKeys(false).forEach(s -> placeholderList.getItems().add(s));
            }
        }else {

            if(FileConfig.characterConfig != null && FileConfig.characterConfig.getConfigurationSection(placeholderType) != null){
                placeholderList.getItems().clear();
                FileConfig.characterConfig.getConfigurationSection(placeholderType).getKeys(false).forEach(s -> placeholderList.getItems().add(s));
            }
        }
    }

    //**=============================================================================**/

    //當選擇字符分類
    public void onChoiceFileList(){
        String fileName = "Character/"+characterFileList.getSelectionModel().getSelectedItem();
        //設置字符設定檔
        CharacterMenuPage.setCharaterConfig(fileName);
        characterFile.setText(characterFileList.getSelectionModel().getSelectedItem());
        if(Manager.file_Config_Map.get(fileName) != null){
            FileConfiguration charConfig = Manager.file_Config_Map.get(fileName);
            if(charConfig.getConfigurationSection("") != null){
                characterSelectList.getItems().clear();
                charConfig.getConfigurationSection("").getKeys(false).forEach(s -> characterSelectList.getItems().add(s));
            }
        }
    }
    //新增檔案
    public void addFile(){
        if(!characterFile.getText().isEmpty()){
            characterFileList.getItems().add(characterFile.getText());
            File file = new File(Main.getOpenPath()+"/Character/"+characterFile.getText());
            String fileName = characterFile.getText();
            while (fileName.contains("/")){
                fileName = fileName.replace(fileName.substring(0, fileName.indexOf("/")+1),"");
            }
            if(!file.exists()){
                try {
                    if(file.createNewFile()){
                        FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
                        Manager.file_Name_Map.put("Character/"+characterFile.getText(), fileName);
                        Manager.file_Config_Map.put("Character/"+characterFile.getText(), fileConfiguration);
                    }
                }catch (IOException exception){
                    exception.printStackTrace();
                }
            }

        }

    }

    //移除檔案
    public void removeFile(){
        File file = new File(Main.getOpenPath()+"/Character/"+characterFileList.getSelectionModel().getSelectedItem());
        if(file.exists()){
            file.delete();
        }
        characterFileList.getItems().remove(characterFileList.getSelectionModel().getSelectedItem());
        characterSelectList.getItems().clear();
        characterContent.clear();
        characterContentList.getItems().clear();
    }
    //新增字符
    public void addCharacter(){

        if(!characterSelect.getText().isEmpty()){
            if(Manager.file_Config_Map.get("Character/"+characterFileList.getSelectionModel().getSelectedItem()) != null){
                FileConfiguration fileConfiguration = Manager.file_Config_Map.get("Character/"+characterFileList.getSelectionModel().getSelectedItem());
                List<String> charList = new ArrayList<>();
                charList.add("content[]");
                fileConfiguration.set(characterSelect.getText()+".message", charList);

            }
            characterSelectList.getItems().add(characterSelect.getText());
        }

    }
    //移除字符
    public void removeCharacter(){
        if(Manager.file_Config_Map.get("Character/"+characterFileList.getSelectionModel().getSelectedItem()) != null){
            FileConfiguration fileConfiguration = Manager.file_Config_Map.get("Character/"+characterFileList.getSelectionModel().getSelectedItem());

            fileConfiguration.set(characterSelectList.getSelectionModel().getSelectedItem(), null);
        }
        characterSelectList.getItems().remove(characterSelectList.getSelectionModel().getSelectedItem());
    }

    //新增字符內容
    public void addCharacterContent(){
        if(!StringControl.getValue(characterType).isEmpty()){

            if(!StringControl.getValue(characterType).isEmpty()){
                String output = StringControl.getValue(characterType)+"[";

                if(!StringControl.getValue(characterFunction).isEmpty()){
                    output += "Function="+StringControl.getValue(characterFunction);
                }

                if(!characterContent.getText().isEmpty()){
                    if(!StringControl.getValue(characterFunction).isEmpty()){
                        output += ";";
                    }
                    if(StringControl.getValue(characterType).equals("Content")){
                        output += characterContent.getText();
                    }else {
                        output += "Message="+characterContent.getText();
                    }
                }

                output += "]";
                characterContentList.getItems().add(output);

                //設置字符設定
                List<String> set = new ArrayList<>();
                set.addAll(characterContentList.getItems());
                CharacterMenuPage.setValue(set);

            }

        }

    }
    //編輯字符內容
    public void editCharacterContent(){
        if(!StringControl.getValue(characterType).isEmpty()){

            if(!StringControl.getValue(characterType).isEmpty()){
                String output = StringControl.getValue(characterType)+"[";

                if(!StringControl.getValue(characterFunction).isEmpty()){
                    output += "Function="+StringControl.getValue(characterFunction);
                }

                if(!characterContent.getText().isEmpty()){
                    if(!StringControl.getValue(characterFunction).isEmpty()){
                        output += ";";
                    }
                    if(StringControl.getValue(characterType).equals("Content")){
                        output += characterContent.getText();
                    }else {
                        output += "Message="+characterContent.getText();
                    }

                }

                output += "]";
                characterContentList.getItems().set(characterContentList.getSelectionModel().getSelectedIndex() ,output);
                //設置字符設定
                List<String> set = new ArrayList<>();
                set.addAll(characterContentList.getItems());
                CharacterMenuPage.setValue(set);

            }

        }
    }

    //移除字符內容
    public void removeCharacterContent(){
        characterContentList.getItems().remove(characterContentList.getSelectionModel().getSelectedItem());
        //設置字符設定
        List<String> set = new ArrayList<>();
        set.addAll(characterContentList.getItems());
        CharacterMenuPage.setValue(set);
    }


    //當選擇字符
    public void onChoiceSelectList(){
        String fileName = "Character/"+characterFileList.getSelectionModel().getSelectedItem();
        if(Manager.file_Config_Map.get(fileName) != null){
            String charName = characterSelectList.getSelectionModel().getSelectedItem();
            FileConfiguration charConfig = Manager.file_Config_Map.get(fileName);
            characterContentList.getItems().clear();
            charConfig.getStringList(charName+".message").forEach(s -> characterContentList.getItems().add(s));

            CharacterMenuPage.setPatch(charName+".message");
        }
    }

    //當選擇字符內容
    public void onChoiceSelectContentList(){
        String characterContentString = characterContentList.getSelectionModel().getSelectedItem();

        Map<String, String> getCharacterMap = FileSearch.getCharacterMap(characterContentString);

        String charType = StringConversion.getActionKey(getCharacterMap, new String[]{"charactertype"});
        String charFunction = StringConversion.getActionKey(getCharacterMap, new String[]{"Function","fc"});
        String charMessage = StringConversion.getActionKey(getCharacterMap, new String[]{"message","m"});
        if(StringConversion.headToUP(charType).equals("Content")){
            //字符功能內容
            if(getCharacterMap.get("mid") != null)
            characterContent.setText(getCharacterMap.get("mid"));
        }else {
            //字符功能內容
            characterContent.setText(charMessage);
            //選定字符功能選項
            CharacterMenuPage.setFunction(charFunction, characterFunction);
        }
        //選定字符方法
        StringControl.setValue(characterType, StringConversion.headToUP(charType));


    }

    //**=============================================================================**/

    //打開設定檔
    public void open() {
        if(TopMenu.openCDSetting()){

        }
    }

    //打開設定介面
    public void openSettings(){
        SettingsPage.display();
    }

    //存檔
    public void saveFile(){
        FileControl.save();
    }

    //關閉編輯器
    public void exit(){
        Main.timer.cancel();
        Main.mainWindow.close();
        CmdMain.forcedEndServer();
    }

    //打開伺服器介面
    public void openServerMenu(){ ServerMenuPage.display(); }
    //打開職業介面
    public void openClassMenu(){ ClassMenuPage.display(); }
    //打開技能編輯介面
    public void openSkillMenu(){ SkillMenuPage.display(); }
    //打開動作介面
    public void openActionMenu(){ ActionMenuPage.display(); }
    //打開字符介面
    public void opCharacterMenu(){ CharacterMenuPage.display(); }
    //打開GUI介面
    public void opGUIMenu(){ GUIMenuPage.display(); }
    //打開物品介面
    public void openItemsMenu(){ ItemsMenuPage.display(); }
    //打開材質包介面
    public void openTextureMenu(){ TextureMenuPage.display(); }
    //打開怪物介面
    public void openModsMenu(){ MobsMenuPage.display(); }

    //**=============================================================================**/

}
