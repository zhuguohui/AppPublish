package com.zgh.app.util2;

import java.awt.*;

public class ShowUtil {
    private static int screenWidth;
    private static int screenHeight;
    static {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         screenWidth = (int) screenSize.getWidth();//获得屏幕得宽
         screenHeight = (int) screenSize.getHeight();//获得屏幕得高
    }
    public static Point showScreenCenter(Component component){
        int width=component.getWidth();
        int height=component.getHeight();
        int x= (int) (0.5 *(screenWidth-width));
        int y= (int) (0.5*(screenHeight-height));
        return new Point(x,y);
    }
}
