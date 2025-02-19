/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE 
** STRICTLY ADHERED TO THE TENURES OF THE 
** OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY. 
 */ 

/* Student Name: Saeed Alneyadi.11 */

#include <stdio.h>
#include <stdlib.h>
#include "lab4.h"

void tot_WS_cost(Node *listPtr) {
    Node *currentNode = &(*listPtr);
    float output = 0;

    while (currentNode != NULL) {
        output += (*currentNode).grocery_item.pricing.wholesalePrice * (float)((*currentNode).grocery_item.pricing.wholesaleQuantity);
        currentNode = (*currentNode).next;
    }

    printf("Total Wholesale cost: $%.2f\n", output);
}
