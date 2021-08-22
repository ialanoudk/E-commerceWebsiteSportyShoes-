package com.sporty.models;  
  
public class ProductData {  
	private int id;   
	private Product product;
	private Cart cart; 
	
	public ProductData(Cart cart, Product p ) {
		setCart(cart);
		setProduct(p); 
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
}  