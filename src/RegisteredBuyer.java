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

		return username;
	}

	@Override
	public String getPassword() {

		return password;
	}

	public List<Document> getPromotions() {

		if (!subscribed)
			return null;
		else
			return Inventory.getInstance().getPromotions();

	}

	public void subsribe() {

		subscribed = true;
	}

}
