/*
 * @Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * @MacID: ravip2, shahk24, olivepjm
 * @Student_Number:  1407109, 1419350, 1430273
 * @Description: A class that handles the reading of the inventory and also the printing of the inventory in addition to sorting the inventory
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserInterface {
	//initializing the arrays to hold the information
	private Readable[] readables;
	private Audio[] audioProducts;
	//int to hold the page it is on
	private int currentPage;
	//returns the page number
	public int getCurrentPage(){
		return currentPage;
	}
	//changes the page number
	public int changeCurrentPage(int page){
		/* Page 1 = Sign in
		 * Page 2 = Choose Username
		 * Page 3 = Hello Mr. White
		 * Page 4 = No Access
		 * Page 5 = View Items by Cart, Shopping cart, Sign out menu.
		 * Page 6 = Readable or audio menu.
		 * Page 7 = Shopping cart
		 * Page 8 = readable
		 * Page 9 = audio 
		 * Page 10= cart
		 * Page 11= Admin login
		 */
		currentPage = page;
		//for loop that iterates 10 times
		for(int i=0;i<10;i++){
			//print 10 empty lines to indicate a new page
			System.out.println();
		}
		return currentPage;
	}
	//get all the the readable items, books and ebooks
	public void getReadables() throws IOException{
		//list that will hold all the readable items
		List<Readable> reads = new ArrayList<Readable>();
		//Opening the filereader to read Books.txt
		BufferedReader file = new BufferedReader(new FileReader("Books.txt"));
		//variable that will hold the information from the line in the file
		String line;
		//loop while the line it reads is not null
		while ((line=file.readLine()) !=null){
			//array that spilts the information in the line by ", "
			String[] info = line.split(", ");
			//making a new Book item with the information provided in the text file and adds it to the list
			reads.add(new Book(Integer.parseInt(info[0]), info[1], info[2], Integer.parseInt(info[3]), Integer.parseInt(info[4])));
		}
		//closing the file
		file.close();
		//opening "EBooks.txt"
		file = new BufferedReader(new FileReader("EBooks.txt"));
		//loop while the line it reads is not null
		while ((line=file.readLine()) !=null){
			//array that spilts the information in the line by ", "
			String[] info = line.split(", ");
			//making a new eBook item with the information provided in the text file and adds it to the list
			reads.add(new eBook(Integer.parseInt(info[0]), info[1], info[2], Integer.parseInt(info[3]), Integer.parseInt(info[4])));
		}
		//closing the file
		file.close();
		//sorting the reads list by the serial number
		Collections.sort(reads, new Comparator<Readable>() {
			//function compare will take 2 readables from the list
			public int compare(Readable object1, Readable object2) {
				//will return 1,0,-1 depending on the serial number and will rearrange the list
				return object1.getSerial().compareTo(object2.getSerial());
			}
		} );
		//setting the readables array to be the size of the reads list
		readables = new Readable[reads.size()];
		//converting the information in the list to the readables array
		readables = reads.toArray(readables);
	}
	//get all the audio items, CDs and MP3
	public void getAudioProducts() throws IOException{
		//list that will hold all the readable items
		List<Audio> aud = new ArrayList<Audio>();
		//Opening the filereader to read Books.txt
		BufferedReader file = new BufferedReader(new FileReader("MP3.txt"));
		//variable that will hold the information from the line in the file
		String line;
		//loop while the line it reads is not null
		while ((line=file.readLine()) !=null){
			//array that spilts the information in the line by ", "
			String[] info = line.split(", ");
			//making a new mp3 item with the information provided in the text file and adds it to the list
			aud.add(new MP3(Integer.parseInt(info[0]), info[1], info[2], Integer.parseInt(info[3]), Integer.parseInt(info[4])));
		}
		//closing the file
		file.close();
		//opening "CDs.txt"
		file = new BufferedReader(new FileReader("CDs.txt"));
		//loop while the line it reads is not null
		while ((line=file.readLine()) !=null){
			//array that spilts the information in the line by ", "
			String[] info = line.split(", ");
			//making a new cd item with the information provided in the text file and adds it to the list
			aud.add(new CD(Integer.parseInt(info[0]), info[1], info[2], Integer.parseInt(info[3]), Integer.parseInt(info[4])));
		}
		//closing the file
		file.close();
		//sorting the reads list by the serial number
		Collections.sort(aud, new Comparator<Audio>() {
			//function compare will take 2 readables from the list
			public int compare(final Audio object1, final Audio object2) {
				//will return 1,0,-1 depending on the serial number and will rearrange the list
				return object1.getSerial().compareTo(object2.getSerial());
			}
		} );
		//setting the readables array to be the size of the reads list
		audioProducts = new Audio[aud.size()];
		//converting the information in the list to the readables array
		audioProducts = aud.toArray(audioProducts);
	}
	//admin function to sort the items based on price
	public List<Item> getItemsByPrice(){
		//list that holds all the audio type files
		List<Audio> aud = new ArrayList<Audio>();
		//for loop to add the values in the array to the list
		for(int i=0; i < audioProducts.length; i++){
			aud.add(audioProducts[i]);
		}
		//sorting the information based on their prices
		Collections.sort(aud, new Comparator<Audio>() {
			//comparing 2 audio objects
			public int compare(final Audio object1, final Audio object2) {
				//return the difference of the prices
				//negative means object2 is greater, 0 they are same, and positive object 1 is greater
				return object1.getPrice() - object2.getPrice();
			}
		} );
		//list that holds all the readable type files
		List<Readable> reads = new ArrayList<Readable>();
		//for loop to add the values in the array to the list
		for(int i=0; i < readables.length; i++){
			reads.add(readables[i]);
		}
		//sorting the information based on their prices
		Collections.sort(reads, new Comparator<Readable>() {
			//comparing 2 readable objects
			public int compare(final Readable object1, final Readable object2) {
				//return the difference of the prices
				//negative means object2 is greater, 0 they are same, and positive object 1 is greater
				return object1.getPrice() - object2.getPrice();
			}
		} );
		//merge sorting the 2 lists
		//list for all items in the inventory
		List<Item> items = new ArrayList<Item>();
		//boolean to determine if the sort is done
		boolean done = false;
		//counters for audio and readables
		int iAud = 0;
		int iReads = 0;
		//while done == false
		while(!done){
			//if both counters are the same size of their respective lists
			if(iAud == aud.size() && iReads == reads.size()){
				//sorting is done
				done = true;
				//goto the next iteration of the loop
				continue;
			} 
			//if finished going through all audio items
			else if(iAud == aud.size()){
				//add the readables to the items list
				items.add(reads.get(iReads));
				//increment the readable counter
				iReads += 1;
				//goto next iteration of loop
				continue;
			} 
			//if finished going through all readable items
			else if(iReads == aud.size()){
				//add the audio to the items list
				items.add(aud.get(iAud));
				//increment the audio counter
				iAud += 1;
				//goto next iteration of loop
				continue;
			}
			//if the audio list's item's price at the counter is less than the readable list's item's price
			if((aud.get(iAud).getPrice() - reads.get(iReads).getPrice()) < 0){
				//add the audio item to the items list
				items.add(aud.get(iAud));
				//increment the audio counter
				iAud += 1;
			} 
			//if it is greater...
			else {
				//add the readable item to the items list
				items.add(reads.get(iReads));
				//increment the readable counter
				iReads += 1;
			}
		}
		//return the items list
		return items;
	}
	//admin function to sort items based on name
	public List<Item> getItemsByName(){
		//list that holds all the audio type files
		List<Audio> aud = new ArrayList<Audio>();
		//for loop to add the values in the array to the list
		for(int i=0; i < audioProducts.length; i++){
			aud.add(audioProducts[i]);
		}
		//sorting the information based on their prices
		Collections.sort(aud, new Comparator<Audio>() {
			//comparing 2 audio objects
			public int compare(final Audio object1, final Audio object2) {
				//return the difference of the prices
				//negative means object2 is greater, 0 they are same, and positive object 1 is greater
				return object1.getName().compareToIgnoreCase(object2.getName());
			}
		} );
		//list that holds all the readable type files
		List<Readable> reads = new ArrayList<Readable>();
		//for loop to add the values in the array to the list
		for(int i=0; i < readables.length; i++){
			reads.add(readables[i]);
		}
		//sorting the information based on their prices
		Collections.sort(reads, new Comparator<Readable>() {
			//comparing 2 readable objects
			public int compare(final Readable object1, final Readable object2) {
				//return the difference of the prices
				//negative means object2 is greater, 0 they are same, and positive object 1 is greater
				return object1.getName().compareToIgnoreCase(object2.getName());
			}
		} );
		//merge sorting the 2 lists
		//list for all items in the inventory
		List<Item> items = new ArrayList<Item>();
		//boolean to determine if the sort is done
		boolean done = false;
		//counters for audio and readables
		int iAud = 0;
		int iReads = 0;
		//while done == false
		while(!done){
			//if both counters are the same size of their respective lists
			if(iAud == aud.size() && iReads == reads.size()){
				//sorting is done
				done = true;
				//goto the next iteration of the loop
				continue;
			} 
			//if finished going through all audio items
			else if(iAud == aud.size()){
				//add the readables to the items list
				items.add(reads.get(iReads));
				//increment the readable counter
				iReads += 1;
				//goto next iteration of loop
				continue;
			} 
			//if finished going through all readable items
			else if(iReads == aud.size()){
				//add the audio to the items list
				items.add(aud.get(iAud));
				//increment the audio counter
				iAud += 1;
				//goto next iteration of loop
				continue;
			}
			//if the audio list's item's name compared to the readable's name ignoring case is negative, meaning the audio is larger
			if((aud.get(iAud).getName().compareToIgnoreCase(reads.get(iReads).getName())) < 0){
				//add the audio item to the items list
				items.add(aud.get(iAud));
				//incrementing the audio counter
				iAud += 1;
			} 
			//if it is positive, the readable is greater
			else {
				//add the readable item to the items list
				items.add(reads.get(iReads));
				//increment the readable counter
				iReads += 1;
			}
		}
		//returns the items list
		return items;
	}
	public void showReadables() throws IOException{
		getReadables();
		//printing the headings with certain spacing
		System.out.printf("%-10.10s %-30.30s %-30.30s %-10.10s %-22.22s %-15.15s%n","S.No","Name of Book","Author","Price($)","Quantity in Store","Type");
		//for loop for the length of the readables array
		for(int i=0;i<readables.length;i++){
			//temporary string with the information from readables[i]
			String temp = readables[i].getInfo();
			//splitting the information by ", "
			String[] info = temp.split(", ");
			//if quantity is more than 0
			if(Integer.parseInt(info[4])>0){
				//printing the information from readables[i] in the spacing of the headings
				System.out.printf("%-10.10s %-30.30s %-30.30s %-10.10s %-22.22s %-15.15s%n",info[0]+".",info[1],info[2],info[3],info[4],info[5]);
			}
		}
	}
	public void showAudioProducts() throws IOException{
		getAudioProducts();
		//printing the headings with certain spacing
		System.out.printf("%-10.10s %-30.30s %-30.30s %-10.10s %-22.22s %-15.15s%n","S.No","Name","Artist","Price($)","Quantity in Store","Type");
		//for loop for the length of the audio array
		for(int i=0;i<audioProducts.length;i++){
			//temporary string with the information from audioProducts[i]
			String temp = audioProducts[i].getInfo();
			//splitting the information by ", "
			String[] info = temp.split(", ");
			//if quantity is more than 0
			if(Integer.parseInt(info[4])>0){
				//printing the information from audioProducts[i] in the spacing of the headings
				System.out.printf("%-10.10s %-30.30s %-30.30s %-10.10s %-22.22s %-15.15s%n",info[0]+".",info[1],info[2],info[3],info[4],info[5]);
			}
		}
	}
	public void showItemsByPrice(){
		//get the items sorted
		List<Item> items = getItemsByPrice();
		//printing the heading with certain spaces
		System.out.printf("%-10.10s %-30.30s %-30.30s %-10.10s %-22.22s %-15.15s%n","S.No","Name","Artist","Price($)","Quantity in Store","Type");
		//for loop for the size of the sorted items list
		for(int i=0;i<items.size();i++){
			//temporary string with the information from the ith element of the list
			String temp = items.get(i).getInfo();
			//splitting the information by ", "
			String[] info = temp.split(", ");
			//if the quantity is more than 0
			if(Integer.parseInt(info[4])>0){
				//printing the informatin from the list with the spacing from the headings
				System.out.printf("%-10.10s %-30.30s %-30.30s %-10.10s %-22.22s %-15.15s%n",info[0]+".",info[1],info[2],info[3],info[4],info[5]);
			}
		}
	}
	public void showItemsByName(){
		//get the items sorted
		List<Item> items = getItemsByName();
		//printing the heading with certain spaces
		System.out.printf("%-10.10s %-30.30s %-30.30s %-10.10s %-22.22s %-15.15s%n","S.No","Name","Artist","Price($)","Quantity in Store","Type");
		//for loop for the size of the sorted items list
		for(int i=0;i<items.size();i++){
			//temporary string with the information from the ith element of the list
			String temp = items.get(i).getInfo();
			//splitting the information by ", "
			String[] info = temp.split(", ");
			//if the quantity is more than 0
			if(Integer.parseInt(info[4])>0){
				//printing the informatin from the list with the spacing from the headings
				System.out.printf("%-10.10s %-30.30s %-30.30s %-10.10s %-22.22s %-15.15s%n",info[0]+".",info[1],info[2],info[3],info[4],info[5]);
			}
		}
	}

	// method to get the item object based on the serial number.
	public Item getBySerialNumber(int serialNumber) throws IOException{
		// Create new item object 
		Item foundItem = null;
		// getting the Readables
		getReadables();
		// loop going though the Readables searching for the serialNumber
		for(int i=0;i<readables.length;i++){
			// if the serialNumbers match
			if(readables[i].getSerial().equals(serialNumber+"")){
				// set found items to the ith element in Readables
				foundItem = readables[i];
				// breaks out of the statement
				break;
			}
		}
		// loop going though the Audio Products searching for the serialNumber
		getAudioProducts();
		for(int i=0;i<audioProducts.length;i++){
			// if the serialNumbers match
			if(audioProducts[i].getSerial().equals(serialNumber+"")){
				// set found items to the ith element in Audio Products
				foundItem = audioProducts[i];
				// breaks out of the statement
				break;
			}
		}
		// return the found item
		return foundItem;
	}

	// method to make a new serial number when Admin adds a item 
	public int getNewSerialNumber() throws IOException{
		// getting Readables and audio Products 
		getReadables();
		getAudioProducts();
		// setting variables to initial values
		// Serial to 0
		int newSerial = 0;
		//Found to false
		boolean found = false;
		// while loop incrementing through all existing serial numbers.
		// when next serial number is not existent return new Serial

		//while not found
		while (!found){
			// Increment new serial
			newSerial++;
			// use getBySerialNumber to find the temp item with new serial
			Item tmp = getBySerialNumber(newSerial);
			//if serial does not exist
			if(tmp == null){
				// stop looping
				found = true;
			}
		}
		// return the new serial number
		return newSerial;
	}
	public void updateItem(List<Item> update) throws IOException {
		//for loop for the size of the list sent in
		for(int i=0;i<update.size();i++){
			//initializing the reader and writer
			BufferedReader file = null;
			BufferedWriter br = null;
			//variable for the line from the file
			String line;
			//switch case depending on the type of file
			switch (update.get(i).getType()){
			//if the type is CD
			case "CD":
				//list for the type
				List<Audio> cd = new ArrayList<Audio>();
				//openning the file in the reader
				file = new BufferedReader(new FileReader("CDs.txt"));
				//while the line in the file is not null
				while ((line=file.readLine()) !=null){
					//split the information in the line by ", "
					String[] info = line.split(", ");
					//add a new Item with the information from the file
					cd.add(new CD(Integer.parseInt(info[0]), info[1], info[2], Integer.parseInt(info[3]), Integer.parseInt(info[4])));
				}
				//close the reader
				file.close();
				//for loop for the size of the list of items from the text file(inventory)
				for(int j=0;j<cd.size();j++){
					//if the serial number from the list equals a serial number from the list of items that need to be updated
					if(cd.get(j).getSerial().equals(update.get(i).getSerial())){
						//set the quantity of that item to be the quantity of the item from the updated list
						cd.get(j).setQuantity(update.get(i).getQuantity());
					}
				}
				//open a buffered writer, this clears the file completely
				br = new BufferedWriter(new FileWriter("CDs.txt"));
				//close the writer
				br.close();
				//reopen the writer, but now the true parameter means that it can be appended
				br = new BufferedWriter(new FileWriter("CDs.txt",true));
				//for loop for the size of the list
				for(int j=0;j<cd.size();j++){
					//split the information of the item by ", "
					String[] info = cd.get(j).getInfo().split(", ");
					//string with all the informatoin that will go in the file
					String fileLine = info[0]+", "+info[1]+", "+info[2]+", "+info[3]+", "+info[4];
					//goto the next line in the file
					br.newLine();
					//write the line to the file
					br.write(fileLine);
				}
				br.close();
				break;
				//if the type is Book
			case "Book":
				//list for the type
				List<Readable> books = new ArrayList<Readable>();
				//openning the file in the reader
				file = new BufferedReader(new FileReader("Books.txt"));
				//while the line in the file is not null
				while ((line=file.readLine()) !=null){
					//split the information in the line by ", "
					String[] info = line.split(", ");
					//add a new Item with the information from the file
					books.add(new Book(Integer.parseInt(info[0]), info[1], info[2], Integer.parseInt(info[3]), Integer.parseInt(info[4])));
				}
				//close the reader
				file.close();
				//for loop for the size of the list of items from the text file(inventory)
				for(int j=0;j<books.size();j++){
					//if the serial number from the list equals a serial number from the list of items that need to be updated
					if(books.get(j).getSerial().equals(update.get(i).getSerial())){
						//set the quantity of that item to be the quantity of the item from the updated list
						books.get(j).setQuantity(update.get(i).getQuantity());
					}
				}
				//open a buffered writer, this clears the file completely
				br = new BufferedWriter(new FileWriter("Books.txt"));
				//close the writer
				br.close();
				//reopen the writer, but now the true parameter means that it can be appended
				br = new BufferedWriter(new FileWriter("Books.txt",true));
				//for loop for the size of the list
				for(int j=0;j<books.size();j++){
					//split the information of the item by ", "
					String[] info = books.get(j).getInfo().split(", ");
					//string with all the informatoin that will go in the file
					String fileLine = info[0]+", "+info[1]+", "+info[2]+", "+info[3]+", "+info[4];
					//goto the next line in the file
					br.newLine();
					//write the line to the file
					br.write(fileLine);
				}
				br.close();
				break;
				//if the type is ebook
			case "eBook":
				//list for the type
				List<Readable> ebooks = new ArrayList<Readable>();
				//openning the file in the reader
				file = new BufferedReader(new FileReader("Ebooks.txt"));
				//while the line in the file is not null
				while ((line=file.readLine()) !=null){
					//split the information in the line by ", "
					String[] info = line.split(", ");
					//add a new Item with the information from the file
					ebooks.add(new eBook(Integer.parseInt(info[0]), info[1], info[2], Integer.parseInt(info[3]), Integer.parseInt(info[4])));
				}
				//close the reader
				file.close();
				//for loop for the size of the list of items from the text file(inventory)
				for(int j=0;j<ebooks.size();j++){
					//if the serial number from the list equals a serial number from the list of items that need to be updated
					if(ebooks.get(j).getSerial().equals(update.get(i).getSerial())){
						//set the quantity of that item to be the quantity of the item from the updated list
						ebooks.get(j).setQuantity(update.get(i).getQuantity());
					}
				}
				//open a buffered writer, this clears the file completely
				br = new BufferedWriter(new FileWriter("Ebooks.txt"));
				//close the writer
				br.close();
				//reopen the writer, but now the true parameter means that it can be appended
				br = new BufferedWriter(new FileWriter("Ebooks.txt",true));
				//for loop for the size of the list
				for(int j=0;j<ebooks.size();j++){
					//split the information of the item by ", "
					String[] info = ebooks.get(j).getInfo().split(", ");
					//string with all the informatoin that will go in the file
					String fileLine = info[0]+", "+info[1]+", "+info[2]+", "+info[3]+", "+info[4];
					//goto the next line in the file
					br.newLine();
					//write the line to the file
					br.write(fileLine);
				}
				br.close();
				break;
				//if the type is MP3
			case "MP3":
				//list for the type
				List<Audio> mp3 = new ArrayList<Audio>();
				//openning the file in the reader
				file = new BufferedReader(new FileReader("MP3.txt"));
				//while the line in the file is not null
				while ((line=file.readLine()) !=null){
					//split the information in the line by ", "
					String[] info = line.split(", ");
					//add a new Item with the information from the file
					mp3.add(new MP3(Integer.parseInt(info[0]), info[1], info[2], Integer.parseInt(info[3]), Integer.parseInt(info[4])));
				}
				//close the reader
				file.close();
				//for loop for the size of the list of items from the text file(inventory)
				for(int j=0;j<mp3.size();j++){
					//if the serial number from the list equals a serial number from the list of items that need to be updated
					if(mp3.get(j).getSerial().equals(update.get(i).getSerial())){
						//set the quantity of that item to be the quantity of the item from the updated list
						mp3.get(j).setQuantity(update.get(i).getQuantity());
					}
				}
				//open a buffered writer, this clears the file completely
				br = new BufferedWriter(new FileWriter("MP3.txt"));
				//close the writer
				br.close();
				//reopen the writer, but now the true parameter means that it can be appended
				br = new BufferedWriter(new FileWriter("MP3.txt",true));
				//for loop for the size of the list
				for(int j=0;j<mp3.size();j++){
					//split the information of the item by ", "
					String[] info = mp3.get(j).getInfo().split(", ");
					//string with all the informatoin that will go in the file
					String fileLine = info[0]+", "+info[1]+", "+info[2]+", "+info[3]+", "+info[4];
					//goto the next line in the file
					br.newLine();
					//write the line to the file
					br.write(fileLine);
				}
				br.close();
				break;
			}
		}
	}

}