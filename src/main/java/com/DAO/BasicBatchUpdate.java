package com.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.bo.Employee;
@Component
public class BasicBatchUpdate {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int[] batchUpdate(final List<Employee> actors) {
        return this.jdbcTemplate.batchUpdate(
                "update employee set name = ?, role = ? where id = ?",
                new BatchPreparedStatementSetter() {
                   
                    public int getBatchSize() {
                        return actors.size();
                    }
					public void setValues(java.sql.PreparedStatement ps, int i) throws SQLException {
						 ps.setString(1, actors.get(i).getName());
	                        ps.setString(2, actors.get(i).getRole());
	                        ps.setInt(3, actors.get(i).getId());
						
					}
                });
    }
	public int[] batchUpdate2(final List<Employee> actors) {
        List<Object[]> batch = new ArrayList<Object[]>();
        for (Employee actor : actors) {
            Object[] values = new Object[] {
                    actor.getName(), actor.getRole(), actor.getId()};
            batch.add(values);
        }
        return this.jdbcTemplate.batchUpdate(
                "update employee set name = ?, role = ? where id = ?",
                batch);
    }
}
