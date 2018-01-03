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
public class SimpleJDBCCallExample {
	
	@Autowired
	@Qualifier("basicjdbc")
	private BasicRowMapper empDao;
	@Test
	public void addEmp() {
		 empDao.addEmp(prepareEmployee(77));
		
		
	}
	private Employee prepareEmployee(int id) {
		Employee user = new Employee();
		user.setRole("Sr Engineer");
		user.setName("Prashant N");
		user.setId(id);
		return user;
	}
}
