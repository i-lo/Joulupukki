<%@include file="header.jsp" %>
<%@ page import="model.Lahjansaaja"%>  
<%@ page import="java.util.ArrayList"%>  
<%! @SuppressWarnings("unchecked") %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%-- ALKU TULEE header.jsp tiedostosta******************************************************************************************************--%>


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
<img src="images/user2.png" title="Uusi lahjansaaja" class="icon" id="uusiLahjansaaja">
<span class='otsikkoTeksti'>Lis�� lahjansaaja</span>
<br><br>
<fieldset id="hakuAlue">
	<legend>Hae lahjansaajaa:</legend>	
	<input type="text" name="hakuSana" id="hakuSana" value="<%out.println(hakusana);%>">
	<img src="images/search.png" title="Etsi" class="icon" id="haeLahjansaaja">
</fieldset>
<fieldset id="tulosAlue">
	<legend></legend>
	
	<div id="poistonVahvistus">
	<span id="poistettavaNimi"></span>
	<img src="images/accept.png" title="Poista" class="icon" id="vahvistaPoisto">
	<img src="images/delete.png" title="Peruuta" class="icon" id="peruutaPoisto">
	</div>
	<table id="tiedotTaulu">
		<thead>
			<tr>
				<th>ID</th>
				<th>Etunimi</th>
				<th>Sukunimi</th>	
				<th></th>			
			</tr>
		</thead>
		<tbody>
		<%
		if(request.getAttribute("lahjansaajat")!=null){	
			ArrayList<Lahjansaaja> lahjansaajat = (ArrayList<Lahjansaaja>)request.getAttribute("lahjansaajat");
			for(int i=0;i<lahjansaajat.size();i++){
				out.print("\n<tr>");
				out.print("<td>" + lahjansaajat.get(i).getLahjansaaja_id() + "</td>");
				out.print("<td>" + lahjansaajat.get(i).getEtunimi() + "</td>");
				out.print("<td>" + lahjansaajat.get(i).getSukunimi()+ "</td>");	
				out.print("<td>");
				out.print("\n<img src='images/list.png' class='icon lisaalahja' id='lahjalistalle_"+lahjansaajat.get(i).getLahjansaaja_id()+"' name='"+lahjansaajat.get(i).getEtunimi()+" "+lahjansaajat.get(i).getSukunimi()+"' title='Lis�� lahja listalle'>");
				out.print("\n<img src='images/pencil.png' class='icon muokkaa' id='muokkaa_"+lahjansaajat.get(i).getLahjansaaja_id()+"' name='"+lahjansaajat.get(i).getEtunimi()+" "+lahjansaajat.get(i).getSukunimi()+"' title='Muokkaa'>");
				out.print("\n<img src='images/delete.png' class='icon poista' id='poista_"+lahjansaajat.get(i).getLahjansaaja_id()+"' name='"+lahjansaajat.get(i).getEtunimi()+" "+lahjansaajat.get(i).getSukunimi()+"' title='Poista'>");
				out.print("</td>");
				out.print("</tr>");
			}	
		}
		%>
		</tbody>
	</table>
</fieldset>
<script>
$(document).ready(function(){
	$("#poistonVahvistus").hide();
		
	$("#hakuAlue").css({
		"width": "210px", 
		"border-radius": "15px"
	});
	
	$("#tulosAlue").css({
		"width": "400px", 
		"border-radius": "15px"
	});	
	
	$("#uusiLahjansaaja").click(function(){
		document.location="uusilahjansaaja.jsp";	
	});	
	
	$("#haeLahjansaaja").click(function(){
		document.location=encodeURI("Servlet_HaeLahjansaajat?hakusana=" + $("#hakuSana").val());	
	});
	
	
	$(".lisaalahja").click(function(){
		//Objektin ominaisuuksien ja arvojen läpikäynti (ei tarvita tässä tapauksessa, vain esimerkkinä)
		/*
		var obj = $(this)[0]; 	
		for(var key in obj) {
			console.log('key: ' + key + '\n' + 'value: ' + obj[key]);
		}
		*/
		document.location="Servlet_LahjaListalle?Lahjansaaja_id=" + $(this)[0].id;	
	});
	
	
	$(".muokkaa").click(function(){
		//Objektin ominaisuuksien ja arvojen läpikäynti (ei tarvita tässä tapauksessa, vain esimerkkinä)
		/*
		var obj = $(this)[0]; 	
		for(var key in obj) {
			console.log('key: ' + key + '\n' + 'value: ' + obj[key]);
		}
		*/	
		document.location="Servlet_MuokkaaLahjansaaja?Lahjansaaja_id=" + $(this)[0].id;	
	});
	
	var poistettava_id;	
	$(".poista").click(function(event){	//event kantaa mukanaan tapahtuman, mm. klikkauksen sijainnin	
		poistettava_id=$(this)[0].id;
		$("#poistettavaNimi").html("Poista " + $(this)[0].name + "?<br>");
		$("#poistonVahvistus").css({ 
			"width": "170px", 
			"height": "50px",
			
			"border-radius": "15px",
			"color": "black",
			"text-align": "center",
			"padding": "10px",
			"border": "1px solid black",
			"position": "absolute",
			"left": event.pageX +"px",
			"top": event.pageY +"px"
		});			
		$("#poistonVahvistus").show();
	});
	
	$("#vahvistaPoisto").click(function(){
		document.location="Servlet_PoistaLahjansaaja?Lahjansaaja_id="+poistettava_id;
	});
	
	$("#peruutaPoisto").click(function(){
		$("#poistonVahvistus").hide();
	});
	
	
});
</script>

</div>
</body>
</html>