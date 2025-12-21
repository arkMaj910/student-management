package com.learning.studentmanagement.dao;

import com.learning.studentmanagement.model.Student;
import com.learning.studentmanagement.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDAO {
    public void addStudent(Student s) {
        String query =
                "INSERT INTO students(name, admission_no, email, age, department) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, s.getName());
            stmt.setString(2, s.getAdmissionNo());
            stmt.setString(3, s.getEmail());
            stmt.setInt(4, s.getAge());
            stmt.setString(5, s.getDepartment());
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
                        .admissionNo(rs.getString("admission_no"))
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
            if (!rs.next()) {
                System.out.println("No student with name " + name);
            } else {
                do {
                    list.add(new Student.Builder()
                            .name(rs.getString("name"))
                            .id(rs.getInt("id"))
                            .admissionNo(rs.getString("admission_no"))
                            .email(rs.getString("email"))
                            .age(rs.getInt("age"))
                            .department(rs.getString("department"))
                            .build());
                } while (rs.next());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public Optional<Student> searchByAdmNo(String admNo) {
        String sql = """
                SELECT id, name, admission_no, age, email, department
                FROM students
                WHERE admission_no = ?""";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, admNo);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            } else {
                Student s = new Student.Builder()
                        .id(rs.getInt(1))
                        .name(rs.getString(2))
                        .admissionNo(rs.getString(3))
                        .age(rs.getInt(4))
                        .email(rs.getString(5))
                        .department(rs.getString(6))
                        .build();

                return Optional.of(s);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
