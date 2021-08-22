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
import com.sporty.impl.ProductDAO;
import com.sporty.models.Category;
import com.sporty.models.Product;
import com.sporty.models.Product;
 
@Controller  
public class ProductController {   
	 
    @Autowired
    CategoryDAO categoryDAO;
    
    @Autowired
    ProductDAO productDAO;
  
    @RequestMapping({ "/product" })
    public String productHandler(Model m,
    		HttpSession httpSession, 
    		@RequestParam(value = "id", defaultValue = "") int id){ 

    	List <Category> Categlist = categoryDAO.getCategorys(); 
    	m.addAttribute("listCategories", Categlist ); 
    	
    	Product product = productDAO.getProduct(id); 
    	m.addAttribute("product", product ); 
        return "product";  
    }   
 

    @RequestMapping(value = { "/admin/newproduct" }, method = RequestMethod.GET)
    public String category(Model model,
    						@RequestParam(value = "id", defaultValue = "0") int id,
    						HttpSession httpSession){ 

    	if(httpSession.getAttribute("id") == null) {
    		return "redirect:/admin/login";
    	} 
        Product product = null;
 
        if (id > 0) {
        	product = productDAO.getProduct(id);
        } 
        
        List<Category> categories= categoryDAO.getCategorys();  
        model.addAttribute("categories",categories);  
        model.addAttribute("product",product); 
        return "productForm";
    }
  
    @RequestMapping(value = { "/admin/newproduct" }, method = RequestMethod.POST) 
    public String productSave(Model model, 
    		@RequestParam(value = "CategoryID", defaultValue = "") String CategoryID,
    		@RequestParam(value = "ProductName", defaultValue = "") String ProductName,
    		@RequestParam(value = "ProductImage", defaultValue = "") String ProductImage, 
    		@RequestParam(value = "Description", defaultValue = "") String Description, 
    		@RequestParam(value = "Price", defaultValue = "0") String Price, 
    		@RequestParam(value = "productId", defaultValue = "0") int productId,   
    		HttpSession httpSession) { 
    	if(httpSession.getAttribute("id") == null) {
    		return "redirect:/admin/login";
    	} 
    	Product product = new Product();

    	product.setCategoryID(CategoryID); 
    	product.setProductName(ProductName); 
    	product.setProductImage(ProductImage); 
    	product.setDescription(Description); 
    	product.setPrice(Price); 
    	
        try {
        	if(productId==0) {
        		product.setId(0);
        		productDAO.save(product);  
        	}else {
        		product.setId(productId);
            	productDAO.update(product); 
        	}
        } catch (Exception e) { 
            String message = e.getMessage();
            model.addAttribute("message", message); 
            return "productForm";
 
        }
        return "redirect:/admin/products";
    }


    @RequestMapping({ "/admin/products" })
    public String categoryListHandler(Model m,
    		@RequestParam(value = "msg", defaultValue = "") String msg){  
        List<Product> items= productDAO.getProducts(0);  
        m.addAttribute("items",items);
        m.addAttribute("msg",msg);
        return "productList";  
    }    
    

    @RequestMapping(value = { "/admin/deleteProduct" }, method = RequestMethod.GET)
    public String deleteCategory(Model model,
    						@RequestParam(value = "id", defaultValue = "0") int id,
    						HttpSession httpSession){ 

    	if(httpSession.getAttribute("adminLoggedIn") == null) {
    		return "redirect:/admin/login";
    	}  
 
        if (id > 0) {
        	if(productDAO.delete(id) > 0) {
        		model.addAttribute("msg", "Product Deleted Successfully");
        	}else {
        		model.addAttribute("msg", "Couldn't delete the product");
        	}
        }

        return "redirect:/admin/products";
    }
  
}  