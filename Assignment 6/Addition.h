/*
 * Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * MacID: ravip2, shahk24, olivepjm
 * Student_Number:  1407109, 1419350, 1430273
 * Description: Header File - Addition class, extends ArithmeticExpression
 */

#ifndef ADDITION_H_
#define ADDITION_H_

#include "ArithmeticExpression.h"
#include <string>
#include <sstream>

using namespace std;

class Addition : public ArithmeticExpression {

public:
    Addition(string &a, string &b); //Declares constructor
    virtual string evaluate(); //Declares evaluate
    virtual ~Addition(); //Declares destructor
};




#endif /* ADDITION_H_ */