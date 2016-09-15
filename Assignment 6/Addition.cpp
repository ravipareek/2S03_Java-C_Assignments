/*
 * Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * MacID: ravip2, shahk24, olivepjm
 * Student_Number:  1407109, 1419350, 1430273
 * Description: Addition class, extends ArithmeticExpression
 */

using namespace std;


#include "Addition.h"

Addition::Addition(string& a,string &b) {
    left = new ArithmeticExpression(a); // Declares left as a new Expression
    right = new ArithmeticExpression(b); // Declares right as a new Expression
    oper = '+'; // Set operator as '+'
}


string Addition::evaluate() {
    float calc = (convert(left->toString()) + convert(right->toString())); //Evaluates left and right and saves it in calc
    stringstream ss; //Declares ss as a stringstream
    ss << calc; //Assigns calc to ss
    return ss.str(); //Returns ss as a string
}


// destructor
Addition::~Addition() {
	// TODO Auto-generated destructor stub
}

