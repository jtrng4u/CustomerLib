package trng.imcs.jdbc;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface AddressDao {

	public Address get(Integer streetNo) throws SQLException;

	public Address save(Address address) throws SQLException;


}
