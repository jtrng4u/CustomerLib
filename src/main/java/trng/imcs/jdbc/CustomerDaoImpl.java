package trng.imcs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public Customer get(Integer customerId) throws SQLException {
		try (Connection connection = createConnection();) {
			String sql = "select customer_id, name, dob, salary from Customer where customer_id = ? ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, customerId);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer id = rs.getInt("customer_id");
				String name = rs.getString("name");
				LocalDate dob = rs.getDate("dob") != null ? rs.getDate("dob").toLocalDate() : null;
				Float salary = rs.getFloat("salary");

				return new Customer(id, name, dob, salary);
			}
		}
		return null;
	}

	@Override
	public Customer save(Customer customer) throws SQLException {
		try (Connection connection = createConnection();) {
			String sql = "insert into  Customer (name, dob, salary) values (?, ?, ?) ";
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, customer.getName());
			ps.setDate(2, java.sql.Date.valueOf(customer.getDob()));
			ps.setFloat(3, customer.getAnualSalary());

			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated >= 1) {
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						customer.setId(generatedKeys.getInt(1));
					} else {
						throw new SQLException("Creating user failed, no ID obtained.");
					}
				}
			}
		}
		return customer;
	}

	@Override
	public boolean update(Customer customer) throws SQLException {
		try (Connection connection = createConnection();) {
			String sql = "update customer set name = ? , dob = ?, salary =?  where customer_id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, customer.getName());
			ps.setDate(2, java.sql.Date.valueOf(customer.getDob()));
			ps.setFloat(3, customer.getAnualSalary());
			ps.setInt(4, customer.getId());

			int rowsUpdated = ps.executeUpdate();
			return rowsUpdated >= 1;
		}
	}

	@Override
	public boolean delete(Integer customerId) throws SQLException {
		try (Connection connection = createConnection();) {
			String sql = "delete from Customer where customer_id = ? ";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, customerId);
			int rowsUpdated = ps.executeUpdate();
			return rowsUpdated >= 1;
		}
	}

	@Override
	public List<Customer> getCustomers(LocalDate dob) {
		// TODO Auto-generated method stub
		return null;
	}

	private Connection createConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return null;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/imcs23", "root", "root123");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}

		return connection;
	}
}
