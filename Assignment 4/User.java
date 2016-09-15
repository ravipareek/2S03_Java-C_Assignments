/*
 * @Name: Pareekshit Ravi, Kunal Shah, Pedro Oliveira
 * @MacID: ravip2, shahk24, olivepjm
 * @Student_Number:  1407109, 1419350, 1430273
 * @Description: Declares user class
 */

public class User {
	//Declare private String username
	private String username;
	//Declare getUsername(String) function
	public String getUsername(String username){
		//Sets this.username to username
		this.username = username;
		//returns this.username
		return this.username;
	}
	//Declare returnUsername()
	public String returnUsername(){
		//returns this.username
		return username;
	}
}