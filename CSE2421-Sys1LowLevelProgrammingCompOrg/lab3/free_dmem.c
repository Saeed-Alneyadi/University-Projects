/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE STRICTLY ADHERED TO THE 
TENURES OF THE OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY WITH RESPECT TO 
THIS ASSIGNMENT.
*/

#include "lab3.h"
#include <stdio.h>
#include <stdlib.h>

void free_dmem(char **ingredients, char ***thispizza) {
    free(ingredients);
    free(thispizza);
}
