/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE 
** STRICTLY ADHERED TO THE TENURES OF THE 
** OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY. 
 */ 

/* Student Name: Saeed Alneyadi.11 */

#include <stdio.h>
#include <stdlib.h>
#include "lab4.h"

Node *build_list(FILE *input_file, int *groceryCount) {
    Node *listHeadPtr = NULL, *newNodePtr = NULL;
    int input = 1, idx;
    char line[256];

    newNodePtr = (Node *)malloc(sizeof(Node));
    input = fscanf(input_file, "%[^\t]", &((*newNodePtr).grocery_item.item));

    while (input > 0) {
        fscanf(input_file, " %[^\t]", &((*newNodePtr).grocery_item.department));
        fscanf(input_file, " %d", &((*newNodePtr).grocery_item.stockNumber));
        fscanf(input_file, " %f", &((*newNodePtr).grocery_item.pricing.retailPrice));
        fscanf(input_file, " %f", &((*newNodePtr).grocery_item.pricing.wholesalePrice));
        fscanf(input_file, " %d", &((*newNodePtr).grocery_item.pricing.retailQuantity));
        fscanf(input_file, " %d\n", &((*newNodePtr).grocery_item.pricing.wholesaleQuantity));
        insert_node(&listHeadPtr, newNodePtr);
        (*groceryCount)++;
        newNodePtr = (Node *)malloc(sizeof(Node));
        input = fscanf(input_file, "%[^\t]", &((*newNodePtr).grocery_item.item));
    }

    /* RETURN Statement */
    return listHeadPtr;
}
