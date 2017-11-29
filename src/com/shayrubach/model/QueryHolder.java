package com.shayrubach.model;

public class QueryHolder {
    public static final String newProjectQuery =
            "INSERT INTO projects(" +
                    "project_id," +
                    "date_started," +
                    "name," +
                    "description)" +
                    "VALUES (?,?,?,?);";

    public static final String modifyProjectQuery =
            "";

}
