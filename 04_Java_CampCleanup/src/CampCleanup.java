import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class CampCleanup {

	public static LinkedList<String> ReadInArea (File file) {

		//create list to fill in the content of the strings
		LinkedList<String> area = new LinkedList<>();
		
		//Create object of Buffered Reader
		BufferedReader br = null;
		try {
			br = new BufferedReader (new FileReader(file));	
			String st;	
			//read in each line of file
			while ((st = br.readLine()) != null) {
				area.add(st);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return area;
	

    }
	public static LinkedList<ArrayList<Integer>> ElfPair(LinkedList<String> area){
		LinkedList<ArrayList<Integer>> elfpairs = new LinkedList<ArrayList<Integer>>();
		for (String st: area) {
			ArrayList<Integer> range = new ArrayList<Integer>();
			String split_comma[] = st.split(",");
			
			for (String s: split_comma) {
				//System.out.println(s);
				String split_minus[] = s.split("-");
				for (String s_minus: split_minus) {
					range.add(Integer.parseInt(s_minus));
					
					//System.out.println(s_minus);
				}
			}
			elfpairs.add(range);
		}
		return elfpairs;		
	}
	
	public static int CompareAreaTotal(LinkedList<ArrayList<Integer>> elfpairs) {
		int count = 0;
		for (ArrayList<Integer> elves : elfpairs) {
			Boolean double_area = false;
			int start_one = elves.get(0);
			int end_one = elves.get(1);
			int start_two = elves.get(2);
			int end_two = elves.get(3);
			
			// compare values:
			if (start_one == start_two) {
				++count;
				double_area=true;
			}
			
			else if ((start_one < start_two) && (end_one >= end_two)) {
					++count;
					double_area=true;
					//continue;
			}
			else if ((start_two < start_one) && (end_two >= end_one) )  {
					++count;
					double_area=true;
			}
				

			
//			if (double_area == true){
//				System.out.println(elves);
//			}
			}
		//System.out.println(count);
		return count;

		}
	public static int CompareAreaOverlap(LinkedList<ArrayList<Integer>> elfpairs) {
		int count = 0;
		for (ArrayList<Integer> elves : elfpairs) {
			Boolean area_overlap = false;
			int start_one = elves.get(0);
			int end_one = elves.get(1);
			int start_two = elves.get(2);
			int end_two = elves.get(3);
			
			if  (!(((start_one < start_two) && (end_one<start_two)) | ((start_two < start_one) && (end_two < start_one)))) {
				System.out.println(elves);
				++count;
			}
		}
		return count;
		
	}
	
	
	
	public static void main(String[] args) {
		File file = new File("/Users/mayte/GitHub/adventofcode_2022/04_Java_CampCleanup/resources/camp.txt");	
		LinkedList<String> area = ReadInArea(file);
		LinkedList<ArrayList<Integer>> elves = ElfPair(area);
		//System.out.println(elves);
		System.out.println(CompareAreaTotal(elves));
		System.out.println(CompareAreaOverlap(elves));
	

	}

}
