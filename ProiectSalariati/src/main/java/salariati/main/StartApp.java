package salariati.main;

import salariati.model.Employee;
import salariati.repository.implementations.EmployeeImpl;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.repository.mock.EmployeeMock;
import salariati.validator.EmployeeValidator;
import salariati.controller.EmployeeController;
import salariati.enumeration.DidacticFunction;
import salariati.view.EmployeeView;

import java.util.List;

//functionalitati
//i.	 adaugarea unui nou angajat (nume, prenume, CNP, functia didactica, salariul de incadrare);
//ii.	 modificarea functiei didactice (asistent/lector/conferentiar/profesor) a unui angajat;
//iii.	 afisarea salariatilor ordonati descrescator dupa salariu si crescator dupa varsta (CNP).
public class StartApp {

//	public static void main(String[] args) {
//
//		EmployeeRepositoryInterface employeesRepository = new EmployeeMock();
//		EmployeeController employeeController = new EmployeeController(employeesRepository);
//
//		for(Employee _employee : employeeController.getEmployeesList())
//			System.out.println(_employee.toString());
//		System.out.println("-----------------------------------------");
//
//		Employee employee = new Employee("LastName", "1234567894321", DidacticFunction.ASISTENT, "2500");
//		employeeController.addEmployee(employee);
//
//		for(Employee _employee : employeeController.getEmployeesList())
//			System.out.println(_employee.toString());
//
//		EmployeeValidator validator = new EmployeeValidator();
//		System.out.println( validator.isValid(new Employee("LastName", "1234567894322", DidacticFunction.TEACHER, "3400")) );
//
//	}

    public static void main(String[] args) {
//        init2();
        EmployeeRepositoryInterface employeesRepository = new EmployeeImpl();
		EmployeeController employeeController = new EmployeeController(employeesRepository);
        EmployeeView employeeView = new EmployeeView(employeeController);

        employeeView.menu();

    }

//    private static void init2() {
//
//        EmployeeRepositoryInterface employeeRepositoryInterface = new EmployeeImpl();
//        EmployeeRepositoryInterface employeesRepository = new EmployeeMock();
//        List<Employee> employeeList = employeesRepository.getEmployeeList();
//
//        for (Employee em :
//                employeeList) {
//            employeeRepositoryInterface.addEmployee(em);
//        }
//    }
}
