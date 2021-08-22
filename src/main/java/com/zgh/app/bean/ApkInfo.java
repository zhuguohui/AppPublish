package com.zgh.app.bean;

public class ApkInfo {
    public final String apkName;
    public final String customName;
    public final String apkVersionName;
    public final String apkBuildCode;

    public final String apkOutputFileName;
    public final String apkOutputDirectory;
    public final String apkLogoPath;

    public ApkInfo(String apkName, String customName, String apkVersionName, String apkBuildCode, String apkOutputFileName, String apkOutputDirectory, String apkLogoPath) {
        this.apkName = apkName;
        this.customName = customName;
        this.apkVersionName = apkVersionName;
        this.apkBuildCode = apkBuildCode;
        this.apkOutputFileName = apkOutputFileName;
        this.apkOutputDirectory = apkOutputDirectory;
        this.apkLogoPath = apkLogoPath;
    }
}
