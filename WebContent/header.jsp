<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!--  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script><!-- datepickeriä varten -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css"><!-- datepickeriä varten -->
<title>Joulupukki</title>
</head>
<style>
* {
  margin: 0;
  padding: 0;
}
 
nav {
  margin: 50px;
  /*text-align: center;*/
}
 
ul {
  overflow: hidden;
  list-style-type: none;

}
 
li {
  height: 50px;
  float: left;
  margin-right: 0px;
  border-right: 1px solid #FCC9B9;
  padding: 0 60px;
 /* text-align: center; */
}
 
li:last-child {
  border-right: none;
}
 
li a {
  text-decoration: none;
  color: #DC3023; /*red-orange (lit.orangutan-colored) */
  font: 25px/1 Helvetica, Verdana, sans-serif;
  text-transform: uppercase;
 
  -webkit-transition: all 0.5s ease;
  -moz-transition: all 0.5s ease;
  -o-transition: all 0.5s ease;
  -ms-transition: all 0.5s ease;
  transition: all 0.5s ease;
}

.linkki {
	color: #DC3023; /*red-orange (lit.orangutan-colored) */
}

.linkki:hover {
	color: #FCC9B9;  /*cherry blossom color */
}

a:hover {
	 color: #FCC9B9;  /*cherry blossom color */
}
 
li a:hover {
  color: #FCC9B9;  /*cherry blossom color */
}
 
li.active a {
  font-weight: bold;
  color: #DC3023; /*red-orange (lit.orangutan-colored) */
}


.header_icon{
	height:50px;
}
.icon{
	height:30px;
}
.icon_mini{
	height:20px;
	vertical-align:bottom;
}

#containerr {
	width: 80%;
	margin: 60px;
}


/* Popup container */
.popup {
  position: relative;
  display: inline-block;
  cursor: pointer;
}

/* The actual popup (appears on top) */
.popup .popuptext {
  visibility: hidden;
  width: 140px;
  background-color: rgb(255,255,255, 0.2);
  color: #DC3023;
  text-align: right;
  border-radius: 6px;
  padding: 8px 0;
  position: absolute;
  z-index: 1;
  bottom: 125%;
  left: 50%;
  margin-left: -80px;
}

/* Popup arrow */
.popup .popuptext::after {
  content: "";
  position: absolute;
  top: 100%;
  left: 50%;
  margin-left: -5px;
  border-width: 5px;
  border-style: solid;
  border-color: transparent transparent transparent transparent;
}

/* Toggle this class when clicking on the popup container (hide and show the popup) */
.popup .show {
  visibility: visible;
  -webkit-animation: fadeIn 1s;
  animation: fadeIn 1s
}


body {
  background-color: rgb(255, 230, 230, 0.2);
   
}

</style>
<body>


<nav>

		<ul>
			<li><a href="kaikkienlahjojentulostus.jsp">Lahjalista</a></li>
			<li><a href="lahjansaajahaku.jsp">Lahjansaajat</a></li>
			<li><a href="lahjahaku.jsp">Lahjat</a></li>
			<li><a href="kauppahaku.jsp">Kaupat</a></li>
			<li><img src="images/santaclaus.png" class="header_icon" title="Ho ho ho" onclick="joulupukki()">
			<div class="popup" >
  <span class="popuptext" id="myPopup">Ho ho ho!</span>	
		</ul>
		
</div>
</nav>


<hr>
</body>
<script>
// When the user clicks on <div>, open the popup
function joulupukki() {
  var popup = document.getElementById("myPopup");
  popup.classList.toggle("show");
}
</script>