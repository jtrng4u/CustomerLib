package trng.imcs.jdbc;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface CustomerDao {

	/**
	 * 
	 * @param customerId
	 * @return Customer matching for customerId.
	 * @throws SQLException
	 */
	public Customer get(Integer customerId) throws SQLException;

	public Customer save(Customer customer) throws SQLException;

	public boolean update(Customer customer) throws SQLException;

	public boolean delete(Integer customerId) throws SQLException;

	public List<Customer> getCustomers(LocalDate dob) throws SQLException;

}
