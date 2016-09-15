/*
 * Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * MacID: ravip2, shahk24, olivepjm
 * Student_Number:  1407109, 1419350, 1430273
 * Description: Header File - Multiplication class, extends ArithmeticExpression
 */

#ifndef MULTIPLICATION_H_
#define MULTIPLICATION_H_

#include "ArithmeticExpression.h"
#include <string>
#include <sstream>
using namespace std;

class Multiplication : public ArithmeticExpression {
    
public:
    //Declares constructor
    Multiplication(string& a, string& b);
    //Declares evaluate
    virtual string evaluate();
    //Declares destructor
    virtual ~Multiplication();
};

#endif /* MULTIPLICATION_H_ */
