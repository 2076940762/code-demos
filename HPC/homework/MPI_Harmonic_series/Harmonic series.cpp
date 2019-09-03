#define _CRT_SECURE_NO_WARNINGS

#include "mpi.h"
#include <time.h>
#include <iostream>
#include "BigDouble.h"
#include "omp.h"

#pragma  comment(lib,"mpi.lib")
using namespace std;

const int n = 100000;
const int d = 50;
// const int n = 7;
// const int d = 12;
const int N = 256;

int main(int argc,char* argv[])
{
	MPI_Init(&argc, &argv);

	int myMpiSize = -1, myMpiRank = -1;        //进程数和进程号
	MPI_Comm_size(MPI_COMM_WORLD,&myMpiSize);
	MPI_Comm_rank(MPI_COMM_WORLD, &myMpiRank);
	
	BigDouble result("0.0", d), temp("0.0", d);

	//任务分割
	int part = n / myMpiSize;
	if (myMpiRank == 0) //主进程
	{
		for (int i=1;i<part;i++)
		{
			temp.set(BigDouble::Division(i, d), d);
			result = result + temp;
			temp.set("0.0", d);
		}

		//只有一个进程时
		if (myMpiSize == 1) 
		{
			temp.set(BigDouble::Division(n, d), d);
			result = result + temp;
			temp.set("0.0", d);
		}

		omp_lock_t lock;
		omp_init_lock(&lock);
		//接收其他进程发来的结果
#pragma omp parallel for schedule(dynamic) num_threads(4)
		for (int i=1;i<myMpiSize;i++)
		{
			MPI_Status status;
			int len = -1;
			char * recvBuf = new char[N];
			memset(recvBuf, 0, N * sizeof(char));
// 			int MPI_Recv(void *buf, int count, MPI_Datatype datatype, int source, int tag, MPI_Comm comm, MPI_Status *status)
// 				OUT      buf       接收消息数据的首地址
// 				IN       Count     接收消息数组元素的最大个数(接收缓冲区大小)
// 				IN       datatype  接收消息的数据类型
// 				IN       source    发送消息的进程编号
// 				IN       tag       消息标签
// 				IN       comm      通信器
// 				OUT      status    接收消息时返回的状态
			MPI_Recv(recvBuf, N, MPI_CHAR, MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &status);
			MPI_Get_count(&status, MPI_CHAR,&len);
			recvBuf[len] = '\0';

			omp_set_lock(&lock);
			temp.set(string(recvBuf), d);
			result = result + temp;
			omp_unset_lock(&lock);

			memset(recvBuf, 0, N * sizeof(char));
		}

		omp_destroy_lock(&lock);

		//输出最终结果
		cout << "S" << n << "=" << result.toStringRounding() << endl;
		cout << "clock():" << clock() << endl;
	}
	else  if (myMpiRank == myMpiSize -1)//最后一个进程
	{
		for (int i = part * myMpiRank; i <= n; i++)
		{
			temp.set(BigDouble::Division(i, d), d);
			result = result + temp;
			temp.set("0.0", d);
		}
		
		//发送结果到0号进程
		string strDigital = result.toString();
		// 		int MPI_send(void *buf, int count, MPI_Datatype datatype, int dest, int tag, MPI_Comm comm)
		// 		IN      buf       所要发送消息数据的首地址
		// 		IN      count     发送消息数组元素的个数
		// 		IN      datatype  发送消息的数据类型
		// 		IN      dest      接收消息的进程编号
		// 		IN      tag       消息标签
		// 		IN      comm      通信器
		MPI_Send((void*)strDigital.c_str(), strDigital.size(), MPI_CHAR, 0, myMpiRank, MPI_COMM_WORLD);

	}
	else  //其他进程
	{
		for (int i = part*myMpiRank; i < part * (myMpiRank+1); i++)
		{
			temp.set(BigDouble::Division(i, d), d);
			result = result + temp;
			temp.set("0.0", d);
		}

		//发送结果到0号进程
		string strDigital = result.toString();
// 		int MPI_send(void *buf, int count, MPI_Datatype datatype, int dest, int tag, MPI_Comm comm)
// 		IN      buf       所要发送消息数据的首地址
// 		IN      count     发送消息数组元素的个数
// 		IN      datatype  发送消息的数据类型
// 		IN      dest      接收消息的进程编号
// 		IN      tag       消息标签
// 		IN      comm      通信器
		MPI_Send((void*)strDigital.c_str(), strDigital.size(), MPI_CHAR, 0, myMpiRank, MPI_COMM_WORLD);
	}
	
	MPI_Finalize();
}
