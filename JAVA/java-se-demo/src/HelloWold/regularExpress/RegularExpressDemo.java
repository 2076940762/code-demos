package HelloWold.regularExpress;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//java.util.regex ����Ҫ�������������ࣺ
//pattern ������һ��������ʽ�ı����ʾ��Pattern ��û�й������췽����Ҫ����һ�� Pattern ������������ȵ����乫����̬���뷽����������һ�� Pattern ���󡣸÷�������һ��������ʽ��Ϊ���ĵ�һ��������
//Matcher �����Ƕ������ַ������н��ͺ�ƥ����������档��Pattern ��һ����Matcher Ҳû�й������췽��������Ҫ���� Pattern ����� matcher ���������һ�� Matcher ����
//PatternSyntaxException ��һ����ǿ���쳣�࣬����ʾһ��������ʽģʽ�е��﷨����
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
		// ������ʽ
		// \s ƥ���κοհ��ַ��������ո��Ʊ������ҳ���ȵ�
		Pattern p = Pattern.compile("this\\s+is\\s+text");
		Matcher m = p.matcher("this is text");
		System.out.println(m.matches());

		// . ƥ������з���\n��\r��֮����κε����ַ���Ҫƥ����� '\n' ���ڵ��κ��ַ�����ʹ����"(.|\n)"��ģʽ

		System.out.println(Pattern.compile(".*").matcher("aaaaa").matches()); // true

		System.out.println(Pattern.compile("[.]*").matcher(".....").matches()); // true
		System.out.println(Pattern.compile("[.]*").matcher("aaaa").matches()); // false

		// Attempts to match the entire region against the pattern.
		System.out.println(Pattern.compile("once").matcher("There once was a man from NewYork").matches());// flase

		// Like the {@link #matches matches} method, this method always starts
		// * at the beginning of the region; unlike that method, it does not
		// * require that the entire region be matched.
		// ���ַ�����ʼƥ�䣬Ҫ��ƥ�������ַ���
		System.out.println(Pattern.compile("once").matcher("There once was a man from NewYork").lookingAt());// false

		// * Attempts to find the next subsequence of the input sequence that matches
		// the pattern.
		// ��Ҫ��ƥ��������ַ�������һ���ӿ�ʼƥ��
		System.out.println(Pattern.compile("once").matcher("There once was a man from NewYork").find());// true
		// [a-z] //ƥ�����е�Сд��ĸ
		// [A-Z] //ƥ�����еĴ�д��ĸ
		// [a-zA-Z] //ƥ�����е���ĸ
		// [0-9] //ƥ�����е�����
		// [0-9\.\-] //ƥ�����е����֣���źͼ���
		// [ \f\r\t\n] //ƥ�����еİ��ַ�

		// [[:alpha:]] �κ���ĸ
		// [[:digit:]] �κ�����
		// [[:alnum:]] �κ���ĸ������
		// [[:space:]] �κοհ��ַ�
		// [[:upper:]] �κδ�д��ĸ
		// [[:lower:]] �κ�Сд��ĸ
		// [[:punct:]] �κα�����
		// [[:xdigit:]]�κ�16���Ƶ����֣��൱��[0-9a-fA-F]
		System.out.println(Pattern.compile("[[:alpha:]]+[[:digit:]]+").matcher("faadsf2134").matches());// false
		System.out.println(Pattern.compile("[[:alpha:]]+[[:digit:]]+").matcher("faadsf2134").lookingAt());// false
		System.out.println(Pattern.compile("[[:alpha:]]+[[:digit:]]+").matcher("faadsf2134").find()); // true

		System.out.println("----------------------");

		System.out.println(Pattern.compile("[[:alpha:]]{1,100}[[:digit:]]{1,100}").matcher("faadsf2134").matches());// false
		System.out.println(Pattern.compile("[[:alpha:]]{1,100}[[:digit:]]{1,100}").matcher("faadsf2134").lookingAt());// false
		System.out.println(Pattern.compile("[[:alpha:]]{1,100}[[:digit:]]{1,100}").matcher("faadsf2134").find());// true

		// ^[a-zA-Z0-9_]{1,}$ // ���а���һ�����ϵ���ĸ�����ֻ��»��ߵ��ַ���
		// ^[1-9][0-9]{0,}$ // ���е�������
		// ^\-{0,1}[0-9]{1,}$ // ���е�����
		// ^[-]?[0-9]+\.?[0-9]+$ // ���еĸ�����
		System.out.println("...................................");
		System.out.println(Pattern.compile("^[-]?[0-9]+\\.?[0-9]+$").matcher("3.141592653").matches());// true
		System.out.println(Pattern.compile("^\\-{0,1}[0-9]{1,}").matcher("3141592653").matches()); // true

		System.out.println("*******************************");

		// (?:pattern) ƥ�� pattern ������ȡƥ������Ҳ����˵����һ���ǻ�ȡƥ�䣬�����д洢���Ժ�ʹ�á�����ʹ�� "��" �ַ� (|)
		// �����һ��ģʽ�ĸ��������Ǻ����á����磬 'industr(?:y|ies) ����һ���� 'industry|industries' �����Եı��ʽ��
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
		final String strContent = "<H1>Chapter 1 - ����������ʽ</H1>";

		System.out.println(strContent.length());// 28
		// ̰��
		Pattern p1 = Pattern.compile("<.*>");
		Matcher m1 = p1.matcher(strContent);
		m1.find();
//		System.out.println(m1.matches());         //true
		System.out.println("start:" + m1.start() + "\tend:" + m1.end());// start:0 end:28

		System.out.println("------------------------------------");

		// ��̰���������ֻ��Ҫƥ�俪ʼ�ͽ��� H1 ��ǩ������ķ�̰�����ʽֻƥ�� <H1>��
		Pattern p2 = Pattern.compile("<.*?>");
		Matcher m2 = p2.matcher(strContent);
		m2.find();
//		System.out.println(m2.matches());
		System.out.println("start:" + m2.start() + "\tend:" + m2.end());// start:0 end:4

		System.out.println("------------------------------------");

		// \w������ĸ�»���
		Pattern p3 = Pattern.compile("<\\w+?>");
		Matcher m3 = p3.matcher(strContent);
		m3.find();
//		System.out.println(m3.matches());
		System.out.println("start:" + m3.start() + "\tend:" + m3.end());// start:0 end:4
	}

	public static void function3() {
		String strContent = "Chapter     99";

		// \b���ʱ߽� ^��ͷ $�ַ�����β \\s�հ׷�
		Pattern p4 = Pattern.compile("^Chapter\\b\\s*[1-9][0-9]{0,1}$");
		Matcher m4 = p4.matcher(strContent);
		m4.find();
		System.out.println(strContent.length());// 14
		System.out.println("start:" + m4.start() + "\tend:" + m4.end());// start:0 end:14

	}

	public static void function4() {
		String strContent = "Is is the cost of 	  of gasoline going up   	 up";

//		����ı��ʽ������ [a-z]+ ָ���ģ�����һ��������ĸ��������ʽ�ĵڶ������Ƕ���ǰ�������ƥ��������ã��������ʵĵڶ���ƥ�������������ű��ʽƥ�䡣\1 ָ����һ����ƥ���
//		���ʱ߽�Ԫ�ַ�ȷ��ֻ����������ʡ��������� "is issued" �� "this is" ֮��Ĵ��齫������ȷ�ر��˱��ʽʶ��
//		������ʽ�����ȫ�ֱ�� g ָ�����ñ��ʽӦ�õ������ַ������ܹ����ҵ��ľ����ܶ��ƥ�䡣
//		���ʽ�Ľ�β���Ĳ����ִ�Сд i ���ָ�������ִ�Сд��
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

		// \w:������ĸ�»���
//		Pattern p6 = Pattern.compile("^(\\w+)://([^/:]+)(:\\d*)?([^# ]*)");
		Pattern p6 = Pattern.compile("^(\\w+)://([^/:]+):(\\d*)?([^# ]*)");
//		��һ�������ӱ��ʽ���� Web ��ַ��Э�鲿�֡����ӱ��ʽƥ����ð�ź�������б��ǰ����κε��ʡ�
//		�ڶ��������ӱ��ʽ�����ַ�����ַ���֡��ӱ��ʽƥ�� : �� / ֮���һ�������ַ���
//		�����������ӱ��ʽ����˿ںţ����ָ���˵Ļ��������ӱ��ʽƥ��ð�ź��������������֡�ֻ���ظ�һ�θ��ӱ��ʽ��
//		��󣬵��ĸ������ӱ��ʽ���� Web ��ַָ����·���� / ��ҳ��Ϣ�����ӱ��ʽ��ƥ�䲻���� # ��ո��ַ����κ��ַ����С�
//		��������ʽӦ�õ������ URI������ƥ���������������ݣ�
//		��һ�������ӱ��ʽ���� http
//		�ڶ��������ӱ��ʽ���� www.runoob.com
//		�����������ӱ��ʽ���� :80
//		���ĸ������ӱ��ʽ���� /html/html-tutorial.html
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
