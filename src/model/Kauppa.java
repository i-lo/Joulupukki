package model;

public class Kauppa {
	private int kauppa_id;
	private String kauppa, katuosoite, postinumero, kotisivu;
	
	public int getKauppa_id() {
		return kauppa_id;
	}
	
	
	public void setKauppa_id(int kauppa_id) {
		this.kauppa_id = kauppa_id;
	}
	
	
	public String getKauppa() {
		return kauppa;
	}
	
	
	public void setKauppa(String kauppa) {
		this.kauppa = kauppa;
	}
	
	
	public String getKatuosoite() {
		return katuosoite;
	}
	
	
	public void setKatuosoite(String katuosoite) {
		this.katuosoite = katuosoite;
	}
	
	
	public String getPostinumero() {
		return postinumero;
	}
	
	
	public void setPostinumero(String postinumero) {
		this.postinumero = postinumero;
	}
	
	
	public String getKotisivu() {
		return kotisivu;
	}
	
	
	public void setKotisivu(String kotisivu) {
		this.kotisivu = kotisivu;
	}
	
	
	@Override
	public String toString() {
		return "Kauppa [kauppa_id=" + kauppa_id + ", kauppa=" + kauppa + ", katuosoite=" + katuosoite + ", postinumero="
				+ postinumero + ", kotisivu=" + kotisivu + "]";
	}
	
	
	public Kauppa(int kauppa_id, String kauppa, String katuosoite, String postinumero, String kotisivu) {
		super();
		this.kauppa_id = kauppa_id;
		this.kauppa = kauppa;
		this.katuosoite = katuosoite;
		this.postinumero = postinumero;
		this.kotisivu = kotisivu;
	}


	public Kauppa() {
		// TODO Auto-generated constructor stub
	}
	

}
