/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE 
** STRICTLY ADHERED TO THE TENURES OF THE 
** OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY. 
 */ 

/* Student Name: Saeed Alneyadi.11 */

#include <stdio.h>
#include <stdlib.h>
#include "lab4.h"

void dep_groc_list(Node *listPtr) {
    Node *currentNode = &(*listPtr);
    char inDep[30];
    int subStr, idx, i;

    printf("Enter Department Name to print: ");
    scanf(" %[^\n]", inDep);
    printf("Grocery Item List for %s: \n", inDep);
    printf("Stock#\tQuantity\tDepartment\tItem\n");

    while (currentNode != NULL) {
        subStr = 0;

        for (idx = 0; idx < 30; idx++) {
            if ((inDep[0] == ((*currentNode).grocery_item.department)[idx]) && (inDep[0] != '\0') && (inDep[0] != ' ')) {
                subStr = 1;
                for (i = 0; inDep[i] != '\0'; i++) {
                    if (inDep[i] != ((*currentNode).grocery_item.department)[idx + i]) {
                        subStr = 0;
                        break;
                    }
                }
            }

            if (subStr) {
                break;
            }
        }

        if (subStr) {
            printf("%d\t",(*currentNode).grocery_item.stockNumber);
            printf("%d\t",(*currentNode).grocery_item.pricing.wholesaleQuantity - (*currentNode).grocery_item.pricing.retailQuantity);
            printf("%s\t",(*currentNode).grocery_item.department);
            printf("%s\n",(*currentNode).grocery_item.item);
        }
        currentNode = (*currentNode).next;
    }
}
