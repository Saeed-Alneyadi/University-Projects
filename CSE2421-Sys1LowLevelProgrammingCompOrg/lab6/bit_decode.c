#include <stdio.h>

unsigned char rotate_right(unsigned char value);
unsigned char rotate_left(unsigned char value);
unsigned int create_key();

int main()
{

unsigned int cur_in, key;
int count = 0;

    key = create_key();
    getchar();	/* trash '\n' */

    printf("enter encoded text: ");

    while ( (scanf("%x",&cur_in) > 0)) 	/* read in a hex value	*/
    {
	if(count %2){ 	/* if it's an odd count, go left	*/
		cur_in = (unsigned int)rotate_left(cur_in);
	}
	else { 		/* if it's an even count, go right	*/
		cur_in = (unsigned int)rotate_right(cur_in);
	}
	count++;
	cur_in = cur_in ^ key;		/* convert to a valid ASCII value	*/
 	putchar(cur_in);		/* out it goes!	*/
        cur_in = getchar();		/* grab either a space or a newline	*/
	if(cur_in == '\n') break;	/* notice there is no character limit in this implementation	*/
    }
    putchar('\n');

return(0);
}
