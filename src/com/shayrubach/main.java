package com.shayrubach;

import com.shayrubach.controller.gui.AppController;
import com.shayrubach.view.*;

import java.sql.*;
import java.util.ArrayList;


public class main {


    public static void main(String[] args)  {
        try {
            AppController appController = new AppController(new GuiMainPanel());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}



