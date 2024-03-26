package bbl.util;

import java.util.Comparator;

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
		for(int i=length-1;i>=1;i--)
		{
			for(int j=0;j<i;j++)
			{
				if(comp.compare(array[j],array[j+1])>0)
				{
					T temp=array[j];
					array[j]=array[j+1];
					array[j+1]=temp;
				}
			}
		}
	}
}