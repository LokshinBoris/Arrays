package bbl.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bbl.util.Arrays;

class ArraysTests 
{
	Integer[] numbers= {100,-3,23,4,8,41,56,-7};
	String[] strings= {"abc","lmn","123", null, "a"};
	String[] stringsMin= {"abc","lmn","123", "y"};
	 
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

}
