package servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao_Kauppa;
import dao.Dao_Lahja;
import model.Kauppa;
import model.Lahja;
import model.Lahjatieto;

@WebServlet("/Servlet_MuokkaaLahja")
public class Servlet_MuokkaaLahja extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
	   
    public Servlet_MuokkaaLahja() {
        super();
        System.out.println("Servlet_MuokkaaLahja.Servlet_MuokkaaLahja()");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_MuokkaaLahja.doGet()");
		String id = request.getParameter("Lahja_id");
		id=id.replace("muokkaa_", "");
		Dao_Lahja dao = new Dao_Lahja();
		try {
			Lahjatieto lahja = dao.haeLahjatieto(Integer.parseInt(id));
			request.setAttribute("lahja", lahja);
			String jsp = "/muokkaalahja.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);	
		} catch (Exception e) {			
			e.printStackTrace();
		} 
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_MuokkaaLahja.doPost()");
		

		
		
		Lahja lahja = new Lahja();
		lahja.setLahja_id(Integer.parseInt(request.getParameter("Lahja_id")));
		lahja.setTuotenimi(request.getParameter("tuotenimi"));
		lahja.setHinta(Integer.parseInt(request.getParameter("hinta")));
		
		Dao_Kauppa daoK = new Dao_Kauppa();
		Kauppa kauppa = new Kauppa();
	
		String kaupanNimi= request.getParameter("kauppanimi");
		
		try {
			kauppa = daoK.haeKauppaNimella(kaupanNimi);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Lahjatieto lahjatieto = new Lahjatieto(kauppa, lahja);
		
		Dao_Lahja daoL = new Dao_Lahja();
		daoL.muokkaaLahja(lahjatieto);
		

		
		
	
	
		//Kaupan nimi pit‰‰ enkoodata, koska se kulkee urlissa ja siin‰ voi olla skandeja
		response.sendRedirect("Servlet_HaeKaupat?hakusana=" + URLEncoder.encode(request.getParameter("kauppanimi"), "UTF-8"));
	}
	
}
