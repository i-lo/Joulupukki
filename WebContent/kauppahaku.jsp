<%@include file="header.jsp" %>
<%@ page import="model.Kauppa"%>  
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
if(session.getAttribute("hakusanaKauppa")!=null){
	hakusana=session.getAttribute("hakusanaKauppa").toString();
}else{
	hakusana="";
} 
%>
<img src="images/store.png" title="Uusi kauppa" class="icon" id="uusiKauppa">
<span class='otsikkoTeksti'>Lis�� uusi kauppa</span>
<br><br>
<fieldset id="hakuAlue">
	<legend>Haku:</legend>	
	<input type="text" name="hakuSana" id="hakuSana" value="<%out.println(hakusana);%>">
	<img src="images/search.png" title="Etsi" class="icon" id="haeKauppa">
</fieldset>
<fieldset id="tulosAlue">
	<legend>Tulos:</legend>
	<div id="poistonVahvistus">
	<span id="poistettavaNimi"></span>
	<img src="images/accept.png" title="Poista" class="icon" id="vahvistaPoisto">
	<img src="images/delete.png" title="Peruuta" class="icon" id="peruutaPoisto">
	</div>
	<table id="tiedotTaulu">
		<thead>
			<tr>
				<th>Kauppa_id</th>
				<th>Kauppa</th>
				<th>Osoite</th>	
				<th></th>			
			</tr>
		</thead>
		<tbody>
		<%
		if(request.getAttribute("kaupat")!=null){	
			ArrayList<Kauppa> kaupat = (ArrayList<Kauppa>)request.getAttribute("kaupat");
			for(int i=0;i<kaupat.size();i++){
				out.print("\n<tr>");
				out.print("<td>" + kaupat.get(i).getKauppa_id() + "</td>");
				out.print("<td>" + kaupat.get(i).getKauppa() + "</td>");
				out.print("<td>" + kaupat.get(i).getKatuosoite() + "</td>");
				out.print("<td>");
				out.print("\n<img src='images/pencil.png' class='icon muokkaa' id='muokkaa_"+kaupat.get(i).getKauppa_id()+"' name='"+kaupat.get(i).getKauppa()+"' title='Muokkaa'>");
				out.print("\n<img src='images/delete.png' class='icon poista' id='poista_"+kaupat.get(i).getKauppa_id()+"' name='"+kaupat.get(i).getKauppa()+" "+kaupat.get(i).getKauppa()+"' title='Poista'>");
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
		"width": "900px", 
		"border-radius": "15px"
	});	
	
	$("#uusiKauppa").click(function(){
		document.location="uusikauppa.jsp";	
	});	
	
	$("#haeKauppa").click(function(){
		document.location=encodeURI("Servlet_HaeKaupat?hakusana=" + $("#hakuSana").val());	
	});
	
	
	$(".muokkaa").click(function(){
		//Objektin ominaisuuksien ja arvojen läpikäynti (ei tarvita tässä tapauksessa, vain esimerkkinä)
		/*
		var obj = $(this)[0]; 	
		for(var key in obj) {
			console.log('key: ' + key + '\n' + 'value: ' + obj[key]);
		}
		*/	
		document.location="Servlet_MuokkaaKauppa?Kauppa_id=" + $(this)[0].id;	
	});
	var poistettava_id;	
	$(".poista").click(function(event){	//event kantaa mukanaan tapahtuman, mm. klikkauksen sijainnin	
		poistettava_id=$(this)[0].id;
		$("#poistettavaNimi").html("Poista " + $(this)[0].name + "?<br>");
		$("#poistonVahvistus").css({ 
			"width": "170px", 
			"height": "50px",
			"background-color": "#4CAF50",
			"border-radius": "15px",
			"color": "white",
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
		document.location="Servlet_PoistaKauppa?Kauppa_id="+poistettava_id;
	});
	
	$("#peruutaPoisto").click(function(){
		$("#poistonVahvistus").hide();
	});
	
	
});
</script>

</div>
</body>
</html>