package org.example.intensive.services;

import org.example.intensive.dto.EmployeeDTOshow;
import org.example.intensive.dto.ProjectDTOshow;
import org.example.intensive.models.Employee;
import org.example.intensive.models.Project;
import org.example.intensive.repositories.ProjectsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectsService {
    private final ProjectsRepository projectsRepository;
    private final ModelMapper modelMapper;
    @Autowired
    public ProjectsService(ProjectsRepository projectsRepository, ModelMapper modelMapper) {
        this.projectsRepository = projectsRepository;
        this.modelMapper = modelMapper;
    }
    public List<ProjectDTOshow> index(){
        List<ProjectDTOshow> projectDTOshows = new ArrayList<>();
        List<Project> projects = new ArrayList<>(projectsRepository.index());
        for (Project project : projects){
            List<String> employeeNames = new ArrayList<>();
            ProjectDTOshow projectDTOshow = modelMapper.map(project, ProjectDTOshow.class);
            for (Employee employee: project.getEmployees()){
                employeeNames.add(String.valueOf(new StringBuilder(employee.getFirstName()).append(" ").append(employee.getLastName())));
            }
            projectDTOshow.setEmployeesName(employeeNames);
            projectDTOshows.add(projectDTOshow);
        }
        return projectDTOshows;
    }
}
