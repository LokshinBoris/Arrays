package bbl.employees;

import java.util.Iterator;
import java.util.NoSuchElementException;
import bbl.util.Arrays;
//SO far we don't consider optimization
public class Company implements Iterable
{
	Employee[] employees;
	public void addEmployee(Employee empl)
	{
		//TODO adds new Employee to array of employees
		//if an employee with id equaled to id of empl exists, then to throw IllegalStateException
		int index = Arrays.binarySearch(employees, empl, (a,b)->a.compareTo(b));
		if(index>=0)
		{
			throw new IllegalStateException( String.format("An employee with id=(%d) alredy working in the company",empl.getId()) );
		}
		employees=Arrays.add(employees, empl);
		Arrays.bubbleSort(employees);
	}
	public Employee getEmployee(long id) 
	{
		// data about an employee with a given id value
		// if the company doesn't have such employee, then return null
		Employee emp= new Employee(id, 0 ," ");
		int index = Arrays.binarySearch(employees, emp, (a,b)->a.compareTo(b));
		
		return index<0 ? null: employees[index];
	}
	public Employee removeEmployee(long id)
	{
		//removes from the company an employee with a given id
		//if such employee doesn't exist, throw NoSuchElementException
		//returns reference to being removed employee
		Employee emp= new Employee(id, 0 ," ");
		int index = Arrays.binarySearch(employees, emp, (a,b)->a.compareTo(b));
		if(index<0)
		{
			throw new NoSuchElementException( String.format("Employee id=(%d) not found",id) );
		}
		Employee oldEmployee= new Employee(employees[index].getId(),employees[index].getBasicSalary(),employees[index].getDepartment());
		int i=0;
		for(int j=0; j<employees.length;j++)
		{
			if(!employees[j].equals(oldEmployee))
			{
				employees[i++]=employees[j];
			}
		}
		employees=java.util.Arrays.copyOf(employees,i);
		return oldEmployee;
	}
	public int getDepartmentBudget(String department)
	{
		//returns sum of basic salary values for all employees of a given department
		//if employees of a given department don't exist, returns 0
		int sum=0;
		for(Employee emp: employees)
		{
			if(emp.getDepartment().equals(department))	sum=sum+emp.getBasicSalary();
		}	
		return sum;
	}
	public Company(Employee[] employees, boolean withSort) 
	{
		// withSort need for test
		this.employees = Arrays.copy(employees);
		if(withSort) Arrays.bubbleSort(this.employees);
	}
	@Override
	public Iterator<Employee> iterator()
	{
		return new CompanyIterator();
	}
	private class CompanyIterator implements Iterator<Employee>
	{

		private int current=0;
		//iterating all employees in the ascending order of the ID values
		@Override
		public boolean hasNext()
		{
			return employees.length<0? false:current<employees.length;
		}

		@Override
		public Employee next() 
		{
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			return employees[current++];
		}
		
	}
}