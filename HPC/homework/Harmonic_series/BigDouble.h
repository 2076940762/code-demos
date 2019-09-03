#pragma once
#include <string>

using namespace std;

class BigDouble
{
public:
	BigDouble(string str, int precision);
	BigDouble(char * str, int precision);
	BigDouble(const BigDouble & other);
	BigDouble operator+(BigDouble & other);
	BigDouble & operator =(const BigDouble &);
	string toString();
	string toStringRounding();
	int get_size();
	int get_precision();
	void Adjust2Precision(int pre);
	int get(int pos);
	void set(const char * str, int pre);
	void set(const string str, int pre);

public:
	static string Division(int denominator,int prec);
protected:

private:
	void Adjust2Precision();
private:
	string m_strNum;
	int    m_precision;

private:
	static const int m_morePrec = 5;
};

char Int2Char(int a);