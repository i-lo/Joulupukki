package dao;

import java.util.ArrayList;

import model.Postinumero;

public class Testaa_Dao_Postinumero {

	public static void main(String[] args) {
		ArrayList<Postinumero> postinumerot = new ArrayList<Postinumero>();
		Dao_Postinumero dao = new Dao_Postinumero();
		try {
			postinumerot = dao.listaaKaikki();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0; i<postinumerot.size();i++) {
			System.out.println(postinumerot.get(i).getPostitoimipaikka());
		}
	}

}
