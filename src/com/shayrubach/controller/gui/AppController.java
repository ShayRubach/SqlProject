package com.shayrubach.controller.gui;

import com.shayrubach.controller.IController;
import com.shayrubach.view.GuiMainPanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppController implements IController {

    private GuiMainPanel gui;
    private ArrayList<TableController> controllers = new ArrayList<TableController>();

    String DRIVER = "com.mysql.jdbc.Driver" ;
    String URL = "jdbc:mysql://localhost:3306/softwarecompany?createDatabaseIfNotExist=true" ;
    Connection connection = null;

    public AppController(GuiMainPanel gui) throws SQLException {
        connect();
        setGui(gui);
        initControllers();
        bind();
        loadDb();
    }

    public void loadDb() throws SQLException {
        for(TableController c : controllers)
            if(c != null) c.loadDb();
    }

    private void connect() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,"root","");
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    /* init all controllers with their appropriate table and mention its name */
    private void initControllers() {
        controllers.add(new TableController(gui.getTableProjects(),"PROJECTS"));
        //TODO: change Ctrlrs to be explicit ctrls ProjectCtrl, EngCtrl etc...
        //controllers.add(new TableController(gui.getTableAreas(),"ARES"));
        //controllers.add(new TableController(gui.getTableEng(),"ENGINEERS"));
        //controllers.add(new TableController(gui.getTableMonitor(),"MONITOR"));
        //controllers.add(new TableController(gui.getTableTopProj(),"TOP_PROJECTS"));
        //controllers.add(new TableController(gui.getTableTopEng(),"TOP_ENGINEERS"));

        for(TableController tc : controllers){
            tc.setGui(gui);
            tc.setConnection(connection);
        }


    }

    /* binds all controller references in gui to their actual controllers.
     * later, every gui component would be able to call its controller for service */
    public void bind(){
        for (int i = 0; i < controllers.size(); i++) {
            gui.getControllers().add(controllers.get(i));
        }
    }


    public boolean connectDb(String dbName) {
        return false;
    }

    public boolean dissconnectDb() {
        return false;
    }

    public GuiMainPanel getGui() {
        return gui;
    }

    public void setGui(GuiMainPanel gui) {
        this.gui = gui;
    }
}
