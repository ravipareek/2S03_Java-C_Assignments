/*
 * @Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * @MacID: ravip2, shahk24, olivepjm
 * @Student_Number:  1407109, 1419350, 1430273
 * @Description: Declares the abstract class Item
 */

public abstract class Item {
	//Declares int price
	protected int price;
	//Declares int sNo
	protected int sNo;
	//Declares String maker
	protected String maker;
	//Declares String name
	protected String name;
	//Declares int quantity
	protected int quantity;
	//Declares String type
	protected String type;
	//Declares abstract method getInfo()
	public abstract String getInfo();
	//Declares abstract method getPrice()
	public abstract int getPrice();
	//Declares method getSerial()
	public String getSerial(){
		//Returns this.sNo
		return sNo+"";
	}
	//Declares method getName()
	public String getName(){
		//Returns this.name
		return name;
	}
	//Declares method getQuantity()
	public int getQuantity(){
		//Returns this.quantity
		return quantity;
	}
	//Declares method setQuantity()
	public void setQuantity(int amount){
		//Sets this.quantity to amount
		quantity = amount;
	}
	//Declares method getType()
	public String getType(){
		//Returns this.type
		return type;
	}
}