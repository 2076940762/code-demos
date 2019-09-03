#include "pch.h"
#include <stdio.h>
#include <iostream>
#include "mpi.h"
#include <string>
#include <string.h>

using namespace std;

#pragma comment(lib,"mpi.lib")

int main(int argc, char* argv[])
{	
	MPI_Init(&argc, &argv); //初始化MPI环境

	double beginTime = MPI_Wtime();//获取墙上时间

	int rank;
	int size;
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);  //获取当前进程在MPI_COMM_WORLD通信器中的编号
	MPI_Comm_size(MPI_COMM_WORLD, &size);  //获取MPI_COMM_WORLD通信器中进程数
	
	char  ProcesserName[MPI_MAX_PROCESSOR_NAME] = { 0 }; 
	int len = -1;
	MPI_Get_processor_name(ProcesserName, &len); //获取处理器名称
	std::string strProName(ProcesserName,len);	

	int mainVer, subVer;
	MPI_Get_version(&mainVer, &subVer);       //获取mpi版本号

	double EndTime = MPI_Wtime();
	std::cout << "\n***hello world.\n" << "rank = " << rank << ",\nsize = " << size << ",\nprocesserName = " << strProName << ",\nused time "
		<< EndTime - beginTime << " ,\nversion" << mainVer << "." << subVer << "\n***"<<endl;
	
	cout << "\n\n********************点对点通信******************" << endl;
	int sBuffer[] = { 1,2,3,4,5,6,7,8,93,123 };
	int recBuff[1024] = { 0 };
	
	if (rank == 0)
	{
		//int MPI_Send(const void *buf, int count, MPI_Datatype datatype, int dest, int tag,MPI_Comm comm)
		MPI_Send(sBuffer, sizeof(sBuffer)/sizeof(int), MPI_INT, 1, 0, MPI_COMM_WORLD);
	}
	else
	{
		MPI_Status statu;
// 		int MPI_Recv(void *buf, int count, MPI_Datatype datatype, int source, int tag,
// 			MPI_Comm comm, MPI_Status *status)
		MPI_Recv(recBuff, 1024, MPI_INT,  0, 0, MPI_COMM_WORLD, &statu);  //count为接收缓冲区大小

		int count;
		MPI_Get_count(&statu, MPI_INT, &count);//获取接收到的数据个数

		std::cout << endl;
		for (int i=0;i< count;i++)
		{
			cout << recBuff[i] << ",";
		}
		std::cout << endl;
		
	}
	   
	MPI_Finalize(); //结束MPI环境，除了rank=0的进程，其余进程结束后0号进程才继续运行
		
	return 0;
}