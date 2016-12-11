<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Combat</title>
<script type="text/javascript" language="JavaScript"> 
	function carga(){
	document.getElementById("txtJugador1").focus();
	}
	
	function validaDatos(){
	var jugador1 = document.getElementById("txtJugador1");
	var jugador2 = document.getElementById("txtJugador2");
	if(jugador1.value ==""||jugador1.value==null & jugador2.value ==""||jugador2.value==null){	
		alert("Debe ingresar dos personajes");
		jugador1.focus();
		return false;	
	}
	else if(jugador1.value ==""||jugador1.value==null){	
		alert("Debe ingresar un jugador");
		jugador1.focus();
		return false;	
	}else if(jugador2.value ==""||jugador2.value==null){	
		alert("Debe ingresar un jugador");
		jugador2.focus();
		return false;	
	}else if(jugador1.value==jugador2.value){
		alert("Debe ingresar dos personajes distintos");
		jugador2.value = "";
		jugador2.focus();
					
		return false;
	}else{
		return true;
	}
	
	}
	
</script>

</head>
<body>

	<form id="formPersonajes" action="SeleccionarPersonaje" method="post" >
		<table align="center">		
		<tr>
				<td> <label>Player number one: </label> </td>
				<td> <input type="text" name="txtJugador1" required> </td>
				<td></td>
				<td><label>Player number two: </label></td>
				<td><input type="text" name="txtJugador2" required></td>
				<td><button type="submit" class="button expanded" value="start">start</button></td>
		</tr>		
		</table>
 </form>

</body>
</html>