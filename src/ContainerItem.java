import java.util.*;

public class ContainerItem extends Item{
	ArrayList<Item> container = new ArrayList<>();
	
	public ContainerItem() {
		super();
	}
	
	public ContainerItem(String pName, String pType, String pDescription) {
		super(pName, pType, pDescription);
	}
	
	public void addToContainer(Item i) {
		container.add(i);
	}
	
	public Item removeUsingName(String containerName) {
		Item temp = null;
		for (int i = 0; i<container.size(); i++) {
			if(container.get(i).getName().equals(containerName))
				temp = container.get(i);
				container.remove(temp);		
		}
		return temp;
	}
	
	public Item removeUsingIndex(int containerIndex) {
		return container.remove(containerIndex);
	}
	
	public int countContainer() {
		return container.size();
	}
	
	public boolean containsItem(String itemName) {
		for(int i = 0; i<container.size(); i++) {
			if(container.get(i).getName().equals(itemName))
				return true;
		}
		return false;
	}
	
	@Override
	public String getDescription() {
		String listOfItems = "";
		for (Item i : container) {
			listOfItems += "\n" + i.getName();
		}
		return super.getDescription() + listOfItems;
	}
}
