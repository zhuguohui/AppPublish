package com.zgh.app.bean;

public class ApkInfo {
    public  String apkName;
    public  String customName;
    public  String apkVersionName;
    public  String apkBuildCode;

    public  String apkOutputFileName;
    public  String apkOutputDirectory;
    public  String apkLogoPath;

    //----------发布相关---------
    public  String apkDownLoadUrl;
    //-----------email相关---------
    public String downLoadCodePath;


    public ApkInfo(String apkName, String customName, String apkVersionName, String apkBuildCode, String apkOutputFileName, String apkOutputDirectory, String apkLogoPath, String apkDownLoadUrl) {
        this.apkName = apkName;
        this.customName = customName;
        this.apkVersionName = apkVersionName;
        this.apkBuildCode = apkBuildCode;
        this.apkOutputFileName = apkOutputFileName;
        this.apkOutputDirectory = apkOutputDirectory;
        this.apkLogoPath = apkLogoPath;
        this.apkDownLoadUrl = apkDownLoadUrl;
    }
}
