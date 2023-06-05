package org.example.intensive.dto;

import org.example.intensive.models.Employee;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class ProjectDTOshow {
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 1, max = 50, message = "Name should be between 1 and 50 characters")
    private String name;

    private List<String> employeesName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEmployeesName() {
        return employeesName;
    }

    public void setEmployeesName(List<String> employeesName) {
        this.employeesName = employeesName;
    }
}
