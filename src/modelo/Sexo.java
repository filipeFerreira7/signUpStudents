package modelo;

public enum Sexo {
	MASCULINO(1, "Masculino"), FEMININO(2, "Feminino");

	private int id;
	private String desc;

	Sexo(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	Sexo(){
		
	}

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

	public static Sexo valueOf(Integer id) {
		for (Sexo sexo : Sexo.values()) {
			if (sexo.getId() == id) {
				return sexo;
			}
		}
		return null;
	}
}
