/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE STRICTLY ADHERED TO THE 
TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY WITH RESPECT TO 
THIS ASSIGNMENT.
*/

/* Student name: Saeed Alneyadi.11 */

#include "lab3.h"
#include <stdio.h>
#include <stdlib.h>

void save_info(char **ingredients, int ingCount, char ***thispizza, int pizzaIngCount) {
    int input, idx;
    char *fileName;
    FILE *output_file;

    printf("Do you want to save them? (1=yes, 2=no): ");
    scanf("%d", input);

    if (input == 1) {
        output_file = fopen(fileName, "w");

        if (output_file == NULL) {
            perror(fileName);
            exit(EXIT_FAILURE);
        }

        fprintf(output_file, "Available ingredients today are:\n");
        for (idx = 0; idx < ingCount; idx++) {
            fprintf(output_file, "%d. %s\n", idx, ingredients[idx]); 
        }
        /* Printing all the inputted ingreidents. */

        fprintf(output_file, "\nIngredients on This Pizza are:\n");
        for (idx = 0; idx < pizzaIngCount; idx++) {
            fprintf(output_file, "%d. %s\n", idx + 1, **(thispizza + idx)); 
        }

        fclose(output_file);

        printf("Today's available ingredients and what was ordered for this pizza have been saved to the file mypizza");
    }
}