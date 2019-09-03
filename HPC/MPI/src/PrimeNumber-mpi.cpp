#include "stdio.h"
#include "mpi.h"
#include <math.h>
int IsPrimeNumber(int n){
	int flag = 1;
	int i;
	int max = sqrt((double)n);
	for (i = 2; i <= max; i++){
		if (n%i == 0){
			flag = 0;
			break;
		}
	}
	return flag;
}
int  main(int argc, char *argv[])
{
	int total = 1000000;
	int  message[1];
	int np, rank;
	double startwtime = 0.0, endwtime;
	MPI_Status status;
	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &np);
	int part = total / np;
	printf("numprocs=%d  rank:%d\n", np, rank);
	startwtime = MPI_Wtime();
	if (0 == rank) //主进程执行
	{
		int total_count = 0;
		int count = 0;
		int i;
		int pre_num = -3;
		//		printf("id:%d --> 2\n",rank);
		for (i = 3; i <= part; i = i + 2)
		{
			if (IsPrimeNumber(i))
			{
				if (pre_num == (i - 2))
					count++;
				//		printf("id:%d --> %d\n",rank, i);
				pre_num = i;
			}
		}
		printf("rank:%d  count:%d\n", rank, count);
		total_count += count;

		//接收各进程找出的连续奇整数为素数的个数并相加
		int source;
		for (source = 1; source<np; source++)
		{
			MPI_Recv(message, 1, MPI_INTEGER, source, 99, MPI_COMM_WORLD, &status);
			printf("source:%d  message:%d\n", source, message[0]);
			total_count += message[0];
		}

		//判断部分之间是否存在连续奇整数为素数
		int m = 0;
		for (m = 1; m <= np; m++)
		{

			if (1 == IsPrimeNumber(m*part - 1) && 1 == IsPrimeNumber(m*part + 1))
				total_count++;

		}
		printf("total_count:%d\n", total_count);
		endwtime = MPI_Wtime();
		printf("run time = %f\n", endwtime - startwtime);

	}
	else //非主进程执行
	{
		int count = 0;
		int pre_num = -3;
		int i;
		for (i = part*rank + 1; i <= part*(rank + 1); i = i + 2)
		{
			if (IsPrimeNumber(i))
			{
				if (pre_num == (i - 2))
					count++;
				//		printf("id:%d --> %d\n",rank, i);
				pre_num = i;
			}
		}
		printf("rank:%d  count:%d\n", rank, count);
		message[0] = count;
		MPI_Send(message, 1, MPI_INTEGER, 0, 99, MPI_COMM_WORLD);
	}

	MPI_Finalize();
}

