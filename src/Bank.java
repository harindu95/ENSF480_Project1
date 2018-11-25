
public class Bank {
	
	private static Bank instance = new Bank();
	
	public static Bank getInstance() {
		if(instance == null){
			instance = new Bank();
		}
		return instance;
	}

	 boolean pay(String payer, String creditCardNumber) {
		// TODO Auto-generated method stub
		// Connect to bank system and validate information
		return true;
	}
}
