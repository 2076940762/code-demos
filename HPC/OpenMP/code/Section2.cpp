#include "omp.h"
#include <iostream>

void main(int argc, char *argv)
{
	//sections,1执行完才执行sections2
#pragma omp parallel
	{
#pragma omp sections
		{
#pragma omp section
			printf("section 1 ThreadId = %d\n", omp_get_thread_num());
#pragma omp section 
			printf("section 2 ThreadId = %d\n", omp_get_thread_num());
		}

#pragma omp sections
		{
#pragma omp section
			printf("section 3 ThreadId = %d\n", omp_get_thread_num());
#pragma omp section 
			printf("section 4 ThreadId = %d\n", omp_get_thread_num());
		}
	}
}
