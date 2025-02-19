/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE 
** STRICTLY ADHERED TO THE TENURES OF THE 
** OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY. 
 */ 

/* Student Name: Saeed Alneyadi.11 */

#include <stdio.h>
#include <stdlib.h>
#include "lab4.h"

void tot_groc_sold(Node *listPtr) {
    Node *currentNode = &(*listPtr);
    int output = 0;

    while (currentNode != NULL) {
        output += (*currentNode).grocery_item.pricing.retailQuantity;
        currentNode = (*currentNode).next;
    }

    printf("Total number of grocery items sold: %d\n", output);
}
