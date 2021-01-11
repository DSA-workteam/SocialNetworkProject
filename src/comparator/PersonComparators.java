package comparator;

import java.util.Comparator;

import dataStructuresImplemented.Person;
import enums.PersonAttributesEnum;

public class PersonComparators {
	
	/**
	 * This function goes through all the strings in the array and compares them one by
	 * one until one of them hasn't a next one or when both have no more strings, this method is the front end.
	 * @param strings1 - Array of Strings
	 * @param strings2 - Array of Strings
	 * @return comparation result, 1, -1 or 0
	 */
	private static int compareRecursiveStart(String[] strings1, String[] strings2) {
		int ret = 0;
		if(strings1 != null && strings2 != null) {
			ret = compareRecursive(strings1, strings2, 0);
		}else if(strings1 == null && strings2 == null)
			ret = 0;
		else if(strings1 == null)
			ret = 1;
		else if(strings2 == null)
			ret = -1;
			 return ret;
	}
	/**
	 * This is the worker method.
	 * @param strings1 - Array of Strings
	 * @param strings2 - Array of Strings
	 * @param depth - The index of the array that it's currently working on.
	 * @return comparation result, 1, -1 or 0
	 */
	private static int compareRecursive(String[] strings1, String[] strings2, int depth) {
		int ret = 0;
		
		if(strings1.length > depth && strings2.length > depth) {
			ret = strings1[depth].compareTo(strings2[depth]);
			if(ret == 0)
				ret = compareRecursive(strings1, strings2, depth+1);
		}else if(strings1.length <= depth && strings2.length <= depth) {
			ret = 0;
		}else if(strings1.length <= depth) {
			ret = 1;
		}else if(strings2.length <= depth) {
			ret = -1;
		}
		
		
		return ret;
	}
	
	
	/**
	 * Comparator that sorts Person by name
	 * @author Borja Moralejo Tobajas
	 *
	 */
	public static class ByName implements Comparator<Person>{

		@Override
		public int compare(Person o1, Person o2) {
			int ret = 0;
			
			String[] names1 = o1.getAttribute(PersonAttributesEnum.NAME), names2 = o2.getAttribute(PersonAttributesEnum.NAME);
			ret = compareRecursiveStart(names1, names2);
			
			
			return ret;
		}
		
	
	}
	/**
	 * Comparator that sorts Person by surname.
	 * @author Borja Moralejo Tobajas
	 *
	 */
	public static class BySurname implements Comparator<Person>{

				@Override
				public int compare(Person o1, Person o2) {
					int ret = 0;
					
					String[] surnames1 = o1.getAttribute(PersonAttributesEnum.SURNAME), surnames2 = o2.getAttribute(PersonAttributesEnum.SURNAME);

					ret = compareRecursiveStart(surnames1, surnames2);				
					
					return ret;
				}
				
			}
	/**
	 * Comparator that sorts Person by suname and name.
	 * @author Borja Moralejo Tobajas
	 *
	 */
	public static class BySurnameAndName implements Comparator<Person>{

		@Override
		public int compare(Person o1, Person o2) {
			
			return new BySurname().thenComparing(new ByName()).compare(o1, o2);
		}
		
	}
	/**
	 * Comparator that sorts Person by Birthplace, surname and name.
	 * @author Borja Moralejo Tobajas
	 *
	 */
	public static class ByBirthplaceSurnameAndName implements Comparator<Person>{

		@Override
		public int compare(Person o1, Person o2) {
			int ret = 0;
			
			String[] birthplace1 = o1.getAttribute(PersonAttributesEnum.BIRTHPLACE), birthplace2 = o2.getAttribute(PersonAttributesEnum.BIRTHPLACE);
		
			ret = compareRecursiveStart(birthplace1, birthplace2);	
				if(ret == 0)
					ret = new BySurnameAndName().compare(o1, o2);	
				
			
			return ret;
		}
		
	}
	
	
	
	
}
