package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao_Lahjansaaja;

@WebServlet("/Servlet_PoistaLahjansaaja")
public class Servlet_PoistaLahjansaaja extends HttpServlet {

	private static final long serialVersionUID = 1L;
	   
    public Servlet_PoistaLahjansaaja() {
        super();
        System.out.println("Servlet_PoistaLahjansaaja.Servlet_PoistaLahjansaaja()");
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_PoistaLahjansaaja.doGet()");
		String id = request.getParameter("Lahjansaaja_id");
		id=id.replace("poista_", "");
		Dao_Lahjansaaja dao = new Dao_Lahjansaaja();
		try {
			dao.poistaLahjansaaja(Integer.parseInt(id));
			HttpSession session = request.getSession(true);
			response.sendRedirect("Servlet_HaeLahjansaajat?hakusana=" + session.getAttribute("hakusanaLahjansaaja"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_PoistaLahjansaaja.doPost()");
	}

	
	
}
