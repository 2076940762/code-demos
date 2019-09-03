#include "pch.h"
#include <windows.h>
#include "mpi.h"
#include <iostream>
#include <string.h>

using namespace std;
const int SEND_LEN = 100;

int main(int argc, char * argv[])
{

	MPI_Init(&argc, &argv);

	int rank = -1;
	int size = -1;
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &size);

	int sendBuffer[SEND_LEN];
// 	for (int i :sendBuffer)
// 	{
// 		i = rank;
// 	}
	for (int i=0;i<SEND_LEN;i++)
	{
		sendBuffer[i] = rank;
	}

	cout << endl << "rank = " << rank << "   ";
	for (int i = 0; i < SEND_LEN; i++)
	{
		cout << sendBuffer[i] << "\t";
	}
	cout << endl;

	const int stride = 120;
	int * recvbuf = new int[size * 120];//每个进程存100个元素，空20个位置后，在下一个位置存i+1个进程发的数据
	if (recvbuf == nullptr)
	{
		MPI_Abort(MPI_COMM_WORLD, -1);
	}
	for (int i=0;i< size * 120;i++)
	{
		recvbuf[i] = -1; //初始化接收缓存区全为-1；
	}

	int * recvcounts = new int[size];
	for (int i=0;i<size;i++)
	{
		recvcounts[i] = SEND_LEN;//root进程接收第i个进程recvcounts[i]个元素
	}

	int * displs = new int[size];
	for (int i=0;i<size;i++)
	{
		displs[i] = i * stride;//第i个进程收到的数据，从recvbuf的第displs[i]元素开始存，占recvcounts[i]个位置
	}

// 	int MPI_Gatherv(void *sendbuf, int sendcount,
// 		MPI_Datatype sendtype, void *recvbuf,
// 		int *recvcounts, int *displs,
// 		MPI_Datatype recvtype, int root, MPI_Comm comm)
	MPI_Gatherv(sendBuffer, SEND_LEN, MPI_INT, recvbuf, recvcounts, displs, MPI_INT, 0, MPI_COMM_WORLD);

// 	HANDLE CreateMutex(
// 		LPSECURITY_ATTRIBUTESlpMutexAttributes,
// 		BOOLbInitialOwner,
// 		LPCTSTRlpName
// 	);
	HANDLE mutexHandle = NULL;
	if ((mutexHandle = OpenMutex(MUTEX_ALL_ACCESS, false, (LPCWSTR)"mutexForGatherV") ) == nullptr)
	{
		mutexHandle = CreateMutex(NULL, false, (LPCWSTR)"mutexForGatherV");
	}

	WaitForSingleObject(mutexHandle, INFINITE);
	if (rank == 0)
	{
		cout << endl << "  rank = " << rank << endl;
		for (int i = 0; i < size * 120; i++)
		{
			cout << recvbuf[i] << "\t";
			if (i+1 % 10 == 0)
			{
				cout << endl;
			}
		}
	}
	ReleaseMutex(mutexHandle);

	MPI_Finalize();
	//CloseHandle(mutexHandle);

// 	cout << endl << "  rank = " << rank << endl;
// 	for (int i = 0; i < size * 120; i++)
// 	{
// 		cout << recvbuf[i] << "\t";
// 		if (i % 10 == 0)
// 		{
// 			cout << endl;
// 		}
// 	}
}