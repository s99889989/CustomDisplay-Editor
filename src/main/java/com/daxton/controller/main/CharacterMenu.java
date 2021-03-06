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
    //??????????????????(???????????????PlaceholdersAPI)
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

    //????????????
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

    //??????????????????
    public void joinContent(){
        String targetString = StringControl.getValue(targetChoice);
        String placeString = StringControl.getValue(placeholderList);
        if(!targetString.isEmpty() && !placeString.isEmpty())
        placeholderContent.setText("<"+targetString+"_"+placeString+">");
    }
    //????????????
    public void defineJoin(){
        if(!placeholderContent.getText().isEmpty()){
            characterContent.setText(characterContent.getText()+placeholderContent.getText());
        }
    }
    //????????????
    public void clearContnt(){
        characterContent.clear();
    }

    //????????????
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

    //??????????????????
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

    //?????????????????????
    public void onChoiceFileList(){
        String fileName = "Character/"+characterFileList.getSelectionModel().getSelectedItem();
        //?????????????????????
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
    //????????????
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

    //????????????
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
    //????????????
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
    //????????????
    public void removeCharacter(){
        if(Manager.file_Config_Map.get("Character/"+characterFileList.getSelectionModel().getSelectedItem()) != null){
            FileConfiguration fileConfiguration = Manager.file_Config_Map.get("Character/"+characterFileList.getSelectionModel().getSelectedItem());

            fileConfiguration.set(characterSelectList.getSelectionModel().getSelectedItem(), null);
        }
        characterSelectList.getItems().remove(characterSelectList.getSelectionModel().getSelectedItem());
    }

    //??????????????????
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

                //??????????????????
                List<String> set = new ArrayList<>();
                set.addAll(characterContentList.getItems());
                CharacterMenuPage.setValue(set);

            }

        }

    }
    //??????????????????
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
                //??????????????????
                List<String> set = new ArrayList<>();
                set.addAll(characterContentList.getItems());
                CharacterMenuPage.setValue(set);

            }

        }
    }

    //??????????????????
    public void removeCharacterContent(){
        characterContentList.getItems().remove(characterContentList.getSelectionModel().getSelectedItem());
        //??????????????????
        List<String> set = new ArrayList<>();
        set.addAll(characterContentList.getItems());
        CharacterMenuPage.setValue(set);
    }


    //???????????????
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

    //?????????????????????
    public void onChoiceSelectContentList(){
        String characterContentString = characterContentList.getSelectionModel().getSelectedItem();

        Map<String, String> getCharacterMap = FileSearch.getCharacterMap(characterContentString);

        String charType = StringConversion.getActionKey(getCharacterMap, new String[]{"charactertype"});
        String charFunction = StringConversion.getActionKey(getCharacterMap, new String[]{"Function","fc"});
        String charMessage = StringConversion.getActionKey(getCharacterMap, new String[]{"message","m"});
        if(StringConversion.headToUP(charType).equals("Content")){
            //??????????????????
            if(getCharacterMap.get("mid") != null)
            characterContent.setText(getCharacterMap.get("mid"));
        }else {
            //??????????????????
            characterContent.setText(charMessage);
            //????????????????????????
            CharacterMenuPage.setFunction(charFunction, characterFunction);
        }
        //??????????????????
        StringControl.setValue(characterType, StringConversion.headToUP(charType));


    }

    //**=============================================================================**/

    //???????????????
    public void open() {
        if(TopMenu.openCDSetting()){

        }
    }

    //??????????????????
    public void openSettings(){
        SettingsPage.display();
    }

    //??????
    public void saveFile(){
        FileControl.save();
    }

    //???????????????
    public void exit(){
        Main.timer.cancel();
        Main.mainWindow.close();
        CmdMain.forcedEndServer();
    }

    //?????????????????????
    public void openServerMenu(){ ServerMenuPage.display(); }
    //??????????????????
    public void openClassMenu(){ ClassMenuPage.display(); }
    //????????????????????????
    public void openSkillMenu(){ SkillMenuPage.display(); }
    //??????????????????
    public void openActionMenu(){ ActionMenuPage.display(); }
    //??????????????????
    public void opCharacterMenu(){ CharacterMenuPage.display(); }
    //??????GUI??????
    public void opGUIMenu(){ GUIMenuPage.display(); }
    //??????????????????
    public void openItemsMenu(){ ItemsMenuPage.display(); }
    //?????????????????????
    public void openTextureMenu(){ TextureMenuPage.display(); }
    //??????????????????
    public void openModsMenu(){ MobsMenuPage.display(); }

    //**=============================================================================**/

}
