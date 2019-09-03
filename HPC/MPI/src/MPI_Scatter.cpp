#include "pch.h"
#include <mpi.h>
#include <stdio.h>

int main(int argc, char *argv[])
{
	int rank, size, i, j;
	double param[40], mine;
	int sCount, rCount;

	MPI_Init(&argc, &argv);

	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &size);

	rCount = 1;

	if (rank == 3)
	{
		for (i = 0; i < size; i++) param[i] = 23.0 + i;
		sCount = 1;
	}

	MPI_Scatter(param, sCount, MPI_DOUBLE, &mine, rCount, MPI_DOUBLE, 3, MPI_COMM_WORLD);
	printf("P:%d mine is %f\n", rank, mine);

	MPI_Finalize();
}
