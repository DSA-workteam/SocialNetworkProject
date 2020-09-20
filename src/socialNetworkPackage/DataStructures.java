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
	
	public static class StringBlock{
			public ArrayList<String> ids;
			public String data;
			
			public StringBlock less,more;
			public StringBlock(String _data) {
				data = _data;
				ids = new ArrayList<String>(2);
				
			}
			
			
			public void addId(String id) {				
					ids.add(id);
			}
			public ArrayList<String> getIds(){
				return ids;
			}
			
			public StringBlock getBlock(String otherData) {
				StringBlock ret = null;
				
					
				int comparation = data.toUpperCase().compareTo(otherData.toUpperCase());
				if( comparation == 0) {
					ret = this;
				}else if(comparation < 0) {
					if(less != null)
						ret = less.getBlock(otherData);	
					else {
						less = new StringBlock(otherData);
						ret = less;
					}
				
				}else {
					if(more != null)
						ret = more.getBlock(otherData);
					else {
						more = new StringBlock(otherData);
						ret = more;
					}
				}				
					return ret;
			}
	}
	
	
	public class Person {
		public String id;
		public StringBlock name;
		public ArrayList<String> surnames;
		public String birthdate;
		public String birthplace;
		public String home;
		public ArrayList<String> studiedat;
		public ArrayList<String> workedat;
		public ArrayList<String> movies;
		public String groupcode;
		
		public void setName(String name) {
			
		}
		public void setSurnames(ArrayList<String> surnames) {
			
		}
		public void setBirthdate(String birthdate) {
			
		}
		public void setBirthplace(String birthplace) {
			
		}
		public void setHome(String home) {
			
		}
		public void setStudiedat(ArrayList<String> studiedat) {
			
		}
		public void setWorkedat(ArrayList<String> workedat) {
			
		}
		public void setMovies(ArrayList<String> movies) {
			
		}
		public void setGroupcode(String groupcode) {
			
		}
		/*
		 * Constructor
		 */
		public Person(String _id) {
			id = _id;			
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
