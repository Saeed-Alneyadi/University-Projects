/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE STRICTLY ADHERED TO THE 
TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY WITH RESPECT TO 
THIS ASSIGNMENT.
*/

/* Student name: Saeed Alneyadi.11 */

#include "lab3.h"
#include <stdio.h>
#include <stdlib.h>

int get_thispizza(char **ingredients, char ***thispizza) {
    int pizzaIngCount, idx, input; /* Integer Variables Decleration and Initilization */

    printf("Of our 10 available ingredients, how many do you plan to put on your pizza? "); /* Asking the user to enter the number of fresh ingrediants s/he want in his/her pizza. */
    scanf("%d\n", pizzaIngCount);

    thispizza = (char ***)malloc(pizzaIngCount * sizeof(char **));

    printf("Enter the number next to each ingredient you want on your pizza: ");
    for (idx = 0; idx < pizzaIngCount; idx++) {
        scanf("%d ", &input);
        **(thispizza + idx) = ingredients[input + 1];
    }

    scanf("\nThe ingredients on your pizza will be:\n");

    for (idx = 0; idx < pizzaIngCount; idx++) {
        printf("%d. %s\n", idx + 1, **(thispizza + idx)); 
    }

    return pizzaIngCount;
}
