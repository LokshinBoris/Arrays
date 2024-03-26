package bbl.util.tests;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer>
{
	public int compare(Integer o1, Integer o2)
	{
		// first even numbers sorted in the  ascending order
		// last odd numbers sorted in descending order
		// return -1 if elem1<elem2
		// return 0  if elem1=elem2
		// return 1  if elem1>elem2
		int res=0;
		int elem1=o1.intValue();
		int elem2=o2.intValue();
		boolean isEvenElem1=(elem1%2==0);
		boolean isEvenElem2=(elem2%2==0);
		if(isEvenElem1==isEvenElem2)
		{
			if(isEvenElem1)
			{
				// both Even
				if(elem1<elem2) res=-1;
				else if(elem1>elem2) res=1;
				else res=0;
			}
			else
			{
				// both Odd
				if(elem1<elem2) res=1;
				else if(elem1>elem2) res=-1;
				else res=0;
			}			
		}
		else
		{
			if(isEvenElem1) 
			{
				// first Even, second Odd
				res=-1;
			}
			else 
			{
				// first Odd, second Even
				res=1;
			}
		}
		return res;		
	}

}
