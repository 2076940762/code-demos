#include "pch.h"
#include <iostream>
#include "mpi.h"

using namespace std;

int main(int argc, char * argv[]) {
	int rank, size;

	MPI_Init(&argc, &argv);
	MPI_Comm_size(MPI_COMM_WORLD, &size);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);

	int sendcount = 1;
	int recvcount = sendcount; //recvcount 和 sendcount 必须相等
	int len = sendcount * size;
	int *sendbuf = new int[len];
	int *recvbuf = new int[len];

	for (int i = 0; i < len; i++) 
		sendbuf[i] = i + size * rank;

	MPI_Alltoall(sendbuf, sendcount, MPI_INT, recvbuf, recvcount, MPI_INT, MPI_COMM_WORLD);

	//输出sendbuf
	for (int i = 0; i < size; i++)
	{
		if (i == rank)
		{
			cout << "Sendbuf on process " << rank << " holds " << len << " data :" << endl;
			for (int j = 0; j < len; j++) 
				cout << sendbuf[j] << " ";
			cout << endl;
		}
		MPI_Barrier(MPI_COMM_WORLD);
	}
	if (rank == 0) cout << endl;

	MPI_Barrier(MPI_COMM_WORLD);

	//输出recvbuf
	for (int i = 0; i < size; i++)
	{
		if (i == rank)
		{
			cout << "Recvbuf on process " << rank << " holds " << len << " data :" << endl;
			for (int j = 0; j < len; j++)
				cout << recvbuf[j] << " ";
			cout << endl;
		}

		MPI_Barrier(MPI_COMM_WORLD);
	} 
	
	delete[] sendbuf;
	delete[] recvbuf;

	MPI_Finalize();
	return EXIT_SUCCESS;

}
