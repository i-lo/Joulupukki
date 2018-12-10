package model;

public class Lahjatieto {
	private Kauppa kauppa;
	private Lahja lahja;
	
	
	public Lahjatieto(Kauppa kauppa, Lahja lahja) {
		super();
		this.kauppa = kauppa;
		this.lahja = lahja;
	}


	public Kauppa getKauppa() {
		return kauppa;
	}


	public void setKauppa(Kauppa kauppa) {
		this.kauppa = kauppa;
	}


	public Lahja getLahja() {
		return lahja;
	}


	public void setLahja(Lahja lahja) {
		this.lahja = lahja;
	}

	
}
