/*
schedule子句描述如何将循环的迭代划分给线程组中的线程
如果没有指定chunk大小，迭代会尽可能的平均分配给每个线程
type为static，循环被分成大小为 chunk的块，静态分配给线程
type为dynamic,循环被动态划分为大小为chunk的块，动态分配给线程
ordered：用来指定for循环的执行要按顺序执行
private：指导编译器将一个或多个变量私有化
firstprivate:并行区域内的私有变量继承外面共享变量的值作为初始值，并且在退出并行区域后，共享变量的值保持不变。
lastprivate:在退出并行区域时将私有变量的值赋给共享变量
shared：声明一个或多个变量是共享变量
reduction：对一个或多个参数条目指定一个操作符，每个线程将创建参数条目的一个私有拷贝，在区域的结束处，将用私有拷贝的值通过指定的运行符运算，原始的参数条目被运算结果的值更新。
nowait：不在并行for循环出口处放置同步障碍
for指令则是用来将一个for循环分配到多个线程中执行。for指令一般可以和parallel指令合起来形成parallel for指令使用，也可以单独用在parallel语句的并行块中。
#pragma omp [parallel] for [子句]
      for循环语句
*/
#include <omp.h>
#include <iostream>

using namespace std;

int main()
{
//#pragma  omp parallel for schedule(dynamic,5)
//#pragma  omp parallel for schedule(static,5)
#pragma  omp parallel for schedule(guided,5)
	for (int i =0 ;i<100;i++)
	{
		printf("thead ID: %d \n", omp_get_thread_num());
	}

#pragma omp barrier

	int a[10];
	int b[] = { 1,2,3,4,5,6,7,8,9,0 };
	int c[] = { 0,9,8,7,6,5,4,3,2,1 };

	long A, B, C;
	A = 0, B = 0,C = 0;

#pragma omp parallel for  default(shared)
	for (int i = 0;i<10;i++)
	{
		a[i] = b[i] + c[i];

		A += a[i];
		B += b[i];
		C += c[i];
	}
#pragma omp master
	{
		cout << "\n shared(a,b,c):" << endl;
		cout << "b: ";
		for (int i : b)
		{
			cout << i << "\t";
		}
		cout << endl;

		cout << "c: ";
		for (int i : c)
		{
			cout << i << "\t";
		}
		cout << endl;

		cout << "a: ";
		for (int i : a)
		{
			cout << i << "\t";
		}
		cout << endl;
		
		cout << "B=" << B << endl;
		cout << "C=" << C << endl;
		cout << "A=" << A << endl;
		A = 0;
	}
//  b: 1    2       3       4       5       6       7       8       9       0
// 	c : 0    9       8       7       6       5       4       3       2       1
// 	a : 1    11      11      11      11      11      11      11      11      1

#pragma  omp barrier
	A = 0, B = 0, C = 0;
	
#pragma omp parallel for firstprivate(a,b,c,A,B,C)
	for (int i = 0; i < 10; i++)
	{
		a[i] = b[i] + c[i];

		A += a[i];
		B += b[i];
		C += c[i];
	}

#pragma omp master
	{
		cout << "\n private(a,b,c):" << endl;
		cout << "b: ";
		for (int i:b)
		{
			cout << i << "\t";
		}
		cout << endl;

		cout << "c: ";
		for (int i:c)
		{
			cout << i << "\t";
		}
		cout << endl;

		cout << "a: ";
		for (int i : a)
		{
			cout << i << "\t";
		}
		cout << endl;	

		cout << "B=" << B << endl;
		cout << "C=" << C << endl;
		cout << "A=" << A << endl;
	}



}