package salariati.controller;

import java.util.List;

import salariati.exception.EmployeeException;
import salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;

public class EmployeeController {

    private EmployeeRepositoryInterface employeeRepository;

    public EmployeeController(EmployeeRepositoryInterface employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addEmployee(Employee employee) {
        employeeRepository.addEmployee(employee);
    }

    public List<Employee> getEmployeesList() {
        return employeeRepository.getEmployeeList();
    }

    public void modifyEmployee(Employee oldEmployee, Employee newEmployee) {
        employeeRepository.modifyEmployee(oldEmployee, newEmployee);
    }

    public void deleteEmployee(Employee employee) {
        employeeRepository.deleteEmployee(employee);
    }

    public Employee findByName(String employeeName) throws EmployeeException {
        List<Employee> employeeList = getEmployeesList();

        for (Employee employee :
                employeeList) {
            if (employee.getLastName().equals(employeeName)) {
                return employee;
            }
        }

        throw new EmployeeException("The username is incorrect.");
    }

    public void update(Employee employee) {
        deleteEmployee(employee);
        addEmployee(employee);

    }
}
