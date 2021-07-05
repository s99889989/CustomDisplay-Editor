package com.daxton.api.backup;

import com.daxton.page.main.ServerMenuPage;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class zip3 {

    public static void unzip(String zipFilePath, String desDirectory) throws Exception {
        ServerMenuPage.print("正在複製備份...");
        File desDir = new File(desDirectory);
        if (!desDir.exists()) {
            boolean mkdirSuccess = desDir.mkdir();
            if (!mkdirSuccess) {
                throw new Exception("建立解壓目標資料夾失敗");
            }
        }
        // 讀入流
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath));
        // 遍歷每一個檔案
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        while (zipEntry != null) {
            if (zipEntry.isDirectory()) { // 資料夾
                String unzipFilePath = desDirectory + File.separator + zipEntry.getName();
                // 直接建立
                mkdir(new File(unzipFilePath));
            } else { // 檔案
                String unzipFilePath = desDirectory + File.separator + zipEntry.getName();
                File file = new File(unzipFilePath);
                // 建立父目錄
                mkdir(file.getParentFile());
                // 寫出檔案流
                BufferedOutputStream bufferedOutputStream =
                        new BufferedOutputStream(new FileOutputStream(unzipFilePath));
                byte[] bytes = new byte[1024];
                int readLen;
                while ((readLen = zipInputStream.read(bytes)) != -1) {
                    bufferedOutputStream.write(bytes, 0, readLen);
                }
                bufferedOutputStream.close();
            }
            zipInputStream.closeEntry();
            zipEntry = zipInputStream.getNextEntry();
        }
        zipInputStream.close();
    }

    // 如果父目錄不存在則建立
    private static void mkdir(File file) {
        if (null == file || file.exists()) {
            return;
        }
        mkdir(file.getParentFile());
        file.mkdir();
    }



}
