package transaction;

public interface AccountDao {

	public void transferIn(String name,Double money);

	public void transferOut(String name,Double money);

}
