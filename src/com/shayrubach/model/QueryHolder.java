package com.shayrubach.model;

public class QueryHolder {
    public static final String QUERY_NEW_PROJECT =
            "INSERT INTO projects(" +
                    "project_id," +
                    "date_started," +
                    "name," +
                    "description)" +
                    "VALUES (?,?,?,?);";

    public static final String QUERY_MODIFY_PROJECT =
            "";

    public static final String QUERY_REMOVE_PROJECT =
            "DELETE FROM projects" +
            "WHERE project_id='?'   ";
}
