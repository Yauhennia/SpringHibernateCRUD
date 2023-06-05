package org.example.intensive.controllers;

import org.example.intensive.dto.EmployeeDTOshow;
import org.example.intensive.dto.ProjectDTOshow;
import org.example.intensive.services.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectsController {
    private final ProjectsService projectsService;
    @Autowired
    public ProjectsController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }
    @GetMapping()
    public List<ProjectDTOshow> getProjects() {
        List <ProjectDTOshow> projectDTOshows= projectsService.index();
        return projectDTOshows;
    }
}
