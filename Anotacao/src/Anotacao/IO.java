package Anotacao;

import java.util.Scanner;
import java.util.ArrayList;


class Anotacao{
	String texto;
	String titulo;
	
	public Anotacao(String titulo, String texto) {
		super();
		this.texto = texto;
		this.titulo = titulo;
	}
	public String toString() {
		return titulo + " " + texto;
	}
	
}

class User implements Comparable<User>{ 
	private String password;
	private String username;
	public Repositorio<Anotacao> anotacoes;
	
	public Repositorio<Anotacao> getAnotacao() {
		return anotacoes;
	}
	public void setAnotação(Repositorio<Anotacao> anotacoes) {
		this.anotacoes = anotacoes;
	}
	
	public User(String username, String password) {
		this.password = password;
		this.username = username;
		anotacoes = new Repositorio <Anotacao>("");
		
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}
	public String getUsername() {
		return username;
	}	
	public String toString() {
		return " " + this.username;
	}
	public int compareTo(User o) {
		return this.username.compareTo(o.username);
	}
}

class GerenciadorDeLogin{
	private Repositorio<User> usuarios;
	private User user;
	
	public GerenciadorDeLogin(Repositorio<User> usuarios) {
		this.usuarios = usuarios;
		user = null;
	}
	
	void login(String username, String senha){
		if(user != null)
			throw new RuntimeException("fail: ja existe alguem logado");
		if(!usuarios.get(username).matchPassword(senha))
			throw new RuntimeException("fail: password invalido");
		this.user = usuarios.get(username);
	}
	
	void logout(){
		if(user == null)
			throw new RuntimeException("fail: ninguem logado");
		user = null;
	}
	
	public User getUser(){
		if(user == null)
			throw new RuntimeException("fail: ninguem logado");
		return user;
	}
}

class Controller{
	Repositorio<User> usur;
	GerenciadorDeLogin gerLogin;
	Repositorio<Anotacao> anotações;
	
	public Controller() {
		usur = new Repositorio<User>("usuario");
		gerLogin = new GerenciadorDeLogin(usur);
		anotações = new Repositorio<Anotacao>("anotação");
	}

    public String oracle(String line){
        String ui[] = line.split(" ");

        if(ui[0].equals("help"))
            return "addUser _username _password, login _username _password\n" + 
                   "logout, changePass _old _new, showUser\n" + 
                   "addNotes _titulo _texto, rmNotes _titulo, showNotes";
        else if(ui[0].equals("addUser"))
        	usur.add(ui[1], new User(ui[1], ui[2]));
        else if(ui[0].equals("login"))
        	gerLogin.login(ui[1], ui[2]);
        else if(ui[0].equals("logout"))
        	gerLogin.logout();
        else if(ui[0].equals("changePass")) {
        	User user = gerLogin.getUser();
        	if(user.matchPassword(ui[1]))
        		user.setPassword(ui[2]);
        }
        else if(ui[0].equals("addNotes")) {
		    String texto = " ";
		    for(int i = 2 ; i<ui.length; i++)
		    	texto += ui[i] + "";
		    gerLogin.getUser().anotacoes.add(ui[1], new Anotacao(ui[1],texto)); 
		}
        else if(ui[0].equals("rmNotes")) {
        	gerLogin.getUser().anotacoes.remove(ui[1]);
        }
        else if(ui[0].equals("showUser")) {
        	String saida = "";
        	for(User user : usur.getAll())
        		saida += user.getUsername() + "\n";
        	return saida;
        }
//
        else if(ui[0].equals("showNotes")) {
        	String saida = " ";
    		for(User u : usur.getAll())
    				saida += u.getAnotacao() + "\n";
    			return saida;
        }
        else
            return "comando invalido";
        return "done";
    }
}
   

public class IO {
    static Scanner scan = new Scanner(System.in);
      
    static private String tab(String text){
        return "  " + String.join("\n  ", text.split("\n"));
    }
    
    public static void main(String[] args) {
        Controller cont = new Controller();
        System.out.println("Digite um comando ou help:");
        while(true){
            String line = scan.nextLine();
            try {
                System.out.println(tab(cont.oracle(line)));
            }catch(Exception e) {
                System.out.println(tab(e.getMessage()));
            }
        }
    }
}