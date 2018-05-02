import java.util.HashMap;
import java.util.Map;


public class SpeedDatabase {

	HashMap<String, Integer> database = new HashMap<String, Integer>();

	// Default Constructor
	public SpeedDatabase() {

	}

	public void putIntoDatabase(String key, int value) {
		
		database.put(key, value);
		
	}
	
	public void deleteFromDatabase(String key) {
		database.remove(key);
		
	}
	
	public void deleteAllFromDatabase(String key) {
		database.clear();
		
	}
	
	public int searchDatabase(String key) {
		int value = database.get(key);
		return value;
		
	}

	public Map<String,Integer> getAll() {
		return database;
	}
}
