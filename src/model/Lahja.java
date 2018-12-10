package model;

public class Lahja {
	private int lahja_id, hinta, ostettu, paketoitu;
	private String tuotenimi;
	
	
	public int getLahja_id() {
		return lahja_id;
	}
	
	
	public int getOstettu() {
		return ostettu;
	}


	public void setOstettu(int ostettu) {
		this.ostettu = ostettu;
	}


	public int getPaketoitu() {
		return paketoitu;
	}


	public void setPaketoitu(int paketoitu) {
		this.paketoitu = paketoitu;
	}


	public void setLahja_id(int lahja_id) {
		this.lahja_id = lahja_id;
	}
	
	
	public int getHinta() {
		return hinta;
	}
	
	
	public void setHinta(int hinta) {
		this.hinta = hinta;
	}
	
	

	
	public String getTuotenimi() {
		return tuotenimi;
	}
	
	
	public void setTuotenimi(String tuotenimi) {
		this.tuotenimi = tuotenimi;
	}


	@Override
	public String toString() {
		return "Lahja [lahja_id=" + lahja_id + ", hinta=" + hinta + " tuotenimi="
				+ tuotenimi + "]";
	}


	public Lahja(int lahja_id, int hinta,  String tuotenimi) {
		super();
		this.lahja_id = lahja_id;
		this.hinta = hinta;
		this.tuotenimi = tuotenimi;
	}
	
	
	public Lahja() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
