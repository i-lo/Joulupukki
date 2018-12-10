package model;

import java.util.List;

public class SaajanLahja {
	private int lahjansaada_id;
	private String etunimi, sukunimi;
	private List<Lahjatieto> lahjat;



	
	
	public SaajanLahja(int lahjansaada_id, String etunimi, String sukunimi, List<Lahjatieto> lahjat) {
		super();
		this.lahjansaada_id = lahjansaada_id;
		this.etunimi = etunimi;
		this.sukunimi = sukunimi;
		this.lahjat = lahjat;
	}

	


	public SaajanLahja(int lahjansaada_id, List<Lahjatieto> lahjat) {
		super();
		this.lahjansaada_id = lahjansaada_id;
		this.lahjat = lahjat;
	}








	public int getLahjansaada_id() {
		return lahjansaada_id;
	}



	public void setLahjansaada_id(int lahjansaada_id) {
		this.lahjansaada_id = lahjansaada_id;
	}



	public String getSukunimi() {
		return sukunimi;
	}



	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}



	public String getEtunimi() {
		return etunimi;
	}

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	public List<Lahjatieto> getLahjat() {
		return lahjat;
	}

	public void setLahjat(List<Lahjatieto> lahjat) {
		this.lahjat = lahjat;
	}

}
