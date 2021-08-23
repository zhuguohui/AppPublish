package com.zgh.app.bean;

import java.util.List;

public class PublishInfo {
    EmailItem emailInfo;
    List<ApkInfo> apkInfos;

    public PublishInfo(EmailItem emailInfo, List<ApkInfo> apkInfos) {
        this.emailInfo = emailInfo;
        this.apkInfos = apkInfos;
    }

    public void setEmailInfo(EmailItem emailInfo) {
        this.emailInfo = emailInfo;
    }

    public void setApkInfos(List<ApkInfo> apkInfos) {
        this.apkInfos = apkInfos;
    }

    public EmailItem getEmailInfo() {
        return emailInfo;
    }

    public List<ApkInfo> getApkInfos() {
        return apkInfos;
    }
}
