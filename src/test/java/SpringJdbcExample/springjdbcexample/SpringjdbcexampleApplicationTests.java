package SpringJdbcExample.springjdbcexample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.DAO.BasicJdbcTemplate;
import com.DAO.BasicRowMapper;
import com.bo.Employee;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringjdbcexampleApplicationTests {

	
	@Autowired
	@Qualifier("basicjdbc")
	private BasicRowMapper empDao;
	@Autowired
	private BasicJdbcTemplate basicEmpDao;
	
	/**
	 * Basic CRUD Operations using JDBC Template
	 */
	@Test
	public void createUser() {
		basicEmpDao.saveEmployee(prepareEmployee());
		
	}
	@Test
	public void createUserByArray() {
		basicEmpDao.saveEmployeeByArray(prepareEmployee(20));
		
	}
	@Test
	public void saveEmployeeByVarArgs() {
		basicEmpDao.saveEmployeeByVarArgs(prepareEmployee(30));
		
	}
	@Test
	public void updateUser() {
		basicEmpDao.updateEmployee(prepareEmployee(1));
		
	}
	@Test
	public void deleteUser() {
		basicEmpDao.deleteEmployee(prepareEmployee(1));
		
	}
	/*
	 * End of Basic JDBC Template
	 */
	
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
