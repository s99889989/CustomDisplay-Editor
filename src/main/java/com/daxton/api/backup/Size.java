package com.daxton.api.backup;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;

public class Size {

    public Size(){

    }

    public static String fileSize(String patch){

        String outPut = "";
        FileChannel fc= null;
        try {
            File f= new File(patch);
            FileInputStream fis= new FileInputStream(f);
            fc= fis.getChannel();
            outPut = readableFileSize(fc.size());
        }catch (IOException exception){

        }

        return outPut;
    }

    public static String readableFileSize(long size) {
        if(size <= 0) return "0";
        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

}
