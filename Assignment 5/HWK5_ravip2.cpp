/**
 * @author: Pareekshit Ravi
 * @studentNumber: 1407109
 * @macID: ravip2
 * @Description: Takes number inputs from the keyboard and calculates the average and sample standard
 * 		 deviation of the numbers
 */

//imports
#include <iostream>
#include <list>
#include <sstream>
#include <math.h>
#include <stdio.h>
using namespace std;

float calculateAverage(list<float> numbers){
	//variable to hold the sum of all the numbers
	float sum=0;
	//for loop iterating through the values in the list using the indicators of the list
	for (list<float>::iterator it=numbers.begin(); it != numbers.end(); ++it){
		//getting the number from the list and saving it in number variable
		float number = *it;
		//adding number to the value of sum
		sum+=number;
	}
	//the answer is the sum divided by the number of entries (size of the list)
	float answer = sum/numbers.size();
	//returning the answer
	return answer;
}
float calculateStdDiv(list<float> numbers){
	//calculating the average of the numbers given
	float average = calculateAverage(numbers);
	//list to hold the values that are the difference between the value and the mean/average
	list<float> diffMean;
	//for loop iterating through the values in the list using the indicators of the list
	for (list<float>::iterator it=numbers.begin(); it != numbers.end(); ++it){
		//temporary variable is the difference between the value in the list and the average of the list
		float temp = *it - average;
		//squaring the value
		temp= pow(temp,2);
		//adding the value to the front of diffMean list
		diffMean.push_front(temp);
	}
	//calculating the average of the newly made list
	float answer = calculateAverage(diffMean);
	//taking the square root of the average calculated above
	answer= pow(answer,0.5);
	//returning the number
	return answer;
}
int main() {
	//initializing a variable to hold the user's input
	string input = "";
	//initializing a list to hold all the user's inputs
	list<float> numbers;\
	//counter for number of entries
	int numberCount=1;
	//while loop while the input string is not equal to '#' character
	while(input!="#"){
		//prompt user for number
		cout << "Enter value " << numberCount << ": ";
		//save the user's entry to the input variable
		cin >> input;
		//variable to change the input string to a float
		float num;
		//treating string like  stream named ss
		stringstream ss(input);
		//ss is now a float with the value of the string
		ss >> num;
		//adding the num variable to the numbers list
		numbers.push_front(num);
		//adding one to the count of entries
		numberCount++;
	}
	//after loop is finished, the first value in the list is the #, so removing
	//the out
	numbers.pop_front();
	//going to the next line in the printing screen
	cout << "\n";
	//subprogram to find the average and print to screen
	cout << "The average is " << calculateAverage(numbers);
	cout << "\n";
	//subprogram to find the sample standard deviation and print to screen to
	//2 decimal places
	printf("The standard deviation is %0.2f", calculateStdDiv(numbers));
	return 0;
}
