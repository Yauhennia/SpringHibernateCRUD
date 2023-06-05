package org.example.intensive.services;

import org.example.intensive.dto.EmployeeDTOshow;
import org.example.intensive.dto.EmployeeDTOsave;
import org.example.intensive.models.Employee;
import org.example.intensive.models.Position;
import org.example.intensive.models.Project;
import org.example.intensive.repositories.EmployeesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeesService {
    private final EmployeesRepository employeesRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public EmployeesService(EmployeesRepository employeesRepository, ModelMapper modelMapper) {
        this.employeesRepository = employeesRepository;
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDTOshow> index(){
        List<EmployeeDTOshow> employeeDTOs = new ArrayList<>();
        List<Employee> employees = new ArrayList<>(employeesRepository.index());
        for (Employee employee : employees){
            EmployeeDTOshow employeeDTOshow= convertToEmployeeDtoShow(employee);
            employeeDTOs.add(employeeDTOshow);
        }
        return employeeDTOs;
    }
    public EmployeeDTOshow show(int id){
        Employee employee = employeesRepository.show(id);
        return convertToEmployeeDtoShow(employee);
    }
    public void save(EmployeeDTOsave employeeDTOsave) {
        Employee employee = convertToEmployeeSave(employeeDTOsave);
        employeesRepository.save(employee, employeeDTOsave.getPositionName());
    }
    public void update(int id, EmployeeDTOsave employeeDTOsave) {
        employeesRepository.update(id, employeeDTOsave);
    }
    public void delete(int id) {
        employeesRepository.delete(id);
    }
    public EmployeeDTOshow convertToEmployeeDtoShow(Employee employee) {
        List<Project> projects = employee.getProjects().stream().toList();
        EmployeeDTOshow employeeDTOshow = modelMapper.map(employee, EmployeeDTOshow.class);
        employeeDTOshow.setPositionName(employee.getPosition().getName());
        for (Project project : projects){
            employeeDTOshow.setProjectsName(project.getName());
        }
        return employeeDTOshow;

    }
    public Employee convertToEmployeeSave(EmployeeDTOsave employeeDTOsave){
        return modelMapper.map(employeeDTOsave, Employee.class);
    }

}
