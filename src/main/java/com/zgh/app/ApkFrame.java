package com.zgh.app;

import com.zgh.app.util2.ShowUtil;

import javax.swing.*;
import java.awt.*;

public class ApkFrame extends JFrame {
    protected int width = 500;
    protected int height = 500;

    public ApkFrame() throws HeadlessException {
        setTitle("app发布助手");

        this.setSize(width, height);
        //屏幕中央显示
        this.setLocation(ShowUtil.showScreenCenter(this));
    }
}
