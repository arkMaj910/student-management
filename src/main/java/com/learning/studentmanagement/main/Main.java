package com.learning.studentmanagement.main;

import com.learning.studentmanagement.dao.StudentDAO;
import com.learning.studentmanagement.model.Student;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        System.out.println("""
                Press :\s
                    1. Insert Record
                    2. See all records
                    3. Search by name
                    4. Exit""");

        System.out.println("Enter choice : ");
        int choice = in.nextInt();
        in.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.println("Enter name: ");
                String name = in.nextLine();
                System.out.println("Enter email: ");
                String email = in.nextLine();
                System.out.println("Enter age: ");
                int age = in.nextInt();
                in.nextLine();
                System.out.println("Enter department: ");
                String dept = in.nextLine();
                Student student = new Student.Builder()
                        .name(name)
                        .email(email)
                        .age(age)
                        .department(dept)
                        .build();

                dao.addStudent(student);
                System.out.println("Student added");
            }

            case 2 -> {
                List<Student> list = dao.getAllStudents();
                for (Student student : list) {
                    System.out.println(student);
                }
            }

            case 3 -> {
                System.out.print("Enter name: ");
                String name = in.nextLine();
                List<Student> list = dao.searchByName(name);
                for (Student stud : list) {
                    System.out.println(stud);
                }
            }

            default -> System.exit(0);
        }

        in.close();
    }
}
