package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ApplicationException;
import negocio.CtrlPartida;

			/*Integrantes del grupo: 
			Amaya, Florencia
			García , María Belén 
			Zorzi, Ornella Daniela
			*/
/**
 * Servlet implementation class Partida
 */
@WebServlet("/Partida")
public class Partida extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Partida() {
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
		
		CtrlPartida ctrlPartida = (CtrlPartida)request.getSession().getAttribute("ctrlPartida");
		String rta = "";
		if(request.getParameter("btnAtaque1")!=null){
			//Ataca el jugador 1
			System.out.println(request.getParameter("txtAtaque1").toString());
			int energiaAtaque = Integer.parseInt(request.getParameter("txtAtaque1").toString());
			
			try{
				rta = ctrlPartida.ataque(energiaAtaque, ctrlPartida.getPersonaje1(), ctrlPartida.getPersonaje2());
				System.out.println(rta);
			}catch(ApplicationException e){
				e.printStackTrace();
			}			
		}else if(request.getParameter("btnAtaque2")!=null){
			//Ataca el jugador 2 
			int energiaAtaque = Integer.parseInt(request.getParameter("txtAtaque2").toString());
			try{
				rta = ctrlPartida.ataque(energiaAtaque, ctrlPartida.getPersonaje2(), ctrlPartida.getPersonaje1());
				System.out.println(rta);
			}catch(ApplicationException e){
				e.printStackTrace();
			}						
		}
		if (request.getParameter("btnDefensa1")!=null){
			//defiende el jugador 1 
			try {
				rta = ctrlPartida.defensaEnergia(ctrlPartida.getPersonaje1());
				rta = ctrlPartida.defensaVida(ctrlPartida.getPersonaje1());
				System.out.println(rta);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (request.getParameter("btnDefensa2")!=null){
			//defiende el jugador 2 
			try {
				rta = ctrlPartida.defensaEnergia(ctrlPartida.getPersonaje2());
				rta = ctrlPartida.defensaVida(ctrlPartida.getPersonaje2());
				System.out.println(rta);
			} catch (ApplicationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.getRequestDispatcher("partida.jsp").forward(request, response);		
	}

}
