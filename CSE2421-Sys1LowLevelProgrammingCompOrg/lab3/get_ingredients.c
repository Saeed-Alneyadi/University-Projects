/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE STRICTLY ADHERED TO THE 
TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY WITH RESPECT TO 
THIS ASSIGNMENT.
*/

/* Student name: Saeed Alneyadi.11 */

#include "lab3.h"
#include <stdio.h>
#include <stdlib.h>

int get_ingredients(char **ingredients) {
    int idx, ingCount; /* Integer Variables Decleration and Initilization */

    printf("How many available pizza ingredients do we have today? "); /* Asking the user to enter number fresh ingreidents he plan to enter. */
    scanf("%d", &ingCount); /* Storing the input to ingredCount. */

    ingredients = (char **)malloc(ingCount * sizeof(char *));

    printf("Enter the %d ingredients one to a line:\n", ingCount); /* Asking the user to enter each fresh ingredient on a separate line. */
    for (idx = 0; idx < ingCount; idx++) {
        get_item(ingredients, idx);
    }
    /* Storing user input to input dynamic array. */

    printf("Available ingredients today are:\n");
    for (idx = 0; idx < ingCount; idx++) {
        printf("%d. %s\n", idx + 1, ingredients[idx]); 
    }
    /* Printing all the inputted ingreidents. */

    return ingCount;
}
