/*
 * Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * MacID: ravip2, shahk24, olivepjm
 * Student_Number:  1407109, 1419350, 1430273
 * Description: Multiplication class, extends ArithmeticExpression
 */

#include "Multiplication.h"

using namespace std;


Multiplication::Multiplication(string& a, string& b) {
    //Declares left as a new Expression
    left = new ArithmeticExpression(a);
    //Declares right as a new Expression
    right = new ArithmeticExpression(b);
    //Set operator as '+'
    oper = '*';
}

string Multiplication::evaluate() {
    //Evaluates left and right and saves it in calc
    float calc = (convert(left->toString()) * convert(right->toString()));
    //Declares ss as a stringstream
    stringstream ss;
    //Assigns calc to ss
    ss << calc;
    //Returns ss as a string
    return ss.str();
}

// destructor
Multiplication::~Multiplication() {
    // TODO Auto-generated destructor stub
}