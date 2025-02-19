/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE 
** STRICTLY ADHERED TO THE TENURES OF THE 
** OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY. 
 */ 

/* Student Name: Saeed Alneyadi.11 */

#include <stdio.h>
#include <stdlib.h>
#include "lab4.h"

void add_groc(Node *listPtr) {
    Node *newNode;
    newNode = (Node *)malloc(sizeof(Node));
    
    printf("Enter grocery item name: ");
    scanf(" %[^\n]", &((*newNode).grocery_item.item));

    printf("Enter Department: ");
    scanf(" %[^\n]", &((*newNode).grocery_item.department));

    printf("Enter item stock number: ");
    scanf(" %d", &((*newNode).grocery_item.stockNumber));

    printf("Enter item retail price: ");
    scanf(" %f", &((*newNode).grocery_item.pricing.retailPrice));

    printf("Enter item Wholesale price: ");
    scanf(" %f", &((*newNode).grocery_item.pricing.wholesalePrice));

    printf("Enter item retail quantity: ");
    scanf(" %f", &((*newNode).grocery_item.pricing.retailQuantity));

    printf("Enter item Wholesale quantity: ");
    scanf(" %f", &((*newNode).grocery_item.pricing.wholesaleQuantity));

    insert_node(&listPtr, newNode);
}
