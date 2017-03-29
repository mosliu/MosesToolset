/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.liuxuan.UI.component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Moses
 */
public class JDesktop extends JDesktopPane {

    URL url = this.getClass().getResource("/images/background/004.jpg");
    private BufferedImage bakgroundimg;
    private static final Logger log = LoggerFactory.getLogger(JDesktop.class);
    private boolean scrollenable = false;

    public JDesktop(URL _url) {
        this(_url, false);
    }

    public JDesktop(URL _url, boolean scroll) {
        this.scrollenable = scroll;

        if (_url != null) {
            url = _url;
        }
        try {
            bakgroundimg = ImageIO.read(url);
        } catch (IOException ex) {
            log.error("", ex);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (scrollenable) {
            Dimension d = preferredSizeOfAllFrames();
            this.setPreferredSize(d);
            this.revalidate();
        }
    }

    /**
     * @return 返回最佳desktop尺寸..
     */
    public Dimension preferredSizeOfAllFrames() {
        JInternalFrame[] array = getAllFrames();

        int maxX = 0;
        int maxY = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[ i].isVisible()) {
                int cx;
                cx = array[i].getX();
                int x = cx + array[i].getWidth();
                if (x > maxX) {
                    maxX = x;
                }
                int cy;
                cy = array[i].getY();
                int y = cy + array[i].getHeight();
                if (y > maxY) {
                    maxY = y;
                }
            }
        }
        return new Dimension(maxX, maxY);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(bakgroundimg, 0, 0, this.getWidth(), this.getHeight(), null);
    }

//    @Override
//    public Dimension getPreferredSize() {
////        Dimension d= this.preferredSizeOfAllFrames();
////        double width = d.getWidth()>bakgroundimg.getWidth()?d.getWidth():bakgroundimg.getWidth();
////        double height = d.getHeight()>bakgroundimg.getHeight()?d.getHeight():bakgroundimg.getHeight();
////        
////        return new Dimension((int)width,(int) height);
//        return new Dimension(bakgroundimg.getWidth(), bakgroundimg.getHeight());
//    }
}
