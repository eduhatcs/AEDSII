// package ex1;

import java.util.Scanner;

class Date{
	private int dia;
	private int mes;
	private int ano;

	public Date(){
		this(0,0,0);
	}

	public Date(int dia, int mes, int ano){
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}

	public Date(String line){
		int len = line.length();
		StringBuilder sb = new StringBuilder();
		int[] infos = new int[3];
		int info = 0;
		for(int i = 0; i < len; i++){
			if(line.charAt(i) != '/'){
				sb.append(line.charAt(i));
			}else{
				infos[info++] = Integer.parseInt(sb.toString()); 
				sb = new StringBuilder();
			}
		}
		infos[info++] = Integer.parseInt(sb.toString()); 

		this.dia = infos[0];
		this.mes = infos[1];
		this.ano = infos[2];
	}


	public int getDia() { return this.dia; }
	public int getMes() { return this.mes; }
	public int getAno() { return this.ano; }

	public void setDia(int dia){ this.dia = dia; }
	public void setMes(int mes){ this.mes = mes; }
	public void setAno(int ano){ this.ano = ano; }

	public String toString(){
		return String.format("%d/%02d/%04d",dia,mes,ano);
	}
}

class Jogadores{
	private String nome;
	private String foto;
	private Date nascimento;
	private int id;
	private int[] times;

	public Jogadores(){
		this("Sem nome","Sem foto",new Date(), 0, new int[1]);
	}

	public Jogadores(String nome, String foto,Date nascimento,int id,int[] times){
		this.nome = nome;
		this.foto = foto;
		this.nascimento = nascimento;
		this.id = id;
		this.times = times;
	}

	public String getNome() { return this.nome; }
	public String getFoto() { return this.foto; }
	public Date getNascimento() { return this.nascimento; }
	public int getId() { return this.id; }
	public int[] getTimes() { return this.times; }

	public void setNome(String nome) { this.nome = nome; }
	public void setFoto(String foto) { this.foto = foto; }
	public void setNascimento(Date nascimento) { this.nascimento = nascimento; }
	public void setId(int id) { this.id = id; }
	public void setTimes(int[] times) { this.times = times; }

	public void imprimir(){
		System.out.println(id + " " + nome + " " + nascimento.toString() + " " + foto +  " " + timesToString());
	}

	public int[] strToIntArray(String line, int intLen){
		int len = line.length();
		int[] tmp = new int[intLen];
		int index = 0;

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < len; i++){
			if(line.charAt(i) != ',' && line.charAt(i) != ' '){
				sb.append(line.charAt(i));
			}else if(line.charAt(i) == ','){
				tmp[index++] = Integer.parseInt(sb.toString()); 
				sb = new StringBuilder();
			}
		}
		tmp[index] = Integer.parseInt(sb.toString());

		return tmp;
	}

	public String timesToString(){
		StringBuilder sb = new StringBuilder();
		int len = this.times.length;

		sb.append('(');

		for(int i = 0; i < len; i++){
			if(i != len - 1)
				sb.append(this.times[i] + ", ");
			else
				sb.append(this.times[i]);
		}

		sb.append(')');

		return sb.toString();
	}

	public void ler(String line){
		int len = line.length();
		StringBuilder sb = new StringBuilder();

		int virgulas = 0;
		for(int i = 0; i < len; i++){
			if(line.charAt(i) == ','){
				virgulas++;
				i++;
			}

			if(virgulas == 1){
				while(line.charAt(i) != ','){
					sb.append(line.charAt(i++));
				}
				this.nome = sb.toString();
				sb = new StringBuilder();
				virgulas++;
				i++;
			}
			if(virgulas == 2){
				while(line.charAt(i) != ','){
					sb.append(line.charAt(i++));
				}
				this.foto = sb.toString();
				sb = new StringBuilder();
				virgulas++;
				i++;
			}
			if(virgulas == 3){
				while(line.charAt(i) != ','){
					sb.append(line.charAt(i++));
				}
				this.nascimento = new Date(sb.toString());
				sb = new StringBuilder();
				virgulas++;
				i++;
			}
			if(virgulas == 5){
				while(line.charAt(i) != ','){
					sb.append(line.charAt(i++));
				}
				this.id = Integer.parseInt(sb.toString());
				sb = new StringBuilder();
				virgulas++;
				i+=2;
			}
			if(i < len && virgulas == 6){
				int index = 0;
				while(i < len && line.charAt(i) != '"'){
					if((line.charAt(i) >= '0' &&  line.charAt(i) <= '9') || line.charAt(i) == ','){
						sb.append(line.charAt(i));
						if(line.charAt(i) == ',')
							index++;
					}
					i++;
				}
				this.times = strToIntArray(sb.toString(),index + 1);
			}
		}
	}
}

public class Main{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();

		while(!line.equals("FIM")){
			Jogadores jogador = new Jogadores();

			jogador.ler(line);

			jogador.imprimir();

			line = sc.nextLine();
		}

		sc.close();
	}
}
