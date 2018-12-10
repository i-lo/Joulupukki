package servlet;

import java.io.IOException;
import java.net.URLEncoder;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Lahjansaaja;
import dao.Dao_Lahjansaaja;

@WebServlet("/Servlet_MuokkaaLahjansaaja")
public class Servlet_MuokkaaLahjansaaja extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	   
    public Servlet_MuokkaaLahjansaaja() {
        super();
        System.out.println("Servlet_MuokkaaLahjansaaja.Servlet_MuokkaaLahjansaaja()");
    }
	
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_MuokkaaLahjansaaja.doGet()");
		String id = request.getParameter("Lahjansaaja_id");
		id=id.replace("muokkaa_", "");
		Dao_Lahjansaaja dao = new Dao_Lahjansaaja();
		try {
			Lahjansaaja lahjansaaja = dao.haeLahjansaaja(Integer.parseInt(id));
			request.setAttribute("lahjansaaja", lahjansaaja);
			String jsp = "/muokkaalahjansaaja.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);	
		} catch (Exception e) {			
			e.printStackTrace();
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_MuokkaaLahjansaaja.doPost()");
		
		Lahjansaaja lahjansaaja = new Lahjansaaja();
		lahjansaaja.setLahjansaaja_id(Integer.parseInt(request.getParameter("Lahjansaaja_id")));
		lahjansaaja.setEtunimi(request.getParameter("etunimi"));
		lahjansaaja.setSukunimi(request.getParameter("sukunimi"));
		lahjansaaja.setKotiosoite(request.getParameter("kotiosoite"));
		lahjansaaja.setPostinumero(request.getParameter("postinumero"));
		
		
		Dao_Lahjansaaja dao = new Dao_Lahjansaaja();
		dao.muokkaaLahjansaaja(lahjansaaja);
		//Sukunimi pit‰‰ enkoodata, koska se kulkee urlissa ja siin‰ voi olla skandeja
		response.sendRedirect("Servlet_HaeLahjansaajat?hakusana=" + URLEncoder.encode(request.getParameter("sukunimi"), "UTF-8"));
	}


}
