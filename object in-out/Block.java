package test;

import java.io.Serializable;

//Serializable ����ȭ

public class Block implements Serializable{
	
	public String transactoin;
	
	public Block(){
		this.transactoin = "Hello";
	}
	
	public void setTransaction(String transaction){
		this.transactoin = transaction;
	}
	
	public String getTransaction(){
		return this.transactoin;
	}

}
