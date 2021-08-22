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

import com.sporty.impl.CategoryDAO;
import com.sporty.impl.ProductDAO;
import com.sporty.models.Category;
import com.sporty.models.Product;
 
@Controller  
public class HomeController {   
	 
    @Autowired
    CategoryDAO categoryDAO;
    
    @Autowired
    ProductDAO productDAO;

    @RequestMapping({ "/" })
    public String loginHandler(Model m, HttpSession httpSession, 
    		@RequestParam(value = "id", defaultValue = "0") int id){ 
    	List <Category> Categlist = categoryDAO.getCategorys();
    	List <Product> Prodlist = productDAO.getProducts(id);
    	m.addAttribute("listCategories", Categlist );
    	m.addAttribute("listProducts", Prodlist);
        return "home";  
    }    
}  