package org.example.intensive.controllers;

import org.example.intensive.dto.EmployeeDTOshow;
import org.example.intensive.dto.EmployeeDTOsave;
import org.example.intensive.repositories.EmployeesRepository;
import org.example.intensive.services.EmployeesService;
import org.example.intensive.util.EmployeeErrorResponse;
import org.example.intensive.util.EmployeeNotCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final EmployeesService employeesService;
    @Autowired
    public EmployeesController(EmployeesService employeesService, EmployeesRepository employeesRepository) {
        this.employeesService = employeesService;
    }

    @GetMapping()
    public List<EmployeeDTOshow> getEmployees() {
        List <EmployeeDTOshow> employeesDTOShow= employeesService.index();
        return employeesDTOShow;
    }
    @GetMapping({"/{id}"})
    public EmployeeDTOshow getEmployee(@PathVariable("id") int id) {
        EmployeeDTOshow employeesDTOShow= employeesService.show(id);
        return employeesDTOShow;
    }
    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid EmployeeDTOsave employeeDTOsave,
                                             BindingResult bindingResult) {
        EmployeeErrorHandling(bindingResult);
        employeesService.save(employeeDTOsave);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PatchMapping("{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid EmployeeDTOsave employeeDTOsave,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        EmployeeErrorHandling(bindingResult);
        employeesService.update(id, employeeDTOsave);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        employeesService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private void EmployeeErrorHandling(BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new EmployeeNotCreatedException(errorMsg.toString());
        }
    }


    @ExceptionHandler
    private ResponseEntity<EmployeeErrorResponse> handlerException(EmployeeNotCreatedException e){
        EmployeeErrorResponse response = new EmployeeErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
