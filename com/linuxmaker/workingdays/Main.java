package com.linuxmaker.workingdays;

import java.io.FileNotFoundException;

/**
 * Created by Andreas Günther, IT-LINUXMAKER
 * Date: 15.05.16
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Thread splashThread = new Thread(new SplashScreen());
        splashThread.start();
        MainGui mainFrame = new MainGui();
        mainFrame.main(null);
    }
}
