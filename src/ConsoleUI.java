import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

	public int nextInt(Scanner in) {
		while (true) {
			try {
				int item = in.nextInt();
				in.nextLine();
				return item;
			} catch (InputMismatchException e) {
				in.nextLine();
				System.out.println("Invalid input. Please enter a integer");
			}
		}
	}

	public void viewDocument(Scanner in, Operator o) {

		System.out.print("Document ID: ");
		String id = in.nextLine().trim();

		Document d = o.viewDocument(id);
		if (d != null)
			System.out.println(d.toString());
		else
			System.out.println("No document with id:" + id + " exits.");

	}

	public void removeDocument(Scanner in, Operator o) {

		System.out.print("Document ID: ");
		String id = in.nextLine().trim();

		Document d = o.viewDocument(id);

		if (d != null) {
			o.removeDocument(d);
			System.out.println("Document removed.");
		} else
			System.out.println("No document with id:" + id + " exits.");

	}

	public void updateDocument(Scanner in, Operator o) {

		System.out.println("Enter document....");
		System.out.print("Document ID: ");
		String id = in.nextLine().trim();
		System.out.print("Type: ");
		String type = in.nextLine().trim();
		System.out.print("Author: ");
		String author = in.nextLine().trim();
		System.out.print("Description: ");
		String description = in.nextLine().trim();
		Document d = new Document(id, type, description, author);
		o.addDocument(d);

	}

	public void addDocument(Scanner in, Operator o) {

		System.out.println("Enter document details");
		System.out.print("Document ID: ");
		String id = in.nextLine().trim();
		System.out.print("Type: ");
		String type = in.nextLine().trim();
		System.out.print("Author: ");
		String author = in.nextLine().trim();
		System.out.print("Description: ");
		String description = in.nextLine().trim();
		Document d = new Document(id, type, description, author);
		o.addDocument(d);

	}

	public Operator operatorLogin(Scanner in) {

		for (int i = 0; i < 5; i++) {
			System.out.println("Login");
			System.out.print("username: ");
			String username = in.nextLine().trim();
			System.out.print("password: ");
			String password = in.nextLine().trim();
			Operator o = new Operator(username, password);
			boolean valid = o.authenticate();
			if (valid) {

				return o;
			} else {
				System.out.println("Invalid credentials!");
			}
		}
		System.out.println("Authentication failed.");

		return null;
	}

	public void handleOperator(Scanner in, Operator o) {

		boolean loop = true;
		while (loop) {

			System.out.println("Select one of the following options");
			System.out.println("1.View document");
			System.out.println("2.Add document");
			System.out.println("3.Remove document");
			System.out.println("4.Update document");
			System.out.println("5.Exit");

			int option = nextInt(in);

			switch (option) {
			case 1:
				viewDocument(in, o);
				break;
			case 2:
				addDocument(in, o);
				break;
			case 3:
				removeDocument(in, o);
				break;
			case 4:
				updateDocument(in, o);
				break;
			case 5:
				loop = false;
				break;
			default:
				break;
			}
		}

	}

	public RegisteredBuyer buyerLogin(Scanner in) {

		for (int i = 0; i < 5; i++) {
			System.out.println("Login");
			System.out.print("username: ");
			String username = in.nextLine().trim();
			System.out.print("password: ");
			String password = in.nextLine().trim();
			RegisteredBuyer b = BuyerRegistration.getInstance().authenticate(username, password);
			if (b != null) {

				return b;
			} else {
				System.out.println("Invalid credentials!");
			}
		}
		System.out.println("Authentication failed.");

		return null;
	}

	public void display() {
		Scanner in = new Scanner(System.in);
		System.out.println("Select one of the options:");
		System.out.println("1. Operator login");
		System.out.println("2. Registered buyer login");
		System.out.println("3. Just continue as a normal buyer");

		int option = nextInt(in);

		switch (option) {
		case 1:
			Operator o = operatorLogin(in);
			handleOperator(in, o);
			break;
		case 2:
			RegisteredBuyer b = buyerLogin(in);
			handleRegisteredBuyer(in, b);
			break;
		case 3:
			handleBuyer(in);
		case 4:
		default:
			break;
		}

		Inventory.getInstance().save();
		BuyerRegistration.getInstance().save();
		System.out.println("System exiting...");
		in.close();

	}

	private void handleBuyer(Scanner in) {
		Buyer b = new Buyer();

		// TODO Auto-generated method stub
		while (true) {

			System.out.println("Select one of the options:");
			System.out.println("1. Search for item");
			System.out.println("2. Register");
			System.out.println("3. Proceed to Payment");
			System.out.println("4. Exit");
			int option = nextInt(in);

			switch (option) {
			case 1:
				seachItem(in, b);
				break;
			case 2:
				registerBuyer(in);
				break;
			case 3:
				handlePayment(in, b);
				break;
			case 4:

				return;
			}
		}

	}

	private void handlePayment(Scanner in, Buyer b) {

		Order order = b.cart.getOrder();
		if (b.cart.isEmpty()) {
			System.out.println("No items in cart.!");
			return;
		}

		System.out.printf("Total price: %.2f USD\n", order.price);
		System.out.print("Payer name: ");
		String name = in.nextLine().trim();
		System.out.print("Card number: ");
		String cardNumber = in.nextLine().trim();
		boolean valid = order.pay(name, cardNumber);
		if (valid) {
			System.out.println("Transaction completed!");
			b.clearCart();
		} else {
			System.out.println("Error !!!. Transaction failed!");
		}

	}

	private void seachItem(Scanner in, Buyer b) {
		// TODO Auto-generated method stub

		System.out.print("\nEnter Keyword:");
		String keyword = in.nextLine().trim();
		List<Document> list = b.search(keyword);
		int i = 0;
		for (Document d : list) {

			System.out.println((i + 1) + ". " + d.toString());
			i++;
		}

		if (list.isEmpty()) {
			System.out.println("No items found");
			return;
		}

		// Add item to order.
		System.out.print("\nAdd items to shopping cart(y/n): ");
		String yOrn = in.nextLine().trim().toLowerCase();
		if (yOrn.equals("y")) {
			System.out.print("Item number: ");
			int item = nextInt(in);
			System.out.print("Number of items: ");
			int numItems = nextInt(in);
			b.addToCart(list.get(item - 1), numItems);
		} else {

			return;
		}

	}

	private void registerBuyer(Scanner in) {
		// TODO Auto-generated method stub

		System.out.print("\nEnter username:");
		String username = in.nextLine().trim();
		System.out.print("\nEnter password:");
		String password = in.nextLine().trim();
		RegisteredBuyer r = new RegisteredBuyer(username, password);
		r.register(username, password);
		handleRegisteredBuyer(in, r);
	}

	private void handleRegisteredBuyer(Scanner in, RegisteredBuyer b) {

		// TODO Auto-generated method stub
		while (true) {

			System.out.println("Select one of the options:");
			System.out.println("1. Search for item");
			if (b.isSubscribed()) {
				System.out.println("2. View Promotions");
				System.out.println("3. Unsubscribe");
			} else {
				System.out.println("4. Subscribe");
			}

			System.out.println("5. Proceed to Payment");
			System.out.println("7. Exit");
			int option = nextInt(in);

			switch (option) {
			case 1:
				seachItem(in, b);
				break;
			case 2:
				if (b.isSubscribed())
					showPromotions(in, b);
				break;
			case 3:
				if (b.isSubscribed()) {
					b.unsubscribe();
					System.out.println("You are unsubsribed from promotions.");
				}
				break;
			case 4:
				if (!b.isSubscribed()) {
					b.subsribe();
					System.out.println("You have subscribed into our promotions.");
				}
				break;
			case 5:
				handlePayment(in, b);
				break;
			case 7:
				return;

			default:

				break;
			}
		}
	}

	private void showPromotions(Scanner in, RegisteredBuyer b) {
		// TODO Auto-generated method stub

		List<Document> list = b.getPromotions();
		int i = 0;
		if (!b.isSubscribed()) {
			System.out.println("You have unsubscribed from promotions.");
			return;
		}

		for (Document d : list) {

			System.out.println((i + 1) + ". " + d.toString());
			i++;
		}

		if (list.isEmpty()) {
			System.out.println("No items found");
			return;
		}

		// Add item to order.
		System.out.print("\nAdd items to shopping cart(y/n): ");
		String yOrn = in.nextLine().trim().toLowerCase();
		if (yOrn.equals("y")) {
			System.out.print("Item number: ");
			int item = nextInt(in);
			System.out.print("Number of items: ");
			int numItems = nextInt(in);
			b.addToCart(list.get(item - 1), numItems);
		} else {

			return;
		}
	}
}
