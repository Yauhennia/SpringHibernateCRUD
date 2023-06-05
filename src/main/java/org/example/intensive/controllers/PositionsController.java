package org.example.intensive.controllers;

import org.example.intensive.dto.EmployeeDTOsave;
import org.example.intensive.dto.PositionDTOsave;
import org.example.intensive.dto.PositionDTOshow;
import org.example.intensive.services.PositionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/positions")
public class PositionsController {
    private final PositionsService positionsService;
    @Autowired
    public PositionsController(PositionsService positionsService) {
        this.positionsService = positionsService;
    }
    @GetMapping()
    public List<PositionDTOshow> getEmployees() {
        return positionsService.index();
    }
    @GetMapping({"/{id}"})
    public PositionDTOshow getPosition(@PathVariable("id") int id) {
       return positionsService.show(id);
    }
    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PositionDTOsave positionDTOsave,
                                             BindingResult bindingResult) {
        positionsService.save(positionDTOsave);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PatchMapping("{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid PositionDTOsave positionDTOsave,
                                             BindingResult bindingResult,
                                             @PathVariable("id") int id) {

        positionsService.update(id, positionDTOsave);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        positionsService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}