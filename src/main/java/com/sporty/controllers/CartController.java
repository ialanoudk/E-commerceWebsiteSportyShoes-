package com.sporty.controllers;   
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sporty.impl.CartDAO;
import com.sporty.impl.CategoryDAO;
import com.sporty.impl.ProductDAO;
import com.sporty.models.Cart;
import com.sporty.models.Category;
import com.sporty.models.Product;
import com.sporty.models.ProductData;
 
@Controller  
public class CartController {   
	 
    @Autowired
    CartDAO cartDAO;
    
    @Autowired
    ProductDAO productDAO;
 
    @Autowired
    CategoryDAO categoryDAO;
    
    @RequestMapping({ "/addtocart" })
    public String addCartHandler(Model m,
    		HttpSession httpSession, 
    		@RequestParam(value = "id", defaultValue = "") int id){ 

    	if(httpSession.getAttribute("id") == null) {
    		return "redirect:/login";
    	}
    	int userId = (Integer)httpSession.getAttribute("id");
    	if(id>0)
    	{
    		Cart c = new Cart();
    		c.setCustID(Integer.toString(userId));
    		c.setProductID(Integer.toString(id));
    		c.setQuantity("1");
    		cartDAO.save(c);
        	m.addAttribute("msg", "Item Added to Cart successfully"); 
    	}
    	Product product = productDAO.getProduct(id); 
    	m.addAttribute("product", product ); 
        return "product";  
    }   
 

    @RequestMapping({ "/cart" })
    public String viewCartHandler(Model m,
    		HttpSession httpSession){ 
    	if(httpSession.getAttribute("id") == null) {
    		return "redirect:/login";
    	}
    	int userId = (Integer)httpSession.getAttribute("id");
    	List <ProductData> items = cartDAO.getCartsProducts(userId);
    	List <Category> Categlist = categoryDAO.getCategorys();  
    	m.addAttribute("listCategories", Categlist);
    	m.addAttribute("items", items );
        return "cart";  
    }   

    @RequestMapping({ "/removeitem" })
    public String removeitemtHandler(Model m,
    		HttpSession httpSession,
    		@RequestParam(value = "id", defaultValue = "0") int id){ 

    	if(httpSession.getAttribute("id") == null) {
    		return "redirect:/login";
    	}
    	int userId = (Integer)httpSession.getAttribute("id");
    	if(id>0)
    	{
    		Cart c = new Cart();
    		c.setId(id); 
    		if(cartDAO.delete(id)>0)
    			m.addAttribute("msg", "Item Deleted from cart successfully"); 
    		else
    		{
    			return "redirect:/cart";
    		}
    	}else {
    		return "redirect:/cart";
    	}
    	List <ProductData> items = cartDAO.getCartsProducts(userId);
    	List <Category> Categlist = categoryDAO.getCategorys();  
    	m.addAttribute("listCategories", Categlist);
    	m.addAttribute("items", items );
        return "cart";  
    }   
    
}  