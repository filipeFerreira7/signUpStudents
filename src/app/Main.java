package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Aluno;
import modelo.Sexo;
import modelo.Turno;

public class Main {
	public static void main(String[] args) {
		List<Aluno> lista = new ArrayList<Aluno>();
		menuInterativo(lista);

	}

	public static void menuInterativo(List<Aluno> lista) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Bem vindo ao menu interativo! escolha algumas das funções: " + "\n 1 - Cadastrar alunos"
				+ "\n 2 - Listar alunos" + "\n 3 - Atualizar dados de aluno" + "\n 4- adicionar aluno"
				+ "\n 5 - remover aluno" + "\n 6 - buscar aluno" + "\n 7- sair");

		int opcao = sc.nextInt();

		switch (opcao) {
		case 1:
			System.out.println("Quantos alunos você deseja cadastrar? ");
			int aux = sc.nextInt();
			for (int i = 0; i < aux; i++) {
				lista.add(Aluno.lerDados());
			}
			System.out.println("Aluno cadastrado com sucesso!");

			System.out.println("Deseja fazer mais alguma operação? 1(SIM) 2(NAO)");
			int opc = sc.nextInt();
			if (opc == 1) {
				Main.menuInterativo(lista);
			} else if (opc == 2) {
				System.out.println("Programa finalizado.");
			} else {
				System.out.println("Comando inválido. Programa finalizado.");
			}
			break;
		case 2:
			System.out.println("Escolha o filtro de busca: \n 1 - Listar alunos do sexo masculino"
					+ "\n 2 - Listar alunos do sexo feminino" + "\n 3 - Listar alunos que estudam pela manhã"
					+ "\n 4 - Listar alunos que estudam pela noite" + "\n 5- Listar alunos em ordem alfabética"
					+ "\n 6- Listar alunos ordenado pela idade" + "\n 7- Listar alunos ordenado pela sexo"
					+ "\n 8 - Listar todos os alunos ");
			int resposta = sc.nextInt();

			switch (resposta) {
			case 1:
				System.out.println("Apenas do sexo MASCULINO: ");
				Aluno.imprimirLista(lista, Sexo.MASCULINO);

				break;
			case 2:
				System.out.println("Apenas do sexo FEMININO");
				Aluno.imprimirLista(lista, Sexo.FEMININO);
				break;
			case 3:
				System.out.println("Apenas quem estuda pela manhã");
				Aluno.imprimirLista(lista, Turno.MATUTINO);
				break;
			case 4:
				System.out.println("Apenas quem estuda pela noite");
				Aluno.imprimirLista(lista, Turno.NOTURNO);
				break;
			case 5:
				System.out.println("Lista ordenada por nome");
				Aluno.imprimirListaPorNome(lista);
				break;
			case 6:
				System.out.println("Lista ordenada por idade: ");
				Aluno.imprimirListaOrdenadaPorIdade(lista);
				break;
			case 7:
				System.out.println("Lista ordenada por sexo: ");
				Aluno.imprimirListaOrdenadaPorSexo(lista);
				break;
			case 8:
				System.out.println("Todos os alunos: ");
				Aluno.imprimirLista(lista);
				break;

			default:
				System.out.println("Comando inválido.");
				break;
			}

			break;

		case 3:
			Aluno.atualizarUsuario(lista);
			break;
		case 4:
			Aluno.adicionarUsuario(lista);
			break;
		case 5:
			Aluno.removerAluno(lista);
			break;
		case 6:
			System.out.println("Informe o tipo de busca: " + "\n 1 - buscar pelo cpf" + "\n 2 - buscar pelo sexo"
					+ "\n 3 - buscar pelo turno");
			int option = sc.nextInt();

			switch (option) {
			case 1:
				Aluno.buscaPeloCpf(lista);
				break;
			case 2:
				Aluno.buscaPeloSexo(lista);
				break;
			case 3:
				Aluno.buscaPeloTurno(lista);
				break;
			}
		case 7:
			System.out.println("Sistema finalizado.");
			break;

		default:
			System.out.println("Comando inválido");
			break;
		}

	}

}
