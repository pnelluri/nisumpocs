package SpringJdbcExample.springjdbcexample;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.TXMS.IsolationTesting;
import com.TXMS.PropagationTesting;
import com.TXMS.TestClass;
import com.bo.Employee;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TransactionTest {
	@Autowired
	TestClass testClass;
	@Autowired
	PropagationTesting propagationTesting;
	
	@Autowired
	IsolationTesting isolationTesting;
	
	@Test
	public void mandatoryTest() {
		try {
			testClass.propagationMandatory();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void propagationRequiredAndNever() {
		try {
			testClass.propagationRequiredAndNever();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void propagationRequiredAndMandatory() {
		try {
			testClass.propagationRequiredAndMandatory();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	// To Test automocity Fail Case
	@Test
	public void propagationRequiredAutomocityFailTest() {
		try {
			propagationTesting.propagationRequired();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	// To Test automocity Pass Case
	@Test
	public void propagationRequiredAutomocityPassTest() {
		try {
			propagationTesting.propagationRequiredAutomocityPassTest();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void isolationRequired() {
		try {
			List<Employee> employees = isolationTesting.isolationRequired(0);
			System.out.println("getEmployeesUsingResultSetRowMapper");
			for(Employee e : employees) {
				System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void saveEmployeeByVarArgs() {
		try {
			int id = isolationTesting.saveEmployeeByVarArgs(prepareEmployee(121));
			System.out.println("getEmployeesUsingResultSetRowMapper");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	private Employee prepareEmployee(int id) {
		Employee user = new Employee();
		user.setRole("PL");
		user.setName("Harish Sure Prashant");
		user.setId(id);
		return user;
	}
	@Test
	public void isolationUC() {
		try {
			List<Employee> employees = isolationTesting.isolationUC(0);
			System.out.println("getEmployeesUsingResultSetRowMapper");
			for(Employee e : employees) {
				System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void updateEmployeeByPreparedStatement() {
		isolationTesting.updateEmployeeByPreparedStatement(prepareEmployee(2));
		
	}
	@Test
	public void isolationRepeatableRead() {
		try {
			List<Employee> employees = isolationTesting.isolationRepeatableRead(0);
			System.out.println("getEmployeesUsingResultSetRowMapper");
			for(Employee e : employees) {
				System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void isolationDefault() {
		try {
			List<Employee> employees = isolationTesting.isolationDefault(0);
			System.out.println("getEmployeesUsingResultSetRowMapper");
			for(Employee e : employees) {
				System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void isolationReadCommitted() {
		try {
			List<Employee> employees = isolationTesting.isolationReadCommitted(0);
			System.out.println("getEmployeesUsingResultSetRowMapper");
			for(Employee e : employees) {
				System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void isolationSerailisableRead() {
		try {
			List<Employee> employees = isolationTesting.isolationSerailisableRead(0);
			System.out.println("getEmployeesUsingResultSetRowMapper");
			for(Employee e : employees) {
				System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
