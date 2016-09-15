/*
 * Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * MacID: ravip2, shahk24, olivepjm
 * Student_Number:  1407109, 1419350, 1430273
 * Description: TBD
 */

//CHANGES

#include <string>
 #include <sstream>
#include <vector>
#include <iostream>
#include <iomanip>
#include "Addition.h"
#include "Subtraction.h"
#include "Multiplication.h"
#include "Division.h"


using namespace std;

int main();
vector<char> stringToVector(string in){
	vector<char> returnValue;
	for(int i = 0; i < in.length(); i++){
		returnValue.push_back(in[i]);
	}
	return returnValue;
}

string vectorToString(vector<char> in){
	string returnValue;
	for(int i = 0; i < in.size(); i++){
		returnValue += in[i];
	}
	return returnValue;
}

vector<char> addBrackets(int pos, const string e) {
	//initializing the variables
	string returning = "";
	string leftPart = "";
	string rightPart = "";
	int brackets = 0;
	int leftStop = 0;
	int rightStop = 0;
	//right part after * or /
	for (int i = pos + 1;i < e.length();i ++) {
		if (e[i] == '(') {
			brackets ++ ;
		}
		if (e[i] == ')') {
			brackets -- ;
		}
		if (brackets == 0 && (e[i] == '+' || e[i] == '-' || e[i] == '*' || e[i] == '/')) {
			rightPart = e.substr(i, e.length() - i);
			rightStop = i;
			break;
		}
	}
	//left part before * or /
	for (int i = pos - 1;i > 0;i --) {
		if (e[i] == '(') {
			brackets ++ ;
		}
		if (e[i] == ')') {
			brackets -- ;
		}
		if (brackets == 0 && (e[i] == '+' || e[i] == '-' || e[i] == '*' || e[i] == '/')) {
			leftPart = e.substr(0,i + 1);
			leftStop = i;
			break;
		}
	}
	//adding ( to left part
	leftPart = leftPart + string("(");
	//add ) to start of right part
	rightPart = string(")") + rightPart;
	//if left stop is 0
	if (leftStop == 0)
	{
		//make leftStop -1
		leftStop = -1;
	}
	//if right stop is 0
	if (rightStop == 0)
	{
		//make it the length of the string
		rightStop = e.length();
	}
	//if left and right are both just the brackets
	if (leftPart == "(" && rightPart == ")")
	{
		//set returning to the original
		returning = e;
	}
	else
	{
		//make returning the left part + substring of the expression with the * or / + the right part
		returning = leftPart + e.substr(leftStop + 1, rightStop - (leftStop + 1)) + rightPart;
	}
	//return the returninging string as a char array
	return stringToVector(returning);
}

string formatExpression(string fullExpression) {
	//initialize counter
	int bracket = 0;
	//initializing variable
	bool end = true;
	//if first and last chars are brackets
	while (fullExpression[0] == '(' && fullExpression[fullExpression.length() - 1] == ')' && bracket==0 && end) {

		//loop for length of string
		for (int i = 0;i < fullExpression.length();i ++) {
			//if char is open bracket
			if (fullExpression[i] == '(')
			{
				//increment counter
				bracket ++ ;
			}
			//if char is closed bracket
			else if (fullExpression[i] == ')') {
				//decrement counter
				bracket -- ;
				//if bracket is 0 and not at end of string
				if (bracket == 0 && i != fullExpression.length() - 1) {
					//set end to false
					end = false;
				}
			}
		}
		//end value didnt change
		if (end == true)
		{
			//remove first and last variables
			fullExpression = fullExpression.substr(1, (fullExpression.length() - 1) - 1);
		}
	}
	//return the string
	return fullExpression;
}

int checkValidity(const string fullExpression) {
	//getting char array from the string
	vector<char> chars;
	for (int i = 0; i < fullExpression.length(); ++i) {
		chars.push_back(fullExpression[i]);
	}
	//initialize count
	int brackets = 0;
	//loop for length of char array
	for (int i = 0;i < chars.size();i ++) {
		//checks if 2 operators beside each other
		//if the char4 is + - * or /
		if (chars[i] == ('+') || chars[i] == ('-') || chars[i] == ('*') || chars[i] == ('/')) {
			//if the next char is also a ioerand
			if (chars[i + 1] == ('+') || chars[i + 1] == ('-') || chars[i + 1] == ('*') || chars[i + 1] == ('/')) {
				//expression not valid
				return 2;
			}
		}
		//if char is open bracket
		if (chars[i] == '(') {
			//increment count
			brackets ++ ;
		}
		//char is close bracket
		else if (chars[i] == ')') {
			//decrement count
			brackets -- ;
		}
	}
	//if count is not 0 at end
	if (brackets != 0)
	{
		//expression not valid
		return 2;
	}
	//werid ass characters
	for (int i=0;i<chars.size();i++){
		if (!isdigit(chars[i])){
			if (!(chars[i] == '+' || chars[i] == '-' || chars[i] == '*' || chars[i] == '/' || chars[i] == '(' || chars[i] == ')' || chars[i] == '@')){
				return 2;
			}
		}
	}
	//single characters
	bool noOP = true;
	for (int i = 0;i < chars.size();i ++) {
		if (chars[i]==('+') || chars[i]==('-') || chars[i]==('*') || chars[i]==('/')) {
			noOP = false;
		}
	}
	if(noOP)
		return 1;
	//if reaches end, return true
	return 0;
}

string removeSpaces(string input){
	string out;
	for (int i = 0; i < input.length(); ++i) {
		if (input[i] != ' ') {
			out += input[i];
		}
	}
	return out;
}

int main() {
	//keep looping till break is called, when # is entered
	while (true) {
		//initializing string for the final answer
		string answer = "";
		//prompt user for input
		cout << string("Please enter an expression: ");
		//get input from user
		string fullExpression;
		getline(cin, fullExpression);
		//if the entered text is #, then break the while loop
		if (fullExpression == "#")
		{
			//break from while
			break;
		}
		//remove all spaces
		fullExpression = removeSpaces(fullExpression);
		//save the string entered by the user
		string originalExpression = fullExpression;
		//checks the validity of the expression, if true
		switch (checkValidity(fullExpression)) {
			case 0: {
				//format the expression to a form that can be executed
				fullExpression = formatExpression(fullExpression);
				//get the array of the characters of the expression
				vector<char> chars = stringToVector(fullExpression);
				//keep count on if open or closed bracket
				int brackets = 0;
				//number of operands not in brackets
				int centerOpCount = 0;
				//loop for length of char array
				for (int i = 0;i < chars.size();i ++) {
					//if the char[i] is a open bracket
					if (chars[i] == '(') {
						//increment count
						brackets ++ ;
					}
					//if the char[i] is a closed bracket
					else if (chars[i] == ')') {
						//decrement count
						brackets -- ;
					}
					//if the bracket count is less than or equal to 0 AND the char is a * or /
					if (brackets <= 0 && (chars[i] == '*' || chars[i] == '/')) {
						//add brackets around the * or /
						chars = addBrackets(i, fullExpression);
						//if there is a change in the string
						if (fullExpression != vectorToString(chars)) {
							//make fullExpression be a string made from the char array
							fullExpression = vectorToString(chars);
							//increment the count by 2
							i += 2;
						}
					}
				}
				//reset the count
				brackets = 0;
				//loop for length of char array
				for (int i = 0;i < chars.size();i ++) {
					//if the char[i] is a open bracket
					if (chars[i] == '(') {
						//increment count
						brackets ++ ;
					}
					//if the char[i] is a closed bracket
					else if (chars[i] == ')') {
						//decrement count
						brackets -- ;
					}
					//if the bracket count is  0 AND the char is a + - * or /
					else if (brackets == 0 && (chars[i] == '+' || chars[i] == '-' || chars[i] == '*' || chars[i] == '/')) {
						//increment the counter
						centerOpCount ++ ;
					}
				}
				//initialize the variable for left part of expression
				string left = "";
				//initialize the variable for right part of expression
				string right = "";
				//initialize variable for operand
				char op;
				//initialize variable for where left expression ends
				int stop = 0;
				//counter for operands
				int opCount = 0;
				//finding the center operand, outside brackets
	//			centerOpCount = static_cast<int>(ceil((centerOpCount + 1) / 2));
				//reseting the brackets variable
				brackets = 0;
				//loop for length of char array
				for (int i = chars.size()-1;i > 0;i--) {
					//if the char[i] is a open bracket
					if (chars[i] == '(') {
						//increment count
						brackets -- ;
					}
					//if the char[i] is a closed bracket
					else if (chars[i] == ')') {
						//decrement count
						brackets ++ ;
					}
					//if the bracket count is  0 AND the char is a + - * or /
					else if (brackets == 0 && (chars[i] == '+' || chars[i] == '-' || chars[i] == '*' || chars[i] == '/')) {
						//increment count
						opCount ++ ;
						//if opCount == centerOpCount
//						if (opCount == centerOpCount)
						{
							//set stop to i
							stop = i;
							//break for loop
							break;
						}
					}
					//the char[i] is added to left expression
					right = chars[i] + right;
				}
				//the operand is set to op
				op = fullExpression[stop];
				//right expression is the remaining string
				left = fullExpression.substr(0, stop);
				//in the case of single numbers, simply have the other value as 0, mainly for the case of negative numbers
				if (left == "") {
					left = "0";
				}
				//CHECK THIS LATER
				if (right == "") {
					cout << "Expression is not valid" << endl;
					break;
				}
				ArithmeticExpression *ex = NULL;
				//switch case based on operand
				switch (op) {
				//if op is +
				case '+': {
					//create add expression
					ex = new Addition(left, right);
					//evaluate it
					answer = ex->evaluate();
					delete ex;
					//break from switch
					break;
				//if op is -
				}
				case '-': {
					//create Subtraction expression
					ex = new Subtraction(left, right);
					//evaluate it
					answer = ex->evaluate();
					delete ex;
					//break from switch
					break;
				//if op is *
				}
				case '*': {
					//create Multiplication expression
					ex = new Multiplication(left, right);
					//evaluate it
					answer = ex->evaluate();
					delete ex;
					//break from switch
					break;
				//if op is  /
				}
				case '/': {
					//create Division expression
					ex = new Division(left, right);
					//evaluate it
					answer = ex->evaluate();
					delete ex;
					//break from switch
					break;
				}
				}
				//print the original expression
				cout << originalExpression << string(" = ");
				//print the answer to 2 decimal places
				stringstream ss;
				ss << answer;
				float f;
				ss >> f;
				cout << fixed << setprecision(2)<< f << endl;
				break;
			}
		case 2:{
				//print error
				cout << "Expression is not well formed\n";
				break;
			}
		//if expression not valid
		case 1: {
			cout << fullExpression << endl;
			break;
		}
		}
	}
}