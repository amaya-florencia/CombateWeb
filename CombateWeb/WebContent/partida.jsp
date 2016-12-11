<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="negocio.CtrlPartida"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Start War!!</title>
</head>
<body>
	<table>
		<tr>
			<td></td>
			<td><%=((CtrlPartida) session.getAttribute("ctrlPartida")).getPersonaje1().getNombrePersonaje()%></td>
			<td></td>
			<td></td>
			<td><%=((CtrlPartida) session.getAttribute("ctrlPartida")).getPersonaje2().getNombrePersonaje()%></td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><label>Energia de ataque: </label></td>
			<td>
				<form id="formAtaque1" name="formAtaque1" action="Partida"
					method="post">
					<input type="number" name="txtAtaque1" min=0
						max=<%=((CtrlPartida) session.getAttribute("ctrlPartida")).getPersonaje1().getEnergia()%>required  %>
					<button type="submit" name="btnAtaque1" value="Inicio">Atack
						1</button>
				</form>
			</td>
			<td style="width: 54px;"></td>
			<td><label>Energia de ataque: </label></td>
			<td>
				<form id="formAtaque2" name="formAtaque2" action="Partida"
					method="post">
					<input type="number" name="txtAtaque2" min=0
						max=<%=((CtrlPartida) session.getAttribute("ctrlPartida")).getPersonaje2().getEnergia()%>required  %>
					<button type="submit" name="btnAtaque2" value="Inicio">Atack
						2</button>
				</form>
			</td>

			<td></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<form id="formDefensa1" name="formDefensa1" action="Partida"
					method="post">
					<button type="submit" name="btnDefensa1">Defend 1</button>
				</form>
			</td>
			<td></td>
			<td></td>
			<td>
				<form id="formDefensa2" name="formDefensa2" action="Partida"
					method="post">
					<button type="submit" name="btnDefensa2">Defend 2</button>
				</form>
			</td>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td><label>Vida: </label>
			<td><%=((CtrlPartida) session.getAttribute("ctrlPartida")).getPersonaje1().getVida()%></td>
			<td></td>
			<td><label></label></td>
			<td><label>Vida: </label></td>
			<td><%=((CtrlPartida) session.getAttribute("ctrlPartida")).getPersonaje2().getVida()%></td>


			<td></td>
		</tr>
		<tr>
			<td><label>Energia: </label></td>
			<td><%=((CtrlPartida) session.getAttribute("ctrlPartida")).getPersonaje1().getEnergia()%></td>
			<td></td>
			<td></td>
			<td><label>Energia: </label></td>
			<td><%=((CtrlPartida) session.getAttribute("ctrlPartida")).getPersonaje2().getEnergia()%></td>
			<td></td>

		</tr>
		<tr>
			<td><label>Defensa: </label></td>
			<td><%=((CtrlPartida) session.getAttribute("ctrlPartida")).getPersonaje1().getDefensa()%></td>
			<td></td>
			<td></td>
			<td><label>Defensa: </label></td>
			<td><%=((CtrlPartida) session.getAttribute("ctrlPartida")).getPersonaje2().getDefensa()%></td>

			<td></td>
		</tr>
		<tr>
			<td><label>Evasion: </label></td>
			<td><%=((CtrlPartida) session.getAttribute("ctrlPartida")).getPersonaje1().getEvasion()%></td>

			<td></td>
			<td></td>
			<td><label>Evasion: </label></td>
			<td><%=((CtrlPartida) session.getAttribute("ctrlPartida")).getPersonaje2().getEvasion()%></td>

			<td></td>
		</tr>
	</table>
	
<div><%=((CtrlPartida) session.getAttribute("ctrlPartida")).getMensajes()%></div>

</body>
</html>