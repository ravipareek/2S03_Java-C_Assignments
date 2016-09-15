import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Name: Pareekshit Ravi
 * @MacID: ravip2
 * @Student_Number:  1407109
 * @Description: Finding all the combinations of numbers that will sum together to give the last argument entered
 */
public class HWK3_3_ravip2 {
	//golbal List to hold all the answers that the program finds
	public static List<String> allAnswers = new ArrayList<String>();
	
	public static void main(String[] args) {
		//initializing the size of the array that will hold all the numbers that are used to find the sum
		int[] inputs = new int[args.length-1];
		//loop to iterate through the size of the input array created
		for(int i=0;i<inputs.length;i++){
			//populating the array with the inputs
			inputs[i] = Integer.parseInt(args[i]);
		}
		//the last input is the sum variable
		int sum = Integer.parseInt(args[args.length-1]);
		//creating a list to hold the answer
		//not using an array because the size of the answer is unknown
		List<Integer> answer = new ArrayList<Integer>();
		//populating the list with a 0 so it is not empty
		answer.add(0);
		//loop to iterate through the size of the input array
		for(int i=0;i<inputs.length;i++){
			//if the value of the number at that instance is smaller than the sum
			if ((inputs[i]<sum)){
				//call the recursive function to get the possible sums starting at the i'th value of the input array
				findAllSums(inputs,i,answer,sum);
				//emptying the answer array
				answer.removeAll(answer);
				//and populating it with a 0 so it is not empty
				answer.add(0);
			}
		}
	}

	private static void findAllSums(int[] inputs, int index, List<Integer> answer, int sum) {
		//initializing the sum variable
		int currentSum = 0;
		//loop to iterate through the size of the answer list
		for(int i=0;i<answer.size();i++){
			//calculating the sum of all the numbers in the answer list
			currentSum+=answer.get(i);
		}
		//if the currentSum + the value of the inputs at the index passed is less than or equal to the desired sum
		if (inputs[index]+currentSum<=sum){
			//add the value at the index of the input array to the a answer
			answer.add(inputs[index]);
			//and also change the value of the current sum to have the new input
			currentSum+=inputs[index];
		}
		//if the sum is reached
		if (currentSum==sum){
			//inputing the first curly bracket
			String printAnswer = "{";
			//loop for the size of the answer list
			for(int i=1;i<answer.size();i++){
				//printing each entry of the answer list followed by a comma
				printAnswer+=answer.get(i) + ",";
			}
			//removing the final comma
			printAnswer = printAnswer.substring(0, printAnswer.length()-1);
			//printing the closing bracket
			printAnswer+="}";
			//checks the list of previous answers to ensure no answers are repeated
			if(!alreadyGot(printAnswer)){
				//the answer is unique and it is added to the list of answers for check for future
				allAnswers.add(printAnswer);
				//print the answer
				System.out.println(printAnswer);
			}
		}
		//the sum is not yet reached
		else{
			//as long as it is not at the final value of the input array
			if (index!=inputs.length){
				//saving the current length of the answer array
				int length = answer.size();
				// loop for the size of the input array but starting from the next number from 
				//the index that it is currently at
				for (int i=index+1;i<inputs.length;i++){
					//recursively calling to find the answer but now checking with the following number
					findAllSums(inputs,i,answer,sum);
					//for loop for the length of the answer array when it was last at this position of the stack
					//iterating backwards
					for (int j=answer.size();j>length;j--){
						//removing all the newly added numbers so it can continue the search for all combinations
						//by ignoring the i'th value it added last time and trying from there on
						answer.remove(j-1);
					}
				}				
			}
		}
	}

	private static boolean alreadyGot(String ans) {
		//loop for the size of the allAnswers list
		for (int i=0;i<allAnswers.size();i++){
			//get the i'th solution
			String xSol = allAnswers.get(i);
			//removing the curly brackets from the solution in the list and the answer passed as a parameter
			xSol = xSol.replace("{","");
			xSol = xSol.replace("}","");
			ans = ans.replace("{","");
			ans = ans.replace("}","");
			//making the existing solution into an array by separating them by commas
			String[] xSolArray = xSol.split(",");
			//making the passed solution in to an array by separating them by commas
			String[] ansArray = ans.split(",");
			//sorting the two arrays in alphabetical order
			Arrays.sort(xSolArray);
			Arrays.sort(ansArray);
//			printArray(xSolArray);
//			printArray(ansArray);
			//if their lengths are different, then do nothing and go to check the next value in the allAnswers list
			if(xSolArray.length!=ansArray.length){
			} 
			//the two arrays have the same lengths, and this checks if the values are equal
			//since the two are sorted, if the solutions are the same just in a different order
			//this will get rid of that case
			else if (Arrays.equals(xSolArray, ansArray)){
				//return true if they are both equal, if not, then try the next value in allAnswers
				return true;
			}
			
		}
		//if it made it through the full for loop, it means the string passed was not
		//found in the all answers list, and thus false is passed
		return false;
	}

	@SuppressWarnings("unused")
	private static void printArray(String[] array) {
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]);
		}
		System.out.println();
	}

}

