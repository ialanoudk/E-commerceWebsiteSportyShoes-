package com.sporty.models;  
  
public class OrderData {  
	private int id;   
	private User customer;
	private Order order;
	public int getId() {
		return id;
	}
	public OrderData(User customer, Order order) {
		this.customer = customer;
		this.order = order;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getCustomer() {
		return customer;
	}
	public void setCustomer(User customer) {
		this.customer = customer;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	} 
	 
}  