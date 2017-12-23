package com.shayrubach.model;

import com.shayrubach.annotations.BadLogic;
import com.shayrubach.annotations.NestedQuery;

public class QueryHolder {
    public static final String QUERY_NEW_PROJECT =
            "INSERT INTO projects(" +
                    "project_id," +
                    "date_started," +
                    "name," +
                    "description," +
                    "customers," +
                    "tools)" +
                    "VALUES (?,?,?,?,?,?);";

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
            "SELECT project_id,name " +
            "FROM projects " +
            "WHERE name=?;";

    public static final String QUERY_GET_ALL_PROJECT_NAMES =
            "SELECT name FROM projects;";

    @NestedQuery
    public static final String QUERY_GET_ALL_PROJECT_AREAS =
            "SELECT areas.name,areas.area_id " +
            "FROM areas " +
                "INNER JOIN project_areas " +
                "ON areas.area_id = project_areas.area_id " +
                "AND project_areas.project_id =?;";

    @NestedQuery
    public static final String QUERY_GET_PROJECT_AND_AREA_IDS_BY_NAME =
            "SELECT areas.area_id,projects.project_id " +
                    "FROM areas " +
                        "JOIN projects " +
                        "ON projects.name=? " +
                        "WHERE areas.name=?;";

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

    public static final String QUERY_GET_ALL_AREA_NAMES =
            "SELECT name FROM areas";

    public static final String QUERY_ADD_AREA_TO_PROJECT =
            "INSERT IGNORE INTO project_areas " +
                    "(project_id,area_id) " +
                    "VALUES(?,?);";


    @BadLogic
    @NestedQuery
    public static final String GET_AVAILABLE_AREAS_BY_PROJECT_NAME =
            "SELECT area_id,project_id FROM areas,projects WHERE project_id=? AND name NOT IN (SELECT  ";

    public static final String GET_PROJECT_ID_BY_NAME =
            "SELECT project_id " +
                    "FROM projects " +
                    "WHERE name=?;";

    public static final String GET_AREA_ID_BY_NAME =
            "SELECT area_id,name " +
                    "FROM areas" +
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
