package jogoDeDama;

import java.util.Scanner;

public class Tabuleiro {

		public static void main(Object[] args, String nomeJogador) {

			Impressao.imprimir("\nJOGO DE DAMAS");

			String [][] tabuleiroInicial = tabuleiro();

			Impressao.tabDamas(tabuleiroInicial);

			jogoON(tabuleiroInicial);

			Menu.obterEscolhaJogo(nomeJogador);

		}

		public static String [] [] tabuleiro() {

			int l = 9, c = 9;

			String [] [] tabuleiro = new String [l] [c];

			for (int il = 0; il < tabuleiro.length - 1; il++) {
				for (int ic = 0; ic < tabuleiro[il].length - 1; ic++) {

					tabuleiro [il] [ic] = "|_|";

					if (il == 0) {
						if (ic % 2 == 0) {
							tabuleiro[il][ic] = "|P|";
						} 
					} else if (il == 1) {
						if (ic % 2 != 0) {
							tabuleiro[il][ic] = "|P|";
						} 
					} else if (il == 2) {
						if (ic % 2 == 0) {
							tabuleiro[il][ic] = "|P|";
						} 
					}

					if (il == 5) {
						if (ic % 2 != 0) {
							tabuleiro[il][ic] = "|B|";
						} 
					} else if (il == 6) {
						if (ic % 2 == 0) {
							tabuleiro[il][ic] = "|B|";
						} 
					} else if (il == 7 ) {
						if (ic % 2 != 0) {
							tabuleiro[il][ic] = "|B|";
						} 
					}

				}

				int numeroCasa= 0;

				while(numeroCasa < 8) {

					tabuleiro[8][numeroCasa] = "|" + numeroCasa + "|";
					tabuleiro[numeroCasa][8] = "|" + numeroCasa + "|";
					numeroCasa++;
				}
			}

			tabuleiro[8][8] = "|*|";

			return tabuleiro;

		}

		public static String [][] jogoON(String [][] tabuleiro) {

			String corPreto = "P", corBranco = "B";
			String pecaPreto = "|P|", pecaBranco = "|B|", damaPreto = "|DP|", damaBranco = "|DB|";
			int rodada = 1, ptsBranco = 0, ptsPreto = 0;

			do { 

				tabuleiro = jogada(tabuleiro, corBranco, pecaBranco, damaBranco, rodada, ptsBranco, ptsPreto);
				Impressao.tabDamas(tabuleiro);
				ptsBranco = pontosBranco(pecaPreto, tabuleiro, ptsBranco);

				tabuleiro = jogada(tabuleiro, corPreto, pecaPreto, damaPreto, rodada, ptsBranco, ptsPreto);
				Impressao.tabDamas(tabuleiro);
				ptsPreto = pontosPreto(pecaBranco, tabuleiro, ptsPreto);	
				rodada++;

			} while (rodada < 50);

			//(vitoria == false && empate == false);
			//criar metodo para testar vitoria e metodo para testar empate

			return tabuleiro;
		}

		public static String [][] jogada (String [][] tabuleiro, String corPeca, String tipoPeca, String tipoDama, int rodada, int ptsBranco, int ptsPreto) {

			int colunaSeguinte, linhaSeguinte, colunaOrigem, linhaOrigem;
			boolean validar = false;
			String pecaPreta = "P", pecaBranca = "B";

			Scanner sc = new Scanner(System.in);

			do { Impressao.imprimir("\n"
					+ "Rodada " + rodada + " - Jogada de " + corPeca + ":"
					+ "\nPts. " + pecaBranca +  " = " + ptsBranco + " | Pts. " + pecaPreta + " = " + ptsPreto
					+"\n"
					+ "\nQual a linha da peça que deseja mover? (0 a 7)");

			linhaOrigem = sc.nextInt();

			Impressao.imprimir("Qual a coluna da peça que deseja mover? (0 a 7)");

			colunaOrigem = sc.nextInt();

			} while (tabuleiro[linhaOrigem][colunaOrigem] != tipoPeca);

			//verificando se o local de origem tem peca

			do {

				Impressao.imprimir("\nPara qual linha deseja mover essa peça? (0 a 7)");

				linhaSeguinte = sc.nextInt();

				Impressao.imprimir("Para qual coluna deseja mover essa peça? (0 a 7)");

				colunaSeguinte = sc.nextInt();

				validar = validarJogada(linhaOrigem, linhaSeguinte, colunaOrigem, colunaSeguinte, tabuleiro, tipoPeca, tipoDama, ptsBranco, ptsPreto);

				//verificando se é possível mover a peça pra o local desejado

			} while (validar == false);

			return tabuleiro;
		}

		public static boolean validarJogada(int linhaOrigem, int linhaSeguinte, int colunaOrigem, int colunaSeguinte, String[][] tabuleiro, String tipoPeca, String tipoDama, int ptsBranco, int ptsPreto) {

			boolean validar = false;
			int subLinha = linhaSeguinte - linhaOrigem, subCol = colunaSeguinte - colunaOrigem, linhaSegComeu = 0, colSegComeu = 0;

			String pecaPreta = "|P|", pecaBranca = "|B|", damaPreto = "|DP|", damaBranco = "|DB|", vazio = "|_|", pecaOposta = "|_|", linhaIntermed, colunaIntermed;

			if (tipoPeca == pecaPreta) {
				pecaOposta = pecaBranca;
				tipoDama = damaPreto;
			} else if (tipoPeca == pecaBranca) {
				pecaOposta = pecaPreta;
				tipoDama = damaBranco;
			}

			if (subLinha == 2) {
				linhaSegComeu = 1;
			} else if (subLinha == -2) {
				linhaSegComeu = -1;
			}

			if (subCol == 2) {
				colSegComeu = 1;
			} else if (subCol == -2) {
				colSegComeu = -1;

			}

			if (tabuleiro[linhaSeguinte][colunaSeguinte] == vazio) {

				if ((linhaSeguinte == linhaOrigem + 1 || linhaSeguinte == linhaOrigem - 1) && colunaSeguinte == colunaOrigem + 1 || colunaSeguinte == colunaOrigem - 1) {
					
					tabuleiro[linhaSeguinte][colunaSeguinte] = tipoPeca;
					
					if(linhaSeguinte == 0 && tipoPeca == pecaBranca) {
						tabuleiro[linhaSeguinte][colunaSeguinte] = damaBranco;
					} 
					
					if (linhaSeguinte == 7 && tipoPeca == pecaPreta) {
						tabuleiro[linhaSeguinte][colunaSeguinte] = damaPreto;
					}
					
					//preenchendo com a peça a linha e coluna seguinte
					tabuleiro[linhaOrigem][colunaOrigem] = vazio;
					//apagando onde a peça estava anteriormente

					validar = true;
					
				} else {	
						
						if ((tabuleiro[linhaOrigem+linhaSegComeu][colunaOrigem+colSegComeu] == pecaOposta) && (linhaSeguinte == linhaOrigem+subLinha) && (colunaSeguinte == colunaOrigem+subCol)) {
						
						validar = true;
						
						tabuleiro[linhaOrigem+linhaSegComeu][colunaOrigem+colSegComeu] = vazio;
						//apagando a peça comida
						tabuleiro[linhaOrigem][colunaOrigem] = vazio;
						//apagando onde a peça estava anteriormente
						tabuleiro[linhaSeguinte][colunaSeguinte] = tipoPeca;
						//preenchendo com a peça a linha e coluna seguintes
						if(linhaSeguinte == 0 && tipoPeca == pecaBranca) {
							tabuleiro[linhaSeguinte][colunaSeguinte] = damaBranco;
						} 
						
						if (linhaSeguinte == 7 && tipoPeca == pecaPreta) {
							tabuleiro[linhaSeguinte][colunaSeguinte] = damaPreto;
						}
						
						if(tipoPeca == pecaPreta) {
							ptsPreto++;
						} else if (tipoPeca == pecaBranca) {
							ptsBranco++;
						}

						} 
					}

				}

			return validar;

		}
		
		public static int pontosBranco (String pecaPreto, String [][] tabuleiro, int ptsBranco) {
			
			ptsBranco = 12;
			
			for (int i = 0; i < tabuleiro.length; i++) {
				for (int j = 0; j < tabuleiro[i].length; j++) {
					
					if(tabuleiro[i][j] == pecaPreto) {
						ptsBranco--;
					}
					
				}
			}
			
			return ptsBranco;
			
		}
		
	public static int pontosPreto (String pecaBranco, String [][] tabuleiro, int ptsPreto) {
			
			ptsPreto = 12;

			for (int i = 0; i < tabuleiro.length; i++) {
				for (int j = 0; j < tabuleiro[i].length; j++) {
					
					if(tabuleiro[i][j] == pecaBranco) {
						ptsPreto--;
					}		
				}
			}
			return ptsPreto;	
		}

	public static void main(Object args, String nomeJogador) {
		Impressao.imprimir("\nJOGO DE DAMAS");

		String [][] tabuleiroInicial = tabuleiro();

		Impressao.tabDamas(tabuleiroInicial);

		jogoON(tabuleiroInicial);

		Menu.obterEscolhaJogo(nomeJogador);

		
	}


		/*
		 *DAMA: ◉  ◎
		 *
		 * 1. Repetir método jogadaPreto para jogadaBranco - DONE;
		 * 2. Criar lógica de validação de jogada: só é válida se for na diagonal (linha +1 ou -1 && coluna +1 ou -1) - DONE;
		 * 2.a. Escanear se existe pedra na jogada validada e não existe na jogada validada +1 ou -1) - DONE;
		 * 3. Criar lógica para "comer pedra" (a pedra contrária +1 ou -1) e acumular pontos (Branco++, Preto++) - DONE;
		 * 3. Criar lógica de promoção à dama (se for preta e chegar a ultima linha ou se for branca e chegar a primeira linha)
		 * 4. Criar lógica de vitória: escanear o tabuleiro para verificar se ainda existe |○| ou |●| - IN PROGRESS;
		 * 4.1. Regras de empate (docs)
		 * 5. Criar movimento especial da dama (várias pedras)
		 */
	}