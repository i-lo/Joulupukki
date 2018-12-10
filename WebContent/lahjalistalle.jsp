<%@include file="header.jsp" %>
<%@ page import="model.Lahjansaaja"%>  
<%@ page import="java.util.ArrayList"%>  
<%! @SuppressWarnings("unchecked") %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

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

<%@ page import="model.Lahjatieto"%>
<%@ page import="java.util.ArrayList"%>
 
	<table id="tiedotTaulu">
		<thead>
			<tr>
				<th>Tuote</th>
				<th>Hinta</th>
				<th>Kauppa</th>	
				<th></th>			
			</tr>
		</thead>
		<tbody>
		<%
			String kayttajaId = (String)request.getAttribute("id");
			ArrayList<Lahjatieto> lahjatiedot = (ArrayList<Lahjatieto>)request.getAttribute("lahjatiedot");
			for(int i=0;i<lahjatiedot.size();i++){
				out.print("\n<tr>");
				out.print("<td>" + lahjatiedot.get(i).getLahja().getTuotenimi() + "</td>");
				out.print("<td>" + lahjatiedot.get(i).getLahja().getHinta() + "</td>");
				out.print("<td>" + lahjatiedot.get(i).getKauppa().getKauppa() + "</td>");

				out.print("<td>");
		
				
				out.print("</td>");
				out.print("</tr>");
				
		}
		%>
		</tbody>
	</table>

<form action="Servlet_LahjaListalle" method="post">
		<label>Lis‰tt‰v‰ lahja:</label>
			<input type="text" name="tuotenimi" id="tuotenimi" size="40"><br>
			<label></label>	
			<input type="submit" value="Lis‰‰ lahja" id="lisaaBtn">
			<%
			if(request.getParameter("ok")!=null){
				if(request.getParameter("ok").equals("1")){
					out.print("Lahja on lis‰tty.");	
				}else if(request.getParameter("ok").equals("0")){
					out.print("Lahjan lis‰‰minen ep‰onnistui.");	
				}
			}
			%>


<script>
$(document).ready(function(){

	
	$(".lisaalahja").click(function(){
		//Objektin ominaisuuksien ja arvojen l√§pik√§ynti (ei tarvita t√§ss√§ tapauksessa, vain esimerkkin√§)
		/*
		var obj = $(this)[0]; 	
		for(var key in obj) {
			console.log('key: ' + key + '\n' + 'value: ' + obj[key]);
		}
		*/
		document.location="Servlet_LahjaListalle?&Lahjansaaja_id="+$(this)[0].id;
	
		
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