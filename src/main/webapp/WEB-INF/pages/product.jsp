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
					<h2>Product's Information</h2>    
						<c:if test="${msg != null}">
							<div class="note">${msg}</div>
						</c:if>
				       <div class="productBoxDetail">
			            	<div class="product-image">
			                   <img src="${product.productImage}" />
			              	</div>
				            <div>Name: ${product.productName}</div>  
				            <div>Price: ${product.price}</div>   
				            <div><h2>Description</h2> ${product.description}</div>   
				            <div class="addToCart"><a href='./addtocart?id=${product.id}'>Add To Cart</a> </div> 
				       </div>
				  
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

 