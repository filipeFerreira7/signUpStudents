package modelo;

public enum Turno {
	MATUTINO(1, "Matutino"), NOTURNO(2, "Noturno");

	Turno(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	Turno(){
		
	}

	private int id;
	private String desc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static Turno valueOf(Integer id) {
		for (Turno turno : Turno.values()) {
			if (turno.getId() == id) {
				return turno;
			}
		}
		return null;
	}

}
