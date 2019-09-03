package InnerClasss;

public class StaticInnerClass {
	public static int a =1;
	public int b =2;
	
	public static class Inner{
		public static int c =3;
		public int d =4;
		
//		public static void main(String[] args) {
//			System.out.println("hello");
//		}
		
	}
	
	public static void main(String [] args) {
		System.out.println("StaticInnerClass.a = "+StaticInnerClass.a );
		System.out.println("StaticInnerClass.Inner.c = "+StaticInnerClass.Inner.c);
		
		StaticInnerClass outer = new StaticInnerClass();
		System.out.println("outer.b = "+outer.b);
		
		StaticInnerClass.Inner In =new StaticInnerClass.Inner(); 
		System.out.println("In.d = "+In.d);
	}

}
