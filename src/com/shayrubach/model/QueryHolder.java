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

    public static final String QUERY_GET_ALL_PROJECTS =
            "SELECT * FROM projects;";

    public static final String QUERY_GET_ALL_AREAS =
            "SELECT * FROM areas;";

    public static final String QUERY_GET_ALL_ENGINEERS =
            "SELECT * FROM engineers;";
}
