package com.daxton.config;

import com.daxton.function.Manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FileSearch {

    public static List<String> getTypeFileName(String type){
        List<String> fileNameList = new ArrayList<>();

        Manager.file_Name_Map.forEach((s, s2) -> {
            //System.out.println(s+" : "+s2);
            if(s.startsWith(type)){
                fileNameList.add(s2);
            }
        });

        return fileNameList;
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
                if(ze.getName().contains(".properties") && ze.getName().contains("/language/") && !(ze.getName().contains("plugin"))){
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

}
