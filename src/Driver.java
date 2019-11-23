import java.util.Scanner;

import javax.swing.JFrame;

public class Driver {
	// character’s current Location
	public static Location myLocation;
	public static Location secondLoc;

	// store the Items that your character is currently carrying
	public static ContainerItem myInventory = new ContainerItem("MyBag", "Bag", "itemRemoved");

	public static void main(String[] args) {
		
//		Setting up the Graphic Design & User Interface
		JFrame window = new JFrame("Adventure Game");
		window.setSize(640,480);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

//		Declaring the first location
		Location home = new Location("Area51", "Home of the Aliens");
		Item i1 = new Item("Cleaver", "Knife", "Rectangular Blade");
		Item i2 = new Item("Handgun", "Gun", "Default Gun");
		Item i3 = new Item("SmallKit", "First Aid Kit", "10 extra bloods");

		home.addToCollection(i1);
		home.addToCollection(i2);
		home.addToCollection(i3);

		ContainerItem a1 = new ContainerItem("DefaultVault", "Vault", "Contains Diamonds");
		a1.addToContainer(new Item("WhiteDiamond", "Diamond", "White Diamond"));
		a1.addToContainer(new Item("BlackDiamond", "Diamond", "Black Diamond"));

		ContainerItem a2 = new ContainerItem("DefaultChest", "Chest", "Contains Grenades");
		a2.addToContainer(new Item("TearGas", "Grenades", "Chemical Grenades"));
		
		home.addToCollection(a1);
		home.addToCollection(a2);
		
		myLocation = home;
		
//		Declaring a second location
		Location city = new Location("Los Angeles, CA", "Civilized Area");	
		city.addToCollection(new Item("iPhone", "Phone", "iPhone 11 Pro Max"));
		city.addToCollection(new Item("Sushi", "Food", "Edible food"));
		city.addToCollection(new Item("Suit", "Clothing", "Find some clothes"));
		
//		Declaring a third location
		Location forest = new Location("Amazon", "Amazon Rainforest");
		forest.addToCollection(new Item("Jaguar", "Animal", "Fast animal"));
		forest.addToCollection(new Item("Anaconda", "Snake", "Dangerous Animal"));
		forest.addToCollection(new Item("Centipede", "Animal", "Poisonous Animal"));
		
//		Declaring a forth location
		Location beach = new Location("Miami Beach", "Beach");
		beach.addToCollection(new Item("YellowSand", "Sand", "Sand on the beach"));
		beach.addToCollection(new Item("Goldfish", "Fish", "Fish on the beach"));
		beach.addToCollection(new Item("Beach Umbrella", "Umbrella", "Umbrella on the beach"));
		
//		Declaring a fifth location
		Location field = new Location("Indiana", "Field");
		field.addToCollection(new Item("Corn", "Food", "Indiana Corn Field"));
		field.addToCollection(new Item("Shovel", "Tool", "Can be used as weapon"));		

//		Connection multiple locations
		
//				Forest
//				  |
//		Home --	City --	Field
//				  |
//				Beach
		
		home.direction.put("right", city);
		city.direction.put("left", home);
		
		forest.direction.put("down", city);
		city.direction.put("up", forest);
		
		city.direction.put("down", beach);
		beach.direction.put("up", city);
		
		city.direction.put("right", field);
		field.direction.put("left", city);
		

		Scanner s = new Scanner(System.in);
		while (true) {
			String userText = s.nextLine();
			if (userText.equals("quit")) {
				System.exit(0);
			} 
			else if (userText.equals("look")) {
				System.out.println("Your current location: " + myLocation.getDescription());
				System.out.println("List of items in this location: ");
				for (Item i : myLocation.collection) {
					System.out.println("- "+i.getName());
				}
			} 
			else if (userText.split(" ")[0].equals("examine")) {
				String name = userText.substring(8);
				for (Item i : myLocation.collection) {
					if (i.getName().equals(name)) {
						System.out.println("Name: " + name + "\nDescription: " + i.getDescription());
					}
				}
			} 
			
			//take [name] from [container]
			else if (userText.split(" ").length == 4 && userText.split(" ")[0].equals("take")
					&& userText.split(" ")[2].equals("from")) {
				
				String name = userText.split(" ")[1];
				String containerName = userText.split(" ")[3];
				if(myLocation.containsItem(containerName)) {
					
					//Get the container that has the item that needs to be taken
					ContainerItem container = (ContainerItem)myLocation.getItem(containerName);
					
					if(container.containsItem(name)) {
						//Add to myInventory the item in the container found above 
						myInventory.addToContainer(container.removeUsingName(name));
					}
					else {
						System.out.println("Item not found in the container");
					}
				}
				else {
					System.out.println("Container not found in this location");
				}
				
			} 
			else if (userText.split(" ").length == 2 && userText.split(" ")[0].equals("take")) {
				String name = userText.split(" ")[1];
				if(myLocation.containsItem(name)) {
					Item pickUpItem = myLocation.remove(name);
					myInventory.addToContainer(pickUpItem);
				}
				else {
					System.out.println("Cannot find this item in this location");
				}
			} 
			
			else if (userText.split(" ")[0].equals("drop")) {
				String name = userText.split(" ")[1];
				if(myInventory.containsItem(name)) {
					myLocation.addToCollection(myInventory.removeUsingName(name));
				}
				else {
					System.out.println("This item is not in your bag");
				}
			}
			
			else if (userText.split(" ").length == 4 && userText.split(" ")[0].equals("put")
					&& userText.split(" ")[2].equals("in")) {
				String name = userText.split(" ")[1];
				String containerName = userText.split(" ")[3];
				
				if(myInventory.containsItem(name)) {
					if(myLocation.containsItem(containerName)) {
						ContainerItem temp = (ContainerItem)myLocation.getItem(containerName);
						temp.addToContainer(myInventory.removeUsingName(name));
					}
					else {
						System.out.println("The container is not in this location");
					}
				}
				else {
					System.out.println("The item is not in your inventory");
				}
			}
			else if(userText.equals("inventory")){
				System.out.println("Items in your inventory are: ");
				for(Item i: myInventory.container) {
					System.out.println("- " + i.getName());
				}
			}
			
			else if(userText.split(" ").length == 2 && userText.split(" ")[0].equals("go")) {
				String goTo = userText.split(" ")[1];
				if(myLocation.direction.containsKey(goTo)) {
					myLocation = myLocation.direction.get(goTo);
				}
				else {
					System.out.println("The provided direction is not valid");
				}
			}
			else if(userText.equals("help")) {
				printCommands();
			}

			else {
				System.out.println("I don’t know how to do that");
			}
		}
	}
	public static void printCommands() {
		System.out.println("Please type one of the following: ");
		System.out.println("1. look: Get current location’s description and the names of the items found at the current location.");
		System.out.println("2. examine [NAME]:get the item with the given name from the current location and print the Item’s name and description");
		System.out.println("3. take [NAME]: remove this item from the current location and add it to the character’s inventory");
		System.out.println("4. drop [NAME]: remove the item from the character’s inventory and add it to the current location");
		System.out.println("5. take [NAME] from [CONTAINER]: remove the item from the [container] and add it to the character’s inventory");
		System.out.println("6. put [NAME] in [CONTAINER]:  remove the item from the character’s inventory and add it to the specified [container] at the character’s current location.");
		System.out.println("7. inventory:  List the items in your character’s inventory.");
		System.out.println("8. go [DIRECTION]: go in any direction represented as a String");
		System.out.println("9. quit: exit and the program ends.");
	}
}
