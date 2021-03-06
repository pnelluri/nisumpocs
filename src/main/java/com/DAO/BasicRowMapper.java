package com.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.bo.Employee;
import com.mysql.jdbc.PreparedStatement;

@Component("basicjdbc")
public class BasicRowMapper {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/*@Autowired
	private SimpleJdbcInsert insertActor;*/
	//Prapared statement
	@Autowired
	private NamedParameterJdbcTemplate namedParameterjdbcTemplate;
	
	public Employee getEmployeeUsingResultSetRowMapper(){  
	    String query="select * from Employee where id=?";  
	    return jdbcTemplate.queryForObject(query,new Object[] {10},new RowMapper<Employee>(){

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
	
	
	public List<Employee> getEmployeesUsingResultSetRowMapper(){  
	    String query="select * from Employee";  
	    return jdbcTemplate.query(query,new RowMapper<Employee>(){

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
	
	
	public void add(Employee actor) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", actor.getName());
        parameters.put("role", actor.getRole());
//        /*insertActor.setTableName("employee");
//        Number newId = insertActor.executeAndReturnKey(parameters);*/
//        actor.setId(newId.intValue());
    }
	public void addEmp(Employee actor) {
        Map<String, Object> parameters = new HashMap();
        parameters.put("name", actor.getName());
        parameters.put("role", actor.getRole());
        parameters.put("id", actor.getId());
       /* insertActor.setTableName("employee");
        insertActor.execute(parameters);*/
        
    }
	
	
}
