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
						<c:if test="${userId > 0}">
							<h2>Your Orders</h2>  
						</c:if>  
						<c:if test="${userId == 0}">
							<h2>List of all orders</h2>  
						</c:if>  
						<c:if test="${msg != null}">
							<div class="note">${msg}</div>
						</c:if>
						<c:if test="${not empty items}">
							<table border="1" width="100%">
								<tr>
									<th>ID</th>
									<c:if test="${userId == 0}">
										<th>Customer Name</th>
									</c:if>
									<th>Order Date</th>
									<th>View Detail</th> 
								</tr> 
								<c:if test="${userId == 0}">
								    <c:forEach items="${items}" var="item"> 
							            <tr>
											<td>${item.order.id}</td>
											<th>${item.customer.name}</th> 
											<td>${item.order.orderDate}</td> 
											<td><a href="orderDetail?id=${item.order.id}">View Detail</a></td> 
										</tr>	    
								    </c:forEach>
							    </c:if>
								<c:if test="${userId > 0}">
								    <c:forEach items="${items}" var="item"> 
							            <tr>
											<td>${item.id}</td> 
											<td>${item.orderDate}</td> 
											<td><a href="orderDetail?id=${item.id}">View Detail</a></td> 
										</tr>	    
								    </c:forEach>
							    </c:if>
							    
							</table>	 
						</c:if>
						<c:if test="${empty items}">
							<div class="note">You haven't do any order</div>
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

 