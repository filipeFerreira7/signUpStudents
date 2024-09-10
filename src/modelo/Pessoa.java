package modelo;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Pessoa {
	private String nome;
	private LocalDate dataNascimento;
	private String cpf;
	private Sexo sexo;
	private Telefone tel;

	public Pessoa(String nome, LocalDate dataNascimento, String cpf, Sexo sexo, Telefone tel) {
		super();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
		this.sexo = sexo;
		this.tel = tel;
	}
	public Pessoa() {
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Telefone getTel() {
		return tel;
	}

	public void setTel(Telefone tel) {
		this.tel = tel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, dataNascimento, nome, sexo, tel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(dataNascimento, other.dataNascimento)
				&& Objects.equals(nome, other.nome) && sexo == other.sexo && Objects.equals(tel, other.tel);
	}

	@Override
	public String toString() {
		return nome + ", dataNascimento=" + dataNascimento + "\n cpf=" + cpf + ", sexo=" + sexo
				+ ", tel=" + tel;
	}

}
