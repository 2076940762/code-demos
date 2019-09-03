#include <omp.h>
#include <iostream>

using namespace std;

int main()
{
	int count = 0;
	
#pragma omp parallel for
	for (int i=0;i<10000;i++)
	{
#pragma omp atomic
		count++;
	}

	cout << count << endl;
}