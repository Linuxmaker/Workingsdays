package com.linuxmaker.workingdays;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.SimpleDateFormat;

/**
 * Created by @author Andreas Günther, IT-LINUXMAKER on 15.05.16.
 */
public class MainGui extends JFrame {
    private boolean saturdayWork;
    private String state;

    /**
     * Creates the form of MainGui
     */
    public MainGui() {
        initComponents();
    }

    /**
     * ActionListeners of the form
     */
    private void okButtonActionPerformed(ActionEvent evt) {
        try {
            WorkingDays workingDays = new WorkingDays();
            daysLabel.setVisible(true);
            daysLabel.setText("Anzahl der Arbeitstage");
            if (workSaturday.isSelected()) {
                saturdayWork = true;
            }
            state = (String) stateComboBox.getSelectedItem();
            resultLabel.setText(String.valueOf(workingDays.calculateDays(new SimpleDateFormat("dd.MM.yyyy").format(firstWorkingDayDateChooser.getDate()), new SimpleDateFormat("dd.MM.yyyy").format(lastWorkingDayDateChooser.getDate()), saturdayWork, state, (String) capacity.getSelectedItem())));
            resultLabel.setVisible(true);
            resultLabel2.setVisible(true);
        } catch (NullPointerException exp) {
            JOptionPane.showMessageDialog(null, "Tragen Sie bitte ein gültiges Datum ein!");
        }
    }

    private void resetButtonActionPerformed(ActionEvent evt) {
        lastWorkingDayDateChooser.setCalendar(null);
        firstWorkingDayDateChooser.setCalendar(null);
        daysLabel.setText(" ");
        resultLabel.setVisible(false);
        resultLabel2.setVisible(false);
        silvester.setSelected(false);
        workSaturday.setSelected(false);
        christmasEve.setSelected(false);
    }
    private void cancelButtonActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    private void menuItemExitActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    private void menuItemAboutActionPerformed(ActionEvent evt) {
        AboutFrame.main(null);
    }

    /**
     * Components of the form
     */
    private void initComponents() {
        /* Initialization of the variables */
        rootPanel = new JPanel();
        contentPanel = new JPanel();
        innerPanel = new JPanel();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();
        resetButton = new JButton();
        menuBar = new JMenuBar();
        file = new JMenu();
        help = new JMenu();
        menuItemExit = new JMenuItem();
        menuItemAbout = new JMenuItem();
        firstWorkingDayLabel = new JLabel();
        lastWorkingDayLabel = new JLabel();
        firstWorkingDayDateChooser = new JDateChooser();
        lastWorkingDayDateChooser = new JDateChooser();
        workSaturday = new JCheckBox();
        christmasEve = new JCheckBox();
        silvester = new JCheckBox();
        stateComboBox = new JComboBox<>();
        capacity = new JComboBox<>();
        capacityLabel = new JLabel("Auslastung");
        daysLabel = new JLabel();
        resultLabel = new JLabel();
        resultLabel2 = new JLabel("Manntage");
        /* End of initialization of the variables */

        //======== this ========
        Container rootContainer = getContentPane();
        rootContainer.setLayout(new BorderLayout());

        //======== rootPanel ========
        {
            rootPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
            rootPanel.setLayout(new BorderLayout());

            //======== contentPaneL ========
            {
                contentPanel.setLayout(new GridBagLayout());
                contentPanel.setBorder(new EmptyBorder(12, 12, 0, 12));
                ((GridBagLayout)contentPanel.getLayout()).columnWidths = new int[] {180, 50, 120, 0};
                ((GridBagLayout)contentPanel.getLayout()).rowHeights = new int[] {0, 0, 0, 0};
                ((GridBagLayout)contentPanel.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0};
                ((GridBagLayout)contentPanel.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0};

                firstWorkingDayLabel.setText("Erster Arbeitstag");
                firstWorkingDayLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
                contentPanel.add(firstWorkingDayLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));
                contentPanel.add(firstWorkingDayDateChooser, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));
                lastWorkingDayLabel.setText("Letzter Arbeitstag");
                lastWorkingDayLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
                contentPanel.add(lastWorkingDayLabel, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));
                contentPanel.add(lastWorkingDayDateChooser, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));

                //---- Inner Panel ----
                {
                    innerPanel.setLayout(new GridBagLayout());
                    innerPanel.setBorder(new TitledBorder(null, "Zusätzliche Einstellungen", TitledBorder.LEADING,
                            TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.PLAIN, 11), Color.blue));
                    ((GridBagLayout) innerPanel.getLayout()).columnWidths = new int[]{0, 0, 0, 0};
                    ((GridBagLayout) innerPanel.getLayout()).rowHeights = new int[]{0, 0, 0, 0};
                    ((GridBagLayout) innerPanel.getLayout()).columnWeights = new double[]{0.0, 0.0, 0.0, 1.0};
                    ((GridBagLayout) innerPanel.getLayout()).rowWeights = new double[]{0.0, 0.0, 0.0, 1.0};
                    workSaturday.setText("Samstags");
                    workSaturday.setToolTipText("Ankreuzen bei Samstagsarbeit");
                    workSaturday.setFont(new Font("Dialog", Font.PLAIN, 12));
                    innerPanel.add(workSaturday, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                    christmasEve.setText("Heiligabend");
                    christmasEve.setToolTipText("Ankreuzen bei Arbeit an Heiligabend");
                    christmasEve.setFont(new Font("Dialog", Font.PLAIN, 12));
                    innerPanel.add(christmasEve, new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                    silvester.setText("Silvester");
                    silvester.setToolTipText("Ankreuzen bei Arbeit an Silvester");
                    silvester.setFont(new Font("Dialog", Font.PLAIN, 12));
                    innerPanel.add(silvester, new GridBagConstraints(4, 2, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 5, 5), 0, 0));
                    stateComboBox.setFont(new Font("Dialog", Font.PLAIN, 11));
                    stateComboBox.setToolTipText("Wählen Sie das passende Bundesland");
                    stateComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                            "Baden-Württemberg",
                            "Bayern",
                            "Berlin",
                            "Brandenburg",
                            "Bremen",
                            "Hamburg",
                            "Hessen",
                            "Mecklenburg-Vorpommern",
                            "Niedersachsen",
                            "Nordrhein-Westfalen",
                            "Rheinland-Pfalz",
                            "Saarland",
                            "Sachsen",
                            "Sachsen-Anhalt",
                            "Schleswig-Holstein",
                            "Thüringen"
                    }));
                    innerPanel.add(stateComboBox, new GridBagConstraints(0, 0, 3, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 5, 5, 5), 0, 0));
                    capacity.setFont(new Font("Dialog", Font.PLAIN, 11));
                    capacity.setToolTipText("Auslastung pro Woche");
                    capacity.setModel(new DefaultComboBoxModel<>(new String[]{
                            "100%",
                            "80%",
                            "60%",
                            "40%",
                            "20%"
                    }));
                    innerPanel.add(capacity, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 5, 5, 5), 0, 0));
                    capacityLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
                    innerPanel.add(capacityLabel, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                            GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 5, 5, 5), 0, 0));
                }
                contentPanel.add(innerPanel, new GridBagConstraints(0, 3, 4, 1, 0.0, 0.0,
                        GridBagConstraints.PAGE_START, GridBagConstraints.BOTH,
                        new Insets(0, 0, 5, 5), 0, 0));
            }
            rootPanel.add(contentPanel, BorderLayout.CENTER);

            //======== Result ========

            {
                daysLabel.setText(" ");
                daysLabel.setFont(new Font("Dialog", Font.BOLD, 11));
                contentPanel.add(daysLabel, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));
                resultLabel.setFont(new Font("Dialog", Font.BOLD, 11));
                contentPanel.add(resultLabel, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));
                resultLabel2.setFont(new Font("Dialog", Font.BOLD, 11));
                resultLabel2.setVisible(false);
                contentPanel.add(resultLabel2, new GridBagConstraints(2, 5, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));
            }

            //======== ButtonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 12, 12, 12));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {85, 85, 50, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 0.0};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {0.0, 0.0, 0.0, 1.0};
                ((GridBagLayout)buttonBar.getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 1.0};

                okButton.setText("Berechnen");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        okButtonActionPerformed(evt);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                resetButton.setText("Reset");
                resetButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        resetButtonActionPerformed(evt);
                    }
                });
                buttonBar.add(resetButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                cancelButton.setText("Beenden");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        cancelButtonActionPerformed(evt);
                    }
                });
                buttonBar.add(cancelButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));
            }

            rootPanel.add(buttonBar, BorderLayout.SOUTH);

            //======== MenuBar ========
            {
                //======== Menu 1 ========
                {
                    file.setText("Datei");
                    //---- menuItems ----
                    menuItemExit.setText("Beenden");
                    menuItemExit.setMnemonic('B');
                    menuItemExit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            menuItemExitActionPerformed(evt);
                        }
                    });
                    file.add(menuItemExit);
                }
                menuBar.add(file);

                //======== Menu 2 ========
                {
                    help.setText("Über");
                    //---- menuItems ----
                    menuItemAbout.setText("Über ...");
                    menuItemAbout.setMnemonic('b');
                    menuItemAbout.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            menuItemAboutActionPerformed(evt);
                        }
                    });
                    help.add(menuItemAbout);
                }
                menuBar.add(help);
            }
            rootPanel.add(menuBar, BorderLayout.NORTH);

        }
        rootContainer.add(rootPanel, BorderLayout.CENTER);
        pack();
        setTitle("Berechnung der Arbeitstage");
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(getOwner());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainGui start = new MainGui();
                start.setVisible(true);
            }
        });
    }

    /* Declaration of the variables */
    private JPanel rootPanel;
    private JPanel contentPanel;
    private JPanel innerPanel;
    private JPanel buttonBar;
    private JMenuBar menuBar;
    private JMenu file;
    private JMenu help;
    private JMenuItem menuItemExit;
    private JMenuItem menuItemAbout;
    private JLabel firstWorkingDayLabel;
    private JDateChooser firstWorkingDayDateChooser;
    private JLabel lastWorkingDayLabel;
    private JDateChooser lastWorkingDayDateChooser;
    private JLabel daysLabel;
    private JLabel resultLabel;
    private JLabel resultLabel2;
    private JLabel capacityLabel;
    private JButton okButton;
    private JButton cancelButton;
    private JButton resetButton;
    private JCheckBox workSaturday;
    private JCheckBox christmasEve;
    private JCheckBox silvester;
    private JComboBox<String> stateComboBox;
    private JComboBox<String> capacity;
    /* End of Declaration of the variables */
}
