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

import model.Lahja;
import model.Lahjansaaja;
import model.Lahjatieto;
import model.SaajanLahja;
import dao.Dao_Lahja;
import dao.Dao_Lahjansaaja;

@WebServlet("/Servlet_LahjatietojenTulostus")
public class Servlet_LahjatietojenTulostus extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Servlet_LahjatietojenTulostus() {
		super();
		System.out.println("Servlet_LahjatietojenTulostus.Servlet_LahjatietojenTulostus()");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet_LahjatietojenTulostus.doGet()");
		
		
		try {
			
			Dao_Lahjansaaja daoL = new Dao_Lahjansaaja();
			ArrayList<Lahjansaaja> kaikkiLahjansaajat = daoL.haeKaikkiLahjansaajat();
			
			ArrayList<Lahja> tyypinLahjat = new ArrayList();
			ArrayList<SaajanLahja> kaikkienSaajienKaikkiLahjatiedot =  new ArrayList();
			
			Dao_Lahja daoLahja = new Dao_Lahja();
			
			for (Lahjansaaja lahjansaaja : kaikkiLahjansaajat) { // k‰y l‰pi lahjansaajat 
				tyypinLahjat = daoL.haeLahjansaajanLahjat(lahjansaaja.getLahjansaaja_id()); // lis‰‰ yhden saajan lahjat listalle
				
				ArrayList<Lahjatieto> tyypinLahjatiedot = new ArrayList(); 
				
				for (Lahja lahjanen : tyypinLahjat) { // k‰y l‰pi yhden ihmisen lahjalistan
					Lahjatieto lahjatieto = daoLahja.haeLahjatieto(lahjanen.getLahja_id()); // hakee yhden lahjan kaikki tiedot (lahjatietto = lahjan ja kaupan tiedot)
					tyypinLahjatiedot.add(lahjatieto); // hakee lahjan lahjatiedot (lahja ja kauppatiedot) ja lis‰‰ saajan lahjatiedot-listalle
				}
				SaajanLahja saajanLahjat = new SaajanLahja(lahjansaaja.getLahjansaaja_id(), lahjansaaja.getEtunimi(), lahjansaaja.getSukunimi(), tyypinLahjatiedot); // yhdistet‰‰n saaja ja h‰nen lahjatiedot
				kaikkienSaajienKaikkiLahjatiedot.add(saajanLahjat); // lista, jossa kaikki lahjansaajat lahjatietoineen
			}
			
			
			String hakusana = request.getParameter("hakusana");
			
			HttpSession session = request.getSession(true);
			session.setAttribute("hakusanaLahjansaaja", hakusana);	
			

			request.setAttribute("kaikkienSaajienKaikkiLahjatiedot", kaikkienSaajienKaikkiLahjatiedot);
			String jsp = "/kaikkienlahjojentulostus.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet_LahjatietojenTulostus.doPost()");
	}
	
}
