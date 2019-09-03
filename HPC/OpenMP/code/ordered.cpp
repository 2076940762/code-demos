#include <omp.h>
#include <iostream>
#include <iostream>

using namespace std;

void work(int k)
{
#pragma omp ordered
	printf("thread id = %d, k = %d\n", omp_get_thread_num(), k);

#pragma omp ordered
	printf("%d\n", k);
}

void ordered_func(int lb, int ub, int stride)
{
	int i;
#pragma omp parallel for ordered schedule (dynamic)
	for (i = lb; i < ub; i += stride)
		work(i);
}

int main()
{
	ordered_func(0, 50, 5);
	return 0;
}

/*
thread id = 0, k = 0
0
thread id = 0, k = 5
5
thread id = 0, k = 10
10
thread id = 0, k = 15
15
thread id = 0, k = 20
20
thread id = 0, k = 25
25
thread id = 0, k = 30
30
thread id = 0, k = 35
35
thread id = 0, k = 40
40
thread id = 0, k = 45
45
*/