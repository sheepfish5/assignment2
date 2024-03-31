package com.sheepfish5.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import com.sheepfish5.Client;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientUI extends JPanel
                      implements ActionListener {
    
    static String addStudentString = "addStudent";
    static String queryByIDString = "queryByID";
    static String queryByNameString = "queryByName";
    static String deleteByIDString = "deleteByID";

    JPanel rightPane;

    private JTextField studentName;

    private String addStudentGender = "female";

    public ClientUI() {
        super(new BorderLayout());

        // Create the radio buttons
        JRadioButton addStudentButton = new JRadioButton(addStudentString);
        addStudentButton.setMnemonic(KeyEvent.VK_A);
        addStudentButton.setActionCommand(addStudentString);
        addStudentButton.setSelected(true);

        JRadioButton queryByIDButton = new JRadioButton(queryByIDString);
        queryByIDButton.setMnemonic(KeyEvent.VK_I);
        queryByIDButton.setActionCommand(queryByIDString);

        JRadioButton queryByNameButton = new JRadioButton(queryByNameString);
        queryByNameButton.setMnemonic(KeyEvent.VK_N);
        queryByNameButton.setActionCommand(queryByNameString);

        JRadioButton deleteByIDButton = new JRadioButton(deleteByIDString);
        deleteByIDButton.setMnemonic(KeyEvent.VK_D);
        deleteByIDButton.setActionCommand(deleteByIDString);

        // Group the radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(addStudentButton);
        group.add(queryByIDButton);
        group.add(queryByNameButton);
        group.add(deleteByIDButton);

        // TODO: add a listener for each button
        addStudentButton.addActionListener(this);
        queryByIDButton.addActionListener(this);
        queryByNameButton.addActionListener(this);
        deleteByIDButton.addActionListener(this);

        // set the right side
        rightPane = new JPanel();
        rightPane.setPreferredSize(new Dimension(200, 200));
        setAddStudentPane();
        

        JPanel radioPanel = new JPanel(new GridLayout(0, 1));
        radioPanel.add(addStudentButton);
        radioPanel.add(queryByIDButton);
        radioPanel.add(queryByNameButton);
        radioPanel.add(deleteByIDButton);

        // border
        Border blankLine = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder title = BorderFactory.createTitledBorder(blankLine, "调用接口");
        title.setTitleJustification(TitledBorder.CENTER);
        radioPanel.setBorder(title);


        add(radioPanel, BorderLayout.LINE_START);
        add(rightPane, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private void setAddStudentPane() {
        rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.Y_AXIS));
        // innerGrid includes name, gender, birthday
        JPanel innerGrid = new JPanel(new GridLayout(3, 2));
        JTextField studentName = new JTextField(30);
        studentName.setSize(40, 20);
        JTextField studentBirthday = new JTextField(30);
        
        JRadioButton femaleButton = new JRadioButton("female");
        femaleButton.setSelected(true);
        femaleButton.addActionListener(e -> {
            addStudentGender = "female";
        });
        JRadioButton maleButton = new JRadioButton("male");
        maleButton.addActionListener(e -> {
            addStudentGender = "male";
        });
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(femaleButton);
        genderGroup.add(maleButton);
        JPanel genderPanel = new JPanel(new GridLayout(0, 1));
        genderPanel.add(femaleButton);
        genderPanel.add(maleButton);

        JLabel nameLabel = new JLabel("name:");
        nameLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JLabel genderLabel = new JLabel("gender:");
        genderLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        JLabel birthdayLabel = new JLabel("birthday:");
        birthdayLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        innerGrid.add(nameLabel);
        innerGrid.add(studentName);
        innerGrid.add(genderLabel);
        innerGrid.add(genderPanel);
        innerGrid.add(birthdayLabel);
        innerGrid.add(studentBirthday);
        // innerGrid.setPreferredSize(new Dimension(100, 100));
        innerGrid.setAlignmentX(Component.CENTER_ALIGNMENT);

        // set the hint message label
        JLabel hintMsg = new JLabel("");
        hintMsg.setAlignmentX(Component.LEFT_ALIGNMENT);

        // set submit button
        JButton submit = new JButton("submit");
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit.addActionListener(e -> {
            // get the value of studentName, birthday
            String name = studentName.getText();
            String birthday = studentBirthday.getText();
            // get gender from addStudentGender

            // TODO: invocat addStudentGender
            log.info("invocate addStudentGender: name={}, gender={}, birthday={}", 
                    name, addStudentGender, birthday);
            
            // TODO: based on the return value, set the hint message
        });

        JPanel padding = new JPanel();
        padding.setPreferredSize(new Dimension(200, 200));

        rightPane.add(innerGrid);
        rightPane.add(submit);
        rightPane.add(hintMsg);
        rightPane.add(padding);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private static void createAndShowGUI() {
        JFrame jFrame = new JFrame("assign2");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        URL favicon = Client.class.getResource("favicon16x16.ico");
        if (favicon != null) {
            jFrame.setIconImage(new ImageIcon(favicon).getImage());
        }

        // TODO: core code here
        ClientUI newContentPane = new ClientUI();
        newContentPane.setOpaque(true);
        jFrame.setContentPane(newContentPane);


        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    public static void startGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
