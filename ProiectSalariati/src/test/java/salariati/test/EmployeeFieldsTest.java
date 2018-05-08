package salariati.test;

import static org.junit.Assert.*;

import salariati.controller.EmployeeController;
import salariati.exception.EmployeeException;
import salariati.model.Employee;

import org.junit.*;

import salariati.repository.implementations.EmployeeImpl;
import salariati.repository.interfaces.EmployeeRepositoryInterface;
import salariati.validator.EmployeeValidator;
import salariati.enumeration.DidacticFunction;

public class EmployeeFieldsTest {

	private EmployeeValidator employeeValidator;
	private Employee employee;
	private EmployeeController employeeController;
	
	@Before
	public void setUp() {
		EmployeeRepositoryInterface repo = new EmployeeImpl();
		employeeController = new EmployeeController(repo);
		employeeValidator = new EmployeeValidator();
		employee = new Employee("Ardelean", "1234567891234", DidacticFunction.ASISTENT, 1234.0);
	}
	
	@Test
	public void testValidLastName() {
		employee.setLastName("ValidLastName");
		assertTrue(employeeValidator.isValid(employee));
	}
	
	@Test
	public void testInvalidLastName() {
		employee.setLastName("Invalid#LastName");
		assertFalse(employeeValidator.isValid(employee));
		employee.setLastName("Invalid!@1");
		assertFalse(employeeValidator.isValid(employee));
	}
	
	@Test
	public void testValidCNP() {
		assertTrue(employeeValidator.isValid(employee));
		employee.setCnp("1910509055057");
		assertTrue(employeeValidator.isValid(employee));
	}
	@Test
	public void testValidSalary() {
		assertTrue(employeeValidator.isValid(employee));
		employee.setSalary(1500.0);
		assertTrue(employeeValidator.isValid(employee));
	}

	@Test
	public void testFindByName(){
		employeeController.addEmployee(employee);

		try {
			assertEquals(employeeController.findByName("Ardelean").getCnp(), employee.getCnp());
		} catch (EmployeeException e) {
			e.printStackTrace();
		}
		try {
			assertNull(employeeController.findByName("Popescu").getCnp());
		} catch (EmployeeException e) {
			assertTrue(true);
		}

	}


}
