package SpringJdbcExample.springjdbcexample;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.DAO.BasicBatchUpdate;
import com.DAO.BasicPreparedStatement;
import com.DAO.BasicResultSetExtractor;
import com.DAO.BasicRowMapper;
import com.bo.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest

public class BatchUpdateTest {
	@Autowired
	private BasicBatchUpdate empDao;
	
	
	
	@Test
	public void updateEmployeeUsingBatch() {
		empDao.batchUpdate(prepareEmployees());
		
	}
	private Employee prepareEmployee(int id) {
		Employee user = new Employee();
		user.setRole("My Role");
		user.setName("My Name");
		user.setId(id);
		return user;
	}
	private List<Employee> prepareEmployees() {
		List<Employee> list = new ArrayList<>();
		list.add(prepareEmployee(37));
		list.add(prepareEmployee(38));
		return list;
	}
}
