#include <iostream>
#include "omp.h"
#include "stdio.h"

int counter = 0;
#pragma omp threadprivate(counter)

int increment_counter()
{
	counter++;
	return(counter);
}

int main(int argc, char* argv[])
{
	int iterator;
	 counter = 10;
	printf("counter = %d\n", counter);

#pragma omp parallel sections  copyin(counter)  private(iterator)
	{
#pragma omp section
		{
			int count1;
			for (iterator = 0; iterator < 2; iterator++)
			{
				count1 = increment_counter();
				printf("count1 = %d, thread_num = %d\n", count1, omp_get_thread_num());
			}
		}
#pragma omp section
		{
			int count2;
			for (iterator = 0; iterator < 4; iterator++)
			{
				count2 = increment_counter();
				printf("count2 = %d, thread_num = %d\n", count2, omp_get_thread_num());
			}
		}
	}
	printf("counter = %d, I  am thread %d\n", counter,omp_get_thread_num());
}
