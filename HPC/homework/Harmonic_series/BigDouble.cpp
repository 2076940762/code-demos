#define _CRT_SECURE_NO_WARNINGS
#include "BigDouble.h"
#include <iostream>
#include "omp.h"

BigDouble::BigDouble(string str, int precision)
{
	this->m_strNum = str;
	this->m_precision = precision+m_morePrec;

	Adjust2Precision();	
}

BigDouble::BigDouble(char * str, int precision)
{
	m_strNum.assign(str);
	m_precision = precision+ m_morePrec;
	Adjust2Precision();
}

BigDouble::BigDouble(const BigDouble & other)
{
	m_precision = other.m_precision;
	m_strNum = other.m_strNum;
	Adjust2Precision();
}

void BigDouble::set(const char * str, int pre)
{
	if (str == nullptr)
	{
		return;
	}

	m_strNum.assign(str);
	m_precision = pre+m_morePrec;
	Adjust2Precision();
}

void BigDouble::set(const string str, int pre)
{
	if (str.size() == 0)
	{
		return;
	}

	m_strNum.assign(str);
	m_precision = pre+m_morePrec;
	Adjust2Precision();
}


BigDouble BigDouble::operator+(BigDouble & other)
{
	BigDouble result("0.0", 10);
	//调整精度对齐小数点
	int maxPrecision = m_precision > other.m_precision ? m_precision : other.m_precision;
	Adjust2Precision();
	other.Adjust2Precision();

	string::size_type size_a = m_strNum.size();
	string::size_type size_b = other.get_size();
	string::size_type maxSize = size_a > size_b ? size_a : size_b;
	maxSize++;                               //可能会有进位
	char * pTemp = new char[maxSize + 1];    //字符串结束标志
	if (pTemp == nullptr)
	{
		std::cout << "pTemp == NULL" << endl;
		return result;                        //以后再处理
	}
	for (int i=0;i<maxSize;i++)
	{
		pTemp[i] = '0';
	}
	pTemp[maxSize] = '\0';

	int falg = 0;//进位
	int i;
	if (size_a>size_b)
	{
		//会有进位产生，所以不并行化
		for ( i= 0;i < size_b;i++)
		{
			if (m_strNum[size_a - i - 1] == '.')   //跳过小数点
			{
				pTemp[maxSize - 1 - i] = '.';
				continue;
			}
			int c = this->get(size_a-i-1) + other.get(size_b-i-1) + falg;
			pTemp[maxSize - 1 - i] = Int2Char(c % 10);
			falg = c / 10;
		}

		for (;i<maxSize-1;i++)
		{
			int c = this->get(size_a - 1 - i) + falg;
			pTemp[maxSize - 1 - i] = Int2Char( c % 10);
			falg = c / 10;
		}
		pTemp[0] = Int2Char(falg);
	}
	else
	{
		for (i=0;i<size_a;i++)
		{
			if (m_strNum[size_a-1-i] == '.')
			{
				pTemp[maxSize - 1 - i] = '.';
				continue;
			}
			int c = get(size_a - 1 - i) + other.get(size_b - 1 - i) + falg;
			pTemp[maxSize - 1 - i] = Int2Char(c % 10);
			falg = c / 10;
		}

		for (; i < maxSize - 1; i++)
		{
			int c = other.get(size_b - 1 - i) + falg;
			pTemp[maxSize - 1 - i] = Int2Char(c % 10);
			falg = c / 10;
		}

		pTemp[0] = Int2Char(falg);
	}

	result.set(pTemp, maxPrecision-m_morePrec);
	return result;
}

BigDouble &BigDouble::operator =(const BigDouble & other)
{
	this->m_precision = other.m_precision;
	this->m_strNum = other.m_strNum;

	this->Adjust2Precision();
	return *this;
}

string BigDouble::toStringRounding()
{
	string strResult = m_strNum;
	if (strResult[strResult.size() -m_morePrec] >= '5')
	{
		strResult[strResult.size() - m_morePrec - 1]++;
	}
	strResult.erase(strResult.size() - m_morePrec);
	return strResult;
}

string BigDouble::toString()
{
	return m_strNum;
}

//调整精度，对齐小数点
void BigDouble::Adjust2Precision()
{
	string::size_type	pointPos = m_strNum.find(".");
	if (pointPos == string::npos)
	{
		return;
	}
	int prec = m_strNum.substr(pointPos + 1).size();
	if (prec < m_precision)
	{
		for (int i = 0; i < m_precision-prec; i++)
		{
			m_strNum.append("0");
		}
	}

	if (prec>m_precision) //超出精度
	{
		m_strNum.erase(m_strNum.size() - (prec - m_precision));
	}


	//删除前面多余的0
	//if (m_strNum[0] = '0' && m_strNum.size() > m_precision+2 && m_strNum.find('.') != string::npos)
	{
		
		string::size_type pos = m_strNum.find_first_not_of('0');
		if (pos == pointPos)  // m_strNum[pos]='.'   
		{
			pos--;
		}
		m_strNum = m_strNum.substr(pos);
	}

}

int BigDouble::get_size()
{
	return m_strNum.size();
}

int BigDouble::get_precision()
{
	return m_precision-m_morePrec;
}

void BigDouble::Adjust2Precision(int pre)
{
	this->m_precision = pre+m_morePrec;
	Adjust2Precision();
}

int BigDouble::get(int pos)
{
	if (pos<0 || pos >= m_strNum.size())
	{
		return -1;
	}

	return m_strNum[pos] - '0';

}

char Int2Char(int a)
{
	char ch='0';
	switch (a)
	{
	case 0:
		ch = '0';
		break;
	case 1:
		ch = '1';
		break;
	case 2:
		ch = '2';
		break;
	case 3:
		ch = '3'; 
		break;
	case 4:
		ch = '4';
		break;
	case 5:
		ch = '5';
		break;
	case 6:
		ch = '6';
		break;
	case 7:
		ch = '7';
		break;
	case 8:
		ch = '8';
		break;
	case 9:
		ch = '9';
		break;
	}
	return ch;
}

string BigDouble::Division(int denominator,int prec)
{

	if (denominator<=0 || prec < 0 )
	{
		return "0.0";
	}

	if (denominator == 1)
	{
		return "1.0";
	}

	string strResult = "0.";
	int a = 10;
	int c = 0;
	for (int i=0;i<prec+m_morePrec;i++)
	{
		c = a / denominator;
		strResult.append( string(1,Int2Char(c)));
		a = (a % denominator) * 10;
	}

	//最后一位
	if (a/denominator >= 5)
	{
		strResult[strResult.size() - 1]++;
	}

	return strResult;
}


