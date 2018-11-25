import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BuyerRegistration implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4505921584054004221L;
	private static BuyerRegistration instance = new BuyerRegistration();
	
	private Map<String, RegisteredBuyer> buyers;
	
	public BuyerRegistration() {
		buyers = new HashMap<>();
		Object data = readFromFile(new File("buyers.db"));
		if(data != null)
			buyers = (HashMap<String, RegisteredBuyer>)data;
	}
	
	public static BuyerRegistration getInstance() {
		if(instance == null)
			instance = new BuyerRegistration();
		return instance;
	}


	public void register(String username, String password) {
		RegisteredBuyer b = new RegisteredBuyer(username, password);
		buyers.put(username, b);
	}

	public RegisteredBuyer authenticate(String username, String password) {
		RegisteredBuyer b = buyers.get(username);
		if(b == null)
			return null;
		if(b.getPassword().equals(password))
			return b;
		else
			return null;
		
	}


	public void save() {
		writeToFile(new File("buyers.db"));
	}

	public void writeToFile(File path) {
		try (ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(path))) {
			write.writeObject(buyers);
		} catch (Exception e) {
			// do something
			e.printStackTrace();
		} 
	}

	public static Object readFromFile(File path) {
		Object data = null;

		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path))) {
			data = inFile.readObject();
			return data;
		}catch(FileNotFoundException f) {
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		return data;
	}
}
