#define _CRT_SECURE_NO_WARNINGS

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

	char broadcastBuffer[1024] = "hello world";
	if (rank == 0)
	{
		strcpy(broadcastBuffer, "The Message Passing Interface Standard (MPI) is a message passing library standard based on the consensus of the MPI Forum");
	}
	cout << "rank = " << rank << "befor broadcast\t" << broadcastBuffer << endl;

// 		int MPI_Bcast(void *buffer, int count,
// 			MPI_Datatype datatype, int root, MPI_Comm comm)
	//MPI_Bcast(broadcastBuffer, strlen(broadcastBuffer), MPI_CHAR,0, MPI_COMM_WORLD);
	MPI_Bcast(broadcastBuffer, 1024, MPI_CHAR,0, MPI_COMM_WORLD);

	cout << "rank = " << rank << "end broadcat \t" << broadcastBuffer << endl;

	
	MPI_Finalize();

}