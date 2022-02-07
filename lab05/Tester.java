package lab05;

public class Tester {
	
	public static void main(String[] args) {
		
		
		
		String line = "-----------------------------";
		double[] test = {1.2, 1.4, 2.0, 1.9};
		double[] test1 = {1.2, 1.4, 1.6, 2.0, 1.9};
		
		SortedBinarySet sortedBinarySet = new SortedBinarySet(); // testing the first
		
		SortedBinarySet set = new SortedBinarySet(test1); // testing the second constructor
		
		System.out.println(set.toString());               // testing the toString
		
		System.out.println(line);
		
		sortedBinarySet.insert(1.2);
		sortedBinarySet.insert(1.4);            // testing the insert method
		sortedBinarySet.insert(1.8);
		sortedBinarySet.insert(2.0);
		sortedBinarySet.insert(1.9);
		
		System.out.println(sortedBinarySet.toString());
		
		sortedBinarySet.remove(1.8);                        // testing the remove method
		
		System.out.println(line);
		
		System.out.println(sortedBinarySet.toString());
		
		System.out.println(line);
		
		sortedBinarySet.grow();                              // testing the grow method, capacity should increase
		
		System.out.println(sortedBinarySet.toString());
		
		System.out.println(line);
		
		System.out.println("contains test for 1.2: " + sortedBinarySet.contains(1.2));
		System.out.println("contains test for 1.3: " + sortedBinarySet.contains(1.3));
		System.out.println(line);
		System.out.println("containsAll test for test: " + sortedBinarySet.containsAll(test));
		System.out.println("containsAll test for test1: " + sortedBinarySet.containsAll(test1));
		
		System.out.println(line);
		
		sortedBinarySet.clear();                      // testing the clear method
		
		System.out.println(sortedBinarySet.toString());
		
		System.out.println(line);
		
	
		// -----------------------------------------------------------------------------
		
		// sequential vs binary 
		
		SortedBinarySet set2 = new SortedBinarySet();
		
		double tempTest = 1.0;
		for (int i = 0; i < 1000000; i++) {
			set2.insert(tempTest);
			tempTest += 1.0;
		}
		
		//int tempIndex = set2.findIndex(89000.0);
		int tempIndex1 = set2.findIndex(323000.0);
		
		System.out.println("" + tempIndex1);
		
		System.out.println(System.nanoTime());
		
		// For sequentialFind (100k array), 
		//     the index was 88999 as expected
		//     the nanoTime was 546802830056300  / Test 2: 547191347665000  / Test 3: 547402415151800
		
		// For binaryFind (100k array),
		//     the index was 88999 of course
		//     the nanoTime was 546898492427499* / Test 2; 547286350260500* / Test 3: 547424376536999*
		
		// For sequentialFind (1m array), 
				
		//     Console was taking forever not outputting anything
		
		// For binaryFind (1m array),
				//     the index was 322999 of course
				//     the nanoTime was 547806283144200
		
		/*
		 * For the 100k array the sequential find was faster, but that's only because 100k is a small number.
		 * For the 1m array the binary find was much faster, the sequential find didn't even fully run.
		 * Therefore, in the long run, binary search is much more efficient than sequential.
		 * 
		 * 
		 * 
		 */
		
	}
}
