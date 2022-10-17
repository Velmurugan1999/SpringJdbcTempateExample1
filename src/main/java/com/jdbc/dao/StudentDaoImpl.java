package com.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.jdbc.student.Student;
import com.jdbc.student.StudentRowMapper;

public class StudentDaoImpl implements StudentDao {
	JdbcTemplate template;

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public void insert(Student s) {
		String sqal = "insert into student_details values(" + s.getSid() + ",'" + s.getName() + "','"
				+ s.getDepartment() + "'," + s.getCpa() + ",'" + s.getAddress() + "')";
		if (template.update(sqal) == 1)
			System.out.println("Insertion Successfully!!!!!!");
		else
			System.out.println("Insertion Failed!!!!!!!!");
	}
	public Student findById(int id)
	{
		String sql="select * from student_details where sid="+id;
		try {
		Student s=template.queryForObject(sql, new StudentRowMapper());
		
			System.out.println(s.toString());
			return s;
		}
		catch (Exception e) {
			System.out.println("No record found!!!");
			return null;
		}
		
	}

	public List<Student> getAll() {
		String sql = "select *from student_details";
		System.out.format("%-5s %-15s %-15s %-15s\n", "ID", "Name", "Department", "Address");
		List<Student> list = template.query(sql, new StudentRowMapper());
		list.forEach(s -> System.out.format("%-5s %-15s %-15s %-15s\n", s.getSid(), s.getName(), s.getDepartment(),
				s.getCpa(), s.getAddress()));
		System.out.println();
		return list;
	}
	public List<Student> getAllRowMap(){
		String sql="select * form student_details";
		System.out.format("%-5s %-15s %-15s %-15s\n", "ID", "Name", "Department", "Address");
		List<Student> list=template.query(sql, BeanPropertyRowMapper.newInstance(Student.class));//new BeanPropertyRowMapper<Student>(Student.class));
		list.forEach(s -> System.out.format("%-5s %-15s %-15s %-15s\n", s.getSid(), s.getName(), s.getDepartment(),
				s.getCpa(), s.getAddress()));
		System.out.println();
		return list;
	}
	public List<Student> getAllByResultsetExtractor() {
		String sql = "select * from student_details";
		System.out.println("By using resultset extractor......");
		System.out.format("%-5s %-15s %-15s %-15s\n", "ID", "Name", "Department", "Address");
		List<Student> list = template.query(sql, new ResultSetExtractor<List<Student>>() {

			@Override
			public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				List<Student> l = new ArrayList<Student>();
				while (rs.next()) {
					Student s = new Student();
					s.setSid(rs.getInt(1));
					s.setName(rs.getString(2));
					s.setDepartment(rs.getString(3));
					s.setCpa(rs.getDouble(4));
					s.setAddress(rs.getString(5));
					l.add(s);
				}
				return l;
			}

		});
		list.forEach(s -> System.out.format("%-5s %-15s %-15s %-15s\n", s.getSid(), s.getName(), s.getDepartment(),
				s.getAddress(), s.getAddress()));

		return list;
	}

	public void update(String s, int id) {
		String sql = "update student_details set sname='" + s + "' where sid=" + id + "";
		if (template.update(sql) == 1)
			System.out.println("Updated Successfully!!!!!!");
		else
			System.out.println("Updating Failed!!!!!!!!");
	}

	public void updatePrepareStatement(String s, int id) {
		String sql = "update student_details set sname=? where sid=?";
		boolean b = template.execute(sql, new PreparedStatementCallback<Boolean>() {

			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {

				ps.setString(1, s);
				ps.setInt(2, id);
				boolean a = ps.execute();
				System.out.println(a);
				return a;
			}

		});

	}

	public void delete(int id) {
		String sql = "delete from student_details where sid=" + id + "";
		if (template.update(sql) == 1)
			System.out.println("Deleted Successfully!!!!!!");
		else
			System.out.println("Deletion Failed!!!!!!!!");
	}

}
