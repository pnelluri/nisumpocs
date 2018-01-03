package SpringJdbcExample.springjdbcexample;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.DAO.BasicResultSetExtractor;
import com.bo.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ResultSetExtractorExample {
	
	@Autowired
	private BasicResultSetExtractor  basicRSEEmpDao;
	@Test
	public void getEmployeesUsingResultSetExtractor() {
		List<Employee> employees = basicRSEEmpDao.getEmployeesUsingResultSetExtractor();
		for(Employee e : employees) {
			System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
		}
		
	}
	@Test
	public void loadEmployeeByCustomeResultSetExractor() {
		List<Employee> employees = basicRSEEmpDao.loadEmployeeByCustomeResultSetExractor();
		System.out.println("getEmployeesUsingResultSetRowMapper");
		for(Employee e : employees) {
			System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
		}
		
	}
}
