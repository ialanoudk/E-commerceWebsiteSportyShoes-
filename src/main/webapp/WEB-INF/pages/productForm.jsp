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
					<ul> 
					 	<%@ include file='user_menu.html' %> 
					</ul>
					
				</div>
				<div class="main">
					<h2>Add/Update Product</h2> 
					<c:if test="${error == false}">
			            <div class="note">${error}</div>
			        </c:if>
					<c:if test="${msg != null}">
			            <div class="note">${msg}</div>
			        </c:if>
					
					<form action="./newproduct" method="post" class="form">
						<div>Category Name:</div>
						<div><input type="text" name="ProductName" value="${product.productName}" required></div> 
						<c:if test="${product.id > 0}">
							<div><input type="hidden" name="productId"  value="${product.id}" required></div> 
						</c:if>
			           <div>
			               <td>Category *</td>
			               <div style="color:red;">
			                   <select name="CategoryID">
			                  		<c:forEach items="${categories}" var="category">
			                   			<option value="${category.id}">${category.name}</option>
			   						</c:forEach>
			                   </select>
			               </div> 
			           </div>
			           
						<div>Image URL:</div>
						<div><input type="text" name="ProductImage" value="${product.productImage}" required></div> 
			           
						<div>Price</div>
						<div><input type="text" name="Price" value="${product.price}" required></div> 
			           
						<div>Description</div>
						<div><textarea name="Description" rows="15" cols="40" required>${product.description}</textarea> </div> 
			           
						<div><input type="submit" value="Save"></div> 
					</form>
				</div>
				<div class="clear"></div>				
			</div>
			
			<div class="footer">
				 <%@ include file='footer.html' %>
			</div>
		</div>
	</body>
</html>
