 #include "pch.h"
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

	int * const recvbuf = new int[size];
	if (recvbuf == nullptr)
	{
		MPI_Abort(MPI_COMM_WORLD, -1);
	}
	
// 	���н���(����������) �� sendbuf �е����ݷ��͸�������, �����̽���Щ���ݰ����̺ŵ�˳��
// 		���ν��յ� recvbuf ��.���ͺͽ��յ����������볤�ȱ�������, �����ͺͽ���ʹ�õ��������ͱ���
// 		������ͬ����������.���� recvbuf, recvcount �� recvtype ���Ը�����������.
// 	int MPI_Gather(void *sendbuf, int sendcount,
// 		MPI_Datatype sendtype, void *recvbuf,
// 		int recvcount, MPI_Datatype recvtype, int root,
// 		MPI_Comm comm)
	MPI_Gather(&rank, 1, MPI_INT, recvbuf, 1, MPI_INT, 0, MPI_COMM_WORLD);//sendcount�����recvcount���

	if (rank == 0)
	{
		for (int i=0;i<size;i++)
		{
			cout << recvbuf[i] << "\t";
		}
		cout << endl;
	}


	MPI_Finalize();

}