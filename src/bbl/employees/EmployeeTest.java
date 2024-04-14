package bbl.employees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmployeeTest 
{
	

	@Test
	void manegerTests()
	{
		Manager manager= new Manager(123l,1000,"Development",1.5f);
	
		assertEquals(1500,manager.computeSalary());
	}
	
	@Test
	void WageEmployeeTests()
	{
		WageEmployee wageEmployee= new WageEmployee(123l,1000,"Development",6,2000);
	
		assertEquals(13000,wageEmployee.computeSalary());
	}
	
	@Test
	void salesPersonTests()
	{
		SalesPerson salersPerson= new SalesPerson(123l,1000,"Development",8,100,10f,100000l);
	
		assertEquals(11800,salersPerson.computeSalary());
	}
	
}
