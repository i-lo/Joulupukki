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


@WebServlet("/Servlet_UusiLahja")


public class Servlet_UusiLahja extends HttpServlet {

private static final long serialVersionUID = 1L;
    
    public Servlet_UusiLahja() {
        super();
        System.out.println("Servlet_UusiLahja.Servlet_UusiLahja()");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_UusiLahja.doGet()");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet_UusiLahja.doPost()");
		/*
		Helper helper = new Helper();
		String tiedot = helper.naytaMuuttujaArvoparit(request, response);
		PrintWriter out = response.getWriter();
	    response.setContentType("text/html"); 		
	    out.println(tiedot);	
	    */
		
		Lahja lahja = new Lahja();
		lahja.setTuotenimi(request.getParameter("tuotenimi"));
		lahja.setHinta(Integer.parseInt(request.getParameter("hinta")));
		
		String kaupanNimi = request.getParameter("kauppa");

		Dao_Kauppa daoK = new Dao_Kauppa();
		Kauppa kauppa = new Kauppa();
		try {
			daoK.lisaaKauppaNimella(kaupanNimi);
			kauppa = daoK.haeKauppaNimella(kaupanNimi);
				
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Dao_Lahja dao = new Dao_Lahja();
		Lahjatieto lahjatieto = new Lahjatieto(kauppa, lahja);
			try {
				if(dao.lisaaLahja(lahjatieto)){
					response.sendRedirect("uusilahja.jsp?ok=1");
				}else{
					response.sendRedirect("uusilahja.jsp?ok=0");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

		
	}
	
	
}
