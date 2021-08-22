package com.zgh.app.util2;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ImageCopyUtil {
    /**
     * 把转入的图片资源设置到剪切板上
     * @param image 可以是image 或者是BufferedImage类型
     */
    //类名.setImageClipboard(img);  //给剪切板设置图片型内容
    public static void setImage(Image image) {
        Images imgSel = new Images(image);
        //设置
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imgSel, null);
    }
    /**
     * 把转入的图片资源设置到剪切板上
     */
    //类名.setImageClipboard(img);  //给剪切板设置图片型内容
    public static void copyImage(JLabel label) {
        ImageIcon imageIcon= (ImageIcon) label.getIcon();
        Images imgSel = new Images(imageIcon.getImage());
        //设置
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imgSel, null);
    }

    public static class Images implements Transferable {
        private Image image; //得到图片或者图片流
        public Images(Image image) {this.image = image;}

        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{DataFlavor.imageFlavor};
        }

        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return DataFlavor.imageFlavor.equals(flavor);
        }

        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            if (!DataFlavor.imageFlavor.equals(flavor)) {throw new UnsupportedFlavorException(flavor);}
            return image;
        }
    }
}
