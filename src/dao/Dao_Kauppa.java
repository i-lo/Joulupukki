package dao;

import java.util.ArrayList;

import model.Kauppa;

public class Dao_Kauppa extends Dao {
	
	public boolean lisaaKauppa(Kauppa kauppa) throws Exception{
		boolean paluuArvo=true;
		
		sql="SELECT * FROM OP_KAUPAT WHERE KAUPPA = ?";
		con=yhdista();
		if(con!=null){ 
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setString(1, kauppa.getKauppa());
    		rs = stmtPrep.executeQuery();  
    		if(rs.isBeforeFirst()){ //jos kysely tuotti dataa, eli kauppa lisätty
    			paluuArvo=false; //    			 			       			
			}	
    		con.close(); 
		}		
		if(paluuArvo==true){

		sql="INSERT INTO OP_KAUPAT(KAUPPA, KATUOSOITE, POSTINUMERO, KOTISIVU) VALUES (?,?,?,?)";
				try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setString(1, kauppa.getKauppa());
			stmtPrep.setString(2, kauppa.getKatuosoite());
			stmtPrep.setString(3, kauppa.getPostinumero());
			stmtPrep.setString(4, kauppa.getKotisivu());
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}		
	}
	
		return paluuArvo;
	}
	
	public boolean lisaaKauppaNimella(String kaupanNimi) throws Exception{
		boolean paluuArvo=true;
		
		sql="SELECT * FROM OP_KAUPAT WHERE KAUPPA = ?";
		con=yhdista();
		if(con!=null){ 
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setString(1, kaupanNimi);
    		rs = stmtPrep.executeQuery();  
    		if(rs.isBeforeFirst()){ //jos kysely tuotti dataa, eli kauppa lisätty
    			paluuArvo=false; //    			 			       			
			}	
    		con.close(); 
		}		
		if(paluuArvo==true){

		sql="INSERT INTO OP_KAUPAT(KAUPPA, KATUOSOITE, POSTINUMERO, KOTISIVU) VALUES (?,?,?,?)";
				try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setString(1, kaupanNimi);
			stmtPrep.setString(2, "");
			stmtPrep.setString(3, "");
			stmtPrep.setString(4, "");
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}		
	}
	
		return paluuArvo;
	}
	
	
	
	
	
	public boolean muokkaaKauppa(Kauppa kauppa){
		boolean paluuArvo=true;
		
		sql="UPDATE OP_KAUPAT SET KAUPPA=?, KATUOSOITE=?, POSTINUMERO=?, KOTISIVU=? WHERE KAUPPA_ID=?";
		
	
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setString(1, kauppa.getKauppa());
			stmtPrep.setString(2, kauppa.getKatuosoite());
			stmtPrep.setString(3, kauppa.getPostinumero());
			stmtPrep.setString(4, kauppa.getKotisivu());
			stmtPrep.setInt(5, kauppa.getKauppa_id());
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}				
		return paluuArvo;
	}
	
	public ArrayList<Kauppa> haeKaupat(String hakusana) throws Exception{		
		ArrayList<Kauppa> kaupat = new ArrayList<Kauppa>();
		sql = "SELECT * FROM OP_KAUPAT WHERE (KAUPPA LIKE ?) AND POISTETTU = ?"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setString(1, "%"+hakusana+"%");
			stmtPrep.setInt(2, 0);
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){ //jos kysely onnistui									
				while(rs.next()){
					Kauppa kauppa = new Kauppa();
					kauppa.setKauppa_id(rs.getInt("KAUPPA_ID"));
					kauppa.setKauppa(rs.getString("KAUPPA"));
					kauppa.setKatuosoite(rs.getString("KATUOSOITE"));
					kauppa.setPostinumero(rs.getString("POSTINUMERO"));
					kauppa.setKotisivu(rs.getString("KOTISIVU"));
					kaupat.add(kauppa);
				}					
			}
			con.close();
		}			
		return kaupat;
	}
	
	
	public Kauppa haeKauppa(int kauppa_id) throws Exception{
		Kauppa kauppa=null;		
		sql = "SELECT * FROM OP_KAUPAT WHERE KAUPPA_ID=?"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setInt(1, kauppa_id);			
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){ //jos kysely onnistui									
				while(rs.next()){
					kauppa = new Kauppa();
					kauppa.setKauppa_id(rs.getInt("KAUPPA_ID"));
					kauppa.setKauppa(rs.getString("KAUPPA"));
					kauppa.setKatuosoite(rs.getString("KATUOSOITE"));
					kauppa.setPostinumero(rs.getString("POSTINUMERO"));
					kauppa.setKotisivu(rs.getString("KOTISIVU"));
				}					
			}
			con.close();
		}			
		return kauppa;
	}
	
	
	public Kauppa haeKauppaNimella(String kauppaNimi) throws Exception{
		Kauppa kauppa=null;		
		sql = "SELECT * FROM OP_KAUPAT WHERE KAUPPA=?"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setString(1, kauppaNimi);			
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){ //jos kysely onnistui									
				while(rs.next()){
					kauppa = new Kauppa();
					kauppa.setKauppa_id(rs.getInt("KAUPPA_ID"));
					kauppa.setKauppa(rs.getString("KAUPPA"));
					kauppa.setKatuosoite(rs.getString("KATUOSOITE"));
					kauppa.setPostinumero(rs.getString("POSTINUMERO"));
					kauppa.setKotisivu(rs.getString("KOTISIVU"));
				}					
			}
			con.close();
		}			
		return kauppa;
	}
	
	public void poistaKauppa(int kauppa_id) throws Exception{		
		sql = "UPDATE OP_KAUPAT SET POISTETTU=1 WHERE KAUPPA_ID=?"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setInt(1, kauppa_id);			
			stmtPrep.executeUpdate();			
			con.close();
		}		
	}
}

