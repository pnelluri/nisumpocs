package com.TXMS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.bo.Employee;

public class BasicResultSetExtractor {
	@Autowired
	private NamedParameterJdbcTemplate namedParameterjdbcTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	//Prapared statement



	public List<Employee> getEmployeesUsingResultSetExtractor(){  
		String query="select * from Employee";  
		return jdbcTemplate.query(query,new ResultSetExtractor<List<Employee>>(){

			@Override
			public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Employee> list=new ArrayList<Employee>();  
				while(rs.next()){  
					Employee e=new Employee();  
					e.setId(rs.getInt(1));  
					e.setName(rs.getString(2));  
					e.setRole(rs.getString(3));  
					list.add(e);  
				}  
				return list;  
			}
		});  
	}

	public  List<Employee> loadEmployeeByNamedParameter(){  
		String query="select * from Employee where id=:id";  
		Map<String,Object> map=new HashMap<String,Object>();  
		map.put("id",1);  
		List<Employee> listOfEmployees = (List<Employee>)namedParameterjdbcTemplate.query(query,map,new MyResultSetExtracter());
		return listOfEmployees;
	}
	private class MyResultSetExtracter implements ResultSetExtractor{


		@Override
		public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
			List<Employee> list=new ArrayList<Employee>();  
			while(rs.next()){  
				Employee e = new Employee();
				e.setId(rs.getInt(1));  
				e.setName(rs.getString(2));  
				e.setRole(rs.getString(3));  
				list.add(e);
			}
			return list;
		}
	}
}
