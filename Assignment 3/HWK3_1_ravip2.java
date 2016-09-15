/**
 * @Name: Pareekshit Ravi
 * @MacID: ravip2
 * @Student_Number:  1407109
 * @Description: Calculated the total number of combinations based on the inputs of k being the size of the set
 * 				 and n being the number of overall variables to work with
 */
public class HWK3_1_ravip2 {
	public static void main(String[] args) {
		//getting the overall number of variables from input
		int n = Integer.parseInt(args[0]);
		//getting the size of the set from input
		int k = Integer.parseInt(args[1]);
		//making sure that k>n because otherwise Binomial Factor does not exist
		if (k>n){
			//printing an error message
//			System.out.println("K must be less than n");
		}else{
			//printing the returned value
			System.out.println(binomialFactor(n,k));
		}
	}

	private static int binomialFactor(int n, int k) {
		//initializing the answer
		int factor = 0;
		//if n=k or k=0, then the answer is 1
		if (n==k || k==0){
			return 1;
		}else{
			//the binomial factor is the sum of the factor of (n-1) and (k-1) and the factor of (n-1) and k
			//the final factor will take the sum of all the factors that are calculated when the recursive
			//function was called
			
			//this recursive function exercises this math formula: n!/(k!*(n-k)!)
			factor += binomialFactor(n-1,k-1) + binomialFactor(n-1,k);
			return factor;
		}
	}
}
