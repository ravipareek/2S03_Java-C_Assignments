/*
 * Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * MacID: ravip2, shahk24, olivepjm
 * Student_Number:  1407109, 1419350, 1430273
 * Description: Header: ArithmeticExpression Class extends Expression class
 */

#ifndef ARITHMETICEXPRESSION_H_
#define ARITHMETICEXPRESSION_H_

#include "Expression.h"
#include <string>
#include <vector>
#include <iostream>

using namespace std;

class ArithmeticExpression : public Expression {
public:
	//Declares left as a new Expression pointer
	Expression* left;
	//Declares right as a new Expression pointer
	Expression* right;
	//Declares oper as a char
	char oper;
	//Declares value as a float
	float value;
	//Declares function evaluate
    string evaluate();
	//Declares function print
    void print();
	//Declares function convert
    float convert(const string s);
	// Declares function ArithmeticExpression
	ArithmeticExpression(string a, string b, char c);
	// overloading ArithmeticExpression for default case. No args
	ArithmeticExpression();
	// overloading ArithmeticExpression for case 1 arg
	ArithmeticExpression(string a);
	// ArithmeticExpression destructor
	~ArithmeticExpression();

	string vectorToString(vector<char> in);

	string addBrackets(int pos, const string e);
};



#endif /* ARITHMETICEXPRESSION_H_ */
