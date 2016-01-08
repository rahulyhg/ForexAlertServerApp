<%@page import="java.util.List"%>

<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Foreign Exchange Currency Alert</title>

<link type="text/css" media="screen" rel="stylesheet" href="stylesheets/reset.css" />
<link type="text/css" media="screen" rel="stylesheet" href="stylesheets/main.css" />

</head>
<body>

<div id="header">
  <h1 style="font-family:cursive;"><b><font size="5" color="white">Foreign Exchange Alert</font></b></h1>
  <ul>
    <li><a href="index.html" class="active">Home</a></li>
    <li><a href="mailto:jatinsaxena86@gmail.com">Contact</a></li>
  </ul>
  <form action="#" method="post">
  <!--  <fieldset>
    <input type="text" value="Search website..." class="search" />
    </fieldset> -->
  </form>
</div>
<!-- //#header -->

<div id="sub-header">
  <div id="sub-header-inner">
      <h2 style="font-family:cursive;">GET OUR APP FOR DOWNLOAD.</h2>
      <br>
       <p class="highlight">Set the target currency rate in android app and get 
    the instant alert whenever the exchange rate crosses your target rate. </p>
    <p>We'll be your eyes and ears on the currency markets and make sure that you never miss your target rate. </p>
    <p class="learn-more"><a href="interior.html">Download App</a></p>
    <!-- <img src="images/layout/sub_header_img.png" alt="" />  </div> -->
    
    <img src="images/layout/Exchange-Rate.png" alt="" /> </div> 
</div>
<!-- //#sub-header -->

<div id="content" class="clearfix">
<div id="content-inner"> 
  
    <!--  <h3>List of Currently Configured Devices</h3> -->
      <div class="column">   
            <h2>About</h2>
      <br></br>
	<form action="getDevices" method="GET">
	 <p>Forex Alert is a <strong> reliable and near about real time service</strong> that alerted user with its configured <strong> currency exchange rate </strong>. To get the list of currently configured devices, Please <strong> click on the below button. </strong></p>
	
	
	    <p class="read-more"><a href="#" onclick="document.forms[1].submit();return false;"><b>Device List</b></a></p>
	    
	    
<% List list = (ArrayList)session.getAttribute("list"); 
         %>
	    
	    <table class="flat-table">
  <tbody>
    <tr>
      <th>Token ID</th>
      <th>Threshold Rate</th>
      </tr>
      <% if(list==null) { %>
      <tr><td></td>
      <td></td>
      </tr>
      
      <% } else { %>
      
       <c:forEach items="${list}" var="dataItem">
        <tr>
            <td>${dataItem.token}</td>
            <td>${dataItem.rate}</td>
        </tr>
    </c:forEach>
    <% } %>
  </tbody>
	</table>
	
	<!--<input type="submit" value="Submit" /> -->
	
	</form>
	   </div>
	
	
	<div class="column">
      <h2>Services</h2>
      <br></br>
      <p>User can set the <strong> minimum currency exchange rate </strong> for which he wants to be alerted.Forex Alert server will keep a watch on <strong> current exchange rate</strong> and send a <strong>notification on the user mobile </strong> if the current exchange rate is crosses user configured exchange rate. The service is near about <strong> real time </strong> as the server will check the <strong> updated exchange rate on every minute.</strong></p>
      
    </div>
	
	 <div class="column last">
      <h2>Connect</h2>
      <br></br>
      <p>We'd love to connect with everyone that uses our services.</p>
      <p>Please feel free to <a href="mailto:jatinsaxena86@gmail.com">contact us </a>for any feedback.</p>
      
    </div>
	
    </div>
        </div>
        
        
 <!-- //#content -->

<div id="footer">
  <ul>
    </ul>
  <p>Website designed by: <a href="http://www.agilo.hr">Agilo</a>, Code: <a href="http://www.slicejack.com" title="PSD to (X)HTML service">Slicejack</a></p>
</div>
<!-- //#footer -->

</body>
</html>