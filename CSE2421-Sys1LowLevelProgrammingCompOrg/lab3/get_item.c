/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE STRICTLY ADHERED TO THE 
TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY WITH RESPECT TO 
THIS ASSIGNMENT.
*/

/* Student name: Saeed Alneyadi.11 */

#include "lab3.h"
#include <stdio.h>
#include <stdlib.h>

void get_item(char **ingredients, int idx) {
    char *input;
    getchar();
    scanf("%s\n", input);
    *(ingredients + idx) = input;
}
