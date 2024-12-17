package com.example.passion_flowers.dao;

public class SQLQueries {
    public static final String SQL_SELECT_ALL_USERS =
            "SELECT user_id, name, pass FROM users.users";
//    public static final String SQL_SELECT_STUDENT_BY_LASTNAME =
//            "SELECT student_id, lastname, average FROM users.students WHERE lastname=?";

    public static final String SQL_SELECT_USERS_BY_USER_ID =
            "SELECT user_id, name, pass FROM users.users WHERE user_id=?";
//    public static final String SQL_DELETE_BY_STUDENT_ID =
//            "DELETE FROM users.students WHERE student_id = ?";
//    public static final String SQL_DELETE_BY_STUDENT =
//            "DELETE FROM users.students WHERE student_id =? and  lastname=? and average=?";
//    public static final String SQL_CREATE_NEW_OBJECT =
//            "INSERT INTO users.students (student_id,lastname,average) VALUES (?, ?, ?)";
//    public static final String SQL_UPDATE_ROW =
//            "UPDATE users.students SET lastname =?, average =? WHERE student_id =?";

}
