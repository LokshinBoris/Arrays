package bbl.util.tests;

import java.util.Comparator;

public class StringsComparator implements Comparator <String>
{
	public int compare(String o1, String o2)
	 {
		 return o1.length() - o2.length();
	 }
}
