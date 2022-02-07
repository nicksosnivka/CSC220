package lab02;

public class Matrix {
	int numRows;
	int numColumns;
	int data[][];
	
	// default constructor
	public Matrix(){}
//	
//	// constructor 1 - Constructor for new zero matrix
//	public Matrix(int rowDim, int colDim){
//		/*
//		* TODO: write a constructor that would create a matrix
//		* of correct size and initialize it to 0. 
//		*/
//		
//		numRows = rowDim;
//		numColumns = colDim;
//		data = new int[rowDim][colDim];
//		
//	
//	}
//	
//	
//	// constructor 2 - Constructor with data for new matrix (automatically determines dimensions)
	public Matrix(int d[][])
	{
		/* 1) put the numRows to be the number of 1D arrays in the 2D array
		*  2) specify the numColumns and set it
		*  3) be careful of special cases you are supposed to handle them properly
		*  4) create a new matrix to hold the data
		*  5) copy the data over
		*/
		
		numRows = d.length;
		numColumns = d[0].length;
		
		data = new int [numRows][numColumns];
		
		for(int row = 0; row<numRows; row++) {
			for (int col = 0; col<numColumns; col++) {
				data[row][col] = d[row][col];
			}
		}
		
		
	}	
//	
//	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		
		/*
		 * TODO: replace the below return statement with the correct code, you must return a String that represents this 
		 * 			matrix, as specified in the instruction for M1 - anything else IS NOT acceptable
		 */
		String matrixString = "\n";
		
		for(int row = 0; row<numRows; row++) {
			for(int col = 0; col<numColumns; col++) {
				matrixString += data[row][col];
				matrixString += " ";
				if (col == numColumns - 1) {
					matrixString += "\n";
				}
			}
			
		}
		
		return matrixString; // placeholder		
	}
	
	// *** you will implement the rest of the methods for your assignment
	// *** don't touch them before finishing the lab portion
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object o)
	{
		if(!(o instanceof Matrix)) // make sure the Object we're comparing to is a Matrix
			return false;
		Matrix m = (Matrix)o; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		
		boolean equalsBool = false;
		
		if ((this.numRows == m.numRows) && (this.numColumns == m.numColumns)) {
			
			equalsBool = true;
			for(int row = 0; row<numRows; row++) {
				for(int col = 0; col<numColumns; col++) {
					if (this.data[row][col] != m.data[row][col]) {
						equalsBool = false;
					}
				}
			}
			
			
		}
		
		return equalsBool;
		
	}

	public Matrix mult(Matrix m)
	{
		
		if (this.numColumns == m.numRows) {
			
			int mult[][] = new int[this.numRows][m.numColumns];
			
			for (int row = 0; row<this.numRows; row++) {
				for (int col = 0; col<m.numColumns; col++) {
					for (int i = 0; i<this.numColumns; i++) {
						mult[row][col] += (this.data[row][i] * m.data[i][col]);
					}
				}
			}
			
			return new Matrix(mult);
			
		} else {
			return null;
		}
		
	}
	
	public Matrix add(Matrix m)
	{
		int addition[][] = new int[this.numRows][this.numColumns];
		
		if ((this.numRows == m.numRows) && (this.numColumns == m.numColumns)) {
			for(int row = 0; row<numRows; row++) {
				for(int col = 0; col<numColumns; col++) {
					addition[row][col] = this.data[row][col] + m.data[row][col];
				}
			}
			
			return new Matrix(addition);
		}
		return null;
	}
    
    public Matrix transpose()
    {
        int tempRows = this.numColumns;
        int tempColumns = this.numRows;
        
        int tempMatrix[][] = new int[tempRows][tempColumns];
        
        for(int row = 0; row<numRows; row++) {
			for(int col = 0; col<numColumns; col++) {
				tempMatrix[col][row] = this.data[row][col];
			}
		}
        
        return new Matrix(tempMatrix);
    }
}
