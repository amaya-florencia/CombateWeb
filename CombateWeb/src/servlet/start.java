package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Personaje;
import negocio.CtrlABMCPersonaje;
import util.ApplicationException;

/**
 * Servlet implementation class start
 */
@WebServlet("/start")
public class start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public start() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CtrlABMCPersonaje ctrl = new CtrlABMCPersonaje();
		Personaje p1= new Personaje();
		p1.setIdPersonaje(Integer.parseInt(request.getParameter("Personaje1")));
		Personaje p2= new Personaje();
		p2.setIdPersonaje(Integer.parseInt(request.getParameter("Personaje2")));
		try {
			p1=ctrl.buscarPersonajePorNombre(p1.getNombrePersonaje());
			p2=ctrl.buscarPersonajePorNombre(p2.getNombrePersonaje());
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		request.getSession().setAttribute("P1", p1);
		request.getSession().setAttribute("P2", p2);
		request.getRequestDispatcher("WEB-INF/inicio.jsp").forward(request, response);
	}

}
