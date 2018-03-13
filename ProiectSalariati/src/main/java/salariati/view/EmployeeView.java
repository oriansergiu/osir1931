package salariati.view;

import salariati.controller.EmployeeController;
import salariati.enumeration.DidacticFunction;
import salariati.exception.EmployeeException;
import salariati.model.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class EmployeeView {

    private EmployeeController employeeController;

    public EmployeeView(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public void menu() {
        showMenu();
        menuSelection();
    }

    public void showMenu() {
        System.out.println("--MENU--\n" +
                "1. Add employee\n" +
                "2. Update employee\n" +
                "3. Show employee\n" +
                "0. Exit.\n");
    }

    public void menuSelection() {
        System.out.println("Do: ");
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();

        switch (number) {
            case 1:
                addEmployee();
                break;
            case 2:
                updateEmployee();
                break;
            case 3:
                getAll();
                break;
            case 0:
                break;
            default:
                noImpl();
                break;

        }
    }

    private void noImpl() {
        System.out.println("That option doesn't exist yet.");
        menuSelection();
    }

    private void getAll() {

        List<Employee> employeeList = employeeController.getEmployeesList();

        employeeList.sort(Comparator.comparing(Employee::getSalary)
                .reversed()
                .thenComparing(Comparator.comparing(Employee::getCnp)));

        employeeList.forEach(System.out::println);
        menu();
    }

    private void updateEmployee() {
        System.out.println("Employee username: ");
        Scanner in = new Scanner(System.in);
        String employeeName = in.next();

        Employee employee = null;
        try {
            employee = employeeController.findByName(employeeName);
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
            updateEmployee();
        }

        DidacticFunction didacticFunction = getFunction(in);
        if (didacticFunction == null){
            updateEmployee();
        }else {
            employee.setFunction(didacticFunction);
        }

        employeeController.update(employee);
        System.out.println("The employee function was successfully updated.");
        menu();

    }

    private void addEmployee() {
        System.out.println("--Insert data--");
        Scanner in = new Scanner(System.in);
        System.out.println("Username: ");
        String username = in.next();
        System.out.println("CNP: ");
        String cnp = in.next();
        System.out.println("Function: ");
        DidacticFunction didacticFunction = getFunction(in);
        System.out.println("Salary: ");
        String salary = in.next();

        Employee employee = new Employee(username, cnp, didacticFunction, salary);
        employeeController.addEmployee(employee);
        System.out.println("Employee was successfully saved.");
        menu();
    }

    private DidacticFunction getFunction(Scanner in){
        System.out.println("Chose one function:\n" +
                "1. Assistant\n" +
                "2. Teacher\n" +
                "3. Lecturer\n");

        int function = in.nextInt();
        switch (function) {
            case 1:
                return DidacticFunction.ASISTENT;
            case 2:
                return DidacticFunction.TEACHER;
            case 3:
                return DidacticFunction.LECTURER;
            default:
                return null;
        }
    }
}
