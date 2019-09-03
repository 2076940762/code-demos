package inherit;
/**
 * 
 * @author qingtian
 *
 */
public abstract class Employee {
	
	public Employee(String Name ,long Id)
	{
		this.setName(Name);
		this.setId(Id);
	}

	public abstract void Work();


	
	
	/**
	 * @return the id
	 */
	public long getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		Id = id;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}





	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}



	private String Name;
	private long Id;
	

}
