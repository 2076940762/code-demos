#include <iostream>
#include <omp.h>
using namespace std;

int main()
{
	int i, max =-1 , min = 1000,n=1000;
	int ar[1000];

	for (i=0;i<1000;i++)
	{
		ar[i] = i + 1;
	}
	
#pragma omp parallel for
	for (i = 0; i < n; i++)
	{
#pragma omp critical (max)
		if (ar[i] > max)
			max = ar[i];

#pragma omp critical (min)
		if (ar[i] < min)
			min = ar[i];
	}

	cout << "min:" << min << "\t max:" << max << endl;
}

