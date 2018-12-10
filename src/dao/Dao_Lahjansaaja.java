package dao;

import java.util.ArrayList;

import model.Lahja;
import model.Lahjansaaja;
import model.Lahjatieto;
import model.SaajanLahja;

public class Dao_Lahjansaaja extends Dao {

	public boolean lisaaLahjansaaja(Lahjansaaja lahjansaaja) throws Exception {
		boolean paluuArvo = true;

		sql = "SELECT * FROM OP_LAHJANSAAJAT WHERE ETUNIMI=?";
		con = yhdista();
		if (con != null) {
			stmtPrep = con.prepareStatement(sql);
			stmtPrep.setString(1, lahjansaaja.getEtunimi());
			rs = stmtPrep.executeQuery();
			if (rs.isBeforeFirst()) { // jos kysely tuotti dataa, eli saaja jo
										// lisätty
				paluuArvo = false; //
			}
			con.close();
		}
		if (paluuArvo == true) {
			sql = "INSERT INTO OP_LAHJANSAAJAT(ETUNIMI, SUKUNIMI, KOTIOSOITE, POSTINUMERO) VALUES (?,?,?,?)";
			try {
				con = yhdista();
				stmtPrep = con.prepareStatement(sql);
				stmtPrep.setString(1, lahjansaaja.getEtunimi());
				stmtPrep.setString(2, lahjansaaja.getSukunimi());
				stmtPrep.setString(3, lahjansaaja.getKotiosoite());
				stmtPrep.setString(4, lahjansaaja.getPostinumero());
				stmtPrep.executeUpdate();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				paluuArvo = false;
			}
		}
		return paluuArvo;

	}

	public boolean muokkaaLahjansaaja(Lahjansaaja lahjansaaja) {
		boolean paluuArvo = true;

		sql = "UPDATE OP_LAHJANSAAJAT SET ETUNIMI=?, SUKUNIMI=?, KOTIOSOITE=?, POSTINUMERO=? WHERE LAHJANSAAJA_ID=?";

		System.out.println("TESTI TESTI " + lahjansaaja.getPostinumero());
		try {
			con = yhdista();
			stmtPrep = con.prepareStatement(sql);
			stmtPrep.setString(1, lahjansaaja.getEtunimi());
			stmtPrep.setString(2, lahjansaaja.getSukunimi());
			stmtPrep.setString(3, lahjansaaja.getKotiosoite());
			stmtPrep.setString(4, lahjansaaja.getPostinumero());
			stmtPrep.setInt(5, lahjansaaja.getLahjansaaja_id());
			stmtPrep.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			paluuArvo = false;
		}
		return paluuArvo;
	}

	public ArrayList<Lahjansaaja> haeLahjansaajat(String hakusana) throws Exception {
		ArrayList<Lahjansaaja> lahjansaajat = new ArrayList<Lahjansaaja>();
		sql = "SELECT * FROM OP_LAHJANSAAJAT WHERE (ETUNIMI LIKE ? OR SUKUNIMI LIKE ?) AND POISTETTU=?";
		con = yhdista();
		if (con != null) { // jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql);
			stmtPrep.setString(1, "%" + hakusana + "%");
			stmtPrep.setString(2, "%" + hakusana + "%");
			stmtPrep.setInt(3, 0); // hakee ne, joita ei poistettu
			rs = stmtPrep.executeQuery();
			if (rs != null) { // jos kysely onnistui
				while (rs.next()) {
					Lahjansaaja lahjansaaja = new Lahjansaaja();
					lahjansaaja.setLahjansaaja_id(rs.getInt("LAHJANSAAJA_ID"));
					lahjansaaja.setEtunimi(rs.getString("ETUNIMI"));
					lahjansaaja.setSukunimi(rs.getString("SUKUNIMI"));
					lahjansaaja.setKotiosoite(rs.getString("KOTIOSOITE"));
					lahjansaaja.setPostinumero(rs.getString("POSTINUMERO"));
					lahjansaajat.add(lahjansaaja);

				}
			}
			con.close();
		}
		return lahjansaajat;
	}
	
	
	public ArrayList<Lahjansaaja> haeKaikkiLahjansaajat() throws Exception {
		ArrayList<Lahjansaaja> lahjansaajat = new ArrayList<Lahjansaaja>();
		sql = "SELECT * FROM OP_LAHJANSAAJAT WHERE POISTETTU=?";
		con = yhdista();
		if (con != null) { // jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql);
			stmtPrep.setInt(1, 0); // hakee ne, joita ei poistettu
			rs = stmtPrep.executeQuery();
			if (rs != null) { // jos kysely onnistui
				while (rs.next()) {
					Lahjansaaja lahjansaaja = new Lahjansaaja();
					lahjansaaja.setLahjansaaja_id(rs.getInt("LAHJANSAAJA_ID"));
					lahjansaaja.setEtunimi(rs.getString("ETUNIMI"));
					lahjansaaja.setSukunimi(rs.getString("SUKUNIMI"));
					lahjansaaja.setKotiosoite(rs.getString("KOTIOSOITE"));
					lahjansaaja.setPostinumero(rs.getString("POSTINUMERO"));
					lahjansaajat.add(lahjansaaja);

				}
			}
			con.close();
		}
		return lahjansaajat;
	}
	
	

	public Lahjansaaja haeLahjansaaja(int lahjansaaja_id) throws Exception {
		Lahjansaaja lahjansaaja = null;
		sql = "SELECT * FROM OP_LAHJANSAAJAT WHERE LAHJANSAAJA_ID=? AND POISTETTU=?";
		con = yhdista();
		if (con != null) { // jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql);
			stmtPrep.setInt(1, lahjansaaja_id);
			stmtPrep.setInt(2, 0); // 0 tarkoittaa, että lahjansaajaa ei ole
									// poistettu
			rs = stmtPrep.executeQuery();
			if (rs != null) { // jos kysely onnistui
				while (rs.next()) {
					lahjansaaja = new Lahjansaaja();
					lahjansaaja.setLahjansaaja_id(rs.getInt("LAHJANSAAJA_ID"));
					lahjansaaja.setEtunimi(rs.getString("ETUNIMI"));
					lahjansaaja.setSukunimi(rs.getString("SUKUNIMI"));
					lahjansaaja.setKotiosoite(rs.getString("KOTIOSOITE"));
					lahjansaaja.setPostinumero(rs.getString("POSTINUMERO"));

				}
			}
			con.close();
		}
		return lahjansaaja;
	}

	public void poistaLahjansaaja(int lahjansaaja_id) throws Exception {
		sql = "UPDATE OP_LAHJANSAAJAT SET POISTETTU=1 WHERE LAHJANSAAJA_ID=?";
		con = yhdista();
		if (con != null) { // jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql);
			stmtPrep.setInt(1, lahjansaaja_id);
			stmtPrep.executeUpdate();
			con.close();
		}
	}

	
	
	public void lisaaLahjaSaajalle (int lahjansaaja_id, int lahjaID) throws Exception{
		
		sql = "INSERT INTO OP_LSAAJAT_LAHJAT (LAHJANSAAJA_ID, LAHJA_ID, OSTETTU, PAKETOITU) VALUES (?,?,?,?)";
		
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setInt(1, lahjansaaja_id);
			stmtPrep.setInt(2, lahjaID);
			stmtPrep.setInt(3, 0);
			stmtPrep.setInt(4, 0);
			stmtPrep.executeUpdate();
			con.close();
		 
		} catch (Exception e) {				
		e.printStackTrace();
				
		}
	}
	
	
	public ArrayList<Lahja> haeLahjansaajanLahjat (int lahjansaajaId) throws Exception {
		ArrayList<Lahja> lahjat = new ArrayList<>();
		sql = "SELECT * FROM OP_LSAAJAT_LAHJAT WHERE LAHJANSAAJA_ID =?";
		con = yhdista();
		if (con != null) { // jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql);
			stmtPrep.setInt(1, lahjansaajaId);
			rs = stmtPrep.executeQuery();
			if (rs != null) { // jos kysely onnistui
				while (rs.next()) {
					Lahja lahja = new Lahja();
					lahja.setLahja_id(rs.getInt("LAHJA_ID"));
					lahja.setOstettu(rs.getInt("OSTETTU"));
					lahja.setPaketoitu(rs.getInt("PAKETOITU"));
					
					lahjat.add(lahja);

				}
			}
			con.close();
		}
		return lahjat;
	}
	
	
	public ArrayList<Lahja> haeKaikkienLahjat () throws Exception {
		ArrayList<Lahja> lahjat = new ArrayList<>();
		sql = "SELECT * FROM OP_LSAAJAT_LAHJAT WHERE POISTETTU=?";
		con = yhdista();
		if (con != null) { // jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql);
			stmtPrep.setInt(1, 0); // hakee ne, joita ei poistettu
			rs = stmtPrep.executeQuery();
			if (rs != null) { // jos kysely onnistui
				while (rs.next()) {
					Lahja lahja = new Lahja();
					lahja.setLahja_id(rs.getInt("LAHJA_ID"));
					lahja.setOstettu(rs.getInt("OSTETTU"));
					lahja.setPaketoitu(rs.getInt("PAKETOITU"));
					
					lahjat.add(lahja);
					
					
					Lahjansaaja lahjansaaja = new Lahjansaaja();
					lahjansaaja.setLahjansaaja_id(rs.getInt("LAHJANSAAJA_ID"));
					
					

				}
			}
			con.close();
		}
		return lahjat;
	}
	

}
