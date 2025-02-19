/* BY SUBMITTING THIS FILE TO CARMEN, I CERTIFY THAT I HAVE 
** STRICTLY ADHERED TO THE TENURES OF THE 
** OHIO STATE UNIVERSITY’S ACADEMIC INTEGRITY POLICY. 
 */ 

/* Student Name: Saeed Alneyadi.11 */

#include <stdio.h>
#include <stdlib.h>
#include "lab4.h"

int date_diff(char *todayDate, int fileDays, int fileYears) {
    /* Variables Decleartions and Initialization */
    const int daysAfterMon[] = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365};
    int todayDays, todayYears;
    
    /* Converting char integers to days and months. */
    todayDays = daysAfterMon[(todayDate[0] - 48)*10 + (todayDate[1] - 48) - 1] + (todayDate[3] - 48)*10 + (todayDate[4] - 48);
    todayYears = (todayDate[6] - 48)*1000 + (todayDate[7] - 48)*100 + (todayDate[8] - 48)*10 + (todayDate[9] - 48);

    /* RETURN Statement */
    return ((todayYears - fileYears)*365 + (todayDays - fileDays));
}
