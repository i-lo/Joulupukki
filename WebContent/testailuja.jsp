<%@include file="ylaosa.jsp" %>
<div class="otsikko">T�ss� keskiosa</div>
<span id="tulos"></span>
<hr>
<div class="otsikko">T�ss� alaosa</div>
<input type="button" id="nappi" value="Hyv�ksy" />
<script>
$(document).ready(function(){
	$("#nappi").click(function() {
		$("#tulos").html("moi");	
		$("#nappi").val("moi");
		$("#tulos").css({
			"color": "red",
			"font-weight": "bold",
		});
		$(".otsikko").css({
			"color": "green",
			"font-weight": "bold",
		});		
	});
	
	$("#tekstikentta").change(function() {
		$("#tulos").html($("#tekstikentta").val());	
	});
	
});
</script>
</body>
</html>







