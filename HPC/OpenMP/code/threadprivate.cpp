/*
threadprivate��private����������threadprivate�����ı���ͨ����ȫ�ַ�Χ����Ч�ģ���private�����ı���ֻ���������Ĳ��й�������Ч��
threadprivate�Ķ�Ӧ����ֻ������copyin��copyprivate��schedule��num_threads��if�Ӿ��У����������κ������Ӿ��С�
����threadprivate�ı����ĵ�ַ�����ǳ�����
����C++���ࣨclass�����ͱ���������threadprivate�Ĳ���ʱ��Щ���ƣ�������ʱ�����ⲿ��ʼ��ʱ�����������ȷ�Ŀ������캯����

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
copyprivate�Ӿ��ṩ��һ�ֻ�����һ��˽�б�����һ��ֵ��һ���̹߳㲥��ִ��ͬһ��������������̡߳�
�÷����£�
copyprivate(list)
copyprivate�Ӿ���Թ���single���죬��single�����barrier����֮ǰ������˹㲥������copyprivate���Զ�private��threadprivate�Ӿ��еı������в��������ǵ�ʹ��single����ʱ��copyprivate�ı�����������private��firstprivate�Ӿ��С�


*/