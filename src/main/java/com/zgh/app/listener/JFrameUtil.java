package com.zgh.app.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class JFrameUtil {


    public static void miniTrayEnable(JFrame mf) {  //窗口最小化到任务栏托盘

        //设置软件在托盘上显示的图标
        URL imageURL = JFrameUtil.class.getResource("/img/apk2.png");
        System.out.println(imageURL);
        if (imageURL == null) {
            System.out.println("图标找不到");
            return;
        }
        ImageIcon image = new ImageIcon(imageURL);
        Image img = image.getImage().getScaledInstance(63, 63, BufferedImage.SCALE_SMOOTH);

        SystemTray systemTray = SystemTray.getSystemTray(); //获得系统托盘的实例
        TrayIcon trayIcon = null;

        try {
            trayIcon = new TrayIcon(img, "apk发布助手");
            systemTray.add(trayIcon); //设置托盘的图标，*.gif与该类文件同一目录
            mf.setIconImage(img);
            trayIcon.setImageAutoSize(true);
        } catch (AWTException e2) {
            e2.printStackTrace();
        }

        //窗口最小化时软件dispose
        mf.addWindowListener(new WindowAdapter() {
            //图标化窗口时调用事件
            public void windowIconified(WindowEvent e) {
                mf.dispose(); //窗口最小化时dispose该窗口
            }
        });
        //使用JDialog 作为JPopupMenu载体
        JDialog popWindow = new JDialog();
        popWindow.setUndecorated(true);
        //popWindow作为JPopupMenu载体不需要多大的size
        popWindow.setSize(1, 1);

        //创建JPopupMenu
        //重写firePopupMenuWillBecomeInvisible
        //消失后将绑定的组件一起消失
        JPopupMenu pop = new JPopupMenu() {
            @Override
            public void firePopupMenuWillBecomeInvisible() {
                popWindow.setVisible(false);
                System.out.println("JPopupMenu不可见时绑定载体组件popWindow也不可见");
            }
        };
        pop.setSize(100, 30);
        //添加菜单选项
        JMenuItem exit = new JMenuItem("退出");
        pop.add(exit);
        exit.addActionListener(e -> {
            System.exit(0);
        });

        //双击托盘图标，软件正常显示
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                //左键点击
                if (e.getButton() == 1) {
                    //显示窗口
                    mf.setExtendedState(Frame.NORMAL); //正常化状态
                    mf.setVisible(true);
                } else if (e.getButton() == 3 && e.isPopupTrigger()) {
                    //右键点击弹出JPopupMenu绑定的载体以及JPopupMenu
                    popWindow.setLocation(e.getX() + 5, e.getY() - 5 - 30);
                    popWindow.setVisible(true);
                    pop.show(popWindow, 0, 0);
                }
            }
          /*  public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) //双击托盘窗口再现
                    //置此 frame 的状态。该状态表示为逐位掩码。
                    mf.setExtendedState(Frame.NORMAL); //正常化状态
                mf.setVisible(true);
            }*/
        });

    }

}
