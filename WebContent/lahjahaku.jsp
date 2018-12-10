<%@include file="header.jsp" %>
<%@ page import="model.Lahjatieto"%>
<%@ page import="model.Lahja"%>  
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
if(session.getAttribute("hakusanaLahja")!=null){
	hakusana=session.getAttribute("hakusanaLahja").toString();
}else{
	hakusana="";
} 
%>
<img src="images/gift.png" title="Uusi lahja" class="icon" id="uusiLahja">
<span class='otsikkoTeksti'>Lis�� uusi lahja</span>
<br><br>
<fieldset id="hakuAlue">
	<legend>Haku:</legend>	
	<input type="text" name="hakuSana" id="hakuSana" value="<%out.println(hakusana);%>">
	<img src="images/search.png" title="Etsi" class="icon" id="haeLahja">
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
				<th>Lahja_id</th>
				<th>Tuotenimi</th>
				<th>Hinta</th>
				<th>Kauppa</th>	
				<th></th>			
			</tr>
		</thead>
		<tbody>
		<%
		if(request.getAttribute("lahjat")!=null){	
			ArrayList<Lahjatieto> lahjat = (ArrayList<Lahjatieto>)request.getAttribute("lahjat");
			for(int i=0;i<lahjat.size();i++){
				out.print("\n<tr>");
				out.print("<td>" + lahjat.get(i).getLahja().getLahja_id() + "</td>");
				out.print("<td>" + lahjat.get(i).getLahja().getTuotenimi() + "</td>");
				out.print("<td>" + lahjat.get(i).getLahja().getHinta() + "e" + "</td>");
				out.print("<td>" + lahjat.get(i).getKauppa().getKauppa() + "</td>");
				out.print("<td>");
				out.print("\n<img src='images/pencil.png' class='icon muokkaa' id='muokkaa_"+lahjat.get(i).getLahja().getLahja_id()+"' name='"+lahjat.get(i).getLahja().getTuotenimi()+"' title='Muokkaa'>");
				out.print("\n<img src='images/delete.png' class='icon poista' id='poista_"+lahjat.get(i).getLahja().getLahja_id()+"' name='"+lahjat.get(i).getLahja().getTuotenimi()+" "+lahjat.get(i).getLahja().getTuotenimi()+"' title='Poista'>");
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
	
	$("#uusiLahja").click(function(){
		document.location="uusilahja.jsp";	
	});	
	
	$("#haeLahja").click(function(){
		document.location=encodeURI("Servlet_HaeLahjat?hakusana=" + $("#hakuSana").val());	
	});
	
	
	$(".muokkaa").click(function(){
		//Objektin ominaisuuksien ja arvojen läpikäynti (ei tarvita tässä tapauksessa, vain esimerkkinä)
		/*
		var obj = $(this)[0]; 	
		for(var key in obj) {
			console.log('key: ' + key + '\n' + 'value: ' + obj[key]);
		}
		*/	
		document.location="Servlet_MuokkaaLahja?Lahja_id=" + $(this)[0].id;	
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
		document.location="Servlet_PoistaLahja?Lahja_id="+poistettava_id;
	});
	
	$("#peruutaPoisto").click(function(){
		$("#poistonVahvistus").hide();
	});
	
	
});
</script>

</div>
</body>
</html>