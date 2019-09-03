#include <omp.h>
#include <iostream>

using namespace std;

int main()
{
#pragma omp parallel num_threads(10)
	{
		
#pragma omp master 
		{
			cout << "threads num: " << omp_get_num_threads() << endl;
			cout << "processor num: " << omp_get_num_procs() << endl;
		}

//Í¬²½ÕÏ
#pragma omp barrier

#pragma omp critical(cout)
		cout << "thread ID: " << omp_get_thread_num() << "  hello openmp" << endl;		
		
		
	}
	system("pause");
}
