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
<%@ page import="model.Kauppa"%> 
<%
Kauppa kauppa = null;
if( request.getAttribute("kauppa")!=null){
	kauppa = (Kauppa)request.getAttribute("kauppa");	
}
%>

<fieldset id="KaupanTiedot">
	<legend>Kaupan tiedot:</legend>
	<form action="Servlet_MuokkaaKauppa" method="post" id="uusiLomake">
		<label>Kauppa:</label>
			<input type="text" name="kauppanimi" id="kauppanimi" value="<%out.print(kauppa.getKauppa());%>" size="40"><br>	
		<label>Osoite:</label>
			<input type="text" name="osoite" id="osoite" value="<%out.print(kauppa.getKatuosoite());%>" size="40"><br>
		<label>Postinumero:</label>
			<input type="text" name="postinumero" id="postinumero" value="<%out.print(kauppa.getPostinumero());%>" size="40"><br>	
	

		<label></label>	
			<input type="submit" value="Vahvista muutos" id="lisaaBtn">
			<%
			if(request.getParameter("ok")!=null){
				if(request.getParameter("ok").equals("1")){
					out.print("Tiedot on muutettu.");	
				}else if(request.getParameter("ok").equals("0")){
					out.print("Tietojen muuttaminen epäonnistui.");	
				}
			}
			%>
		<input type="hidden" name="Kauppa_id" value="<%out.print(kauppa.getKauppa_id());%>">
	</form>
</fieldset>
</div>
</body>
</html>