<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="sponsorItemModel.sponsor" %>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.js" integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE=" crossorigin="anonymous"></script>
	<script src="Components/Sponsor.js"></script>
</head>
<body>
	<div class="container"><div class="row"><div class="col-6"> 
	
	<h1><center>Sponsor Management</center></h1>
	<form id="formItem" name="formItem">
 		Name : 
 		 <input id="name" name="name" type="text" 
 				class="form-control form-control-sm">
 				
 		 <br> Company : 
		 <input id="company" name="company" type="text" 
		 		class="form-control form-control-sm">
		 		
		 <br> project : 
		 <input id="project" name="project" type="text" 
		 		class="form-control form-control-sm">
		 		
		 		
		 <br>
		 <input id="btnSave" name="btnSave" type="button" value="Save" 
		 		class="btn btn-primary">
		 <input type="hidden" id="hidItemIDSave" 
		 		name="hidItemIDSave" value="">
	</form>
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemsGrid">
		 <%
			 sponsor sponObj = new sponsor(); 
			 out.print(sponObj.getsponsorItem()); 
		 %>
	</div>
</div> </div> </div>
</body>
</html>