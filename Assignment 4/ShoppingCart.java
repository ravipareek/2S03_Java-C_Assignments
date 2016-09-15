/*
 * @Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * @MacID: ravip2, shahk24, olivepjm
 * @Student_Number:  1407109, 1419350, 1430273
 * @Description: This class is inherited from user class,
 * Declares ShoppingCart
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart extends User {
	// declaring universal variables
	String username;
	// naming userInterface
	UserInterface uInterface;
	//naming Scanner
	Scanner sc;
	private String[] content; //Array of Items
	private List<Item> cart = new ArrayList<Item>();
	//is there a physical product
	boolean physicalProduct = false;
	//constructor of the shopping cart
	public ShoppingCart(User p, UserInterface u){
		//assigning the username to the user p's username
		username = p.returnUsername();
		//assigning uInterface to be u
		uInterface = u;
	}
	//function to get content
	public String[] getContent(){
		//content is the size of the cart
		content = new String[cart.size()];
		//converting the cart information into an array (will not be the information in the the cart)
		content = cart.toArray(content);
		//returning the content
		return content;
	}
	//add the item to the shopping cart
	public void addItem(Item item, int quantity) throws IOException{
		//boolean to check if the item was already in the cart
		boolean repeat = false;
		//for loop for the size of the cart
		for(int i=0;i<cart.size();i++){
			//if the cart serial number matches the item's serial number
			if (cart.get(i).getSerial().trim().equals(item.getSerial().trim())){
				//change the quantity in the cart to be equal to the the existing quantity and the new amount added
				cart.get(i).setQuantity(cart.get(i).getQuantity()+quantity);
				//set the value of repeated to be true
				repeat = true;
			}
		}
		//if it item is not repeated
		if (!repeat){
			//set the quantity of the item to the amount given
			item.setQuantity(quantity);
			//add the item to the cart
			cart.add(item);
		}
		//if the quantity is greater than 0
		if(quantity>0){
			//writer with file name "Cart_"+username+".txt"
			BufferedWriter sCart = new BufferedWriter(new FileWriter("Cart_" + username + ".txt"));
			//close the file, this will empty the file
			sCart.close();
			//reopen the file and the true parameter will allow appending the file
			sCart = new BufferedWriter(new FileWriter("Cart_" + username + ".txt",true));
			//for loop the size of the cart
			for (int i=0;i<cart.size();i++){
				//setting the formatting for the date
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				//creating the calendar object
				Calendar c = Calendar.getInstance();
				//getting the date and formatting it
				String date = df.format(c.getTime());
				//line with information that is going to be printed to the file
				String line = cart.get(i).getSerial() + ", " + cart.get(i).getName() + ", " + date + ", " + cart.get(i).getQuantity();
				//print line to cart
				sCart.write(line);
				//next line
				sCart.newLine();
			}
			//close file when finished
			sCart.close();
		}
	}
	public void printCart() throws IOException{
		//read the information from the cart file
		readCart();
		//list for all the physical items(ie. Books and CDs)
		List<Item> physical = new ArrayList<Item>();
		//initially set the physical product to false
		physicalProduct = false;
		//loop through the cart
		for(int i=0;i<cart.size();i++){
			//print the headings with certain spacing
			System.out.printf("%-30.30s %-15.15s %-15.15s %-20.20s%n",cart.get(i).getName(),"",cart.get(i).getQuantity(),cart.get(i).getPrice());
			//if the item type is Book or CD
			if (cart.get(i).getType().equals("Book") || cart.get(i).getType().equals("CD")){
				//there is a physical product
				physicalProduct = true;
				//add it to the physical list
				physical.add(cart.get(i));
			}
		}
		//print empty line
		System.out.println();
		//variable will take the sum of all physcial products
		int physicalitemSum = 0;
		//variable for the sum of all products
		int productSum = 0;
		//holds the environmental tax
		double environmentalTax = 0;
		//holds the hst value
		double hst = 0;
		//holds the shipping cost
		double shipping = 0;
		//holds the total costs
		double total;
		//loop for the size of the physical list
		for(int i= 0;i<physical.size();i++){
			//taking the sum of all the physicalitems
			//sum is the previous sum + the next item's price*the quantity of it
			physicalitemSum += physical.get(i).getPrice() * physical.get(i).getQuantity();
		}
		//if there is a physcial product
		if (physicalProduct){
			//environmental tax is 2% of the physical items
			environmentalTax = physicalitemSum*0.02;
			//printing the environmental tax with the spacing
			System.out.printf("%-30.30s %-15.15s %-15.15s %-20.20s%n","Environmental Tax","2%","",environmentalTax);
		}
		//loop for the size of the cart
		for(int i= 0;i<cart.size();i++){
			//take in the sum of the entire cart
			//sum is the previous sum + the next item's price*the quantity of it
			productSum += cart.get(i).getPrice() * cart.get(i).getQuantity();
		}
		//hst is 13% of the productSum
		hst = productSum*0.13;
		//print the hst with the spacing
		System.out.printf("%-30.30s %-15.15s %-15.15s %-20.20s%n","HST","13%","", hst);
		//print empty line
		System.out.println();
		//if there is a physcial product
		if (physicalProduct){
			//the shipping is 10% of the physcial products
			shipping = physicalitemSum*0.10;
			//print the shipping with the spacing
			System.out.printf("%-30.30s %-15.15s %-15.15s %-20.20s%n","Shipping","10%","", shipping);
		}
		//print empty line
		System.out.println();
		//calculate the total being the sum of the environmentalTax, hst, shipping and the product sum
		total = environmentalTax + hst + shipping + productSum;
		//printing a solid line
		System.out.printf("%-30.30s %-15.15s %-15.15s %-10.10s%n","","","","_________________________________________");
		//print the total with the special spacing
		System.out.printf("%-30.30s %-15.15s %-15.15s %-10.10s%n","Total:","","", total);
		//goto confirmation
		confirmation(total);
	}
	private void confirmation(double total) throws IOException {
		//initialize the scanner
		sc = new Scanner(System.in);
		//prompt the user if they want to pay or nah
		System.out.println("Are you sure you want to pay?  Yes or No");
		//get the user's response
		String response = sc.next();
		//if the response is yes but ignore case
		if (response.equalsIgnoreCase("yes")){
			//initializing the reader
			BufferedReader itemsBoughtR;
			//numnber of lines in the file
			int fileLines = 0;
			//string for the line in the file
			String line;
			//try to read the itemsbought.txt
			try {
				//open the ItemsBought.txt file
				itemsBoughtR = new BufferedReader(new FileReader("ItemsBought.txt"));
				//while the line is not null
				while ((line=itemsBoughtR.readLine()) !=null){
					//increment the counter for the number of lines
					fileLines++;
				}
				//close the file
				itemsBoughtR.close();
			}
			//catch if the file is not there 
			catch (FileNotFoundException e) {
				//do nothing if the file doesnt exist
			}
			//open writer to name of ItemsBought.txt with the true parameter to append the text file
			BufferedWriter itemsBoughtW = new BufferedWriter(new FileWriter("ItemsBought.txt",true));
			//if it is a new file
			if(fileLines==0){
				//add the headings
				line = "Username \t Confirmation ID \t Product Name(s) \t Total Cost";
				//increase the number of lines by 1
				fileLines++;
				//write this to the file
				itemsBoughtW.write(line);
				//next line in the file
				itemsBoughtW.newLine();
			}
			//look at size of items bought file size-1 to determine the confirmation id
			String conformationID = "U"	+ String.format("%04d", fileLines-1);
			//initializing the allProductNames variable
			String allProductNames = "";
			//for loop for the size of the cart
			for(int i=0;i<cart.size();i++){
				//allProductNames is all the names in the cart seperated by a comma
				allProductNames = allProductNames + ", " +  cart.get(i).getName();
			}
			//line is the username, conformationID, allProductNames and the totalCost all separated by a tab
			line = username + " \t " + conformationID + " \t " + allProductNames + " \t " + total;
			//write the line to the file
			itemsBoughtW.write(line);
			//goto the next line
			itemsBoughtW.newLine();
			//close the file
			itemsBoughtW.close();
			//print the confirmation number
			System.out.println("Confirmation ID: " + conformationID);
			//if there is a physical product
			if(physicalProduct){
				//print shipping confirmation
				System.out.println("Item(s) shipped to " + username);
			}
			//empty the cart object
			cart.clear();
			// update Inventory after you checkout.
			updateInventory();


		}
		else if (response.equalsIgnoreCase("no")){
			//return to previous menu
		}
		else{
			System.out.println("Not an acceptable answer");
			//goto confirmation again for a proper response
			confirmation(total);
		}
	}
	private void readCart() throws IOException {
		//clear the cart
		cart.clear();
		//open the cart text file for the user
		BufferedReader file = new BufferedReader(new FileReader("Cart_" + username + ".txt"));
		//variable for the line from the file
		String line;
		//counter to follow the while loop for the length of the file
		int counter = 0;
		//loop while the line it reads is not null
		while ((line=file.readLine()) !=null){
			//adding the item to the cart from the first element from the line that was split by ", "
			cart.add(uInterface.getBySerialNumber(Integer.parseInt(line.split(", ")[0])));
			//setting the quantity based on the line(the third element from the split)
			cart.get(counter).setQuantity(Integer.parseInt(line.split(", ")[3]));
			//incrementing the counter
			counter++;
		}
		//closing the read file
		file.close();
	}

	// function to update inventory after checkout
	private void updateInventory() throws IOException {
		//Defining list for serial numbers
		List<Integer> sNo = new ArrayList<Integer>();
		//start to read file Cart_[username].txt
		BufferedReader file = new BufferedReader(new FileReader("Cart_" + username + ".txt"));
		// store one line of the file in variable line
		String line;
		// loop while the line it reads is not null and the name is not yet found
		// gets the serial number and quantity from the cart file
		while ((line=file.readLine()) !=null){
			//splits line at every ", " stores in string array cartInfo
			String[] cartInfo = line.split(", ");
			// Appending the Serial number to sNum list
			sNo.add(Integer.parseInt(cartInfo[0]));
		}
		//close file Cart_[username].txt
		file.close();
		// defining new array list update
		List<Item> update = new ArrayList<Item>();
		// updates the item object quantity 
		for(int i=0;i<cart.size();i++){
			// gets the item baced on the serial number and sets it to temp
			Item temp = uInterface.getBySerialNumber(sNo.get(i));
			// updates the quantity of the "temp" item that was found
			temp.setQuantity(temp.getQuantity()-cart.get(i).getQuantity());
			// adds temp item to the update list
			update.add(temp);
		}

		// passes list update to function updateItem in UserInterface class 
		uInterface.updateItem(update);
		// Create a file type File named Cart_username.txt after checkout
		File f = new File("Cart_" + username + ".txt");
		//delete the file
		f.delete();
	}
}