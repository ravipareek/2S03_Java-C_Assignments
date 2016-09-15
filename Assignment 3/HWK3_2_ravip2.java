import java.util.ArrayList;
import java.util.List;

/**
 * @Name: Pareekshit Ravi
 * @MacID: ravip2
 * @Student_Number:  1407109
 * @Description: Finding all the non-contiguous substrings of the set inputed
 */
public class HWK3_2_ravip2 {
	//global list to hold all the possible solutions
	public static List<String> allAnswers = new ArrayList<String>();

	public static void main(String[] args) {
		//getting the input from the argument
		String input = args[0];
		//calling the recursive function to get the sub strings
		//the "" is because it is the answer string
		//the 2 0's are because it needs to start at the beginning of the string and so far, there have been no chars added to the answer
		nContSeq(input,"",0 ,0);

		//this logic will printing the answer is the format that is required
		for(int i=0;i<allAnswers.size();i++){
			//boolean to check that it has found the first non-comma char
			boolean firstValueFound = false;
			//getting the i'th value of the allAnswer List and splitting it by nothing to make it into an array
			String[] ans = allAnswers.get(i).split("");
			//method to remove the commas before and after the first and last terms respectively
			ans = removeCommas(ans);
			//method the check if the answer is a non-contiguous substring
			if (checkSet(ans))
			{
				//printing the open bracket
				System.out.print("{");
				//loop for the length of the answer
				for(int j=1;j<ans.length;j++){
					//if value is not a comma and the firstValueFound is false
					if (!ans[j].equals(",") && !firstValueFound){
						//set the value of first value found to true
						firstValueFound=true;
					}
					//if the first value has been found and the j'th index of the answer array is a comma
					if(firstValueFound && ans[j].equals(",")){
						//loop from one more than j till the end of the answer array
						for(int a=j+1;a<ans.length;a++){
							//if there are any more commas after j'th index
							if(ans[a].equals(",")){
								//set those positions as empty
								ans[a]="";
							}
						}
					}
					//print the string at that index
					System.out.print(ans[j]);
				}
				//it has reached the end of the answer so goes to the next line
				System.out.println();
			}
		}
	}

	private static boolean checkSet(String[] ans) {
		//list to hold all the indexes where a comma is there
		List<Integer> commaIndex = new ArrayList<Integer>();
		//for loop for the size of the answer array
		for(int i=0;i<ans.length;i++){
			//if the i'th index is a comma
			if(ans[i].equals(",")){
				//add the index to the list
				commaIndex.add(i);
			}
		}
		//a variable that will decide if the array is actually a non-contiguous substring
		boolean consecutive = true;
		//setting the index of the first comma as the first value in the list
		int index = commaIndex.get(0);
		//going from the second value of the list for the size of the commaIndex list
		//this is the number of commas in the substring after the unneeded commas were removed
		for(int i=1;i<commaIndex.size();i++){
			//if the next commas' index is the previous index's + 1, it means that there is a gap in between the commas
			//and thus it is not a non-contiguous substring
			if (commaIndex.get(i)==index+1){
				//set the variable to true
				consecutive = true;
				//making the index variable the i'th term in the list to compare to the next index in the list
				index = commaIndex.get(i);
			} 
			//the substring is not non-contiguous
			else{
				//set the variable to true
				consecutive = false;
				//leave the for loop
				break;
			}
		}
		//return the variable constructive
		//if it was a non-contiguous substring or not
		return consecutive;
	}

	private static String[] removeCommas(String[] ans) {
		//initializing variables for the indexes of the the first and last chars
		int first=0;
		int last=0;
		//for loop from 1 to the size of the answer array
		for(int i=1;i<ans.length;i++){
			//skipping over the first curly bracket, the first non-comma char is the first
			if(!ans[i].equals(",")){
				//the index is saved in first and leave the for loop
				first=i;
				break;
			}
		}
		//for loop from answer array length - 2 because from the second last elemebt till the first element
		for(int i=ans.length-2;i>first;i--){
			//	the first non-comma char is the last char		
			if(!ans[i].equals(",")){
				//the index is saved in last and leave the for loop
				last=i;
				break;
			}
		}
		//for loop from the start of the array till first
		for(int i=0;i<first;i++){
			//if there is a comma there
			if(ans[i].equals(",")){
				//set the value at index i to be blank
				ans[i]="";
			}
		}
		//for loop from the last char of the array till the end
		for(int i=last;i<ans.length;i++){
			//if there is a comma there
			if(ans[i].equals(",")){
				//set the value at index i to be blank
				ans[i]="";
			}
		}
		//return the changed array
		return ans;
	}

	private static void nContSeq(String input, String ans, int index, int chars) {
		//if it has reached the final element of the input string
		if (index == input.length()) {
			//replacing the commas with spaces, and then trimming the string by removing the spaces
			//and comparing that length to the added value
			//if the length of that is larger than chars, it means that it is a solution
			if (ans.replaceAll(",", " ").trim().length() > chars){
				//adding the answer string with the curly brackets around it to the global list
				allAnswers.add("{"+ans+"}");
			}
		} 
		//the index has not yet reached the final element of the input string
		else {
			//recursively calling the function but this time adding the next letter to the answer and incrementing both counters
			nContSeq(input, ans + input.substring(index,index+1), index + 1, chars + 1);
			//recursively calling the function but instead of passing the next element of the string,
			//it adds a comma there instead and doesn't increment the chars counter because no letter was added to the answer
			nContSeq(input, ans + ",", index + 1, chars);
		}
	}

}
