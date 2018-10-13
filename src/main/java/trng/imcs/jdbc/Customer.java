package trng.imcs.jdbc;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Customer {

	private Integer id;
	
	private String name;
	
	private LocalDate dob;
	
	private Float anualSalary;
}
