package inherit;

public abstract class Developer extends Employee {

	public Developer(String Name, long Id) {
		super(Name, Id);
	}
	public Developer(){
		this("",0);
	}
}
