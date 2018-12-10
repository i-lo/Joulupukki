package servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao_Lahjansaaja;
import model.Lahjansaaja;

@WebServlet("/Servlet_UusiLahjansaaja")
public class Servlet_UusiLahjansaaja extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
    
    public Servlet_UusiLahjansaaja() {
        super();
        System.out.println("Servlet_UusiLahjansaaja.Servlet_UusiLahjansaaja()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_UusiLahjansaaja.doGet()");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_UusiLahjansaaja.doPost()");
		/*
		Helper helper = new Helper();
		String tiedot = helper.naytaMuuttujaArvoparit(request, response);
		PrintWriter out = response.getWriter();
	    response.setContentType("text/html"); 		
	    out.println(tiedot);	
	    */
		
		Lahjansaaja lahjansaaja = new Lahjansaaja();
		lahjansaaja.setEtunimi(request.getParameter("etunimi"));
		lahjansaaja.setSukunimi(request.getParameter("sukunimi"));
		lahjansaaja.setKotiosoite(request.getParameter("kotiosoite"));
		lahjansaaja.setPostinumero(request.getParameter("postinumero"));

		
		Dao_Lahjansaaja dao = new Dao_Lahjansaaja();
		
			try {
				if(dao.lisaaLahjansaaja(lahjansaaja)){
					response.sendRedirect("uusilahjansaaja.jsp?ok=1");
				}else{
					response.sendRedirect("uusilahjansaaja.jsp?ok=0");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}

}
