/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE 
** STRICTLY ADHERED TO THE TENURES OF THE 
** OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY. 
 */ 

/* Student Name: Saeed Alneyadi.11 */

#include <stdio.h>
#include <stdlib.h>
#include "lab4.h"

void delete_node(Node **listHeadPtr, int stkNum) {
    /* Variables Decleartions and Initialization */
    Node *traversePtr;
    traversePtr = listHeadPtr;

    if (listHeadPtr == NULL) {
        perror("ERROR: listHeadPtr is NULL.");
    } else {
        *listHeadPtr = traversePtr->next;
        free(traversePtr);
    }
}
