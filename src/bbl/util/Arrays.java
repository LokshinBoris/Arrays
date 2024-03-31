package bbl.util;

import java.util.Comparator;
import java.util.function.Predicate;

public class Arrays 
{
	public static <T> int indexOf(T[] array, T element)
	{
		int index=0;
		while (index<array.length && !equals(array[index],element))
		{
			index++;
		}
		return index==array.length ? -1: index;
	}
	
	public static <T> boolean equals(T elem1, T elem2)
	{
		return elem1==null? elem1==elem2: elem1.equals(elem2);
	}
	
	public static <T> T min(T[] array, Comparator<T> comp)
	{
		T res=null;
		if (array!=null && array.length>0)
		{
			res =array[0];
			for(int i=1;i<array.length;i++)
			{
				if (comp.compare(res, array[i])>0)
				{
					res=array[i];
				}
			}
		}
		return res;
	}
	
	
	public static <T> void bubbleSort(T[] array, Comparator<T> comp)
	{
		int length=array.length;
		boolean ischanged=true;
		for(int i=length-1;i>=1 && ischanged;i--)
		{
			ischanged=false;
			for(int j=0;j<i;j++)
			{
				if(comp.compare(array[j],array[j+1])>0)
				{
					T temp=array[j];
					array[j]=array[j+1];
					array[j+1]=temp;
					ischanged=true;
				}
			}
		}
	}
	
	public static <T> int binarySearch(T[] array, T key, Comparator<T> comp)
	{
		//TODO
		//left index = 0
		//right index = array.length - 1
		//middle (left + right) / 2
		//left part - left index, right index = middle - 1
		//right part - left index = middle + 1, right index
		//while left <= right
		//returns exactly what the standard binarySearch does
		//if there are several equaled elements no guarantee that
		// being returned index is the one to first occurrence
		int down=0;
		int up=array.length-1;
		int middle=0;
		int resCompare=0;
		int index=-1;
		while(down<=up && index<0)
		{
			
			middle=down+(up-down)/2;
		//	System.out.printf("Befor Down=%d Up=%d Middle=%d\n", down,up,middle);
			resCompare=comp.compare(array[middle],key);
			if(resCompare<0) down=middle+1;			//array[middle]<key -> search up
			else if (resCompare>0) up=middle-1;		//array[middle]>key -> search down
				 else index=middle;  				//array[middle]=key -> stop
		}
		if(index<0)
		{
			if(resCompare<0)
			{
				if(middle==array.length-1) index=-(middle)-1;
				else index=-(middle+1)-1;
 
			}
			else index= -middle-1;
		}
		return index;
	}
	
	public static <T> T[] search(T[] array, Predicate<T> predicate) {
		//Impossible to allocate memory for generic array
		//Only Arrays.copyOf may be used
		T[] arResult = java.util.Arrays.copyOf(array, array.length);
		int index = 0;
		for(int i = 0; i < array.length; i++) {
			if(predicate.test(array[i])) {
				arResult[index++] = array[i];
			}
		}
		return java.util.Arrays.copyOf(arResult,index);
	}
	public static <T> T[] removeIf(T[] array, Predicate<T> predicate)
	{
		return search(array,predicate.negate());
	}
	
}
