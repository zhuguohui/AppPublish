package com.zgh.app;

import com.zgh.app.bean.ApkInfo;
import com.zgh.app.util2.FileCopyUtil;
import com.zgh.app.util2.FileUtil;

import javax.swing.*;
import java.awt.*;

public class ApkBuildFrame extends ApkFrame {
    final ApkInfo[] apkInfos;
    JPanel jPanel;
    public ApkBuildFrame(ApkInfo... apkInfos) throws HeadlessException {
        super();
        this.apkInfos = apkInfos;

         jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.setSize(500, 0);


        for (int i = 0; i < apkInfos.length; i++) {

            ApkInfo apkInfo = apkInfos[i];

            //发布成功

            text(apkInfo.apkName,30);

            //版本名称
            text(apkInfo.customName,20);

            //版本号

            text("版本号:" + apkInfo.apkVersionName,20);

            text("BuildCod:"+apkInfo.apkBuildCode,20);

            empty(50);

            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(apkInfo.apkLogoPath));
            jPanel.add(label);

            empty(30);

            //复制功能
            JPanel menuPanel = new JPanel();
            menuPanel.setLayout(new FlowLayout());
            menuPanel.setSize(500, 200);

            JButton jButton = new JButton("复制");
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

        //设置垂直滚动
        JScrollPane jScrollPane = new JScrollPane(jPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setViewportView(jPanel);
        this.getContentPane().add(jScrollPane);


        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void text(String info){
        text(info,15);
    }

    public void text(String info,int fontSize){
        JLabel label=new JLabel(info);
        label.setFont(font(fontSize));
        jPanel.add(label);
    }

    public void empty(int height){
        Label emptyLabel = new Label();
        emptyLabel.setSize(width, height);
        jPanel.add(emptyLabel);
    }

    public Font font(int size){
        return   new Font("微软雅黑", 1, 20);
    }
}
