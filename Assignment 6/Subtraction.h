/*
 * Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * MacID: ravip2, shahk24, olivepjm
 * Student_Number:  1407109, 1419350, 1430273
 * Description: Header File - Subtraction class, extends ArithmeticExpression
 */

#ifndef SUBTRACTION_H_
#define SUBTRACTION_H_

using namespace std;

#include "ArithmeticExpression.h"
#include <string>
#include <sstream>
class Subtraction : public ArithmeticExpression {

public:
    // Declares constructor
    Subtraction(string& a,string& b);
    // Declares evaluate
    virtual string evaluate();
    // Declares destructor
    virtual ~Subtraction();
};



#endif /* SUBTRACTION_H_ */
