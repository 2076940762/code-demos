package hello

class Person {

  private var m_id: Int = 0;
  private var m_name: String = null;
  private var m_age: Int = 0;
  private var m_sex: String = null;

  //构造函数
  def this(id: Int, name: String, age: Int, sex: String) {
    this(); //调用主构造函数
    m_id = id;
    m_name = name;
    m_age = age;
    m_sex = sex;
  }

  def this(id: Int, name: String) {
    this(id, name, 0, null);
  }

  def id = m_id;
  def id_=(id: Int) {
    this.m_id = id;
  }

  def getName = m_name;
  def setName(name: String) {
    m_name = name;
  }

  def getAge() = {
    m_age; //return
  }

  def setAge(age: Int) {
    m_age = age;
  }

  def apply(): Unit = {
    println("**********************************************");
  }

  /**
   * update
   */
  def update(attr: String, value: String) = {
    println("~~~~~~~~~~~~update~~~~~~~~~~~")
    if (attr != null && attr.trim().equalsIgnoreCase("id")) {
      m_id = Integer.parseInt(value);
    } else if (attr != null && attr.trim().equalsIgnoreCase("name")) {
      m_name = value;
    } else if (attr != null && attr.trim().equalsIgnoreCase("age")) {
      m_age = Integer.parseInt(value);
    } else if (attr != null && attr.trim().equalsIgnoreCase("sex")) {
      m_sex = value;
    }

  }

}

/**
 * 伴生单例
 * 单例中的字段和方法相当于java类的静态成员
 * applay 方法作为工厂
 */
object Person {

  //返回值可以自动推断出的时候可以不用写
  def apply(id: Int, name: String, age: Int, sex: String) = {
    println(" *******apply ********");
    new Person(id, name, age, sex);
  }

  def apply(id: Int, name: String) = {
    new Person(id, name);
  }

  /**
   * unapply
   */
  def unapply(p: Person) : Option[(Int,String,Int,String)]= {
    if (p == null) {
      None;
    } else {
      Some(p.m_id, p.m_name, p.m_age, p.m_sex);
    }
  }

}

/**
 * 孤立的 单例对象
 */
object Test {

  def main(args: Array[String]): Unit = {

    val p = Person(1, "皮卡丘", 12, "male"); //这里会调用apply方法

    println("id=" + p.id);
    println("name=" + p.getName);
    p.setName("王麻子");
    println("name=" + p.getName);
    println("age=" + p.getAge());

    p(); //调用class中的apply
    println("++++++++++++++++++++++");

    var p2 = Person(111, "李自成");
    p2.apply();
    p2(); //apply

    p2("name") = "崇祯"; //调用update方法
    println("name=" + p.getName);

    println("=======================");
    
    var Person(a,b,c,d)=Person(1111,"周杰伦",44,"male"); //unapply
    println(" "+a+b+c+d)
    
  }

}








