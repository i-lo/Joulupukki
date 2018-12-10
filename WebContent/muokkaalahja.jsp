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
<%@ page import="model.Lahja"%> 
<%@ page import="model.Lahjatieto"%>
<%
Lahjatieto lahja = null;
if( request.getAttribute("lahja")!=null){
	lahja = (Lahjatieto)request.getAttribute("lahja");	
}
%>

<fieldset id="lahjanTiedot">
	<legend>Lahjan tiedot:</legend>
	<form action="Servlet_MuokkaaLahja" method="post" id="uusiLomake">
		<label>Tuotenimi:</label>
			<input type="text" name="tuotenimi" id="tuotenimi" value="<%out.print(lahja.getLahja().getTuotenimi());%>" size="40"><br>	
		<label>Hinta:</label>
			<input type="text" name="hinta" id="hinta" value="<%out.print(lahja.getLahja().getHinta());%>" size="40"><br>	
		<label>Kauppa:</label>
			<input type="text" name="kauppanimi" id="kauppanimi" value="<%out.print(lahja.getKauppa().getKauppa());%>" size="40"><br>

		
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
		<input type="hidden" name="Lahja_id" value="<%out.print(lahja.getLahja().getLahja_id());%>">
	</form>
</fieldset>
</div>
</body>
</html>