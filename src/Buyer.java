import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Buyer {

	ShoppingCart cart;
	
	Buyer(){
		cart = new ShoppingCart();
	}
	
	
	public List<Document> search(String keyword) {
		return Inventory.getInstance().search(keyword);
	}
	
	public RegisteredBuyer register(String username, String password){
		BuyerRegistration.getInstance().register(username, password);
		return BuyerRegistration.getInstance().authenticate(username, password);
	}

	public void addToCart(Document document, int num) {
		// TODO Auto-generated method stub
		cart.add(document, num);
	}


	public void clearCart() {
		// TODO Auto-generated method stub
		cart = new ShoppingCart();
	}
}
