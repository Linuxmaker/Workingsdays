package com.linuxmaker.workingdays;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JWindow;

/**
 * Created by Andreas Günther, IT-LINUXMAKER
 * Date: 15.05.16
 */
public class SplashScreen extends JWindow implements Runnable {

    public void run() {
        setSize(365, 206);
        setLocationRelativeTo(null);
        setVisible(true);

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            dispose();
        }
        dispose();
    }

    public void paint(Graphics g) {
        BufferedImage splashImage;
        try {
            splashImage = ImageIO.read(Main.class.getResource("/resources/splashScreen.jpg"));
            g.drawImage(splashImage, 0, 0, this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
