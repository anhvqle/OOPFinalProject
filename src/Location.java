import java.util.*;

public class Location {
	private String name;
	private String description;
	ArrayList<Item> collection = new ArrayList<>();
	HashMap<String, Location> direction = new HashMap<>();
	
	public Location() {}
	
	/**
	 * @param name
	 * @param description
	 */
	public Location(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	/**
	 * @param Item i to add to the collection
	 */
	public void addToCollection(Item i) {
		collection.add(i);
	}
	
	public boolean containsItem(String itemsName) {
		for(Item i: collection) {
			if(i.getName().equals(itemsName))
				return true;
		}
		return false;
	}
	
	public Item getItem(String itemsName) {
		for (Item i: collection) {
			if(i.getName().equals(itemsName)) {
				return i;
			}
		}
		return null;
	}
	
	public int countItem() {
		return collection.size();
	}
	
	public Item remove(String itemsName) {
		Item temp = null;
		for (int i = 0; i<collection.size(); i++) {
			if(collection.get(i).getName().equals(itemsName)) {
				temp = collection.get(i);
				collection.remove(i);
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
