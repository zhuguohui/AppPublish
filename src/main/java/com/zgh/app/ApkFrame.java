package com.zgh.app;

import com.zgh.app.bean.ApkInfo;
import com.zgh.app.util2.ShowUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class ApkFrame extends JFrame {
    protected int width = 500;
    protected int height = 500;
    protected final List<ApkInfo> apkInfos;
    protected JPanel jPanel;

    public ApkFrame(List<ApkInfo> apkInfos) throws HeadlessException {
        this.apkInfos = apkInfos;
        setTitle("app发布助手");
        this.setSize(width, height);
        //屏幕中央显示
        this.setLocation(ShowUtil.showScreenCenter(this));
        jPanel=new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        jPanel.setSize(500,0);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        buildUI();
        this.setVisible(true);
    }

    private void buildUI() {

        for (int i = 0; i < apkInfos.size(); i++) {

            ApkInfo apkInfo = apkInfos.get(i);

            buildItem(apkInfo);

        }

        //设置垂直滚动
        JScrollPane jScrollPane = new JScrollPane(jPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setViewportView(jPanel);
        this.getContentPane().add(jScrollPane);
    }

    abstract protected void buildItem(ApkInfo apkInfo);

    public void text(String info){
        text(info,15);
    }

    public void text(String info,int fontSize){
        JLabel label=new JLabel(info);
        label.setFont(font(fontSize));
        label.setAlignmentX(0.5f);
        jPanel.add(label,BorderLayout.CENTER);
    }
    public JLabel getText(String info,int fontSize){
        JLabel label=new JLabel(info);
        label.setFont(font(fontSize));
        return label;

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
