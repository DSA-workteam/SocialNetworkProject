package socialNetworkPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DataLoader {
	
	
	public void loadFile(String fileName) {
		File f = new File(fileName);
		try {
			Scanner s = new Scanner(f);
			while(s.hasNext())
				loadPerson(s.next());
			s.close();
		} catch (FileNotFoundException e) {
			System.out.println("You introduced an invalid filename.");
		}
		
	}
	
	
	/**
	 * Takes the input and 
	 * @param data String - csv format
	 */
	public void loadPerson(String data) {
	
		
		//p.print();	
	}
	
	
	
	
}
