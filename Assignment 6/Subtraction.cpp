/*
 * Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * MacID: ravip2, shahk24, olivepjm
 * Student_Number:  1407109, 1419350, 1430273
 * Description: Header File - Subtraction class, extends ArithmeticExpression
 */

#include "Subtraction.h"

using namespace std;


Subtraction::Subtraction(string& a,string& b) {
    // Declares left as a new Expression
    left = new ArithmeticExpression(a);
    // Declares right as a new Expression
    right = new ArithmeticExpression(b);
    // Set operator as '-'
    oper = '-';
}

string Subtraction::evaluate() {
    // Evaluates left and right and saves it in calc
    float calc = (convert(left->toString()) - convert(right->toString()));
    // Declares ss as a stringstream
    stringstream ss;
    // Assigns calc to ss
    ss << calc;
    // Returns ss as a string
    return ss.str();
}

Subtraction::~Subtraction() {
// TODO Auto-generated destructor stub
}