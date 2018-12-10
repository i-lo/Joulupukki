package model;

import java.util.ArrayList;

public class Lahjansaaja {
	private int lahjansaaja_id;
	private String etunimi, sukunimi, kotiosoite, postinumero;
	
	
	public Lahjansaaja(){
		super();
	}
	
	
	public Lahjansaaja(int lahjansaaja_id, String etunimi, String sukunimi, String kotiosoite, String postinumero) {
		super();
		this.lahjansaaja_id = lahjansaaja_id;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.kotiosoite = kotiosoite;
		this.postinumero = postinumero;

	}
	
	



	@Override
	public String toString() {
		return "Lahjansaajat [lahjansaaja_id=" + lahjansaaja_id + ", etunimi=" + etunimi + ", sukunimi=" + sukunimi
				+ ", kotiosoite=" + kotiosoite + ", postinumero=" + postinumero + "]";
	}
	
	
	public int getLahjansaaja_id() {
		return lahjansaaja_id;
	}
	
	
	public void setLahjansaaja_id(int lahjansaaja_id) {
		this.lahjansaaja_id = lahjansaaja_id;
	}
	
	
	public String getEtunimi() {
		return etunimi;
	}
	
	
	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}
	
	
	public String getSukunimi() {
		return sukunimi;
	}
	
	
	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}
	
	
	public String getKotiosoite() {
		return kotiosoite;
	}
	
	
	public void setKotiosoite(String kotiosoite) {
		this.kotiosoite = kotiosoite;
	}
	
	
	public String getPostinumero() {
		return postinumero;
	}
	
	
	public void setPostinumero(String postinumero) {
		this.postinumero = postinumero;
	}

}



