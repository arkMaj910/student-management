package com.learning.studentmanagement.model;

public class Student {
    private final int id;
    private final String name;
    private final String email;
    private final int age;
    private final String department;

    private Student(Builder builder) {
        id = builder.id;
        name = builder.name;
        email = builder.email;
        age = builder.age;
        department = builder.department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", department='" + department + '\'' +
                '}';
    }

    public static class Builder {
        int id;
        String name;
        String email;
        int age;
        String department;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder department(String department) {
            this.department = department;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
