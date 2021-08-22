package com.sporty.impl;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.List;  
import org.springframework.jdbc.core.BeanPropertyRowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.RowMapper;

import com.sporty.models.Product;
  
public class ProductDAO {  
	JdbcTemplate dbTemplate;  
	Product c = null;
	public void setTemplate(JdbcTemplate dbTemplate) {  
	    this.dbTemplate = dbTemplate;  
	}  
	public int save(Product product){  
	    String sql="insert into products(ProductName , CategoryID,   ProductImage, Description, Price) "
	    		+ "values('"+product.getProductName()+"','"+product.getCategoryID()+"','"+product.getProductImage()+"','"
	    		+product.getDescription()+"','"+product.getPrice()+"')"; 
	    return dbTemplate.update(sql);  
	}  
	public int update(Product product){  
	    String sql="update products set  "
	    		+ "ProductName = '"+product.getProductName()+"',"
	    		+ "CategoryID = '"+product.getCategoryID()+"' ,"
	    		+ "ProductImage = '"+product.getProductImage()+"' ,"
	    		+ "Description  = '"+product.getDescription()+"' , "
	    		+ "Price  = '"+product.getPrice()+"' "
	    		+ " where id="+product.getId()+"";  
	    return dbTemplate.update(sql);  
	}  
	public int delete(int id){  
	    String sql="delete from products where id="+id+"";  
	    return dbTemplate.update(sql);  
	}  
	public Product getProduct(int id){  
	    String sql="select * from products where id=?"; 
	    System.out.println(sql+id);
	    return dbTemplate.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Product>(Product.class));  
	}  
	 
	
	public List<Product> getProducts(int id){  
		String sql = "select * from products ";
		if(id>0) {
			sql = sql +" where CategoryID = '"+id+"'";
		}
	    return dbTemplate.query(sql,new RowMapper<Product>(){  
	        public Product mapRow(ResultSet rs, int row) throws SQLException {  
	            Product e=new Product();  
	            e.setId(rs.getInt(1));  
	            e.setCategoryID(rs.getString(2)); 
	            e.setProductName(rs.getString(3));
	            e.setProductImage(rs.getString(4));
	            e.setDescription(rs.getString(5));
	            e.setPrice(rs.getString(6));
	            return e;  
	        }  
	    });  
	}  
}  