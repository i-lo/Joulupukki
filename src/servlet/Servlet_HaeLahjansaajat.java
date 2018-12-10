package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Lahjansaaja;
import dao.Dao_Lahjansaaja;


@WebServlet("/Servlet_HaeLahjansaajat")
public class Servlet_HaeLahjansaajat extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;
    
	   
    public Servlet_HaeLahjansaajat() {
        super();
       System.out.println("Servlet_HaeLahjansaajat.Servlet_HaeLahjansaajat()");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_HaeLahjansaajaat.doGet()");
		String hakusana = request.getParameter("hakusana");
		
		HttpSession session = request.getSession(true);
		session.setAttribute("hakusanaLahjansaaja", hakusana);	
				
		Dao_Lahjansaaja dao = new Dao_Lahjansaaja();
		try {
			ArrayList<Lahjansaaja> lahjansaajat = dao.haeLahjansaajat(hakusana);
			request.setAttribute("lahjansaajat", lahjansaajat);		
			String jsp = "/lahjansaajahaku.jsp"; 
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);	
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_HaeLahjansaajat.doPost()");
	}
	
	

}
