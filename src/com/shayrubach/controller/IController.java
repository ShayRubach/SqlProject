package com.shayrubach.controller;


public interface IController {
    boolean connectDb(String dbName);
    boolean dissconnectDb();
}
