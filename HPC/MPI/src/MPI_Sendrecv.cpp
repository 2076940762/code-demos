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
	int rank = -1;
	
	MPI_Init(&argc, &argv);

	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	if (rank == 0)
	{
		int sendBuffer[1024];
		for (int i=0;i<1024;i++)
		{
			sendBuffer[1] = i * i;
		}

		char revBuffer[1024]="";
		
		MPI_Status status;
		//接收发送无先后顺序
// 		int MPI_Sendrecv(void *sendbuff, int sendcount,
// 			MPI_Datatype sendtype, int dest, int sendtag,
// 			void *recvbuff, int recvcount,
// 			MPI_Datatype recvtype, int source, int recvtag,
// 			MPI_Comm comm, MPI_Status *status)
		MPI_Sendrecv(sendBuffer, 1000, MPI_INT, 1, 0, revBuffer, 1024, MPI_CHAR, 1, 1, MPI_COMM_WORLD, &status);

		int recCount = -1;
		MPI_Get_count(&status, MPI_CHAR, &recCount);

		std::cout << "rank = " << rank << "\t:";
		for (int i = 0; i < recCount; i++)
		{
			cout << revBuffer[i] << ",";
		}
		cout << endl;
	}
	else
	{
		int recBuff[1024] = { 0 };
		MPI_Status status;

// 		int MPI_Recv(void *buff, int count,
// 			MPI_Datatype datatype, int source, int tag,
// 			MPI_Comm comm, MPI_Status *status)
		MPI_Recv(recBuff, 1024, MPI_INT, 0, 0, MPI_COMM_WORLD, &status);

		int recCount = -1;
		MPI_Get_count(&status, MPI_INT, &recCount);
		std::cout << "rank = " << rank << "\t:";
		for (int i = 0; i < recCount; i++)
		{
			cout << recBuff[i] << ",";
		}
		cout << endl;

		char SendBuff[1024] = "";
		int ch = 'a';
		for (int i=0;i<1024;i++)
		{
			SendBuff[i] = ch;
			ch = (++ch - 'a') %( 'z'-'a') + 'a';
		}

// 		int MPI_Send(void *buff, int count,
// 			MPI_Datatype datatype, int dest, int tag,
// 			MPI_Comm comm)
		MPI_Send(SendBuff, 1024, MPI_CHAR, 0, 1, MPI_COMM_WORLD);


	}

	MPI_Finalize();
}