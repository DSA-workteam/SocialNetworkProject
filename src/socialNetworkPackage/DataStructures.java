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
		public ArrayList<StringBlock> surnames;
		public StringBlock birthdate;
		public StringBlock birthplace;
		public StringBlock home;
		public ArrayList<StringBlock> studiedat;
		public ArrayList<StringBlock> workedat;
		public ArrayList<StringBlock> movies;
		public StringBlock groupcode;
		
		public void setName(String _name) {
			name = SocialNetwork.getStaticStringBlock(SocialNetwork.NAME).getBlock(_name);
		}
		
		public void setSurnames(ArrayList<String> _surnames) {
			for (String s : _surnames) {
				surnames.add(SocialNetwork.getStaticStringBlock(SocialNetwork.SURNAME).getBlock(s));
	
			}
			
		}
		public void setBirthdate(String _birthdate) {
			birthdate = SocialNetwork.getStaticStringBlock(SocialNetwork.BIRTHDATE).getBlock(_birthdate);
		}
		public void setBirthplace(String _birthplace) {
			birthplace = SocialNetwork.getStaticStringBlock(SocialNetwork.BIRTHPLACE).getBlock(_birthplace);

		}
		public void setHome(String _home) {
			home = SocialNetwork.getStaticStringBlock(SocialNetwork.HOME).getBlock(_home);

		}
		public void setStudiedat(ArrayList<String> _studiedat) {
			for (String s : _studiedat) {
				studiedat.add(SocialNetwork.getStaticStringBlock(SocialNetwork.STUDIEDAT).getBlock(s));
	
			}
		}
		public void setWorkedat(ArrayList<String> _workedat) {
			for (String s : _workedat) {
				workedat.add(SocialNetwork.getStaticStringBlock(SocialNetwork.WORKEDAT).getBlock(s));
	
			}
		}
		public void setMovies(ArrayList<String> _movies) {
			for (String s : _movies) {
				movies.add(SocialNetwork.getStaticStringBlock(SocialNetwork.MOVIES).getBlock(s));
	
			}
		}
		public void setGroupcode(String _groupcode) {
			groupcode = SocialNetwork.getStaticStringBlock(SocialNetwork.GROUPCODE).getBlock(_groupcode);
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
