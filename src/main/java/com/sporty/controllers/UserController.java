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
import com.sporty.impl.CustomerDAO;
import com.sporty.impl.ProductDAO;
import com.sporty.models.Cart;
import com.sporty.models.Category;
import com.sporty.models.User;
import com.sporty.models.Product;
 
@Controller  
public class UserController {   

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    ProductDAO productDAO;
    
    @Autowired
    CustomerDAO customerDAO;

    @RequestMapping(value="/login",method = RequestMethod.POST)  
    public String loginHandler(Model m, 
    		@RequestParam("email") String userName,
    		@RequestParam("pass") String password,
    		HttpSession httpSession){ 
    	if(customerDAO.login(userName, password)) {   
    		User t = customerDAO.getCustomer();
    		httpSession.setAttribute("userLoggedIn", true); 
    		httpSession.setAttribute("id", t.getId());
    		httpSession.setAttribute("userName", t.getName()); 
            return "redirect:/";   
    	}else {
    		m.addAttribute("error","Invalid User");
    	}
        return "login";  
    }   

    @RequestMapping(value="/login",method = RequestMethod.GET)  
    public String loginPageHandler(Model m ,
    		HttpSession httpSession){ 
    	 
        return "login";  
    }   
    


    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String registerHandler(HttpServletRequest request, //
            Model model,  
    		@RequestParam("name") String name,
    		@RequestParam("email") String email,
    		@RequestParam("pass") String pass) {
  
    	User c = new User();
    	c.setEmail(email);
    	c.setName(name);
    	c.setPassword(pass);
    	if(customerDAO.save(c) > 0) {
    		model.addAttribute("msg", "You have successfully register, you can login now!");
    		return "login";
    	}else {
    		model.addAttribute("msg", "Some thing goes worng");
            return "register";
    	}
    }
    
 
    @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public String registerHandler(HttpServletRequest request, Model model,
    		HttpSession httpSession){  
    	if(httpSession.getAttribute("userLoggedIn")!= null ) {    
            return "home";    
    	} 

        return "register";
    }


    @RequestMapping({ "/logout" })
    public String listProductHandler(Model m,
    		HttpSession httpSession){   
        httpSession.invalidate();   
        return "redirect:login";  
    }   
    
}  