package comparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.Comparator;
import java.util.Iterator;
import java.util.stream.Stream;



import dataStructuresImplemented.DataHolder;
import dataStructuresImplemented.Person;
import enums.PersonAttributesEnum;

public class W7BorjaMoralejo {

	private static void printIntoFile(String fileName, Stream<Person> source) {
		String path = System.getProperty("user.dir") +"\\res\\"+ fileName+".txt";
		File writeFile = new File(path);
		try {
			PrintWriter writerPrinter = new PrintWriter(writeFile);
		
			source.forEach(person -> {writerPrinter.println(person.toString());});;
		
			writerPrinter.close();
		} catch (FileNotFoundException e) {
		}
	}
	
	private static void printIntoFile2(String fileName, Person[] source) {
		String path = System.getProperty("user.dir") +"\\res\\"+ fileName+".txt";
		File writeFile = new File(path);
		try {
			PrintWriter writerPrinter = new PrintWriter(writeFile);
		
			for(int i = 0; i < source.length;i++)
				writerPrinter.println(source[i]);
		
			writerPrinter.close();
		} catch (FileNotFoundException e) {
		}
	}
	
	public static void W7A() {
		
		Comparator<Person> comparator = new PersonComparators.BySurnameAndName(); 
		Stream<Person> stream = DataHolder.getInstance().getPeople().stream().sorted(comparator);
		printIntoFile("W7aBorjaMoralejo",stream);
	}
	
	public static void W7B() {
		Comparator<Person> comparator = new PersonComparators.ByBirthplaceSurnameAndName(); 
		Stream<Person> stream = DataHolder.getInstance().getPeople().stream().sorted(comparator);
		printIntoFile("W7bBorjaMoralejo",stream);
	}

	public static void W7C() {
	
		Person[] people = new Person[DataHolder.getInstance().getPeople().size()];
		int i = 0;
		Iterator<Person> it = DataHolder.getInstance().getPeople().iterator();
		while(it.hasNext()) 
			people[i++] = it.next();
			
		
			
		
		Quicksort.sort(people) ;
		printIntoFile2("W7cBorjaMoralejo", people);
	}
	/*
	public static void W7D() {
		Comparator<Person> comparator = new PersonComparators.ByMovies(); 
		Stream<Person> stream = DataHolder.getInstance().getPeople().stream().sorted(comparator);
		printIntoFile("W7dBorjaMoralejo",stream);
		
	}
	*/
	
	
	
}
