package com.zgh.app;

import com.zgh.app.bean.ApkInfo;
import com.zgh.app.util2.FileCopyUtil;
import com.zgh.app.util2.FileUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ApkBuildFrame extends ApkFrame {

    public ApkBuildFrame(List<ApkInfo> apkInfos) throws HeadlessException {
        super(apkInfos);
    }

    @Override
    protected void  buildItem(ApkInfo apkInfo) {
        text(apkInfo.apkName,30);

        //发布成功

        //版本名称
        text(apkInfo.customName,20);

        //版本号

        text("版本号:" + apkInfo.apkVersionName,20);

        text("BuildCod:"+apkInfo.apkBuildCode,20);

        empty(50);

        JPanel infoPanel=new JPanel();
        infoPanel.setSize(500,500);


        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(new ImageIcon(apkInfo.apkLogoPath));
        infoPanel.add(iconLabel,BorderLayout.CENTER);

        jPanel.add(infoPanel);


        empty(30);

        //复制功能
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout());
        menuPanel.setSize(500, 200);

        JButton jButton = new JButton("复制apk");
        jButton.setSize(50, 50);
        jButton.addActionListener(e -> FileCopyUtil.copy(apkInfo.apkOutputDirectory + java.io.File.separator+ apkInfo.apkOutputFileName));
        menuPanel.add(jButton);

        JButton jButton2 = new JButton("打开所在文件夹");
        jButton2.setSize(50, 50);
        jButton2.addActionListener(e -> FileUtil.openDir(apkInfo.apkOutputDirectory));

        menuPanel.add(jButton2);

        jPanel.add(menuPanel);

        //增加间隙

        empty(100);
    }


}
