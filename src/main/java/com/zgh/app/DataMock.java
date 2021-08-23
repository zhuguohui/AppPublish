package com.zgh.app;

import com.zgh.app.bean.ApkInfo;
import com.zgh.app.bean.EmailItem;
import com.zgh.app.bean.PublishInfo;

import java.util.ArrayList;
import java.util.List;

public class DataMock {
    static List<ApkInfo> apkInfos=new ArrayList<>();
    static {
        String apkDownLoadUrl = "http://d.cc53.cn/e6da";
        String logoPath = "D:\\TRS_GIT\\2019\\04_JiaXinNews\\JXRB_android\\app\\publish\\icon\\app_logo.png";
        ApkInfo apkInfo = new ApkInfo(
                "读嘉",
                "正式版",
                "1.4.1",
                "71",
                "读嘉_official_v1.4.4_build72_release_202108222144.apk",
                "E:\\WORK\\GIT\\HangZhou\\JXRB\\JXRB_android\\app\\build\\outputs\\apk\\official\\release",
                logoPath,
                apkDownLoadUrl);
        ApkInfo apkInfo2 = new ApkInfo(
                "读嘉",
                "蒲公英版",
                "1.4.1",
                "71",
                "读嘉_official_v1.4.4_build72_release_202108222022.apk",
                "E:\\WORK\\GIT\\HangZhou\\JXRB\\JXRB_android\\app\\build\\outputs\\apk\\official\\release",
                logoPath,
                apkDownLoadUrl);
        ApkInfo apkInfo3 = new ApkInfo(
                "读嘉",
                "测试版",
                "1.4.1",
                "71",
                "app-official-debug.apk",
                "E:\\WORK\\GIT\\HangZhou\\JXRB\\JXRB_android\\app\\build\\outputs\\apk\\official\\debug",
                logoPath,
                apkDownLoadUrl);
        apkInfos.add(apkInfo);
        apkInfos.add(apkInfo2);
        apkInfos.add(apkInfo3);
        EmailItem emailItem=new EmailItem("smtp.qq.com","287718603@qq.com","manfwbcmpmbwbidd",true);
        publishInfo=new PublishInfo(emailItem,apkInfos);

    }

    static PublishInfo publishInfo;
    public static List<ApkInfo> getData() {

        return apkInfos;
    }
}
