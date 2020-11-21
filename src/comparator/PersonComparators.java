package comparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

import abstractDataTypesImplemented.GenericArrayListBinaryTree;
import abstractDataTypesPackage.DataBucketADT;
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
	/**
	 * Comparator that sorts Person by Birthdate, surname and name.
	 * @author Borja Moralejo Tobajas
	 *
	 */
	public static class ByBirthdateSurnameAndName implements Comparator<Person>{

		@Override
		public int compare(Person o1, Person o2) {
			int ret = 0;
			
			String[] birthdate1 = o1.getAttribute(PersonAttributesEnum.BIRTHDATE), birthdate2 = o2.getAttribute(PersonAttributesEnum.BIRTHDATE);
			
			if(birthdate1 != null) {
				birthdate1 = birthdate1[0].split("-");
				String tmp = birthdate1[0];
				birthdate1[0] = birthdate1[2];
				birthdate1[2] = tmp;
			}
			
			if(birthdate2 != null) {
				birthdate2 = birthdate2[0].split("-");
				String tmp = birthdate2[0];
				birthdate2[0] = birthdate2[2];
				birthdate2[2] = tmp;
			}
			
			ret = compareRecursiveStart(birthdate1, birthdate2);	
				if(ret == 0)
					ret =new BySurnameAndName().compare(o1, o2);	
			
				
				
			
			return ret;
		}
		
	}
	
	private static ArrayList<Integer> order;
	/*
	private static ArrayList<Integer> inverseInorderTraversal(GenericArrayListBinaryTree<String, Integer> root) {
		
		order = new ArrayList<Integer>();
		
		recursiveInverseInOrder(root);
		
		
		
		
		return order;
		
	}
	*/
	/*
	private static void recursiveInverseInOrder(GenericArrayListBinaryTree<String, Integer> node) {
		if(node.more != null)
			recursiveInverseInOrder(node.more);
		for(int i = 0; i < node.getElemets(node.getKey()).size();i++)
			order.add(node.getKey());
		
		if(node.less != null)
			recursiveInverseInOrder(node.less);
	}
	
	public static class ByMovies implements Comparator<Person>{

		@Override
		public int compare(Person o1, Person o2) {
			int ret = 0;
			DataBucketADT<String,String>[] movies1 = o1.getAttributesRelatedDataBuckets(PersonAttributesEnum.MOVIES), movies2 = o2.getAttributesRelatedDataBuckets(PersonAttributesEnum.MOVIES);
			
			 if(movies1 != null && movies2 != null) {
				// Crear 2 arboles ordenados  y hacer un inorderinverso iterator y comparar uno a uno
				
				GenericArrayListBinaryTree<String, Integer> tree1,tree2;		
				
				tree1 = new GenericArrayListBinaryTree<String, Integer>(movies1[0].getKey(),movies1[0].size());
				for(int i = 1; i < movies1.length;i++) {
					tree1.addElement(movies1[i].getKey(), movies1[i].size());
				}
			
				tree2 = new GenericArrayListBinaryTree<String, Integer>(movies2[0].getKey(),movies2[0].size());
				for(int i = 1; i < movies2.length;i++)
					tree2.addElement(movies2[i].getKey(), movies2[i].size());
				Iterator<Integer> it1 = inverseInorderTraversal(tree1).iterator();
				

				Iterator<Integer> it2 = inverseInorderTraversal(tree2).iterator();
				boolean bothHaveNext = it1.hasNext() && it2.hasNext();
				
				while(bothHaveNext && ret == 0) {
					

					ret = -it1.next().compareTo(it2.next());					
					bothHaveNext = it1.hasNext() && it2.hasNext();
					
				}
				if(ret == 0) {
					if(it1.hasNext())
						ret = 1;
					else if(it2.hasNext())
						ret = -1;
					else ret = 0;
				}

				
				
			}else if(movies1 == null && movies2 == null) {
				ret = 0;
			}else if(movies1 == null) {
				ret = 1;
			}else if(movies2 == null) {
				ret = -1;
			}
				
			
			return ret;
		}
		
		
		
	}
	*/
	
}
