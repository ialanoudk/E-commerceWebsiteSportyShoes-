package com.sporty.impl;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.List;  
import org.springframework.jdbc.core.BeanPropertyRowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.RowMapper;

import com.sporty.models.User;
  
  
public class AdminDAO {  
	JdbcTemplate template;  
	User customer = null;
	public void setTemplate(JdbcTemplate template) {  
	    this.template = template;  
	}  
	public int update(User p){  
	    String sql="update admin set Name = '"+p.getName()+"' , Email = '"+p.getEmail()+"', Password = '"+p.getPassword()+"' where id = "+p.getId();   
	    return template.update(sql);  
	}    
	public User getAdmin(int id){  
	    String sql="select * from admin where id=?";  
	    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<User>(User.class));  
	}   
	public boolean login(String Email, String Password){  
	    String sql="select * from admin where Email =? and Password = ?";   
	    try {
	    	customer = template.queryForObject(sql, new Object[]{Email,Password},new BeanPropertyRowMapper<User>(User.class));  
	    }catch(Exception e) {
	    	
	    }
	    return (customer != null);
	}
	
	public User getAdmin() {
		return customer;
	}
	 
}  