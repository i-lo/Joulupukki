<%@include file="header.jsp" %>
<%@ page import="model.SaajanLahja"%> 
<%@ page import="java.util.ArrayList"%>  
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
<% 
String hakusana;
if(session.getAttribute("hakusanaLahjansaaja")!=null){
	hakusana=session.getAttribute("hakusanaLahjansaaja").toString();
}else{
	hakusana="";
} 
%>
<fieldset id="hakuAlue2">
	<legend>Hae lahjalistat: </legend>	

	<img src="images/search.png" title="Etsi" class="icon" id="haeLahjat">
</fieldset>
<fieldset id="tulosAlue">
	<table id="tiedotTaulu">
		<thead>
			<tr>
				<th>Lahjansaajan nimi</th>
				<th>Lahja</th>
				<th>Hinta</th>
				<th>Kauppa</th>	
				<th></th>			
			</tr>
		</thead>
		<tbody>
		<%
		if(request.getAttribute("kaikkienSaajienKaikkiLahjatiedot")!=null){	
			ArrayList<SaajanLahja> kaikkienLahjatiedot = (ArrayList<SaajanLahja>)request.getAttribute("kaikkienSaajienKaikkiLahjatiedot");
			for(int i=0; i<kaikkienLahjatiedot.size(); i++){
				out.print("\n<tr>");
				out.print("<td>" + kaikkienLahjatiedot.get(i).getEtunimi() + " " +kaikkienLahjatiedot.get(i).getSukunimi() + "</td>");
				
				for(int j=0; j<kaikkienLahjatiedot.get(i).getLahjat().size(); j++) {
					out.print("<tr>");
						out.print("<td>");
						out.print("<td>" + kaikkienLahjatiedot.get(i).getLahjat().get(j).getLahja().getTuotenimi() + "</td>");
						out.print("<td>" + kaikkienLahjatiedot.get(i).getLahjat().get(j).getLahja().getHinta() + "e" + "</td>");
						out.print("<td>" + kaikkienLahjatiedot.get(i).getLahjat().get(j).getKauppa().getKauppa() + "</td>");
						out.print("</td>");
					out.print("</tr>");
				}				
				out.print("</tr>");
			}	
		}
		%>
		</tbody>
	</table>
</fieldset>
<script>
$(document).ready(function(){
	$("#haeLahjat").click(function(){
	document.location=encodeURI("Servlet_LahjatietojenTulostus?hakusana=" + $("#hakuSana").val());	
	});
});
</script>
</div>
</body>
</html>