package com.daxton.api.backup;

import java.io.File;

public class DeleteFile {


    public static boolean deleteDirectory(String dir){
        //如果dir不以檔案分隔符結尾,自動新增檔案分隔符
        if(!dir.endsWith(File.separator)){
            dir = dir+File.separator;
        }
        File dirFile = new File(dir);
        //如果dir對應的檔案不存在,或者不是一個目錄,則退出
        if(!dirFile.exists() || !dirFile.isDirectory()){
            System.out.println("刪除目錄失敗"+dir+"目錄不存在!");
            return false;
        }
        boolean flag = true;
        //刪除資料夾下的所有檔案(包括子目錄)
        File[] files = dirFile.listFiles();
        for(int i=0;i<files.length;i++){
            //刪除子檔案
            if(files[i].isFile()){
                flag = deleteFile(files[i].getAbsolutePath());
                if(!flag){
                    break;
                }
            }
            //刪除子目錄
            else{
                flag = deleteDirectory(files[i].getAbsolutePath());
                if(!flag){
                    break;
                }
            }
        }

        if(!flag){
            System.out.println("刪除目錄失敗");
            return false;
        }

        //刪除當前目錄
        if(dirFile.delete()){
            //System.out.println("刪除目錄"+dir+"成功!");
            return true;
        }else{
            System.out.println("刪除目錄"+dir+"失敗!");
            return false;
        }
        //return true;
    }

    public static boolean deleteFile(String fileName){
        File file = new File(fileName);
        if(file.isFile() && file.exists()){
            file.delete();
            //System.out.println("刪除單個檔案"+fileName+"成功!");
            return true;
        }else{
            System.out.println("刪除單個檔案"+fileName+"失敗!");
            return false;
        }
    }

}
