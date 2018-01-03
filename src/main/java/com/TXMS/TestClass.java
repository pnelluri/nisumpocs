package com.TXMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bo.Employee;

@Transactional(readOnly=true)
@Component
public class TestClass {
	
	
	
	@Transactional(propagation=Propagation.MANDATORY)
	public void propagationMandatory() {
		System.out.println("propagationMandatory");
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void propagationRequiredNew() {
		System.out.println("propagationRequiredNew");
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void propagationRequiredAndMandatory() {
		propagationMandatory();
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public void propagationNever() {
		System.out.println("");
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void propagationRequiredAndNever() {
		propagationNever();
	}
	
	
}
