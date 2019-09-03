#include "pch.h"
#include <mpi.h>
#include <stdio.h>

/* Run with 16 processes */

int main(int argc, char *argv[])
{
	int rank, root = 7;
	struct 
	{
		double value;
		int rank; 
	}  in, out;

	MPI_Init(&argc, &argv);

	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	
	in.value = rank + 1;
	in.rank = rank;
	
// 	int MPI_Reduce(void *sendbuf, void *recvbuf, int count,
// 		MPI_Datatype datatype, MPI_Op op, int root,
// 		MPI_Comm comm)
	//MPI_MAXLOC最大值及位置
	MPI_Reduce(&in, &out, 1, MPI_DOUBLE_INT, MPI_MAXLOC, root, MPI_COMM_WORLD);
	if (rank == root)
		printf("P:%d max=%lf at rank %d\n", rank, out.value, out.rank);

	MPI_Reduce(&in, &out, 1, MPI_DOUBLE_INT, MPI_MINLOC, root, MPI_COMM_WORLD);
	if (rank == root)
		printf("P:%d min=%lf at rank %d\n", rank, out.value, out.rank); 
	
	MPI_Finalize();
}
