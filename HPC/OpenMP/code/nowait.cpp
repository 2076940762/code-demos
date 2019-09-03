#include <omp.h>
#include <stdio.h>

int main()
{
	int size = 100;
	int x[100], z[100] = { 2 }, y[100] = { 0 };

#pragma  omp  parallel num_threads(5)
	{
#pragma omp  for nowait
		for (int i = 1; i < size; ++i)
		{
			x[i] = (y[i] + z[i]) / 2;
			printf("thread %d \n", omp_get_thread_num());
		}//此处不再隐式同步	

		printf("thread %d finished\n", omp_get_thread_num());
	}
}
