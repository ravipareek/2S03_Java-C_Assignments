/*
 * Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * MacID: ravip2, shahk24, olivepjm
 * Student_Number:  1407109, 1419350, 1430273
 * Description: Header: Expression Class
 */

#ifndef EXPRESSION_H_
#define EXPRESSION_H_

#include <string>
using namespace std;

class Expression {
public:
	// Declares left as a string
	string expr;
	// Declares virtual function evaluate as a string with default value of 0
	virtual string evaluate() = 0;
	// Declares virtual function print as a string with default value of 0
	virtual void print() = 0;
	// Declares virtual function toString as a string with default value of 0
	virtual string toString();
};



#endif	/* ifndef EXPRESSION_h_ */
