#include "pch.h"
#include <windows.h>
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
	if (rank == -1 || size == -1)
	{
		cout << "rank =" << rank << "," << "size = " << size << endl;
	}

	//发送缓冲区
	const int SEND_LEN = 10;
	int  sendBuf[SEND_LEN] ;
	cout << "sendbuf rank=" << rank << " : ";
	for (int a:sendBuf)
	{
		a = rank;
		cout << a << ",";
	}
	cout << endl;
	

	//接收区
	int * recvBuf = new int[size*SEND_LEN];
	for (int i=0;i<size*SEND_LEN;i++)
	{
		recvBuf[i] = -1;
	}

// 	int MPI_Allgather(void *sendbuf, int sendcount,
// 		MPI_Datatype sendtype, void *recvbuf,
// 		int recvcount, MPI_Datatype recvtype,
// 		MPI_Comm comm)
	MPI_Allgather(sendBuf, SEND_LEN, MPI_INT, recvBuf, SEND_LEN, MPI_INT, MPI_COMM_WORLD);

	HANDLE mutex = NULL;
	if ((mutex = OpenMutex(MUTEX_ALL_ACCESS,false,(LPCWSTR)"mutex for MPI_Allgather")) == NULL)
	{
		mutex = CreateMutex(NULL, false, (LPCWSTR)"mutex for MPI_Allgather");
	}

	WaitForSingleObject(mutex, INFINITE);
	cout << "recvbuf rank = " << rank << "  : \n";
	for (int i = 0 ;i< size*SEND_LEN;i++)
	{
		cout << recvBuf[i] << "\t";
		if ((i+1)%100 == 0)
		{
			cout << endl;
		}
	}
	cout << "\n" << endl;
	ReleaseMutex(mutex);
	
	MPI_Barrier(MPI_COMM_WORLD);

	MPI_Finalize();

	//CloseHandle(mutex);
}
