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
					<h2>List of Products</h2>   
				   <c:forEach items="${listProducts}" var="product">
				       <div class="productBox">
			            	<div class="product-image">
			                   <img src="${product.productImage}" />
			              	</div>
				            <div>Name: ${product.productName}</div> 
				            <div>Price: ${product.price}</div>   
				            <div><a href='./product?id=${product.id}'>View Detail</a> </div> 
				       </div>
				 
				   </c:forEach>
					<c:if test="${empty listProducts}">
						<div class="note">No products in this category</div>
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

 