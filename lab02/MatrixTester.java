/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package lab02;

public class MatrixTester {
		public static void main(String[] args)
		{			
			Matrix M1 = new Matrix(new int[][]
			                                 {{1, 2, 3},
											  {4, 5, 6}});
			
			Matrix M2 = new Matrix(new int[][]
			                                 {{7, 8, 9},
											  {10, 11, 12}});
			
			Matrix M3 = new Matrix(new int[][] {{7, 8},
												{9, 10}, 
												{11, 12}});
			
//			/* 
//			 * Note that none of the tests below will be correct until you have implemented all methods.
//			 * This is just one example of a test, you must write more tests and cover all cases.
//			 */			
//			
			Matrix ComputedAddition = M1.add(M2);
			
			System.out.println("Computed result for M1 + M2:\n" + ComputedAddition);
					
//			// this is the known correct result of multiplying M1 by M2
			Matrix referenceMultiply = new Matrix(new int[][]
			                                                {{58, 64},
															 {139, 154}});			
			
			// get the matrix computed by your mult method
			Matrix computedMultiply = M1.mult(M3);
			
			// exercises your toString method
			System.out.println("Computed result for M1 * M3:\n" + computedMultiply); 
			
			// exercises your .equals method, and makes sure that your computed result is the same as the known correct result
			if(!referenceMultiply.equals(computedMultiply)) 
				System.out.println("Your multiplication is wrong. Should be:\n" + referenceMultiply);
//			
//			//TODO: fill in more tests that fully exercise all of your methods
			
			boolean computedEquals = M1.equals(M2);
			
			System.out.println("M1 equals M2? \n" + computedEquals); // checking if they both don't equal each other it returns false
			
			Matrix M4 = new Matrix(new int[][] {{3, 5},
												{8, 2}, 
												{5, 0}});
			
			boolean computedEqualsPt2 = M3.equals(M4); // checking if it returns true if both equal
			
			System.out.println("M3 equals M4? \n" + computedEqualsPt2);
			
			boolean computedEqualsPt3 = M2.equals(M3); // checking if returns false if dimensions are not the same
			
			System.out.println("M2 equals M3? \n" + computedEqualsPt3);
	
			Matrix transposeTest1 = M1.transpose();
			
			System.out.println("The transpose of M1 is \n" + transposeTest1);
			
			Matrix transposeTest2 = M2.transpose();
			
			System.out.println("The transpose of M1 is \n" + transposeTest2);
			
			Matrix transposeTest3 = M3.transpose();
			
			System.out.println("The transpose of M1 is \n" + transposeTest3);
			
			Matrix ComputedAdditionPt2 = M2.add(M3);
			
			System.out.println("Computed result for M2 + M3:\n" + ComputedAdditionPt2); //checking if it returns null if dimensions are not the same
			
			Matrix ComputedAdditionPt3 = M3.add(M4);
			
			System.out.println("Computed result for M3 + M4:\n" + ComputedAdditionPt3);
			
			Matrix computedMultiplyPt2 = M2.mult(M3);
			
			Matrix referenceMultiplyPt2 = new Matrix(new int[][]
                    {{220, 244},
					 {301, 334}});
			
			System.out.println("Computed result for M2 * M3:\n" + computedMultiplyPt2);
			
			if(!referenceMultiplyPt2.equals(computedMultiplyPt2)) 
				System.out.println("Your multiplication is wrong. Should be:\n" + referenceMultiply);
			
		}		
}
