package IODemo;

import java.io.Serializable;

public class Person implements Serializable  {

	public Person() {
		
	}	
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
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
	private String name;
	private int age;
	
	transient String  sex;//×èÖ¹ÐòÁÐ»¯
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5190857506206500370L;
	
}
