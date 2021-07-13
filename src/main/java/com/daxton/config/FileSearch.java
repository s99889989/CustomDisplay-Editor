package com.daxton.config;

import com.daxton.function.Manager;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FileSearch {

    //獲取設定檔內的類別檔案名稱
    public static List<String> getTypeFileName(String type){
        List<String> fileNameList = new ArrayList<>();

        Manager.file_Name_Map.forEach((s, s2) -> {
            if(s.startsWith(type)){
                fileNameList.add(s2);
            }
        });

        return fileNameList;
    }

    //獲取設定檔內的類別檔案關鍵字
    public static List<String> getTypeFileKey(String type){
        List<String> fileKeyList = new ArrayList<>();
        Manager.file_Name_Map.forEach((s, s2) -> {
            if(s.startsWith(type)){
                fileKeyList.add(s);
            }
        });
        return fileKeyList;
    }

    //獲取設定檔內的類別檔案設定
    public static List<FileConfiguration> getTypeFileConfig(String type){
        List<FileConfiguration> fileConfigList = new ArrayList<>();

        Manager.file_Config_Map.forEach((s, fileConfiguration) -> {
            if(s.startsWith(type)){
                fileConfigList.add(fileConfiguration);
            }
        });


        return fileConfigList;
    }

    //獲取資料夾下.yml檔案名稱
    public static List<File> getFiles(String path){
        File root = new File(path);
        List<File> files = new ArrayList<>();
        if(!root.isDirectory()){
            if(root.getAbsolutePath().endsWith(".yml") || root.getAbsolutePath().endsWith(".png")){
                files.add(root);
            }
        }else{
            File[] subFiles = root.listFiles();
            assert subFiles != null;
            for(File f : subFiles){
                files.addAll(getFiles(f.getAbsolutePath()));
            }
        }
        return files;
    }

    //讀取jar內的檔案名稱
    public static List<String> readZipFile(String file) throws Exception {
        ZipFile zf = new ZipFile(file);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ZipInputStream zin = new ZipInputStream(in);
        ZipEntry ze;
        List<String> stringList = new ArrayList<>();
        while ((ze = zin.getNextEntry()) != null) {
            if (!ze.isDirectory()) {
                long size = ze.getSize();
                if(!(ze.getName().contains(".class"))){
                    stringList.add(ze.getName());
                }
                if (size > 0) {
                    BufferedReader br = new BufferedReader( new InputStreamReader(zf.getInputStream(ze)));
//                    String line;
//                    while ((line = br.readLine()) != null) {
//                    }
                    br.close();
                }
            }
        }
        zin.closeEntry();
        return stringList;
    }



    //把一條動作目標轉成Map
    public static Map<String, String> setTargetAction(String inputString){
        Map<String, String> actionMap = new HashMap<>();
        if(inputString.contains("@")){

            if (inputString.contains("{") && inputString.contains("}")) {
                int num1 = appearNumber(inputString, "\\{");
                int num2 = appearNumber(inputString, "\\}");
                if (num1 == 1 && num2 == 1) {
                    String actionType = inputString.substring(inputString.indexOf("@")+1, inputString.indexOf("{")).trim();
                    actionMap.put("targettype",actionType);

                    String midSet = inputString.substring(inputString.indexOf("{")+1, inputString.indexOf("}"));

                    List<String> midSetList = getBlockList(midSet,";");
                    midSetList.forEach(midKey -> {
                        String[] midArray = midKey.split("=");
                        if(midArray.length == 2){
                            //cd.getLogger().info(midArray[0]+" : "+midArray[1]);
                            actionMap.put(midArray[0].toLowerCase(),midArray[1]);
                        }
                    });

                }
            }else {
                String actionType = inputString.substring(inputString.indexOf("@")+1).trim();
                actionMap.put("targettype",actionType);
            }
        }

        return actionMap;
    }
    //把字符轉成Map
    public static Map<String, String> getCharacterMap (String inputString){
        Map<String, String> actionMap = new HashMap<>();
        if (inputString.contains("[") && inputString.contains("]")) {
            int num1 = appearNumber(inputString, "\\[");
            int num2 = appearNumber(inputString, "\\]");
            if (num1 == 1 && num2 == 1) {
                String actionType = inputString.substring(0, inputString.indexOf("[")).trim();
                actionMap.put("charactertype",actionType.trim());

                String midSet = inputString.substring(inputString.indexOf("[")+1, inputString.indexOf("]"));
                List<String> midSetList = getBlockList(midSet,";");
                midSetList.forEach(midKey -> {
                    String[] midArray = midKey.split("=");
                    if(midArray.length == 2){
                        //cd.getLogger().info(midArray[0]+" : "+midArray[1]);
                        actionMap.put(midArray[0].toLowerCase(),midArray[1]);
                    }
                });

            }
        }
        return actionMap;
    }

    //把一條動作轉成Map
    public static Map<String, String> setClassAction(String inputString){
        Map<String, String> actionMap = new HashMap<>();
        if (inputString.contains("[") && inputString.contains("]")) {

            int num1 = appearNumber(inputString, "\\[");
            int num2 = appearNumber(inputString, "\\]");
            if (num1 == 1 && num2 == 1) {
                //設定動作類型
                String actionType = inputString.substring(0, inputString.indexOf("[")).trim();
                actionMap.put("actiontype",actionType.trim());
                //cd.getLogger().info("ActionType"+" : "+actionType);

                //從]往後的字串
                String lastSet = inputString.substring(inputString.indexOf("]")+1)+" ";
                //設定目標
                if(lastSet.contains("@")){
                    String targetKey = lastSet.substring(lastSet.indexOf("@"), lastSet.indexOf(" ",lastSet.indexOf("@")));

                    actionMap.put("targetkey",targetKey);
                    //cd.getLogger().info("TargetKey"+" : "+targetKey);
                }
                //設定觸發
                if(lastSet.contains("~")){
                    String triggerKey = lastSet.substring(lastSet.indexOf("~"), lastSet.indexOf(" ",lastSet.indexOf("~")));
                    actionMap.put("triggerkey",triggerKey);
                    //cd.getLogger().info("TriggerKey"+" : "+triggerKey);
                }

                String midSet = inputString.substring(inputString.indexOf("[")+1, inputString.indexOf("]"));

                List<String> midSetList = getBlockList(midSet,";");
                midSetList.forEach(midKey -> {
                    String[] midArray = midKey.split("=");
                    if(midArray.length == 2){
                        //cd.getLogger().info(midArray[0]+" : "+midArray[1]);
                        actionMap.put(midArray[0].toLowerCase(),midArray[1]);
                    }
                });
            }
        }

        return actionMap;
    }

    //計算指定單字出現次數
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }

    //丟入字串和key 轉成List
    public static List<String> getBlockList(String string,String key){
        List<String> stringList = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(string,key);
        while(stringTokenizer.hasMoreElements()){
            stringList.add(stringTokenizer.nextToken());
        }
        return stringList;
    }

}
