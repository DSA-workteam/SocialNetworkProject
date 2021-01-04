package test;




import socialNetworkPackage.SocialNetwork;

class TestingMenuTimes {

	//@Test
	void test() {
		//InputStream sysInBackup = System.in; // backup System.in to restore it later
		String[] input = new String[1];
		input[0] = "1"+ System.lineSeparator() +
				"1" + System.lineSeparator() + "150kpeople" + System.lineSeparator() + 
				"2"+ System.lineSeparator()+"150kfriends"+ System.lineSeparator() + 
				"3" + System.lineSeparator() + "peopleTimeTest"+ System.lineSeparator()+ 
				"4" + System.lineSeparator() + "friendsTimeTest"+ System.lineSeparator() + 
				"0" + System.lineSeparator() + 
				"2" + System.lineSeparator() + "1" + System.lineSeparator() +
				"Zhan" + System.lineSeparator() + "1"+ System.lineSeparator()+ "c"+ System.lineSeparator()+
		

			
				"2" + System.lineSeparator() + "JUnitSurname" + System.lineSeparator() +
				"0" + System.lineSeparator()
				
		
		+ "2" + System.lineSeparator() + "Donostia" + System.lineSeparator() + 
		"3" + System.lineSeparator() + "1990" + System.lineSeparator() + "2003" + System.lineSeparator() 
		+ "4" + System.lineSeparator() + "residential" + System.lineSeparator() + 
		"5" + System.lineSeparator() + "c" + System.lineSeparator() + 
		"0" + System.lineSeparator() + "3" 
		+ System.lineSeparator() + "1" + System.lineSeparator() + "10" + System.lineSeparator() + "The General" 
		+ System.lineSeparator() + 
		"1" + System.lineSeparator() + "1" + System.lineSeparator() + "Ju1354" + System.lineSeparator() + "2"
		+ System.lineSeparator() + "1" + System.lineSeparator() + "JUNITTEST,Jupiter,Unit,1-1-2000,unknown,Internet,Internet,,,,G777"
		+ System.lineSeparator() + "2" + System.lineSeparator() + "JUNITTEST"
		+ System.lineSeparator() + "3" + System.lineSeparator() + "Ju1354" + System.lineSeparator() + "0"
		+ System.lineSeparator() + "3" + System.lineSeparator() + "1" + System.lineSeparator() + "20" + System.lineSeparator() + "2" + System.lineSeparator() + "100"
		+ System.lineSeparator() + "0"+ System.lineSeparator() + "4"
		+ System.lineSeparator() + "0" + System.lineSeparator()
		
		 + "-1";
		
		
		
		//ByteArrayInputStream in = new ByteArrayInputStream(input);
		//System.setIn(in);
		SocialNetwork.main(input);

		// do your thing

		// optionally, reset System.in to its original
		//System.setIn(sysInBackup);
	}

}
