package com.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.bo.Employee;
@Component("bjt")
public class BasicJdbcTemplate {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int saveEmployee(Employee e){  
	    String query="insert into employee values( '"+e.getId()+"','"+e.getName()+"','"+e.getRole()+"')";  
	    return jdbcTemplate.update(query);  
	}  
	public int saveEmployeeByArray(final Employee e){  
	    String query="insert into employee values(?,?,?)";  
	    return jdbcTemplate.update(query,new Object[] {e.getId(),e.getName(),e.getRole()});  
	}
	public int saveEmployeeByVarArgs(final Employee e){  
	    String query="insert into employee values(?,?,?)";  
	    return jdbcTemplate.update(query,e.getId(),e.getName(),e.getRole());  
	}
	public int updateEmployee(Employee e){  
	    String query="update employee set  name='"+e.getName()+"',role='"+e.getRole()+"' where id='"+e.getId()+"' ";  
	    return jdbcTemplate.update(query);  
	}  
	public int deleteEmployee(Employee e){  
	    String query="delete from employee where id='"+e.getId()+"' ";  
	    return jdbcTemplate.update(query);  
	}  
	  
}
