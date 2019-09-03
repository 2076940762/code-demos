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
	cout << "rank:" << rank << "  hello world" << endl;
	MPI_Barrier(MPI_COMM_WORLD);
	cout << "rank:" << rank << "  after MPI_Barrier " << endl;


	MPI_Finalize();
// rank:4  hello world
// 	rank : 8  hello world
// 	rank : 5  hello world
// 	rank : 2  hello world
// 	rank : 6  hello world
// 	rank : 9  hello world
// 	rank : 0  hello world
// 	rank : 3  hello world
// 	rank : 7  hello world
// 	rank : 1  hello world
// 	rank : 0  after MPI_Barrier
// 	rank : 8  after MPI_Barrier
// 	rank : 1  after MPI_Barrier
// 	rank : 9  after MPI_Barrier
// 	rank : 4  after MPI_Barrier
// 	rank : 5  after MPI_Barrier
// 	rank : 6  after MPI_Barrier
// 	rank : 2  after MPI_Barrier
// 	rank : 7  after MPI_Barrier
// 	rank : 3  after MPI_Barrier
// 	请按任意键继续. . .

}
