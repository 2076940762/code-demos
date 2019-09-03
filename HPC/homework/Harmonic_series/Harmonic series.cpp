#define _CRT_SECURE_NO_WARNINGS

#include "mpi.h"
#include <iostream>
#include "BigDouble.h"
#include <time.h>

#pragma  comment(lib,"mpi.lib")
using namespace std;

const int n = 100000;
const int d = 50;
// const int n = 7;
// const int d = 12;

int main(int argc,char* argv[])
{


	BigDouble result("0.0", d), temp("0.0",d);
	for (int i=1;i<=n;i++)
	{
		temp.set(BigDouble::Division(i, d), d);
		result = result + temp;
		temp.set("0.0", d);
	}

	cout << result.toStringRounding() << endl;
	cout << "clock():" << clock() << endl;
}
