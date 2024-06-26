package bbl.util.tests;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer>
{
	public int compare(Integer o1, Integer o2)
	{
		// first even numbers sorted in the  ascending order
		// last odd numbers sorted in descending order
		// return <0 if elem1<elem2
		// return 0  if elem1=elem2
		// return >0  if elem1>elem2
		int res=0;

		boolean isEveno1=(o1%2==0);
		boolean isEveno2=(o2%2==0);
		if(isEveno1==isEveno2)
		{
			if(isEveno1) res=o1-o2;
			else res=o2-o1;

		}	
		else
		{
			if(isEveno1) res=-1; // first Even, second Odd
			else res=1; // first Odd, second Even
		}
		return res;		
	}

}
