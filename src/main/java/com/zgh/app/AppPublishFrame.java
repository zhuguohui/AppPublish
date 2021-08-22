package com.zgh.app;

import com.zgh.app.listener.JFrameUtil;
import com.zgh.app.util2.FileUtil;
import com.zgh.app.util2.ImageCopyUtil;
import com.zgh.app.util2.QRCodeGenerator;
import com.zgh.app.util2.ShowUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AppPublishFrame extends ApkFrame {
    public AppPublishFrame() throws HeadlessException {
        super();
        //设置托盘隐藏功能
        JFrameUtil.miniTrayEnable(this);
        String codePath = createCode();



        JPanel jPanel=new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        jPanel.setSize(500,0);

        for(int i=0;i<3;i++) {

            JLabel label = new JLabel();
            label.setIcon(new ImageIcon(codePath));
            jPanel.add(label);

            //复制功能
            JPanel menuPanel=new JPanel();
            menuPanel.setLayout(new FlowLayout());
            menuPanel.setSize(500,200);

            JButton jButton = new JButton("复制");
            jButton.setSize(50,50);
            jButton.addActionListener(e -> ImageCopyUtil.copyImage(label));
            menuPanel.add(jButton);

            JButton jButton2 = new JButton("发送");
            jButton2.setSize(50,50);

            menuPanel.add(jButton2);

            jPanel.add(menuPanel);

            //增加间隙
            Label emptyLabel=new Label();
            emptyLabel.setSize(10,50);
            jPanel.add(emptyLabel);
        }

        //设置垂直滚动
        JScrollPane jScrollPane=new JScrollPane(jPanel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        jScrollPane.setSize(width,height);
        jScrollPane.setViewportView(jPanel);
        this.getContentPane().add(jScrollPane);


        //    jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setVisible(true);
    }
    private static String createCode(){
        List<String> titles = new ArrayList<>();
        titles.add("发布:拓尔思");
        titles.add("名称:读嘉App");
        titles.add("版本:1.4.4.901 (Build 72)");
        titles.add("更新于: 2021-08-21 12:50");
        String logoPath = "E:\\WORK\\GIT\\HangZhou\\JXRB\\JXRB_android\\app\\src\\main\\res\\drawable-xxxhdpi\\ic_logo.png";

        String content = "http://d.cc53.cn/e6da";
        try {
            return   QRCodeGenerator.encode(titles, content, logoPath, true, new FileUtil());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
