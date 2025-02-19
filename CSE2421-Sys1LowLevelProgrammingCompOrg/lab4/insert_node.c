/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE 
** STRICTLY ADHERED TO THE TENURES OF THE 
** OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY. 
 */ 

/* Student Name: Saeed Alneyadi.11 */

#include <stdio.h>
#include <stdlib.h>
#include "lab4.h"

void insert_node(Node **listHeadPtr, Node *newNodePtr) {
    if (*listHeadPtr == NULL) {
        *listHeadPtr = newNodePtr;
        newNodePtr->next = NULL;
    } else if ((*(*listHeadPtr)).grocery_item.stockNumber <= (*newNodePtr).grocery_item.stockNumber) {
        newNodePtr->next = *listHeadPtr;
        *listHeadPtr = newNodePtr;
    } else {
        insert_node(&((**listHeadPtr).next), newNodePtr);
    }
}
