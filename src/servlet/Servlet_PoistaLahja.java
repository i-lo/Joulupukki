package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.Dao_Kauppa;
import dao.Dao_Lahja;

@WebServlet("/Servlet_PoistaLahja")
public class Servlet_PoistaLahja extends HttpServlet {

	private static final long serialVersionUID = 1L;
	   
    public Servlet_PoistaLahja() {
        super();
        System.out.println("Servlet_PoistaLahja.Servlet_PoistaLahja()");
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_PoistaLahja.doGet()");
		String id = request.getParameter("Lahja_id");
		id=id.replace("poista_", "");
		Dao_Lahja daoL = new Dao_Lahja();
		try {
			daoL.poistaLahja(Integer.parseInt(id));
			HttpSession session = request.getSession(true);
			response.sendRedirect("Servlet_HaeLahjat?hakusana=" + session.getAttribute("hakusanaLahja"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_PoistaLahja.doPost()");
	}
	
	
}
