import java.io.Serializable;

public class Document implements Serializable{
	
	private static final long serialVersionUID = 23244;
	
	private String id;
	private String type;
	private String description;
	private String author;
	
	public Document(String id, String type, String description, String author) {
		this.id = id;
		this.type = type;
		this.description = description;
		this.author = author;
	}
	
	public String getID() {
		return id;
	}
	public void setID(String id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return String.format("ID: %s\n"
				    + "Type: %s\n"
				    + "Descirption: %s \n"
				    + "Author: %s\n", id, type, description, author);
	}
}
