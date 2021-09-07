package spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.rest.entity.Employee;
import spring.rest.exception_handling.NoSuchEmployeeException;
import spring.rest.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployees(@PathVariable int id) {
        Employee emp = employeeService.getEmployee(id);
        if (emp == null) {
            throw new NoSuchEmployeeException(String.format("There is no employee with ID = %d in Database", id));
        }
        return emp;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee emp = employeeService.getEmployee(id);
        if (emp == null) {
            throw new NoSuchEmployeeException(String.format("There is no employee with ID = %d in Database", id));
        }
        employeeService.deleteEmployee(id);
        return String.format("Employee with ID = %d was deleted", id);
    }
}
