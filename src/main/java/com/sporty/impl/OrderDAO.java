package com.sporty.impl;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.List;
import java.sql.Connection; 

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.sporty.models.Cart;
import com.sporty.models.Order;
import com.sporty.models.OrderData;
import com.sporty.models.Product;
import com.sporty.models.ProductData;
import com.sporty.models.User; 
  
public class OrderDAO {  
	@Autowired
	ProductDAO productDAO;
	@Autowired
	CartDAO cartDAO;
	@Autowired
	CustomerDAO customerDAO;
	
	JdbcTemplate dbTemplate;  
	Order p = null;
	public void setTemplate(JdbcTemplate dbTemplate) {  
	    this.dbTemplate = dbTemplate;  
	}  
	
	   
	
	public int update(Order order){  
	    String sql="update order set  "
	    		+ "CustID = '"+order.getCustID()+"',"
	    		+ "OrderDate = '"+order.getOrderDate()+"' " 
	    		+ " where id="+order.getId()+"";  
	    return dbTemplate.update(sql);  
	}  
	public int delete(int id){  
	    String sql="delete from orders where id="+id+"";  
	    return dbTemplate.update(sql);  
	}  

	public Order getOrder(int id){  
	    String sql="select * from orders where id=?";  
	    return dbTemplate.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Order>(Order.class));  
	}  
 
	 

	public List<Order> getOrders(int id){
		String sql = "select * from orders";
		if(id > 0)
		{
			sql = sql + " where CustID = "+id;
		}
	    return dbTemplate.query(sql,new RowMapper<Order>(){  
	        public Order mapRow(ResultSet rs, int row) throws SQLException {  
	            Order e=new Order();  
	            e.setId(rs.getInt(1));   
	            e.setCustID(rs.getString(2)); 
	            e.setOrderDate(rs.getString(3));  
	            return e;  
	        }  
	    });  
	}

	public List<OrderData> getOrders(){
		String sql = "select * from orders"; 
	    return dbTemplate.query(sql,new RowMapper<OrderData>(){  
	        public OrderData mapRow(ResultSet rs, int row) throws SQLException {  
	            Order order=new Order();  
	            order.setId(rs.getInt(1));   
	            order.setCustID(rs.getString(2)); 
	            order.setOrderDate(rs.getString(3));
	            User customer = customerDAO.getCustomer(rs.getInt(2));
	            OrderData od = new OrderData(customer , order);
	            return od;  
	        }  
	    });  
	}
	public List<ProductData> getOrdersDetail(int orderId) {
		return dbTemplate.query("select * from orderdetails where OrderID = ?", new Object[]{orderId},new RowMapper<ProductData>(){  
	        public ProductData mapRow(ResultSet rs, int row) throws SQLException {  
	            Cart c =new Cart();  
	            c.setId(rs.getInt(1)); 
	            c.setCustID(rs.getString(2));  
	            c.setProductID(rs.getString(3));
	            c.setQuantity(rs.getString(4));
	            
	            Product p = productDAO.getProduct(rs.getInt(3));
	            ProductData pd = new ProductData(c, p);
	            return pd;  
	        }  
	    });  
	}
	public int confirm(int userId) {
		String sql="insert into orders(CustID , OrderDate) "
	    		+ "values(?, now())";  
		 

		KeyHolder keyHolder = new GeneratedKeyHolder();
		dbTemplate.update(
		    new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps =
		                connection.prepareStatement(sql, new String[] {"id"});
		            ps.setInt(1, userId); 
		            return ps;
		        }
		    },
		    keyHolder); 
		int id =  keyHolder.getKey().intValue();
		if(id>0)
		{
			List<ProductData> pdata = cartDAO.getCartsProducts(userId);
			for (ProductData productData : pdata) {
				String sql2="insert into  orderdetails(OrderID , ProductID, Qty) values('"+id+"',"
						+ "'"+productData.getProduct().getId()+"',"
								+ "'"+productData.getCart().getQuantity()+"')  ";  
			    dbTemplate.update(sql2);  
			}
			cartDAO.delete(userId);
			return 1;
		}else {
			return 0;
		}
		
	}
	 
}  