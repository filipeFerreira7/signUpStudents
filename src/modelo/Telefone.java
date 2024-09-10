package modelo;

import java.util.Objects;

public class Telefone {
	private int dd;
	private String num;

	public Telefone(int dd, String num) {
		super();
		this.dd = dd;
		this.num = num;
	}

	public Telefone() {

	}

	public int getDd() {
		return dd;
	}

	public void setDd(int dd) {
		this.dd = dd;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dd, num);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		return dd == other.dd && Objects.equals(num, other.num);
	}

	@Override
	public String toString() {
		return "dd" + dd + ", num=" + num;
	}

}
