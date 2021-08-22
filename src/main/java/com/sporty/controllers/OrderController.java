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

import com.sporty.impl.OrderDAO;
import com.sporty.impl.CategoryDAO;
import com.sporty.impl.ProductDAO;
import com.sporty.models.Cart;
import com.sporty.models.Category;
import com.sporty.models.Order;
import com.sporty.models.OrderData;
import com.sporty.models.Product;
import com.sporty.models.ProductData;
 
@Controller  
public class OrderController {   
	 
    @Autowired
    OrderDAO orderDAO;
    
    @Autowired
    ProductDAO productDAO;
 
    @Autowired
    CategoryDAO categoryDAO;
     

    @RequestMapping({ "/myorders" })
    public String viewCartHandler(Model m,
    		HttpSession httpSession){ 
    	if(httpSession.getAttribute("id") == null) {
    		return "redirect:/login";
    	}
    	int userId = (Integer)httpSession.getAttribute("id");
    	List <Order> items = orderDAO.getOrders(userId);
    	List <Category> Categlist = categoryDAO.getCategorys();  
    	m.addAttribute("listCategories", Categlist);
    	m.addAttribute("userId", userId );
    	m.addAttribute("items", items );
        return "order";  
    }   
 

    @RequestMapping({ "/admin/orders" })
    public String viewOrdersForAdminHandler(Model m,
    		HttpSession httpSession){ 
    	if(httpSession.getAttribute("id") == null) {
    		return "redirect:/admin/login";
    	} 
    	int userId = 0;
    	List <OrderData> items = orderDAO.getOrders();
    	List <Category> Categlist = categoryDAO.getCategorys();  
    	m.addAttribute("listCategories", Categlist);
    	m.addAttribute("userId", userId );
    	m.addAttribute("items", items );
        return "order";  
    }   
 
    
    @RequestMapping({ "/confirmOrder" })
    public String confirmOrderHandler(Model m,
    		HttpSession httpSession){ 
    	if(httpSession.getAttribute("id") == null) {
    		return "redirect:/login";
    	}
    	int userId = (Integer)httpSession.getAttribute("id");
    	orderDAO.confirm(userId);
    	List <Order> items = orderDAO.getOrders(userId);
    	List <Category> Categlist = categoryDAO.getCategorys();  
    	m.addAttribute("listCategories", Categlist);
    	m.addAttribute("items", items );
        return "order";  
    }

    
    @RequestMapping({ "/orderDetail" })
    public String orderDetailHandler(Model m,
    		HttpSession httpSession,
    		@RequestParam(value = "id", defaultValue = "0") int id){ 
    	if(httpSession.getAttribute("id") == null) {
    		return "redirect:/login";
    	}
    	int userId = (Integer)httpSession.getAttribute("id");
    	orderDAO.confirm(userId);
    	Order order = orderDAO.getOrder(id);
    	List <ProductData> items = orderDAO.getOrdersDetail(id);
    	List <Category> Categlist = categoryDAO.getCategorys();  
    	m.addAttribute("listCategories", Categlist);
    	m.addAttribute("items", items );
    	m.addAttribute("order", order );
        return "orderdetails";  
    }
    
    @RequestMapping({ "/admin/orderDetail" })
    public String adminOrderDetailHandler(Model m,
    		HttpSession httpSession,
    		@RequestParam(value = "id", defaultValue = "0") int id){ 
    	if(httpSession.getAttribute("id") == null) {
    		return "redirect:/admin/login";
    	}
    	int userId = (Integer)httpSession.getAttribute("id");
    	orderDAO.confirm(userId);
    	Order order = orderDAO.getOrder(id);
    	List <ProductData> items = orderDAO.getOrdersDetail(id);
    	List <Category> Categlist = categoryDAO.getCategorys();  
    	m.addAttribute("listCategories", Categlist);
    	m.addAttribute("items", items );
    	m.addAttribute("order", order );
        return "orderdetails";  
    }
}  