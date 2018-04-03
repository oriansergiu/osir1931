package salariati.repository.mock;

import java.util.ArrayList;
import java.util.List;

import salariati.enumeration.DidacticFunction;

import salariati.model.Employee;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.validator.EmployeeValidator;

public class EmployeeMock implements EmployeeRepositoryInterface {

	private List<Employee> employeeList;
	private EmployeeValidator employeeValidator;
	
	public EmployeeMock() {
		
		employeeValidator = new EmployeeValidator();
		employeeList = new ArrayList<Employee>();
		
		Employee Ionel   = new Employee("Pacuraru", "1234567890876", DidacticFunction.ASISTENT, 2500.0);
		Employee Mihai   = new Employee("Dumitrescu", "1234567890876", DidacticFunction.LECTURER, 3000.0);
		Employee Ionela  = new Employee("Ionescu", "1234567890876", DidacticFunction.LECTURER, 3200.0);
		Employee Mihaela = new Employee("Pacuraru", "1234567890876", DidacticFunction.ASISTENT, 1500.0);
		Employee Vasile  = new Employee("Georgescu", "1234567890876", DidacticFunction.TEACHER,  2200.0);
		Employee Marin   = new Employee("Puscas", "1234567890876", DidacticFunction.TEACHER,  2100.0);
		
		employeeList.add( Ionel );
		employeeList.add( Mihai );
		employeeList.add( Ionela );
		employeeList.add( Mihaela );
		employeeList.add( Vasile );
		employeeList.add( Marin );
	}
	
	@Override
	public boolean addEmployee(Employee employee) {
		if ( employeeValidator.isValid(employee)) {
			employeeList.add(employee);
			return true;
		}
		return false;
	}
	
	@Override
	public void deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
	}

	@Override
	public void modifyEmployee(Employee oldEmployee, Employee newEmployee) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	@Override
	public List<Employee> getEmployeeByCriteria(String criteria) {
		// TODO Auto-generated method stub
		return null;
	}

}
