<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
	<head> 
		<style>
			<%@ include file='style.css' %>
		</style>
	</head>
	
	<body>
		<div class="body">
			<div class="header">
				 <img src="https://previews.123rf.com/images/gmast3r/gmast3r1706/gmast3r170600624/79995338-shopping-cart-discount-tag-sale-special-offer-banner-flat-vector-illustration.jpg"/> 
			</div>
			
			
			<div class="mainContent">
				<div class="leftSide"> 
					<%@ include file='user_menu.html' %>
				</div>
				<div class="main">
					<h2>Order Details</h2>    
						<c:if test="${msg != null}">
							<div class="note">${msg}</div>
						</c:if>
						<ul>
							<li>Order ID: ${order.id}</li>
							<li>Order Date: ${order.orderDate}</li> 
						</ul>
						<c:if test="${not empty items}">
							<table border="1" width="100%">
								<tr>
									<th>Product</th>
									<th>QTY</th>
									<th>Unit Price</th>
									<th>Total</th> 
								</tr>
								<c:set var = "sum" scope = "session" value = "${0}"/>
	
							    <c:forEach items="${items}" var="item"> 
						            <tr>
										<td>${item.product.productName}</td>
										<td>${item.cart.quantity}</td>
										<td>${item.product.price}</td>
										<td>${item.product.price * item.cart.quantity}</td> 
										<c:set var = "sum"  value = "${sum + item.product.price * item.cart.quantity }"/>
									</tr>	    
							    </c:forEach>
							</table>	
							<b>TOTAL : ${sum}</b>  
						</c:if>
						<c:if test="${empty items}">
							<div class="note">Cart is empty</div>
						</c:if>
				   <div class="clear"></div>	
				</div>			
			</div>
			<div class="clear"></div>	
			<div class="footer">
				 <%@ include file='footer.html' %>
			</div>
		</div>
	</body>
</html>

 