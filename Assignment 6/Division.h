/*
 * Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * MacID: ravip2, shahk24, olivepjm
 * Student_Number:  1407109, 1419350, 1430273
 * Description: Header File - Division class, extends ArithmeticExpression
 */

#ifndef DIVISION_H_
#define DIVISION_H_

#include "ArithmeticExpression.h"
#include <string>
#include <sstream>

using namespace std;

class Division : public ArithmeticExpression {
    
public:
    //Declares constructor
    Division(string& a,string& b);
    //Declares evaluate
    virtual string evaluate();
    //Declares destructor
    virtual ~Division();
};


#endif /* DIVISION_H_ */
