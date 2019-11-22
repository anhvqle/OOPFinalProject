
public class Item {
	private String name;
	private String type;
	private String description;
	
	public Item() {}
	
	/**
	 * @param name
	 * @param type
	 * @param description
	 */
	public Item(String name, String type, String description) {
		this.name = name;
		this.type = type;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return ("Items: \n\tName: " + name + "\n\tType: " + type + "\n\tDescription: " + description);
		
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
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
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
