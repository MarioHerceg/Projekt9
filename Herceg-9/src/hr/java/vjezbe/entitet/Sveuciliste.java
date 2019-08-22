package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.util.List;

public class Sveuciliste<T extends ObrazovnaUstanova> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4812783479095600039L;
	private List<T> lista;

	public Sveuciliste(List<T> lista) {
		super();
		this.lista = lista;
	}
	
	public void dodajObrazovnuUstanovu(T dodaj) {
		lista.add(dodaj);
	}
	
	public T dohvatiObrazovnuUstanovu(Integer i) {
		return lista.get(i);
	}
	
	public List<T> dohvatiListu() {
		return lista;
	}

	

}
