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
					<h2>Update Admin Profile</h2> 
					<c:if test="${error == false}">
			            <div class="note">${error}</div>
			        </c:if>
					<c:if test="${msg != null}">
			            <div class="note">${msg}</div>
			        </c:if>
					
					<form action="./profile" method="post" class="form">
						<div>Admin Name:</div>
						<div><input type="text" name="name" value="${data.name}" required></div>   
						<div>Email:</div>
						<div><input type="email" name="email" value="${data.email}" required></div> 
			           
						<div>Password</div>
						<div><input type="password" name="password" value="" required></div> 
			            
						<div><input type="submit" value="Update"></div> 
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
