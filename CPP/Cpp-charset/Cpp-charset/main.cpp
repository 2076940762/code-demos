#include "charset.h"

void showHex(const char* bytes, int len) {
	for (int i = 0; i < len; i++) {
		printf("%02x ", (unsigned char)bytes[i]);
	}
}

void showHex(std::string charset, std::string str) {
	printf("%10s: ", charset.data());
	showHex(str.data(), str.size());
	printf("\n");
}

void showHex(std::string charset, std::wstring str) {
	printf("%10s: ", charset.data());
	showHex((char*)str.data(), 2 * str.size());
	printf("\n");
}

int main(int argc, char* argv[])
{
	std::wstring wstr(L"中abc国");
	std::string str("中abc国");

	std::string ansi;
	std::string utf8;
	std::string gbk;
	std::wstring unicode;

	showHex("unicode", wstr);
	showHex("ansi", str);

	ansi = UnicodeToAnsi(wstr); showHex("ansi", ansi);

	unicode = AnsiToUnicode(ansi); showHex("unicode", unicode);

	utf8 = AnsiToUtf8(str); showHex("utf8", utf8);
	ansi = Utf8ToAnsi(utf8); showHex("ansi", ansi);

	utf8 = UnicodeToUtf8(wstr); showHex("utf8", utf8);
	unicode = Utf8ToUnicode(utf8); showHex("unicode", unicode);

	gbk = Utf8ToGBK(utf8); showHex("gbk", gbk);
	utf8 = GBKToUtf8(gbk); showHex("utf8", utf8);

	getchar();
	return 0;
}