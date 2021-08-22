package com.sporty.models;  
  
public class Cart {  
	private int id;  
	private String CustID , Quantity, ProductID;    
	  
	public int getId() {  
	    return id;  
	}  
	public void setId(int id) {  
	    this.id = id;  
	}
	public String getCustID() {
		return CustID;
	}
	public void setCustID(String custID) {
		CustID = custID;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	public String getProductID() {
		return ProductID;
	}
	public void setProductID(String productID) {
		ProductID = productID;
	}   
  
}  