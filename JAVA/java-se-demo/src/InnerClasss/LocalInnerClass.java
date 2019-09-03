package InnerClasss;

public class LocalInnerClass {
	public static int a = 1;
	public int b = 2;

	public void method() {
		class A {
//			public static int c=1
			public int d = a;
		}
		A a= new A();
		System.out.println("a.d =" +a.d);
	}

	public static void main(String[] args) {
		LocalInnerClass outer =new LocalInnerClass();
		outer.method();
		System.out.println("outer.b ="+outer.b);
	}
}
