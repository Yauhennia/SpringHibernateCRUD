package org.example.intensive.dto;

import org.example.intensive.models.Employee;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

public class PositionDTOshow {

    private String name;

    public List<String> employeesName;

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
