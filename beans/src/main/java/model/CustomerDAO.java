package model;


public interface CustomerDAO {

	public abstract CustomerBean select(String id);

	public abstract boolean update(byte[] password, String email,
			java.util.Date birth, String custid);

}