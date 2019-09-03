#include <iostream>
#include "omp.h"

using namespace std;

int main()
{
	omp_set_num_threads(5);

#pragma omp parallel sections 
	{
#pragma omp section
		printf("section 1 ThreadId = %d\n", omp_get_thread_num());

#pragma omp section 
		printf("section 2 ThreadId = %d\n", omp_get_thread_num());

#pragma omp section
		printf("section 3 ThreadId = %d\n", omp_get_thread_num());

#pragma omp section 
		printf("section 4 ThreadId = %d\n", omp_get_thread_num());
	}
}