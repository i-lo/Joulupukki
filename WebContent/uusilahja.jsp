<%@include file="header.jsp" %> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style>
#containerr {
	width: 80%;
	margin: 60px;
}

</style>
<body>
<div id="containerr" >

<fieldset id="lahjaTiedot">
	<legend>Lahjan tiedot:</legend>
	<form action="Servlet_UusiLahja" method="post" id="uusiLomake">
		<label>Tuotenimi:</label>
			<input type="text" name="tuotenimi" id="tuotenimi" value="" size="40"><br>	
		<label>Hinta:</label>
			<input type="text" name="hinta" id="hinta" value="" size="40"><br>	
		<label>Kauppa:</label>
			<input type="text" name="kauppa" id="kauppa" value="" size="40"><br>	
		<label></label>	
			<input type="submit" value="Lisää uusi" id="lisaaBtn">
			<%
			if(request.getParameter("ok")!=null){
				if(request.getParameter("ok").equals("1")){
					out.print("Uusi lahja on lisätty.");	
				}else if(request.getParameter("ok").equals("0")){
					out.print("Lahjan lisäys epäonnistui.");	
				}
			}
			%>
	</form>
</fieldset>







</div>
</body>
</html>