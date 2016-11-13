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
	
	if(jugador1.value ==""||jugador1.value==null){	
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
<body onload="carga();">

<form action="CombateWeb.jr" method="post" onsubmit="return validaDatos();">


<table align="center">

<tr>
		<td> <label>Player number one: </label> </td>
		<td> <input type="text" id="txtJugador1"> </td>
		<td><button type="submit" name="btnInicio" value="Inicio" onclick="deshabilitarBoton();">Fight!!</button></td>
		<td><label>Player number two: </label></td>
		<td><input id="txtJugador2"></td>

</tr>
<tr>
		<td> <label>Energia de ataque: </label> </td>
		<td> <input type="text" name="txtAtaque1"> </td>
		<td><button type="submit" name="btnAtaque" value="Inicio">Atack</button></td>
		<td><label>Energia de ataque: </label></td>
		<td><input type="text" id="txtAtaque2"></td>

</tr>
<tr>
		<td> <label>Vida: </label> 
		<td> <input disabled="disabled" id="txtVida1"> 
			
		</td>
		<td>
			<button  type="button" id="btnDefensa">defend</button>
			
		</td>
		<td>  <label>Vida: </label> </td>
		<td>  <input disabled="disabled" name="txtVida2"> </td>
		

</tr>
<tr>
		<td> <label>Energia: </label></td>
		<td> <input disabled="disabled" name="txtEnergia1"> </td>
		<td>
			<button  type="button" id="btnEvade">evade</button>			
		</td>
		<td>  <label>Energia: </label></td>
		<td>  <input disabled="disabled" name="txtEnergia2"> </td>
</tr>
<tr>
		<td> <label>Defensa: </label></td>
		<td> <input disabled="disabled" id="txtDefensa1"> </td>
		<td>
			
			
		</td>
		<td>  <label>Defensa: </label> </td>
		<td>  <input disabled="disabled" id="txtDefensa2"> </td>

</tr>
<tr>
		<td> <label>Evasion: </label> </td>
		<td> <input disabled="disabled" name="txtEvasion1"> </td>
		
		<td>	
			
		</td>
		<td>  <label>Evasion: </label></td>
		<td>  <input disabled="disabled" id="txtEvasion2"> </td>

</tr>

 </table>
 </form>

</body>
</html>