import java.util.*;
import java.io.*;

class Date{
	String dia;
	String mes;
	String ano;

	public Date() {}

	public Date(String line) {
		StringBuilder str = new StringBuilder();
		int cont = 0;
		String[] field = new String[3];
				
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);

			if (c != '/') str.append(c);
			else {
				field[cont++] = str.toString();
				str.setLength(0);
			}
		}		
		
		this.dia = field[0];
		this.mes = field[1];
		this.ano = field[2];
	}

	/*
	public String toString() {
		return System.out.printf("%d/%02d/%04d", dia, mes, ano);
	}
	*/
}

class Jogadores{
	public String nome;
	public String foto;
	public Date nascimento;
	public int id;
	public int[] times;

	public Jogadores() {}

	public Jogadores(int id, String nome, String foto, String nascimento) {
	this.nome = nome;
	this.foto = foto;
	Date tmpNascimento = new Date(nascimento);
	this.nascimento = tmpNascimento;
}

	public String getNome() {
		return this.nome;
	}

	public void imprimir() {
		System.out.print(this.id + " " + this.nome + " " + this.nascimento.dia + "/" +
			this.nascimento.mes + "/" + this.nascimento.ano + " " + this.foto + " " + "(");
	for(int i = 0; i < times.length - 1; i++) {
		System.out.print(times[i] + ", ");
	}
	System.out.println(times[times.length - 1] + ")");
}

public void ler(String input) {
	int i = 0;
	String index = separar(input, i, ',');
	i += index.length() + 1;
	this.nome = separar(input, i, ',');
	i += nome.length() + 1;
	this.foto = separar(input, i, ',');
	i += foto.length() + 1;
	this.nascimento = new Date();
	this.nascimento.dia = separar(input, i, '/');
	i += nascimento.dia.length() + 1;
	this.nascimento.mes = separar(input, i, '/');
	i += nascimento.mes.length() + 1;
	this.nascimento.ano = separar(input, i, ',');
	i += nascimento.ano.length() + 1;
	index = separar(input, i, ',');
	i += index.length() + 1;
	String idString = separar(input, i, ',');
	this.id = Integer.parseInt(idString);
	i += idString.length() + 1;
	int n = contarTimes(input, i);
	if(input.charAt(i) == '"') i+=2;
	else i++;
	this.times = new int[n];
	for(int j = 0; j < n - 1; j++) {
		String timeString = separar(input, i, ',');
		this.times[j] = Integer.parseInt(timeString);
		i += timeString.length() + 2;
	}
	
	String timeString = separar(input , i, ']');
	this.times[n - 1] = Integer.parseInt(timeString);
	// sc.next();
}

public String separar(String input, int i, char delimiter) {
	String stringer = "";
	while(input.charAt(i) != delimiter) {
		stringer += input.charAt(i);
		i++;
	}
	return stringer;
}

public int contarTimes(String input, int i) {
	int times = 1;
	while(input.charAt(i) != ']') {
		if(input.charAt(i) == ',') {
			times++;
		}
		i++;
	}
	return times;
}
}

public class Jogador {

	/*
	 * FUNÇÃO RECURSIVA
	*/
	public static boolean pesquisaBinaria(Jogadores[] j, String key, int esq, int dir) {
		boolean encontrou = false;
		int meio = (esq + dir) /2;

		if (esq <= dir) {
			if (key.equals(j[meio].getNome())) {
				encontrou = true;
			}
			else if (key.compareTo(j[meio].getNome()) < 0) {
				encontrou = pesquisaBinaria(j, key, meio+1, dir);
			} 
			else {
				encontrou = pesquisaBinaria(j, key, esq, meio-1);
			}
		}

		return encontrou;
	}

	/*
	 * FUNÇÃO BASE
	*/
	public static boolean pesquisaBinaria(Jogadores[] j, String key, int index) {
		return pesquisaBinaria(j, key, 0, index);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Jogadores[] jogadores = new Jogadores[40];
		int index = 0;

		String line = sc.nextLine();

		while (!line.equals("FIM")) {	
			Jogadores jogador = new Jogadores();
			jogador.ler(line);
			
			jogadores[index++] = jogador;	

		//131, Mattheus, https://tmssl.akamaized.net/images/portrait/header/226138
		}

		line = sc.nextLine();
		while (!line.equals("FIM")) {

			if(pesquisaBinaria(jogadores, line, index)) {
				System.out.println("SIM");
			}
			else { 
				System.out.println("NAO");
			}
			
			line = sc.nextLine();
		}


		sc.close();
	}
}
