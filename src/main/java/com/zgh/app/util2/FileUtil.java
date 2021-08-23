package com.zgh.app.util2;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
    public String writeFile(InputStream input, final File outFile) {
        System.out.println("临时文件位置:"+outFile.getPath());
        try {
            FileOutputStream fos = new FileOutputStream(outFile);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = input.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            fos.close();
            input.close();
            return outFile.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void openDir(String path){
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
