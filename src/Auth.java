import java.util.HashMap;
import java.util.Map;

public class Auth {

	enum Permission {
		OPERATOR
	};

	private Map<String, String> users;
	private Map<String, Permission> permissions;
	private static Auth instance = new Auth();

	private Auth() {
		users = new HashMap<>();
		permissions = new HashMap<>();
		users.put("test", "passwd");
		permissions.put("test", Permission.OPERATOR);
	}

	public static Auth getInstance() {
		if (instance == null)
			instance = new Auth();
		return instance;
	}

	public boolean validate(User user, Permission p) {
		boolean valid = true;
		String password = ((String) users.get(user.getUsername()));
		if (password == null)
			return false;
		valid = password.equals(user.getPassword());
		valid = valid && (permissions.get(user.getUsername()) == p);
		return valid;

	}

	public void addUser(User user, Permission p) {
		users.put(user.getUsername(), user.getUsername());
		permissions.put(user.getUsername(), p);
	}

}
