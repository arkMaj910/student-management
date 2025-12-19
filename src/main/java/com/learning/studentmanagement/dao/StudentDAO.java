package com.learning.studentmanagement.dao;

import com.learning.studentmanagement.model.Student;
import com.learning.studentmanagement.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public void addStudent(Student s) {
        String query =
                "INSERT INTO students(name, admission_no, email, age, department) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, s.getName());
            stmt.setString(2, s.getEmail());
            stmt.setInt(3, s.getAge());
            stmt.setString(4, s.getDepartment());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getAllStudents() {
        String query = "SELECT * FROM STUDENTS";
        List<Student> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                list.add(new Student.Builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .age(rs.getInt("age"))
                        .department(rs.getString("department"))
                        .build());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public List<Student> searchByName(String name) {
        String query = """
                SELECT id, name, admission_no, email, age, department
                   FROM students
                   WHERE name = ?""";

        List<Student> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Student.Builder()
                        .name(rs.getString("name"))
                        .id(rs.getInt("id"))
                        .admissionNo(rs.getString("admission_no"))
                        .email(rs.getString("email"))
                        .age(rs.getInt("age"))
                        .department(rs.getString("department"))
                        .build());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
