import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



public class ShoppingCart {
	final double DOCUMENT_PRICE = 20.0;
	Map<Document, Integer> cart;

	ShoppingCart() {
		cart = new HashMap<>();
	}

	void add(Document d, int num) {
		cart.put(d, num);
	}

	Order getOrder() {
		Order order = new Order(0);
		Set<Document> keys = cart.keySet();
		Iterator<Document> it = keys.iterator();
		while(it.hasNext()) {
			order.price += DOCUMENT_PRICE * cart.get(it.next());
		}
		return order;
	}
	
	boolean isEmpty() {
		return cart.isEmpty();
	}
}

class Order {
	double price = 0;

	Order(double price) {
		this.price = price;
	}
	
	boolean pay(String payer, String creditCardNumber) {
		return Bank.getInstance().pay(payer, creditCardNumber);
	}
}