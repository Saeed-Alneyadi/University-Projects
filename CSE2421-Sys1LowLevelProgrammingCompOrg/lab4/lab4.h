/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE 
** STRICTLY ADHERED TO THE TENURES OF THE 
** OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY. 
 */ 

/* Student Name: Saeed Alneyadi.11 */

/* STRUCT declerations */

#include <stdio.h>
#include <stdlib.h>

struct Cost { 
    float wholesalePrice; 
    float retailPrice; 
    int wholesaleQuantity; 
    int retailQuantity; 
}; 

struct Data { 
    char item[50]; 
    char department[30]; 
    int stockNumber; 
    struct Cost pricing; 
};

typedef struct Node { 
    struct Data grocery_item; 
    struct Node *next; 
} Node;

/* Function Declerations */

void insert_node(Node **listHeadPtr, Node *nextNodePtr);
void delete_node(Node **listHeadPtr, int stkNum);
int date_diff(char *todayDate, int fileDays, int fileYears);
Node *build_list(FILE *input_file, int *groceryCount);
void print_list(FILE *output_file, Node *listHeadPtr);

/* Function Declerations of Function Array */

void tot_rev(Node *listPtr);
void tot_WS_cost(Node *listPtr);
void tot_WS_sale(Node *listPtr);
void tot_profit( Node *listPtr);
void tot_groc_sold(Node *listPtr);
void avg_profit(Node *listPtr);
void groc_in_stk(Node *listPtr);
void out_of_stk_groc(Node *listPtr);
void dep_groc_list(Node *listPtr);
void add_groc(Node *listPtr);
void remove_groc(Node *listPtr);
