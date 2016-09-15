/*
 * Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * MacID: ravip2, shahk24, olivepjm
 * Student_Number:  1407109, 1419350, 1430273
 * Description: Implementation for ArithmeticExpression. Breaks the expression into smaller expressions and executes
 */

// import external libraries
#include "ArithmeticExpression.h"
#include "Addition.h"
#include "Subtraction.h"
#include "Multiplication.h"
#include "Division.h"
#include <string.h>
#include <stdlib.h>
#include <sstream>
#include <iostream>
using namespace std;

// Prints the full expression to console
void ArithmeticExpression::print() {
    // left expression, oporator , right expression
    cout << left << oper << right << endl;
}
// constuctor for ArithmeticExpression 3 args
ArithmeticExpression::ArithmeticExpression(string l, string r, char op){
    // concatenates the full expression and saves to expr
    // left expression, oporator , right expression
    expr = l+op+r;
}
// constuctor for ArithmeticExpression 1 arg
ArithmeticExpression::ArithmeticExpression(string a) {
    // sets expr to a
    expr = a;
}
string ArithmeticExpression::vectorToString(vector<char> in){
    string returnValue;
    for(int i = 0; i < in.size(); i++){
        returnValue += in[i];
    }
    return returnValue;
}
// formats the string exprestion into sub bracketed parts and returns string
string format(string fullExpression) {
    //initialize counter
    int bracket = 0;
    //initializing variable
    bool end = true;
    //if first and last chars are brackets
    while (fullExpression[0] == '(' && fullExpression[fullExpression.length() - 1] == ')' && bracket==0 && end) {
        //loop for length of string
        for (int i = 0;i < fullExpression.length();i ++) {
            //if char is open bracket
            if (fullExpression[i] == '(') {
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
        if (end == true) {
            //remove first and last variables
            fullExpression = fullExpression.substr (1, fullExpression.length()-2);
        }
    }
    //return the string
    return fullExpression;
}

// evaluate returns blank. unused.
string ArithmeticExpression::evaluate() {
    return "";
}

string ArithmeticExpression::addBrackets(int pos, const string e) {
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
    return returning;
}

// parses  
float ArithmeticExpression::convert(const string str) {
    // save the result of format(str) to s
    string s = format(str);
    // set noOP to true 
    bool noOP = true;
    // for length of s
    for (int i = 0;i < s.length();i ++) {
        // if substring contains opporator chars then 
        if (s.substr(i, 1)==("+") || s.substr(i, 1)==("-") || s.substr(i, 1)==("*") || s.substr(i, 1)==("/")) {
            // set noOP to false
            noOP = false;
        }
    }
    // if noOP is true
    if (noOP) {
        // sets value to s
        value = atof(s.c_str());
    }
    // if noOP is false
    else {
        string left = ""; //Initializes left
        string right = ""; //Initializes right
        char op; //Declares op
        int stop = 0; //Declares stop
        //keep count on if open or closed bracket
        int brackets = 0;
        //number of operands not in brackets
        int centerOpCount = 0;
        //loop for length of char array
        for (int i = 0;i < s.length();i ++) {
            //if the char[i] is a open bracket
            if (s[i] == '(') {
                //increment count
                brackets ++ ;
            }
                //if the char[i] is a closed bracket
            else if (s[i] == ')') {
                //decrement count
                brackets -- ;
            }
            //if the bracket count is less than or equal to 0 AND the char is a * or /
            if (brackets <= 0 && (s[i] == '*' || s[i] == '/')) {
                //add brackets around the * or /
                string string1 = addBrackets(i, s);
                //if there is a change in the string
                if (s != string1) {
                    //make fullExpression be a string made from the char array
                    s = string1;
                    //increment the count by 2
                    i += 2;
                }
            }
        }
        //reset the count
        brackets = 0;
        //for every char in s
        for (int i = s.length()-1;i > 0;i --) {
            // if char is '('
            if (s[i] == '(') {
                //Decrement brackets by one
                brackets -- ;
            }
            // if char is ')'
            if (s[i] == ')') {
                //Increment brackets by one
                brackets ++ ;
            }
            // if char is operator
            if (brackets == 0 && (s[i] == '+' || s[i] == '-' || s[i] == '*' || s[i] == '/')) {
                //set stop to i
                stop = i;
                //break
                break;
            }
            // inserts char befor right
            right = s[i] + right;
        }
        //set op tp s[stop]
        op = s[stop];
        //set left to the substring of s from 0 to stop
        left = s.substr(0, stop);

        //if left is empty
        if (left == "") {
            //set left to "0"
            left = "0";
        }
        //if right is empty
        if (right == "") {
            //sets right to "0"
            right = "0";
            //prints error message
            cout << "Expression is not valid" << endl;
            // return 0
            return 0;
        }
        // new ArithmeticExpression ex 
        ArithmeticExpression *ex = NULL;
        // Switch Case depending on the operator
        switch (op) {
            // case Addition
            case '+': {
                // creating new oprator object
                ex = new Addition(left, right);
                // string to float of evaluate
                value = atof((ex->evaluate()).c_str());
                //deallocate ex from memory
                delete ex;
                //break
                break;
            }
            // Case Subtraction 
            case '-': {
                // creating new oprator object
                ex = new Subtraction(left, right);
                // string to float of evaluate
                value = atof((ex->evaluate()).c_str());
                //deallocate ex from memory
                delete ex;
                //break
                break;
            }
            // Case Multiplication
            case '*': {
                // creating new oprator object
                ex = new Multiplication(left, right);
                // string to float of evaluate 
                value = atof((ex->evaluate()).c_str());
                //deallocate ex from memory
                delete ex;
                //break
                break;
            }
            // Case Division
            case '/': {
                // creating new oprator object
                ex = new Division(left, right);
                // string to float of evaluate
                value = atof((ex->evaluate()).c_str());
                //deallocate ex from memory
                delete ex;
                //break
                break;
            }
        }
    }
    // return value
    return value;
}
// making the default constuctor
ArithmeticExpression::ArithmeticExpression() {

}

// making default destructor
ArithmeticExpression::~ArithmeticExpression() {
    delete left; //deallocates left
    delete right; //deallocates right
}