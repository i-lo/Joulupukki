package servlet;

import java.io.IOException;
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

@WebServlet("/Servlet_UusiKauppa")
public class Servlet_UusiKauppa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Servlet_UusiKauppa() {
        super();
        System.out.println("Servlet_UusiKauppa.Servlet_UusiKauppa()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet_UusiKauppa.doGet()");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet_UusiKauppa.doPost()");
		/*
		 * Helper helper = new Helper(); String tiedot =
		 * helper.naytaMuuttujaArvoparit(request, response); PrintWriter out =
		 * response.getWriter(); response.setContentType("text/html");
		 * out.println(tiedot);
		 */

	
		Kauppa kauppa = new Kauppa();
		kauppa.setKauppa(request.getParameter("kauppanimi"));
		kauppa.setKatuosoite(request.getParameter("osoite"));
		kauppa.setPostinumero(request.getParameter("postinumero"));
		
		
		Dao_Kauppa daoK = new Dao_Kauppa();
		try {
			if (daoK.lisaaKauppa(kauppa)) {
				response.sendRedirect("uusikauppa.jsp?ok=1");
			} else {
				response.sendRedirect("uusikauppa.jsp?ok=0");
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		

	}

}
