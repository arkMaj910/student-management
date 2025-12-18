package com.learning.studentmanagement.main;

import com.learning.studentmanagement.util.DBConnection;

public class Main {
    public static void main(String[] args) {
        DBConnection.getConnection();
        System.out.println("Application started");
    }
}
