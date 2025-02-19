/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE 
** STRICTLY ADHERED TO THE TENURES OF THE 
** OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY. 
 */ 

/* Student Name: Saeed Alneyadi.11 */

#include <stdio.h>
#include <stdlib.h>
#include "lab4.h"

void out_of_stk_groc(Node *listPtr) {
    Node *currentNode = &(*listPtr);

    printf("Grocery items out of Stock: \n");
    printf("Stock#\tDepartment\tItem\n");

    while (currentNode != NULL) {
        if (((*currentNode).grocery_item.pricing.wholesaleQuantity - (*currentNode).grocery_item.pricing.retailQuantity) == 0) {
            printf("%d\t",(*currentNode).grocery_item.stockNumber);
            printf("%s\t",(*currentNode).grocery_item.department);
            printf("%s\n",(*currentNode).grocery_item.item);
        }
        currentNode = (*currentNode).next;
    }
}
