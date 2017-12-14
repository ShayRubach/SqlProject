package com.shayrubach.view;


import com.shayrubach.controller.gui.TableController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class GuiMainPanel {

    private static final int E_RESET    = 0 ;
    private static final int E_MODIFY   = 1 ;
    private static final int E_REMOVE   = -1 ;
    private static final int E_NEW      = 2;
    private static final int EDIT_TAB   = 6 ;
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
    private JComboBox jcbEngPhones;
    private JButton addEngProBtn;
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

    private DefaultTableModel tbProjModel;
    private DefaultTableModel tbEngModel;
    private DefaultTableModel tbAreaModel;
    private DefaultTableModel tbTopProjModel;
    private DefaultTableModel tbTopEngModel;
    private DefaultTableModel tbMonitorModel;

    private ArrayList<TableController> controllers = new ArrayList<TableController>();


    public GuiMainPanel() {
        initMainFrame();
        initTables();
        initButtons();
        initCBoxes();
        initEditFields();
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
    }

    private void initEngCB() {
        initEngCBText();


    }

    private void initAreaCB() {
        initAreaCBText();
    }

    private void initProCB() {
        initProCBText();

        jcbChooseProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if a specific project was chosen
                if(jcbChooseProject.getSelectedIndex() > 1)
                    jcbChooseMilestone.setEnabled(true);
            }
        });

        jcbChooseMilestone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jcbChooseMilestone.getSelectedIndex() == 2){
                    //TODO: enable needed fields + call controller for 'add' logic
                }

                else if(jcbChooseMilestone.getSelectedIndex() > 4) {
                    //TODO: set milestone field available
                }
            }
        });

    }

    private void initProCBText() {
        jcbChooseProject.addItem("-- choose Project --");
        jcbChooseProject.addItem("  ");
        //send controller to fetch all project names from DB
        // add all projects to CB

        jcbChooseStep.addItem("-- choose Development Step --");
        jcbChooseStep.addItem(" ");
        jcbChooseStep.addItem("Design and Architecture");
        jcbChooseStep.addItem("Development and Implementation");
        jcbChooseStep.addItem("Deliver Final Product");


        jcbChooseMilestone.addItem("-- choose/add Milestone --");
        jcbChooseMilestone.addItem(" ");
        jcbChooseMilestone.addItem("--> choose this to add new Milestone <--");
        jcbChooseMilestone.addItem(" ");
        jcbChooseMilestone.addItem(" ");
    }

    public void initEngCBText() {
        jcbChooseEng.addItem("-- choose Engineer --");
        jcbChooseEng.addItem(" ");

        jcbChooseEngPro.addItem("-- choose Project --");
        jcbChooseEngPro.addItem(" ");

        jcbEngPhones.addItem("-- Phone list -- ");
        jcbEngPhones.addItem("  ");
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


        tbProjModel = new DefaultTableModel(null,tbProjColumns);
        tbEngModel = new DefaultTableModel(null,tbEngColumns);
        tbAreaModel = new DefaultTableModel(null,tbAreaColumns);
        tbTopProjModel = new DefaultTableModel(null,tbProjColumns);
        tbTopEngModel = new DefaultTableModel(null,tbEngColumns);
        tbMonitorModel = new DefaultTableModel(null,tbMonitorColumns);


        tableProjects.setModel(tbProjModel);
        tableAreas.setModel(tbAreaModel);
        tableEng.setModel(tbEngModel);
        tableMonitor.setModel(tbMonitorModel);

        tableTopProj.setModel(tbTopProjModel);
        tableTopEng.setModel(tbTopEngModel);

        //TODO: RELOAD ALL DB EVERYTIME WE START THE APP
        //getControllers().get(0).loadDB();
    }

    private void initButtons() {
        setEditListeners();

        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //TODO: IMPLEMENT UPDATE()
//                if(tabbedPane.getSelectedIndex() != EDIT_TAB){
//                    //update all changed in tabs
//                    for(TableController c : controllers){
//                        c.update();
//                    }
//                }


            }
        });
    }

    private void setEditListeners() {

        setEditPro();
        setEditArea();
        setEditEng();
    }

    private void setEditEng() {
        //TODO: RESET ALL FIELDS (Clear texts inside JEditText, back to 0 index on CBs..) WHEN SWAPPING BETWEEN RADIOS!!

        modifyEngineerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyEngineerRadioButton.setSelected(true);
                newEngineerRadioButton.setSelected(false);
                removeEngineerRadioButton.setSelected(false);

                jcbChooseEng.setEnabled(true);
            }
        });

        newEngineerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyEngineerRadioButton.setSelected(false);
                newEngineerRadioButton.setSelected(true);
                removeEngineerRadioButton.setSelected(false);

                jcbChooseEng.setEnabled(false);
            }
        });

        removeEngineerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyEngineerRadioButton.setSelected(false);
                newEngineerRadioButton.setSelected(false);
                removeEngineerRadioButton.setSelected(true);

                jcbChooseEng.setEnabled(true);
            }
        });
    }

    private void setEditArea() {
        //TODO: RESET ALL FIELDS (Clear texts inside JEditText, back to 0 index on CBs..) WHEN SWAPPING BETWEEN RADIOS!!

        newAreaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newAreaRadioButton.setSelected(true);
                removeAreaRadioButton.setSelected(false);

            }
        });

        removeAreaRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAreaRadioButton.setSelected(true);
                newAreaRadioButton.setSelected(false);

                jcbChooseArea.setEnabled(true);
            }
        });
    }

    private void setEditPro() {
        //TODO: RESET ALL FIELDS (Clear texts inside JEditText, back to 0 index on CBs..) WHEN SWAPPING BETWEEN RADIOS!!

        modifyProjectRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setComponentStates(E_RESET,tabEditPro);
                modifyProjectRadioButton.setSelected(true);
                newProjectRadioButton.setSelected(false);
                removeProjectRadioButton.setSelected(false);

                jcbChooseProject.setEnabled(true);
                enableProFields(E_MODIFY);
                edProRate.setEnabled(false);

                //TODO: fetchProjectsFromDB();
            }
        });

        newProjectRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setComponentStates(E_RESET,tabEditPro);
                newProjectRadioButton.setSelected(true);
                modifyProjectRadioButton.setSelected(false);
                removeProjectRadioButton.setSelected(false);

                jcbChooseProject.setEnabled(false);

                enableProFields(E_NEW);
                edProRate.setEnabled(false);

            }
        });

        removeProjectRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setComponentStates(E_RESET,tabEditPro);
                removeProjectRadioButton.setSelected(true);
                modifyProjectRadioButton.setSelected(false);
                newProjectRadioButton.setSelected(false);

                jcbChooseProject.setEnabled(true);
                enableProFields(E_REMOVE);
                edProRate.setEnabled(false);


            }
        });

        applyProButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: call controller.update or something to create new Entity from fields with these conditions below

                controllers.get(0).addEntity(PROJECT_ENTITY);
                if(modifyProjectRadioButton.isSelected()){}
                if(newProjectRadioButton.isSelected()){}
                if(removeProjectRadioButton.isSelected()){}
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

                setNewProTextHint(E_NEW);
                //TODO: call controller.generateId() and set inside labelEngId

                break;
            case E_MODIFY:
                setNewProTextHint(E_MODIFY);
                break;
            case E_REMOVE:
                setNewProTextHint(E_REMOVE);
                break;
        }
    }

    private void setNewProTextHint(int MODE) {
        for(Component ep : tabEditPro.getComponents()) {

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

                    edProRate.setText("0");
                    edProName.setText("Project_X");
                    edProDesc.setText("this project is bound to investigate the X letter");
                    edProDate.setText("24.10.2017");
                    edProCust.setText("James Dean");
                    edProTools.setText("Github,VisualStudio,VMWare,JIRA");
                    edProMilestone.setText("finish the design in 14 days from kick off");
                    edProMoney.setText("12000");
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

    public JComboBox getJcbEngPhones() {
        return jcbEngPhones;
    }

    public void setJcbEngPhones(JComboBox jcbEngPhones) {
        this.jcbEngPhones = jcbEngPhones;
    }

    public JButton getAddEngProBtn() {
        return addEngProBtn;
    }

    public void setAddEngProBtn(JButton addEngProBtn) {
        this.addEngProBtn = addEngProBtn;
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

    public void fillProjectTable(String[] formedProjectRow) {
        getTbProjModel().addRow(formedProjectRow);
    }
}
