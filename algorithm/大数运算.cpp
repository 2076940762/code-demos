#include "stdafx.h"
#include <iostream>
using namespace std;

//大数加法
void array_add(const int *a, int len_a, const int *b, int len_b, int *r, int len_r)//a,b都大于零，a+b存在r中。
{
	for (int i=0; i<len_r; ++i)
	{
		r[i] = 0;
	}
	r += len_r-1;      //r指向最后一个元素
	const int *p_short;
	const int *p_long;
	int add_count;
	int add_count2;

	if (len_a<=len_b)
	{
		add_count = len_a;
		add_count2 = len_b-len_a;
		p_short = a+len_a-1;
		p_long = b+len_b-1;
	}
	else
	{
		add_count = len_b;
		add_count2 = len_a-len_b;
		p_long = a+len_a-1;
		p_short = b+len_b-1;
	}	
	int carry = 0;
	int sum_num;	
	
	while (add_count--)
	{
		sum_num = *(p_short--)+*(p_long--)+carry;
		if (sum_num>=10)
		{
			carry = 1;
			*(r--) = sum_num-10;
		}
		else
		{
			carry = 0;
			*(r--) = sum_num;
		}
	}
	while (add_count2--)
	{
		sum_num = *(p_long--)+carry;
		if (sum_num>=10)
		{
			carry = 1;
			*(r--) = sum_num-10;
		}
		else
		{
			carry = 0;
			*(r--) = sum_num;
		}
	}
	if (carry==1)
	{
		*(r--) = 1;
	}	
}

void array_substract(const int *a, int len_a, const int *b, int len_b, int *r, int len_r)//保证a大于b，且a，b大于零，结果存在r中
{	
	for (int i=0; i<len_r; ++i)
	{
		r[i] = 0;
	}
	
	r += len_r-1;
	int count_back = len_b;
	int count_front = len_a-len_b;
	const int* pa = a+len_a-1;
	const int* pb = b+len_b-1;
	int carry=0;
	while (count_back--)
	{
		if (carry+*pa>=*pb)
		{
			*(r--) = carry+*(pa--)-*(pb--);
			carry = 0;
		}
		else
		{
			*(r--) = 10+carry+*(pa--)-*(pb--);
			carry = -1;
		}		
	}
	if (carry==-1)
	{
		*(r--) = -1+*(pa--);
		count_front--;
	}
	while (count_front-- )
	{
		*(r--) = *(pa--);
	}
	while (*(++r)==0 && --len_a);	
}

void array_multiply(const int *a, int len_a, const int *b, int len_b, int *r, int len_r)//大数乘法，仿照手工计算实现，能够给出中间结果,
																						//a*b的绝对值存在r中,要保证len_r>=len_a+len_b.
{
	int pos = 0;	
	int* product = new int[len_r];
	int* r_temp = new int[len_r];

	for (int i=0; i<len_r; ++i)
	{
		r_temp[i] = 0;
	}
	
	int product_front;
	int product_back;
	int b_count;
	int a_count;

	for (a_count=len_a-1; a_count>-1; --a_count)
	{
		product_front = 0;
		for (int i=0; i<len_r; ++i)
		{
			product[i] = 0;
		}
		for (b_count=len_b-1; b_count>-1; --b_count)
		{
			product_back = (product_front+a[a_count]*b[b_count])%10;
			product_front = (product_front+a[a_count]*b[b_count])/10;
			product[len_r-(len_a-1+len_b-1-a_count-b_count)-1] = product_back;
		}
		product[len_r-(len_a-1+len_b-1-a_count-b_count)-1] = product_front;
		array_add(product, len_r, r_temp, len_r, r, len_r);
		for(int i=0; i<len_r; ++i)
		{
			r_temp[i] = r[i];
		}
	}
	delete[] product;
}

void q_array_multiply(const int *a, int len_a, const int *b, int len_b, int *r)//数组a和数组b相乘，结果保存在r中，r的长度为len_a+len_b，此算法的
                                                                               //效率更高
{
	int r_len = len_b+len_a;
	memset(r, 0, r_len*sizeof(int));
	for (int a_count=len_a-1; a_count>-1; --a_count)
	{
		for (int b_count=len_b-1; b_count>-1; --b_count)
		{
			r[a_count+b_count+1] += a[a_count]*b[b_count];
		}
	}
	for (int r_count=r_len-1; r_count>0; --r_count)
	{
		r[r_count-1] += r[r_count]/10;
		r[r_count] = r[r_count]%10;
	}	
}

int array_compare(const int *a, int len_a, const int *b, int len_b)//比较两个正数的大小
{
	if(len_a>len_b)
	{
		return 1;
	}
	if(len_a<len_b)
	{
		return -1;
	}

	int len = len_a;
	int i;
	for(i=0; (i<len) && (a[i]==b[i]); ++i);
	if(i==len)
	{
		return 0;
	}
	if(a[i]>b[i])
	{
		return 1;
	}
	if(a[i]<b[i])
	{
		return -1;
	}
	return true;
}


void array_division(const int *a, int len_a, const int *b, int len_b, char *r, int len_r)//两个正整数相除，即a/b，结果保存在r中,要求r的长度足够
{
	int *a_copy = new int[len_a+len_b+len_r];
	int len_a_copy = len_b;
	int *a_r = new int[len_a+len_b+len_r];
	int len_a_r = len_a+len_b+len_r;

	for(int i=0; i<len_a; ++i)
	{
		a_copy[i] = a[i];
	}
	for(int i=len_a; i<len_a_r; ++i)
	{
		a_copy[i] = 0;
	}

	if(array_compare(a_copy, len_a_copy, b, len_b)<0)
	{
		++len_a_copy;
	}	

	int r_pos=0;
	int count = 0;
	int point_count = 0;
	while(r_pos<len_r-1)
	{
		count = 0;
		while(array_compare(a_copy, len_a_copy, b, len_b)>=0)
		{
			++count;
			array_substract(a_copy, len_a_copy, b, len_b, a_r, len_a_r);
			for(int i=0; i<len_a_copy; ++i)
			{
				a_copy[i] = a_r[len_a_r-len_a_copy+i];
			}

			int j;
			for(j=0; (j<len_a_copy) && (a_copy[j]==0); ++j);
			for(int i=j; i<len_a_r; ++i)
			{
				a_copy[i-j] = a_copy[i];
			}
			len_a_copy = len_a_copy-j;
			point_count += j;
		}
		r[r_pos++] = count+'0';
		++len_a_copy;
		
		while((array_compare(a_copy, len_a_copy, b, len_b)<0) && (r_pos<len_r-1))
		{
			r[r_pos++] = '0';
			if(*a_copy==0)
			{
				for(int i=1; i<len_a_r; ++i)
				{
					a_copy[i-1] = a_copy[i];
				}
				--len_a_copy;
				++point_count;
			}	
			++len_a_copy;			
		}	
	}
	int point_pos = len_a_r-point_count-len_a_copy-len_b;
	if(point_pos>0)
	{	
		for(int i=len_r-2; i>point_pos-1; --i)
		{
			r[i+1] = r[i];
		}
		r[point_pos] = '.';
	}
	else
	{
		for(int i=len_r-1-(2-point_pos);i>=0;--i)
		{
			r[i+2-point_pos] = r[i];
		}
		for(int i=0; i<2-point_pos; ++i)
		{
			r[i] = '0';
		}
		r[1] = '.';
	}
}

void factorial(int n, int *r, int len_r)//计算n的阶乘，要求计算结果不超过len_r位
{
	int *a = new int[len_r];
	int *b = new int[len_r];
	b[len_r-1] = 1;
	for(int i=0; i<len_r-1; ++i)
	{
		b[i] = 0;
	}
	int len_a;
	int len_b = 1;
	for(int i=1; i<=n; ++i)
	{
		int i_temp = i;
		int j;
		for(j=len_r-1; i_temp!=0; --j)
		{
			a[j] = i_temp%10;
			i_temp /= 10;
		}
		len_a = len_r-1-j;
		array_multiply(&a[j+1], len_a, &b[len_r-len_b], len_b, r, len_r);
		for(j=0; r[j]==0; ++j);
		len_b = len_r-j;
		int *temp;
		temp = r;
		r = b;
		b = temp;	
	}
	if(n%2==0)
	{
		for(int i=0; i<len_r; ++i)
		{
			r[i] = b[i];
		}
	}

}

int* power(int m, int n, int len_r)//计算m的n次幂,要求n>=0,保证len_r的长度足够保存结果
{
	int *a = new int[len_r];	
	int i;
	for(i=len_r-1; m!=0; --i)
		{
			a[i] = m%10;			
			m /= 10;
		}
	for(int j=i; j>=0; --j)
	{
		a[j] = 0;		
	}	
	int len_a = len_r-1-i;
	int *r = new int[len_r];
	int *b = new int[len_r];	
	for(i=0; i<len_r-1; ++i)
	{
		r[i] = 0;
		b[i] = 0;
	}
	r[len_r-1] = 1;
	b[len_r-1] = 0;
	int len_b = 1;

	int *temp;
	while(n--!=0)
	{
		int j;
		for(i=len_r-1; i>=len_r-len_a; --i)
		{
			for(j=len_r-1; j>=len_r-len_b; --j)
			{
				b[i+j-(len_r-1)] += a[i]*r[j];
			}
		}
		for(i=len_r-1; i>0; --i)
		{
			if(b[i]>=10)
			{
				b[i-1] += b[i]/10;
				b[i] %= 10;
			}
		}
		for(i=0; b[i]==0; ++i);
		len_b = len_r-i;
		
		temp = r;
		r = b;
		b = temp;
		for(i=0; i<len_r; ++i)
		{
			b[i] = 0;
		}
	}
	delete[] a;
	delete[] b;
	return r;
}

int _tmain(int argc, _TCHAR* argv[])//该算法的所有结果都保存在数组r中，r中多余的位数都默认为0
{
	int a[7]={1,2,3,4,5,6,7};
	int b[3]={3,2,1};
	int r[100];	

	cout<<"加法"<<endl;
	array_add(a, sizeof(a)/sizeof(int), b, sizeof(b)/sizeof(int), r, sizeof(r)/sizeof(int));	
	for(int i=0; i<100; ++i)
	{
		cout<<r[i];
	}
	cout<<endl;

	cout<<"减法"<<endl;
	array_substract(a, sizeof(a)/sizeof(int), b, sizeof(b)/sizeof(int), r, sizeof(r)/sizeof(int));	
	for(int i=0; i<100; ++i)
	{
		cout<<r[i];
	}	
	cout<<endl;

	cout<<"乘法"<<endl;
	array_multiply(a, sizeof(a)/sizeof(int), b, sizeof(b)/sizeof(int), r, sizeof(r)/sizeof(int));	
	for(int i=0; i<100; ++i)
	{
		cout<<r[i];
	}	
	cout<<endl;

	char r2[100];
	cout<<"除法"<<endl;
	array_division(a, sizeof(a)/sizeof(int), b, sizeof(b)/sizeof(int), r2, sizeof(r2)/sizeof(char));	
	for(int i=0; i<100; ++i)
	{
		cout<<r2[i];
	}	
	cout<<endl;
	return 0;
}

