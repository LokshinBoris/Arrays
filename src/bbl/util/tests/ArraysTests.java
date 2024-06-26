package bbl.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import bbl.util.Arrays;

class ArraysTests 
{
	Integer[] numbers= {100,-3,23,4,8,41,56,-7};
	String[] strings= {"abc","lmn","123", null, "a"};
	String[] stringsMin= {"abc","lmn","123", "y"};
	Integer [] sortedArray= { -6, -3, 0 , 4, 24, 100};
	 
	void indexOfTest()
	{
		assertEquals(1,Arrays.indexOf(strings, "lmn"));
		assertEquals(3,Arrays.indexOf(strings,null));
		assertEquals(-1,Arrays.indexOf(numbers, 150));
		assertEquals(4,Arrays.indexOf(numbers, 8));
	}
	@Test
	void minValueTest()
	{
		assertEquals("y",Arrays.min(stringsMin, new StringsLengsCompartor()));
		assertEquals("123",Arrays.min(stringsMin, new StringsComparator()));
	}
	
	@Test
	void evenOddComparatorTest()
	{
		Integer o1=20;
		Integer o2=30;
		Integer o3=15;
		Integer o4=-1;
		EvenOddComparator eoComp=new EvenOddComparator();
		assertTrue(eoComp.compare(o1, o2)<0);
		assertTrue(eoComp.compare(o1, o3)<0);
		assertTrue(eoComp.compare(o3, o2)>0);
		assertTrue(eoComp.compare(o3, o4)<0);
		assertTrue(eoComp.compare(o1, o1)==0);

		assertTrue(eoComp.compare(o2, o1)>0);
		assertTrue(eoComp.compare(o3, o1)>0);
		assertTrue(eoComp.compare(o2, o3)<0);
		assertTrue(eoComp.compare(o4, o3)>0);
		assertTrue(eoComp.compare(o1, o1)==0);

		assertTrue(eoComp.compare(o4, o4)==0);
	}
	
	@Test
	void bubbleSortTest()
	{
		Integer [] expected = {4,8,56,100,41,23,-3,-7};
		Integer [] numbersCopy=java.util.Arrays.copyOf(numbers,numbers.length);
		Arrays.bubbleSort(numbersCopy, new EvenOddComparator());
		// first test method
		for(int i=0;i<numbersCopy.length;i++)
		{
			assertEquals(expected[i], numbersCopy[i]);
		}
		// second test method
		assertArrayEquals(expected, numbersCopy);	
	}
	
	@Test
	void binarySearchTest()
	{
		Integer[] sortedArray= { -6, -3, 0 , 4, 24, 24, 24, 100};
		Comparator<Integer> compInt=(a,b)->a-b;
		
		assertEquals(1, Arrays.binarySearch(sortedArray,-3,compInt) );
		assertEquals(0, Arrays.binarySearch(sortedArray,-6,compInt) );
		assertEquals(7, Arrays.binarySearch(sortedArray,100,compInt) );
		assertEquals(2, Arrays.binarySearch(sortedArray,0,compInt) );
		int index = Arrays.binarySearch(sortedArray,24,compInt);
		assertTrue(index>=4 && index<=6);
		assertEquals(-4, Arrays.binarySearch(sortedArray,1,compInt) );
		assertEquals(-2, Arrays.binarySearch(sortedArray,-4,compInt) );
		assertEquals(-1, Arrays.binarySearch(sortedArray,-8,compInt) );
		assertEquals(-9, Arrays.binarySearch(sortedArray,122,compInt) );		
	}

	@Test
	 void removeIfTest()
	 {
		
		// numbers= {100,-3,23,4,8,41,56,-7};
		Integer[] expectedNoNegative= {100,23,4,8,41,56};
		assertArrayEquals(expectedNoNegative,Arrays.removeIf(numbers,a->a<0) );
		
		// stringsMin = {"abc","l9n","123", "y"};
		Predicate<String> predStringWithNumeric = str -> str.matches(".*\\d.*"); 	
		String[] expectedNumeric= {"abc","lmn", "y"};	
		assertArrayEquals(expectedNumeric,Arrays.removeIf(stringsMin,predStringWithNumeric));
		String[] testNumeric= {"adb3", "2s", "6890", "0"};
		String[] expectedNull = new String[0];
		assertArrayEquals(expectedNull,Arrays.removeIf(testNumeric,predStringWithNumeric));
	 }
	
	 @Test
	 void addTest()
	 {
		 Integer[] expected = {100, -3, 23, 4, 8, 41, 56, -7, 150};
		 Integer[] actual = Arrays.add(numbers, 150);
		 assertArrayEquals(expected, actual);
	 }
	 
	 @Test
	 void lineSearchTest()
	 {
		 Integer[] arr = {100, -3, 23, 4, 8, 41, 56, -7, 150};
		 assertEquals(1,Arrays.lineSearch(arr, a->a==-3));
		 assertEquals(8,Arrays.lineSearch(arr, a->a==150 ));
		 assertEquals(-1,Arrays.lineSearch(arr, a->a==42 ) );
	 }

	 
	 @Test
	 void personsSortTest() 
	 {
		 Person prs1 = new Person(123, 1985);
		 Person prs2 = new Person(120, 2000);
		 Person prs3 = new Person(128, 1999);
		 Person[] persons = {
				prs1, prs2, prs3 
		 };
		 Arrays.bubbleSort(persons);
		 Person[] expected = {new Person(120, 2000),
				 new Person(123, 1985),
				 new Person(128, 1999)};
		 Person[] expectedAge = {
				 new Person(120, 2000),
				 new Person(128, 1999),
				 new Person(123, 1985)
		 };
		 
		 assertArrayEquals(expected, persons);
		 Arrays.bubbleSort(persons,
				 (p1, p2) -> Integer.compare(p2.birthYear, p1.birthYear));
		 
		 assertArrayEquals(expectedAge, persons);
	 }

}
class Person implements Comparable<Person>{
	long id;
	int birthYear;
	public Person(long id, int birthYear) {
		this.id = id;
		this.birthYear = birthYear;
	}
	@Override
	public int compareTo(Person o) {
		
		return Long.compare(id, o.id);
	}
	@Override
	public int hashCode() {
		return Objects.hash(birthYear, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return birthYear == other.birthYear && id == other.id;
	}
	
	
}
