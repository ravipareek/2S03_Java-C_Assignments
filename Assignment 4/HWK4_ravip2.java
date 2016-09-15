/*
 * @Name: Pareekshit Ravi,Kunal Shah, Pedro Oliveira
 * @MacID: ravip2, shahk24, olivepjm
 * @Student_Number:  1407109, 1419350, 1430273
 * @Description: A program that creates a market place, with a user login, ADMIN controls, shop for items, add them to a cart, and checkout	 
 * 				 Bonus was done with a showing previous items  
 * 				 Bonus was done with making an ADMIN user
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HWK4_ravip2 {
	static Scanner sc;
	static UserInterface userInterface;
	static ShoppingCart shoppingCart;
	static List<String> allUsers;
	static User person;
	public static void main(String[] args) throws IOException {
		boolean admin = false;
		//initializing the keyboard input
		sc =  new Scanner(System.in);
		String username;
		//boolean to see if the user has signed in
		boolean signedIn = false;
		//boolean to check if the name is in the database
		boolean userExists = false;
		//reader to read the Users.txt file
		BufferedReader user;
		//variable to hold the line that it will read
		String line;
		//list to have all the lines from the Users.txt file
		allUsers = new ArrayList<String>();
		//creating userInterface object
		userInterface = new UserInterface();
		//keep looping till the user successfully logs in
		while (!signedIn){
			//prompting for the input
			System.out.println("1. Sign in");
			System.out.println("2. Sign up");
			System.out.print("Choose your option: ");
			try{
				//switch based in the integer typed
				switch (sc.nextInt()) {
				//if the integer is 1
				case 1:
					// add 10 line page break to indicate new page
					userInterface.changeCurrentPage(3);
					
					//prompting for the username
					System.out.print("Enter your username: ");
					//saving the name to a variable
					username = sc.next();
					user = new BufferedReader(new FileReader("Users.txt"));
					//loop while the line it reads is not null and the name is not yet found
					while ((line=user.readLine()) !=null && !userExists){
						allUsers.add(line);
						//if the line is the same as the username typed
						if(line.equalsIgnoreCase(username)){
							//changing the boolean to say that username is found
							userExists = true;
							username = line;
						}
						if (line.split(",")[0].equals("ADMIN") && username.equals("ADMIN")){
							admin = true;
							userExists = true;
						}
					}
					//if the username is found in the file
					if (userExists) {
						//changing boolean to say user signed in
						signedIn = true;
						person = new User();
						person.getUsername(username);
						//print welcome
						System.out.println("Hello "+username);
						if (admin){
							adminLogin();
						}
					}
					//username was not found in the file
					else{
						//print no access
						System.out.println("No Access");
						//adding 10 new lines to indicate new page
						userInterface.changeCurrentPage(4);
					}
					//close the file
					user.close();
					//break from the switch
					break;
				// if the integer is 2
				case 2:
					//adding 10 new lines to indicate new page
					userInterface.changeCurrentPage(2);
					//prompt user for new username
					System.out.println("Choose your username: ");
					//save the entered string in variable
					username = sc.next();
					user = new BufferedReader(new FileReader("Users.txt"));
					//loop while the line it reads is not null and the name is not yet found
					while ((line=user.readLine()) !=null && !userExists){
						allUsers.add(line);
						//if the line is the same as the username typed
						if(line.split(",")[0].equals(username))
							//changing the boolean to say that username is found
							userExists = true;
					}
					//if the user doesnt exist
					if(!userExists){
						//adding the new username to the second last index of the list
						allUsers.add(allUsers.size()-1,username);
						//writer to write to the Users.txt file, false argument empties the file
						BufferedWriter outUser = new BufferedWriter(new FileWriter("Users.txt",false));
						outUser.close();
						//writer to write to the Users.txt file, true argument allows to append file
						outUser = new BufferedWriter(new FileWriter("Users.txt",true));
						//for loop iterating through full list of all usernames registered
						for(int i=0;i<allUsers.size();i++){
							//writing the username to the file
							outUser.write(allUsers.get(i));
							outUser.newLine();
						}
						//printing confirmation
						System.out.println("Username successfully added");
						//clear the allUsers list
						allUsers.clear();
						//closing file
						outUser.close();
						//breaking from the switch statement
						break;
					}
					//user exists in file
					else{
						//printing message to screen
						System.out.println("Username is already registered");
						//break from the case
						break;
					}
				//if entered a invalid number
				default: 
					//go back to main
					userInterface.changeCurrentPage(1);
					main(null);
				}					
			}
			//if the input is not an integer, try again
			catch(Exception e){
				//go back to the start of main
				main(null);
			}
		}
		//if the user is not an admin
		if(!admin){
			//create a shopping cart object
			shoppingCart = new ShoppingCart(person, userInterface);
			//goto page 5
			p5();
		}
	}

	private static void adminLogin() throws IOException {
		//prompting the user to enter password
		System.out.print("Please enter your password: ");
		//getting password from user
		String password = sc.next();
		//if password is the same as the password in the text file
		//the password in the file is the last line, and is separated by the colon
		if(password.equals(allUsers.get(allUsers.size()-1).split(",")[1])){
			//admin has signed in
			adminSignedIn();
		}
		//wrong password
		else{
			//tell user wrong password and give options
			System.out.println("Password was incorrect, enter 0 to try again");
			System.out.println("Enter -1 to return to signin with different username");
			//try the inputs the user enters
			try{
				//switch case for the user's int input
				switch (sc.nextInt()){
				//if user inputed -1
				case -1:
					//go back to main
					main(null);
					//break from the statement
					break;
				//if anything other than -1
				//just in case the input is not 0, but they wanted to retry
				default:
					//go back to adminLogin
					adminLogin();
					//break from the statement
					break;
				}
			}
			//if the inputs are not integers
			catch(Exception e){
				//inform the user to enter a number
				System.out.println("Please enter a number.");
				//go back to adminLogin
				adminLogin();
			}
		}
	}
	private static void adminSignedIn() throws IOException{
		// adding 10 new lines to indicate new page
		userInterface.changeCurrentPage(11);
		//creating shopping cart
		shoppingCart = new ShoppingCart(person, userInterface);
		//printing the options
		//printing admin has signed in
		System.out.println("ADMIN Signed In");
		//prints view items by price
		System.out.println("1. View Items By Price");
		//prints view items by name
		System.out.println("2. View Items By Name");
		//prints add items
		System.out.println("3. Add Items");
		//prints change password
		System.out.println("4. Change Password");
		//prints sign out
		System.out.println("5. Sign Out");
		//prompts user for option
		System.out.print("Choose your option: ");
		//switch case based on user's input
		switch (sc.nextInt()){
		//if input is 1
		case 1:
			// adding 10 new lines to indicate new page
			userInterface.changeCurrentPage(11);
			//get the audio products from file
			userInterface.getAudioProducts();
			//get the readables from file
			userInterface.getReadables();
			//print the items sorted by price
			userInterface.showItemsByPrice();
			//prompt the user to press enter to continue
			System.out.println("Press enter to continue");
			//if the user sends an input, works with enter, or any character and then enter
			try{
				//read the input from the user
				System.in.read();
				//once read an input, go to adminSignedIn page for options
				adminSignedIn();
			}
			//catch if could not get input
			catch (Exception e){
				
			}
			//break from statement
			break;
		//if input was 2
		case 2:
			// adding 10 new lines to indicate new page
			userInterface.changeCurrentPage(11);
			//get the audio products from file
			userInterface.getAudioProducts();
			//get the readables from file
			userInterface.getReadables();
			//print the items sorted by name
			userInterface.showItemsByName();
			//prompt the user to press enter to continue
			System.out.println("Press enter to continue");
			//if the user sends an input, works with enter, or any character and then enter
			try{
				//read the inputs from the user
				System.in.read();
				//once read an input, go to adminSignedIn page for options
				adminSignedIn();
			}
			//catch if could not get input
			catch (Exception e){
			}
			//break from statement
			break;
		//if input was 3
		case 3:
			//add items function
			addItems();
			//break from statement
			break;
		//if input was 4
		case 4:
			// adding 10 new lines to indicate new page
			userInterface.changeCurrentPage(11);
			//prompt the user for a new password
			System.out.println("Enter the new password");
			//get the user's input and save to variable
			String newPass = sc.next();
			//open Users.txt file and empty it
			BufferedWriter outUser = new BufferedWriter(new FileWriter("Users.txt",false));
			//close the file
			outUser.close();
			//reopen the file, but this time allow appending
			outUser = new BufferedWriter(new FileWriter("Users.txt",true));
			//remove the last element from the list
			allUsers.remove(allUsers.size()-1);
			//add the new password to the list
			allUsers.add("ADMIN:" + newPass);
			//for loop iterating through full list of all usernames registered
			for(int i=0;i<allUsers.size();i++){
				//writing the username to the file
				outUser.write(allUsers.get(i));
				//going to the nest line
				outUser.newLine();
			}
			//close the writer file
			outUser.close();
			//informs the user the password is changed
			System.out.println("Password Changed");
			//go back to adminSignedIn
			adminSignedIn();
			//break from the statement
			break;
		//if input was 5
		case 5:
			// adding 10 new lines to indicate new page
			userInterface.changeCurrentPage(11);
			//informs the user they signed out
			System.out.println("Signed Out.");
			//goto main
			main(null);
			//break from statement
			break;
		//if invalid input
		default: 
			//go to admin signed in
			adminSignedIn();
		}
	}

	private static void p5() throws IOException {
		//adding 10 new lines to indicate new page
		userInterface.changeCurrentPage(5);
		//prints options
		//print View Items by Category
		System.out.println("1. View Items By Category");
		//print View shopping cart
		System.out.println("2. View Shopping Cart");
		//print sign out
		System.out.println("3. Sign Out");
		//print view pervious orders
		System.out.println("4. View Previous Orders");
		//prompt user for option
		System.out.print("Choose your option: ");
		//switch case depending on the int the user enters
		switch (sc.nextInt()){
		//if input was 1
		case 1:
			//get the audio products from file
			userInterface.getAudioProducts();
			//get the readable products from file
			userInterface.getReadables();
			//goto page 6
			p6();
			//break from the statement
			break;
		//if input was 2
		case 2:
			//goto page 7		
			p7();
			//break from statement
			break;
		//if input was 3
		case 3:
			//inform user they signed out
			System.out.println("Signed Out.");
			//adding 10 new lines to indicate new page
			userInterface.changeCurrentPage(0);
			//go back to the main
			main(null);
			//break from the statement
			break;
		case 4:
			BufferedReader itemsBoughtR = new BufferedReader(new FileReader("ItemsBought.txt"));
			String line;
			//printing headers with special spacing
			System.out.printf("%-15.15s %-30.30s %-15.15s%n","Confirmation ID","Product Name","Total");
			//loop while the line it reads is not null
			while ((line=itemsBoughtR.readLine()) !=null){
				//splits the line by the tab character
				String[] info = line.split("\t");
				//if the username matches the name in the file
				if(info[0].trim().equals(person.returnUsername().trim())){
					//split the info[2] element by the commas as it has all the items that were checked out at once
					String[] products = info[2].split(",");
					//allProducts starts at being the second element ignoring the first comma
					String allProducts=products[1];
					//for loop for the length of the products array starting at the second element
					//if there are less than 2, then it wont show up
					for(int i=2;i<products.length;i++){
						//seperating all the products bought by a comma
						allProducts = allProducts + "," + products[i];
					}
					//print the information with the special spacing
					System.out.printf("%-15.15s %-30.30s %-15.15s%n",info[1],allProducts,info[3]);
				}
			}
			//close the file
			itemsBoughtR.close();
			System.out.println("Press enter to continue");
			try{
				//read the input from the user
				System.in.read();
				//once read an input, go to adminSignedIn page for options
				adminSignedIn();
			}
			//catch if could not get input
			catch (Exception e){
				
			}
			p5();
			break;
		//if inupt is not an option
		default:
			//goto page 5
			p5();
		}
	}

	private static void p6() throws IOException {
		//adding 10 new lines to indicate new page
		userInterface.changeCurrentPage(6);
		//give the options
		//prints readables
		System.out.println("1. Readables");
		//prints audio
		System.out.println("2. Audio");
		//gives user option to return to -1
		System.out.println("Press -1 to return to previous menu");
		//prompt user for input
		System.out.print("Choose your option: ");
		//switch based on user's input
		switch (sc.nextInt()){
		//if input was 1
		case 1:
			//goto page 8
			p8();
			//break from statement
			break;
		//if the input was 2
		case 2:
			//goto page 9
			p9();
			//break from the statement
			break;
		//if the input was -1
		case -1:
			//goto page 5
			p5();
			//break from the statement
			break;
		}
	}

	private static void p9() throws IOException {
		//adding 10 new lines to indicate new page
		userInterface.changeCurrentPage(9);
		// show audio products from user interface
		userInterface.showAudioProducts();
		// shows user options
		System.out.println("Press -1 to return to previous menu");
		System.out.print("Choose your option: ");
		// prompts for user input
		int book = sc.nextInt();
		// if user submits -1 then go back to p6
		if(book == -1){
			p6();
		} else {
			try{
				// item of type audio is set to item got from serial number that user inputed
				Audio item = (Audio) userInterface.getBySerialNumber(book);
				// promts user for quantity of item
				System.out.print("Enter quantity: ");
				// gets quantity
				int quantity = sc.nextInt();
				// if the quantity requested is less than the quantity in inventory then 
				if(quantity<=item.getQuantity()){
					// passes item and quantity to method shoppingcart
					shoppingCart.addItem(item, quantity);
					// gives sucess statment to user
					System.out.println(quantity + " " + item.getName() + " was successfully added to your cart");
					// prompts user for action
					System.out.println("Press -2 to continue shopping or Press 0 to CheckOut");
					// takes input from user
					switch (sc.nextInt()){
					// if user gives -2
					case -2:
						// go to p6 
						p6();
						// breaks out of the statment
						break;
					case 0:
						// if case 0 the go to p10
						p10();
						// breaks out of the statment
						break;
					}
				}
				else {
					// gives user error message then goes back to p9
					System.out.println("Not available for that quantity");
					p9();
				}
			} catch(Exception e){
				// gives user error message then goes back to p9
				System.out.println("Please choose a valid serial number");
				p9();
			}
		}
	}

	private static void p8() throws IOException {
		//adding 10 new lines to indicate new page
		userInterface.changeCurrentPage(8);
		// show Readables products from user interface
		userInterface.showReadables();
		// shows user options
		System.out.println("Press -1 to return to previous menu");
		System.out.print("Choose your option: ");
		// prompts for user input
		int book = sc.nextInt();
		// if user submits -1 then go back to p6
		if(book == -1){
			p6();
		} else {
			try{
				// item of type Readables is set to item got from serial number that user inputed
				Readable item = (Readable) userInterface.getBySerialNumber(book);
				// promts user for quantity of item
				System.out.print("Enter quantity: ");
				// gets quantity
				int quantity = sc.nextInt();
				// if the quantity requested is less than the quantity in inventory then 
				if(quantity<=item.getQuantity()){
					// passes item and quantity to method shoppingcart
					shoppingCart.addItem(item, quantity);
					// gives sucess statment to user
					System.out.println(quantity + " " + item.getName() + " was successfully added to your cart");
					// prompts user for action
					System.out.println("Press -2 to continue shopping or Press 0 to CheckOut");
					// takes input from user
					switch (sc.nextInt()){
					// if user gives -2
					case -2:
						// go to p6 
						p6();
						// breaks out of the statment
						break;
					case 0:
						// if case 0 the go to p10
						p10();
						// breaks out of the statment
						break;
					}
				}
				else {
					// gives user error message then goes back to p9
					System.out.println("Not available for that quantity");
					p8();
				}
			} catch(Exception e) {
				// gives user error message then goes back to p9
				System.out.println("Please choose a valid serial number");
				p9();
			}
		}
	}

	private static void p10() throws IOException {
		//adding 10 new lines to indicate new page
		userInterface.changeCurrentPage(10);
		//Prints out "Billing Information:"
		System.out.println("Billing Information:");
		//Prints header for shopping cart
		System.out.printf("%-30.30s %-15.15s %-15.15s %-20.20s%n","Name","","Quantity","Price");
		//Prints new line
		System.out.println();
		//Prints the formatted contents of shopping cart
		shoppingCart.printCart();
		//Returns to p6();
		p6();
	}

	private static void p7() throws IOException {
		//adding 10 new lines to indicate new page
		userInterface.changeCurrentPage(7);
		//Opens the cart file of the username and assigns it to cartFile
		BufferedReader cartFile;
		//try reading the file
		try {
			cartFile = new BufferedReader(new FileReader("Cart_"+person.returnUsername()+".txt"));
			//Declares line as a String
			String line;
			//loop while the line it reads is not null and the name is not yet found
			while ((line=cartFile.readLine()) !=null){
				//Prints out the file line
				System.out.println(line);
			}
			//Close the cart file
			cartFile.close();
			//Informs the user to press 0 to go to checkout
			System.out.println("Press 0 to proceed to checkout");
			//Informs the user to press -1 to return to previous menu
			System.out.println("Press -1 to return to the previous menu");
			//Prompts the user for the choice
			System.out.print("Choose you option: ");
			//Get next integer as choice
			switch (sc.nextInt()){
			//If -1:
			case -1:
				//Goto p5()
				p5();
				//Break
				break;
			//If 0:
			case 0:
				//Goto p10()
				p10();
				//Break
				break;
			}
		}
		//catch an error if the file is not found
		catch (FileNotFoundException e) {
			//print a message
			System.out.println("You do not have any items in your cart");
			//goto page 5 for options
			p5();
		}
		
	}
	private static void addItems() throws IOException{
		// adding 10 new lines to indicate new page
		userInterface.changeCurrentPage(11);
		//Prints out "Add Items"
		System.out.println("Add Items");
		//Prints out "1. Book"
		System.out.println("1. Book");
		//Prints out "2. eBook"
		System.out.println("2. eBook");
		//Prints out "3. CD"
		System.out.println("3. CD");
		//Prints out "4. MP3"
		System.out.println("4. MP3");
		//Prints out "Press -1 to return."
		System.out.println("Press -1 to return.");
		//Prints out "Choose type of item to add: "
		System.out.println("Choose type of item to add: ");
		//Declare option as the next integer
		int option = sc.nextInt();
		//Gets next line
		sc.nextLine();
		switch (option){
		//If option 1 was selected:
		case 1:
			//add new Book
			addBook();
			//return to addItems()
			addItems();
			//Breaks out of statement
			break;
		//If option 2 was selected:
		case 2:
			//add new eBook
			addeBook();
			//return to addItems()
			addItems();
			//Breaks out of statement
			break;
		//If option 3 was selected:
		case 3:
			//add new CD
			addCD();
			//return to addItems()
			addItems();
			//Breaks out of statement
			break;
		//If option 4 was selected:
		case 4:
			//add new MP3
			addMP3();
			//return to addItems()
			addItems();
			//Breaks out of statement
			break;
		//If option -1 was selected:
		case -1:
			//add new inSignedIn
			adminSignedIn();
			//return to addItems()
			break;
			//Breaks out of statement
		//Else
		default:
			//Prompts user to enter valid option
			System.out.println("Please enter a valid option.");
			//Returns to addItems()
			addItems();
			//Breaks
			break;
		}
	}
	private static void addBook() throws IOException{
		// adding 10 new lines to indicate new page
		userInterface.changeCurrentPage(11);
		//Informs user that the program is creating a new book
		System.out.println("Creating new book...");
		//Prints out Title
		System.out.print("Title: ");
		//Declare title as next line
		String title = sc.nextLine();
		//Prints out Author
		System.out.print("Author: ");
		//Declare author as next line
		String author = sc.nextLine();
		//Declare valid as false
		boolean valid = false;
		//Declare amount as 0
		int amount = 0;
		//Declare price as 0
		int price = 0;
		//Declare sNo as a new serial number
		int sNo = userInterface.getNewSerialNumber();
		//while input not valid
		while(!valid){
			//try
			try{
				//Prompts user to input price
				System.out.print("Price: $");
				//Assign the next integer to price
				price = sc.nextInt();
				//Set valid to true
				valid = true;
			//If user inputs non-integer values:
			} catch(Exception e){
				//Prompt user to input a valid price
				System.out.println("Please enter a valid price");
			}
		}
		//Reset to valid to false
		valid = false;
		//while input not valid
		while(!valid){
			//try
			try{
				//Prompts user to input amount
				System.out.print("Amount: ");
				//Assign the next integer to amount
				amount = sc.nextInt();
				//Set valid to true
				valid = true;
			//If user inputs non-integer values:
			} catch(Exception e){
				//Prompt user to input a valid price
				System.out.println("Please enter a valid amount");
			}
		}
		//Open Books.txt
		BufferedWriter bw = new BufferedWriter(new FileWriter("Books.txt",true));
		//Declare itemInfo as the formatted String to be written to Books.txt
		String itemInfo = sNo + ", " + title + ", " + author + ", " + price + ", " + amount;
		//Print new line
		bw.newLine();
		//Write to file
		bw.write(itemInfo);
		//Close file
		bw.close();
	}
	private static void addeBook() throws IOException{
		// adding 10 new lines to indicate new page
		userInterface.changeCurrentPage(11);
		//Informs user that the program is creating a new eBook
		System.out.println("Creating new eBook...");
		//Prints out Title
		System.out.print("Title: ");
		//Declare title as next line
		String title = sc.nextLine();
		//Prints out Author
		System.out.print("Author: ");
		//Declare author as next line
		String author = sc.nextLine();
		//Declare valid as false
		boolean valid = false;
		//Declare amount as 0
		int amount = 0;
		//Declare price as 0
		int price = 0;
		//Declare sNo as a new serial number
		int sNo = userInterface.getNewSerialNumber();
		//while input not valid
		while(!valid){
			//try
			try{
				//Prompts user to input price
				System.out.print("Price: $");
				//Assign the next integer to price
				price = sc.nextInt();
				//Set valid to true
				valid = true;
			//If user inputs non-integer values:
			} catch(Exception e){
				//Prompt user to input a valid price
				System.out.println("Please enter a valid price");
			}
		}
		//Reset to valid to false
		valid = false;
		//while input not valid
		while(!valid){
			//try
			try{
				//Prompts user to input amout
				System.out.print("Amount: ");
				//Assign the next integer to amount
				amount = sc.nextInt();
				//Set valid to true
				valid = true;
			//If user inputs non-integer values:
			} catch(Exception e){
				//Prompt user to input a valid price
				System.out.println("Please enter a valid amount");
			}
		}
		//Open Ebooks.txt
		BufferedWriter bw = new BufferedWriter(new FileWriter("Ebooks.txt",true));
		//Declare itemInfo as the formatted String to be written to Ebooks.txt
		String itemInfo = sNo + ", " + title + ", " + author + ", " + price + ", " + amount;
		//Print new line
		bw.newLine();
		//Write to file
		bw.write(itemInfo);
		//Close file
		bw.close();
	}
	private static void addCD() throws IOException{
		// adding 10 new lines to indicate new page
		userInterface.changeCurrentPage(11);
		//Informs user that the program is creating a new CD
		System.out.println("Creating new CD...");
		//Prints out Title
		System.out.print("Title: ");
		//Declare title as next line
		String title = sc.nextLine();
		//Prints out Author
		System.out.print("Author: ");
		//Declare author as next line
		String author = sc.nextLine();
		//Declare valid as false
		boolean valid = false;
		//Declare amount as 0
		int amount = 0;
		//Declare price as 0
		int price = 0;
		//Declare sNo as a new serial number
		int sNo = userInterface.getNewSerialNumber();
		//while input not valid
		while(!valid){
			//try
			try{
				//Prompts user to input price
				System.out.print("Price: $");
				//Assign the next integer to price
				price = sc.nextInt();
				//Set valid to true
				valid = true;
			//If user inputs non-integer values:
			} catch(Exception e){
				//Prompt user to input a valid price
				System.out.println("Please enter a valid price");
			}
		}
		//Reset to valid to false
		valid = false;
		//while input not valid
		while(!valid){
			//try
			try{
				//Prompts user to input amout
				System.out.print("Amount: ");
				//Assign the next integer to amount
				amount = sc.nextInt();
				//Set valid to true
				valid = true;
			//If user inputs non-integer values:
			} catch(Exception e){
				//Prompt user to input a valid price
				System.out.println("Please enter a valid amount");
			}
		}
		//Open CDs.txt
		BufferedWriter bw = new BufferedWriter(new FileWriter("CDs.txt",true));
		//Declare itemInfo as the formatted String to be written to CDs.txt
		String itemInfo = sNo + ", " + title + ", " + author + ", " + price + ", " + amount;
		//Print new line
		bw.newLine();
		//Write to file
		bw.write(itemInfo);
		//Close file
		bw.close();
	}
	private static void addMP3() throws IOException{
		// adding 10 new lines to indicate new page
		userInterface.changeCurrentPage(11);
		//Informs user that the program is creating a new MP3
		System.out.println("Creating new MP3...");
		//Prints out Title
		System.out.print("Title: ");
		//Declare title as next line
		String title = sc.nextLine();
		//Prints out Author
		System.out.print("Author: ");
		//Declare author as next line
		String author = sc.nextLine();
		//Declare valid as false
		boolean valid = false;
		//Declare amount as 0
		int amount = 0;
		//Declare price as 0
		int price = 0;
		//Declare sNo as a new serial number
		int sNo = userInterface.getNewSerialNumber();
		//while input not valid
		while(!valid){
			//try
			try{
				//Prompts user to input price
				System.out.print("Price: $");
				//Assign the next integer to price
				price = sc.nextInt();
				//Set valid to true
				valid = true;
			//If user inputs non-integer values:
			} catch(Exception e){
				//Prompt user to input a valid price
				System.out.println("Please enter a valid price");
			}
		}
		//Reset to valid to false
		valid = false;
		//while input not valid
		while(!valid){
			//try
			try{
				//Prompts user to input amout
				System.out.print("Amount: ");
				//Assign the next integer to amount
				amount = sc.nextInt();
				//Set valid to true
				valid = true;
			//If user inputs non-integer values:
			} catch(Exception e){
				//Prompt user to input a valid price
				System.out.println("Please enter a valid amount");
			}
		}
		//Open MP3.txt
		BufferedWriter bw = new BufferedWriter(new FileWriter("MP3.txt",true));
		//Declare itemInfo as the formatted String to be written to MP3.txt
		String itemInfo = sNo + ", " + title + ", " + author + ", " + price + ", " + amount;
		//Print new line
		bw.newLine();
		//Write to file
		bw.write(itemInfo);
		//Close file
		bw.close();
	}
}