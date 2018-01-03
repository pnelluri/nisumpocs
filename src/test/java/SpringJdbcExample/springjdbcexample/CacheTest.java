package SpringJdbcExample.springjdbcexample;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.jni.Time;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.DAO.BasicJdbcTemplate;
import com.DAO.BasicRowMapper;
import com.bo.Employee;
import com.cache.SimpleBookRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CacheTest {
	@Autowired
	SimpleBookRepository bookRepository;
	@Autowired
	@Qualifier("basicjdbc")
	private BasicRowMapper empDao;
	
	@Autowired
	@Qualifier("bjt")
	private BasicJdbcTemplate basicEmpDao;
	
	@Test
	public void getByIsbnWithoutCacheTest() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			System.out.println(dateFormat.format(new Date()));
	        System.out.println(dateFormat.format(new Date())+"isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
	        System.out.println(dateFormat.format(new Date())+"isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
	        System.out.println(dateFormat.format(new Date())+"isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
	        System.out.println(dateFormat.format(new Date())+"isbn-4567 -->" + bookRepository.getByIsbn("isbn-4567"));
	        System.out.println(dateFormat.format(new Date())+"isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
	        System.out.println(dateFormat.format(new Date())+"isbn-1234 -->" + bookRepository.getByIsbn("isbn-1234"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void getByIsbnWithCache() {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			System.out.println(dateFormat.format(new Date()));
	        System.out.println(dateFormat.format(new Date())+"isbn-1234 -->" + bookRepository.getByIsbnWithCache("isbn-1234"));
	        System.out.println(dateFormat.format(new Date())+"isbn-4567 -->" + bookRepository.getByIsbnWithCache("isbn-4567"));
	        System.out.println(dateFormat.format(new Date())+"isbn-1234 -->" + bookRepository.getByIsbnWithCache("isbn-1234"));
	        System.out.println(dateFormat.format(new Date())+"isbn-4567 -->" + bookRepository.getByIsbnWithCache("isbn-4567"));
	        System.out.println(dateFormat.format(new Date())+"isbn-1234 -->" + bookRepository.getByIsbnWithCache("isbn-1234"));
	        System.out.println(dateFormat.format(new Date())+"isbn-1234 -->" + bookRepository.getByIsbnWithCache("isbn-1234"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getDeleteAndGetEmployeesWithoutUsingCacheEvict() {
		try {
	        System.out.println( empDao.getEmployeesUsingResultSetRowMapper(30,1));
	        System.out.println( basicEmpDao.deleteEmployee(prepareEmployee(30)));
	        System.out.println( empDao.getEmployeesUsingResultSetRowMapper(30,2));
	       
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void getDeleteAndGetEmployeesUsingCacheEvict() {
		try {
	        System.out.println( empDao.getEmployeesUsingResultSetRowMapper(33,1));
	        System.out.println( basicEmpDao.deleteEmployee(33));
	        System.out.println( empDao.getEmployeesUsingResultSetRowMapper(33,2));
	       
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void saveAndGetEmployeesWithoutUsingCachePut() {
		try {
	        System.out.println( empDao.getEmployeesUsingResultSetRowMapper(33,1));
	        System.out.println( basicEmpDao.saveEmployee(prepareEmployee(35)));
	        System.out.println( empDao.getEmployeesUsingResultSetRowMapper(33,2));
	       
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void saveAndGetEmployeesWithUsingCachePut() {
		try {
	        System.out.println( empDao.getEmployeesUsingResultSetRowMapper(33,1));
	        System.out.println( basicEmpDao.saveEmployeeWithCache(prepareEmployee(35)));
	        System.out.println( empDao.getEmployeesUsingResultSetRowMapper(33,2));
	       
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

	private Employee prepareEmployee(int id) {
		Employee user = new Employee();
		user.setRole("Sr Engineer");
		user.setName("Prashant N");
		user.setId(id);
		return user;
	}
}
