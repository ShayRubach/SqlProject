package com.shayrubach.view;


import com.shayrubach.controller.gui.tablectrls.TableController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class GuiMainPanel {

    private static final int E_RESET    = 0 ;
    private static final int E_MODIFY   = 1 ;
    private static final int E_REMOVE   = -1 ;
    private static final int E_NEW      = 2;

    public static final int PROJECT_ENTITY = 0;
    public static final int AREA_ENTITY = 1;
    public static final int ENGINEER_ENTITY = 2;

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
    private JComboBox cbChooseEntityType;
    private JEditorPane edProMilestone;
    private JComboBox jcbChooseProject;
    private JComboBox jcbChooseArea;
    private JComboBox jcbChooseEng;
    private JComboBox jcbChooseStep;
    private JComboBox jcbChooseEngPro;
    private JEditorPane edProRate;
    private JEditorPane edProDesc;
    private JEditorPane edProName;
    private JEditorPane edProTools;
    private JEditorPane edProDate;
    private JEditorPane editorPane10;
    private JEditorPane edAreaSpec;
    private JEditorPane edEngProjects;
    private JEditorPane edEngRate;
    private JEditorPane edEngAddress;
    private JEditorPane edEngArea;
    private JEditorPane edEngFname;
    private JEditorPane editorPane20;
    private JEditorPane editorPane21;
    private JEditorPane edAreaName;
    private JEditorPane edEngLname;
    private JEditorPane edProMoney;
    private JComboBox jcbChooseMilestone;

    private JButton applyButtonEng;
    private JPanel tabEditAll;
    private JRadioButton modifyProjectRadioButton;
    private JRadioButton newProjectRadioButton;
    private JRadioButton removeProjectRadioButton;
    private JRadioButton removeAreaRadioButton;
    private JRadioButton newEngineerRadioButton;
    private JRadioButton removeEngineerRadioButton;
    private JPanel tabEditPro;
    private JPanel tabEditArea;
    private JPanel tabEditEng;
    private JRadioButton modifyEngineerRadioButton;
    private JButton addEngPhoneBtn;
    private JRadioButton newAreaRadioButton;
    private JLabel LabelAreaId;
    private JLabel labelProId;
    private JLabel labelEngId;
    private JEditorPane edEngDate;
    private JEditorPane edEngAge;
    private JButton applyProButton;
    private JEditorPane edProMsDueDate;
    private JEditorPane edProCust;
    private JComboBox jcbChooseProArea;
    private JPanel tabUpcomingMilestones;
    private JPanel tabDevSteps;
    private JTable tableUpcomingMilestones;
    private JTable tableDevSteps;
    private JButton applyAreaButton;
    private JComboBox jcbProjNewArea;
    private JComboBox jcbNewArea;
    private JButton addNewAreaButton;
    private JRadioButton addNewAreaRadioButton;
    private JPanel tabGroups;
    private JScrollPane jspGroup;
    private JTable tableGroupsPro;
    private JComboBox jcbGroupPro;
    private JComboBox jcbGroupArea;
    private JLabel labelCurrDate;
    private JLabel labelTotalRevenue;
    private JRadioButton addProjectRadioButton;
    private JEditorPane edEngPhone;
    private JComboBox jcbEngJoinProj;
    private JRadioButton addPhoneNoRadioButton;
    private JComboBox jcbEngSelectArea;
    private JComboBox jcbEngRateProj;
    private JRadioButton rateProjectRadioButton;
    private JComboBox jcbEngRateValue;
    private JButton rateButtonEng;
    private JComboBox jcbGroupEngProName;
    private JTable tableGroupEngPro;
    private JTable tableGroupEngPhones;
    private JRadioButton addNewMilestoneRadioButton;


    private DefaultTableModel tbProjModel;
    private DefaultTableModel tbEngModel;
    private DefaultTableModel tbAreaModel;
    private DefaultTableModel tbTopProjModel;
    private DefaultTableModel tbTopEngModel;
    private DefaultTableModel tbMonitorModel;
    private DefaultTableModel tbMilestoneModel;
    private DefaultTableModel tbDevStepsModel;
    private DefaultTableModel tbGroupModel;
    private DefaultTableModel tbGroupEngProModel;
    private DefaultTableModel tbGroupEngPhonesModel;


    private ArrayList<TableController> controllers = new ArrayList<TableController>();


    public GuiMainPanel() {
        initMainFrame();
        initTables();
        initJsps();
        initCBoxes();
        initEditFields();
        initTabs();
    }

    private void initJsps() {
        //jspProj = new JScrollPane(tableProjects, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableProjects.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableAreas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableEng.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableTopProj.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableTopEng.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableDevSteps.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableUpcomingMilestones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableMonitor.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


    }

    private void initEditFields() {
        setComponentStates(E_RESET,null);
//        setComponentStates(E_MODIFY,tabEditPro);
    }

    private void setComponentStates(int ACTION, JPanel panel) {
        switch (ACTION){
            case E_RESET:
                // concat all components in edit tab to a single array
                ArrayList<Component> allEditComp = new ArrayList<>(Arrays.asList(tabEditPro.getComponents()));
                allEditComp.addAll(Arrays.asList(tabEditArea.getComponents()));
                allEditComp.addAll(Arrays.asList(tabEditEng.getComponents()));
                //get array reference like -> allEditComp.toArray();

                // disable all components but the radio buttons
                for(Component c : allEditComp){
                    if (!(c instanceof JRadioButton) && (!(c instanceof JLabel)) && (!(c instanceof JButton)))
                        c.setEnabled(false);
                }

            case E_MODIFY:
//                for(Component c : panel.getComponents()){
//                    if (!(c instanceof JRadioButton))
//                        c.setEnabled(false);
//                }
            case E_REMOVE:

        }
    }

    private void initCBoxes() {
        initProCB();
        initAreaCB();
        initEngCB();
        initGroupCB();
    }

    public void resetJcbItems(JComboBox cb, String entity){
        cb.removeAllItems();
        cb.addItem("-- Select "+entity+ " --");
        cb.addItem("");
    }



    private void initGroupCB() {
        jcbGroupEngProName.setEnabled(true);
        jcbGroupPro.setEnabled(true);
        jcbGroupArea.setEnabled(false);

        resetJcbItems(jcbGroupPro,"Project");
        resetJcbItems(jcbGroupArea,"Area");


        jcbGroupPro.addActionListener(e -> {
            jcbGroupArea.setEnabled(false);
            if(jcbGroupPro.getSelectedIndex() < 2) return;
            jcbGroupArea.setEnabled(true);
            getTbGroupModel().setNumRows(0);

        });

        jcbGroupArea.addActionListener(e -> {
            if(jcbGroupPro.getSelectedIndex() < 2) return;
            try {
                getControllers().get(0).getProjectEngs();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        });



        jcbGroupEngProName.addActionListener(e -> {
            if(jcbGroupEngProName.getSelectedIndex() < 2){
                return;
            }
                //load db to tables
            try {
                getControllers().get(0).getEngProject();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
    }

    private void initEngCB() {
        initEngCBText();


        jcbChooseEng.addActionListener(e -> {
            try {

                if (rateProjectRadioButton.isSelected()) {
                    resetJcbItems(jcbEngRateProj, "Project to rate");

                    getControllers().get(0).getEngProject();
                    int i = 1;
                    while (i <= 10) {
                        jcbEngRateValue.addItem(String.valueOf(i++));
                    }
                }

                if(modifyEngineerRadioButton.isSelected()){
                    ResultSet rs = getControllers().get(0).getEngData();
                    while(rs.next()){
                        edEngFname.setText(rs.getString(3));
                        edEngLname.setText(rs.getString(4));
                        edEngDate.setText(rs.getString(6));
                        edEngAddress.setText(rs.getString(5));
                    }
                }
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }


        });
    }


    private void initAreaCB() {
        initAreaCBText();
    }

    private void initProCB() {
        initProCBText();

        jcbChooseProject.addActionListener(e -> {
            //if a specific project was chosen
            if (jcbChooseProject.getSelectedIndex() > 1) {
                jcbChooseMilestone.setEnabled(false);
                try {
                    if (addNewMilestoneRadioButton.isSelected()) {
                        getControllers().get(0).getProjectDevStep();
                    }

                    if(modifyProjectRadioButton.isSelected()){
                        ResultSet rs = getControllers().get(0).getProjectData();
                        while (rs.next()) {
                            edProName.setText(rs.getString(3));
                            edProDesc.setText(rs.getString(4));
                            edProTools.setText(rs.getString(6));
                            edProCust.setText(rs.getString(5));
                            edProDate.setText(rs.getString(2));
                        }
                    }

                }
                 catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }



        });

        jcbChooseMilestone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jcbChooseMilestone.getSelectedIndex() == 2){
                }

                else if(jcbChooseMilestone.getSelectedIndex() > 4) {
                }
            }
        });

    }

    private void initProCBText() {
        jcbChooseProject.addItem("-- choose Project --");
        jcbChooseProject.addItem("  ");
        //send controller to fetch all project names from DB
        // add all projects to CB

        jcbChooseProArea = jcbChooseArea;

        jcbChooseStep.addItem("-- choose Development Step --");
        jcbChooseStep.addItem(" ");
        jcbChooseStep.addItem("Design and Architecture");
        jcbChooseStep.addItem("Development and Implementation");
        jcbChooseStep.addItem("Deliver Final Product");
        jcbChooseStep.addItem("Maintenance");
        jcbChooseStep.addItem("QA & Automation");

        jcbNewArea.addItem("-- choose Area --");
        jcbNewArea.addItem(" ");

        jcbChooseMilestone.addItem("-- choose/add Milestone --");
        jcbChooseMilestone.addItem(" ");
        jcbChooseMilestone.addItem("--> choose this to add new Milestone <--");
        jcbChooseMilestone.addItem(" ");
        jcbChooseMilestone.addItem(" ");
    }

    public void initEngCBText() {
        jcbChooseEng.addItem("-- choose Engineer --");
        jcbChooseEng.addItem(" ");

    }

    public void initAreaCBText() {
        jcbChooseArea.addItem("-- choose Area --");
        jcbChooseArea.addItem("  ");
    }

    private void initMainFrame() {
        mainFrame = new JFrame("MySQL Database Project - by Shay Rubach (c) ");
        mainFrame.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
        mainFrame.setContentPane(this.getPanelMain());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //changeTheme();
        mainFrame.setVisible(true);

    }

    private void changeTheme() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(mainFrame);
    }

    private void initTables() {

        final String[] tbProjColumns = {"Name","Description","Customers","Development Tools","Date Started","ID","Rate"};
        final String[] tbEngColumns = {"First Name","Last Name","Birth","Age","Address","ID","Area"};
        final String[] tbAreaColumns = {"Name","Specialty","ID"};
        final String[] tbMonitorColumns = {"Description","TimeStamp"};
        final String[] tbMilestonesColumns = {"Milestone","Date","Money Granted"};
        final String[] tbDevStepsColumns = {"State","Projects","Tools"};
        final String[] tbGroupColumns = {"First Name","Last Name","ID"};
        final String[] tbGroupEngProColumns  = {"Project","Rate"};
        final String[] tbGroupEngPhonesColumns   = {"Phone No."};

        tableProjects.setBackground(new Color(255,255,255));
        tableEng.setBackground(new Color(255,255,255));
        tableAreas.setBackground(new Color(255,255,255));
        tableTopProj.setBackground(new Color(255,255,255));
        tableTopEng.setBackground(new Color(255,255,255));
        tableMonitor.setBackground(new Color(255,255,255));
        tableUpcomingMilestones.setBackground(new Color(255,255,255));
        tableDevSteps.setBackground(new Color(255,255,255));
        tableGroupsPro.setBackground(new Color(255,255,255));
        tableGroupEngPro.setBackground(new Color(255,255,255));
        tableGroupEngPhones.setBackground(new Color(255,255,255));



        tbProjModel = new DefaultTableModel(null,tbProjColumns);
        tbEngModel = new DefaultTableModel(null,tbEngColumns);
        tbAreaModel = new DefaultTableModel(null,tbAreaColumns);
        tbTopProjModel = new DefaultTableModel(null,tbProjColumns);
        tbTopEngModel = new DefaultTableModel(null,tbEngColumns);
        tbMonitorModel = new DefaultTableModel(null,tbMonitorColumns);
        tbMilestoneModel = new DefaultTableModel(null,tbMilestonesColumns);
        tbDevStepsModel = new DefaultTableModel(null,tbDevStepsColumns);
        tbGroupModel = new DefaultTableModel(null,tbGroupColumns);
        tbGroupEngProModel = new DefaultTableModel(null,tbGroupEngProColumns);
        tbGroupEngPhonesModel = new DefaultTableModel(null,tbGroupEngPhonesColumns);


        tableProjects.setModel(tbProjModel);
        tableAreas.setModel(tbAreaModel);
        tableEng.setModel(tbEngModel);
        tableMonitor.setModel(tbMonitorModel);

        tableTopProj.setModel(tbTopProjModel);
        tableTopEng.setModel(tbTopEngModel);
        tableUpcomingMilestones.setModel(tbMilestoneModel);
        tableDevSteps.setModel(tbDevStepsModel);
        tableGroupsPro.setModel(tbGroupModel);
        tableGroupEngPro.setModel(tbGroupEngProModel);
        tableGroupEngPhones.setModel(tbGroupEngPhonesModel);


        /*
        tableEng.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()){
                    JComboBox cb = new JComboBox();

                    System.out.println("row pressed");
                    //get projects,rate and phones.

                    cb.addActionListener(e1 -> {
                        if(cb.getSelectedIndex() > 2){
                            System.out.println("BOOM");
                        }
                    });

                    getTableEng().getColumn("Projects").setCellEditor(new DefaultCellEditor(cb));
                    resetJcbItems(cb,"");
                    cb.addItem(tableEng.getValueAt(tableEng.getSelectedRow(), 0).toString());

                }

            }
        });
        */

    }

    private void applyFixedColumnsWidth(JTable table,int size) {
        int i = 0;
        while (i < table.getColumnModel().getColumnCount()){
            table.getColumnModel().getColumn(i).setMaxWidth(size);
            table.getColumnModel().getColumn(i).setPreferredWidth(size);
        }

    }

    private void initTabs() {
        setEditListeners();

        tabbedPane.addChangeListener(e -> {
            switch(tabbedPane.getSelectedIndex()){

                //top projects
                case 3:

                    break;
                //top eng
                case 4:

                    break;
                //upcoming milestones
                case 5:

                    break;
                //dev steps
                case 6:

                    break;
                //groups
                case 7:
                    PreparedStatement ps = null;
                    ResultSet rs = null;
                    getTbGroupModel().setNumRows(0);
                    getTbGroupEngPhonesModel().setNumRows(0);
                    getTbGroupEngProModel().setNumRows(0);

                    resetJcbItems(jcbGroupEngProName,"Engineer");
                    jcbGroupPro.setSelectedIndex(0);
                    jcbGroupArea.setSelectedIndex(0);


                    try {
                        getControllers().get(0).loadDbEngineers(ps,rs);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    break;

            }

        });
    }

    private void setEditListeners() {
        setEditPro();
        setEditArea();
        setEditEng();
    }

    private void setEditEng() {

        applyButtonEng.addActionListener(e -> {
            jcbEngSelectArea.setEnabled(false);
            //TODO 05: CHECK IF THE ENG AREA MATCHES THE PROJECTS AREA!!!!!!!!!!

            try {
                if (modifyEngineerRadioButton.isSelected()) {
                    getControllers().get(0).modifyEntity(ENGINEER_ENTITY);
                    cleanEdFields(tabEditEng);

                }

                if (newEngineerRadioButton.isSelected()) {
                    getControllers().get(0).addEntity(ENGINEER_ENTITY);
                }

                if (addProjectRadioButton.isSelected()) {
                    // .... add project
                    getControllers().get(0).addNewEngineerToProject();
                }

                if (addPhoneNoRadioButton.isSelected()) {
                    getControllers().get(0).addNewPhoneNoToEng();
                }

                if (removeEngineerRadioButton.isSelected()) {
                    getControllers().get(0).removeEntity(ENGINEER_ENTITY);
                }

                unselectEngRadioButtons();
            }
             catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        rateButtonEng.addActionListener(e -> {

            if(rateProjectRadioButton.isSelected()){
                try {
                    getControllers().get(0).rateAProject();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            unselectEngRadioButtons();
        });
        modifyEngineerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jcbEngSelectArea.setEnabled(false);modifyEngineerRadioButton.setSelected(true);
                newEngineerRadioButton.setSelected(false);


                removeEngineerRadioButton.setSelected(false);
                addPhoneNoRadioButton.setSelected(false);
                addProjectRadioButton.setSelected(false);
                rateProjectRadioButton.setSelected(false);
                jcbEngSelectArea.setEnabled(false);
                jcbEngRateValue.setEnabled(false);
                jcbEngRateProj.setEnabled(false);
                jcbEngJoinProj.setEnabled(false);

                jcbChooseEng.setEnabled(true);
                enableEdFields(tabEditEng);
                getControllers().get(0).getEntityDetails(ENGINEER_ENTITY,getJcbChooseEng());

            }
        });

        newEngineerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyEngineerRadioButton.setSelected(false);
                newEngineerRadioButton.setSelected(true);
                removeEngineerRadioButton.setSelected(false);
                addPhoneNoRadioButton.setSelected(false);
                addProjectRadioButton.setSelected(false);
                rateProjectRadioButton.setSelected(false);
                jcbEngSelectArea.setEnabled(true);

                jcbChooseEng.setEnabled(false);
                cleanEdFields(tabEditEng);
                enableEdFields(tabEditEng);
                try {
                    resetJcbItems(jcbEngSelectArea,"Area");
                    getControllers().get(0).getAvailableAreas();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        removeEngineerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyEngineerRadioButton.setSelected(false);
                newEngineerRadioButton.setSelected(false);
                removeEngineerRadioButton.setSelected(true);
                addPhoneNoRadioButton.setSelected(false);
                addProjectRadioButton.setSelected(false);
                rateProjectRadioButton.setSelected(false);

                jcbEngSelectArea.setEnabled(false);
                jcbChooseEng.setEnabled(true);
                cleanEdFields(tabEditEng);
            }
        });

        addProjectRadioButton.addActionListener(e -> {
            //resetJcbItems(jcbEngJoinProj,"Project to join to");

            jcbEngJoinProj.setEnabled(true);
            modifyEngineerRadioButton.setSelected(false);
            newEngineerRadioButton.setSelected(false);
            removeEngineerRadioButton.setSelected(false);
            addPhoneNoRadioButton.setSelected(false);
            rateProjectRadioButton.setSelected(false);

            getJcbChooseEng().setEnabled(true);
        });


        rateProjectRadioButton.addActionListener(e -> {

            jcbChooseEng.setSelectedIndex(0);
            jcbEngRateValue.setSelectedIndex(0);
            jcbChooseEng.setEnabled(true);

            jcbEngRateProj.setEnabled(true);
            resetJcbItems(jcbEngRateValue,"Rate (1 - 10 )");
            resetJcbItems(jcbEngRateProj,"Project to rate");
            unselectEngRadioButtons();
            rateProjectRadioButton.setSelected(true);
        });

        jcbEngRateProj.addActionListener(e -> {
            if(jcbEngRateProj.getSelectedIndex() < 2) return;

            jcbEngRateValue.setEnabled(true);


        });

        addPhoneNoRadioButton.addActionListener(e -> {
            unselectEngRadioButtons();
            addPhoneNoRadioButton.setSelected(true);
            jcbChooseEng.setEnabled(true);
            edEngPhone.setEditable(true);
            edEngPhone.setEnabled(true);
        });
    }

    private void unselectEngRadioButtons() {
        for(Component c : tabEditEng.getComponents()){
            if(c instanceof JRadioButton)
                ((JRadioButton) c).setSelected(false);
        }

    }

    private void setEditArea() {
        applyAreaButton.setEnabled(true);

        newAreaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newAreaRadioButton.setSelected(true);
                removeAreaRadioButton.setSelected(false);
                jcbChooseArea.setEnabled(false);
                edAreaName.setEditable(true);
                edAreaName.setEnabled(true);
                edAreaSpec.setEditable(true);
                edAreaSpec.setEnabled(true);
            }
        });

        removeAreaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAreaRadioButton.setSelected(true);
                newAreaRadioButton.setSelected(false);
                jcbChooseArea.setEnabled(true);
                getEdAreaName().setText("");
                getEdAreaSpec().setText("");
            }
        });

        applyAreaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(newAreaRadioButton.isSelected()){
                        controllers.get(0).addEntity(AREA_ENTITY);
                    }
                    if(removeAreaRadioButton.isSelected()){
                        controllers.get(0).removeEntity(AREA_ENTITY);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });
    }

    private void setEditPro() {


        modifyProjectRadioButton.addActionListener(e -> {
            setComponentStates(E_RESET,tabEditPro);
            modifyProjectRadioButton.setSelected(true);
            newProjectRadioButton.setSelected(false);
            removeProjectRadioButton.setSelected(false);
            addNewMilestoneRadioButton.setSelected(false);

            jcbChooseProject.setEnabled(true);
            enableProFields(E_MODIFY);
            edProRate.setEnabled(false);

        });

        newProjectRadioButton.addActionListener(e -> {
            setComponentStates(E_RESET,tabEditPro);
            newProjectRadioButton.setSelected(true);
            modifyProjectRadioButton.setSelected(false);
            removeProjectRadioButton.setSelected(false);
            addNewMilestoneRadioButton.setSelected(false);

            jcbChooseProject.setEnabled(false);
            jcbChooseProArea.setEnabled(true);

            enableProFields(E_NEW);
            edProRate.setEnabled(false);

        });

        removeProjectRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setComponentStates(E_RESET,tabEditPro);
                removeProjectRadioButton.setSelected(true);
                modifyProjectRadioButton.setSelected(false);
                newProjectRadioButton.setSelected(false);
                addNewMilestoneRadioButton.setSelected(false);

                jcbChooseProject.setEnabled(true);
                enableProFields(E_REMOVE);
                edProRate.setEnabled(false);


            }
        });

        addNewAreaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProjectRadioButton.setSelected(false);
                modifyProjectRadioButton.setSelected(false);
                newProjectRadioButton.setSelected(false);

                jcbChooseProject.setEnabled(true);
                jcbNewArea.setEnabled(true);

                try {
                    controllers.get(0).getAvailableAreas();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        addNewAreaButton.addActionListener(e -> {
            try {
                controllers.get(0).addNewAreaToProject();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        addNewMilestoneRadioButton.addActionListener(e -> {
            if(addNewMilestoneRadioButton.isSelected()){
                edProMilestone.setText("milestone description");
                edProMsDueDate.setText(labelCurrDate.getText().substring(6).replace('/','.'));
                edProMoney.setText("24500");

                jcbChooseProject.setEnabled(true);
                edProMilestone.setEnabled(true);
                edProMsDueDate.setEnabled(true);
                edProMoney.setEnabled(true);

                newProjectRadioButton.setSelected(false);
                modifyProjectRadioButton.setSelected(false);
                removeProjectRadioButton.setSelected(false);
            }
        });

        applyProButton.addActionListener(e -> {

            try {
                if(newProjectRadioButton.isSelected()){
                    controllers.get(0).addEntity(PROJECT_ENTITY);
                }
                if(modifyProjectRadioButton.isSelected()){
                    //TODO: add project_modify
                }
                if(removeProjectRadioButton.isSelected() && jcbChooseProject.getSelectedIndex() > 1){
                    controllers.get(0).removeEntity(PROJECT_ENTITY);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }


        });

    }

    public void enableProFields(int MODE){

        //turn all fields on
        for(Component c : tabEditPro.getComponents()) {
            if(c instanceof JEditorPane)
                c.setEnabled(true);
        }

        switch (MODE) {
            case E_NEW:
                jcbChooseStep.setSelectedIndex(2);
                jcbChooseMilestone.setSelectedIndex(2);
                jcbChooseMilestone.setEnabled(false);

                setNewProTextHint(E_NEW,tabEditPro);

                break;
            case E_MODIFY:

                break;
            case E_REMOVE:

                break;
        }
    }

    private void cleanEdFields(JPanel tab) {
        for (Component ep : tab.getComponents()) {
            if (ep instanceof JEditorPane) {
                JEditorPane newEp = (JEditorPane) ep;
                ((JEditorPane) ep).setText("");
            }
        }
    }

    private void enableEdFields(JPanel tab) {
        for (Component ep : tab.getComponents()) {
            if (ep instanceof JEditorPane) {
                JEditorPane newEp = (JEditorPane) ep;
                ((JEditorPane) ep).setEnabled(true);
                ((JEditorPane) ep).setEditable(true);

            }
        }
    }



    private void setNewProTextHint(int MODE,JPanel tab) {
        for(Component ep : tab.getComponents()) {

            if (ep instanceof JEditorPane) {
                JEditorPane newEp = (JEditorPane) ep;
                ((JEditorPane)ep).setText("");
                if(MODE == E_NEW){
                    ep.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            ((JEditorPane) ep).setText("");
                        }
                        @Override
                        public void mousePressed(MouseEvent e) {}
                        @Override
                        public void mouseReleased(MouseEvent e) {}
                        @Override
                        public void mouseEntered(MouseEvent e) {}
                        @Override
                        public void mouseExited(MouseEvent e) {}
                    });

//                    edProRate.setText("0");
//                    edProName.setText("Project_X");
//                    edProDesc.setText("this project is bound to investigate the X letter");
                    edProDate.setText("24.10.2017");
                    edProCust.setText("James Dean,Google");
                    edProTools.setText("Github,VisualStudio,VMWare,JIRA");
//                    edProMilestone.setText("finish the design in 14 days from kick off");
//                    edProMoney.setText("12000");
                }
            }
        }
    }

    // getters & setters

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

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public JButton getButtonEditAreas() {
        return buttonEditAreas;
    }

    public void setButtonEditAreas(JButton buttonEditAreas) {
        this.buttonEditAreas = buttonEditAreas;
    }

    public JButton getButtonEditEng() {
        return buttonEditEng;
    }

    public void setButtonEditEng(JButton buttonEditEng) {
        this.buttonEditEng = buttonEditEng;
    }

    public JButton getButtonEditProject() {
        return buttonEditProject;
    }

    public void setButtonEditProject(JButton buttonEditProject) {
        this.buttonEditProject = buttonEditProject;
    }

    public JScrollPane getTabMonitor() {
        return tabMonitor;
    }

    public void setTabMonitor(JScrollPane tabMonitor) {
        this.tabMonitor = tabMonitor;
    }

    public JScrollPane getJspProj() {
        return jspProj;
    }

    public void setJspProj(JScrollPane jspProj) {
        this.jspProj = jspProj;
    }

    public JScrollPane getJspArea() {
        return jspArea;
    }

    public void setJspArea(JScrollPane jspArea) {
        this.jspArea = jspArea;
    }

    public JScrollPane getJspEng() {
        return jspEng;
    }

    public void setJspEng(JScrollPane jspEng) {
        this.jspEng = jspEng;
    }

    public JScrollPane getJspTopProj() {
        return jspTopProj;
    }

    public void setJspTopProj(JScrollPane jspTopProj) {
        this.jspTopProj = jspTopProj;
    }

    public JScrollPane getJspTopEng() {
        return jspTopEng;
    }

    public void setJspTopEng(JScrollPane jspTopEng) {
        this.jspTopEng = jspTopEng;
    }

    public JScrollPane getJspMonitor() {
        return jspMonitor;
    }

    public void setJspMonitor(JScrollPane jspMonitor) {
        this.jspMonitor = jspMonitor;
    }

    public JComboBox getCbChooseEntityType() {
        return cbChooseEntityType;
    }

    public void setCbChooseEntityType(JComboBox cbChooseEntityType) {
        this.cbChooseEntityType = cbChooseEntityType;
    }

    public JEditorPane getEdProMilestone() {
        return edProMilestone;
    }

    public void setEdProMilestone(JEditorPane edProMilestone) {
        this.edProMilestone = edProMilestone;
    }

    public JComboBox getJcbChooseProject() {
        return jcbChooseProject;
    }

    public void setJcbChooseProject(JComboBox jcbChooseProject) {
        this.jcbChooseProject = jcbChooseProject;
    }

    public JComboBox getJcbChooseArea() {
        return jcbChooseArea;
    }

    public void setJcbChooseArea(JComboBox jcbChooseArea) {
        this.jcbChooseArea = jcbChooseArea;
    }

    public JComboBox getJcbChooseEng() {
        return jcbChooseEng;
    }

    public void setJcbChooseEng(JComboBox jcbChooseEng) {
        this.jcbChooseEng = jcbChooseEng;
    }

    public JComboBox getJcbChooseStep() {
        return jcbChooseStep;
    }

    public void setJcbChooseStep(JComboBox jcbChooseStep) {
        this.jcbChooseStep = jcbChooseStep;
    }

    public JComboBox getJcbChooseEngPro() {
        return jcbChooseEngPro;
    }

    public void setJcbChooseEngPro(JComboBox jcbChooseEngPro) {
        this.jcbChooseEngPro = jcbChooseEngPro;
    }

    public JEditorPane getEdProRate() {
        return edProRate;
    }

    public void setEdProRate(JEditorPane edProRate) {
        this.edProRate = edProRate;
    }

    public JEditorPane getEdProDesc() {
        return edProDesc;
    }

    public void setEdProDesc(JEditorPane edProDesc) {
        this.edProDesc = edProDesc;
    }

    public JEditorPane getEdProName() {
        return edProName;
    }

    public void setEdProName(JEditorPane edProName) {
        this.edProName = edProName;
    }

    public JEditorPane getEdProTools() {
        return edProTools;
    }

    public void setEdProTools(JEditorPane edProTools) {
        this.edProTools = edProTools;
    }

    public JEditorPane getEdProDate() {
        return edProDate;
    }

    public void setEdProDate(JEditorPane edProDate) {
        this.edProDate = edProDate;
    }

    public JEditorPane getEditorPane10() {
        return editorPane10;
    }

    public void setEditorPane10(JEditorPane editorPane10) {
        this.editorPane10 = editorPane10;
    }

    public JEditorPane getEdAreaSpec() {
        return edAreaSpec;
    }

    public void setEdAreaSpec(JEditorPane edAreaSpec) {
        this.edAreaSpec = edAreaSpec;
    }

    public JEditorPane getEdEngProjects() {
        return edEngProjects;
    }

    public void setEdEngProjects(JEditorPane edEngProjects) {
        this.edEngProjects = edEngProjects;
    }

    public JEditorPane getEdEngRate() {
        return edEngRate;
    }

    public void setEdEngRate(JEditorPane edEngRate) {
        this.edEngRate = edEngRate;
    }

    public JEditorPane getEdEngAddress() {
        return edEngAddress;
    }

    public void setEdEngAddress(JEditorPane edEngAddress) {
        this.edEngAddress = edEngAddress;
    }

    public JEditorPane getEdEngArea() {
        return edEngArea;
    }

    public void setEdEngArea(JEditorPane edEngArea) {
        this.edEngArea = edEngArea;
    }

    public JEditorPane getEdEngFname() {
        return edEngFname;
    }

    public void setEdEngFname(JEditorPane edEngFname) {
        this.edEngFname = edEngFname;
    }

    public JEditorPane getEditorPane20() {
        return editorPane20;
    }

    public void setEditorPane20(JEditorPane editorPane20) {
        this.editorPane20 = editorPane20;
    }

    public JEditorPane getEditorPane21() {
        return editorPane21;
    }

    public void setEditorPane21(JEditorPane editorPane21) {
        this.editorPane21 = editorPane21;
    }

    public JEditorPane getEdAreaName() {
        return edAreaName;
    }

    public void setEdAreaName(JEditorPane edAreaName) {
        this.edAreaName = edAreaName;
    }

    public JEditorPane getEdEngLname() {
        return edEngLname;
    }

    public void setEdEngLname(JEditorPane edEngLname) {
        this.edEngLname = edEngLname;
    }

    public JEditorPane getEdProMoney() {
        return edProMoney;
    }

    public void setEdProMoney(JEditorPane edProMoney) {
        this.edProMoney = edProMoney;
    }

    public JComboBox getJcbChooseMilestone() {
        return jcbChooseMilestone;
    }

    public void setJcbChooseMilestone(JComboBox jcbChooseMilestone) {
        this.jcbChooseMilestone = jcbChooseMilestone;
    }

    public JButton getApplyButtonEng() {
        return applyButtonEng;
    }

    public void setApplyButtonEng(JButton applyButtonEng) {
        this.applyButtonEng = applyButtonEng;
    }

    public JPanel getTabEditAll() {
        return tabEditAll;
    }

    public void setTabEditAll(JPanel tabEditAll) {
        this.tabEditAll = tabEditAll;
    }

    public JRadioButton getModifyProjectRadioButton() {
        return modifyProjectRadioButton;
    }

    public void setModifyProjectRadioButton(JRadioButton modifyProjectRadioButton) {
        this.modifyProjectRadioButton = modifyProjectRadioButton;
    }

    public JRadioButton getNewProjectRadioButton() {
        return newProjectRadioButton;
    }

    public void setNewProjectRadioButton(JRadioButton newProjectRadioButton) {
        this.newProjectRadioButton = newProjectRadioButton;
    }

    public JRadioButton getRemoveProjectRadioButton() {
        return removeProjectRadioButton;
    }

    public void setRemoveProjectRadioButton(JRadioButton removeProjectRadioButton) {
        this.removeProjectRadioButton = removeProjectRadioButton;
    }

    public JRadioButton getRemoveAreaRadioButton() {
        return removeAreaRadioButton;
    }

    public void setRemoveAreaRadioButton(JRadioButton removeAreaRadioButton) {
        this.removeAreaRadioButton = removeAreaRadioButton;
    }

    public JRadioButton getNewEngineerRadioButton() {
        return newEngineerRadioButton;
    }

    public void setNewEngineerRadioButton(JRadioButton newEngineerRadioButton) {
        this.newEngineerRadioButton = newEngineerRadioButton;
    }

    public JRadioButton getRemoveEngineerRadioButton() {
        return removeEngineerRadioButton;
    }

    public void setRemoveEngineerRadioButton(JRadioButton removeEngineerRadioButton) {
        this.removeEngineerRadioButton = removeEngineerRadioButton;
    }

    public JPanel getTabEditPro() {
        return tabEditPro;
    }

    public void setTabEditPro(JPanel tabEditPro) {
        this.tabEditPro = tabEditPro;
    }

    public JPanel getTabEditArea() {
        return tabEditArea;
    }

    public void setTabEditArea(JPanel tabEditArea) {
        this.tabEditArea = tabEditArea;
    }

    public JPanel getTabEditEng() {
        return tabEditEng;
    }

    public void setTabEditEng(JPanel tabEditEng) {
        this.tabEditEng = tabEditEng;
    }

    public JRadioButton getModifyEngineerRadioButton() {
        return modifyEngineerRadioButton;
    }

    public void setModifyEngineerRadioButton(JRadioButton modifyEngineerRadioButton) {
        this.modifyEngineerRadioButton = modifyEngineerRadioButton;
    }

    public JButton getAddEngPhoneBtn() {
        return addEngPhoneBtn;
    }

    public void setAddEngPhoneBtn(JButton addEngPhoneBtn) {
        this.addEngPhoneBtn = addEngPhoneBtn;
    }

    public JRadioButton getNewAreaRadioButton() {
        return newAreaRadioButton;
    }

    public void setNewAreaRadioButton(JRadioButton newAreaRadioButton) {
        this.newAreaRadioButton = newAreaRadioButton;
    }

    public JLabel getLabelAreaId() {
        return LabelAreaId;
    }

    public void setLabelAreaId(JLabel labelAreaId) {
        LabelAreaId = labelAreaId;
    }

    public JLabel getLabelProId() {
        return labelProId;
    }

    public void setLabelProId(JLabel labelProId) {
        this.labelProId = labelProId;
    }

    public JLabel getLabelEngId() {
        return labelEngId;
    }

    public void setLabelEngId(JLabel labelEngId) {
        this.labelEngId = labelEngId;
    }

    public JEditorPane getEdEngDate() {
        return edEngDate;
    }

    public void setEdEngDate(JEditorPane edEngDate) {
        this.edEngDate = edEngDate;
    }

    public JEditorPane getEdEngAge() {
        return edEngAge;
    }

    public void setEdEngAge(JEditorPane edEngAge) {
        this.edEngAge = edEngAge;
    }

    public JButton getApplyProButton() {
        return applyProButton;
    }

    public void setApplyProButton(JButton applyProButton) {
        this.applyProButton = applyProButton;
    }

    public void setControllers(ArrayList<TableController> controllers) {
        this.controllers = controllers;
    }

    public DefaultTableModel getTbProjModel() {
        return tbProjModel;
    }

    public void setTbProjModel(DefaultTableModel tbProjModel) {
        this.tbProjModel = tbProjModel;
    }

    public DefaultTableModel getTbEngModel() {
        return tbEngModel;
    }

    public void setTbEngModel(DefaultTableModel tbEngModel) {
        this.tbEngModel = tbEngModel;
    }

    public DefaultTableModel getTbAreaModel() {
        return tbAreaModel;
    }

    public void setTbAreaModel(DefaultTableModel tbAreaModel) {
        this.tbAreaModel = tbAreaModel;
    }

    public DefaultTableModel getTbTopProjModel() {
        return tbTopProjModel;
    }

    public void setTbTopProjModel(DefaultTableModel tbTopProjModel) {
        this.tbTopProjModel = tbTopProjModel;
    }

    public DefaultTableModel getTbTopEngModel() {
        return tbTopEngModel;
    }

    public void setTbTopEngModel(DefaultTableModel tbTopEngModel) {
        this.tbTopEngModel = tbTopEngModel;
    }

    public DefaultTableModel getTbMonitorModel() {
        return tbMonitorModel;
    }

    public void setTbMonitorModel(DefaultTableModel tbMonitorModel) {
        this.tbMonitorModel = tbMonitorModel;
    }

    public JEditorPane getEdProMsDueDate() {
        return edProMsDueDate;
    }

    public void setEdProMsDueDate(JEditorPane edProMsDueDate) {
        this.edProMsDueDate = edProMsDueDate;
    }

    public JEditorPane getEdProCust() {
        return edProCust;
    }

    public void setEdProCust(JEditorPane edProCust) {
        this.edProCust = edProCust;
    }

    public JComboBox getJcbChooseProArea() {
        return jcbChooseProArea;
    }

    public void setJcbChooseProArea(JComboBox jcbChooseProArea) {
        this.jcbChooseProArea = jcbChooseProArea;
    }
    public JButton getApplyAreaButton() {
        return applyAreaButton;
    }


    //TODO 10: turn these fill func into 1 with switch
    public void fillProjectTable(String[] formedProjectRow) {
        getTbProjModel().addRow(formedProjectRow);

    }

    public void fillAreaTable(String[] formedAreaRow) {
        getTbAreaModel().addRow(formedAreaRow);
    }

    public void fillEngTable(Object[] formedRow){
        getTbEngModel().addRow(formedRow);
    }

    public JComboBox getJcbNewArea() {
        return jcbNewArea;
    }

    public void updateBoxes() {
        //update project boxes
        for (int i = 0; i < tableProjects.getRowCount(); i++) {

        }

        //update area boxes
        //update eng boxes
        //update milestone boxes
        //update phone boxes


    }

    public JPanel getTabGroups() {
        return tabGroups;
    }

    public JTable getTableGroupsPro() {
        return tableGroupsPro;
    }

    public JComboBox getJcbGroupPro() {
        return jcbGroupPro;
    }

    public JComboBox getJcbGroupArea() {
        return jcbGroupArea;
    }

    public JComboBox getJcbEngSelectArea() {
        return jcbEngSelectArea;
    }

    public String getAreaIdByName(String s) {
        int ID_COLUMN_INDEX = 2;
        int NAME_COLUMN_INDEX = 0;
        int rowCount = getTableAreas().getRowCount();

        for (int i = 0 ; i < rowCount; ++i) {
            if(getTbAreaModel().getValueAt(i,NAME_COLUMN_INDEX).toString().equals(s)) {
                return getTbAreaModel().getValueAt(i,ID_COLUMN_INDEX).toString();
            }
        }
        return null;
    }


    public String getProjectIdByName(String name){

        int ID_COLUMN_INDEX =5;
        int NAME_COLUMN_INDEX=0;
        int rowCount=getTbProjModel().getRowCount();

        for (int i = 0 ; i < rowCount; ++i) {
            if(getTbProjModel().getValueAt(i,NAME_COLUMN_INDEX).toString().equals(name)) {
                return getTbProjModel().getValueAt(i,ID_COLUMN_INDEX).toString();
            }
        }
        return null;
    }

    public JComboBox getJcbGroupEngProName() {
        return jcbGroupEngProName;
    }

    public JTable getTableGroupEngPro() {
        return tableGroupEngPro;
    }

    public JTable getTableGroupEngPhones() {
        return tableGroupEngPhones;
    }

    public void setLabelCurrDate(String date) {
        StringBuilder sb = new StringBuilder(date);
        String dateDup = date;
        if(date.charAt(3) =='0'){
            date = date.substring(0,3) + dateDup.substring(4,10);
        }
        if(date.charAt(0) == '0'){
            date = date.substring(1);
        }

        this.labelCurrDate.setText("Date: " + date);
    }

    public JComboBox getJcbEngJoinProj() {
        return jcbEngJoinProj;
    }

    public JComboBox getJcbEngRateProj(){
        return jcbEngRateProj;
    }
    public JComboBox getJcbEngRateValue(){
        return jcbEngRateValue;
    }

    public DefaultTableModel getTbGroupEngProModel() {
        return tbGroupEngProModel;
    }

    public DefaultTableModel getTbGroupEngPhonesModel() {
        return tbGroupEngPhonesModel;
    }

    public JEditorPane getEdEngPhone() {
        return edEngPhone;
    }

    public DefaultTableModel getTbGroupModel() {
        return tbGroupModel;
    }
}
