package inherit;

public class JavaEE extends Developer{

	public JavaEE(String strName,long Id){
		super(strName,Id);
	}
	
	public void  Work(){
		System.out.println("JavaEE coding ");
	}
}
