/* Instructor Name: Dr. Jaimie Kelley */
/* Student Name: Saeed Alneyadi.11 */
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

#define ARRAY_SIZE      1000000
#define THREAD_NO       10
#define PER_THREAD_SIZE (ARRAY_SIZE / THREAD_NO)

/* Variable Decleartion */
int sum = 0;
pthread_mutex_t mutex;  // mutex to work with sum variable

void *sum_thread(void *arg) 
{
    /* Variables and Arrays Decleartions */
    int j;
    int *num = (int *)arg;

    /* Calculating sum */
	for(j = 0; j < PER_THREAD_SIZE; j++) {
		pthread_mutex_lock(&mutex);
        sum += *num;
		pthread_mutex_unlock(&mutex);
        num++;
	}

    /* Return Statement */
    return 0;
}

int main()
{
    /* Variables and Arrays Decleartions */
    int i, j;
    int num[THREAD_NO][PER_THREAD_SIZE];
    srand(100);

    for(i = 0; i< THREAD_NO; i++){
	    for(j=0;j < PER_THREAD_SIZE;j++) {
            num[i][j] = rand() % 100;
	    }
    }

	if (pthread_mutex_init(&mutex, 0)) {
		fprintf(stderr, "Mutex init error\n");
        return -1;
	}

    /* Start Threads */
    pthread_t p[THREAD_NO];
    for(i = 0; i< THREAD_NO; i++){
        pthread_create(&p[i], NULL, sum_thread, &num[i][0]);
    }

    /* Wait Threads */
    for(i = 0; i< THREAD_NO; i++){
	    pthread_join(p[i], NULL);
    }
	pthread_mutex_destroy(&mutex); 

    /* Printing sum */
    printf("sum = %d\n", sum);

    /* Return Statement */
    return 0;
}

