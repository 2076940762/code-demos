package InnerClasss;

public class extensWithInnercalss extends InstanceInnerClass {
	public class Inner {
		String str = "hello";

		public void print() {
			System.out.println(str);
		}
	}

	public static void main(String[] args) {
		extensWithInnercalss c1 =new extensWithInnercalss();
		c1.method();
		
		extensWithInnercalss.Inner c2=  c1.new Inner();
		c2.print();
		
	}
}
