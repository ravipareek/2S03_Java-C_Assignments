/*
 * @Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * @MacID: ravip2, shahk24, olivepjm
 * @Student_Number:  1407109, 1419350, 1430273
 * @Description: This class is inherited from item class, declares item type audio
 */

public class Audio extends Item{
	//Initializing protected variable artistName
	protected String artistName;
	@Override
	//Override abstract function get info from item class
	public String getInfo() {
		// returning serial number, name of file, artist name, price, quantity in stock, type of audio item
		return sNo+", "+name+", "+artistName+", "+price+", "+quantity+", "+type;
	}
	//Override abstract function getPrice from item class
	@Override
	public int getPrice() {
		// returning price of audio item
		return price;
	}
}