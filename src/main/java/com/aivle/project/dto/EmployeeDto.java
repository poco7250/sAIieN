package com.aivle.project.dto;

import com.aivle.project.enums.Dept;
import com.aivle.project.enums.Position;
import com.aivle.project.enums.Role;
import com.aivle.project.enums.Team;
import lombok.*;

import java.time.LocalDate;

public class EmployeeDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Post{
        private String employeeId;
        private String employeeName;
        private String password;
        private String position;
        private String dept;
        private String team;
        private String hireDate;
        private Float baseSalary;
        private String accessPermission;
        private String passwordAnswer;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Get{
        private String employeeId;
        private String employeeName;
        private LocalDate hireDate;
        private LocalDate terminationDate;
        private float baseSalary;
        private Position position;
        private Role accessPermission;
        private Dept dept;
        private Team team;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class Patch{
        private String employeeId;
        private String existPassword;
        private String newPassword;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class GetId{
        private String employeeId;
    }
}
