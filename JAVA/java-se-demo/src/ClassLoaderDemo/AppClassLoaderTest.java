package ClassLoaderDemo;

public class AppClassLoaderTest {
	public static void main(String[] args) {
		ClassLoader loader1 = ClassLoader.getSystemClassLoader();
		ClassLoader loader2 = ClassLoader.getSystemClassLoader();
		
		if(loader1 == loader2){
			System.out.println("loader1 == loader2");
		}
		else{
			System.out.println("loader1 != loader2");
		}
	}

}
