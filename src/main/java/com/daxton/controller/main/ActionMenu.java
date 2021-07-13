package com.daxton.controller.main;

import com.daxton.Main;
import com.daxton.api.StringControl;
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
import java.util.Map;
import java.util.Objects;

public class ActionMenu {



    final public String[] actionArrayEntity = new String[]{"Attribute", "Damage", "DCMessage", "Glow", "Heal", "Invisible", "LoggerInfo", "Message", "Move", "MythicSkill", "Name", "PotionEffect", "Teleport"};
    final public String[] actionArrayPlayer = new String[]{"ActionBar", "BossBar", "Command", "Exp", "Item", "Inventory", "Level", "Mana", "Money", "Title"};
    final public String[] actionArrayLocation = new String[]{"Block", "Guise", "Hologram", "Light", "Model", "Particle", "ParticlePNG", "Sound"};
    final public String[] actionArrayMeta = new String[]{"Action", "Break", "Delay", "FixedPoint", "Loop", "LocPng", "Orbital", "SwitchAction"};
    final public String[] actionArrayClass = new String[]{"CustomPoint", "AttributePoint", "ClassAttr", "SetSkillLevel"};
    final public String[] actionArrayMod = new String[]{"ModMessage"};

    final public String[] targetArray = new String[]{"Attribute", "Damage", "DCMessage", "Glow", "Heal", "Invisible", "LoggerInfo", "Message", "Move", "MythicSkill", "Name", "PotionEffect", "Teleport", "ActionBar", "BossBar", "Command", "Exp", "Item", "Inventory", "Level", "Mana", "Money", "Title", "Action", "Break", "Loop", "SwitchAction", "CustomPoint", "AttributePoint", "ClassAttr", "SetSkillLevel", "ModMessage"};
    final public String[] locationArray = new String[]{"Block", "Guise", "Hologram", "Light", "Model", "Particle", "ParticlePNG", "Sound", "FixedPoint", "LocPng", "Orbital"};
    final public String[] nonArray = new String[]{"Delay"};

    @FXML public ListView<String> actionTypeList;
    @FXML public TextField inputActionType;
    @FXML public ListView<String> actionList;
    @FXML public TextField inputAction;
    @FXML public ListView<String> actionContentList;
    @FXML public TextField selectActionContnet;
    @FXML public TextField actionMenuFind;
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

    @FXML
    void initialize() {

        //Target
        if(Main.languageConfig.getConfigurationSection("Aims.EntityTargeters") != null){
            Main.languageConfig.getConfigurationSection("Aims.EntityTargeters").getKeys(false).forEach(s -> targetEntiytList.getItems().add(s));
        }
        if(Manager.file_Config_Map.get("Other/TargetFilters.yml") != null && Manager.file_Config_Map.get("Other/TargetFilters.yml").getConfigurationSection("") != null){
            Manager.file_Config_Map.get("Other/TargetFilters.yml").getConfigurationSection("").getKeys(false).forEach(s -> targetEntityFilter.getItems().add(s));
        }
        //Location
        if(Main.languageConfig.getConfigurationSection("Aims.LocationTargeters") != null){
            Main.languageConfig.getConfigurationSection("Aims.LocationTargeters").getKeys(false).forEach(s -> targetLocationList.getItems().add(s));
        }
        targetLocationList.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            targetLocationList.getSelectionModel().select(targetLocationList.getItems().get(newValue.intValue()));
            onChangeTargetContent();
        });
        if(Manager.file_Config_Map.get("config.yml") != null){
            targetLocationWorld.getItems().add("Self");
            Manager.file_Config_Map.get("config.yml").getStringList("World.List").forEach(s -> targetLocationWorld.getItems().add(s));
        }
        targetLocationWorld.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            targetLocationWorld.getSelectionModel().select(targetLocationWorld.getItems().get(newValue.intValue()));
            onChangeTargetContent();
        });
        targetLocationVec.getItems().add("self");
        targetLocationVec.getItems().add("target");
        targetLocationVec.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            targetLocationVec.getSelectionModel().select(targetLocationVec.getItems().get(newValue.intValue()));
            onChangeTargetContent();
        });
    }

    //改變目標內容(座標)
    public void onChangeTargetContent(){
        //Location
        if(targetLocationList.getSelectionModel().getSelectedItem() != null){

            ActionMenuPage.keyValue.clear();

            if(!StringControl.getValue(targetLocationVec).isEmpty() && !StringControl.getValue(targetLocationVecPitchAdd).isEmpty() && !StringControl.getValue(targetLocationVecYawAdd).isEmpty() && !StringControl.getValue(targetLocationVecDisAdd).isEmpty())
                ActionMenuPage.keyValue.put("VectorAdd", StringControl.getValue(targetLocationVec)+"|"+StringControl.getValue(targetLocationVecPitch)+"|"+StringControl.getValue(targetLocationVecYaw)+"|"+StringControl.getValue(targetLocationVecPitchAdd)+"|"+StringControl.getValue(targetLocationVecYawAdd)+"|"+StringControl.getValue(targetLocationVecDisAdd));

            if(!StringControl.getValue(targetLocationX).isEmpty() && !StringControl.getValue(targetLocationY).isEmpty() && !StringControl.getValue(targetLocationZ).isEmpty())
                ActionMenuPage.keyValue.put("LocationAdd", StringControl.getValue(targetLocationX)+"|"+StringControl.getValue(targetLocationY)+"|"+StringControl.getValue(targetLocationZ));

            ActionMenuPage.keyValue.put("Distance", StringControl.getValue(targetLocationDistance));
            ActionMenuPage.keyValue.put("OnBlock", StringControl.getValue(targetLocationOnTop));
            ActionMenuPage.keyValue.put("WorldName", StringControl.getValue(targetLocationWorld));


            ActionMenuPage.changeTargetContnet(StringControl.getValue(targetLocationList));
        }
    }

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

    //改變目標內容(實體)
    public void addActionContentTarget(){

        //Target
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

    //當輸入動作關鍵字搜尋
    public void onActionMenuFind(){
        if(Main.languageConfig.getConfigurationSection("Actions") != null){
            actionMenuList.getItems().clear();
            Main.languageConfig.getConfigurationSection("Actions").getKeys(false).forEach(s -> {
                if(s.toLowerCase().contains(actionMenuFind.getText().toLowerCase()))
                actionMenuList.getItems().add(s);
            });
        }

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

        Map<String ,String> actionMap = FileSearch.setClassAction(selectContent);

        String targetType = StringConversion.getActionKey(actionMap, new String[]{"targetkey"});

        if(selectContent.contains(" @"))
        selectContent = selectContent.substring(0, selectContent.indexOf(" @"));
        ActionMenuPage.targetContent = targetType;
        ActionMenuPage.actionContent = selectContent;
        ActionMenuPage.setSelectActionContent();

        String actionType = StringConversion.getActionKey(actionMap, new String[]{"actiontype"});

        actionMenuList.getSelectionModel().select(actionType);
        actionMenuList.scrollTo(actionMenuList.getSelectionModel().getSelectedIndex());
        //更改目標選項(座標)
        setTargetLoction(targetType);
        //更改目標選項(實體)
        setTargetContent(targetType);
        //輸入動作類型來更改顯示
        actionMenuControl(actionType);

    }
    //更改目標選項(座標)
    public void setTargetLoction(String inputString){
        if(inputString.isEmpty()){
            return;
        }
        Map<String ,String> actionMap = FileSearch.setTargetAction(inputString);

        String targetType = inputString.substring(1, inputString.indexOf("{")).trim();

        StringControl.setValue(targetLocationList, targetType);

        StringControl.setMapValue(targetLocationDistance, actionMap, new String[]{"Distance", "d"});
        StringControl.setMapValue(targetLocationOnTop, actionMap, new String[]{"OnBlock", "ob"});
        StringControl.setMapValue(targetLocationWorld, actionMap, new String[]{"WorldName", "wn"});

        String vecString = StringConversion.getActionKey(actionMap, new String[]{"VA", "VectorAdd"});
        if(!vecString.isEmpty() && vecString.contains("|")){
            String[] mArray = vecString.split("\\|");
            if(mArray.length == 6){
                StringControl.setValue(targetLocationVec, mArray[0]);
                StringControl.setValue(targetLocationVecPitch, mArray[1]);
                StringControl.setValue(targetLocationVecYaw, mArray[2]);
                StringControl.setValue(targetLocationVecPitchAdd, mArray[3]);
                StringControl.setValue(targetLocationVecYawAdd, mArray[4]);
                StringControl.setValue(targetLocationVecDisAdd, mArray[5]);
            }
        }

        String locString = StringConversion.getActionKey(actionMap, new String[]{"LA", "LocationAdd"});
        if(!locString.isEmpty() && locString.contains("|")){
            String[] mArray = locString.split("\\|");
            if(mArray.length == 3){
                StringControl.setValue(targetLocationX, mArray[0]);
                StringControl.setValue(targetLocationY, mArray[1]);
                StringControl.setValue(targetLocationZ, mArray[2]);

            }
        }

    }

    //更改目標選項(實體)
    public void setTargetContent(String inputString){
        if(inputString.isEmpty()){
            return;
        }
        Map<String ,String> actionMap = FileSearch.setTargetAction(inputString);

        StringControl.setMapValue(targetEntiytList, actionMap, new String[]{"targettype"});
        StringControl.setMapValue(targetEntityFilter, actionMap, new String[]{"Filters","f"});
        StringControl.setMapValue(targetEntityDistance, actionMap, new String[]{"Distance","d"});
        StringControl.setMapValue(targetEntityRadius, actionMap, new String[]{"Radius","r"});

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

        //輸入動作類型來更改顯示
        actionMenuControl(selectString);

        ActionMenuPage.targetContent = "";
        ActionMenuPage.actionContent = selectString+"[]";
        ActionMenuPage.setSelectActionContent();
    }
    //輸入動作類型來更改顯示
    public void actionMenuControl(String actionInputString){
        if(actionInputString == null && actionInputString.isEmpty()){
            return;
        }
        description.setText(Main.languageConfig.getString("Actions."+actionInputString));

        //使用實體目標
        if(Arrays.asList(targetArray).contains(actionInputString)){
            targetEntity.setVisible(true);
            targetLocation.setVisible(false);
        }
        //使用座標目標
        if(Arrays.asList(locationArray).contains(actionInputString)){
            targetEntity.setVisible(false);
            targetLocation.setVisible(true);
        }
        //無目標
        if(Arrays.asList(nonArray).contains(actionInputString)){
            targetEntity.setVisible(false);
            targetLocation.setVisible(false);
        }

        //目標為實體
        if(Arrays.asList(actionArrayEntity).contains(actionInputString)){
            action_tab_entity.setVisible(true);
            action_tab_player.setVisible(false);
            action_tab_location.setVisible(false);
            action_tab_meta.setVisible(false);
            action_tab_class.setVisible(false);
            action_tab_mod.setVisible(false);

            for(String actionKey : actionArrayEntity){
                VBox vBox = (VBox) action_tab_entity.lookup("#"+actionKey);
                vBox.setVisible(false);
                vBox.setManaged(false);
            }

            VBox vBox2 = (VBox) action_tab_entity.lookup("#"+actionInputString);
            vBox2.setVisible(true);
            vBox2.setManaged(true);

        }
        //目標為玩家
        if(Arrays.asList(actionArrayPlayer).contains(actionInputString)){
            action_tab_entity.setVisible(false);
            action_tab_player.setVisible(true);
            action_tab_location.setVisible(false);
            action_tab_meta.setVisible(false);
            action_tab_class.setVisible(false);
            action_tab_mod.setVisible(false);

            for(String actionKey : actionArrayPlayer){
                VBox vBox = (VBox) action_tab_player.lookup("#"+actionKey);
                vBox.setVisible(false);
                vBox.setManaged(false);
            }

            VBox vBox2 = (VBox) action_tab_player.lookup("#"+actionInputString);
            vBox2.setVisible(true);
            vBox2.setManaged(true);

        }
        //目標為座標
        if(Arrays.asList(actionArrayLocation).contains(actionInputString)){
            action_tab_entity.setVisible(false);
            action_tab_player.setVisible(false);
            action_tab_location.setVisible(true);
            action_tab_meta.setVisible(false);
            action_tab_class.setVisible(false);
            action_tab_mod.setVisible(false);

            for(String actionKey : actionArrayLocation){
                VBox vBox = (VBox) action_tab_location.lookup("#"+actionKey);
                vBox.setVisible(false);
                vBox.setManaged(false);
            }

            VBox vBox2 = (VBox) action_tab_location.lookup("#"+actionInputString);
            vBox2.setVisible(true);
            vBox2.setManaged(true);

        }
        //元動作
        if(Arrays.asList(actionArrayMeta).contains(actionInputString)){
            action_tab_entity.setVisible(false);
            action_tab_player.setVisible(false);
            action_tab_location.setVisible(false);
            action_tab_meta.setVisible(true);
            action_tab_class.setVisible(false);
            action_tab_mod.setVisible(false);

            for(String actionKey : actionArrayMeta){
                VBox vBox = (VBox) action_tab_meta.lookup("#"+actionKey);
                vBox.setVisible(false);
                vBox.setManaged(false);
            }

            VBox vBox2 = (VBox) action_tab_meta.lookup("#"+actionInputString);
            vBox2.setVisible(true);
            vBox2.setManaged(true);

        }
        //Class專用
        if(Arrays.asList(actionArrayClass).contains(actionInputString)){
            action_tab_entity.setVisible(false);
            action_tab_player.setVisible(false);
            action_tab_location.setVisible(false);
            action_tab_meta.setVisible(false);
            action_tab_class.setVisible(true);
            action_tab_mod.setVisible(false);

            for(String actionKey : actionArrayClass){
                VBox vBox = (VBox) action_tab_class.lookup("#"+actionKey);
                vBox.setVisible(false);
                vBox.setManaged(false);
            }

            VBox vBox2 = (VBox) action_tab_class.lookup("#"+actionInputString);
            vBox2.setVisible(true);
            vBox2.setManaged(true);

        }

        //Mod相關動作
        if(Arrays.asList(actionArrayMod).contains(actionInputString)){
            action_tab_entity.setVisible(false);
            action_tab_player.setVisible(false);
            action_tab_location.setVisible(false);
            action_tab_meta.setVisible(false);
            action_tab_class.setVisible(false);
            action_tab_mod.setVisible(true);

            for(String actionKey : actionArrayMod){
                VBox vBox = (VBox) action_tab_mod.lookup("#"+actionKey);
                vBox.setVisible(false);
                vBox.setManaged(false);
            }

            VBox vBox2 = (VBox) action_tab_mod.lookup("#"+actionInputString);
            vBox2.setVisible(true);
            vBox2.setManaged(true);

        }

    }

}
