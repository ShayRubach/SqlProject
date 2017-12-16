package com.shayrubach.controller.gui.tablectrls;

import com.shayrubach.controller.IController;
import com.shayrubach.model.QueryHolder;
import com.shayrubach.model.entities.Area;
import com.shayrubach.model.entities.Project;
import com.shayrubach.model.other.Milestone;
import com.shayrubach.view.GuiMainPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class TableController implements IController {

    private JTable table;
    private String tableName;
    private GuiMainPanel gui;

    private Connection connection;

    public TableController(){

    }

    public TableController(JTable table, String tableName) {
        this.table = table;
        this.tableName = tableName;
    }

    public void build(String editedType){
        //
    }

    public void removeEntity(int ENTITY){

        switch (ENTITY){
            case GuiMainPanel.PROJECT_ENTITY:
                try {
                    PreparedStatement stmt = connection.prepareStatement(QueryHolder.QUERY_REMOVE_PROJECT);
                    stmt.setString(0,getGui().getLabelProId().getText().toString());
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case GuiMainPanel.AREA_ENTITY:

                break;
            case GuiMainPanel.ENGINEER_ENTITY:

                break;
        }
    }

    public void addEntity(int ENTITY) throws SQLException {
        //TODO: implement all cases of add/modify/remove entities
        switch(ENTITY){
            case GuiMainPanel.PROJECT_ENTITY:

                String areaName = getGui().getJcbChooseProArea().getSelectedItem().toString();
                String areaSpecialty=null;

                PreparedStatement ps = getConnection().prepareStatement(QueryHolder.QUERY_GET_AREA_BY_NAME);
                ps.setString(1,areaName);

                ResultSet rs = ps.executeQuery();
                //TODO: get the area from db and add it to proj table
//                areaSpecialty = rs.getString(2);
//
//
//                System.out.println(areaName + " , " + areaSpecialty);


                Project p = new Project(
                        //TODO: FIX MILESTONE SHIT FUCK HERE, ADD DUE DATE IN TABLES ATTR
                        new Milestone(getGui().getEdProMilestone().getText().toString(),
                                getGui().getEdProMoney().getText().toString(),
                                getGui().getEdProMsDueDate().getText().toString()),
                        getGui().getEdProDate().getText().toString(),
                        getGui().getEdProDesc().getText().toString(),
                        getGui().getEdProName().getText().toString(),
                        getGui().getJcbChooseStep().toString());

                        p.setArea(new Area(areaName,areaSpecialty));
                        p.setCustomers(getGui().getEdProCust().getText().toString());
                        p.setDevTools(getGui().getEdProTools().getText().toString());


                //upload to db
                try {
                    ArrayList<PreparedStatement> pstmt = new ArrayList<>();

                    pstmt.add(connection.prepareStatement(QueryHolder.QUERY_NEW_PROJECT));
                    pstmt.get(pstmt.size()-1).setString(1,p.getId().toString());
                    pstmt.get(pstmt.size()-1).setString(2,p.getDateStarted().toString());
                    pstmt.get(pstmt.size()-1).setString(3,p.getName().toString());
                    pstmt.get(pstmt.size()-1).setString(4,p.getDescription().toString());

                    //TODO: get the due date and put inside the appropriate class in db and local

                    //execute all statements
                    for (PreparedStatement pst : pstmt) pst.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                getGui().getJcbChooseProject().addItem(p.getName().toString());
                addProToTable(p);
                //TODO: update the other tables with projects??

                //TODO: update GUI



                break;
            case GuiMainPanel.AREA_ENTITY:

//                Area a = new Area(getGui().getEdAreaName().getText(),getGui().getEdAreaSpec().getText());
//                PreparedStatement ps = getConnection().prepareStatement(QueryHolder.QUERY_NEW_AREA);

                break;
            case GuiMainPanel.ENGINEER_ENTITY:

                break;

        }
    }

    private void addProToTable(Project p) {
        //TODO: GET ALL MISSING PROPERTIES HERE!!

        getGui().getTbProjModel().addRow(new Object[] {
                p.getName().toString(),
                "AREA GOES HERE",
                p.getDescription(),
                p.getCustomers(),
                p.getDevTools(),
                p.getDateStarted(),
                //p.getMilestone().getName(),
                null,
                p.getId()
        });
        //TODO: FIX THIS COMBO BOX INSIDE COLUMN
        JComboBox cb = new JComboBox();
        cb.addItem("test");
        cb.addItem("test2");
        cb.addItem("test3");

        getGui().getTableProjects().getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(cb));

    }

    public GuiMainPanel getGui() {
        return gui;
    }

    public void setGui(GuiMainPanel gui) {
        this.gui = gui;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void loadDb() throws SQLException {
        //TODO: implememt load db
        ArrayList<PreparedStatement> pss = new ArrayList<>();
        ArrayList<ResultSet> rss = new ArrayList<>();

        pss.add(getConnection().prepareStatement(QueryHolder.QUERY_GET_ALL_PROJECTS));
        //pss.add(getConnection().prepareStatement(QueryHolder.QUERY_GET_AREAS_BY_PROJECT));
        //pss.add(getConnection().prepareStatement(QueryHolder.QUERY_GET_MILESTONE_BY_PROJECT));
        //pss.add(getConnection().prepareStatement(QueryHolder.QUERY_GET_DEV_TOOLS_BY_PROJECT));



//        pss.add(getConnection().prepareStatement(QueryHolder.QUERY_GET_ALL_AREAS));
//        pss.add(getConnection().prepareStatement(QueryHolder.QUERY_GET_ALL_ENGINEERS));

        ResultSet rs = pss.get(0).executeQuery();

        //add static milestone for testings
//        pss.add(getConnection().prepareStatement(QueryHolder.QUERY_NEW_MILESTONE));
//        pss.get(1).setString(1,"8c410e38");
//        pss.get(1).setString(2,"milestone example");
//        pss.get(1).setString(3,"5112018");
//        pss.get(1).setString(4,"56000");
//        pss.get(1).executeUpdate();


        //TODO: build a row in form of a string array and send it to fill func (get all required db fields)

        //  replace null with the real properites!!!

        while(rs.next()){

            String[] formedProjectRow = {
                    rs.getString(3),    //name
                    "  ",                           //area
                    rs.getString(4),    //desc
                    "  ",                           //customers
                    "  ",                           //dev tools
                    rs.getString(2),    //date started
                    "  ",                           //milestones
                    rs.getString(1)     //id
            };
            getGui().fillProjectTable(formedProjectRow);
        }
    }

}
