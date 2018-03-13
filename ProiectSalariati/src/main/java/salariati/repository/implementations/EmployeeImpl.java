package salariati.repository.implementations;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import salariati.exception.EmployeeException;

import salariati.model.Employee;

import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.validator.EmployeeValidator;

public class EmployeeImpl implements EmployeeRepositoryInterface {

    private final String employeeDBFile = "C:\\Users\\Sergiu\\Desktop\\Facultate\\Sem 6\\Validarea sistemelor soft\\Lab1\\02-ProiectSalariati\\ProiectSalariati\\src\\main\\java\\db\\employees.txt";
    private EmployeeValidator employeeValidator = new EmployeeValidator();

    @Override
    public boolean addEmployee(Employee employee) {
        if (employeeValidator.isValid(employee)) {
            BufferedWriter bw = null;
            try {
                File file = new File(employeeDBFile);
                FileWriter out = new FileWriter(file, true);
                bw = new BufferedWriter(out);
                bw.write(employee.toString()+"\n");
                bw.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void deleteEmployee(Employee employee) {
        File file = new File(employeeDBFile);
        List<String> lines = null;
        try {
            lines = FileUtils.readLines(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> updatedLines = lines.stream().filter(s -> !s.contains(employee.getLastName())).collect(Collectors.toList());
        try {
            FileUtils.writeLines(file, updatedLines);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void modifyEmployee(Employee oldEmployee, Employee newEmployee) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = new ArrayList<Employee>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(employeeDBFile));
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                Employee employee = new Employee();
                try {
                    employee = employee.getEmployeeFromString(line, counter);
                    employeeList.add(employee);
                } catch (EmployeeException ex) {
                    System.err.println("Error while reading: " + ex.toString());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error while reading: " + e);
        } catch (IOException e) {
            System.err.println("Error while reading: " + e);
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Error while closing the file: " + e);
                }
        }

        return employeeList;
    }


    @Override
    public List<Employee> getEmployeeByCriteria(String criteria) {
        List<Employee> employeeList = new ArrayList<Employee>();

        return employeeList;
    }

}
