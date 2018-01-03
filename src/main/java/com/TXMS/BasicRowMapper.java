package com.TXMS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.bo.Employee;
@Component
public class BasicRowMapper {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	//Prapared statement
	
	
	
	
	public List<Employee> getEmployeesUsingResultSetRowMapper(){  
	    String query="select * from Employee where id=?";  
	    return jdbcTemplate.query(query,new Object[] {10},new RowMapper<Employee>(){

			@Override
			public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
				 Employee e=new Employee();  
				 e.setId(rs.getInt(1));  
				 e.setName(rs.getString(2));  
				 e.setRole(rs.getString(3));  
				 return e;
			}
	    });  
	}
	
	public List<Employee> getEmployeesUsingCustomResultSetRowMapper(){  
	    String query="select * from Employee where id=?";  
	    return jdbcTemplate.query(query,new Object[] {10},new MyRowMapper());  
	}
	
	
	private class MyRowMapper implements RowMapper<Employee>{

		public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
			if(rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt(1));  
				e.setName(rs.getString(2));  
				e.setRole(rs.getString(3));  
				return e;
			}
			return null;
		}
	}
	
	
}
