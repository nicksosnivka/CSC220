package lab06;

import java.util.Arrays;

public class Tester {
	
	public static void main(String[] args) {
		
		String line = "-------------------------------------------------";
		
		SortedString sort = new SortedString("APFZs");                  // testing constructor
		
		System.out.println("Unsorted: " + sort.getUnsorted() + "\n" + "Sorted: " + sort.getSorted());
		
		//--------------------------------------------------------------------------
		
		SortedString sort1 = new SortedString("joy");  
		SortedString sort2 = new SortedString("ski");  
		
		
		System.out.println(line + "\n" + sort1.compareTo(sort2));        // SortedString test should return a number > than 0
		
		//--------------------------------------------------------------------------
		
		SortedString sort3 = new SortedString("below");  
		SortedString sort4 = new SortedString("Elbow");  
		
		System.out.println(line + "\n" + sort3.compareTo(sort4));    // Should return 0
		
		System.out.println(line);
		
		
		//--------------------------------------------------------------------------
		InsertionSort<String> sortList = new InsertionSort<String>();
		
		InsertionSort<Integer> numSort = new InsertionSort<Integer>();
		
		String[] oneEle = {"hi"};
		
		String[] oneSorted = sortList.sort(oneEle);
		
		System.out.println(oneSorted[0]);                  // should print "hi"
		
		System.out.println(line);
		
		//--------------------------------------------------------------------------
		
		String[] twoEle = {"hi", "abc"};
		
		String[] twoSorted = sortList.sort(twoEle);
		
		System.out.println(twoSorted[0] + " " + twoSorted[1]);   // output should be abc hi
		
		System.out.println(line);
		
		//--------------------------------------------------------------------------
		
		Integer[] numList = {1, 2, 3, 4, 5, 6};
		
		Integer[] sortedNum = numSort.sort(numList);
		
		System.out.println(Arrays.toString(sortedNum));                            // output should be 1 2 3 4 5 6
		
		System.out.println(line);
		
		//--------------------------------------------------------------------------
		
		Integer[] numList2 = {3, 5, 7, 1, 9, 2};
		
		Integer[] sortedNum2 = numSort.sort(numList2);
		
		System.out.println(Arrays.toString(sortedNum2));                  // should be 1 2 3 5 7 9
		
		System.out.println(line);
		
		//---------------------------------------------------------------------------
		
		InsertionSort<SortedString> ssTemp = new InsertionSort<SortedString>();
		
		String[] sTest = {"joy", "ski", "fed", "cat"};
		
		SortedString[] ssTest = SortedString.toSortedString(sTest);
		
		System.out.println(Arrays.toString(ssTemp.sort(ssTest)));
		
		System.out.println(line);
		
		//---------------------------------------------------------------------------
		
		InsertionSort<Double> isNum = new InsertionSort<Double>();
		
		Double[] isSort = {5.0, 8.0, 2.0, 4.0, 6.0, 1.0, 9.0, 3.0, 7.0, 10.0};                          // Predicting run time
		
		isNum.fit(isSort);
		
		Double[] listK = new Double[100000];
		
		for (int i = 0; i < listK.length-1; i++) {
			listK[i] = Math.random() * 100000;
		}
		
		System.out.println("Prediction runtime of listK: " + isNum.predict(listK.length) + "  (insertionSort)" + "\n" + line);   
		
		
		// With insertionSort, my functions predict that to sort a 100k array, it will take
		// 7.0E8 (11.6 min) to 1.5E9 (25 min)
		
		
		//---------------------------------------------------------------------------
		
		String[] s3 = AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");      // text tester
		
		System.out.println(Arrays.toString(s3) + "\n" + line);
		
		//---------------------------------------------------------------------------
		
		SortedString[] noElements = new SortedString[0];                               // zero elements
		
		String[] s1 = AnagramUtil.getLargestAnagramGroup(noElements);
		
		System.out.println(Arrays.toString(s1) + "\n" + line);
		
		//---------------------------------------------------------------------------
		
		SortedString one = new SortedString("Nicky");                                  
		
		SortedString[] oneElement = {one};                     							// one elements
		
		String[] s2 = AnagramUtil.getLargestAnagramGroup(oneElement);
		
		System.out.println(Arrays.toString(s2) + "\n" + line);
		
		//---------------------------------------------------------------------------
		
		SortedString two = new SortedString("ybo");
		SortedString two2 = new SortedString("boy");
		
		SortedString[] twoElement = {two, two2};                                 // two elements
		
		String[] s4 = AnagramUtil.getLargestAnagramGroup(twoElement);
		
		System.out.println(Arrays.toString(s4) + "\n" + line);
		
		//---------------------------------------------------------------------------
		
		SortedString noAna = new SortedString("acb");
		SortedString noAna1 = new SortedString("zab");
		SortedString noAna2 = new SortedString("mba");                       // no anagrams
		
		SortedString[] noAnagrams = {noAna, noAna1, noAna2};
		
		String[] s5 = AnagramUtil.getLargestAnagramGroup(noAnagrams);
		
		System.out.println(Arrays.toString(s5) + "\n" + line);
		
		//---------------------------------------------------------------------------
		
		String[] s6 = AnagramUtil.getLargestAnagramGroup("largest_anagram_middle.txt");
		 																					// largest group in the middle
		System.out.println(Arrays.toString(s6) + "\n" + line);                                    
		
		//---------------------------------------------------------------------------
		
		String[] s7 = AnagramUtil.getLargestAnagramGroup("largest_anagram_end.txt");
																							// largest group at the end
		System.out.println(Arrays.toString(s7) + "\n" + line);  
		
		//---------------------------------------------------------------------------
		
		MergeSort<Integer> mergeSort = new MergeSort<Integer>();
		
		Integer[] oneNum = {1};                                                              // output should be [1]  (mergesort)
		Integer[] oneNumSorted = mergeSort.sort(oneNum);
		
		System.out.println(Arrays.toString(oneNumSorted) + "  (mergesort)" + "\n" + line);
		
		//---------------------------------------------------------------------------
		
		Integer[] twoNum = {2,1};
		Integer[] twoNumSorted = mergeSort.sort(twoNum);                                    // output should be [1, 2]  (mergesort)
		
		System.out.println(Arrays.toString(twoNumSorted) + "  (mergesort)" + "\n" + line);
		
		//---------------------------------------------------------------------------
		
		Integer[] sortedNumList = {1, 2, 3, 4, 5, 6};
		Integer[] sortedNumSorted = mergeSort.sort(sortedNumList);                                    
		                                                                                      // already sorted
		System.out.println(Arrays.toString(sortedNumSorted) + "  (mergesort)" + "\n" + line);
		
		//---------------------------------------------------------------------------
		
		Integer[] randomNumList = {4, 6, 2, 8, 5, 3, 1};
		Integer[] randomNumSorted = mergeSort.sort(randomNumList);                              // random list being sorted    
		
		System.out.println(Arrays.toString(randomNumSorted) + "  (mergesort)" + "\n" + line);
		
		//---------------------------------------------------------------------------
		
		MergeSort<Double> mergeSort2 = new MergeSort<Double>();                  // Predicting run time for mergeSort
		
		mergeSort2.fit(isSort);            // using an old 10 element array
		
		Double[] listK2 = new Double[100000];
		
		for (int i = 0; i < listK2.length-1; i++) {
			listK2[i] = Math.random() * 100000;
		}
		
		System.out.println("Prediction runtime of listK: " + mergeSort2.predict(listK.length) + "  (mergeSort)" + "\n" + line);  
		
		
		// With mergeSort, my functions predict that to sort a 100k array, it will take
		// 1000000 (1 second) to 1100000 (1.1 seconds)
		// This makes sense since mergeSort is more efficient than than insertionSort
		// O(nlogn) < O(n^2)
		
	}
}
