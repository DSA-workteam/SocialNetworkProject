package dataStructuresImplemented;

import java.lang.ref.Reference;

public class Person {
	
	// Attributes
	public class StringDataBlock extends ArrayListDataBlock<String,String>{
		public StringDataBlock() {
			
		}
	}
	
	private ArrayListDataBlock<String, String>[][] attributes;
	
	public static final int N_PARAMETERS = 10;
	
	
	public Person(String combinedData) {
		
		attributes = new StringDataBlock[N_PARAMETERS][];
		String[] separatedData = combinedData.split(",");
		for(int i =1;i < separatedData.length;i++) {
			String[] attributesData = separatedData[i].split(";");
			attributes[i] = new StringDataBlock[attributesData.length];
			for(int j = 0; j < attributesData.length;j++) {
				attributes[i][j] = null;

			}
			
		}
	}
	
}
