package basic;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FromOracleExample {
	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<Employee>();
		Connection conn = getConnection();
		try {
			String sql = "select first_name, last_name, salary from employees";
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			while(rs.next()) {
				employees.add(new Employee(rs.getString("first_name"), rs.getString("last_name"), rs.getInt("salary")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		employees.stream().filter(t ->t.getSalary() > 20000)
		.forEach(s -> System.out.println(s.getFirstName()
					+ " - " + s.getLastName() 
					+ " - " + s.getSalary()));  // 급여가 20000보다 큰 애들이 나옴
//		employees.stream()
//				.forEach(s -> System.out.println(s.getFirstName()
//							+ " - " + s.getLastName() 
//							+ " - " + s.getSalary()));
		
	}
	public static Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "hr", "hr");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;	
	}
}

class Employee {
	private String firstName;
	private String lastName;
	private int salary;
	
	public Employee(String firstName, String lastName, int salary) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.salary = salary;
		
	}
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getSalary() {
		return salary;
	}
}