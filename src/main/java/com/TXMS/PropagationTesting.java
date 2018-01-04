package com.TXMS;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bo.Employee;
@Component
public class PropagationTesting {
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
	
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void propagationRequired() throws RuntimeErrorException {
		try {
			saveEmployee(prepareEmployee(51));
			propagationRequiresNew();
		}catch (Exception e) {
			throw new RuntimeErrorException(new Error(e.getMessage()));
		}
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void propagationRequiresNew() {
		throw new RuntimeErrorException(new Error("Requires New"));
		
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void propagationRequiredAutomocityPassTest() throws Exception {
		try {
			saveEmployee(prepareEmployee(49));
			propagationRequiredAutomocityPassTest2();
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void propagationRequiredAutomocityPassTest2() {
		saveEmployee(prepareEmployee(38));
		
	}
	
	
	private Employee prepareEmployee(int id) {
		Employee user = new Employee();
		user.setRole("Engineer");
		user.setName("Prashant");
		user.setId(id);
		return user;
	}
}
