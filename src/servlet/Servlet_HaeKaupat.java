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

import dao.Dao_Kauppa;
import dao.Dao_Lahja;
import model.Kauppa;
import model.Lahjatieto;


@WebServlet("/Servlet_HaeKaupat")
public class Servlet_HaeKaupat extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public Servlet_HaeKaupat() {
		super();
		System.out.println("Servlet_HaeKaupat.Servlet_HaeKaupat()");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet_HaeKaupat.doGet()");
		String hakusana = request.getParameter("hakusana");

		HttpSession session = request.getSession(true);
		session.setAttribute("hakusanaKauppa", hakusana);

		Dao_Kauppa dao = new Dao_Kauppa();

		try {
			ArrayList<Kauppa> kaupat = dao.haeKaupat(hakusana);
			request.setAttribute("kaupat", kaupat);
			String jsp = "/kauppahaku.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet_HaeKaupat.doPost()");
	}

}
