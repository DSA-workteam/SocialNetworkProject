package socialNetworkPackage;

import java.util.ArrayList;

public class DataStructures {

	
	public class PeopleBlock{
		public ArrayList<Person> peopleInNode;
		
		public PeopleBlock() {
			peopleInNode = new ArrayList<Person>(3);
		}
		public void addPerson(Person p) {
			peopleInNode.add(p);
		}
		
		public Person getPeople(String id) {
			return null;
		}
		
	}
	public class Person {
		public String id;
		public String name;
		public ArrayList<String> surnames;
		public String birthdate;
		public String birthplace;
		public String home;
		public ArrayList<String> studiedat;
		public ArrayList<String> workedat;
		public ArrayList<String> movies;
		public String groupcode;
		
		
		
		/*
		 * Constructor
		 */
		public Person(String _id, String _name, ArrayList<String> _surnames,
				String _birthdate, String _birthplace, String _home, 
				ArrayList<String> _studiedat, ArrayList<String> _workedat,
				ArrayList<String> _movies, String _groupcode) {
			
			
				id = _id;
				name = _name;
				surnames = _surnames;
				birthdate = _birthdate;
				birthplace = _birthplace;
				home = _home;
				studiedat = _studiedat;
				workedat = _workedat;
				movies = _movies;
				groupcode = _groupcode;
			
			
		}
		@Override
		public boolean equals(Object o) {
			if(o instanceof Person) {
				Person other = (Person)o;
				if(id.equals(other.id))
					return true;
				else return false;
			}else return false;
		}
	}
	
	
}
