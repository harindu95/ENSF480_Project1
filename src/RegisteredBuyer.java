import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegisteredBuyer extends Buyer implements User, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -934860995215918715L;
	private String username;
	private String password;
	private boolean subscribed = true;

	public RegisteredBuyer(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void unsubscribe() {
		subscribed = false;
	}

	public boolean isSubscribed() {
		return subscribed;
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

	public List<Document> getPromotions() {
		// TODO Auto-generated method stub
		List<Document> promotions = new ArrayList<>();
		if (!subscribed)
			return null;

		promotions.add(new Document("567", "Fiction", "Artemis Fowl", "mr.unknown"));
		promotions.add(new Document("568", "Fiction", "Artemis Fowl and Code", "mr.unknown"));
		promotions.add(new Document("569", "Fiction", "Artemis Fowl and Lost Continent", "mr.unknown"));
		promotions.add(new Document("570", "Fiction", "Artemis Fowl and Arctic Incident", "mr.unknown"));
		return promotions;
	}

	public void subsribe() {
		// TODO Auto-generated method stub
		subscribed = true;
	}

}
