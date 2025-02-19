/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I STRICTLY ADHERED TO THE 
 * TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY.
 * THIS IS THE readlines.s FILE FOR LAB 7.
 * Name: Saeed Alneyadi.11
 */

#include <stdio.h>
#include <stdlib.h>

/* STRUCT Declerations */

struct Record {
    int value2;
    int value1;
    int difference;
    long sum;
    long product;
    int quotient;
    int remainder;
};

/* Functions Declerations */

void readlines(FILE *fptr, struct Record *rptr, int count);
void compute(struct Record *rptr, int count);
void printlines(struct Record *rptr, int count);

