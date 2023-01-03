package jogoDeDama;

public class Impressao {

	public static void imprimir (double dbl) {
		System.out.printf("%.2f", dbl);
	}

	public static void imprimir (int vlr) {
		System.out.println(vlr);
	}


	public static void imprimir (String [] vetor) {
		System.out.print(vetor);
	}


	public static void tab(String[][] tabuleiro) {

		for (int i = 0; i < tabuleiro.length; i++) {

			for (int j = 0; j < tabuleiro[i].length; j++) {
				System.out.print(tabuleiro[i][j] +	" ");
			}
			System.out.println();

		}

	}

	public static void tabDamas(String[][] tabuleiro) {

		System.out.println("");

		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				System.out.print(tabuleiro[i][j] + "");}
			System.out.println(" ");
		}

	}


	public static String preencherEspacoStr(String str, int qtd){

		String res = "";
		int tam = qtd - (str + "").length();

		for (int i = 0; i < tam; i++) {
			res+=" ";
		}

		return res+str;

	}

	//usar %5d para jogo da velha e damas

	public static String preencherZero (int valor, int qtd){

		String res = "";
		int tam = qtd - (valor + "").length();

		for (int i = 0; i < tam; i++) {
			res+="0";
		}

		return res+valor;
	}

	public static void imprimir(String str) {
		System.out.println(str);
		
	}
}