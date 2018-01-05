package com.shayrubach.model;

import com.shayrubach.annotations.*;

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

    public static final String QUERY_ADD_PROJECT_TO_DEV_STEP =
            "INSERT INTO project_dev_steps(" +
                    "project_id," +
                    "dev_step_id," +
                    "dev_tools) " +
                    "VALUES(?,?,?);";

    //TODO 08: add transaction here to check if area name exists already? if so . rollback.
    public static final String QUERY_NEW_AREA =
            "INSERT INTO areas(" +
                    "area_id," +
                    "name," +
                    "specialty) " +
                    "VALUES (?,?,?);";

    public static final String QUERY_NEW_ENGINEER =
            "INSERT INTO engineers(" +
                    "eng_id," +
                    "age," +
                    "first_name," +
                    "last_name," +
                    "address," +
                    "birth)" +
                    "VALUES (?,?,?,?,?,?);";

    public static final String QUERY_ADD_MILESTONE_TO_PROJECT =
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


    public static final String QUERY_GET_MONTHLY_MILESTONES =
            "SELECT milestones.milestone," +
                    "milestones.due_date AS date," +
                    "milestones.money_granted AS money," +
                    "projects.name " +
            "FROM milestones " +
            "JOIN projects ON milestones.project_id=projects.project_id " +
            "WHERE SUBSTRING(due_date,4,2)=? OR " +
            "SUBSTRING(milestones.due_date,4,2)=CONCAT('0',?)" +
            "ORDER BY date";

    public static final String QUERY_GET_ALL_MILESTONES =
            "SELECT milestones.milestone," +
                    "milestones.due_date AS date," +
                    "milestones.money_granted AS money," +
                    "projects.name " +
                    "FROM milestones " +
                    "JOIN projects ON milestones.project_id=projects.project_id " +
                    "ORDER BY date";

    public static final String QUERY_GET_PROJECT_RATE =
            "SELECT AVG(rate) " +
            "FROM projects_to_engineers " +
            "WHERE project_id=?";

    public static final String QUERY_GET_ALL_PROJECT_NAMES =
            "SELECT name FROM projects;";

    public static final String QUERY_NEW_PHONE_TO_ENG =
            "INSERT IGNORE INTO phones " +
                    "(eng_id,phone) " +
                    "VALUES(?,?);";

    public static final String QUERY_MODIFY_PROJECT =
            "";


    public static final String SUM_MONTHLY_REVENUES =
            "SELECT SUM(money_granted) " +
                    "FROM milestones " +
                    "WHERE SUBSTRING(due_date,4,2)=? OR " +
                    "SUBSTRING(milestones.due_date,4,2)=CONCAT('0',?)" +
                    "ORDER BY milestones.due_date";

    public static final String SUM_TOTAL_REVENUES =
            "SELECT SUM(money_granted) " +
                    "FROM milestones " +
                    "ORDER BY due_date";


    public static final String QUERY_REMOVE_PROJECT =
            "DELETE FROM projects " +
            "WHERE project_id=?;";

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


    public static final String QUERY_NEW_ENG_PROJ =
            "INSERT IGNORE INTO projects_to_engineers " +
                    "(project_id,eng_id,rate) " +
                    "VALUES(?,?,?);";

    public static final String QUERY_GET_AVAILABLE_PROJECTS_BY_ENG_ID =
            "SELECT name FROM projects " +
            "WHERE project_id IN " +
                "(SELECT project_id FROM projects_to_engineers " +
                "WHERE eng_id=?);";

    public static final String QUERY_GET_PROJ_ID_AND_RATE =
            "SELECT project_id,rate " +
                    "FROM projects_to_engineers " +
                    "WHERE eng_id=?";

    public static final String QUERY_ADD_AREA_TO_ENG =
            "INSERT IGNORE INTO engineer_areas " +
                    "(eng_id,area_id) " +
                    "VALUES(?,?);";

    public static final String QUERY_REMOVE_ENGINEER =
            "DELETE FROM engineers " +
                    "WHERE eng_id=?;";

    public static final String QUERY_REMOVE_AREA =
            "DELETE FROM areas " +
                    "WHERE area_id=?;";

    public static final String GET_PROJET_RATE =
            "SELECT rate FROM projects_to_engineers " +
                    "WHERE project_id=?" +
                    "AND eng_id=?;";

    public static final String GET_PROJECT_ID =
            "SELECT project_id FROM projects " +
                    "WHERE name=?;";

    public static final String QUERY_GET_PROJ_NAME_BY_ID =
            "SELECT name " +
                    "FROM projects " +
                    "WHERE project_id=?;";

    @NestedQuery
    @CorrelatedSubquery
    public static final String GET_ENG_AREA =
            "SELECT name FROM areas " +
                    "WHERE area_id IN " +
                    "(SELECT area_id FROM engineer_areas " +
                    "WHERE eng_id=?);";

    public static final String QUERY_UPDATE_PROJECT_RATE =
            "UPDATE projects_to_engineers " +
                    "SET rate=? " +
                    "WHERE project_id=? " +
                    "AND eng_id=?;";

    public static final String QUERY_GET_PROJ_NAME_AND_RATE =
            "SELECT rate, projects.name " +
                    "FROM projects_to_engineers,projects " +
                    "WHERE eng_id=? " +
                    "AND projects_to_engineers.project_id=projects.project_id";

    public static final String QUERY_GET_PROJ_AVG_RATE =
            "SELECT AVG(projects_to_engineers.rate) " +
                    "FROM projects_to_engineers " +
                    "WHERE projects_to_engineers.project_id=? ";

    public static final String QUERY_GET_TOP_ENGINEERS =
            "SELECT COUNT(project_id) as cnt," +
                    "projects_to_engineers.eng_id," +
                    "engineers.first_name," +
                    "engineers.last_name " +
                    "FROM projects_to_engineers " +
                    "JOIN engineers ON engineers.eng_id = projects_to_engineers.eng_id " +
                    "GROUP BY projects_to_engineers.eng_id " +
                    "ORDER BY cnt DESC " +
                    "LIMIT 3";

    @NestedQuery
    @CorrelatedSubquery
    public static final String QUERY_GET_AREAS_OF_PROJET_BY_NAME =
            "SELECT name FROM areas WHERE area_id IN " +
                    "(SELECT area_id FROM project_areas WHERE project_id IN " +
                    "(SELECT project_id FROM projects WHERE name=?));";


    @SQLTrigger
    @NestedQuery
    public static final String TRIGGER_BEFORE_DELETE_PROJECT =
            "DELIMITER $$ " +
            "CREATE TRIGGER before_delete_projects " +
                    "BEFORE DELETE ON projects " +
                    "FOR EACH ROW " +
                    "BEGIN " +
                        "DELETE FROM " +
                            "project_areas," +
                            "projects_to_engineers," +
                            "project_dev_steps " +
                        "WHERE project_id=old.project_id " +
                    "END $$ " +
                    "DELIMITER ;";

    @BadLogic
    @NestedQuery
    public static final String QUERY_GET_AVAILABLE_AREAS_BY_PROJECT_NAME =
            "SELECT area_id,project_id FROM areas,projects WHERE project_id=? AND name NOT IN (SELECT  ";

    @NestedQuery
    public static final String QUERY_GET_MILESTONE_BY_PROJECT =
            "SELECT milestone FROM milestones "+
                    "WHERE IN   (SELECT project_id "+
                    "FROM projects "+
                    "WHERE project_id=?);";

    @CorrelatedSubquery
    @NestedQuery
    public static final String QUERY_GET_ALL_PROJECT_AREAS =
            "SELECT areas.name,areas.area_id " +
                    "FROM areas " +
                        "INNER JOIN project_areas " +
                        "ON areas.area_id = project_areas.area_id " +
                        "AND project_areas.project_id =?;";

    @CorrelatedSubquery
    @NestedQuery
    public static final String QUERY_GET_PROJECT_AND_AREA_IDS_BY_NAME =
            "SELECT projects.project_id,areas.area_id " +
                    "FROM areas " +
                        "JOIN projects " +
                        "ON projects.name=? " +
                        "WHERE areas.name=?;";

    @CorrelatedSubquery
    @NestedQuery
    public static final String QUERY_GET_ENG_BY_PROJ_ID_AND_AREA_ID =
            "SELECT engineers.first_name,engineers.last_name,engineers.eng_id " +
                    "FROM engineers " +
                    "WHERE engineers.eng_id IN  " +
                    "(SELECT engineers.eng_id " +
                    "FROM engineers " +
                    "WHERE engineers.eng_id IN " +
                    "(SELECT projects_to_engineers.eng_id " +
                    "FROM projects_to_engineers " +
                    "WHERE projects_to_engineers.project_id=?) " +
                    "AND engineers.eng_id IN " +
                    "(SELECT engineer_areas.eng_id " +
                    "FROM engineer_areas " +
                    "WHERE engineer_areas.area_id=?))";

    @TableCreation
    public static final String TABLE_CREATE_PHONES =
            "CREATE TABLE IF NOT EXISTS  phones( " +
                    "eng_id         VARCHAR(32),"  +
                    "phone          VARCHAR(32)," +
                    "FOREIGN KEY (eng_id) REFERENCES engineers(eng_id) ON DELETE CASCADE,"+
                    "PRIMARY KEY(eng_id,phone))";

    public static final String QUERY_GET_PROJECT_DEV_STEP =
            "SELECT name " +
                    "FROM development_steps " +
                    "WHERE dev_step_id IN " +
                    "(SELECT dev_step_id " +
                    "FROM project_dev_steps " +
                    "WHERE project_id=?); ";

    public static final String QUERY_MODIFY_ENG =
            "UPDATE engineers " +
                    "SET " +
                    "first_name=?," +
                    "last_name=?," +
                    "address=?," +
                    "birth=? " +
                    "WHERE eng_id=?";
    public static final String QUERY_GET_PROJECT_DATA =
            "SELECT * FROM projects WHERE project_id=?";

    public static final String QUERY_GET_ENG_DATA =
            "SELECT * FROM engineers WHERE eng_id=?";

    public static final String QUERY_GET_PHONES_BY_ENG_ID =
            "select phone from phones where eng_id=?";

    public static final String DAA_STEP = "e60d69a1";
}
