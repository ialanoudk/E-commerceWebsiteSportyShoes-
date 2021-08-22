package com.sporty.impl;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.List;  
import org.springframework.jdbc.core.BeanPropertyRowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.RowMapper;

import com.sporty.models.User;
  
  
public class CustomerDAO {  
	JdbcTemplate template;  
	User customer = null;
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	public int save(User p){  
	    String sql="insert into customer(Name,Email,Password) values('"+p.getName()+"','"+p.getEmail()+"','"+p.getPassword()+"')";  
	    return template.update(sql);  
	}    
	public User getCustomer(int id){  
	    String sql="select * from customer where id=?";  
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<User>(User.class));  
	}   
	public boolean login(String Email, String Password){  
	    String sql="select * from customer where Email =? and Password = ?";   
	    try {
	    	customer = template.queryForObject(sql, new Object[]{Email,Password},new BeanPropertyRowMapper<User>(User.class));  
	    }catch(Exception e) {
	    	
	    }
	    return (customer != null);
	}
	
	public User getCustomer() {
		return customer;
	}
	
	public List<User> getCustomers(String customer_name){  
		String sql = "select * from customer";
		if(!customer_name.equals(""))
		{
			sql = sql +" where Name like '%"+customer_name+"%'";
			
		}
	    return template.query(sql,new RowMapper<User>(){  
	        public User mapRow(ResultSet rs, int row) throws SQLException {  
	            User e=new User();  
	            e.setId(rs.getInt(1));  
	            e.setName(rs.getString(2));  
	            e.setEmail(rs.getString(3));  
	            return e;  
	        }  
	    });  
	}  
}  