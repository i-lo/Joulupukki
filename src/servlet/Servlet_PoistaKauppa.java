package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao_Kauppa;
import dao.Dao_Lahjansaaja;

@WebServlet("/Servlet_PoistaKauppa")
public class Servlet_PoistaKauppa extends HttpServlet {

	private static final long serialVersionUID = 1L;
	   
    public Servlet_PoistaKauppa() {
        super();
        System.out.println("Servlet_PoistaKauppa.Servlet_PoistaKauppa()");
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_PoistaKauppa.doGet()");
		String id = request.getParameter("Kauppa_id");
		id=id.replace("poista_", "");
		Dao_Kauppa daoK = new Dao_Kauppa();
		try {
			daoK.poistaKauppa(Integer.parseInt(id));
			HttpSession session = request.getSession(true);
			response.sendRedirect("Servlet_HaeKaupat?hakusana=" + session.getAttribute("hakusanaKauppa"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_PoistaKauppa.doPost()");
	}

	
	
}
