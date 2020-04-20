<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<title>LogedIn</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/static/css/style.css" />
	</head>
	<body background="/resources/static/images/2.jpg"/>
	<% String email=request.getParameter("email"); 
	String mob=request.getParameter("mobNumber");
	String a1=request.getParameter("addressLine1");
	String a2=request.getParameter("addressLine2");
	String city=request.getParameter("city");
	
	%>
        <div class="header">
            <a href="#default" class="logo"><img src="/resources/static/images/logo.png" class="logo"alt="logo"></a>
            <div class="header-right">
              <a class="button" href="loginUser">Home</a>
              <a class="button" href="#contact">Contact</a>
              <a class="button" href="#about">About</a>
              <div class="login-button">
                    <a class="button" href="logout">Log Out</a>
              </div>
           
            </div>
          </div>
        
        <section class="section" style= "background-image: url(/resources/static/images/2.jpg);"> 
                   
          <nav>
            <div class="user-info">
                <img class="user-image" src="<c:url value="/resources/static/images/user.jpg"/>" alt="User Image" />
                <h1 style="color:white;"><strong>Hi! ${user.firstName}</strong><br>E-Mail: ${email}<br>
                Connection Id: ${conection.connectionId}<br>
                Mob: ${mob}<br>
                Address: ${a1}<br>
                ${a2}<br>
                ${city} 
                </h1>
            </div>
          </nav>
           <div class="menu">
            <a class="button" href="index.html">Pay Bill</a>&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <a class="button" href="bill-list">Get Bill Details</a>&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <a class="button" href="#about">Change Password</a>
          </div>
<!-- <div align="right" style="color:white;"><a href="logout">Log Out<%out.print(request.getSession().getAttribute("user")); %></a></div> -->
   <hr>
    <h1>Bill List List</h1>
    <hr>
    <!-- <a href="/new">Create New Product</a> -->
    <br/><br/>
    <table border="1" cellpadding="10" style="color:white;">
        <thead>
            <tr>
           
                <th>Bill ID</th>
                <th>Consumed Units</th>
                <th>Generated On</th>
                <th>Submitted On</th>
                <th>Due Date</th>
                <th>Bill Amount</th>
                <th>Bill Status</th>
            
            </tr>
        </thead>
        <tbody>
        <c:forEach var="bill" items="${billList}">s
        
        
            <tr>
               
                <td>${bill.billId}</td>
                <td>${bill.consumedUnits}</td>
                 <td>${bill.generationDate}</td>
                <td>${bill.submitDate}</td>
                <td>${bill.dueDate}</td>
                <td>${bill.billAmount}</td>
                <td>${bill.status}</td>
               
        
        
            </tr>
             </c:forEach>
        </tbody>
    </table>
</div>   
        <footer>
         <!-- copyright -->
		<div class="copyright">
                <p>© 2020 E-PowerBilling @ NIIT Technologies . All rights reserved | Design by : Run Time Terror</p>
            </div>
            <!-- //copyright --> >
        </footer>
        
        </body>
</html>