package servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Kauppa;
import model.Lahjansaaja;
import dao.Dao_Kauppa;
import dao.Dao_Lahjansaaja;

@WebServlet("/Servlet_MuokkaaKauppa")
public class Servlet_MuokkaaKauppa extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	   
    public Servlet_MuokkaaKauppa() {
        super();
        System.out.println("Servlet_MuokkaaKauppa.Servlet_MuokkaaKauppa()");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_MuokkaaKauppa.doGet()");
		String id = request.getParameter("Kauppa_id");
		id=id.replace("muokkaa_", "");
		Dao_Kauppa dao = new Dao_Kauppa();
		try {
			Kauppa kauppa = dao.haeKauppa(Integer.parseInt(id));
			request.setAttribute("kauppa", kauppa);
			String jsp = "/muokkaakauppa.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);	
		} catch (Exception e) {			
			e.printStackTrace();
		} 
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_MuokkaaKauppa.doPost()");
		
		Kauppa kauppa = new Kauppa();
		kauppa.setKauppa_id(Integer.parseInt(request.getParameter("Kauppa_id")));
		kauppa.setKauppa(request.getParameter("kauppanimi"));
		kauppa.setKatuosoite(request.getParameter("osoite"));
		kauppa.setPostinumero(request.getParameter("postinumero"));
		
		Dao_Kauppa daoK = new Dao_Kauppa();
		daoK.muokkaaKauppa(kauppa);
	
		//Kaupan nimi pit‰‰ enkoodata, koska se kulkee urlissa ja siin‰ voi olla skandeja
		response.sendRedirect("Servlet_HaeKaupat?hakusana=" + URLEncoder.encode(request.getParameter("kauppanimi"), "UTF-8"));
	}
	
	
}
