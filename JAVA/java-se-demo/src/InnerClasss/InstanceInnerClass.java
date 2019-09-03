package InnerClasss;

public class InstanceInnerClass {
	private int a =1;
	
	public class Inner{
		public int b =2;
		
		public void print() {
			System.out.println("a = "+a);
			System.out.println("b = "+ b);
		}
	}
	
	public static void main(String[] args) {
		InstanceInnerClass outer =new InstanceInnerClass();
		System.out.println("outer.a = "+outer.a);
		
		InstanceInnerClass.Inner in = outer.new Inner();
		System.out.println("in.b = "+in.b);
	}
	
	public  void method() {
		InstanceInnerClass outer =new InstanceInnerClass();
		System.out.println("outer.a = "+outer.a);
		
		InstanceInnerClass.Inner in = outer.new Inner();
		System.out.println("in.b = "+in.b);
	}

}
