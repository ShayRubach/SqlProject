package com.shayrubach.controller.gui;

import com.shayrubach.view.GuiMainPanel;

import javax.swing.*;

public class TableController {

    private JTable table;
    private String tableName;
    private GuiMainPanel gui;

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

        switch(ENTITY){
            case GuiMainPanel.PROJECT_ENTITY:

                break;
            case GuiMainPanel.AREA_ENTITY:

                break;
            case GuiMainPanel.ENGINEER_ENTITY:

                break;

        }
    }

    public GuiMainPanel getGui() {
        return gui;
    }

    public void setGui(GuiMainPanel gui) {
        this.gui = gui;
    }
}
