package com.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.jdbc.student.Student;

public class StudentNamedParaDaoImpl {
	NamedParameterJdbcTemplate template;
	public StudentNamedParaDaoImpl() {
		
	}
	public NamedParameterJdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(NamedParameterJdbcTemplate template) {
		this.template = template;
	}

	public void updateStudent(int id, String name)
	{
		String sql="update student_details set sname=:name where sid=:id";
		Map<String,Object> map=new HashMap<String,Object>();  
		map.put("id",id);  
		map.put("name",name);  
		 
		Object i=template.execute(sql, map,new PreparedStatementCallback<Object>(){

			@Override
			public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				// TODO Auto-generated method stub
				return ps.executeUpdate();
			}
			
		});
		
	}

	
}
