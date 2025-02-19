/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE STRICTLY ADHERED TO THE 
TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY WITH RESPECT TO 
THIS ASSIGNMENT.
*/

/* Student name: Saeed Alneyadi.11 */

#include <stdio.h>
#include <stdlib.h>
#include "lab3.h"

/* Main Function */

int main() {
    int ingCount, pizzaIngCount, idx; /* Integer Variables Decleration and Initilization */
    char **ingredients; /* this is the pointer for the start of the ingredients[] array */ 
    char ***thispizza; /* this is the pointer for the start of this pizza order array */

    ingCount = get_ingredients(ingredients);

    printf("Welcome to our Pizza ordering system!\n");
    printf("Today we have the following fresh ingredients available:\n");
    for (idx = 0; idx < ingCount; idx++) {
        printf("%d. %s\n", idx, ingredients[idx]); 
    }
    /* Printing all the fresh ingreidents. */

    pizzaIngCount = get_thispizza(ingredients, thispizza);

    save_info(ingredients, ingCount, thispizza, pizzaIngCount);

    free_dmem(ingredients, thispizza);

    return 0;
}