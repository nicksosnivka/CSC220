package lab01;

// Declare an int variable to keep track of winnings.
// Set winnings to 0.
// Loop 100 times.
// Flip the coin – reuse the code you wrote in the lab
// If heads, add $1 to winnings.
// If tails, subtract $1 from winnings.
// Print out winnings.

/** Returns the amount of money you’d win or lose
* by flipping an unbalanced coin 100 times.
*
* @return the amount of money won/lost
*/


public class CoinFlipExperiment {
	
	static public int coinFlipExperiment () {
		
		int winnings = 0;
		
		for(int i = 0; i < 100; i++) {
			
			double flip = Math.random();
			if (flip < 0.505) {
				System.out.println ("Heads");
				winnings++;
			} 
			else {
				System.out.println ("Tails");
				winnings--;
			}
		
		}
			
		System.out.println(winnings);
		return winnings;
	// finish this function: it should be as easy as moving around some code
	}
	
	public static void main(String[] args) {
		int amount = coinFlipExperiment();
		System.out.println("Win/Loss amount: " + amount);
	}
	

}
