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
<%@ page import="model.Lahjansaaja"%> 
<%
Lahjansaaja lahjansaaja = null;
if( request.getAttribute("lahjansaaja")!=null){
	lahjansaaja = (Lahjansaaja)request.getAttribute("lahjansaaja");	
}
%>

<fieldset id="saajanTiedot">
	<legend>Lahjansaajan tiedot:</legend>
	<form action="Servlet_MuokkaaLahjansaaja" method="post" id="uusiLomake">
		<label>Etunimi:</label>
			<input type="text" name="etunimi" id="etunimi" value="<%out.print(lahjansaaja.getEtunimi());%>" size="40"><br>	
		<label>Sukunimi:</label>
			<input type="text" name="sukunimi" id="sukunimi" value="<%out.print(lahjansaaja.getSukunimi());%>" size="40"><br>	
		<label>Kotiosoite:</label>
			<input type="text" name="kotiosoite" id="kotiosoite" value="<%out.print(lahjansaaja.getKotiosoite());%>" size="40"><br>	
		<label>Postinumero:</label>
			<input type="text" name="postinumero" id="postinumero" value="<%out.print(lahjansaaja.getPostinumero());%>" size="5">&nbsp;
			
			<!--   %><span id="postitoimipaikka"></span>
			<span id="postitoimivirhe"></span><br> -->
		
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
		<input type="hidden" name="Lahjansaaja_id" value="<%out.print(lahjansaaja.getLahjansaaja_id());%>">
	</form>
</fieldset>
</div>
</body>
</html>