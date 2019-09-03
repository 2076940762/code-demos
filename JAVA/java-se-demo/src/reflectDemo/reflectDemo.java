package reflectDemo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.Test;

public class reflectDemo {

	@Test
	// 获取Class 对象的三种方法
	public void f1() throws ClassNotFoundException {
		// 方式一: 通过Object类中的getObject()方法
		Person p = new Person();
		Class c1 = p.getClass();
		System.out.println(c1);

		// 方式二: 通过 类名.class 获取到字节码文件对象（任意数据类型都具备一个class静态属性,看上去要比第一种方式简单）。
		Class c2 = Person.class;
		System.out.println(c2);

		// 方式三: 通过Class类中的方法（将类名作为字符串传递给Class类中的静态方法forName即可）。
		Class<Person> c3 = (Class<Person>) Class.forName("reflectDemo.Person");
		System.out.println(c3);

		System.out.println(c1 == c3);
		System.out.println(c1 == c2);
		System.out.println(c1.equals(c2));
		System.out.println(c2.equals(c3));
	}

	@Test
//	public static void main(String[] args) throws Exception {
	public void f2() throws Exception {
		Class<?> classp = Class.forName("reflectDemo.Person");

		// 获取所有的public 修饰的构造方法
		Constructor<?>[] con1 = classp.getConstructors();
		for (Constructor<?> c : con1) {
			System.out.println(c);
		}

		// 获取空参数构造函数
		Constructor<?> con2 = classp.getConstructor();
		Person p = (Person) con2.newInstance();
		p.setAge(18);
		p.setName("123qwer");
		System.out.println(p);

		// 获取有参数构造函数
		Constructor<?> con3 = classp.getConstructor(String.class, int.class);
		Person p1 = (Person) con3.newInstance("asdf", 123);
		System.out.println(p1);

		// 直接构造
		System.out.println("/****************/");
		Class<Person> c1 = (Class<Person>) Class.forName("reflectDemo.Person");
		Person per = c1.newInstance();
		System.out.println(per);

		Method m1 = classp.getMethod("setName", String.class);
		m1.invoke(p1, "abc");
		System.out.println(p1);

		// 访问private成员
		Field f1 = classp.getDeclaredField("age");
		f1.setAccessible(true); // ********************************************* //
		f1.set(p1, 99);
		System.out.println(p1);

	}

	@Test
	// 获取私有构造函数
	public void f3() throws Exception {
		// private Person(int age,String name)
		Class c = Class.forName("reflectDemo.Person");
		Constructor<Person> con = c.getDeclaredConstructor(int.class, String.class);

		con.setAccessible(true);

		Person p = con.newInstance(123, "sdag");
		System.out.println(p);
	}

	@Test
//	获取所有的public字段
	public void f4() throws ClassNotFoundException {
		Class c = Class.forName("reflectDemo.Person");
		Field[] f = c.getFields();

		for (Field field : f) {
			System.out.println(field);
		}

	}

	@Test
//	修改成员变量的值
	public void f5() throws Exception {
		Class<?> c = Class.forName("reflectDemo.Person");
		Person p = (Person) c.newInstance();
		Field f1 = c.getDeclaredField("name");
		f1.setAccessible(true);
		f1.set(p, "王麻子");
		System.out.println(p);
	}

//	返回获取一个方法：
//	public Method getMethod(String name, Class<?>... parameterTypes)
// 			获取public 修饰的方法
//	public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
// 			获取任意的方法，包含私有的
//	参数1: name 要查找的方法名称； 参数2： parameterTypes 该方法的参数类型
//		返回获取多个方法：
//		public Method[] getMethods() 获取本类与父类中所有public 修饰的方法
//		public Method[] getDeclaredMethods() 获取本类中所有的方法(包含私有的)

	@Test
//获取成员方法
	public void f6() throws Exception {
		Class<?> class1 = Class.forName("reflectDemo.Person");
		Method[] methods = class1.getMethods();
		for (Method method : methods) {
			System.out.println(method);
		}
	}

	@Test
	// 获取指定方法运行
	public void f7() throws Exception {
		Class<Person> class1 = (Class<Person>) Class.forName("reflectDemo.Person");
		Person person = class1.newInstance();
		Method method = class1.getMethod("setName", String.class);
		method.invoke(person, "qweerr");
		System.out.println(person);
	}

	@Test
	// 反射与泛型
	public void f8() throws Exception, SecurityException {
		ArrayList<String> arr1 = new ArrayList<String>();
		arr1.add("hello");
		Class c1 = arr1.getClass();
		Method method = c1.getMethod("add", Object.class);
		method.invoke(arr1, 11.234);
		method.invoke(arr1, 11345);
		method.invoke(arr1, 11.6736);
		System.out.println(arr1);

	}
}
