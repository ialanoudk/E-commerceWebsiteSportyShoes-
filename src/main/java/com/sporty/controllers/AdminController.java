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
 
import com.sporty.impl.AdminDAO;
import com.sporty.impl.CategoryDAO;
import com.sporty.impl.CustomerDAO;
import com.sporty.impl.ProductDAO;
import com.sporty.models.Category;
import com.sporty.models.User;
import com.sporty.models.Product;
 
@Controller  
public class AdminController {   

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    ProductDAO productDAO;
    
    @Autowired
    AdminDAO adminDAO;
     
    @Autowired
    CustomerDAO customerDAO;

    @RequestMapping(value="/admin/login",method = RequestMethod.POST)  
    public String loginHandler(Model m, 
    		@RequestParam("email") String userName,
    		@RequestParam("pass") String password,
    		HttpSession httpSession){ 
    	if(adminDAO.login(userName, password)) {   
    		User t = adminDAO.getAdmin();
    		httpSession.setAttribute("adminLoggedIn", true); 
    		httpSession.setAttribute("id", t.getId());
    		httpSession.setAttribute("userName", t.getName()); 
            return "redirect:/admin/";   
    	}else {
    		m.addAttribute("error","Invalid User");
    	}
        return "admin_login";  
    }   

    @RequestMapping(value="/admin/login",method = RequestMethod.GET)  
    public String loginPageHandler(Model m ,
    		HttpSession httpSession){ 
    	 
        return "admin_login";  
    }   
    


    @RequestMapping({ "/admin/" })
    public String addCartHandler(Model m,
    		HttpSession httpSession){ 

    	if(httpSession.getAttribute("adminLoggedIn") == null) {
    		return "redirect:/admin/login";
    	} 
        return "admin_home";  
    }   

    @RequestMapping({ "/admin/logout" })
    public String listProductHandler(Model m,
    		HttpSession httpSession){   
        httpSession.invalidate();   
        return "redirect:login";  
    }   
 
 
    
 
    @RequestMapping(value = { "/admin/newcategory" }, method = RequestMethod.GET)
    public String category(Model model,
    						@RequestParam(value = "id", defaultValue = "0") int id,
    						HttpSession httpSession){ 

    	if(httpSession.getAttribute("adminLoggedIn") == null) {
    		return "redirect:/admin/login";
    	} 
        Category category = null;
 
        if (id > 0) {
        	category = categoryDAO.getCategory(id);
        }
        if (category == null) {
        	category = new Category(); 
        } 
        List<Category> categories=categoryDAO.getCategorys();  
        model.addAttribute("list",categories);  
        model.addAttribute("category",category); 
        return "category";
    }
  
    @RequestMapping(value = { "/admin/newcategory" }, method = RequestMethod.POST) 
    public String productSave(Model model, // 
    		@RequestParam("name") String name, 
    		@RequestParam(value = "categoryId", defaultValue = "0") int categoryId,  
    		HttpSession httpSession) { 
    	if(httpSession.getAttribute("adminLoggedIn") == null) {
    		return "redirect:/admin/login";
    	} 
    	Category categ = new Category();
    	categ.setName(name); 
        try {
        	if(categoryId==0) {
        		categoryDAO.save(categ);  
        	}else {
            	categ.setId(categoryId);
            	categoryDAO.update(categ); 
        	}
        } catch (Exception e) { 
            String message = e.getMessage();
            model.addAttribute("message", message); 
            return "category";
 
        }
        return "redirect:/admin/categories";
    }


    @RequestMapping({ "/admin/categories" })
    public String categoryListHandler(Model m,HttpSession httpSession,
    		@RequestParam(value = "msg", defaultValue = "") String msg){  
    	if(httpSession.getAttribute("adminLoggedIn") == null) {
    		return "redirect:/admin/login";
    	} 
        List<Category> items=categoryDAO.getCategorys();  
        m.addAttribute("items",items); 
        m.addAttribute("msg",msg);
        return "categoryList";  
    }   
    

    @RequestMapping({ "/admin/users" })
    public String usersListHandler(Model m,
    		@RequestParam(value = "name", defaultValue = "") String name,
    		HttpSession httpSession)
    {  
    	if(httpSession.getAttribute("adminLoggedIn") == null) {
    		return "redirect:/admin/login";
    	} 
        List<User> items=customerDAO.getCustomers(name);  
        m.addAttribute("items",items);
        return "users";  
    }   
    
    


    @RequestMapping(value = { "/admin/profile" }, method = RequestMethod.GET)
    public String adminAccountInfo(Model model, HttpSession httpSession,
    		 @RequestParam(value = "msg", defaultValue = "") String msg) {
    	if(httpSession.getAttribute("id")== null ) {    
            return "redirect:/admin/login";    
    	} 
    	int id = (int)httpSession.getAttribute("id");
        User data = adminDAO.getAdmin(id);

        model.addAttribute("data", data);
        model.addAttribute("msg", msg); 
        return "profile";
    }
 

    
    @RequestMapping(value = { "/admin/profile" }, method = RequestMethod.POST)
    public String updateAccHandler(HttpServletRequest request, //
            Model model, // 
    		@RequestParam("name") String userName,
    		@RequestParam("email") String userEmail,
    		@RequestParam("password") String password, HttpSession httpSession) {
  
    	if(httpSession.getAttribute("id")== null ) {    
            return "redirect:/login";    
    	} 
    	int id = (int)httpSession.getAttribute("id");
    	User user = new User();
    	user.setId(id);
    	user.setEmail(userEmail);
    	user.setName(userName);
    	user.setPassword(password);
    	if(adminDAO.update(user) > 0) {
    		model.addAttribute("msg", "Profile is updated sucessfully!"); 
    		User data = adminDAO.getAdmin(id); 
            model.addAttribute("userDetails", data);
    	}else {
    		model.addAttribute("msg", "Couldn't update the profile");
    	}
        return "redirect:/admin/profile";
    }


    @RequestMapping(value = { "/admin/deleteCategory" }, method = RequestMethod.GET)
    public String deleteCategory(Model model,
    						@RequestParam(value = "id", defaultValue = "0") int id,
    						HttpSession httpSession){ 

    	if(httpSession.getAttribute("adminLoggedIn") == null) {
    		return "redirect:/admin/login";
    	} 
        Category category = null;
 
        if (id > 0) {
        	if(categoryDAO.delete(id) > 0) {
        		model.addAttribute("msg", "Category Deleted Successfully");
        	}
        }

        return "redirect:/admin/categories";
    }
  
    
}  