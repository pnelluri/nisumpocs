package SpringJdbcExample.springjdbcexample;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.DAO.BasicRowMapper;
import com.bo.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RowMapperExample {
	@Autowired
	@Qualifier("basicjdbc")
	private BasicRowMapper empDao;
	@Test
	public void getEmployeeUsingResultSetRowMapper() {
		Employee e = empDao.getEmployeeUsingResultSetRowMapper();
		System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());

	}
	@Test
	public void getEmployeesUsingResultSetRowMapper() {
		List<Employee> employees = empDao.getEmployeesUsingResultSetRowMapper();
		for(Employee e : employees) {
			System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
		}
		
	}
	
	@Test
	public void getEmployeesUsingCustomResultSetRowMapper() {
		List<Employee> employees = empDao.getEmployeesUsingCustomResultSetRowMapper();
		System.out.println("getEmployeesUsingResultSetRowMapper");
		for(Employee e : employees) {
			System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
		}
		
	}
	
}
