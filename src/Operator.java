
public class Operator implements User{

	private String username = "username";
	private String password = "password";
	
	public Operator(String uname, String passwd) {
		username = uname;
		password = passwd;
	}
	
	public void addDocument(Document d) {
		Inventory.getInstance().addDocument(d.getID(), d);
	}
	
	public Document viewDocument(String docID) {
		return Inventory.getInstance().getDocument(docID);
	}
	
	public void removeDocument(Document d) {
		Inventory.getInstance().removeDocument(d.getID());
	}
	
	public void updateDocument(Document d) {
		Inventory.getInstance().addDocument(d.getID(), d);
	}
	
	public boolean authenticate() {
		return Auth.getInstance().validate(this, Auth.Permission.OPERATOR);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	
	
}
