package com.shayrubach.view;


import com.shayrubach.controller.gui.TableController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuiMainPanel {

    private JFrame      mainFrame       = null;
    private JTabbedPane tabbedPane      = null;

    private JPanel      panelMain       = null;
    private JPanel      tabProjects     = null;
    private JPanel      tabAreas        = null;
    private JPanel      tabEng          = null;
    private JPanel      tabTopProjects  = null;
    private JPanel      tabTopEng       = null;

    private JTable      tableProjects   = null;
    private JTable      tableAreas      = null;
    private JTable      tableEng        = null;
    private JTable      tableTopProj    = null;
    private JTable      tableTopEng     = null;
    private JTable      tableMonitor;

    private JButton     buttonEditAreas = null;
    private JButton     buttonEditEng   = null;
    private JButton     buttonEditProject= null;

    private JScrollPane tabMonitor      = null;
    private JScrollPane jspProj         = null;
    private JScrollPane jspArea         = null;
    private JScrollPane jspEng          = null;
    private JScrollPane jspTopProj      = null;
    private JScrollPane jspTopEng       = null;
    private JScrollPane jspMonitor      = null;
    private JPanel tabEdit;
    private JButton buttonRemove;
    private JButton buttonAdd;
    private JPanel editPanel;
    private JComboBox cbChooseEntityType;
    private JButton buttonModify;
    private JComboBox cbChooseEntity;
    private JEditorPane editorPane1;
    private JEditorPane editorPane2;
    private JEditorPane editorPane3;
    private JEditorPane editorPane4;
    private JEditorPane editorPane5;
    private JEditorPane editorPane6;
    private JEditorPane editorPane7;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label9;
    private JLabel label2;
    private JLabel label3;
    private JLabel label1;
    private JEditorPane editorPane8;
    private JLabel label8;
    private JComboBox cbAdditiona;
    private JLabel label10;
    private JEditorPane edProMilestone;
    private JComboBox jcbChooseProject;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox jcbChooseStep;
    private JComboBox comboBox5;
    private JEditorPane edProRate;
    private JEditorPane edProDesc;
    private JEditorPane edProName;
    private JEditorPane edProTools;
    private JEditorPane edProDate;
    private JEditorPane editorPane10;
    private JEditorPane editorPane16;
    private JEditorPane editorPane19;
    private JEditorPane editorPane11;
    private JEditorPane editorPane12;
    private JEditorPane editorPane13;
    private JEditorPane editorPane14;
    private JEditorPane editorPane20;
    private JEditorPane editorPane21;
    private JEditorPane editorPane22;
    private JEditorPane editorPane23;
    private JEditorPane edProMoney;
    private JComboBox jcbChooseMilestone;
    private JComboBox comboBox1;
    private JButton addProjectButton;

    private ArrayList<TableController> controllers = new ArrayList<TableController>();


    public GuiMainPanel() {
        initMainFrame();
        initTables();
        initButtons();
    }

    private void initMainFrame() {
        mainFrame = new JFrame("MySQL Database Project - by Shay Rubach (c) ");
        mainFrame.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        mainFrame.setContentPane(this.getPanelMain());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        changeTheme();
        mainFrame.setVisible(true);

    }

    private void changeTheme() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(mainFrame);
    }

    private void initTables() {
        final String[] tbProjColumns = {"Name","Area","Description","Customers","Development Tools","Date Started","Milestones","ID"};
        final String[] tbEngColumns = {"First Name","Last Name","Projects","Rate","Phone","Age","Address","ID"};
        final String[] tbAreaColumns = {"Name","Specialty","ID"};
        final String[] tbMonitorColumns = {"Description","TimeStamp"};

        tableProjects.setBackground(new Color(255,255,255));
        tableEng.setBackground(new Color(255,255,255));
        tableAreas.setBackground(new Color(255,255,255));
        tableTopProj.setBackground(new Color(255,255,255));
        tableTopEng.setBackground(new Color(255,255,255));
        tableMonitor.setBackground(new Color(255,255,255));


        DefaultTableModel tbProjModel = new DefaultTableModel(null,tbProjColumns);
        DefaultTableModel tbEngModel = new DefaultTableModel(null,tbEngColumns);
        DefaultTableModel tbAreaModel = new DefaultTableModel(null,tbAreaColumns);
        DefaultTableModel tbTopProjModel = new DefaultTableModel(null,tbProjColumns);
        DefaultTableModel tbTopEngModel = new DefaultTableModel(null,tbEngColumns);
        DefaultTableModel tbMonitorModel = new DefaultTableModel(null,tbMonitorColumns);


        tableProjects.setModel(tbProjModel);
        tableAreas.setModel(tbAreaModel);
        tableEng.setModel(tbEngModel);
        tableMonitor.setModel(tbMonitorModel);

        tableTopProj.setModel(tbTopProjModel);
        tableTopEng.setModel(tbTopEngModel);

    }

    private void initButtons() {

        cbChooseEntityType.addItem("-- Select Entity --");
        cbChooseEntityType.addItem("PROJECTS");
        cbChooseEntityType.addItem("ENGINEERS");
        cbChooseEntityType.addItem("AREAS");

    }

    /* *************** */
    /* GETTERS SETTERS */
    /* *************** */

    public ArrayList<TableController> getControllers() {
        return controllers;
    }

    public JPanel getPanelMain() {
        return panelMain;
    }

    public void setPanelMain(JPanel panelMain) {
        this.panelMain = panelMain;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public JPanel getTabProjects() {
        return tabProjects;
    }

    public void setTabProjects(JPanel tabProjects) {
        this.tabProjects = tabProjects;
    }

    public JPanel getTabAreas() {
        return tabAreas;
    }

    public void setTabAreas(JPanel tabAreas) {
        this.tabAreas = tabAreas;
    }

    public JPanel getTabEng() {
        return tabEng;
    }

    public void setTabEng(JPanel tabEng) {
        this.tabEng = tabEng;
    }

    public JPanel getTabTopProjects() {
        return tabTopProjects;
    }

    public void setTabTopProjects(JPanel tabTopProjects) {
        this.tabTopProjects = tabTopProjects;
    }

    public JPanel getTabTopEng() {
        return tabTopEng;
    }

    public void setTabTopEng(JPanel tabTopEng) {
        this.tabTopEng = tabTopEng;
    }

    public JTable getTableProjects() {
        return tableProjects;
    }

    public void setTableProjects(JTable tableProjects) {
        this.tableProjects = tableProjects;
    }

    public JTable getTableAreas() {
        return tableAreas;
    }

    public void setTableAreas(JTable tableAreas) {
        this.tableAreas = tableAreas;
    }

    public JTable getTableEng() {
        return tableEng;
    }

    public void setTableEng(JTable tableEng) {
        this.tableEng = tableEng;
    }

    public JTable getTableTopProj() {
        return tableTopProj;
    }

    public void setTableTopProj(JTable tableTopProj) {
        this.tableTopProj = tableTopProj;
    }

    public JTable getTableTopEng() {
        return tableTopEng;
    }

    public void setTableTopEng(JTable tableTopEng) {
        this.tableTopEng = tableTopEng;
    }

    public JTable getTableMonitor() {
        return tableMonitor;
    }

    public void setTableMonitor(JTable tableMonitor) {
        this.tableMonitor = tableMonitor;
    }


}
