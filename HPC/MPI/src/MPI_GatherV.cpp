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
	int * recvbuf = new int[size * 120];//ÿ�����̴�100��Ԫ�أ���20��λ�ú�����һ��λ�ô�i+1�����̷�������
	if (recvbuf == nullptr)
	{
		MPI_Abort(MPI_COMM_WORLD, -1);
	}
	for (int i=0;i< size * 120;i++)
	{
		recvbuf[i] = -1; //��ʼ�����ջ�����ȫΪ-1��
	}

	int * recvcounts = new int[size];
	for (int i=0;i<size;i++)
	{
		recvcounts[i] = SEND_LEN;//root���̽��յ�i������recvcounts[i]��Ԫ��
	}

	int * displs = new int[size];
	for (int i=0;i<size;i++)
	{
		displs[i] = i * stride;//��i�������յ������ݣ���recvbuf�ĵ�displs[i]Ԫ�ؿ�ʼ�棬ռrecvcounts[i]��λ��
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