package org.example.intensive.dto;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDTOshow {

    private int id;

    private String firstName;

    private String lastName;

    private String positionName;

    private List<String> projectsName = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPositionNamee() {
        return positionName;
    }

    public void setPositionName(String position) {
        this.positionName = position;
    }

    public List<String> getProjectsName() {
        return projectsName;
    }
    public void setProjectsName(String s) {
            projectsName.add(s);
        }

}

