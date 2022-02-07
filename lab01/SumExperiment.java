package lab01;

public class SumExperiment {

	public static int checkSum(int[] array){
		// This function will inspect the input to find any pair of values that add up to 20
		// if it find such a pair, it will return the *index* of the smallest value
		// if it does not find such as pair, it will return -1;
		
		// remove the following line after you are done writing the function
		
		int index;
		index = -1;
		int start = 0;
		int end = array.length-1;
		
		while(index == -1 && start < end) {
			
			if ((array[start] + array[end]) == 20) {
				index = start;
			} else if ((array[start] + array[end]) > 20) {
				end--;
			} else if ((array[start] + array[end]) < 20) {
				start++;
			}
			
		}
		
		return index;
	}
	
	
	public static void main(String[] args) {
		int[] array1 = new int[]{5, 7, 8, 9, 10, 15, 16};
		if (checkSum(array1) != 0)
			System.err.println("TEST1 FAILED");
		
		int[] array2 = new int[]{3, 5, 8, 9, 10, 15, 16};
		if (checkSum(array2) != 1)
			System.err.println("TEST2 FAILED");

		
		int[] array3 = new int[]{3, 4, 6, 9, 10, 14, 15};
		if (checkSum(array3) != 2)
			System.err.println("TEST3 FAILED");
		
		int[] array4 = new int[]{6, 7, 8, 9, 10, 15, 16};
		if (checkSum(array4) != -1)
			System.err.println("TEST4 FAILED");
		
		System.out.println("Done!!!");
	}

}
