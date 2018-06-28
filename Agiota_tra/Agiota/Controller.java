package Agiota;

import java.util.ArrayList;
import java.util.Scanner;

//criando a classe cliente
class Cliente {
		//criando objetos da classe cliente
		public String id;
		public String nome;
		public String estado = "vivo";
		public float saldo = 0;
		//criando construtor de cliente
		public Cliente(String clienteid, String nome) {
			this.id = clienteid;
			this.nome =  nome;
		}
		//interação com usuario
		public String toString() {
			return " "+nome +":"+ id+ ":" +estado + "\n";
		}
		
		public String mostrarSaldoClientes() {
			return " nome:" + nome +" saldo : " + saldo + "\n";
		}
}
// criando classe divida
 class Divida {
	 	//criando objetos da classe divida 
		public String nome;
		public float valorDivida;
		public int id = 0;
		public float valorTotal = 0;
		//criando construtor da classe divida
		public Divida(String nome, float valor, int id) {
			this.nome = nome;
			this.valorDivida = valor;
			this.id = id;
			this.valorTotal = valorDivida + valorTotal;
		}
		//interação com usuario
		public String toString() {
			return  " id:" + id + " nome:" + nome + " valor:" + valorDivida +"\n";
		}
}
//criando classe sistema 
 class Sistema {	
			// criando objetos da classe sistema
		public float saldo = 0;
		public float dinheiro;
		public int idTransacao = 0;
		public int id = 0;
		// criando lista de clientes do tipo Cliente e criando lista de dividas do tipo Divida
		ArrayList<Cliente> clientes;
		ArrayList<Divida> dividas;
		//criando construtor de sistema
		public Sistema(float dinheiro) {
			this.dinheiro = dinheiro;
			clientes = new ArrayList<Cliente>();
			dividas =  new ArrayList<Divida>();
			saldo = saldo + dinheiro;
		}
	    //interação com usuario
		public String toString() {
			return "Sistema iniciado com : " + saldo + " " + clientes.toString() + "\n";
		}
		//metodo que add cliente se a pessoa n for ja cadastrada
		public void cadastrarClientes(String nome, String clienteid) {
			for (Cliente c1 : clientes)
				if (c1.nome.equals(nome))
					throw new RuntimeException("Pessoa já cadastrada");
	
			clientes.add(new Cliente(clienteid, nome));
		}
	    //metodo quen empreta dinheirose pessoa estiver cadastrada no sistema e ese o slado n for um valor invalido
		public void emprestarDinheiro(String nome, float valor) {
			for (Cliente c1 : clientes)
				if (c1.nome.equals(nome) ) { 
					c1.saldo = valor + c1.saldo;
					this.cadastrarDivida(nome, valor);
			        return;
		        }
			
			throw new RuntimeException("Pessoa ou saldo invalido");
		}
		//metodo que cadastra divida se o saldo n for inferior ao valor pedido
		public void cadastrarDivida(String nome, float valor) {
		   if(valor <= saldo) {
			  saldo = saldo + valor;
		      this.dividas.add(new Divida(nome,valor, id));
		      id++;
		      return;
	   }
		   else
			   throw new RuntimeException ("Saldo inferior ao valor pedido");
		   
		}
		//mostrar dividas
		public String mostrarDividas() {
				 return "" + dividas.toString();
		}
		//mostrar clientes 
	    public String mostrarClientes() {
	    	String saida =  "";
	    	for(int i = 0; i < clientes.size(); i++)
	    	   saida += ""+ this.clientes.get(i).mostrarSaldoClientes();
        	return saida;
	    }
	    //mostrar cliente especifico
		public void mostrarClienteEspecifico(String nome) {
			int i = 0;
			for (Cliente c : clientes) {
				if (c.nome.equals(nome)) {
					System.out.println(c.mostrarSaldoClientes());
					while( dividas.get(i).nome.equals(nome)) {
						       System.out.println( dividas.get(i).toString());
			                   i++;       
					}
				}
			return;
			}
		    throw new RuntimeException("Cliente não encontrado");
		}
	
		//receber dinheirose o cliente for cadastrado
		public void receberDinheiro(String nome, float valor) {
			for (Cliente c : clientes)
				if (c.nome.equals(nome)) {
					if (c.saldo < 0) {
						c.saldo = saldo + valor;
						dinheiro = dinheiro + valor;
						this.dividas.add(new Divida(nome, valor, id));
						id++;
						return;
					}
				}
	
			throw new RuntimeException("Cliente nao encontrado");
	
		}
		//matar cliente
		public void matarCliente(String nome) {
			for( int i = 0; i < clientes.size(); i++) {
				if(clientes.get(i).nome.equals(nome)) {
					this.clientes.remove(clientes.get(i));
					apagarDividas(nome);
			        return;
				}
			}
			
			throw new RuntimeException("Cliente não encontrado");
		}
		// apagar dividas
		public void apagarDividas( String nome) {
			for(int i = 0 ; i<dividas.size(); i++) {
				if(dividas.get(i).nome.equals(nome))
					this.dividas.remove(dividas.get(i));
				    i--;
			}
		}
	
}
  //criação da classe controller
public class Controller {
	 Sistema sis;
   	 Scanner sca;
 	   	 	    //criação do construtor de controller
	 	public Controller() {
	 		sca = new Scanner(System.in);
	 		
	 	}
	 	// interação com usuario
	 	public String query(String line) {
	 		String[] ui = line.split(" ");
	 		
	 	    if (ui[0].equals("init"))
	 			sis = new Sistema(Float.parseFloat(ui[1]));
	 	    else if (ui[0].equals("show"))
	 			return " " + sis ;
	 	    else if(ui[0].equals("cadastrarCliente"))
	 	    	sis.cadastrarClientes(ui[1],ui[2]);
	 	    else if(ui[0].equals("mostrarDividas"))
	 	    	return "" + sis.mostrarDividas() ;
	 	    else if(ui[0].equals("emprestarDinheiro"))
	 	    	sis.emprestarDinheiro(ui[1],Float.parseFloat(ui[2]));
	 	    else if(ui[0].equals("mostrarClientes"))
	 	    	return ""+ sis.mostrarClientes();
	 	    else if(ui[0].equals("mostrarClienteEspecifico"))
	 	        sis.mostrarClienteEspecifico(ui[1]);
	 	    else if(ui[0].equals("receberDinheiro"))
	 	    	sis.receberDinheiro(ui[1],Float.parseFloat(ui[2]));
	 	    else if(ui[0].equals("matarCliente"))
	 	    	sis.matarCliente(ui[1]);
	 	    else
	 	    	return " Comando invalido";
	 		return "done";
	 	}
	
	
	
	
	 	public void shell() {
	 		while (true) {
	 			String line = sca.nextLine();
	 			try {
	 				System.out.println(query(line));
	 			} catch (RuntimeException re) {
	 				System.out.println(re.getMessage());
	 			}
	 		}
	 	}
	 	
	 	public static void main(String[] args) {
	         Controller c = new Controller();
	         c.shell();
	     }
   
}