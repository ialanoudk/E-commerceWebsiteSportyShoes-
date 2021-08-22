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
					<h2>Login</h2> 
					<c:if test="${loginProcess != null}">
			            <h3>Unknown User</h3>
			        </c:if>
					
					<form action="./register" method="post" class="form">
						<div>User Name:</div>
						<div><input type="text" name="name" required></div>
						<div>User Email:</div>
						<div><input type="email" name="email" required></div>
						 
						<div>Password:</div>
						<div><input type="password"  name="pass" required></div>
						<div><input type="submit" value="Register"></div>
						<div>Have account? <a href="login.jsp">Sing in</a></div>
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
