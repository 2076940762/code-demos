package IODemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStreamdemo {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectOutputStream objWriter = new ObjectOutputStream(new FileOutputStream("e:\\person.txt"));
		Person p1 = new Person("张三",88);
		objWriter.writeObject(p1);		
		objWriter.close();
		
		System.out.println("=======");
		
		ObjectInputStream objReader = new ObjectInputStream(new FileInputStream("e:\\person.txt"));
		Object obj = objReader.readObject();
		System.out.println(obj);
		
		Person p2 = (Person)obj;
		p2.setAge(18);
		p2.setName("老王");
		
		System.out.println(p2);
		objReader.close();
	}
}
