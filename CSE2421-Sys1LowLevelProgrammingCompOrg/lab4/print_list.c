/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE 
** STRICTLY ADHERED TO THE TENURES OF THE 
** OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY. 
 */ 

/* Student Name: Saeed Alneyadi.11 */

#include <stdio.h>
#include <stdlib.h>
#include "lab4.h"

void print_list(FILE *output_file, Node *listHeadPtr) {
    printf("%d\n", (*listHeadPtr).grocery_item.stockNumber);

    if (listHeadPtr->next != NULL) {
        listHeadPtr = listHeadPtr->next;
        print_list(output_file, listHeadPtr);
    }
}
