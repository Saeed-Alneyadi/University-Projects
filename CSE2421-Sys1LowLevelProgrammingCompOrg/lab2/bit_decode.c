#include <stdio.h>

/* Functions Decleartions */

int create_key();
char rotate_right(char num, int counter);
char rotate_left(char num, int counter);
void decode_text();

/* Main Function */

int main() {
    int input, key, counter = 1; /* Integer Variables Decleration and Initilization */
    printf("enter 4-bit key: "); /* Asking the user to enter 4-bit key. */
    key = create_key(); /* Creating 8-bit key */
    printf("enter encoded text: "); /* Asking the user to enter the encoded text. */
    decode_text(key);

   return 0;
}

/* Functions Definitions */

int create_key() {
    int key = 0, idx; /* */

    key += (getchar() - 48);
    for (idx = 0; idx < 3; idx++) {
        key = (key << 1);
        key += (getchar() - 48);
    }

    return ((key << 4) ^ key);
}

char rotate_right(char num, int counter) {
    int idx;

    for (idx = 0; idx < counter; idx++) {
        num = (num >> 1) | ((num & 0x01) << 7);
    }

    return num;
}

char rotate_left(char num, int counter) {
    int idx;

    for (idx = 0; idx < counter; idx++) {
        num = (num << 1) | ((num & 0x80) >> 7);
    }

    return num;
}

void decode_text(int key) {
    int count = 1;
    char digit1, digit2, digit3, ch;
    getchar();
    digit1 = getchar() - 48;
    digit2 = getchar() - 48;
    digit3 = getchar();
    while (digit3 != '\n') {
        if (digit1 > 16) {
            digit1 -= 7;
        }
        if (digit2 > 16) {
            digit2 -= 7;
        }
        ch = (digit1 << 4) + digit2;

        if (count % 2 == 1) {
            ch = rotate_right(ch, count);
        } else {
            ch = rotate_left(ch, count);
        }

        ch = ch ^ key;
        printf("%c", ch);

        digit1 = getchar() - 48;
        digit2 = getchar() - 48;
        digit3 = getchar();

        count++;
    }
}

