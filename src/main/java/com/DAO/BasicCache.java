package com.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bo.Employee;

@Component
public class BasicCache {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Cacheable(value="employees",key="#id")
	public List<Employee> getEmployeesUsingResultSetRowMapper(int id,int count){  
	    String query="select * from Employee where id=?";  
	    return jdbcTemplate.query(query,new Object[] {id},new RowMapper<Employee>(){
			@Override
			public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
				System.out.println("count"+count);
				 Employee e=new Employee();  
				 e.setId(rs.getInt(1));  
				 e.setName(rs.getString(2));  
				 e.setRole(rs.getString(3));  
				 return e;
			}
	    });  
	}
	@CacheEvict(value="employees",key="#id")
	public int deleteEmployee(int id){  
	    String query="delete from employee where id='"+id+"' ";  
	    return jdbcTemplate.update(query);  
	}  
	@CachePut(value="employees",key="#id")
	public int saveEmployeeWithCache(Employee e,int id){  
	    String query="insert into employee values( '"+e.getId()+"','"+e.getName()+"','"+e.getRole()+"')";  
	    return jdbcTemplate.update(query);  
	}
}
