import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Inventory {

	private static Inventory instance = new Inventory();
	Map<String, Document> documents;

	private Inventory() {
		documents = new HashMap<>();
		Object data = readFromFile(new File("documents.db"));
		if (data != null) {
			documents = (HashMap<String, Document>) data;
		}
	}

	public Document getDocument(String id) {
		return documents.get(id);
	}

	public void addDocument(String id, Document d) {
		documents.put(id, d);
	}

	public void removeDocument(String id) {
		documents.remove(id);
	}

	public static Inventory getInstance() {
		if (instance == null) {
			instance = new Inventory();
		}
		return instance;
	}

	public List<Document> search(String keyword) {
		ArrayList<Document> list = new ArrayList<>();
		Iterator<Document> it = documents.values().iterator();
		for (; it.hasNext();) {
			Document d = it.next();
			String txt = d.getAuthor() + d.getID() + d.getDescription() + d.getType();
			txt = txt.toLowerCase().trim();
			keyword = keyword.toLowerCase();
			if (txt.contains(keyword))
				list.add(d);
		}
		return list;
	}

	public void save() {
		writeToFile(new File("documents.db"));
	}

	public void writeToFile(File path) {
		try (ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(path))) {
			write.writeObject(documents);
		} catch (Exception e) {
			// do something
			e.printStackTrace();
		}
	}
	
	public List<Document> getPromotions() {
		// TODO Auto-generated method stub
		List<Document> promotions = new ArrayList<>();
		
		promotions.add(new Document("567", "Fiction", "Artemis Fowl", "mr.unknown"));
		promotions.add(new Document("568", "Fiction", "Artemis Fowl and Code", "mr.unknown"));
		promotions.add(new Document("569", "Fiction", "Artemis Fowl and Lost Continent", "mr.unknown"));
		promotions.add(new Document("570", "Fiction", "Artemis Fowl and Arctic Incident", "mr.unknown"));
		return promotions;
	}

	public static Object readFromFile(File path) {
		Object data = null;

		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path))) {
			data = inFile.readObject();
			return data;
		} catch (FileNotFoundException f) {

		} catch (Exception e) {
			e.printStackTrace();

		}
		return data;
	}
}
