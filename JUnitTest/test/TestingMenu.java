package test;



import org.junit.jupiter.api.Test;

import socialNetworkPackage.SocialNetwork;

class TestingMenu {

	@Test
	void test() {
		//InputStream sysInBackup = System.in; // backup System.in to restore it later
		String[] input = new String[1];
		input[0] = "1"+ System.lineSeparator() +
		// First milestone
		"1" + System.lineSeparator() + "peopleJUnit" + System.lineSeparator() + 
		"2"+ System.lineSeparator()+"friendsJUnit"+ System.lineSeparator() + 
		"3" + System.lineSeparator() + "peopleJUnitPrintTest"+ System.lineSeparator()+ 
		"4" + System.lineSeparator() + "friendsJUnitPrintTest"+ System.lineSeparator() + 
		"0" + System.lineSeparator() +
				
		
		// First milestone wrong input		
		"1" + System.lineSeparator() + "thisfiledoesntexist" + System.lineSeparator() + 
		"2"+ System.lineSeparator()+"notexist"+ System.lineSeparator() + 
		"0" + System.lineSeparator() +
		
		// Second milestone
		"2" + System.lineSeparator() + "1" + System.lineSeparator() +
		"Zaldua" + System.lineSeparator() + "1"+ System.lineSeparator()+ "c"+ System.lineSeparator()+			
		"2" + System.lineSeparator() + "JUnitSurname" + System.lineSeparator() +
		"0" + System.lineSeparator()		
		+ "2" + System.lineSeparator() + "Donostia" + System.lineSeparator() + "3" + System.lineSeparator() + "1990" + System.lineSeparator() + "2003" + System.lineSeparator() 
		+ "4" + System.lineSeparator() + "residential" + System.lineSeparator() + "5" + System.lineSeparator() + "c" + System.lineSeparator() + "0" + System.lineSeparator()
		
		// Second milestone wrong input
		+ "2" + System.lineSeparator() + "1" + System.lineSeparator() +
		"Zaldua" + System.lineSeparator() + "h"+ System.lineSeparator()+ "10"+ System.lineSeparator()+ "c"+ System.lineSeparator()+  "1"+ System.lineSeparator()+ "c"+System.lineSeparator()+		
		"ungabungadoesntexist" + System.lineSeparator()  	
		+ "2" + System.lineSeparator() + "imaginarycity" + System.lineSeparator() + "3" + System.lineSeparator() + "not" + System.lineSeparator() + "adate" + System.lineSeparator() 
		+ "4" + System.lineSeparator() + "notresidentialfile" + System.lineSeparator() + "0" + System.lineSeparator()
		
		// Third milestone
		+ "3" + System.lineSeparator() + "1" + System.lineSeparator() + "Peru57" + System.lineSeparator() + "Ainhoa34"  
		+ System.lineSeparator() + "1" + System.lineSeparator() + "Peru57" + System.lineSeparator() + "Pepe77" 
		+ System.lineSeparator() + "2" + System.lineSeparator() + "Peru57" + System.lineSeparator() + "Ainhoa34"  
		+ System.lineSeparator() + "2" + System.lineSeparator() + "Peru57" + System.lineSeparator() + "Pepe77" 
	    + System.lineSeparator() + "3" + System.lineSeparator() + "c"  + System.lineSeparator() + "0" +  System.lineSeparator()
	    
	    // Third milestone wrong input
 		+ "3" + System.lineSeparator() + "1" + System.lineSeparator() + "notAPerson"  
 		+ System.lineSeparator() + "1" + System.lineSeparator() + "Peru57" + System.lineSeparator() + "notAPerson" 
 		+ System.lineSeparator() + "2" + System.lineSeparator() + "notAPerson" 
 		+ System.lineSeparator() + "2" + System.lineSeparator() + "Peru57" + System.lineSeparator() + "notAPerson" 

 	    + System.lineSeparator() + "30" + System.lineSeparator() + "0" +  System.lineSeparator()
	 	    
	    
	    
	    
	    
		// Extra features
		+ "4" + System.lineSeparator() + "1" + System.lineSeparator() + "10" + System.lineSeparator() + "Cloud Atlas" 
		+ System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() + "Silvia3" + System.lineSeparator() + "2"
		+ System.lineSeparator() + "1" + System.lineSeparator() + "JUNITTEST,Jupiter,Unit,1-1-2000,unknown,Internet,Internet,,,,G777"
		+ System.lineSeparator() + "2" + System.lineSeparator() + "JUNITTEST"
		+ System.lineSeparator() + "3" + System.lineSeparator() + "Silvia3" + System.lineSeparator() + "0"
		+ System.lineSeparator() + "3" + System.lineSeparator() + "1" + System.lineSeparator() + "20" + System.lineSeparator() + "2" + System.lineSeparator() + "100"
		+ System.lineSeparator() + "4"+ System.lineSeparator() + "c" + System.lineSeparator()
		+ System.lineSeparator() + "0"+ System.lineSeparator() 
		+ "5" + System.lineSeparator() + "0" + System.lineSeparator()
		
		
		 + "-1";
		
		
		
		//ByteArrayInputStream in = new ByteArrayInputStream(input);
		//System.setIn(in);
		SocialNetwork.main(input);


	}

}
