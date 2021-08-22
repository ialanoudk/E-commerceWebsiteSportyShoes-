package com.sporty.impl;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.List;  
import org.springframework.jdbc.core.BeanPropertyRowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.RowMapper;

import com.sporty.models.Category;
  
public class CategoryDAO {  
	JdbcTemplate dbTemplate;  
	Category c = null;
	public void setTemplate(JdbcTemplate dbTemplate) {  
	    this.dbTemplate = dbTemplate;  
	}  
	public int save(Category category){  
	    String sql="insert into category(name) values('"+category.getName()+"')";  
	    return dbTemplate.update(sql);  
	}  
	public int update(Category category){  
	    String sql="update category set name='"+category.getName()+"' where id="+category.getId()+"";  
	    return dbTemplate.update(sql);  
	}  
	public int delete(int id){  
	    String sql="delete from category where id="+id+"";  
	    return dbTemplate.update(sql);  
	}  
	public Category getCategory(int id){  
	    String sql="select * from category where id=?";  
	    return dbTemplate.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Category>(Category.class));  
	}  
	 
	
	public List<Category> getCategorys(){  
	    return dbTemplate.query("select * from category",new RowMapper<Category>(){  
	        public Category mapRow(ResultSet rs, int row) throws SQLException {  
	            Category e=new Category();  
	            e.setId(rs.getInt(1));  
	            e.setName(rs.getString(2));     
	            return e;  
	        }  
	    });  
	}  
}  