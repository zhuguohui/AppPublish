package com.zgh.app;

import com.zgh.app.bean.ApkInfo;
import com.zgh.app.bean.PublishInfo;
import com.zgh.app.email.SendEmail;
import com.zgh.app.util2.FileCopyUtil;
import com.zgh.app.util2.FileUtil;
import com.zgh.app.util2.ImageCopyUtil;
import com.zgh.app.util2.QRCodeGenerator;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppPublishFrame extends ApkFrame {


    PublishInfo pushInfo;

    public AppPublishFrame(PublishInfo pushInfo) throws HeadlessException {
        super(pushInfo.getApkInfos());
        this.pushInfo = pushInfo;
        SendEmail.send(pushInfo.getEmailInfo(),pushInfo.getApkInfos());
    }

    @Override
    protected void buildItem(ApkInfo apkInfo) {
        JPanel imgPanel = new JPanel();
        imgPanel.setSize(500, 500);

        String codePath = createCode(apkInfo);
        apkInfo.downLoadCodePath=codePath;
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(codePath));
        label.setLocation(0, 0);
        label.setHorizontalAlignment(JLabel.CENTER);

        imgPanel.add(label, BorderLayout.CENTER);

        jPanel.add(imgPanel);

        //复制功能
        JPanel menuPanel = new JPanel();

        menuPanel.setLayout(new FlowLayout());
        menuPanel.setSize(300, 200);

        JButton jButton = new JButton("复制二维码");
        jButton.setSize(50, 50);
        jButton.addActionListener(e -> ImageCopyUtil.copyImage(label));
        menuPanel.add(jButton);

        JButton jButton2 = new JButton("复制apk");
        jButton2.setSize(50, 50);
        jButton2.addActionListener(e -> FileCopyUtil.copy(apkInfo.apkOutputDirectory + java.io.File.separator + apkInfo.apkOutputFileName));
        menuPanel.add(jButton2);


        JButton jButton3 = new JButton("打开所在文件夹");
        jButton3.setSize(50, 50);
        jButton3.addActionListener(e -> FileUtil.openDir(apkInfo.apkOutputDirectory));

        menuPanel.add(jButton3);


        jPanel.add(menuPanel);

        //增加间隙
        Label emptyLabel = new Label();
        emptyLabel.setSize(10, 50);
        jPanel.add(emptyLabel);
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static String createCode(ApkInfo apkInfo) {
        List<String> titles = new ArrayList<>();
        titles.add("应用名称:" + apkInfo.apkName);
        titles.add("版本名称:" + apkInfo.customName);
        titles.add("版本号:" + apkInfo.apkVersionName);
        titles.add("BuildCode:" + apkInfo.apkBuildCode);
        titles.add("更新时间:" + sdf.format(new Date()));
        String logoPath = apkInfo.apkLogoPath;
        String content = apkInfo.apkDownLoadUrl;
        String fileName = apkInfo.apkName + "_" + apkInfo.customName + "";
        try {
            return QRCodeGenerator.encode(fileName, titles, content, logoPath, true, new FileUtil());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
