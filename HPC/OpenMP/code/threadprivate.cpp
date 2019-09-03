/*
threadprivate和private的区别在于threadprivate声明的变量通常是全局范围内有效的，而private声明的变量只在它所属的并行构造中有效。
threadprivate的对应变量只能用于copyin，copyprivate，schedule，num_threads和if子句中，不能用于任何其他子句中。
用作threadprivate的变量的地址不能是常数。
对于C++的类（class）类型变量，用作threadprivate的参数时有些限制，当定义时带有外部初始化时，必须具有明确的拷贝构造函数。

*/
#include "omp.h"
#include <stdio.h>

int counter = 0;
#pragma omp threadprivate(counter)

int increment_counter() 
{
	counter++;
	return(counter);
}

int main()
{
#pragma omp parallel
	{
		int    count;
#pragma omp single copyprivate(counter)
		{   counter = 50;   }

		count = increment_counter();
		printf("ThreadId: %ld, count = %ld\n", omp_get_thread_num(), count);

	}
}
/*
copyprivate子句提供了一种机制用一个私有变量将一个值从一个线程广播到执行同一并行区域的其他线程。
用法如下：
copyprivate(list)
copyprivate子句可以关联single构造，在single构造的barrier到达之前就完成了广播工作。copyprivate可以对private和threadprivate子句中的变量进行操作，但是当使用single构造时，copyprivate的变量不能用于private和firstprivate子句中。


*/