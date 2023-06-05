package org.example.intensive.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class EmployeeDTOsave {
    @NotEmpty(message = "First Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String firstName;
    @NotEmpty(message = "Last Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String lastName;

    private String positionName;

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

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String position) {
        this.positionName = position;
    }
}
