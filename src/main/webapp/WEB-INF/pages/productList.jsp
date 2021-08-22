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
					<h2>List of categories</h2>    
						<c:if test="${msg != null}">
							<div class="note">${msg}</div>
						</c:if>
						<c:if test="${not empty items}">
							<table border="1" width="100%">
								<tr>
									<th>ID</th>
									<th>Name</th> 
									<th>Price</th> 
									<th>Image</th> 
									<th>Edit</th> 
									<th>Delete</th> 
								</tr>
	
							    <c:forEach items="${items}" var="item"> 
						            <tr>
										<td>${item.id}</td>
										<td>${item.productName}</td>
										<td>${item.price}</td>
										<td><img src="${item.productImage}" width="80" height="80"/></td>
										<td><a href="newproduct?id=${item.id}">update</a></td>
										<td><a href="deleteProduct?id=${item.id}">Delete</a></td> 
									</tr>	    
							    </c:forEach>
							</table>	 
						</c:if>
						<c:if test="${empty items}">
							<div class="note">No products added to database</div>
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

 