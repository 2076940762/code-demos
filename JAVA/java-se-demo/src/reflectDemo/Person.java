package reflectDemo;

public class Person {

	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public  Person() {	
		name="";
	}
	
	private Person(int age,String name) {
		super();
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	private String name;
	private int age;
	
	public String str="hello";
	
}

