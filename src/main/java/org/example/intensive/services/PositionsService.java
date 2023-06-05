package org.example.intensive.services;

import org.example.intensive.dto.EmployeeDTOsave;
import org.example.intensive.dto.EmployeeDTOshow;
import org.example.intensive.dto.PositionDTOsave;
import org.example.intensive.dto.PositionDTOshow;
import org.example.intensive.models.Employee;
import org.example.intensive.models.Position;
import org.example.intensive.repositories.PositionsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionsService {
    private final PositionsRepository positionsRepository;
    private final ModelMapper modelMapper;

    public PositionsService(PositionsRepository positionsRepository, ModelMapper modelMapper) {
        this.positionsRepository = positionsRepository;
        this.modelMapper = modelMapper;
    }
    public List<PositionDTOshow> index(){
        List<PositionDTOshow> positionDTOs = new ArrayList<>();
        List<Position> positions = new ArrayList<>(positionsRepository.index());
        for (Position position : positions){
            positionDTOs.add(positionToDto(position));
        }
        return positionDTOs;
    }

    public PositionDTOshow show(int id){
        Position position = positionsRepository.show(id);
        return positionToDto(position);

    }

    public void save(PositionDTOsave positionDTOsave) {
        Position position = modelMapper.map(positionDTOsave, Position.class);
        positionsRepository.save(position);
    }
    public void update(int id, PositionDTOsave positionDTOsave){
        Position position = modelMapper.map(positionDTOsave, Position.class);
        positionsRepository.update(id, position);
    }
    public void delete(int id) {positionsRepository.delete(id);
    }
    private PositionDTOshow positionToDto(Position position) {
        PositionDTOshow positionDTOshow = new PositionDTOshow();
        List<String> employeeNames = new ArrayList<>();
        positionDTOshow.setName(position.getName());
        for (Employee employee: position.getEmployees()){
            employeeNames.add(String.valueOf(new StringBuilder(employee.getFirstName()).append(" ").append(employee.getLastName())));
        }
        positionDTOshow.setEmployeesName(employeeNames);
        return positionDTOshow;
    }
}
