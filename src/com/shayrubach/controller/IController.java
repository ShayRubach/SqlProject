package com.shayrubach.controller;


import java.sql.SQLException;

public interface IController {
    //boolean connectDb(String dbName);
    //boolean dissconnectDb();
    void loadDb() throws SQLException;
}
