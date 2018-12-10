package dao;

import java.util.ArrayList;

import model.Kauppa;
import model.Lahja;
import model.Lahjatieto;

public class Dao_Lahja extends Dao {
	
	public boolean lisaaLahja(Lahjatieto lahjatieto) throws Exception{
		boolean paluuArvo=true;
		
		sql="SELECT * FROM OP_LAHJAT WHERE TUOTENIMI=?";
		con=yhdista();
		if(con!=null){ 
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setString(1, lahjatieto.getLahja().getTuotenimi());
    		rs = stmtPrep.executeQuery();  
    		if(rs.isBeforeFirst()){ //jos kysely tuotti dataa, eli tuote jo lisätty
    			paluuArvo=false; //    			 			       			
			}	
    		con.close(); 
		}		
		if(paluuArvo==true){
		sql="INSERT INTO OP_LAHJAT(TUOTENIMI, HINTA, KAUPPA_ID) VALUES (?,?,?)";
				try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setString(1, lahjatieto.getLahja().getTuotenimi());
			stmtPrep.setInt(2, lahjatieto.getLahja().getHinta());
			stmtPrep.setInt(3, lahjatieto.getKauppa().getKauppa_id());
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}
		}
		return paluuArvo;
	
	}
	
	
	public boolean muokkaaLahja(Lahjatieto lahjatieto){
		boolean paluuArvo=true;
		
		sql="UPDATE OP_LAHJAT SET TUOTENIMI=?, HINTA=?, KAUPPA_ID=? WHERE LAHJA_ID=?";
		
	
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setString(1, lahjatieto.getLahja().getTuotenimi());
			stmtPrep.setInt(2, lahjatieto.getLahja().getHinta());
			stmtPrep.setInt(3, lahjatieto.getKauppa().getKauppa_id());
			stmtPrep.setInt(4, lahjatieto.getLahja().getLahja_id());
			stmtPrep.executeUpdate();
	        con.close();
		} catch (Exception e) {				
			e.printStackTrace();
			paluuArvo=false;
		}				
		return paluuArvo;
	}
	
	public ArrayList<Lahjatieto> haeLahjatiedot(String hakusana) throws Exception{		
		ArrayList<Lahjatieto> lahjatiedot = new ArrayList<Lahjatieto>();
		sql = "SELECT * FROM OP_LAHJAT WHERE (TUOTENIMI LIKE ?) AND POISTETTU=?"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setString(1, "%"+hakusana+"%");
			stmtPrep.setInt(2,  0); // hakee lahjat, joita ei poistettu
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){ //jos kysely onnistui									
				while(rs.next()){
					Lahja lahja = new Lahja();
					lahja.setLahja_id(rs.getInt("LAHJA_ID"));
					lahja.setTuotenimi(rs.getString("TUOTENIMI"));
					lahja.setHinta(rs.getInt("HINTA"));
					
					Dao_Kauppa daoKauppa = new Dao_Kauppa();
					
					Kauppa kauppa = daoKauppa.haeKauppa(rs.getInt("KAUPPA_ID"));		
					
					Lahjatieto lahjatieto = new Lahjatieto(kauppa, lahja);
					lahjatiedot.add(lahjatieto);
					
				}					
			}
			con.close();
		}			
		return lahjatiedot;
	}
	
	
	public int haeLahjanId (String hakusana) throws Exception{		
		int id=0;
		sql = "SELECT * FROM OP_LAHJAT WHERE (TUOTENIMI LIKE ?) AND POISTETTU=?"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setString(1, "%"+hakusana+"%");
			stmtPrep.setInt(2,  0); // hakee lahjat, joita ei poistettu
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){ //jos kysely onnistui									
				while(rs.next()){
					id = rs.getInt("LAHJA_ID");
					
					
				}					
			}
			con.close();
		}			
		return id;
	}
	
	
	public ArrayList<Lahjatieto> haeKaikkiLahjat() throws Exception{		
		ArrayList<Lahjatieto> lahjatiedot = new ArrayList<Lahjatieto>();
		sql = "SELECT * FROM OP_LAHJAT WHERE POISTETTU=?"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql);
			stmtPrep.setInt(1,  0); // hakee lahjat, joita ei poistettu
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){ //jos kysely onnistui									
				while(rs.next()){
					Lahja lahja = new Lahja();
					lahja.setLahja_id(rs.getInt("LAHJA_ID"));
					lahja.setTuotenimi(rs.getString("TUOTENIMI"));
					lahja.setHinta(rs.getInt("HINTA"));
					
					Dao_Kauppa daoK = new Dao_Kauppa();
					Kauppa kauppa = new Kauppa();
					kauppa = daoK.haeKauppa(rs.getInt("KAUPPA_ID"));
					
					Lahjatieto lahjatieto = new Lahjatieto(kauppa, lahja);
					lahjatiedot.add(lahjatieto);
					
				}					
			}
			con.close();
		}			
		return lahjatiedot;
	}
	
	
	public Lahjatieto haeLahjatieto(int lahja_id) throws Exception{
		Lahja lahja=null;
		Lahjatieto lahjatieto = null;
		sql = "SELECT * FROM OP_LAHJAT WHERE LAHJA_ID=? AND POISTETTU=?"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setInt(1, lahja_id);	
			stmtPrep.setInt(2,  0); // hakee lahjat, joita ei poistettu
    		rs = stmtPrep.executeQuery();  
			if(rs!=null){ //jos kysely onnistui									
				while(rs.next()){
					lahja = new Lahja();
					lahja.setLahja_id(rs.getInt("LAHJA_ID"));
					lahja.setTuotenimi(rs.getString("TUOTENIMI"));
					lahja.setHinta(rs.getInt("HINTA"));
					
					Dao_Kauppa daoK = new Dao_Kauppa();
					Kauppa kauppa = new Kauppa();
					kauppa = daoK.haeKauppa(rs.getInt("KAUPPA_ID"));
					
					lahjatieto = new Lahjatieto(kauppa,lahja);
				}					
			}
			con.close();
		}			
		return lahjatieto;
	}
	
	public void poistaLahja(int lahja_id) throws Exception{		
		sql = "UPDATE OP_LAHJAT SET POISTETTU=1 WHERE LAHJA_ID=?"; 		
		con=yhdista();
		if(con!=null){ //jos yhteys onnistui
			stmtPrep = con.prepareStatement(sql); 
			stmtPrep.setInt(1, lahja_id);			
			stmtPrep.executeUpdate();			
			con.close();
		}		
	}
	

}
