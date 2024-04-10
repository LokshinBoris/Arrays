package bbl.employees;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

class CompanyTest {
private static final long ID1 = 123;
private static final int SALARY1 = 1000;
private static final String DEPARTMENT1 = "Development";
private static final long ID2 = 120;
private static final int SALARY2 = 2000;
private static final long ID3 = 125;
private static final int SALARY3 = 3000;
private static final String DEPARTMENT2 = "QA";
private static final long ID4 = 124;

Employee empl1 = new Employee(ID1, SALARY1, DEPARTMENT1);
Employee empl2 = new Employee(ID2, SALARY2, DEPARTMENT1);
Employee empl3 = new Employee(ID3, SALARY3, DEPARTMENT2);
Employee empl4 = new Employee(ID4, SALARY2,DEPARTMENT2 );

Company company;
@BeforeEach
	void setCompany()
	{
	//before each test there will be created new object company 
	// with array of the given employee objects
		company = new Company(new Employee[] {empl1, empl2, empl3},true);
	}
	@Test
	void testAddEmployee()
	{
		assertThrowsExactly(IllegalStateException.class,() -> company.addEmployee(empl1));
		Employee empl4 = new Employee(124,SALARY2,DEPARTMENT2 );
		Company expectedCompany = new Company(new Employee[] {empl2, empl1, empl4 ,empl3},false);
		company.addEmployee(empl4);
		assertArrayEquals(expectedCompany.employees, company.employees);
	}

	@Test
	void testGetEmployee()
	{
		Employee expectedEmplyee= new Employee(ID1,1000,"");
		Employee getingEmplyee = company.getEmployee(ID1);
		assertEquals(expectedEmplyee.getId(),getingEmplyee.getId());
		
		Employee expectedEmplyeeNull= null;
		Employee getingEmplyeeNull = company.getEmployee(ID1+10);
		assertEquals(expectedEmplyeeNull,getingEmplyeeNull);

	}

	@Test
	void testRemoveEmployee()
	{
		assertThrowsExactly(NoSuchElementException.class,() -> company.removeEmployee(ID4));
		Company expectedCompany1 = new Company(new Employee[] { empl1, empl3},false);
		Company expectedCompany2 = new Company(new Employee[] { empl2, empl3},false);
		Company expectedCompany3 = new Company(new Employee[] { empl2, empl1},false);
		company.removeEmployee(ID2);
		assertArrayEquals(expectedCompany1.employees, company.employees);
		company.addEmployee(empl2);
		company.removeEmployee(ID1);
		assertArrayEquals(expectedCompany2.employees, company.employees);
		company.addEmployee(empl1);
		company.removeEmployee(ID3);
		assertArrayEquals(expectedCompany3.employees, company.employees);
	}

	@Test
	void testGetDepartmentBudget()
	{
		assertEquals(3000,company.getDepartmentBudget(DEPARTMENT1));
		assertEquals(3000,company.getDepartmentBudget(DEPARTMENT2));
		assertEquals(0,company.getDepartmentBudget("Unknown"));
	}

	@Test
	void testIterator()
	{
		Company companyEmpty = new Company(new Employee[0] ,true);
		Iterator<Employee> itEmpty=companyEmpty.iterator();
		assertFalse(itEmpty.hasNext());
		Iterator<Employee> it=company.iterator();
		assertTrue(it.hasNext());
		assertTrue(empl2.equals(it.next()) );
		assertTrue(it.hasNext());
		assertTrue(empl1.equals(it.next()) );
		assertTrue(it.hasNext());
		assertTrue(empl3.equals(it.next()) );
		assertFalse(it.hasNext());
		assertThrowsExactly(NoSuchElementException.class,() -> it.next());		
	}

	@Test
	void testConstructor()
	{
		Company expectedCompany= new Company(new Employee[] {empl2, empl1, empl3},false);
		assertArrayEquals(expectedCompany.employees,company.employees);
	}

}
