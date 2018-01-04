package com.shayrubach.controller.gui;

import com.shayrubach.controller.IController;
import com.shayrubach.controller.gui.tablectrls.TableController;
import com.shayrubach.model.QueryHolder;
import com.shayrubach.view.GuiMainPanel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AppController implements IController {
    public static String date;
    private GuiMainPanel gui;
    private ArrayList<TableController> controllers = new ArrayList<TableController>();

    public String DRIVER = "com.mysql.jdbc.Driver" ;
    public String URL = "jdbc:mysql://localhost:3306/softwarecompany?createDatabaseIfNotExist=true" ;
    public Connection connection = null;

    public AppController(GuiMainPanel gui) throws SQLException {
        connect();
        setUpDb();
        setGui(gui);
        initControllers();
        bind();
        setDate();
        loadDb();
    }

    private void setDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.now();
        date = dtf.format(localDate);
        getGui().setLabelCurrDate(date);
    }

    public static String getDate() {
        return date;
    }

    private void setUpDb() throws SQLException {
        ArrayList<PreparedStatement> statements = new ArrayList<PreparedStatement>();
        ArrayList<String> queries = new ArrayList<>();

        //form table queries
        queries.add(
                "CREATE TABLE IF NOT EXISTS  projects( " +
                        "PRIMARY KEY(project_id)," +
                        "project_id     VARCHAR(32)," +
                        "date_started   VARCHAR(32)," +
                        "name           VARCHAR(32)," +
                        "description    VARCHAR(255)," +
                        "customers      VARCHAR(128)," +
                        "tools          VARCHAR(255))");

        queries.add(
                "CREATE TABLE IF NOT EXISTS  engineers(" +
                        "PRIMARY KEY(eng_id)," +
                        "eng_id         VARCHAR(32)," +
                        "age            VARCHAR(6)," +
                        "first_name     VARCHAR(16)," +
                        "last_name      VARCHAR(16)," +
                        "address        VARCHAR(128)," +
                        "birth          VARCHAR(32) )"  );

        queries.add(
                "CREATE TABLE IF NOT EXISTS  areas(" +
                        "PRIMARY KEY(area_id)," +
                        "area_id        VARCHAR(32)," +
                        "name           VARCHAR(32)," +
                        "specialty      VARCHAR(256))" );

        queries.add(
                "CREATE TABLE IF NOT EXISTS  development_steps(" +
                        "PRIMARY KEY(dev_step_id)," +
                        "dev_step_id    VARCHAR(32)," +
                        "name           VARCHAR(32))");

        queries.add(
                "CREATE TABLE IF NOT EXISTS  projects_to_engineers( " +
                        "project_id     VARCHAR(32)," +
                        "eng_id         VARCHAR(32)," +
                        "rate           VARCHAR(32)," +
                        "FOREIGN KEY(project_id) REFERENCES projects(project_id) ON DELETE CASCADE,"+
                        "FOREIGN KEY(eng_id) REFERENCES engineers(eng_id) ON DELETE CASCADE,"+
                        "PRIMARY KEY(eng_id,project_id))");


        queries.add(QueryHolder.TABLE_CREATE_PHONES);

        queries.add(
                "CREATE TABLE IF NOT EXISTS  engineer_areas( " +
                        "eng_id         VARCHAR(32)," +
                        "area_id        VARCHAR(32)," +
                        "FOREIGN KEY(eng_id) REFERENCES engineers(eng_id) ON DELETE CASCADE,"+
                        "FOREIGN KEY(area_id) REFERENCES areas(area_id) ON DELETE CASCADE,"+
                        "PRIMARY KEY(eng_id,area_id))");

        queries.add(
                "CREATE TABLE IF NOT EXISTS  milestones( " +
                        "project_id     VARCHAR(32)," +
                        "milestone      VARCHAR(128)," +
                        "due_date       VARCHAR(32)," +
                        "money_granted  VARCHAR(32)," +
                        "FOREIGN KEY(project_id) REFERENCES projects(project_id) ON DELETE CASCADE,"+
                        "PRIMARY KEY(project_id,milestone))" );


        queries.add(
                "CREATE TABLE IF NOT EXISTS  project_areas( " +
                        "project_id     VARCHAR(32)," +
                        "area_id        VARCHAR(32)," +
                        "FOREIGN KEY(area_id) REFERENCES areas(area_id) ON DELETE CASCADE,"+
                        "FOREIGN KEY(project_id) REFERENCES projects(project_id) ON DELETE CASCADE,"+
                        "PRIMARY KEY(project_id,area_id))");


        queries.add(
                "CREATE TABLE IF NOT EXISTS  project_dev_steps( " +
                        "project_id     VARCHAR(32)," +
                        "dev_step_id    VARCHAR(32)," +
                        "dev_tools      VARCHAR(1024)," +
                        "FOREIGN KEY(dev_step_id) REFERENCES development_steps(dev_step_id) ON DELETE CASCADE,"+
                        "FOREIGN KEY(project_id) REFERENCES projects(project_id) ON DELETE CASCADE,"+
                        "PRIMARY KEY(project_id,dev_step_id))");

        //add triggers:
        //queries.add(QueryHolder.TRIGGER_BEFORE_DELETE_PROJECT);


        try {

            for (String query : queries)
                statements.add(connection.prepareStatement(query));

            //execute all queries
            for (PreparedStatement ps : statements)
                ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
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
        //controllers.add(new ACtrl(gui.getTableAreas(),"ARES"));
        //controllers.add(new ECtrl(gui.getTableEng(),"ENGINEERS"));
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
