package Twitter;

import java.util.ArrayList;
import java.util.Scanner;

class Gerenciador {
	//crando variavel repositorio do tp tweet com nome tweets
	private Repositorio<Tweet> tweets;
	
	// criando construtor
	public Gerenciador() {
		//criando gerador de tweets para armazenar tweets  
		tweets = new Repositorio<Tweet>("tweets");
	}
	//pegando tweets armazenados 
	public Repositorio<Tweet> getTweets() {
		return tweets;
	}
	// definindo tweet armazenados
	public void setTweets(Repositorio<Tweet> tweets) {
		this.tweets = tweets;
	}
	//gerar tweet
	public void gerarTweet(Tweet t) {
		//adiciona um tweet recebendo id do tweet 
		this.tweets.add(""+t.getIdTweet(), t);
	}
	//mostar tweets
	public String showTweets() {
		String saida = "";
		//para cada tweet dentro do repositorio de tweets
		for(Tweet t : tweets.getAll())
			//saida recebe tweet
			saida += t.toString() + "\n";
		//retorna saida
		return saida;
	}
}

class Tweet {
	//criando variavel id do tweet
	private int idTweet;
	//crainado variavel do tp usuario us
	User us;
	//criando variavel titulo
	private String titulo;
	//criando variavel texto 
	private String texto;
	//criando variavel like
	private boolean isLike;
	//criando variavel lido
	private boolean isLido;
	//criando lista do tp strind da qtd de likes
	private ArrayList<String> quantidadedelikes ;
	
	//criando construtor
	public Tweet(int idTweet,User us,String titulo, String texto) {
		//recebendo id tweet
		this.idTweet = idTweet;
		//recebendo o usuario
		this.us = us;
		//recebendo titulo
		this.titulo = titulo;
		//recebendo texto
		this.texto = texto;
		//recebendo valor do like lido(falso)
		this.isLido = false;
		//recebendo valor do like(falso)
		this.isLike = false;
		//criando nova lista de qtd de likes
		quantidadedelikes = new ArrayList<String>();
	}
	
		//criando setters e getters pegando valores do usuario(id do tweet, usuario, titulo, texto, like e lido)
		public int getIdTweet() {
			return idTweet;
		}
	
		public void setIdTweet(int idTweet) {
			this.idTweet = idTweet;
		}
	
		public User getUs() {
			return us;
		}
	
		public void setUs(User us) {
			this.us = us;
		}
	
		public String getTitulo() {
			return titulo;
		}
	
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
	
		public String getTexto() {
			return texto;
		}
	
		public void setTexto(String texto) {
			this.texto = texto;
		}
		
		public boolean isLike() {
			return isLike;
		}
	
	
		public void setLike(boolean isLike) {
			this.isLike = isLike;
		}
	
	
		public boolean isLido() {
			return isLido;
		}
	
	
		public void setLido(boolean isLido) {
			this.isLido = isLido;
		}
		
		public ArrayList<String> getQuantidadedelikes() {
			return quantidadedelikes;
		}
		//dar like
		public void darLike(String username) {
		//adiciona o nome de quem deu o like a qtd de likes
	       this.quantidadedelikes.add(username);
		}
		
		//mostar likes	
		public String mostrarLikes() {
			String saida = "";
			//enquanto i for menor que qtd de likes
			for(int i = 0; i < quantidadedelikes.size(); i++) {
				//saida recebe qtd de likes	
				saida += ":" + quantidadedelikes.get(i) + "\n";
			}
			//retorna saida
			return saida;
		}
		
		public String toString() {
			return ""+ idTweet + ": "+ us + ": " + titulo + ": " + texto ;
		}
	}

class User {
 	//declarando variaveis da classe user
	private String iduser;
	//lista no repositorio de seguidores
	private Repositorio<User> seguidores;
	//lista no repositorio de seguidos
	private Repositorio<User> seguidos;
	//lista do tweets do usuario no repositorio
	private Repositorio<Tweet> mytweets;
	//lista de timelines do usuario
	private Repositorio<Tweet> timeline;
	Tweet mensagem;
	int contadortwittes = 0;
	
	// declarando construtor da classe user
	public User(String iduser) {
		//recebendo nome do usuario
		this.iduser = iduser;
		//criando um novo repositorio do tipo usuarios  de seguidores
		seguidores = new Repositorio<User>("seguidores");
		//criando um novo repositorio do tipo usuarios  de seguidos
		seguidos = new Repositorio<User>("seguidos");
		//criando um novo repositorio do tipo usuarios  de myTweets
		mytweets = new Repositorio<Tweet>("mytweets");
		//criando um novo repositorio do tipo usuarios  de timeline
	    timeline = new Repositorio<Tweet>("timeline");
	}
			//pegando e retonando timeline(Feed do usuario)
		public Repositorio<Tweet> getTimeline() {
			return timeline;
		}
		
		//pegando id do susario
		public String getIduser() {
			return iduser;
		}
		//definindo o id do usuario
		public void setIduser(String iduser) {
			this.iduser = iduser;
		}
		//pegando e retornando lista seguidores
		public Repositorio<User> getSeguidores() {
			return seguidores;
		}
		//pegando e retornando listaseguidos 
		public Repositorio<User> getSeguidos() {
			return seguidos;
		}
		
		public String toString() {
			return "" + iduser ;
		}
		//mostrar seguidores
		public String showSeguidores() {
			String saida = "";
			//para seguidores do tp Usuario dentro da lista de seguidores ou todos seguidores
			for(User seg : seguidores.getAll())
				//saida recebe seguidores
				saida += seg + " ";
			//retorna seguidores
			return saida ;
		}
		//mostrar seguidos
		public String showSeguidos() {
			String saida = "";
			//para seguidos do tp usuario dentro de lista de seguidos ou todos os seguidos
			for(User seg : seguidos.getAll())
				//sainda recebe seguidos
				saida += seg + " ";
	 		//retorna seguidos
			return saida ;
		}
		//seguir usuario
		public void seguir(User us) {
			//adiciono outro usuario na minha lista de seguidos
			us.seguidos.add(this.getIduser(), new User(this.getIduser()));
			//o usuario se adiciona na lista de seguidores do outro usuario
			this.seguidores.add(us.getIduser(), us);
		} 
		//criar tweet
	    public void criarTweet(Tweet t) {
			//adiciona um novo tweet do usuario aos tweets do usuario
			this.mytweets.add(""+t.getIdTweet(), t);
	    }		
	    
	    public void addTime(Tweet t) {
			//adiciona uma novo tweet a timeline do usuario
	    		this.timeline.add(""+t.getIdTweet(), t);
	    	
	    }
	        // mostrando tweets do usuario 
		public String mostrarMytweets() {
	    	String saida = "";  
		//para cada Tweet s dentro dos tweets do usuario
	    	for(Tweet s : mytweets.getAll()) {
			//saida recebe tweet + usuario +titulo do tweet + texto
	    		saida += " " + s.getIdTweet() + s.getUs() + ":"+s.getTitulo() + " " + s.getTexto() + "\n"; 
	    	}
		//retorna saida
	    	return saida;
	    }
		//mostrar timeline 
		public String mostrarTime() {
	    	String saida = "";
		//para cada tweet s dentro da timeline do usuario ou Feed
	    	for(Tweet s : timeline.getAll()) {
			//se o tweet  não for lido então
	    		if(!s.isLido()) {
			//saida recebe tweet + usuario + titulo + texto
	    		   saida += " " + s.getIdTweet() + " " +s.getUs() + ":"+s.getTitulo() + " " + s.getTexto() + "\n"; 
			//tweet fica lido
	    		   s.setLido(true);
			//conto os tweets lidos
	    	       contadortwittes++;
	    	    }
	    	}
		//mostra a qtd de tweets 
	    	System.out.println(" Voce tem :" + contadortwittes + " tweets");
		//zera o tweets do feed do usuario
	    	contadortwittes = 0;
	    	return saida;
	    }
		// dar like
		public void darLike(int idtweet) {
			//para cada tweet dentro da timeline
			for(Tweet s : timeline.getAll()) {
				// se o tweet que estou pegando estiver na timeline
				if(s.getIdTweet() == idtweet){
					//se n tiver like
					if(!s.isLike()) {
					// dar like
					   s.setLike(true);
					//retona
					   return;
					}
				}
			}
			//tweet n exite na timeline do usuario que eu quero dar like
			throw new RuntimeException("fail: você não possui esse tweet");
		}
		
		
	}

public class Controller {
	//criando repositorio de usuarios de tp usuario
	Repositorio<User> usuarios;
	Scanner sca;
	//criando numero de tweets
	int numertweet= 1;
	//criando variavel do tp TweetGerenciador 
	Gerenciador ger;
	//criando contador de tweets
	int contadortweets = 0;
	
public Controller() {
	sca = new Scanner(System.in);
	//criando novo repositorio de usuarios
	usuarios = new Repositorio<User>("username");
	//craindo novo gerenciador de tweet
	ger = new Gerenciador();
}

public String query(String line) {
	String[] ui = line.split(" ");

	//se digitar addUser
	if (ui[0].equals("addUser"))
		//adiciona novo usuario ao repositorio de usuarios 
		 usuarios.add(ui[1], new User(ui[1]));
	// se digitar showUser
	else if(ui[0].equals("showUser")) {
		String saida= "";
		//para cada usuario dentro de usuarios
		for(User user : usuarios.getAll())
			//saida recebe usuario + seguidores do usuario + seguidos do usuario
			saida += user.toString() + " seguidores: ["+ user.showSeguidores() +"] seguidos: [" + user.showSeguidos() +"] \n";
	    return saida;
	}	
	//se digitar seguir
	else if(ui[0].equals("seguir")) 
		//me adiciona na lista de seguidores e adiciona o outro usuario na lista de seguidos
		usuarios.get(ui[2]).seguir(usuarios.get(ui[1]));
	// se digitar criarTweet
	else if(ui[0].equals("criartweet")) {
       String texto = "";
	
       for(int i = 3 ; i<ui.length; i++)
    	   texto += ui[i] + " ";
       
       for(User s : usuarios.get(ui[1]).getSeguidores().getAll()) 
    	   s.addTime(new Tweet(numertweet, usuarios.get(ui[1]), ui[2], texto));
       
       usuarios.get(ui[1]).criarTweet(new Tweet(numertweet, usuarios.get(ui[1]), ui[2], texto));      
       ger.gerarTweet(new Tweet(numertweet, usuarios.get(ui[1]), ui[2],texto));  
       numertweet++;
	}
	else if(ui[0].equals("mostrarMytweets"))
		System.out.println(usuarios.get(ui[1]).mostrarMytweets());
	else if(ui[0].equals("mostrartweets"))
		System.out.println(ger.showTweets());
	
	else if(ui[0].equals("mostrarTime")) 
		System.out.println(usuarios.get(ui[1]).mostrarTime());
	// se digitar dar liketweet	
	else if(ui[0].equals("liketweet")) {
		//
		usuarios.get(ui[1]).darLike(Integer.parseInt(ui[2]));
		//dar like
		ger.getTweets().get(ui[2]).darLike(usuarios.get(ui[1]).getIduser());
	}
	
	else if(ui[0].equals("showlikes")) {
		System.out.println(" " + ger.getTweets().get(ui[1]).mostrarLikes());
	}
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