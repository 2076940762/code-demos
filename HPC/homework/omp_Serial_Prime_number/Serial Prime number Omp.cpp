/*
(2) 一个素数是一个只能被正整数1和它本身整除的正整数。最小的5个素数是2、3、5、7、11。
有时两个连续的奇数都是素数。例如，在3、5、11后面的奇整数都是素数。
但是7后面的奇整数都不是素数。编写一个并行程序判断，对所有小于1000 000的整数，
计算连续奇整数都是素数的情况的出现次数。
*/

#include <iostream>
#include <windows.h>
#include <time.h>
#include <omp.h>

#define  N     (1000000)

using namespace std;

template<typename T>
bool IsPrimeNum(T num) 
{
	bool flag = true;
	T K = sqrt(num) + 1;
#pragma omp parallel for reduction(&&:flag) schedule(guided,100) num_threads(8)
	for (T i=2;i<K;i++)
	{
		if (num%i == 0)
		{
			flag = false;
		}
	}
	return flag;
}

int main(int argc,char * argv[])
{
	const int stride = 64;

	const long long LEN = (N + 1) / 2;
	bool *Flags = new bool[LEN]; //存放1-N的奇数是不是素数
	if (Flags == NULL)
	{
		cout << "Flags is NULL" << endl;
		return -1;
	}
	omp_set_num_threads(10);
#pragma  omp parallel for  schedule(guided,stride)
	for (long long i=0;i<LEN;i++)
	{
		Flags[i] = false;     //全部初始化为false
	}

	//填数组falgs
#pragma omp parallel for schedule(guided,stride)
	for (long long i = 3; i <= N; i += 2)
	{
		if (IsPrimeNum(i))
		{
			Flags[i / 2-1] = true;
		}
	}

	long long Counter = 0;
	//遍历flags数组
#pragma omp parallel for reduction(+:Counter) schedule(guided,stride)
	for (long long i = 1;i < LEN;i++)
	{
		if (Flags[i]&&Flags[i-1])
		{
			Counter++;
		}
	}
	
	cout << "Counter:"<<Counter << endl;
	cout << "clock():"<<clock() << endl; //#define CLOCKS_PER_SEC  ((clock_t)1000) 毫秒
}
