package com.sheepfish5.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

    static JFrame jFrame;

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
        rightPane.removeAll();
        rightPane.revalidate();
        rightPane.repaint();
        rightPane.setLayout(new GridBagLayout());
        JTextField studentName = new JTextField("", 30);
        JTextField studentBirthday = new JTextField("", 30);
        
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

        JLabel nameLabel = new JLabel("name:", SwingConstants.CENTER);
        JLabel genderLabel = new JLabel("gender:", SwingConstants.CENTER);
        JLabel birthdayLabel = new JLabel("birthday:", SwingConstants.CENTER);

        // set the hint message label
        JLabel hintMsg = new JLabel("提示信息：");
        // hintMsg.setAlignmentX(Component.LEFT_ALIGNMENT);

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

        GridBagConstraints constraints = new GridBagConstraints();
        /* nameLabel */
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weighty = 0.3;
        constraints.weightx = 0.3;  /* set the width */
        rightPane.add(nameLabel, constraints);
        /* studentName */
        constraints.gridx = 2;
        constraints.weighty = 0.3;
        constraints.weightx = 0.3;  /* set the width */
        constraints.fill = GridBagConstraints.HORIZONTAL;
        rightPane.add(studentName, constraints);
        constraints.weightx = 0;
        /* genderLabel */
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weighty = 0.3;
        constraints.weightx = 0.3;  /* set the width */
        rightPane.add(genderLabel, constraints);
        /* genderPanel */
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.weighty = 0.6;
        rightPane.add(genderPanel, constraints);
        /* birthdayLabel */
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.weighty = 0.3;
        rightPane.add(birthdayLabel, constraints);
        /* studentBirthday */
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.weighty = 0.3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        rightPane.add(studentBirthday, constraints);
        /* submit */
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.weighty = 0.3;
        rightPane.add(submit, constraints);
        /* 提示信息 */
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 3;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        constraints.weighty = 0.3;
        rightPane.add(hintMsg, constraints);

        /* column 0 padding */
        JPanel padding = new JPanel();
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.3;
        constraints.weighty = 0.3;
        rightPane.add(padding, constraints);
        constraints.gridy = 1;
        rightPane.add(new JPanel(), constraints);
        constraints.gridy = 2;
        rightPane.add(new JPanel(), constraints);
        constraints.gridy = 3;
        rightPane.add(new JPanel(), constraints);
    }

    private void setQueryByID() {
        rightPane.removeAll();
        rightPane.revalidate();
        rightPane.repaint();
        rightPane.setLayout(new GridBagLayout());
        JLabel idLabel = new JLabel("id:", SwingConstants.CENTER);
        JTextField studentID = new JTextField("", 30);
        JButton submit = new JButton("submit");
        JLabel hintMgs = new JLabel("提示信息: ", SwingConstants.LEFT);
        submit.addActionListener(e -> {
            String stuID = studentID.getText();
            // TODO: invocate query
            log.info("invocate queryByID: id={}", stuID);

            // TODO: based on the return value, set the hintMsg
        });

        GridBagConstraints constraints = new GridBagConstraints();
        /* idLabel */
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 0.15;
        constraints.weighty = 0.3;
        rightPane.add(idLabel, constraints);
        /* studentID */
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.weightx = 0.4;
        constraints.weighty = 0.3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        rightPane.add(studentID, constraints);
        /* submit */
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weightx = 0.15;
        constraints.weighty = 0.3;
        rightPane.add(submit, constraints);
        /* hintMsg */
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weighty = 0.3;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        rightPane.add(hintMgs, constraints);

        /* padding */
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.3;
        constraints.weighty = 0.3;
        rightPane.add(new JPanel(), constraints);
    }

    private void setQueryByName() {
        rightPane.removeAll();
        rightPane.revalidate();
        rightPane.repaint();
        rightPane.setLayout(new GridBagLayout());
        JLabel nameLabel = new JLabel("name:", SwingConstants.CENTER);
        JTextField studentName = new JTextField("", 30);
        JButton submit = new JButton("submit");
        JLabel hintMgs = new JLabel("提示信息: ", SwingConstants.LEFT);
        submit.addActionListener(e -> {
            String name = studentName.getText();
            // TODO: invocate query
            log.info("invocate queryByName: name={}", name);

            // TODO: based on the return value, set the hintMsg
        });

        GridBagConstraints constraints = new GridBagConstraints();
        /* idLabel */
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 0.15;
        constraints.weighty = 0.3;
        rightPane.add(nameLabel, constraints);
        /* studentName */
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.weightx = 0.4;
        constraints.weighty = 0.3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        rightPane.add(studentName, constraints);
        /* submit */
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weightx = 0.15;
        constraints.weighty = 0.3;
        rightPane.add(submit, constraints);
        /* hintMsg */
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weighty = 0.3;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        rightPane.add(hintMgs, constraints);

        /* padding */
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.3;
        constraints.weighty = 0.3;
        rightPane.add(new JPanel(), constraints);
    }

    private void setDeleteById() {
        rightPane.removeAll();
        rightPane.revalidate();
        rightPane.repaint();
        rightPane.setLayout(new GridBagLayout());
        JLabel idLabel = new JLabel("id:", SwingConstants.CENTER);
        JTextField studentID = new JTextField("", 30);
        JButton submit = new JButton("submit");
        JLabel hintMgs = new JLabel("提示信息: ", SwingConstants.LEFT);
        submit.addActionListener(e -> {
            String stuID = studentID.getText();
            // TODO: invocate deleteByID
            log.info("invocate deleteByID: id={}", stuID);

            // TODO: based on the return value, set the hintMsg
        });

        GridBagConstraints constraints = new GridBagConstraints();
        /* idLabel */
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 0.15;
        constraints.weighty = 0.3;
        rightPane.add(idLabel, constraints);
        /* studentID */
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.weightx = 0.4;
        constraints.weighty = 0.3;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        rightPane.add(studentID, constraints);
        /* submit */
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weightx = 0.15;
        constraints.weighty = 0.3;
        rightPane.add(submit, constraints);
        /* hintMsg */
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.weighty = 0.3;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        rightPane.add(hintMgs, constraints);

        /* padding */
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.3;
        constraints.weighty = 0.3;
        rightPane.add(new JPanel(), constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(addStudentString)) {
            /* addStudent */
            log.info("switch to addStudent panel");
            setAddStudentPane();
        } else if (e.getActionCommand().equals(queryByIDString)) {
            /* queryByID */
            log.info("switch to queryByID panel");
            setQueryByID();
        } else if (e.getActionCommand().equals(queryByNameString)) {
            /* queryByName */
            log.info("switch to queryByName panel");
            setQueryByName();
        } else {
            /* deleteByID */
            log.info("switch to deleteByID panel");
            setDeleteById();
        }
    }

    private static void createAndShowGUI() {
        jFrame = new JFrame("assign2");
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
