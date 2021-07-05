package com.daxton.controller.main;

import com.daxton.Main;
import com.daxton.api.StringConversion;
import com.daxton.config.FileControl;
import com.daxton.config.FileSearch;
import com.daxton.function.Manager;
import com.daxton.page.*;
import com.daxton.page.main.*;
import com.daxton.page.topmenu.SettingsPage;
import com.daxton.terminal.CmdMain;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ActionMenu {



    final public String[] actionArrayEntity = new String[]{"Attribute", "Damage", "DCMessage", "Glow", "Heal", "Invisible", "LoggerInfo", "Message", "Move", "MythicSkill", "Name", "PotionEffect", "Teleport"};
    final public String[] actionArrayPlayer = new String[]{"ActionBar", "BossBar", "Command", "Exp", "Item", "Inventory", "Level", "Mana", "Money", "Title"};
    final public String[] actionArrayLocation = new String[]{"Block", "Guise", "Hologram:", "Light", "Model", "Particle", "Sound"};
    final public String[] actionArrayMeta = new String[]{"Action", "Break", "Delay", "FixedPoint", "Loop", "LocPng", "Orbital", "SwitchAction"};
    final public String[] actionArrayClass = new String[]{"CustomPoint", "AttributePoint", "ClassAttr", "SetSkillLevel"};
    final public String[] actionArrayMod = new String[]{"ModMessage"};

    @FXML public ListView<String> actionTypeList;
    @FXML public TextField inputActionType;
    @FXML public ListView<String> actionList;
    @FXML public TextField inputAction;
    @FXML public ListView<String> actionContentList;
    @FXML public TextField selectActionContnet;
    @FXML public ListView<String> actionMenuList;
    @FXML public Text description;

    @FXML public VBox action_tab_entity;
    @FXML public VBox action_tab_player;
    @FXML public VBox action_tab_location;
    @FXML public VBox action_tab_meta;
    @FXML public VBox action_tab_class;
    @FXML public VBox action_tab_mod;

    @FXML public VBox targetEntity;
    @FXML public ListView<String> targetEntiytList;
    @FXML public ListView<String> targetEntityFilter;
    @FXML public TextField targetEntityDistance;
    @FXML public TextField targetEntityRadius;
    @FXML public VBox targetLocation;
    @FXML public ChoiceBox<String> targetLocationList;
    @FXML public ChoiceBox<String> targetLocationWorld;
    @FXML public ChoiceBox<String> targetLocationVec;
    @FXML public CheckBox targetLocationVecPitch;
    @FXML public CheckBox targetLocationVecYaw;
    @FXML public TextField targetLocationVecPitchAdd;
    @FXML public TextField targetLocationVecYawAdd;
    @FXML public TextField targetLocationVecDisAdd;
    @FXML public TextField targetLocationX;
    @FXML public TextField targetLocationY;
    @FXML public TextField targetLocationZ;
    @FXML public TextField targetLocationDistance;
    @FXML public CheckBox targetLocationOnTop;

    //當選擇目標
    public void onTargetEntityList(){
        targetEntityFilter.getSelectionModel().select(null);
        addActionContentTarget();
    }
    //當選擇過濾
    public void onTargetEntityFilter(){
        addActionContentTarget();
    }
    //當輸入距離
    public void onTargetEntityDistance(){
        addActionContentTarget();
    }
    //當輸入範圍
    public void onTargetEntityRadius(){
        addActionContentTarget();
    }

    public void addActionContentTarget(){

        if(targetEntiytList.getSelectionModel().getSelectedItem() != null){
            ActionMenuPage.targetContent = "";

            String targetList = targetEntiytList.getSelectionModel().getSelectedItem();
            ActionMenuPage.targetContent += " @"+targetList;
            String targetFilter = targetEntityFilter.getSelectionModel().getSelectedItem();
            if(targetFilter == null){
                targetFilter = "";
            }
            String targetDistance = targetEntityDistance.getText();
            String targetRadius = targetEntityRadius.getText();
            if(!targetFilter.isEmpty() || !targetDistance.isEmpty() || !targetRadius.isEmpty()){
                ActionMenuPage.targetContent += "{";
                if(!targetFilter.isEmpty()){
                    ActionMenuPage.targetContent += "Filters=" + targetFilter;
                }
                if(!targetDistance.isEmpty()){
                    if(!targetFilter.isEmpty()){
                        ActionMenuPage.targetContent += ";";
                    }
                    ActionMenuPage.targetContent += "Distance=" + targetDistance;
                }
                if(!targetRadius.isEmpty()){
                    if(!targetFilter.isEmpty() || !targetDistance.isEmpty()){
                        ActionMenuPage.targetContent += ";";
                    }
                    ActionMenuPage.targetContent += "Radius=" + targetRadius;
                }
                ActionMenuPage.targetContent += "}";
            }

            ActionMenuPage.setSelectActionContent();
        }



    }

    @FXML
    void initialize() {
        if(Main.languageConfig.getConfigurationSection("Aims.EntityTargeters") != null){
            Main.languageConfig.getConfigurationSection("Aims.EntityTargeters").getKeys(false).forEach(s -> targetEntiytList.getItems().add(s));
        }

        if(Manager.file_Config_Map.get("Other/TargetFilters.yml") != null && Manager.file_Config_Map.get("Other/TargetFilters.yml").getConfigurationSection("") != null){
            Manager.file_Config_Map.get("Other/TargetFilters.yml").getConfigurationSection("").getKeys(false).forEach(s -> targetEntityFilter.getItems().add(s));
        }

    }

    //------------------------------------------------------//

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
    //選擇動作類型表
    public void selectActionTypeList(){
        String selectType = actionTypeList.getSelectionModel().getSelectedItem();
        inputActionType.setText(selectType);
        FileConfiguration actionConfig = Manager.file_Config_Map.get("Actions/"+selectType);
        actionList.getItems().clear();
        Objects.requireNonNull(actionConfig.getConfigurationSection("")).getKeys(false).forEach(s -> actionList.getItems().add(s));
    }
    //新增動作類型表
    public void addActionType(){
        String inputString = inputActionType.getText();
        if(!inputString.isEmpty() && inputString.endsWith(".yml")){
            if(!actionTypeList.getItems().contains(inputString)){
                actionTypeList.getItems().add(inputString);
                actionTypeList.getSelectionModel().select(inputString);
                FileConfiguration defaultActionConfig = Manager.settings_Config_Map.get("DefaultFile/DefaultAction.yml");
                Manager.file_Config_Map.put("Actions/"+inputString, defaultActionConfig);

            }
        }
    }
    //移除動作類型表
    public void removeActionType(){
        String selectType = actionTypeList.getSelectionModel().getSelectedItem();
        actionTypeList.getItems().remove(selectType);
        inputActionType.clear();
        Manager.file_Config_Map.remove("Actions/"+selectType);
        File file = new File(Main.getOpenPath()+"/Actions/"+selectType);
        file.delete();

    }
    //選擇動作
    public void selectActionList(){
        String selectType = actionTypeList.getSelectionModel().getSelectedItem();
        FileConfiguration actionConfig = Manager.file_Config_Map.get("Actions/"+selectType);
        String selectAction = actionList.getSelectionModel().getSelectedItem();
        List<String> actionContentList = actionConfig.getStringList(selectAction+".Action");
        this.actionContentList.getItems().clear();
        this.actionContentList.getItems().addAll(actionContentList);
    }
    //新增動作
    public void addAction(){}
    //移除動作
    public void removeAction(){}
    //確定修改動作
    public void defineModifyAction(){}
    //選擇內容動作
    public void selectActionContent(){
        String selectContent = actionContentList.getSelectionModel().getSelectedItem();

        if(selectContent.contains(" @"))
        selectContent = selectContent.substring(0, selectContent.indexOf(" @"));
        ActionMenuPage.actionContent = selectContent;
        ActionMenuPage.setSelectActionContent();

        String actionType = StringConversion.getActionKey(FileSearch.setClassAction(selectContent), new String[]{"actiontype"});

        //actionMenuList.getSelectionModel().select("Loop");
        actionMenuList.getSelectionModel().select(actionType);
        actionMenuList.scrollTo(actionMenuList.getSelectionModel().getSelectedIndex());


        if(!actionType.isEmpty()){
            description.setText(Main.languageConfig.getString("Actions."+actionType));

            for(String actionKey : actionArrayEntity){
                VBox vBox = (VBox) action_tab_entity.lookup("#"+actionKey);
                vBox.setVisible(false);
                vBox.setManaged(false);
            }
            VBox vBox2 = (VBox) action_tab_entity.lookup("#"+actionType);
            vBox2.setVisible(true);
            vBox2.setManaged(true);

        }

    }
    //新增內容動作
    public void addActionContent(){
        String editString = selectActionContnet.getText();
        if(!editString.isEmpty()){
            actionContentList.getItems().add(editString);
        }


    }
    //確認編輯內容動作
    public void editActionContent(){
        String editString = selectActionContnet.getText();

        actionContentList.getItems().set(actionContentList.getSelectionModel().getSelectedIndex(), editString);

        actionContentList.getSelectionModel().select(editString);
    }
    //移除內容動作
    public void removeActionConten(){

        actionContentList.getItems().remove(actionContentList.getSelectionModel().getSelectedIndex());

    }

    //----------------------------------------------------------//
    //選擇動作選單
    public void selectActionMenu(){
        String selectString = actionMenuList.getSelectionModel().getSelectedItem();

        description.setText(Main.languageConfig.getString("Actions."+selectString));

        actionMenuControl(selectString);

        ActionMenuPage.targetContent = "";
        ActionMenuPage.actionContent = selectString+"[]";
        ActionMenuPage.setSelectActionContent();
    }
    //輸入動作類型來更改顯示
    public void actionMenuControl(String actionInputString){

        //目標為實體
        if(Arrays.asList(actionArrayEntity).contains(actionInputString)){
            action_tab_entity.setVisible(true);
            action_tab_player.setVisible(false);
            action_tab_location.setVisible(false);
            action_tab_meta.setVisible(false);
            action_tab_class.setVisible(false);
            action_tab_mod.setVisible(false);

            targetEntity.setVisible(true);
            targetLocation.setVisible(false);

            for(String actionKey : actionArrayEntity){
                VBox vBox = (VBox) action_tab_entity.lookup("#"+actionKey);
                vBox.setVisible(false);
                vBox.setManaged(false);
            }
            try {
                VBox vBox2 = (VBox) action_tab_entity.lookup("#"+actionInputString);
                vBox2.setVisible(true);
                vBox2.setManaged(true);
            }catch (Exception exception){
                exception.printStackTrace();
            }

        }
        //目標為座標
        if(Arrays.asList(actionArrayLocation).contains(actionInputString)){
            action_tab_entity.setVisible(false);
            action_tab_player.setVisible(false);
            action_tab_location.setVisible(true);
            action_tab_meta.setVisible(false);
            action_tab_class.setVisible(false);
            action_tab_mod.setVisible(false);

            targetEntity.setVisible(false);
            targetLocation.setVisible(true);

            for(String actionKey : actionArrayLocation){
                VBox vBox = (VBox) action_tab_location.lookup("#"+actionKey);
                vBox.setVisible(false);
                vBox.setManaged(false);
            }
            try {
                VBox vBox2 = (VBox) action_tab_location.lookup("#"+actionInputString);
                vBox2.setVisible(true);
                vBox2.setManaged(true);
            }catch (Exception exception){
                exception.printStackTrace();
            }

        }


    }

}
