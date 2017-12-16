package com.shayrubach.model;

import com.shayrubach.annotations.NestedQuery;

public class QueryHolder {
    public static final String QUERY_NEW_PROJECT =
            "INSERT INTO projects(" +
                    "project_id," +
                    "date_started," +
                    "name," +
                    "description)" +
                    "VALUES (?,?,?,?);";

    public static final String QUERY_NEW_AREA =
            "INSERT INTO areas(" +
                    "area_id," +
                    "name," +
                    "specialty) " +
                    "VALUES (?,?,?);";

    public static final String QUERY_NEW_MILESTONE =
            "INSERT INTO milestones (" +
                    "project_id," +
                    "milestone," +
                    "due_date," +
                    "money_granted)" +
                    "VALUES (?,?,?,?);";

    public static final String QUERY_PROJECT_ID_BY_NAME =
            "SELECT project_id" +
            "FROM projects" +
            "WHERE project_id=?;";


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

    public static final String QUERY_GET_AREA_BY_NAME =
            "SELECT * FROM areas " +
            "WHERE name=?;";


    @NestedQuery
    public static final String QUERY_GET_MILESTONE_BY_PROJECT =
            "SELECT milestone FROM milestones "+
                    "WHERE IN   (SELECT project_id "+
                    "FROM projects "+
                    "WHERE project_id=?);";

/*
    SELECT 	project_id,milestone
    FROM 	milestones
    WHERE 	project_id=(SELECT project_id
    FROM projects
    WHERE project_id='8c410e38');
*/







}
