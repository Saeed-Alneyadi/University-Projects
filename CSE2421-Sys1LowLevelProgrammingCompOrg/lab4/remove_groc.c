/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE 
** STRICTLY ADHERED TO THE TENURES OF THE 
** OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY. 
 */ 

/* Student Name: Saeed Alneyadi.11 */

#include <stdio.h>
#include <stdlib.h>
#include "lab4.h"

void remove_groc(Node *listPtr) {
    Node *currentNode = &(*listPtr);
    int stkNum;

    printf("Please enter the grocery item stock number you wish to delete, followed by enter. ");
    scanf("%d", stkNum);

    while (currentNode != NULL) {
        if ((*currentNode).grocery_item.stockNumber == stkNum) {
            delete_node(&listPtr, stkNum);
        }
    }

    printf("Grocery item stock number %d was deleted.", stkNum);
}
