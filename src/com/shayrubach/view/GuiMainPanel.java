package com.shayrubach.view;


import com.shayrubach.controller.gui.tablectrls.TableController;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class GuiMainPanel {

    private static final int E_RESET    = 0 ;
    private static final int E_MODIFY   = 1 ;
    private static final int E_REMOVE   = -1 ;
    private static final int E_NEW      = 2;
    public static final int MONTHLY_REVENUE = 1;
    public static final int TOTAL_REVENUE   = 2;


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
    private JPanel tabMilestones;
    private JPanel tabDevSteps;
    private JTable tableMilestones;
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
    private JRadioButton showAllMilestonesRadioButton;
    private JRadioButton showThisMonthSRadioButton;
    private JComboBox jcbDevStepToShow;

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
        initMilestones();
    }
    private void initJsps() {
        //jspProj = new JScrollPane(tableProjects, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableProjects.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableAreas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableEng.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableTopProj.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableTopEng.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableDevSteps.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableMilestones.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
        initDevStepCB();
    }
    private void initDevStepCB() {
        jcbDevStepToShow.addActionListener(e -> {
            try {
                if (jcbDevStepToShow.getSelectedIndex() > 1) {
                    getControllers().get(0).getProjectByDevStep();
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });
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
    private void initMilestones(){
        showThisMonthSRadioButton.addActionListener(e -> {
            ResultSet rs;
            showAllMilestonesRadioButton.setSelected(false);

            try {
                getControllers().get(0).loadDbMilestones(null,null,2);
                rs = controllers.get(0).calculateRevenue(MONTHLY_REVENUE);
                while (rs.next()) {
                    labelTotalRevenue.setText(labelTotalRevenue.getText().substring(0,14));
                    labelTotalRevenue.setText(labelTotalRevenue.getText() + " " + rs.getString(1) + "$") ;
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        });

        showAllMilestonesRadioButton.addActionListener(e -> {
            ResultSet rs;
            showThisMonthSRadioButton.setSelected(false);
            try {
                getControllers().get(0).loadDbMilestones(null,null,1);
                rs = controllers.get(0).calculateRevenue(TOTAL_REVENUE);
                while (rs.next()) {
                    labelTotalRevenue.setText(labelTotalRevenue.getText().substring(0,14));
                    labelTotalRevenue.setText(labelTotalRevenue.getText() + " " + rs.getString(1) + "$");
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
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
                    if(removeProjectRadioButton.isSelected()){
                        jcbChooseStep.setEnabled(false);
                    }
                    if(addNewMilestoneRadioButton.isSelected()) {

                        jcbChooseStep.setEnabled(false);
                        getControllers().get(0).getProjectDevStep();

                    }

                    if(modifyProjectRadioButton.isSelected()){
                        jcbChooseMilestone.setEnabled(false);
                        jcbChooseStep.setEnabled(true);
                        getControllers().get(0).getProjectDevStep();
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
    private void initTables() {

        final String[] tbProjColumns = {"Name","Description","Customers","Tools","Date Started","ID","Rate"};
        final String[] tbEngColumns = {"First Name","Last Name","Birth","Age","Address","ID","Area"};
        final String[] tbAreaColumns = {"Name","Specialty","ID"};
        final String[] tbMonitorColumns = {"Description","TimeStamp"};
        final String[] tbMilestonesColumns = {"Milestone","Date","Money Granted","Project"};
        final String[] tbDevStepsColumns = {"Projects","Tools"};
        final String[] tbGroupColumns = {"First Name","Last Name","ID"};
        final String[] tbTopProjectsColumns = {"Project","Rate"};
        final String[] tbTopEngineersColumns = {"ID","First Name","Last Name", "Number of Projects"};
        final String[] tbGroupEngProColumns  = {"Project","Rate"};
        final String[] tbGroupEngPhonesColumns   = {"Phone No."};


        tableProjects.setBackground(new Color(255,255,255));
        tableEng.setBackground(new Color(255,255,255));
        tableAreas.setBackground(new Color(255,255,255));
        tableTopProj.setBackground(new Color(255,255,255));
        tableTopEng.setBackground(new Color(255,255,255));
        tableMonitor.setBackground(new Color(255,255,255));
        tableMilestones.setBackground(new Color(255,255,255));
        tableDevSteps.setBackground(new Color(255,255,255));
        tableGroupsPro.setBackground(new Color(255,255,255));
        tableGroupEngPro.setBackground(new Color(255,255,255));
        tableGroupEngPhones.setBackground(new Color(255,255,255));



        tbProjModel = new DefaultTableModel(null,tbProjColumns);
        tbEngModel = new DefaultTableModel(null,tbEngColumns);
        tbAreaModel = new DefaultTableModel(null,tbAreaColumns);
        tbTopProjModel = new DefaultTableModel(null,tbTopProjectsColumns );
        tbTopEngModel = new DefaultTableModel(null, tbTopEngineersColumns);
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
        tableMilestones.setModel(tbMilestoneModel);
        tableDevSteps.setModel(tbDevStepsModel);
        tableGroupsPro.setModel(tbGroupModel);
        tableGroupEngPro.setModel(tbGroupEngProModel);
        tableGroupEngPhones.setModel(tbGroupEngPhonesModel);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tableTopEng.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableEng.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableProjects.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tableMilestones.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableGroupEngPro.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableGroupEngPro.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableTopProj.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

    }
    private void initTabs() {
        setEditListeners();

        tabbedPane.addChangeListener(e -> {
            try {
                switch (tabbedPane.getSelectedIndex()) {

                    //top projects
                    case 3:


                        break;

                    //top eng
                    case 4:

                        break;
                    //milestones
                    case 5:
                        getControllers().get(0).loadDbMilestones(null,null,1);
                        showAllMilestonesRadioButton.setSelected(true);
                        showThisMonthSRadioButton.setSelected(false);

                        break;
                    //dev steps
                    case 6:
                        getTbDevStepsModel().setNumRows(0);
                        getControllers().get(0).loadDbDevSteps(null,null);
                        break;
                    //groups
                    case 7:
                        PreparedStatement ps = null;
                        ResultSet rs = null;
                        getTbGroupModel().setNumRows(0);
                        getTbGroupEngPhonesModel().setNumRows(0);
                        getTbGroupEngProModel().setNumRows(0);

                        resetJcbItems(jcbGroupEngProName, "Engineer");
                        jcbGroupPro.setSelectedIndex(0);
                        jcbGroupArea.setSelectedIndex(0);

                        break;

                }
                getControllers().get(0).loadDbProjects(null, null);
                getControllers().get(0).loadDbEngineers(null, null);
            }
            catch (Exception ex){
                ex.printStackTrace();
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

                getControllers().get(0).loadDbEngineers(null,null);
                getControllers().get(0).loadDbProjects(null,null);
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
            cleanEdFields(tabEditPro);
            setComponentStates(E_RESET,tabEditPro);
            modifyProjectRadioButton.setSelected(true);
            newProjectRadioButton.setSelected(false);
            removeProjectRadioButton.setSelected(false);
            addNewMilestoneRadioButton.setSelected(false);

            jcbChooseProject.setEnabled(true);
            jcbChooseProject.setSelectedIndex(0);
            jcbChooseStep.setEnabled(false);

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
                cleanEdFields(tabEditPro);
                removeProjectRadioButton.setSelected(true);
                modifyProjectRadioButton.setSelected(false);
                newProjectRadioButton.setSelected(false);
                addNewMilestoneRadioButton.setSelected(false);

                jcbChooseProject.setEnabled(true);
                enableProFields(E_REMOVE);
                edProRate.setEnabled(false);

                try {
                    getControllers().get(0).getProjectDevStep();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

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
                jcbChooseStep.setEnabled(false);
                controllers.get(0).addNewAreaToProject();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        addNewMilestoneRadioButton.addActionListener(e -> {
            if(addNewMilestoneRadioButton.isSelected()){
                edProMilestone.setText("Milestone short description");
                //edProMsDueDate.setText(labelCurrDate.getText().substring(6).replace('/','.'));
                edProMsDueDate.setText("04.02.1999");
                edProMoney.setText("24500");

                jcbChooseProject.setEnabled(true);
                jcbChooseMilestone.setEnabled(false);

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
                    controllers.get(0).modifyEntity(PROJECT_ENTITY);
                    jcbChooseStep.setSelectedIndex(0);
                }
                if(removeProjectRadioButton.isSelected() && jcbChooseProject.getSelectedIndex() > 1){
                    controllers.get(0).removeEntity(PROJECT_ENTITY);
                }
                if(addNewMilestoneRadioButton.isSelected()){
                   controllers.get(0).addMilestoneToProject();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            cleanEdFields(getTabEditPro());

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
    public void cleanEdFields(JPanel tab) {
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

                    edProDate.setText("24.10.2017");
                    edProCust.setText("James Dean,Google");
                    edProTools.setText("Github,VisualStudio,VMWare,JIRA");

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
    public JTable getTableProjects() {
        return tableProjects;
    }
    public JTable getTableAreas() {
        return tableAreas;
    }
    public JEditorPane getEdProMilestone() {
        return edProMilestone;
    }
    public JComboBox getJcbChooseProject() {
        return jcbChooseProject;
    }
    public JComboBox getJcbChooseArea() {
        return jcbChooseArea;
    }
    public JComboBox getJcbChooseEng() {
        return jcbChooseEng;
    }
    public JComboBox getJcbChooseStep() {
        return jcbChooseStep;
    }
    public JEditorPane getEdProDesc() {
        return edProDesc;
    }
    public JEditorPane getEdProName() {
        return edProName;
    }
    public JEditorPane getEdProTools() {
        return edProTools;
    }
    public JEditorPane getEdProDate() {
        return edProDate;
    }
    public JEditorPane getEdAreaSpec() {
        return edAreaSpec;
    }
    public JEditorPane getEdEngAddress() {
        return edEngAddress;
    }
    public JEditorPane getEdEngFname() {
        return edEngFname;
    }
    public JEditorPane getEdAreaName() {
        return edAreaName;
    }
    public JEditorPane getEdEngLname() {
        return edEngLname;
    }
    public JEditorPane getEdProMoney() {
        return edProMoney;
    }
    public JRadioButton getModifyProjectRadioButton() {
        return modifyProjectRadioButton;
    }
    public JPanel getTabEditPro() {
        return tabEditPro;
    }
    public JRadioButton getModifyEngineerRadioButton() {
        return modifyEngineerRadioButton;
    }
    public JEditorPane getEdEngDate() {
        return edEngDate;
    }
    public DefaultTableModel getTbProjModel() {
        return tbProjModel;
    }
    public DefaultTableModel getTbEngModel() {
        return tbEngModel;
    }
    public DefaultTableModel getTbAreaModel() {
        return tbAreaModel;
    }
    public DefaultTableModel getTbTopProjModel() {
        return tbTopProjModel;
    }
    public DefaultTableModel getTbTopEngModel() {
        return tbTopEngModel;
    }
    public JEditorPane getEdProMsDueDate() {
        return edProMsDueDate;
    }
    public JEditorPane getEdProCust() {
        return edProCust;
    }
    public JComboBox getJcbChooseProArea() {
        return jcbChooseProArea;
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
    public void setLabelCurrDate(String date) {
        String dateDup = date;
        if(date.charAt(3) =='0'){
            date = date.substring(0,3) + dateDup.substring(4,10);
        }
        if(date.charAt(0) == '0'){
            date = date.substring(1);
        }

        this.labelCurrDate.setText("Date: " + date);
    }
    public DefaultTableModel getTbMilestoneModel() {
        return tbMilestoneModel;
    }
    public DefaultTableModel getTbDevStepsModel() {
        return tbDevStepsModel;
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
    public JLabel getLabelCurrDate() {
        return labelCurrDate;
    }
    public DefaultTableModel getTbGroupModel() {
        return tbGroupModel;
    }
    public JLabel getLabelTotalRevenue() {
        return labelTotalRevenue;
    }
    public JComboBox getJcbDevStepToShow() {
        return jcbDevStepToShow;
    }
    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
}
