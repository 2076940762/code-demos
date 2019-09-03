package HelloWold.regularExpress;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//java.util.regex 包主要包括以下三个类：
//pattern 对象是一个正则表达式的编译表示。Pattern 类没有公共构造方法。要创建一个 Pattern 对象，你必须首先调用其公共静态编译方法，它返回一个 Pattern 对象。该方法接受一个正则表达式作为它的第一个参数。
//Matcher 对象是对输入字符串进行解释和匹配操作的引擎。与Pattern 类一样，Matcher 也没有公共构造方法。你需要调用 Pattern 对象的 matcher 方法来获得一个 Matcher 对象。
//PatternSyntaxException 是一个非强制异常类，它表示一个正则表达式模式中的语法错误。
public class RegularExpressDemo {
	public static void main(String[] args) {
		function1();
		function2();
		function3();
		function4();
		function5();
		TestIP();
	}

	public static void function1() {
		// 正则表达式
		// \s 匹配任何空白字符，包括空格、制表符、换页符等等
		Pattern p = Pattern.compile("this\\s+is\\s+text");
		Matcher m = p.matcher("this is text");
		System.out.println(m.matches());

		// . 匹配除换行符（\n、\r）之外的任何单个字符。要匹配包括 '\n' 在内的任何字符，请使用像"(.|\n)"的模式

		System.out.println(Pattern.compile(".*").matcher("aaaaa").matches()); // true

		System.out.println(Pattern.compile("[.]*").matcher(".....").matches()); // true
		System.out.println(Pattern.compile("[.]*").matcher("aaaa").matches()); // false

		// Attempts to match the entire region against the pattern.
		System.out.println(Pattern.compile("once").matcher("There once was a man from NewYork").matches());// flase

		// Like the {@link #matches matches} method, this method always starts
		// * at the beginning of the region; unlike that method, it does not
		// * require that the entire region be matched.
		// 从字符串开始匹配，要求匹配整个字符串
		System.out.println(Pattern.compile("once").matcher("There once was a man from NewYork").lookingAt());// false

		// * Attempts to find the next subsequence of the input sequence that matches
		// the pattern.
		// 不要求匹配从整个字符串，但一定从开始匹配
		System.out.println(Pattern.compile("once").matcher("There once was a man from NewYork").find());// true
		// [a-z] //匹配所有的小写字母
		// [A-Z] //匹配所有的大写字母
		// [a-zA-Z] //匹配所有的字母
		// [0-9] //匹配所有的数字
		// [0-9\.\-] //匹配所有的数字，句号和减号
		// [ \f\r\t\n] //匹配所有的白字符

		// [[:alpha:]] 任何字母
		// [[:digit:]] 任何数字
		// [[:alnum:]] 任何字母和数字
		// [[:space:]] 任何空白字符
		// [[:upper:]] 任何大写字母
		// [[:lower:]] 任何小写字母
		// [[:punct:]] 任何标点符号
		// [[:xdigit:]]任何16进制的数字，相当于[0-9a-fA-F]
		System.out.println(Pattern.compile("[[:alpha:]]+[[:digit:]]+").matcher("faadsf2134").matches());// false
		System.out.println(Pattern.compile("[[:alpha:]]+[[:digit:]]+").matcher("faadsf2134").lookingAt());// false
		System.out.println(Pattern.compile("[[:alpha:]]+[[:digit:]]+").matcher("faadsf2134").find()); // true

		System.out.println("----------------------");

		System.out.println(Pattern.compile("[[:alpha:]]{1,100}[[:digit:]]{1,100}").matcher("faadsf2134").matches());// false
		System.out.println(Pattern.compile("[[:alpha:]]{1,100}[[:digit:]]{1,100}").matcher("faadsf2134").lookingAt());// false
		System.out.println(Pattern.compile("[[:alpha:]]{1,100}[[:digit:]]{1,100}").matcher("faadsf2134").find());// true

		// ^[a-zA-Z0-9_]{1,}$ // 所有包含一个以上的字母、数字或下划线的字符串
		// ^[1-9][0-9]{0,}$ // 所有的正整数
		// ^\-{0,1}[0-9]{1,}$ // 所有的整数
		// ^[-]?[0-9]+\.?[0-9]+$ // 所有的浮点数
		System.out.println("...................................");
		System.out.println(Pattern.compile("^[-]?[0-9]+\\.?[0-9]+$").matcher("3.141592653").matches());// true
		System.out.println(Pattern.compile("^\\-{0,1}[0-9]{1,}").matcher("3141592653").matches()); // true

		System.out.println("*******************************");

		// (?:pattern) 匹配 pattern 但不获取匹配结果，也就是说这是一个非获取匹配，不进行存储供以后使用。这在使用 "或" 字符 (|)
		// 来组合一个模式的各个部分是很有用。例如， 'industr(?:y|ies) 就是一个比 'industry|industries' 更简略的表达式。
		// String strRge4Path="[a-zA-Z]:[\\\\/]([\\w-_]+[\\\\/])*([\\w-_]+\\.[\\w-_]+)";
		String strRge4Path = "[a-zA-Z]:[\\\\/](?:[\\w-_]+[\\\\/])*(?:[\\w-_]+\\.[\\w-_]+)";
		String strPath = "E:\\Download\\MOOC15-03\\src\\main\\java\\regex\\MatchesLooking.java";
		Pattern p10 = Pattern.compile(strRge4Path);
		Matcher m10 = p10.matcher(strPath);
		System.out.println(m10.matches()); // true
		System.out.println(m10.lookingAt()); // true
		System.out.println(m10.find()); // false
	}

	public static void function2() {
		final String strContent = "<H1>Chapter 1 - 介绍正则表达式</H1>";

		System.out.println(strContent.length());// 28
		// 贪婪
		Pattern p1 = Pattern.compile("<.*>");
		Matcher m1 = p1.matcher(strContent);
		m1.find();
//		System.out.println(m1.matches());         //true
		System.out.println("start:" + m1.start() + "\tend:" + m1.end());// start:0 end:28

		System.out.println("------------------------------------");

		// 非贪婪：如果您只需要匹配开始和结束 H1 标签，下面的非贪婪表达式只匹配 <H1>。
		Pattern p2 = Pattern.compile("<.*?>");
		Matcher m2 = p2.matcher(strContent);
		m2.find();
//		System.out.println(m2.matches());
		System.out.println("start:" + m2.start() + "\tend:" + m2.end());// start:0 end:4

		System.out.println("------------------------------------");

		// \w数字字母下划线
		Pattern p3 = Pattern.compile("<\\w+?>");
		Matcher m3 = p3.matcher(strContent);
		m3.find();
//		System.out.println(m3.matches());
		System.out.println("start:" + m3.start() + "\tend:" + m3.end());// start:0 end:4
	}

	public static void function3() {
		String strContent = "Chapter     99";

		// \b单词边界 ^开头 $字符串结尾 \\s空白符
		Pattern p4 = Pattern.compile("^Chapter\\b\\s*[1-9][0-9]{0,1}$");
		Matcher m4 = p4.matcher(strContent);
		m4.find();
		System.out.println(strContent.length());// 14
		System.out.println("start:" + m4.start() + "\tend:" + m4.end());// start:0 end:14

	}

	public static void function4() {
		String strContent = "Is is the cost of 	  of gasoline going up   	 up";

//		捕获的表达式，正如 [a-z]+ 指定的，包括一个或多个字母。正则表达式的第二部分是对以前捕获的子匹配项的引用，即，单词的第二个匹配项正好由括号表达式匹配。\1 指定第一个子匹配项。
//		单词边界元字符确保只检测整个单词。否则，诸如 "is issued" 或 "this is" 之类的词组将不能正确地被此表达式识别。
//		正则表达式后面的全局标记 g 指定将该表达式应用到输入字符串中能够查找到的尽可能多的匹配。
//		表达式的结尾处的不区分大小写 i 标记指定不区分大小写。
		Pattern p5 = Pattern.compile("\\b([a-z]+)\\s+\\1\\b");
//		Pattern p5 =Pattern.compile("\\b([a-z]+) \\1\\b/ig");
		Matcher m5 = p5.matcher(strContent);
		System.out.println(m5.find());
		;
		System.out.println(strContent.length());// 41
		System.out.println("start:" + m5.start() + "\tend:" + m5.end());// start:15 end:20

	}

	public static void function5() {
		String strCont = "http://www.runoob.com:80/html/html-tutorial.html";

		// \w:数字字母下划线
//		Pattern p6 = Pattern.compile("^(\\w+)://([^/:]+)(:\\d*)?([^# ]*)");
		Pattern p6 = Pattern.compile("^(\\w+)://([^/:]+):(\\d*)?([^# ]*)");
//		第一个括号子表达式捕获 Web 地址的协议部分。该子表达式匹配在冒号和两个正斜杠前面的任何单词。
//		第二个括号子表达式捕获地址的域地址部分。子表达式匹配 : 和 / 之后的一个或多个字符。
//		第三个括号子表达式捕获端口号（如果指定了的话）。该子表达式匹配冒号后面的零个或多个数字。只能重复一次该子表达式。
//		最后，第四个括号子表达式捕获 Web 地址指定的路径和 / 或页信息。该子表达式能匹配不包括 # 或空格字符的任何字符序列。
//		将正则表达式应用到上面的 URI，各子匹配项包含下面的内容：
//		第一个括号子表达式包含 http
//		第二个括号子表达式包含 www.runoob.com
//		第三个括号子表达式包含 :80
//		第四个括号子表达式包含 /html/html-tutorial.html
		Matcher m6 = p6.matcher(strCont);
		System.out.println(m6.matches());// true
		System.out.println(m6.groupCount());
		for (int i = 0; i <= m6.groupCount(); i++) {
			System.out.println(m6.group(i));
		}
//		http://www.runoob.com:80/html/html-tutorial.html
//		http
//		www.runoob.com
//		80
//		/html/html-tutorial.html

	}

	public static void TestIP() {
		Pattern p= Pattern.compile("^(?:(?:(?:2[0-4]\\d)|(?:25[0-5])|([01]?\\d\\d?))\\.){3}((2[0-4]\\d)|(25[0-5])|([01]?\\d\\d?))$");
		Matcher m=p.matcher("255.127.198.16");
		System.out.println(m.matches());    //true
		System.out.println(m.lookingAt());  //true
		System.out.println(m.find());       //false

	}
}
