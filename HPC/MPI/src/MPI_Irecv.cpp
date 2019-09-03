#include "pch.h"
#include "mpi.h"
#include <iostream>
#include <string.h>

using namespace std;

int main(int argc, char * argv[])
{
	int rank = -1;
	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);

	if (rank ==  0)
	{
		char SendBuffer[] = "hello world!";
		//异步发送
// 		int MPI_Isend(void *buf, int count,
// 			MPI_Datatype datatype, int dest, int tag,
// 			MPI_Comm comm, MPI_Request *request)
		MPI_Request sendReq;
		//MPI_Isend(SendBuffer, strlen(SendBuffer), MPI_CHAR, 1, 0, MPI_COMM_WORLD, &sendReq);
		MPI_Isend(SendBuffer, 1023, MPI_CHAR, 1, 0, MPI_COMM_WORLD, &sendReq);

		int sendFlag;
		MPI_Status status;
// 		int MPI_Test(MPI_Request *request, int *flag,MPI_Status *status)//测试通信是否完成
		MPI_Test(&sendReq, &sendFlag, &status);
		while (!sendFlag)
		{
			cout << "waiting for send complete." << endl;
		}
		cout << "send success" << endl;

	}
	else
	{
		char recBuffer[1024] = "";
		memset(recBuffer, 0, 1024 * sizeof(char));
		MPI_Request recvReq;
		// int MPI_Irecv(void *buf, int count,
			// 			MPI_Datatype datatype,
			// 			int source, int tag, MPI_Comm comm,
			// 			MPI_Request *request)
		MPI_Irecv(recBuffer, 1024, MPI_CHAR, 0, 0, MPI_COMM_WORLD, &recvReq);
		
		MPI_Status status;
		/*		int MPI_Wait(MPI_Request *request, MPI_Status *status)*/ //等待通信完成
		MPI_Wait(&recvReq, &status);

		cout << recBuffer << endl;
	}

	MPI_Finalize();
}