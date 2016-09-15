/*
 * @Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * @MacID: ravip2, shahk24, olivepjm
 * @Student_Number:  1407109, 1419350, 1430273
 * @Description: This class is inherited from Readable class, this class is inherited from item class, declares Readable type eBook
 */

public class eBook extends Readable{
	@Override
	// overrides get price from readable class for environmental tax
	public int getPrice(){
		//Returns price
		return price;
	}
	// Book object constructor
	public eBook (int serial, String title, String author, int cost, int amount){
		//sets serial number
		sNo = serial;
		// sets product name
		name = title;
		//sets product author
		authorName = author;
		// sets product price
		price = cost;
		// sets product inventory
		quantity = amount;
		//sets type to eBook
		type = "eBook";
	}
}