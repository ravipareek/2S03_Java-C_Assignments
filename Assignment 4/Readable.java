/*
 * @Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * @MacID: ravip2, shahk24, olivepjm
 * @Student_Number:  1407109, 1419350, 1430273
 * @Description: This class is inherited from item class, declares item type readable.
 */


public class Readable extends Item{
	//Declare protected String authorName
	protected String authorName;
	@Override
	//Override abstract function get info from item class
	public String getInfo() {
		return sNo+", "+name+", "+authorName+", "+price+", "+quantity+", "+type;
	}
	@Override
	//Override abstract function getPrice from item class
	public int getPrice() {
		// returning price of audio item
		return price;
	}
}