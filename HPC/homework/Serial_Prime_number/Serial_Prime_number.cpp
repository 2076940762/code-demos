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
using namespace std;

#define  N     (1000000)

template<typename T>
bool IsPrimeNum(T num) 
{
	bool flag = true;
	T K = sqrt(num) + 1;
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
	long long Counter = 0;
	bool flag = false; //标记上一个奇数是不是素数
	for (long long i=3;i<N;i+=2)
	{
		if (IsPrimeNum(i))
		{
			if (flag)
			{
				Counter++;
			}
			else
			{
				flag = true;
			}
		}
		else
		{
			flag = false;
		}
	}

	cout << "Counter"<<Counter << endl;
	cout << "clock():"<<clock() << endl;

}
