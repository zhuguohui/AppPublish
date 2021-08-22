package com.zgh.app;

import com.zgh.app.bean.ApkInfo;

public class Test {
    public static void main(String[] args) {
        ApkInfo apkInfo=new ApkInfo(
                "读嘉",
                "正式版",
                "1.4.1",
                "71",
                "读嘉_official_v1.4.4_build72_release_202108222144.apk",
                "E:\\WORK\\GIT\\HangZhou\\JXRB\\JXRB_android\\app\\build\\outputs\\apk\\official\\release",
                "E:\\WORK\\GIT\\HangZhou\\JXRB\\JXRB_android\\app\\publish\\icon\\app_logo.png"
        );
        ApkInfo apkInfo2=new ApkInfo(
                "读嘉",
                "蒲公英版",
                "1.4.1",
                "71",
                "读嘉_official_v1.4.4_build72_release_202108222022.apk",
                "E:\\WORK\\GIT\\HangZhou\\JXRB\\JXRB_android\\app\\build\\outputs\\apk\\official\\release",
                "E:\\WORK\\GIT\\HangZhou\\JXRB\\JXRB_android\\app\\publish\\icon\\app_logo.png"
        );
        ApkInfo apkInfo3=new ApkInfo(
                "读嘉",
                "测试版",
                "1.4.1",
                "71",
                "app-official-debug.apk",
                "E:\\WORK\\GIT\\HangZhou\\JXRB\\JXRB_android\\app\\build\\outputs\\apk\\official\\debug",
                "E:\\WORK\\GIT\\HangZhou\\JXRB\\JXRB_android\\app\\publish\\icon\\app_logo.png"
        );
        ApkBuildFrame frame=new ApkBuildFrame(apkInfo,apkInfo2,apkInfo3);
    }


}
