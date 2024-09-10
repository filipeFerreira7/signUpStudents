package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

import app.Main;

public class Aluno extends Pessoa implements Impressao, Comparator<Aluno> {
	private int matricula;
	private Turno turno;

	public Aluno(String nome, LocalDate dataNascimento, String cpf, Sexo sexo, Telefone tel, int matricula,
			Turno turno) {
		super(nome, dataNascimento, cpf, sexo, tel);
		this.matricula = matricula;
		this.turno = turno;
	}

	public Aluno() {

	}

	public static Aluno lerDados() {
		Aluno a = new Aluno();
		Scanner sc = new Scanner(System.in);
		System.out.println("Insira seu nome: ");
		a.setNome(sc.nextLine());
		System.out.println("Insira sua data de nascimento: (dia-Mes-ano) ");
		String dataNascimento = sc.nextLine();
		a.setDataNascimento(LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		System.out.println("Insira seu cpf: ");
		a.setCpf(sc.nextLine());
		System.out.println("Informe o seu sexo: ");
		for (Sexo sexo : Sexo.values()) {
			System.out.println("ID: " + sexo.getId() + "| Nome: " + sexo.getDesc());
		}

		int id = -1;
		while (id < 1 || id > 2) {
			System.out.println("Digite o id: ");
			id = sc.nextInt();
			if (id < 1 || id > 2) {
				System.out.println("Comando inválido, tente novamente");
			}
		}

		a.setSexo(Sexo.valueOf(id));

		System.out.println("Insira o seu dd: ");
		int dd = sc.nextInt();
		sc.nextLine();
		System.out.println("Insira seu numero: ");
		String num = sc.nextLine();

		Telefone tel = new Telefone(dd, num);

		a.setTel(tel);

		System.out.println("Insira sua matricula: ");
		a.setMatricula(sc.nextInt());
		System.out.println("Informe o seu turno: ");
		for (Turno turno : Turno.values()) {
			System.out.println("ID: " + turno.getId() + "| Nome: " + turno.getDesc());
		}
		int idMat = -1;
		while (idMat < 1 || idMat > 2) {
			System.out.println("Digite o id do seu turno: ");
			idMat = sc.nextInt();
			if (idMat < 1 || idMat > 2) {
				System.out.println("Comando inválido, tente novamente");
			}
		}

		a.setTurno(Turno.valueOf(idMat));

		return a;

	}

	public static void imprimirLista(List<Aluno> lista) {
		lista.forEach(p -> System.out.println(p));
		continuarOperacao(lista);
	}

	public static void continuarOperacao(List<Aluno> lista) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Deseja fazer mais alguma operação? 1(SIM), CASO NAO,"
				+ "\nVOLTE PARA O MENU E DIGITE (7) ");
		int opc = sc.nextInt();
		if (opc == 1) {
			Main.menuInterativo(lista);
		} else {
			System.out.println("Comando inválido. Programa finalizado.");
		}
	}

	public static void imprimirLista(List<Aluno> lista, Sexo sexo) {
		lista.stream()
		.filter(pessoa -> pessoa.getSexo() == sexo)
		.forEach(p -> System.out.println(p));
		continuarOperacao(lista);
	}

	public static void imprimirLista(List<Aluno> lista, Turno turno) {
		lista.stream().
		filter(pessoa -> pessoa.getTurno() == turno)
		.forEach(p -> System.out.println(p));
		continuarOperacao(lista);
	}

	public static void imprimirListaPorNome(List<Aluno> lista) {
		lista.sort((o1,o2)-> o1.getNome().compareTo(o2.getNome()));
		imprimirLista(lista);
		continuarOperacao(lista);
	}

	public static void imprimirListaOrdenadaPorIdade(List<Aluno> lista) {
		lista.sort(new Comparator<Pessoa>() {

			@Override
			public int compare(Pessoa o1, Pessoa o2) {
				if (o1.getDataNascimento() == null || o2.getDataNascimento() == null)
					return 0;
				return o1.getDataNascimento().compareTo(o2.getDataNascimento());
			}
		});
		imprimirLista(lista);
		continuarOperacao(lista);
	}

	public static void imprimirListaOrdenadaPorSexo(List<Aluno> lista) {
		lista.sort((o1, o2) -> o1.getSexo().compareTo(o2.getSexo()));
		imprimirLista(lista);
		continuarOperacao(lista);
	}

	public static void buscaPeloCpf(List<Aluno> lista) {
		Map<String, Aluno> mapCpf = lista.stream().
				collect(Collectors.toMap(a -> a.getCpf(), aluno -> aluno));

		Scanner sc = new Scanner(System.in);
		System.out.println("Insira o CPF para busca: ");
		String cpf = sc.nextLine();

		Aluno aluno = mapCpf.get(cpf);
		if (aluno != null) {
			System.out.println(aluno);
		} else {
			System.out.println("Aluno não encontrado.");
		}
		continuarOperacao(lista);
	}

	public static void buscaPeloSexo(List<Aluno> lista) {
		Map<Sexo, List<Aluno>> mapSexo = lista.stream()
				.collect(Collectors.groupingBy(a -> a.getSexo()));

		Scanner sc = new Scanner(System.in);
		System.out.println("Insira o sexo para busca: \n1 - Masculino\n2 - Feminino");
		int id = sc.nextInt();

		Sexo sexo = Sexo.valueOf(id);
		List<Aluno> alunos = mapSexo.get(sexo);
		if (alunos != null && !alunos.isEmpty()) {
			alunos.forEach( p->System.out.println(p));
		} else {
			System.out.println("Nenhum aluno encontrado para o sexo selecionado.");
		}
		continuarOperacao(lista);
	}

	public static void buscaPeloTurno(List<Aluno> lista) {
		Map<Turno, List<Aluno>> mapTurno = lista.stream().
				collect(Collectors.groupingBy(a -> a.getTurno()));

		Scanner sc = new Scanner(System.in);
		System.out.println("Insira o turno para busca: \n1 - Matutino\n2 - Noturno");
		int id = sc.nextInt();

		Turno turno = Turno.valueOf(id);
		List<Aluno> alunos = mapTurno.get(turno);
		if (alunos != null && !alunos.isEmpty()) {
			alunos.forEach(a -> System.out.println(a));
		} else {
			System.out.println("Nenhum aluno encontrado para o turno selecionado.");
		}
		continuarOperacao(lista);
	}

	public static void removerAluno(List<Aluno> lista) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Selecione o usuário a ser removido: ");
		lista.forEach(p -> System.out.println(p));
		int escolha = sc.nextInt();
		if (escolha > 0 || escolha < lista.size()) {
			lista.remove(escolha - 1);
			System.out.println("Usuario removido com sucesso!");
			imprimirLista(lista);
		} else {
			System.out.println("Comando inválido, tente novamente.");
		}
		continuarOperacao(lista);
	}

	public static void adicionarUsuario(List<Aluno> lista) {
		System.out.println("Adicionando usuario...");
		lista.add(lerDados());
		System.out.println("Usuário adicionado com sucesso!");
		imprimirLista(lista);

		continuarOperacao(lista);
	}

	public static void atualizarUsuario(List<Aluno> lista) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Selecione o usuario para ser atualizado: pelo index ");
		lista.forEach(p -> System.out.println(p));
		int escolha = sc.nextInt();
		escolha += -1;

		if (escolha < 0 || escolha >= lista.size()) {
			System.out.println("Comando invalido!");
			return;
		}

		Aluno a = lista.get(escolha);

		System.out.println("Informe o dado a ser atualizado \n 1 - NomeUsuario \n 2 - Telefone \n 3- Turno");
		int resposta = sc.nextInt();
		sc.nextLine();
		if (escolha < 0 || escolha > lista.size()) {
			System.out.println("Comando invalido!");
			return;
		}
		switch (resposta) {
		case 1:
			System.out.println("Informe o novo nome a ser substituido");
			String novoNome = sc.nextLine();
			a.setNome(novoNome);
			System.out.println("Registro feito com sucesso!");
			imprimirLista(lista);
			break;

		case 2:
			System.out.println("Informe o novo dd:");
			int novoDd = sc.nextInt();
			sc.nextLine();
			System.out.println("Informe o novo numero: ");
			String novoTelefone = sc.nextLine();
			Telefone tel = new Telefone(novoDd, novoTelefone);
			tel.setDd(novoDd);
			tel.setNum(novoTelefone);
			a.setTel(tel);
			System.out.println("Registro feito com sucesso!");
			imprimirLista(lista);
			break;
		case 3:
			System.out.println("Informe o id do novo turno: ");
			for (Turno turno : Turno.values()) {
				System.out.println("ID: " + turno.getId() + "| Nome: " + turno.getDesc());
			}
			int novoTurno = -1;
			while (novoTurno < 1 || novoTurno > 2) {
				System.out.println("Digite o id do seu novo turno: ");
				novoTurno = sc.nextInt();
				if (novoTurno < 1 || novoTurno> 2) {
					System.out.println("Comando inválido, tente novamente");
				}
			}
			a.setTurno(Turno.valueOf(novoTurno));
			System.out.println("Turno atualizado com sucesso!");
			break;
		default:
			System.out.println("Opcão inválida!");
			break;

		}

		continuarOperacao(lista);

	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	@Override
	public String toString() {
		return "Aluno(a): [" + super.toString() + "\n [matricula=" + matricula + ", turno=" + turno + "]" + "\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula, turno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return matricula == other.matricula && Objects.equals(turno, other.turno);
	}

	@Override
	public void imprimir() {
		System.out.println(toString());

	}

	@Override
	public int compare(Aluno o1, Aluno o2) {
		return o1.getDataNascimento().compareTo(o2.getDataNascimento());

	}

}
