package main;

public class Msg {

	String id;
	Object message;
	
	Msg(String id,Object message){
		this.id=id;
		this.message=message;
	}
	
	@Override
	public String toString() { 
	    return "This is your msg: " +id;
	} 
}
//ran a zain
