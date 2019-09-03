#include <omp.h>
#include "windows.h"
#include <iostream>

#define N (1024*1024*4)
using namespace std;

int main()
{
	long long sum = 0;
	long *arr = new long[N];
	for (long i=0;i<N;i++)
	{
		arr[i] = 1;
	}

	omp_set_num_threads(1024);
	
	omp_lock_t Lock;
	omp_init_lock(&Lock);

#pragma omp parallel for default(shared) schedule(dynamic,1)
	for (long i=0;i<N;i++)
	{
		omp_set_lock(&Lock);

		sum = sum + arr[i];
		//Sleep(100);
		cout << "thread id :" << omp_get_thread_num() << endl;

		omp_unset_lock(&Lock);
	}
	
	omp_destroy_lock(&Lock);
	cout << "sum = " << sum << endl;
	return 0;
}