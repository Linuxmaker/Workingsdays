package com.linuxmaker.workingdays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by @author Andreas Günther, IT-LINUXMAKER on 15.05.16.
 */
public class AboutFrame extends JFrame {
    private JButton closeButton = new JButton("Schliessen");
    /**
     * Create the form of AboutFrame
     */
    public AboutFrame() {
        initComponents();
    }

    /**
     * ActionListeners of the form
     */
    private void closeButtonActionPerformed(ActionEvent evt) {
        Container frame = closeButton.getParent();
        do {
            frame = frame.getParent();
        } while (!(frame instanceof JFrame));
        ((JFrame) frame).dispose();
    }

    /**
     * Components of the form
     */
    private void initComponents() {
        /* Declaration and initialization of the variables */
        JEditorPane aboutPane = new JEditorPane();
        JEditorPane licensePane = new JEditorPane();
        JScrollPane licenseScroll = new JScrollPane();
        StringBuffer buffer = new StringBuffer();
        /* End of declaration and initialization of the variables */

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(510, 350);
        setResizable(false);
        setTitle("Über WorkingDays");
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        /* Create the resources from Jar file */

        URL url = getClass().getResource("/resources/about.html");
        InputStream input = getClass().getResourceAsStream("/resources/license");
        InputStreamReader inputReader = new InputStreamReader(input);

        try {
            int len;
            while ((len = inputReader.read()) != -1) {
                buffer.append((char) len);
            }
            inputReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /* End of 'Create the resources from Jar file' */

        try {
            aboutPane.setPage(url);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        aboutPane.setPreferredSize(new Dimension(490, 120));
        aboutPane.setEditable(false);
        getContentPane().add(aboutPane);
        licensePane.setPreferredSize(new Dimension(490, 120));
        licensePane.setEditable(false);
        licensePane.setContentType("text/plain");
        licensePane.setText(buffer.toString());
        licenseScroll.setBorder(new TitledBorder(null, "Lizenz-Vereinbarung",
                TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION,
                new Font("Dialog", Font.PLAIN, 12), Color.blue));
        licenseScroll.setViewportView(licensePane);
        licenseScroll.setAutoscrolls(false);
        licenseScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        licenseScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(licenseScroll);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        getContentPane().add(closeButton);
        pack();
        setLocationRelativeTo(getOwner());
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AboutFrame frame = new AboutFrame();
                    frame.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
