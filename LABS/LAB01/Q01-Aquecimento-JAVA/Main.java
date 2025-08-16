import java.util.Scanner;

public class Main {
	public static void countThreeLastMsg(String str[], int pos) {
		for (int i = 0; i < 3; i++) {
			System.out.println("Frase '" + str[pos] + "' possui " + qtMaiusculo(str[pos]) + " letras maiúsculas!");
			pos = (pos + 1) % str.length;
		}
	}

	public static void showThreeLastMsg(String str[], int pos) {
		for (int i = 0; i < 3; i++) {
			System.out.printf("Frase %d - %s%n", i+1, str[pos]);
			pos = (pos + 1) % str.length;
		}
	}

	public static void showLastMsg(String str) {
		System.out.println("A última frase digitada foi: " + str);
	}

	public static void showMsg(String str, int qt) {
		System.out.println("A frase '" + str + "' possui " + qt + " letras maiúsculas!");
	}

	public static int qtMaiusculo(String str) {
		int qt = 0;

		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) >= 65 && str.charAt(i) <= 90) {
				qt++;
			}
		}

		return qt;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int opcao,
		    qtAttempts = 0,
		    qt = 0,
		    qtFrases = 0,
		    pos = 0,
		    lastPos = 0;
		String[] str = new String[3];
		boolean fim = false;

		System.out.println("Bem vindo ao programa! Iremos contar a quantidade de letras maíusculas da frase desejada!");
		while (!fim) {
			System.out.print("\n\tMENU: \n");
			System.out.println("\t0 - Sair");
			System.out.println("\t1 - Digitar frase");
			System.out.println("\t2 - Exibir última frase digitada");
			System.out.println("\t3 - Exibir últimas três frase digitadas");
			System.out.println("\t4 - Contar novamente última frase enviada");
			System.out.println("\t5 - Contar novamente últimas três frases enviadas");
			System.out.println("\t6 - Contar quantas vezes você escolheu alguma opção do programa");
			System.out.print("Sua opção: ");
			opcao = sc.nextInt();

			switch (opcao) {
				case 0: 
					fim = true;
					System.out.println("Obrigado por usar o programa!");
					break;
				case 1:
					System.out.print("Digite sua frase: ");
					sc.nextLine();
					str[pos] = sc.nextLine();
					qt = qtMaiusculo(str[pos]);
					showMsg(str[pos], qt);
					qtAttempts++;
					qtFrases++;
					lastPos = pos;
					pos = (pos + 1) % str.length;
					break;
				case 2: 
					showLastMsg(str[lastPos]);
					qtAttempts++;
					break;
				case 3: 
					if (qtFrases >= 3) {
						showThreeLastMsg(str, lastPos);
					} else {
						System.out.println("ERRO! Você enviou apenas " + qtFrases + " frases!");
					}
					qtAttempts++;
					break;
				case 4:
					qt = qtMaiusculo(str[lastPos]);
					showMsg(str[lastPos], qt);
					qtAttempts++;
					break;
				case 5:
					countThreeLastMsg(str, lastPos);
					qtAttempts++;
					break;
				case 6:
					System.out.println("Você utilizou as opções " + qtAttempts + " vezes!");
					qtAttempts++;
					break;
				default:
					System.out.println("ERRO! Opção digitada fora do padrão. Tente novamente.");
					break;
			} 
		}

		sc.close();
	}
}
