package jogoDeDama;

import java.util.Scanner;

public class Menu {

	public static void main(String[] args) {

		Impressao.imprimir("\n"
				+ "JOGO DA VELHA E JOGO DA DAMA "
				+ "\n"	
				+ "\nSalve! Informe seu nome piva:");

		Scanner sc = new Scanner(System.in);
		String nomeJogador = sc.nextLine();


		obterEscolhaJogo(nomeJogador);

	}

	public static void obterEscolhaJogo(String nomeJogador) {

		Scanner sc = new Scanner(System.in);

		int escolha;

		do {
			Impressao.imprimir("\n"
					+ "Qual jogo voc� quer jogar, " + nomeJogador + "?"
					+ "\n(1) Jogo da Velha;"
					+ "\n(2) Jogo de Damas;"
					+ "\n(3) Sair.");

			escolha = sc.nextInt();

			if (escolha != 1 && escolha != 2 && escolha != 3 && escolha != 4 && escolha != 5) {

				Impressao.imprimir("N�o temos essa op��o, tente novamente.");

			}

		} while (escolha != 1 && escolha != 2 && escolha != 3 && escolha != 4 && escolha != 5);

		//n�o estamos tratado caso a pessoa insira uma letra ao inv�s de um n�mero

		switch (escolha) {

		case 1: 
			Tabuleiro.main(null, nomeJogador);
			break;
		case 2: 
			Tabuleiro.main(null, nomeJogador);
			break;
		case 3:
			Impressao.imprimir("Fim do Jogo");
			break;
		default:
			Impressao.imprimir("Erro");

		}

	}

	public static void aindaQuerJogar(String nomeJogador) {

		int aindaQuerJogar;

		do {

			Impressao.imprimir("Voc� ainda quer jogar, " + nomeJogador + "?"
					+ "\n(1) Sim;"
					+ "\n(2) N�o;");

			Scanner sc = new Scanner (System.in);
			aindaQuerJogar = sc.nextInt();

		} while (aindaQuerJogar != 1 && aindaQuerJogar != 2);

		if (aindaQuerJogar == 1) {
			Menu.obterEscolhaJogo(nomeJogador);
		} else {
			Impressao.imprimir("Certo! Nos vemos de outra vez.");
		}
	}

}


