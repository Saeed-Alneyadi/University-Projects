/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE 
** STRICTLY ADHERED TO THE TENURES OF THE 
** OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY. 
 */ 

/* Student Name: Saeed Alneyadi.11 */

#include <stdio.h>
#include <stdlib.h>
#include "lab4.h"

void avg_profit(Node *listPtr) {
    Node *currentNode = &(*listPtr);
    float output = 0, totRev = 0, totWSCost = 0, totWSSale = 0, totWSQuan = 0;

    while (currentNode != NULL) {
        totRev += (*currentNode).grocery_item.pricing.retailPrice * (float)((*currentNode).grocery_item.pricing.retailQuantity);
        totWSCost += (*currentNode).grocery_item.pricing.wholesalePrice * (float)((*currentNode).grocery_item.pricing.wholesaleQuantity);
        totWSSale += (*currentNode).grocery_item.pricing.wholesalePrice * ((float)((*currentNode).grocery_item.pricing.wholesaleQuantity) - (float)((*currentNode).grocery_item.pricing.retailQuantity));
        totWSQuan += ((float)(*currentNode).grocery_item.pricing.retailQuantity);
        currentNode = (*currentNode).next;
    }

    output = (totRev - (totWSCost - totWSSale))/(totWSQuan);

    printf("Average Profit: $%.2f\n", output);
}
