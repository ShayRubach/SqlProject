package com.shayrubach.controller.gui.tablectrls;

import com.shayrubach.controller.IController;
import com.shayrubach.model.QueryHolder;
import com.shayrubach.model.entities.Area;
import com.shayrubach.model.entities.Engineer;
import com.shayrubach.model.entities.Project;
import com.shayrubach.view.GuiMainPanel;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public void removeEntity(int ENTITY) throws SQLException {
        PreparedStatement ps;
        ResultSet rs=null;
        String projectId;
        final int ID_COLUMN_INDEX = 5;
        switch (ENTITY){
            case GuiMainPanel.PROJECT_ENTITY:
                ps = connection.prepareStatement(QueryHolder.QUERY_PROJECT_ID_BY_NAME);
                ps.setString(1,getGui().getJcbChooseProject().getSelectedItem().toString());
                rs = ps.executeQuery();
                rs.next();
                projectId = rs.getString(1);

                ps = connection.prepareStatement(QueryHolder.QUERY_REMOVE_PROJECT);

                //rs is the project Id:
                ps.setString(1,projectId);
                ps.executeUpdate();

                getGui().getJcbChooseProject().setSelectedIndex(0);
                loadDbProjects(ps,rs);

                break;
            case GuiMainPanel.AREA_ENTITY:
                //TODO: ADD A TRANSACTION TO CHECK IF ID EXISTS, IF NOT, ROLLBACK!
                String areaId = getGui().getAreaIdByName(getGui().getJcbChooseArea().getSelectedItem().toString());
                if(areaId == null){
                    return;
                }
                ps = connection.prepareStatement(QueryHolder.QUERY_REMOVE_AREA);
                ps.setString(1,areaId);
                ps.executeUpdate();
                getGui().getJcbChooseArea().setSelectedIndex(0);
                loadDbAreas(ps,rs);

                break;
            case GuiMainPanel.ENGINEER_ENTITY:

//                String engId = getGui().getEngIdByName(getGui().getJcbChooseArea().getSelectedItem().toString());
//                ps = connection.prepareStatement(QueryHolder.QUERY_GET_AREA_BY_ID);
//                ps.setString(1,engId);
//                ps.executeUpdate();
                break;
        }
    }

    public void addEntity(int ENTITY) throws SQLException {
        //TODO: implement all cases of add/modify/remove entities
        PreparedStatement ps;
        ResultSet rs = null;
        switch(ENTITY){
            case GuiMainPanel.PROJECT_ENTITY:

                String areaName = getGui().getJcbChooseProArea().getSelectedItem().toString();

                ps = getConnection().prepareStatement(QueryHolder.QUERY_GET_AREA_BY_NAME);
                ps.setString(1,areaName);

                rs = ps.executeQuery();

                //TODO: FIX MILESTONE SHIT FUCK HERE, ADD DUE DATE IN TABLES ATTR
                Project p = new Project(
                        getGui().getEdProDate().getText().toString(),
                        getGui().getEdProDesc().getText().toString(),
                        getGui().getEdProName().getText().toString(),
                        getGui().getJcbChooseStep().toString());

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
                    pstmt.get(pstmt.size()-1).setString(5,p.getCustomers().toString());
                    pstmt.get(pstmt.size()-1).setString(6,p.getDevTools().toString());

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
                Area a = new Area(getGui().getEdAreaName().getText(),getGui().getEdAreaSpec().getText());
                ps = getConnection().prepareStatement(QueryHolder.QUERY_NEW_AREA);
                ps.setString(1,a.getId());
                ps.setString(2,a.getName());
                ps.setString(3,a.getSpecialty());
                ps.executeUpdate();

                //addAreaToTable(a);
                getGui().getEdAreaName().setText("");
                getGui().getEdAreaSpec().setText("");

                getGui().resetJcbItems(getGui().getJcbChooseArea(),"Area");
                getGui().resetJcbItems(getGui().getJcbGroupArea(),"Area");

                loadDbAreas(ps,rs);
                break;
            case GuiMainPanel.ENGINEER_ENTITY:
                Engineer eng = new Engineer();
                break;

        }
    }

    private void addProToTable(Project p) {
        //TODO: GET ALL MISSING PROPERTIES HERE!!

        getGui().getTbProjModel().addRow(new Object[] {
                p.getName().toString(),
                "",
                p.getDescription(),
                p.getCustomers(),
                p.getDevTools(),
                p.getDateStarted(),
                p.getId()
        });
//        //TODO: FIX THIS COMBO BOX INSIDE COLUMN
//        JComboBox cb = new JComboBox();
//        cb.addItem("test");
//        cb.addItem("test2");
//        cb.addItem("test3");
//        cb.setSelectedIndex(0);
//
//        getGui().getTableProjects().getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(cb));


    }

    private void addAreaToTable(Area a) {
        //TODO: GET ALL MISSING PROPERTIES HERE!!
        getGui().getTbAreaModel().addRow(new Object[] {
                a.getName().toString(),
                a.getSpecialty(),
                a.getId()
        });

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
        PreparedStatement ps = null;
        ResultSet rs = null;

        loadDbProjects(ps,rs);
        loadDbAreas(ps,rs);
        loadDbEngineers(ps,rs);
        loadDbMilestones(ps,rs);
        loadDbDevSteps(ps,rs);


        //pss.add(getConnection().prepareStatement(QueryHolder.QUERY_GET_AREAS_BY_PROJECT));
        //pss.add(getConnection().prepareStatement(QueryHolder.QUERY_GET_MILESTONE_BY_PROJECT));
        //pss.add(getConnection().prepareStatement(QueryHolder.QUERY_GET_DEV_TOOLS_BY_PROJECT));

//        pss.add(getConnection().prepareStatement(QueryHolder.QUERY_GET_ALL_ENGINEERS));


    }

    private void loadDbDevSteps(PreparedStatement ps, ResultSet rs) throws SQLException  {
    }

    private void loadDbMilestones(PreparedStatement ps, ResultSet rs) throws SQLException  {
    }

    private void loadDbEngineers(PreparedStatement ps, ResultSet rs) throws SQLException  {
    }

    private void loadDbAreas(PreparedStatement ps, ResultSet rs) throws SQLException  {
        ps = getConnection().prepareStatement(QueryHolder.QUERY_GET_ALL_AREAS);
        rs = ps.executeQuery();
        getGui().resetJcbItems(getGui().getJcbChooseArea(),"Area");
        getGui().resetJcbItems(getGui().getJcbGroupArea(),"Area");
        for(int i=0; i < getGui().getTbAreaModel().getRowCount();++i){
            getGui().getTbAreaModel().removeRow(i);
        }

        while(rs.next()){
            String[] formedAreaRow = {
                    rs.getString(2),     //name
                    rs.getString(3),     //spec
                    rs.getString(1)      //id
            };


            getGui().fillAreaTable(formedAreaRow);
            getGui().getJcbChooseArea().addItem(rs.getString(2));
            getGui().getJcbGroupArea().addItem(rs.getString(2));

        }
    }

    private void loadDbProjects(PreparedStatement ps, ResultSet rs) throws SQLException {
        ps = this.getConnection().prepareStatement(QueryHolder.QUERY_GET_ALL_PROJECTS);
        rs = ps.executeQuery();

        for(int i=0; i < getGui().getTbProjModel().getRowCount();++i){
            getGui().getTbAreaModel().removeRow(i);
        }

        while(rs.next()){

            String[] formedProjectRow = {
                    rs.getString(3),    //name
                    rs.getString(4),    //desc
                    rs.getString(5),    //customers
                    rs.getString(6),    //dev tools
                    rs.getString(2),    //date started
                    rs.getString(1)     //id
            };

            getGui().fillProjectTable(formedProjectRow);


            getGui().getJcbChooseProject().addItem(rs.getString(3));
            getGui().getJcbGroupPro().addItem(rs.getString(3));
        }
    }

    public void getAvailableAreas() throws SQLException {
        //TODO: fix this to only show the selected project's specific areas.
        PreparedStatement ps;
        ResultSet rs;
        ps = getConnection().prepareStatement(QueryHolder.QUERY_GET_ALL_AREA_NAMES);
        rs = ps.executeQuery();
        while(rs.next()){
            getGui().getJcbNewArea().addItem(rs.getString(1));
        }

    }

    public void getProjectAreas() throws SQLException {
        //TODO: fix this to only show the selected project's specific areas.
        getGui().resetJcbItems(getGui().getJcbGroupArea(),"Area");

        PreparedStatement ps;
        ResultSet rs;
        ps = getConnection().prepareStatement(QueryHolder.QUERY_GET_AREAS_OF_PROJET_BY_NAME);
        ps.setString(1,getGui().getJcbGroupPro().getSelectedItem().toString());

        rs = ps.executeQuery();
        while(rs.next()){
            getGui().getJcbGroupArea().addItem(rs.getString(1));
        }

    }

    public void addNewAreaToProject() throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        String projectName,areaName, areaId = null, projectId = null;


        //get project name off the fields
        projectName = getGui().getJcbChooseProject().getSelectedItem().toString();
        areaName = getGui().getJcbNewArea().getSelectedItem().toString();

        ps = getConnection().prepareStatement(QueryHolder.QUERY_GET_PROJECT_AND_AREA_IDS_BY_NAME);
        ps.setString(1,projectName);
        ps.setString(2,areaName);
        rs = ps.executeQuery();

        rs.next();
        projectId = rs.getString(1);
        areaId = rs.getString(2);

        ps = getConnection().prepareStatement(QueryHolder.QUERY_ADD_AREA_TO_PROJECT);
        ps.setString(1,projectId);
        ps.setString(2,areaId);
        ps.executeUpdate();

    }

    public void fillGroupCBX() throws SQLException {

        String newData = null;
        PreparedStatement ps;
        ResultSet rs;

        getGui().getJcbGroupPro().removeAllItems();


        ps= getConnection().prepareStatement(QueryHolder.QUERY_GET_ALL_PROJECT_NAMES);
        rs = ps.executeQuery();

        while(rs.next()) {
            newData = rs.getString(1);
            getGui().getJcbGroupPro().addItem(newData);
        }

    }

    public void fillGroupAreaCB() throws SQLException {
        ResultSet rs;
        PreparedStatement ps;

        getGui().getJcbGroupArea().removeAllItems();

        ps = getConnection().prepareStatement(QueryHolder.QUERY_PROJECT_ID_BY_NAME);
        ps.setString(1,getGui().getJcbGroupPro().getSelectedItem().toString());
        rs = ps.executeQuery(); //rs holds the proj id

//      proc the first item
        rs.next();


        ps = getConnection().prepareStatement(QueryHolder.QUERY_GET_ALL_PROJECT_AREAS);
        ps.setString(1,rs.getString(1));
        rs = ps.executeQuery();

        while(rs.next())
            getGui().getJcbGroupArea().addItem(rs.getString(1));
    }

    public void getEntityDetails(int entity, JComboBox jcbChooseEng) {
        switch (entity){
            case GuiMainPanel.ENGINEER_ENTITY:
                //TODO: Get daba from db to show on modified gui entity
                break;
            case GuiMainPanel.PROJECT_ENTITY:
                break;
            case GuiMainPanel.AREA_ENTITY:
                break;
        }

    }
}
