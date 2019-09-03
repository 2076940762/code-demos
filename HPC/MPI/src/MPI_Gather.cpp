 #include "pch.h"
#include "mpi.h"
#include <iostream>
#include <string.h>

using namespace std;

int main(int argc, char * argv[])
{

	MPI_Init(&argc, &argv);
	
	int rank = -1;
	int size = -1;
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &size);

	int * const recvbuf = new int[size];
	if (recvbuf == nullptr)
	{
		MPI_Abort(MPI_COMM_WORLD, -1);
	}
	
// 	所有进程(包括根进程) 将 sendbuf 中的数据发送给根进程, 根进程将这些数据按进程号的顺序
// 		依次接收到 recvbuf 中.发送和接收的数据类型与长度必须相配, 即发送和接收使用的数据类型必须
// 		具有相同的类型序列.参数 recvbuf, recvcount 和 recvtype 仅对根进程有意义.
// 	int MPI_Gather(void *sendbuf, int sendcount,
// 		MPI_Datatype sendtype, void *recvbuf,
// 		int recvcount, MPI_Datatype recvtype, int root,
// 		MPI_Comm comm)
	MPI_Gather(&rank, 1, MPI_INT, recvbuf, 1, MPI_INT, 0, MPI_COMM_WORLD);//sendcount必须和recvcount相等

	if (rank == 0)
	{
		for (int i=0;i<size;i++)
		{
			cout << recvbuf[i] << "\t";
		}
		cout << endl;
	}


	MPI_Finalize();

}