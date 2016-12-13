package servlet;

import java.io.IOException;
			/*Integrantes del grupo: 
			Amaya, Florencia
			García , María Belén 
			Zorzi, Ornella Daniela
			*/
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.*;
import entidades.*;
import util.ApplicationException;

/**
 * Servlet implementation class CombateServlet
 */
@WebServlet("/SeleccionarPersonaje")
public class SeleccionarPersonaje extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CtrlPartida ctrlPartida = null;
	private CtrlABMCPersonaje consulta = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SeleccionarPersonaje() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String per1 = request.getParameter("txtJugador1");

		String per2 = request.getParameter("txtJugador2");

		consulta = new CtrlABMCPersonaje();
		try {
			Personaje personaje1 = consulta.buscarPersonajePorNombre(per1);
			System.out.println("Se cargó en controlador el personaje 1: " + personaje1.getNombrePersonaje());
			Personaje personaje2 = consulta.buscarPersonajePorNombre(per2);
			System.out.println("Se cargó en controlador el personaje 2: " + personaje2.getNombrePersonaje());

			ctrlPartida = new CtrlPartida(personaje1, personaje2);

			HttpSession session = request.getSession(true);
			session.setAttribute("ctrlPartida", ctrlPartida);

			request.getRequestDispatcher("partida.jsp").forward(request, response);

		} catch (ApplicationException e) {
			System.out.println(e.getMessage());
			response.sendRedirect("");
		}

	}

}
