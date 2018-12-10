package servlet;

import javax.servlet.annotation.WebServlet;


import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Lahjansaaja;
import model.Lahjatieto;
import dao.Dao_Lahja;
import dao.Dao_Lahjansaaja;



@WebServlet("/Servlet_LahjaListalle")
public class Servlet_LahjaListalle extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String id;
    
	   
    public Servlet_LahjaListalle() {
        super();
        System.out.println("Servlet_LahjaListalle.Servlet_LahjaListalle()");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_LahjaListalle.doGet()");
	
		this.id = request.getParameter("Lahjansaaja_id");
		id=id.replace("lahjalistalle_", "");
		Dao_Lahja dao = new Dao_Lahja();
	

		try {
			List<Lahjatieto> lahjatiedot = dao.haeKaikkiLahjat();
			request.setAttribute("lahjatiedot", lahjatiedot);
			request.setAttribute("id", id);
			String jsp = "/lahjalistalle.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);	
		} catch (Exception e) {			
			e.printStackTrace();
		} 
		
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	String lahja = request.getParameter("tuotenimi");
	System.out.println(lahja + " " + this.id);
	
	Dao_Lahja daoLahja = new Dao_Lahja();
	Dao_Lahjansaaja dLahjansaaja = new Dao_Lahjansaaja();
	
	try {
		dLahjansaaja.lisaaLahjaSaajalle(Integer.parseInt(this.id), daoLahja.haeLahjanId(lahja));
	} catch (NumberFormatException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	

	response.sendRedirect("/Joulupukki/kaikkienlahjojentulostus.jsp");
		
	
	
	
	}
}
