package socialNetworkPackage;

import java.util.ArrayList;

import socialNetworkPackage.DataStructures.StringBlock;

public class DataStructures {

	
	public static class PeopleBlock{
		public ArrayList<Person> peopleInNode;
		
		public PeopleBlock() {
			peopleInNode = new ArrayList<Person>(3);
		}
		public void addPerson(Person p) {
			peopleInNode.add(p);
		}
		
		public void removePerson(Person p) {
			
			peopleInNode.remove(p);
			// Could be possible not to find the people
		}
		public Person getPerson(String _id) {
			Person ret = null;
			for(Person p: peopleInNode)
				if(p.id.equals(_id))
					ret = p;
			return ret;
		}
		
	}
								
	public static class StringBlock{
			public ArrayList<Person> ids;
			public String data;
			public StringBlock parent;
			public StringBlock less,more;
			
			public StringBlock(String _data) {
				data = _data;
				ids = new ArrayList<Person>(2);
				
			}
			public void removeId(String _id) {
				ids.remove(_id);
				
			}
			/*
			private void removeStringBlock() {
				String otherData = parent.data;
				int comparation = data.toUpperCase().compareTo(parent.data);
				StringBlock replacement = null;
				
				if(comparation < 0) {
					if(less != null) {
						replacement = less;
					}else if(more != null){
						replacement = more;
					}
					parent.less = replacement;
					
				}else {
					if(more != null) {
						replacement = more;
					}else if(less != null) {
						replacement = less;
					}
					parent.more = replacement;
				}				
					
				if(replacement != null)
					replacement.parent = parent;
			
			}
			*/
			public void addId(Person _p) {				
					ids.add(_p);
			}
			public ArrayList<Person> getIds(){
				return ids;
			}
			
			public StringBlock getBlock(String otherData, boolean createBlock) {
				StringBlock ret = null;
				
					
				int comparation = data.toUpperCase().compareTo(otherData.toUpperCase());
				if( comparation == 0) {
					ret = this;
				}else if(comparation < 0) {
					if(less != null)
						ret = less.getBlock(otherData,createBlock);	
					else if(createBlock){
						
						less = new StringBlock(otherData);
						ret = less;
					}
				
				}else {
					if(more != null)
						ret = more.getBlock(otherData,createBlock);
					else if(createBlock){
						more = new StringBlock(otherData);
						ret = more;
					}
				}				
					return ret;
			}
	}
	
	
	public static class Person {
		public String id;
		private StringBlock[][] personInfo;
		
		
		
		
		public void setParameter(int key, String[] p) {
			int sLength = p.length;
			personInfo[key] = new StringBlock[sLength];
			for(int i = 0; i < sLength;i++) {
				System.out.println("parameter set!" +  p[i]);
				personInfo[key][i] = SocialNetwork.getStaticStringBlock(key).getBlock(p[i],true);
				personInfo[key][i].addId(this);
			}
		}
		
		public String[] getParameter(int key) {
			String[] ret = null;
 			if(personInfo[key] != null) {
 				int lenght = personInfo[key].length;
 				ret = new String[lenght];
 				for(int i = 0; i < lenght;i++)
 					ret[i] = personInfo[key][i].data;
 				
 			}
 			return ret;
			
		}
		
		/*
		 * Constructor
		 */
		public Person(String _id) {
			id = _id;	
			personInfo = new StringBlock[SocialNetwork.NPARAMETERS][];
		}
		
		public void freeData() {
			for(int i = 0; i < SocialNetwork.NPARAMETERS;i++) {
				if(personInfo[i] != null)
					for(int j = 0; j < personInfo[i].length;j++)
						if(personInfo[i][j] != null)
							personInfo[i][j].removeId(id);
			}
		}
		
		
		
		public void print() {
			String s = id + "; ";
			for(int i = 0; i < SocialNetwork.NPARAMETERS;i++) {
				if(personInfo[i] != null) {
					for(int j = 0; j < personInfo[i].length;j++)
						if(personInfo[i][j] != null) {
							s += personInfo[i][j].data;
							if( j !=personInfo[i].length-1 )
								s+= ", ";
						}
					s+= "; ";
				}
			}
				System.out.println(s);
		}
		
		@Override
		public boolean equals(Object o) {
			boolean ret = false;
			
			if( o != null) {
				if(o instanceof Person) {
					Person other = (Person)o;
					if(id.equals(other.id))
						ret = true;
					else ret = false;
				}else ret = false;
			}else ret = false;
			
			return ret;
		
		}
	}
	
}
