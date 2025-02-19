/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE 
** STRICTLY ADHERED TO THE TENURES OF THE 
** OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY. 
 */ 

/* Student Name: Saeed Alneyadi.11 */

#include <stdio.h>
#include <stdlib.h>
#include "lab4.h"

void groc_in_stk(Node *listPtr) {
    Node *currentNode = &(*listPtr);

    printf("Grocery items in Stock:\n");
    printf("Stock#\tQuantity\tDepartment\tItem\n");

    while (currentNode != NULL) {
        if (((*currentNode).grocery_item.pricing.wholesaleQuantity - (*currentNode).grocery_item.pricing.retailQuantity) > 0) {
            printf("%d\t",(*currentNode).grocery_item.stockNumber);
            printf("%d\t",(*currentNode).grocery_item.pricing.wholesaleQuantity - (*currentNode).grocery_item.pricing.retailQuantity);
            printf("%s\t",(*currentNode).grocery_item.department);
            printf("%s\n",(*currentNode).grocery_item.item);
        }
        currentNode = (*currentNode).next;
    }
}
