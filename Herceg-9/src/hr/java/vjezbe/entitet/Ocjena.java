package hr.java.vjezbe.entitet;

public enum Ocjena {
	izvrstan(5),
	vrlo_dobar(4),
	dobar(3),
	dovoljan(2),
	nedovoljan(1),
	neutral(0);
	
	private Integer ocjena;

	private Ocjena(Integer ocjena) {
		this.setOcjena(ocjena);
	}

	public Integer getOcjena() {
		return ocjena;
	}

	public void setOcjena(Integer ocjena) {
		this.ocjena = ocjena;
	}
	
}
