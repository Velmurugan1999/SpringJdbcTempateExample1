package com.jdbc;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.jdbc.dao.StudentDao;
import com.jdbc.dao.StudentDaoImpl;
import com.jdbc.dao.StudentNamedParaDaoImpl;
import com.jdbc.student.Student;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("JdbcContext.xml");
		ApplicationContext beanContext = new AnnotationConfigApplicationContext(SpringConfig.class);
		StudentDao dao = (StudentDaoImpl) context.getBean("sdao");
		Student student = beanContext.getBean(Student.class);
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter:\n(1)->Insert details;\n(2)->update name;\n(3)->delete student by id;\n(4)->"
					+ "Get all details\n(5)->find by id\n(other)->Exit");
			int i = Integer.parseInt(sc.nextLine());
			if (i == 1) {
				System.out.println("Enter=> Id    Name   Department   Cgpa    City");
				int id = Integer.parseInt(sc.nextLine());
				String name = sc.nextLine();
				String department = sc.nextLine();
				double cgpa = Double.parseDouble(sc.nextLine());
				String address = sc.nextLine();
				student.setSid(id);
				student.setName(name);
				student.setDepartment(department);
				student.setCpa(cgpa);
				student.setAddress(address);
				dao.insert(student);
			} else if (i == 2) {
				System.out.println("Enter Id and name to update:");
				int id = Integer.parseInt(sc.nextLine());
				String name = sc.nextLine();

				dao.updatePrepareStatement(name, id);
			} else if (i == 3) {
				System.out.println("Enter id to delete the student");
				int id = Integer.parseInt(sc.nextLine());
				dao.delete(id);
			} else if (i == 4) {
				//dao.getAll();
				dao.getAllByResultsetExtractor();
				//dao.getAllRowMap();
			}
			else if(i==5)
			{
				System.out.println("Enter id to find the student");
				int id = Integer.parseInt(sc.nextLine());
				dao.findById(id);
			}
			else {
				break;
			}
		}
		while (true) {
			System.out.println("Enter yes/no to update student with named paramenter jdbc template......");
			String s=sc.next();
			if(s.equalsIgnoreCase("yes"))
			{
				StudentNamedParaDaoImpl pdao = (StudentNamedParaDaoImpl) context.getBean("pdao");
				System.out.println("Enter Id and name to update:");
				int id = Integer.parseInt(sc.nextLine());
				String name = sc.nextLine();
				pdao.updateStudent(id, name);
			}
			else
			{
				System.out.println("Exiting..................................................");
				break;
			}
		}
		
	}

}
