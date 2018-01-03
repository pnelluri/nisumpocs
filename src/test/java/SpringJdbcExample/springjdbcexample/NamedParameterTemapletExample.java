package SpringJdbcExample.springjdbcexample;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.DAO.BasicPreparedStatement;
import com.DAO.BasicResultSetExtractor;
import com.DAO.BasicRowMapper;
import com.bo.Employee;


/**
 * @author Nisum
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NamedParameterTemapletExample {


	
	@Autowired
	@Qualifier("basicjdbc")
	private BasicRowMapper empDao;
	
	@Autowired
	@Qualifier("bps")
	private BasicPreparedStatement basicPSEmpDao;
	@Autowired
	private BasicResultSetExtractor  basicRSEEmpDao;
	/**
	 * Prepared Statement
	 */
	@Test
	public void saveEmployeeByPreparedStatement() {
		basicPSEmpDao.saveEmployeeByPreparedStatement(prepareEmployee(2));
		
	}
	
	@Test
	public void updateEmployeeByPreparedStatement() {
		basicPSEmpDao.updateEmployeeByPreparedStatement(prepareEmployee(2));
		
	}
	/**
	 * End of Prepared statement
	 */
	
	
	/**
	 * Named Parameter JDBC Template examples
	 */
	@Test
	public void saveEmployeeByNamedParameter() {
		basicPSEmpDao.saveEmployeeByNamedParameter(prepareEmployee(3));
		
	}
	@Test
	public void saveEmployeeByNamedParameterSqlParameter() {
		basicPSEmpDao.saveEmployeeByNamedParameterSqlParameter(prepareEmployee(4));
		
	}
	@Test
	public void saveEmployeeByNamedParameterSqlParameter2() {
		basicPSEmpDao.saveEmployeeByNamedParameterSqlParameter2(prepareEmployee(5));
		
	}
	@Test
	public void saveEmployeeByNamedParameterBeanPropertySqlParameter() {
		basicPSEmpDao.saveEmployeeByNamedParameterBeanPropertySqlParameter(prepareEmployee(6));
		
	}
	
	@Test
	public void updateEmployeeByNamedParameter() {
		basicPSEmpDao.updateEmployeeByNamedParameter(prepareEmployee(3));
		
	}
	
	@Test
	public void loadEmployeeByNamedParameter() {
		List<Employee> employees =   basicRSEEmpDao.loadEmployeeByCustomeResultSetExractor();
		System.out.println("loadEmployeeByNamedParameter");
		for(Employee e : employees) {
			System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
		}
		
	}
	/**
	 * End of NamedParameterJdbcTemplate
	 */
	@Test
	public void getEmployeesUsingResultSetExtractor() {
		List<Employee> employees = basicRSEEmpDao.getEmployeesUsingResultSetExtractor();
		for(Employee e : employees) {
			System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
		}
		
	}
	@Test
	public void getEmployeesUsingResultSetRowMapper() {
		List<Employee> employees = empDao.getEmployeesUsingResultSetRowMapper();
		System.out.println("getEmployeesUsingResultSetRowMapper");
		for(Employee e : employees) {
			System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
		}
		
	}
	
	private Employee prepareEmployee() {
		Employee user = new Employee();
		user.setRole("Engineer");
		user.setName("Prashant");
		user.setId(1);
		return user;
	}
	private Employee prepareEmployee(int id) {
		Employee user = new Employee();
		user.setRole("Sr Engineer");
		user.setName("Prashant N");
		user.setId(id);
		return user;
	}
	
	

}
