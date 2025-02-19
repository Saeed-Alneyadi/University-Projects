/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE 
** STRICTLY ADHERED TO THE TENURES OF THE 
** OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY. 
 */ 

/* Student Name: Saeed Alneyadi.11 */

#include <stdio.h>
#include <stdlib.h>
#include "lab4.h"

int main(int argc, char *argv[]) {
    /* Variables Decleartions and Initialization */
    int fileDays, fileYears, groceryCount = 0, input, output;
    char *todayDate = argv[1];
    void (*fp_array[11])(Node *) = {tot_rev, tot_WS_cost, tot_WS_sale, tot_profit, tot_groc_sold, avg_profit, groc_in_stk, out_of_stk_groc, dep_groc_list, add_groc, remove_groc};
    FILE *input_file = fopen(argv[2], "r"), *output_file;
    Node *listHeadPtr;

    /* Reading date (days and years) from the file. */
    fscanf(input_file, "%d\t%d\n", &fileDays, &fileYears);

    /* Printing days difference between today and the date in the file. */
    printf("There are %d days ", date_diff(todayDate, fileDays, fileYears));
    printf("difference between the date entered and the date in the file. ");

    /* Asking the user whether he wants to continue the run of the program or not. */
    printf("Do you wish to continue? ");
    scanf("%c", &input);
    getchar();

    /* Building the groceries list. */
    if (input == 'y') {
       listHeadPtr = build_list(input_file, &groceryCount);
    }

    /* Closing input_file file. */
    fclose(input_file);

    if (input == 'y') {
       /* Printing a progress statement and the number of groceries. */
        printf("\nReading inventory from file \"%s\".", argv[2]);
        printf("\nA total of %d grocery items were read into inventory from the file \"%s\".\n", groceryCount, argv[2]);

        output_file = fopen(argv[3], "w");

        input = 12;

        do {
            if (input != 12) {
                fp_array[input - 1](listHeadPtr);
            }

            printf("\nPlease enter an integer between 1 and 12:");
            printf("\n1) Print Total Revenue");
            printf("\n2) Print Total Wholesale Cost");
            printf("\n3) Print Current Grocery Item Investment");
            printf("\n4) Print Current Profit");
            printf("\n5) Print Total Number of Grocery Items Sold");
            printf("\n6) Print Average Profit per Grocery Item");
            printf("\n7) Print Grocery Items In Stock");
            printf("\n8) Print Out of Stock Grocery Items");
            printf("\n9) Print Grocery Items for a Department");
            printf("\n10) Add Grocery Item to Inventory");
            printf("\n11) Delete Grocery Item from Inventory");
            printf("\n12) Exit Program\n");

            printf("\nOption: ");
            scanf("%d", &input);

        } while (input != 12);

        /* print_list(output_file, listHeadPtr); */

        fclose(output_file);
    }

    /* RETURN Statement */
    return 0;
}
