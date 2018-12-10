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
import dao.Dao_Lahja;
import dao.Dao_Lahjansaaja;

@WebServlet("/Servlet_HaeLahjat")

public class Servlet_HaeLahjat extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Servlet_HaeLahjat() {
		super();
		System.out.println("Servlet_HaeLahjat.Servlet_HaeLahjat()");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet_HaeLahjat.doGet()");
		String hakusana = request.getParameter("hakusana");

		HttpSession session = request.getSession(true);
		session.setAttribute("hakusanaLahja", hakusana);

		Dao_Lahja dao = new Dao_Lahja();
		try {
			ArrayList<Lahjatieto> lahjat = dao.haeLahjatiedot(hakusana);
			request.setAttribute("lahjat", lahjat);
			String jsp = "/lahjahaku.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Servlet_HaeLahjat.doPost()");
	}

}
