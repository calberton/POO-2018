package Contato;


import java.util.ArrayList;
import java.util.Scanner;

class Contato {
	String nome;
	ArrayList<Telefone> fones;
	
	public Contato(String nome) {
		this.nome = nome;
		this.fones = new ArrayList<Telefone>();
	}
	
	public boolean addFone(Telefone t) throws Exception {
		for (Telefone fone : this.fones) {
			if (fone.getFoneId().equals(t.idFone)) {
				throw new Exception("Telefone existente");
			}
		}
		
		return this.fones.add(t);
	}
	
	public boolean rmFone(String foneId) throws Exception{
		for (Telefone tele : fones) {
			if (tele.getFoneId().equals(foneId)) {
				return this.fones.remove(tele);
			}
		}
		throw new Exception("Telefone nao existe");
	}
	
	public String toString() {
		return " " + this.nome + " " + this.fones;
	}
}

class Telefone{
	String idFone;
	int numero;
	
	public Telefone(String foneId, int numero) {
		this.idFone = foneId;
		this.numero = numero;
	}
	
	public String getFoneId() {
		return idFone;
	}
	
	public String toString() {
		return "" + this.idFone + ":" + this.numero;
	}
	
}
class Controller{
	Contato c;
	
	public Controller() {
		c = new Contato("");
	}

    //nossa funcao oraculo que recebe uma pergunta e retorna uma resposta
    public String oracle(String line) throws Exception{
        String ui[] = line.split(" ");

        if(ui[0].equals("help")){
            return "init_nome ; addfome_operatora_numero ; rmfome_operatora ; show";
        }else if(ui[0].equals("init")){
        	c = new Contato(ui[1]);
        }else if(ui[0].equals("show")){
        	return "" + c;
        }else if(ui[0].equals("addfone")){
        	c.addFone(new Telefone(ui[1],Integer.parseInt(ui[2])));
        }else if(ui[0].equals("rmfone")){
        	c.rmFone(ui[1]);
        
        }else{
            return "comando invalido";
        }
        return "done";
    }
}

public class UI {
    //cria um objeto scan para ler strings do teclado
    static Scanner scan = new Scanner(System.in);
    
    //aplica um tab e retorna o texto tabulado com dois espaços
    static private String tab(String text){
        return "  " + String.join("\n  ", text.split("\n"));
    }
    
    public static void main(String[] args) {
        Controller cont = new Controller();
        System.out.println("Digite um comando ou help:");
        while(true){
            String line = scan.nextLine();
            try {
                //se não der problema, faz a pergunta e mostra a resposta
                System.out.println(tab(cont.oracle(line)));
            }catch(Exception e) {
                //se der problema, mostre o erro que deu
                System.out.println(tab(e.getMessage()));
            }
        }
    }
}
