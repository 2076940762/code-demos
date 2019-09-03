package newIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class objStream {
	public static void main(String[] args) throws Exception {
		FileOutputStream fileOut = new FileOutputStream("./a.obj");
		ObjectOutput objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject("hello world");
		objOut.writeObject(new Date());
		objOut.writeObject(new Student("¿œÕı", 201224, 25, 'M'));
		objOut.close();
		
		FileInputStream fileIn = new FileInputStream("./a.obj");
		ObjectInputStream objIn = new ObjectInputStream(fileIn);
		String str = (String)objIn.readObject();
		System.out.println(str);
		
		Date d=(Date)objIn.readObject();
		System.out.println(d);
		
		Student stu = (Student)objIn.readObject();
		System.out.println(stu);
		objIn.close();
	}
	
	public static class Student implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 3930595657011560855L;
		private String name ;
		private long number ;
		private int age;
		private char sex;
		
		public Student(String name,long number,int age,char sex) {
			super();
			this.name = name;
			this.age = age;
			this.number=number;
			this.sex=sex;
		}
		
		@Override
		public String toString() {
			return "Student [name=" + name + ", number=" + number + ", age=" + age + ", sex=" + sex + "]";
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public long getNumber() {
			return number;
		}
		public void setNumber(long number) {
			this.number = number;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public char getSex() {
			return sex;
		}
		public void setSex(char sex) {
			this.sex = sex;
		}
		
	}

}
