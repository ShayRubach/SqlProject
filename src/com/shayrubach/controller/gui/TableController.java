package com.shayrubach.controller.gui;

import com.shayrubach.model.QueryHolder;
import com.shayrubach.model.entities.Project;
import com.shayrubach.model.other.Milestone;
import com.shayrubach.view.GuiMainPanel;
import sun.misc.IOUtils;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
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

    public void addEntity(int ENTITY) {
        //TODO: implement all cases of add/modify/remove entities
        switch(ENTITY){
            case GuiMainPanel.PROJECT_ENTITY:
                Project p = new Project(
                        new Milestone("sadf","dd","dfs"),
                        getGui().getEdProDate().getText().toString(),
                        getGui().getEdProDesc().getText().toString(),
                        getGui().getEdProName().getText().toString(),
                        getGui().getJcbChooseStep().toString());

                //TODO: query- add this entity to the DB
//                String query = "INSERT INTO projects(" +
//                        "project_id," +
//                        "date_started," +
//                        "name," +
//                        "description)" +
//                        "VALUES (?,?,?,?);";
                try {
                    PreparedStatement stmt = connection.prepareStatement(QueryHolder.newProjectQuery);

                    stmt.setString(2,p.getDateStarted().toString());
                    stmt.setString(3,p.getName().toString());
                    stmt.setString(4,p.getDescription().toString());
                    stmt.setString(1,randString(8));
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

    private String randString(int i) {
        String uuid = UUID.randomUUID().toString();
        uuid.replace("-","");
        return uuid.substring(0,i);
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
}
