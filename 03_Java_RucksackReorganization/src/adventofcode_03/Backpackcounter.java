package adventofcode_03;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;




public class Backpackcounter {

	public static LinkedList<String> ReadInRucksack (File file) {

		//create list to fill in the content of the strings
		LinkedList<String> rucksackInhalt = new LinkedList<>();
		
		//Create object of Buffered Reader
		BufferedReader br = null;
		try {
			br = new BufferedReader (new FileReader(file));	
			String st;	
			//read in each line of file
			while ((st = br.readLine()) != null) {
//				System.out.println(st);
				rucksackInhalt.add(st);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(rucksackInhalt.size());
		return rucksackInhalt;

    }
	public static LinkedList<ArrayList<String>> divideRucksackCompartments(LinkedList<String> rucksackInhalt) {
		LinkedList<ArrayList<String>> rucksackCompartments = new LinkedList<ArrayList<String>>();
		String st1;
		String st2;	
    	//read in each strings from List and divide each String st up into the two compartments
		for (String st : rucksackInhalt) {
	    	ArrayList<String> item = new ArrayList<String>();
	    	st1 = st.substring(0, (st.length()/2));
	    	st2 = st.substring((st.length()/2));
	        item.add(st1);
	        item.add(st2);
	        rucksackCompartments.add(item);
			}
//		System.out.println(rucksackCompartments);
//		System.out.println(rucksackCompartments.size());
		return rucksackCompartments;	
	}
	
	public static int findLetterInCompartments(LinkedList<ArrayList<String>> rucksackCompartments) {
		int sum = 0;
		
		for (ArrayList<String> item : rucksackCompartments) {
//			System.out.println(item);
			for (int outer= 0; outer<item.get(0).length(); outer++) {
				char letter = item.get(0).charAt(outer);
				for (int inner=0;inner<item.get(1).length(); inner++) {
					if (letter == item.get(1).charAt(inner)) {
						sum += findValue(letter);
						// set outer loop to last letter so that when this inner loop gets to the "break" statement, 
						// the for loop goes to the next item instead of continuing the search for matching letters
						outer=item.get(0).length();
						break;
					}
					else {
						continue;
					}
				}				
			}
		}
		return sum;
	}
	
	public static LinkedList<ArrayList<String>> divideRucksackGroups(LinkedList<String> rucksackInhalt) {
		LinkedList<ArrayList<String>> rucksackGroups = new LinkedList<ArrayList<String>>();
		int i =0;
		ArrayList<String> group = new ArrayList<String>();
		for (String str : rucksackInhalt) {
			if (i%3 ==0) {
				//for every third backpack, create new list
				group = new ArrayList<String>();
				group.add(str);
			}
			else if (i%3 == 1) {
				group.add(str);		
			}
			else {
				group.add(str);
				rucksackGroups.add(group);
			}
			i++;
		}
		return rucksackGroups;
	}
	
	
	public static int findLetterInGroups(LinkedList<ArrayList<String>> rucksackGroups){
		int sumGroups= 0;
		// loop through all groups
		for (ArrayList<String> group : rucksackGroups) {
			System.out.println(group);
			//grab first element of group and loop through all characters
			for (int outer= 0; outer<group.get(0).length(); outer++) {
				char letter = group.get(0).charAt(outer);
				// compare to letters of second word
				for (int innerOne=0;innerOne<group.get(1).length(); innerOne++) {
					if (letter == group.get(1).charAt(innerOne)) {
						// loop through third word to find characters
						for (int innerTwo=0; innerTwo<group.get(2).length(); innerTwo++) {
							if (letter == group.get(2).charAt(innerTwo)) {
								System.out.println(letter);
								sumGroups += findValue(letter);
								//set outer and innerOne to last element, so the first for-loop grabs next group
								innerOne=group.get(1).length();
								outer=group.get(0).length();
								break;
							}
							else {continue;}
						}
	
					}
					else {
						continue;
					}
				}				
			}
		}
		return sumGroups;
	}
//	sum += findValue(letter);
//	// set outer loop to last letter so that when this inner loop gets to the "break" statement, 
//	// the for loop goes to the next item instead of continuing the search for matching letters
//	outer=group.get(0).length();
//	break;

	
	public static int findValue(char letter) {
		int numericValue = 0;
		if(Character.isLowerCase(letter)) {
			numericValue = (int)letter - (int)'a'+1;
			}
		else {
			numericValue = (int)letter - (int)'A'+27;
		}
		return numericValue;		
	}
	

	public static void main(String[] args) {

		File file = new File("/Users/mayte/Documents/GitHub/adventofcode/adventofcode_03/resources/backpack.txt");
		LinkedList<String> rucksackInhalt = ReadInRucksack(file);	
		LinkedList<ArrayList<String>> dividedCompartments = divideRucksackCompartments(rucksackInhalt);
		int sumCompartments = findLetterInCompartments(dividedCompartments);
		System.out.println(sumCompartments);
		LinkedList<ArrayList<String>> dividedGroups = divideRucksackGroups(rucksackInhalt);
//		System.out.println(dividedGroups)
		int sumGroups = findLetterInGroups(dividedGroups);
		System.out.println(sumGroups);



	
		
        
    }
}