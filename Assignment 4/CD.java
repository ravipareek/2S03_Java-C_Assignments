/*
 * @Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * @MacID: ravip2, shahk24, olivepjm
 * @Student_Number:  1407109, 1419350, 1430273
 * @Description: This class is inherited from Audio class, this class is inherited from item class, declares Audio type CD
 */

public class CD extends Audio{
	@Override
	// overrides get price from audio class for environmental tax
	public int getPrice(){
		//Returns price
		return price;
	}
	// CD object constructor
	public CD (int serial, String title, String author, int cost, int amount){
		//sets serial number
		sNo = serial;
		// sets product name
		name = title;
		//sets product artist
		artistName = author;
		// sets product price
		price = cost;
		// sets product inventory
		quantity = amount;
		//sets type to CD
		type = "CD";
	}
}