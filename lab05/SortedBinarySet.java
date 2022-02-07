package lab05;

public class SortedBinarySet {
	/** represent the simple array that holds the list values */
	public double[] theData;
	/** holds the length of the storage array */
	private int capacity;
	/** holds the number of elements in the list */
	private int size;

	/** constant for initial storage array capacity */
	private static final int INITIAL_STORAGE_CAPACITY = 11;

	/** keep this FALSE for lab; we will play with this in the assignment **/
	public boolean usesBinarySearch = true;

	/** default constructor */
	public SortedBinarySet(){
		
		this.capacity = INITIAL_STORAGE_CAPACITY;
		this.theData = new double[this.capacity];
		this.size = 0;
	}

	public SortedBinarySet(double[] input){
		this();
		
		for (int i = 0; i < input.length; i++) {
			insert(input[i]);
		}
	}

	public boolean empty(){
		if (size == 0) {
			return true;
		} else {
			return false; //placeholder
		}
	}

	public int size(){
		return size;
	}

	public void grow(){
		this.capacity = this.capacity * 2;
		double[] tempData = theData;
		theData = new double[this.capacity];
		
		for (int i = 0; i < size; i++) {
			theData[i] = tempData[i];
		}
	}

	public String toString(){
		
		String outString = "";
		for (int i = 0; i < size; i++) {
			String temp = Double.toString(theData[i]);
			outString += temp + "\n";
		}
		
		return outString + "\n" + "capacity: " + Integer.toString(capacity) + ", size: " + Integer.toString(size);
	}

	public void clear() {
		theData = new double[capacity];
		size = 0;
	}

	public boolean insert(double newVal){
		
		int index = findIndex(newVal);
		//System.out.println("Index is:" + index);
		if (size == capacity) {
			grow();
		}
		
		if (index >= 0) {
			return false;
		} else {
			index = (-index) -1;
			for (int i = size-1; i >= index; i--) {
				theData[i+1] = theData[i];
			}
			theData[index] = newVal;
			size++;
			return true;
		}
	}

	public boolean remove(double element){
		int index = findIndex(element);
		
		if (index < 0) {
			return false;
		} else {
			for (int i = index; i < size-1; i++) {
				theData[i] = theData[i+1];
			}
			size--;
			return true;
		}
	}


	private int sequentialFind(double target) {
		
		for (int i = 0; i < size; i++) {
			if (target == theData[i]) {
				return i;
			} else if (target < theData[i]) {
				return -i-1;
			}
		}
		
		return -size - 1;
	}

	private int binaryFind(double target) {
		int min = 0;
		int max = size-1;
		
		while (min <= max) {
			int mid = (max + min)/2;
			if (theData[mid] == target) {
				return mid;                         // if target is found
			} else if (theData[mid] < target) {
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}
		
		
		return -min-1 ;
	}

	public int findIndex(double target) {
		if (usesBinarySearch) {
			return binaryFind(target);
		} else {
			return sequentialFind(target);

		}
	}

	public boolean containsAll(double[] elements){
		for (int i = 0; i < elements.length; i++) {
			if (binaryFind(elements[i]) < 0) {
				return false;
			}
		}
		
		return true;
	}

	public boolean contains(double element){
		if (binaryFind(element) >= 0) {
			return true;
		} else {
			return false;
		}
	}

}
