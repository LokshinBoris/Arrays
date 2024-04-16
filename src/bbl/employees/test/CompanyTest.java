package bbl.employees.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import bbl.employees.*;
import bbl.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

class CompanyTest {
private static final long ID1 = 123;
private static final int SALARY1 = 1000;
private static final String DEPARTMENT1 = "Development";
private static final float FACTOR1 = (float) 1.5;
private static final long ID2 = 120;
private static final int SALARY2 = 2000;
private static final int HOURS2 = 6;
private static final int WAGE2 = 10;
private static final long ID3 = 125;
private static final int SALARY3 = 3000;
private static final int HOURS3 = 8;
private static final int WAGE3 = 100;
private static final long ID4 = 124;
private static final float PERCENT4=(float)10;
private static final long SALES4=100000;
private static final String DEPARTMENT2 = "QA";
private static final long ID5 = 146;
private static final float FACTOR5 = (float) 1.1;


Manager empl1 = new Manager(ID1, SALARY1, DEPARTMENT1,FACTOR1);
WageEmployee empl2 = new WageEmployee(ID2, SALARY2, DEPARTMENT1,HOURS2,WAGE2);
WageEmployee empl3 = new WageEmployee(ID3, SALARY3, DEPARTMENT2,HOURS3,WAGE3);
SalesPerson empl4 = new SalesPerson(ID4, SALARY2, DEPARTMENT2,HOURS2,WAGE3,PERCENT4,SALES4);
Manager empl5 = new Manager(ID5, SALARY2,DEPARTMENT2,FACTOR5);

Company company;
@BeforeEach
	void setCompany()
	{
	//before each test there will be created new object company 
	// with array of the given employee objects
		company = new Company(new Employee[] {empl1, empl2, empl3, empl4, empl5 },true);
	}
	@Test
	void testAddEmployee()
	{
		assertThrowsExactly(IllegalStateException.class,() -> company.addEmployee(empl1));
		Manager empl6 = new Manager(122,SALARY2,DEPARTMENT2,(float)2.0 );
		Company expectedCompany = new Company(new Employee[] {empl2, empl6, empl1, empl4 ,empl3, empl5},false);
		company.addEmployee(empl6);
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
		assertThrowsExactly(NoSuchElementException.class,() -> company.removeEmployee(199));
		Company expectedCompany1 = new Company(new Employee[] { empl1, empl4, empl3, empl5 },false);
		Company expectedCompany2 = new Company(new Employee[] { empl2, empl4, empl3, empl5 },false);
		Company expectedCompany3 = new Company(new Employee[] { empl2, empl1, empl4, empl5 },false);
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
		// FIXME 
		assertEquals(3560,company.getDepartmentBudget(DEPARTMENT1));
		assertEquals(18600,company.getDepartmentBudget(DEPARTMENT2));
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
		assertTrue(empl4.equals(it.next()) );
		assertTrue(it.hasNext());
		assertTrue(empl3.equals(it.next()) );
		assertTrue(it.hasNext());
		assertTrue(empl5.equals(it.next()) );
		assertFalse(it.hasNext());
		assertThrowsExactly(NoSuchElementException.class,() -> it.next());		
	}

	@Test
	void testConstructor()
	{
		Company expectedCompany= new Company(new Employee[] {empl2, empl1, empl4, empl3, empl5},false);
		assertArrayEquals(expectedCompany.employees,company.employees);
	}
	
	@Test
	void testGetDepartments()
	{
		String[] expectedDepartments= new String[] {"Development", "QA"};
		assertArrayEquals(expectedDepartments,company.getDepartmens()); 
	}
	
	@Test
	void testGetManagersWithMostFactor()
	{
		Manager emp1 = new Manager(10, 1000, DEPARTMENT1,(float)1.5);  // 1500 1.5
		WageEmployee emp2 = new WageEmployee(ID2, SALARY2, DEPARTMENT1,HOURS2,WAGE2);
		Manager emp3 = new Manager(20, 100, DEPARTMENT1,(float)10.);  // 1000 10.
		SalesPerson emp4 = new SalesPerson(ID4, SALARY2, DEPARTMENT2,HOURS2,WAGE3,PERCENT4,SALES4);
		Manager emp5 = new Manager(30, 750 ,DEPARTMENT2,(float)2.);  // 1500 2.
		Manager emp6 = new Manager(40, 1700 ,DEPARTMENT2,(float)1.);  // 1700 1.
		Manager emp7 = new Manager(50, 200 ,DEPARTMENT2,(float)10.);  // 2000 10.

		Company comp=new Company(new Employee[] {emp1, emp2, emp3, emp4, emp5, emp6, emp7 },true);
		Manager[] expectedManagers1 = new Manager[] {emp3, emp7};

		assertArrayEquals(expectedManagers1,comp.getManagersWithMostFactor());
		Manager emp8 = new Manager(60, 100 ,DEPARTMENT2,(float)12.);  // 1200 12.
		comp.addEmployee(emp8);
		Manager[] expectedManagers2 = new Manager[] {emp8};
		assertArrayEquals(expectedManagers2,comp.getManagersWithMostFactor());

	}

}
