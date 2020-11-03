package menuPackage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import abstractDataTypesPackage.NodeADT;
import dataStructuresImplemented.DataHolder;
import dataStructuresImplemented.DataManager;
import dataStructuresImplemented.Person;
import enums.MenuEnum;
import enums.PersonAttributesEnum;
import exceptions.AlreadyOnTheCollectionException;
import exceptions.ElementNotFoundException;
import exceptions.ImpulsoryAttributeRequiredException;
import exceptions.MenuClosedException;

/**
 * This class has all the functionalities of the menu.
 * @author Borja Moralejo Tobajas
 *
 */
public class MenuFunctions{
	
	/**
	 * It holds and stores the state machines attributes. Because java doesn't have the option to give as reference an integer, I made this.
	 * @author Borja Moralejo Tobajas
	 *
	 */
	protected class StateMachineAttributes {
		public MenuEnum state;
		public int substate;
		public int parsedOption;
		
	}
	// Attributes for the state machine
	protected StateMachineAttributes sma;
	
	private String s1, s2; // Strings saved for later use in the functions

	/**
	 * Initializes the state machine of the menu
	 */
	public MenuFunctions() {
		sma = new StateMachineAttributes();
		sma.state = MenuEnum.MAIN;
		sma.substate = 0;
		sma.parsedOption = 0;		
	}
	
	/**
	 * This method is a collection of functions of the menu. In this method near all the functionality of the menu options are implemented.
	 * @param input - String. It takes the input from the main menu loop.
	 * @param sma - {@link StateMachineAttributes} from MenuManager
	 * @throws MenuClosedException - {@link MenuClosedException}. If this is thrown, the main loop of the menu will stop and the program terminates.
	 */
	protected void useInput(String input) throws MenuClosedException{
		switch(sma.state) {
		
		case BORNPEOPLE: // Point 7 of the programming project
			System.out.println("This is the list of all the people that were born in "+input +":");
			try {
				Person[] people = DataHolder.getInstance().searchPeopleByAttribute(PersonAttributesEnum.BIRTHPLACE, input);
				int l = people.length;
				String[] surnames;
				for(int i = 0; i < l;i++) {
					System.out.print("Person id: "+people[i].getAttribute(PersonAttributesEnum.ID)[0]);
					surnames = people[i].getAttribute(PersonAttributesEnum.SURNAME);
					
						System.out.print(" Surname(s): ");
					for(int j = 0; j < surnames.length;j++)
						System.out.print(surnames[j] + " ");
					System.out.println();

				}
				System.out.println();

			} catch (ElementNotFoundException e1) {
				System.err.println("No one was born in that city");
			}
			
			
			sma.substate = 2;
			sma.state = MenuEnum.MAIN;
			break;
		case BUILDPROFILES:	// Point 10 of the programming project
			sma.substate = 2;
			sma.state = MenuEnum.MAIN;
			break;
		case DATES: // Point 8 of the programming project
			if(sma.substate == 0) {
				s1 = input;
				sma.substate = 1;
				
			}else {
				s2 = input;			
				
				int y1 = 0,y2 = 0;
				try {
					y1 = Integer.parseInt(s1);
				}catch(NumberFormatException e) {
					System.err.println("First input wasn't a year!");
				}
				
				try {
					y2 = Integer.parseInt(s2);
				}catch(NumberFormatException e) {
						System.err.println("Second input wasn't a year!");
				}
				if(y1 > y2) {
					int temp = y2;
					y2 = y1;
					y1 = temp;
				}
				for(int i = y1; i < y2;i++)				
					try {
						Person[] peopleSorted = sortPersonArray(DataHolder.getInstance().getYearOfBirth(i +"").getCollection());
						System.out.println();
						
						
						System.out.println("People born in the year "+ i+ " sorted by birthplace, surnames and names: ");
						for(int j = 0; j < peopleSorted.length; j++) {
							System.out.println(peopleSorted[j].toString());
						}
						System.out.println();
					} catch (ElementNotFoundException e) {
					}
				
				sma.substate = 2;
				sma.state = MenuEnum.MAIN;				
			}
			
			break;
		case IDFUNCTIONALITIES:
			switch(sma.substate) {
			case 0:
				int intCast = -1;
				try {
					intCast = Integer.parseInt(input);
				}catch(NumberFormatException e) {
					System.err.println("That's not a number!");
					intCast = -1;
				}finally {
					if(intCast == 0) {
						sma.state = MenuEnum.MAIN;
						sma.substate = 3;
					}else if(intCast >= 0&& intCast <= 3){
						sma.substate = intCast;
					}
					
				}
				break;
			case 1:// Extra function added by us ADD person
				try {
					Person nPerson = new Person(input);
					DataHolder.getInstance().addPersonToNetwork(nPerson);			
					System.out.println("Your person has been loaded successfully");
				} catch (ImpulsoryAttributeRequiredException e) {
					System.out.println("You haven't typed any ID.");
				} catch(AlreadyOnTheCollectionException e) {
					System.out.println("This ID was already selected, please chose a new one");		
				}
				sma.substate = 0;
				sma.state = MenuEnum.IDFUNCTIONALITIES;
				break;
			case 2:  // Extra function added by us REMOVE person
				try {
					Person p = DataHolder.getInstance().getPersonByID(input);
					DataHolder.getInstance().removePersonFromNetwork(p);
					System.out.println("The person has been removed succesfully");
					System.out.println(p);
					System.out.println();
				}catch(ElementNotFoundException e) {
					System.out.println("The person you wanted to delete hasn't been found");
				}
				
				
				sma.state = MenuEnum.IDFUNCTIONALITIES;
				sma.substate = 0;
				break;
			case 3: // Extra function by us, this shows the friends of the given id
				
				try {
					System.out.println(DataHolder.getInstance().getPersonByID(input).getNode().toString());
				} catch (ElementNotFoundException e) {				
					System.err.println("There's not such person with that id");
				}
				
				sma.state = MenuEnum.IDFUNCTIONALITIES;
				sma.substate = 0;
				break;
			}
			break;
		case LOADP: // Point 3 of the programming project 
			DataManager.getInstance().loadFile(input, 0);
			
			sma.state = MenuEnum.MAIN;
			sma.substate = 1;
			break;
		case LOADR: // Point 5 of the programming project
			DataManager.getInstance().loadFile(input, 1);
			
			sma.state = MenuEnum.MAIN;
			sma.substate = 1;
			break;
		case MAIN: // Point 1 of the programming project and it used for navigating in the main menu
			
			try {
				int intCast = Integer.parseInt(input);
				switch(sma.substate) {
				case 0: // MAIN MENU
					if(intCast == -1) {
						throw new MenuClosedException();
					}else if(intCast >= 1 && intCast <= 3) {
						sma.substate = intCast;
					}
					
					break;
				case 1: // FIRST MILESTONE MENU
					
					switch(intCast) {
					case 0:
						sma.substate = 0;
						break;
					case 1:
						sma.substate = 0;
						sma.state = MenuEnum.LOADP;
						break;
					case 2:
						sma.substate = 0;
						sma.state = MenuEnum.LOADR;
						break;
					case 3:
						sma.substate = 0;
						sma.state = MenuEnum.PRINTP;
						break;
					case 4:
						sma.substate = 0;
						sma.state = MenuEnum.PRINTR;
						break;
					default:
						System.err.println("That sma.state doesn't exist");
						break;
					}
					
					break;
				case 2: // SECOND MILESTONE MENU
					switch(intCast) {
					case 0:
						sma.substate = 0;
						break;
					case 1:
						sma.substate = 0;
						sma.state = MenuEnum.SEARCHFRIENDS;
						break;
					case 2:
						sma.substate = 0;
						sma.state = MenuEnum.BORNPEOPLE;
						break;
					case 3:
						sma.substate = 0;
						sma.state = MenuEnum.DATES;
						break;
					case 4:
						sma.substate = 0;
						sma.state = MenuEnum.RESIDENTIAL;
						break;
					case 5:
						sma.substate = 0;
						sma.state = MenuEnum.BUILDPROFILES;
						break;
				
					default:
						System.err.println("That sma.state doesn't exist");
						break;
					}
					
					break;
				case 3: // EXTRA FEATURES MENU
					switch(intCast) {
					case 0:
						sma.substate = 0;
						break;
					
					case 1:
						sma.substate = 0;
						sma.state = MenuEnum.SEARCH;
						break;
					case 2:
						sma.substate = 0;
						sma.state = MenuEnum.IDFUNCTIONALITIES;
						break; 
					case 3:
						sma.substate = 0;			
						sma.state = MenuEnum.RANDOM;
						break;
					case 4:
						System.out.println("Number of people in the network " + DataHolder.getInstance().getNumberOfPeople());
						
						sma.substate = 3;			
						sma.state = MenuEnum.MAIN;
						break;
					default:
						System.err.println("That sma.state doesn't exist");
						break;
					}
					break;
				}
			}catch(NumberFormatException e) {
				System.err.println("That's not a number!");	
			}
			
			break;
		case PRINTP: // Point 4 of the programming project
			DataManager.getInstance().printIntoFile(input);
			sma.state = MenuEnum.MAIN;
			sma.substate = 1;
			break;
		case PRINTR: // Extra function added by us
			
			DataManager.getInstance().printRelationshipsIntoFile(input);
			
			System.out.println();
			System.out.println("Done printing relationships");
			System.out.println();
			sma.state = MenuEnum.MAIN;
			sma.substate = 3;
			break;
		case RANDOM:
			
			try {
			int intCast = Integer.parseInt(input);
			switch(sma.substate) {
				case 0:				
				
					if(intCast == 0) {
						sma.state = MenuEnum.MAIN;
						sma.substate = 3;
					}else if(intCast >= 0&& intCast <= 2){
						sma.substate = intCast;
					}						
					
					break;
				case 1:
					
					for(int i = 0; i < intCast;i++)
						try {
							DataHolder.getInstance().addPersonToNetwork(RandomPeopleGenerator.getInstance().generateRandomPerson());
						} catch (AlreadyOnTheCollectionException e) {
							
						}
					
					sma.state = MenuEnum.RANDOM;
					sma.substate = 0;
					break;
				case 2:
					
					for(int i = 0; i < intCast;i++)							
						RandomPeopleGenerator.getInstance().createRandomRelationship();
					
					sma.state = MenuEnum.RANDOM;
					sma.substate = 0;
					break;
			}
			
			}catch(NumberFormatException e) {
				System.err.println("That's not a number!");	
			}
			
			
			
			break;
	
		case RESIDENTIAL: // Point 9 of the programming project
			DataManager.getInstance().loadFile(input, 2);
			
			sma.state = MenuEnum.MAIN;
			sma.substate = 2;
			break;
		case SEARCH: // Extra feature
			
			
			if(sma.substate == 0) { // Choosing the attribute to search for
				try{
				sma.parsedOption = Integer.parseInt(input);
				
				if(sma.parsedOption == 0) {
					sma.state = MenuEnum.MAIN;
					sma.substate = 3;
				}else if(sma.parsedOption > 0 && sma.parsedOption <= 11)
				sma.substate = 1;
				}catch(NumberFormatException e) {
					System.err.println("That's not a number!");	
				}
			
			}else { // Searching for people that fulfill the have the requested attribute with the given value
				
				try {
					Person[] output = DataHolder.getInstance().searchPeopleByAttribute(PersonAttributesEnum.values()[sma.parsedOption - 1],input);
					System.out.println();
					System.out.println("At least one person has been found, here is the output:");
					for (int i = 0; i < output.length; i++) {
						System.out.println(output[i].toString());
					}
					System.out.println();
				} catch (ElementNotFoundException e) {
					System.out.println();
					System.out.println("The person you tried to find is not in the data base");
					System.out.println();
				}
				
				sma.state = MenuEnum.MAIN;
				sma.substate = 3;
			}			
			break;
		case SEARCHFRIENDS: // Point 6 of the programming project
			switch (sma.substate) {
				case 0:
					s1 = input;
					sma.substate = 1;
					break;
				case 1:
					sma.parsedOption = Integer.parseInt(input);
					if(sma.parsedOption == 2)
						sma.substate = 2;
					else
						sma.substate = 3;
					break;
				case 2:
					s2 = input;
				case 3:
					Collection<Person> friends = new ArrayList<Person>();
					Collection<NodeADT<String>> friendsNode = null;
					Iterator<NodeADT<String>> it = null;
					Collection<Collection<Person>> friendsCollection = new ArrayList<Collection<Person>>();
					try {
						// Loads all the people that have the given surname and iterates over them
						Person[] people = DataHolder.getInstance().searchPeopleByAttribute(PersonAttributesEnum.SURNAME, s1);
						for (Person person: people) {
							friends.add(person);
							friendsNode = person.getNode().getLinkedNodes();
							it = friendsNode.iterator();
							//Iterates over the friends that the selected person has and adds them to the overall friends list
							while(it.hasNext()) {
								friends.add(DataHolder.getInstance().getPersonByID(it.next().getContent()));
							}
							friendsCollection.add(friends);
							friends = new ArrayList<Person>();
						}
						//3 printing options, to console, to file with default name, or to file with custom name. It will loop over and over again until selecting the going back to menu option. If no correct option selected, gives option to try again
						boolean choosen = false;
						switch (sma.parsedOption) {
							case 0: //Go back to menu
								choosen = true;
								break;
							case 1: //Print to console
								Iterator<Collection<Person>> itFC = friendsCollection.iterator();
								while(itFC.hasNext()) {
									Iterator<Person> itF = itFC.next().iterator();
									System.out.println();
									System.out.println(itF.next().getAttribute(PersonAttributesEnum.ID)[0] + "'s friends:");
									System.out.println();
									while (itF.hasNext())
										System.out.println(itF.next().toString());
								}
								System.out.println();
								System.out.println("All friends printed succesfully");
								System.out.println();
								break;
							case 2: //Print to file with custom name
								DataManager.getInstance().printPeoplesFriendsIntoFile(input, friendsCollection);
								break;
							case 3: //Print to file with default name
								DataManager.getInstance().printPeoplesFriendsIntoFile("FRIENDS OF " + s1, friendsCollection);
								break;
							default:
						}
						sma.substate = 1;
						if (choosen) {
							sma.state = MenuEnum.MAIN;
							sma.substate = 2;
						}
					} catch (ElementNotFoundException e) {
						System.out.println();
						System.out.println("There's no one in the system saved with that surname right now");
						System.out.println();
						sma.state = MenuEnum.MAIN;
						sma.substate = 2;
					}
					break;
			}
			break;
		
		default:
			break;
			
		}
	}
	
	/**
	 * Recursive sorting function for the point 8 of the Programming Project.
	 * @param sorting - PersonAttributesEnum {@link PersonAttributesEnum} attribute type that is being compared.
	 * @param p1 - Person {@link Person}. 
	 * @param p2 - Person {@link Person}.
	 * @param depth - int. If the Person objects have more than one attribute of one type, we need to compare all of them, and this parameter is for that.
	 * @return boolean. Whether or not the first person is "lighter" than the second person passed by parameters. With this the selection sorting algorithm decides to swap the "bigger" person.
	 */
	private boolean getFirstPerson(PersonAttributesEnum sorting, Person p1, Person p2, int depth){
		boolean ret = false;
		int comp = 0;
		
		
		// this makes the comparison between p1 and p2, if any or both of the Person objects doesn't have an attribute, the one with attribute has preference, and if both haven't attribute, it checks the next attribute
		try {
			comp = p1.getAttribute(sorting)[depth].compareTo(p2.getAttribute(sorting)[depth]);
		 if(p1.getAttribute(sorting)[depth].equals("") || p2.getAttribute(sorting)[depth].equals("")) {
			 if(p1.getAttribute(sorting)[depth].equals("") && p2.getAttribute(sorting)[depth].equals(""))
				comp = 0;
			else if(p1.getAttribute(sorting)[depth].equals("") )
				comp = 1;
			else if(p2.getAttribute(sorting)[depth].equals("") )
				comp = -1;
		 }

		}catch(NullPointerException e) {
			
		}
		
		if(comp > 0) {
			ret = false;
		}else if(comp < 0) {
			ret = true;
		}else {
			boolean b1,b2;
			// Recursion sorting order
			switch(sorting) {
			case BIRTHPLACE:
				ret = getFirstPerson(PersonAttributesEnum.SURNAME, p1, p2, 0); // Continues recursion 
				break;
			case SURNAME:
				
				// Checks if the Person objects have more attributes of the sorting type, if they do, the recursion increases in depth.
				b1 = p1.getAttribute(PersonAttributesEnum.SURNAME).length <= depth+1;
				b2 =  p2.getAttribute(PersonAttributesEnum.SURNAME).length <= depth+1;
				if(b1 && b2 ) {
					ret = getFirstPerson(PersonAttributesEnum.NAME, p1, p2, 0); // Continues recursion 
				}else if(b1 ){
					ret = false;
				}else if(b2){
					ret = true;
				}else {
					ret = getFirstPerson(PersonAttributesEnum.SURNAME, p1, p2, depth+1); // Continues recursion, comparing the same attribute type but increasing depth
				}
				
				
				break;
			case NAME:
				
				// Checks if the Person objects have more attributes of the sorting type, if they do, the recursion increases in depth.
				b1 = p1.getAttribute(PersonAttributesEnum.NAME).length <= depth+1;
				b2 = p2.getAttribute(PersonAttributesEnum.NAME).length <= depth+1;
				if(b1 && b2 ) {
					ret = false;
				}else if(b1 ){
					ret = false;
				}else if(b2){
					ret = true;
				}else {
					ret = getFirstPerson(PersonAttributesEnum.NAME, p1, p2, depth+1); // Continues recursion, comparing the same attribute type but increasing depth

				}
				
				
				break;
			default:
				System.err.println("Not implemented in menu functions");
				break;
			}
		}
		
		
		
		return ret;
	}
	
	
	/**
	 * Sorting function for the Point 8 of the programming project. It uses selection sorting algorithm O(n2) recursively. It sorts by birthplace, surnames and names.
	 * @param people - Collection {@link Collection} of people to be sorted
	 * @return Person {@link Person}[] sorted
	 */
	private Person[] sortPersonArray(Collection<Person> people){
		
		// Creating unordered array of Person. Casting collection.toArray() doesn't work.
		Person[] ret = new Person[people.size()];
		int sum = 0;
		Iterator<Person> it = people.iterator();
		while(it.hasNext())
			ret[sum++] = it.next();
		
		int size = people.size();
		Person p;
		int index = -1;
		
		// Sort by selection order of the attributes sorted lexicographically birthplace, surname, name
		for(int i = 0; i < size-1; i++) {
			p = ret[i];
			index = -1;
			for(int j = i+1; j < size; j++) {
				if(getFirstPerson(PersonAttributesEnum.BIRTHPLACE, ret[j], p, 0)) {
					p = ret[j];
					index = j;
				}
			}
			if(index != -1) {
				ret[index] = ret[i];
				ret[i] = p;
			}
		}
		
		// TODO use a better sorting algorithm
		
		
		return ret;
	}
	
	
}
