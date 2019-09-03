package InnerClasss;

//¼Ì³ÐÄÚ²¿Àà
public class extendsInnerClass extends InstanceInnerClass.Inner {

	public extendsInnerClass(InstanceInnerClass outer) {
		outer.super();
		System.out.println("construct extendsInnerClass");
	}

	public static void main(String[] args) {
		InstanceInnerClass outer =new InstanceInnerClass();
		extendsInnerClass s = new extendsInnerClass(outer);
		
		s.print();

	}
}
