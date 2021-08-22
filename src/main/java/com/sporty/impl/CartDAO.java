package com.sporty.impl;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.RowMapper;
 
import com.sporty.models.Cart;
import com.sporty.models.Product;
import com.sporty.models.ProductData;
  
public class CartDAO {  
	@Autowired
	ProductDAO productDAO;
	
	JdbcTemplate dbTemplate;  
	Cart p = null;
	public void setTemplate(JdbcTemplate dbTemplate) {  
	    this.dbTemplate = dbTemplate;  
	}  
	public int save(Cart cart){ 
		Cart temp = getCart(cart);
		String sql="insert into cart(CustID , Quantity,   ProductID ) "
	    		+ "values('"+cart.getCustID()+"','"+cart.getQuantity()+"','"+cart.getProductID()+"')";  
		if(temp != null)
		{
		    int qty = Integer.parseInt(temp.getQuantity()) +  Integer.parseInt(cart.getQuantity());
		    cart.setQuantity(Integer.toString(qty));
		    cart.setId(temp.getId());
		    return update(cart);
		}
		return dbTemplate.update(sql); 
	}   
	public int update(Cart cart){  
	    String sql="update cart set  "
	    		+ "CustID = '"+cart.getCustID()+"',"
	    		+ "Quantity = '"+cart.getQuantity()+"' ,"
	    		+ "ProductID = '"+cart.getProductID()+"' " 
	    		+ " where id="+cart.getId()+"";  
	    return dbTemplate.update(sql);  
	}  
	public int delete(int id){  
	    String sql="delete from cart where CustID="+id+"";  
	    return dbTemplate.update(sql);  
	}  

	public Cart getCart(int id){  
	    String sql="select * from cart where id=?";  
	    return dbTemplate.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Cart>(Cart.class));  
	}  

	public Cart getCart(Cart cart){  
	    String sql="select * from cart where CustID =? and ProductID =?";  
	    try {
	    	return dbTemplate.queryForObject(sql, new Object[]{cart.getCustID(), cart.getProductID()},new BeanPropertyRowMapper<Cart>(Cart.class)); 
	    }catch(Exception c) {
	    	return null;
	    }
	}
	 
	
	public List<Cart> getCarts(){  
	    return dbTemplate.query("select * from cart",new RowMapper<Cart>(){  
	        public Cart mapRow(ResultSet rs, int row) throws SQLException {  
	            Cart e=new Cart();  
	            e.setId(rs.getInt(1));   
	            e.setCustID(rs.getString(2)); 
	            e.setQuantity(rs.getString(3)); 
	            e.setProductID(rs.getString(4)); 
	            return e;  
	        }  
	    });  
	}
	public List<ProductData> getCartsProducts(int userId) {
		return dbTemplate.query("select * from cart where CustID = ?", new Object[]{userId},new RowMapper<ProductData>(){  
	        public ProductData mapRow(ResultSet rs, int row) throws SQLException {  
	            Cart c=new Cart();  
	            c.setId(rs.getInt(1)); 
	            c.setCustID(rs.getString(2)); 
	            c.setQuantity(rs.getString(3)); 
	            c.setProductID(rs.getString(4)); 
	            
	            Product p = productDAO.getProduct(rs.getInt(4));
	            ProductData pd = new ProductData(c, p);
	            return pd;  
	        }  
	    });  
	}
	 
}  