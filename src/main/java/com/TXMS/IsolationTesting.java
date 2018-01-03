package com.TXMS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bo.Employee;
@Component
public class IsolationTesting {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public List<Employee> isolationRequired(int id) {
		return getEmployeesUsingResultSetRowMapper(id);
		
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveEmployeeByVarArgs(final Employee e){  
	    String query="insert into employee values(?,?,?)";  
	    int id= jdbcTemplate.update(query,e.getId(),"prashant","TL");  
	    System.out.println("Dat saved");
	    return id;
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_UNCOMMITTED)
	public List<Employee> isolationUC(int id) {
		return getEmployeesUsingResultSetRowMapper(id);
		
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public List<Employee> isolationDefault(int id) {
		List<Employee> employees = getEmployeesUsingResultSetRowMapper(id);
		for(Employee e : employees) {
			System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
		}
		employees = getEmployeesUsingResultSetRowMapper(id);
		for(Employee e : employees) {
			System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
		}
		return employees;
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	public List<Employee> isolationReadCommitted(int id) {
		List<Employee> employees = getEmployeesUsingResultSetRowMapper(id);
		for(Employee e : employees) {
			System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
		}
		employees = getEmployeesUsingResultSetRowMapper(id);
		for(Employee e : employees) {
			System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
		}
		return employees;
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.REPEATABLE_READ)
	public List<Employee> isolationRepeatableRead(int id) {
		List<Employee> employees = getEmployeesUsingResultSetRowMapper(id);
		for(Employee e : employees) {
			System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
		}
		employees = getEmployeesUsingResultSetRowMapper(id);
		for(Employee e : employees) {
			System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
		}
		return employees;
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.SERIALIZABLE)
	public List<Employee> isolationSerailisableRead(int id) {
		List<Employee> employees = getEmployeesUsingResultSetRowMapper(id);
		for(Employee e : employees) {
			System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
		}
		employees = getEmployeesUsingResultSetRowMapper(id);
		for(Employee e : employees) {
			System.out.println(e.getId()+"+"+e.getName()+"+"+e.getRole());
		}
		return employees;
	}
	public Boolean updateEmployeeByPreparedStatement(final Employee e){  
	    String query="update employee set name=? where id=?";  
	    return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
	    public Boolean doInPreparedStatement(PreparedStatement ps)  
	            throws SQLException, DataAccessException {  
	              
	        ps.setInt(2,e.getId());  
	        ps.setString(1,e.getName());  
	              
	        return ps.execute();  
	              
	    }
	    });  
	}
	public List<Employee> getEmployeesUsingResultSetRowMapper(int id){  
	    String query="select * from Employee where id>?";  
	    return jdbcTemplate.query(query,new Object[] {id},new RowMapper<Employee>(){

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
	
	private Employee prepareEmployee(int id) {
		Employee user = new Employee();
		user.setRole("Engineer");
		user.setName("Prashant");
		user.setId(id);
		return user;
	}
}
