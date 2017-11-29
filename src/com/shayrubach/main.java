package com.shayrubach;

import com.shayrubach.controller.gui.AppController;
import com.shayrubach.view.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;




public class main {


    public static void main(String[] args)  {
        AppController appController = new AppController(new GuiMainPanel());


    }

}


/*

        String DRIVER = "com.mysql.jdbc.Driver" ;
        String URL = "jdbc:mysql://localhost:3306/softwarecompany?createDatabaseIfNotExist=true" ;
        Connection connection = null;

        ArrayList<PreparedStatement> statements = new ArrayList<PreparedStatement>();
        ArrayList<String> queries = new ArrayList<>();

        //form table queries
        queries.add(
                "CREATE TABLE IF NOT EXISTS  projects( " +
                    "PRIMARY KEY(project_id)," +
                    "project_id     VARCHAR(32)," +
                    "date_started   VARCHAR(32)," +
                    "name           VARCHAR(32)," +
                    "description    VARCHAR(255))");

        queries.add(
                "CREATE TABLE IF NOT EXISTS  engineers(" +
                    "PRIMARY KEY(eng_id)," +
                    "eng_id         VARCHAR(32)," +
                    "age            VARCHAR(3)," +
                    "first_name     VARCHAR(16)," +
                    "last_name      VARCHAR(16)," +
                    "birth          VARCHAR(32) )"  );

        queries.add(
                "CREATE TABLE IF NOT EXISTS  areas(" +
                    "PRIMARY KEY(area_id)," +
                    "area_id        VARCHAR(32)," +
                    "name           VARCHAR(32)," +
                    "specialty      VARCHAR(32))" );

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
                    "FOREIGN KEY(project_id) REFERENCES projects(project_id),"+
                    "FOREIGN KEY(eng_id) REFERENCES engineers(eng_id),"+
                    "PRIMARY KEY(eng_id,project_id))");


        queries.add(
                "CREATE TABLE IF NOT EXISTS  phones( " +
                    "eng_id         VARCHAR(32),"  +
                    "phone          VARCHAR(32)," +
                    "FOREIGN KEY (eng_id) REFERENCES engineers(eng_id),"+
                    "PRIMARY KEY(eng_id,phone))");

        queries.add(
                "CREATE TABLE IF NOT EXISTS  engineer_areas( " +
                    "eng_id         VARCHAR(32)," +
                    "area_id        VARCHAR(32)," +
                    "FOREIGN KEY(eng_id) REFERENCES engineers(eng_id),"+
                    "FOREIGN KEY(area_id) REFERENCES areas(area_id),"+
                    "PRIMARY KEY(eng_id,area_id))");

        queries.add(
                "CREATE TABLE IF NOT EXISTS  milestones( " +
                    "project_id     VARCHAR(32)," +
                    "milestone      VARCHAR(128)," +
                    "due_date       VARCHAR(32)," +
                    "money_granted  VARCHAR(32)," +
                    "FOREIGN KEY(project_id) REFERENCES projects(project_id),"+
                    "PRIMARY KEY(project_id,milestone))" );


        queries.add(
                "CREATE TABLE IF NOT EXISTS  project_areas( " +
                    "project_id     VARCHAR(32)," +
                    "area_id        VARCHAR(32)," +
                    "FOREIGN KEY(area_id) REFERENCES areas(area_id),"+
                    "FOREIGN KEY(project_id) REFERENCES projects(project_id),"+
                    "PRIMARY KEY(project_id,area_id))");


        queries.add(
                "CREATE TABLE IF NOT EXISTS  project_dev_steps( " +
                    "project_id     VARCHAR(32)," +
                    "dev_step_id    VARCHAR(32)," +
                    "dev_tools      VARCHAR(1024)," +
                    "FOREIGN KEY(dev_step_id) REFERENCES development_steps(dev_step_id),"+
                    "FOREIGN KEY(project_id) REFERENCES projects(project_id),"+
                    "PRIMARY KEY(project_id,dev_step_id))");


        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,"root","");

            for(String query : queries)
                statements.add(connection.prepareStatement(query));

            //execute all queries
            for (PreparedStatement ps : statements)
                ps.executeUpdate();

            if(connection != null)
                connection.close();
        }
        catch (Exception e){
            System.out.println(e);
        }

 */