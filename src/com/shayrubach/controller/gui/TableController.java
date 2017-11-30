package com.shayrubach.controller.gui;

import com.shayrubach.model.QueryHolder;
import com.shayrubach.model.entities.Project;
import com.shayrubach.model.other.Milestone;
import com.shayrubach.view.GuiMainPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class TableController {

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

    public void addEntity(int ENTITY) {
        //TODO: implement all cases of add/modify/remove entities
        switch(ENTITY){
            case GuiMainPanel.PROJECT_ENTITY:
                Project p = new Project(
                        //TODO: FIX MILESTONE SHIT FUCK HERE, ADD DUE DATE IN TABLES ATTR
                        new Milestone(getGui().getEdProMilestone().toString(),
                                getGui().getEdProMoney().toString(),
                                getGui().getEdProMsDueDate().toString()),
                        getGui().getEdProDate().getText().toString(),
                        getGui().getEdProDesc().getText().toString(),
                        getGui().getEdProName().getText().toString(),
                        getGui().getJcbChooseStep().toString());

                //upload to db
                try {
                    PreparedStatement stmt = connection.prepareStatement(QueryHolder.QUERY_NEW_PROJECT);
                    stmt.setString(1,p.getId().toString());
                    stmt.setString(2,p.getDateStarted().toString());
                    stmt.setString(3,p.getName().toString());
                    stmt.setString(4,p.getDescription().toString());

                    //TODO: get the due date and put inside the appropriate class in db and local
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                getGui().getJcbChooseProject().addItem(p.getName().toString());
                addProToTable(p);
                //TODO: update the other tables with projects??

                //TODO: update GUI



                break;
            case GuiMainPanel.AREA_ENTITY:

                break;
            case GuiMainPanel.ENGINEER_ENTITY:

                break;

        }
    }

    private void addProToTable(Project p) {
        getGui().getTbProjModel().addRow(new Object[] {
                p.getId(),
                p.getDateStarted().toString(),
                p.getName().toString(),
                p.getDescription().toString()
        });
    }

//    private String randString(int i) {
//        String uuid = UUID.randomUUID().toString();
//        uuid.replace("-","");
//        return uuid.substring(0,i);
//    }

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

    public void loadDB() {
        //TODO: implememt load db
    }
}
