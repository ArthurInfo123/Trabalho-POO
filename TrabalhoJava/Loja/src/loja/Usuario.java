package loja;

public class Usuario {
		

	private String nome;
	private String email;
	private String senha;
	private int nivel;
	private Boolean logado = false;
	private int userId;
	
	
	public Usuario()
	{
		
	}
	
	public static final int ADMINISTRADOR = 1;
	public static final int CLIENTE = 2;
	
	public Usuario(String email, String senha)
	{
		this.nome = "N/A";
		this.email = email;
		this.senha = senha;
		this.nivel = CLIENTE;
	}
	
	public Usuario(String nome, String email, String senha, int nivel)
	{
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.nivel = nivel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getNivel() {
		return nivel;
	}	
	
	public int quantUsuario(Usuario usuarios[])
	{			
		return usuarios == null ? 0 : usuarios.length;
	}
	
	public Boolean verificaSeTemUsuarioComEmail(Usuario usuarios[])
	{
		for(int i = 0; i < usuarios.length; i++)
		{
			//Se tiver um usuario com o email retorna true, senão retorna false
			if(usuarios[i] != null)
			{
				if(usuarios[i].email == this.email)
				{	
					return true;
				}
				
			}
		}
		return false;
	}

	public Boolean validaEmail()
	{
		if(this.email.contains("@") && this.email.contains(".com"))
		{
			return true;
		}
		
		return false;
	}
	
	public int ultimaPosicaoVetor(Usuario usuarios[])
	{
		int ultimaPosicao = 0;
		
		for(int i = 0; i < usuarios.length; i++)
		{
			if(usuarios[i] == null)
			{
				ultimaPosicao = i;
				break;
			}
		}
		
		return ultimaPosicao;
	}

	public String matchNivel()
	{
		String retorno = "";
		switch(this.nivel)
		{
			case(ADMINISTRADOR):
			{
				retorno = "Administrador";
				break;
			}
			case(CLIENTE):
			{
				retorno = "Cliente";
				break;
			}
		}
		
		return retorno;
	}
	
	public Usuario[] criaUsuario(Usuario usuarios[]) {
	    if (usuarios == null) {
	        usuarios = new Usuario[10];
	        this.userId = 0;
	        usuarios[0] = this;	        
	        return usuarios;
	    }

	    if (this.verificaSeTemUsuarioComEmail(usuarios) || !this.validaEmail() || this.ultimaPosicaoVetor(usuarios) == -1) {
	        return null;
	    }

	    this.userId += 1;
	    usuarios[this.ultimaPosicaoVetor(usuarios)] = this;	
	    return usuarios;
	}
	
	public Usuario validaLogin(Usuario usuarios[])
	{
		Usuario usuarioEncontrado = null;
		
		for(int i = 0; i < quantUsuario(usuarios); i++)
		{			
			if(this.email.equals(usuarios[i].email) && this.senha.equals(usuarios[i].senha) && usuarios[i] != null)
			{
				usuarios[i].logado = true;
				usuarioEncontrado = usuarios[i];
				break;
			}					
		}
		
		return usuarioEncontrado;
	}
	
	public Boolean atualizaUsuario(String nome, String email, String senha, int nivel, Usuario[] usuarios)
	{
		this.nome = nome;
		if(!this.verificaSeTemUsuarioComEmail(usuarios) && this.validaEmail())
		{
			this.email = email;			
		}
		this.senha = senha;
		if(this.nivel == CLIENTE && nivel == ADMINISTRADOR)
		{
			return false;
		}else {
			this.nivel = nivel;
		}
		return true;
	}
	
	public Boolean deletaUsuario(Usuario[] usuarios)
	{
		Boolean excluiu = false;
		for(int i = 0; i < this.quantUsuario(usuarios); i++)
		{
			if(usuarios[i].userId == this.userId)
			{
				usuarios[i] = null;
				excluiu = true;
				break;
			}
		}
		
		return excluiu;
	}
	
	public String toString()
	{
		return "Id: " + this.userId + "\n" + "Nome: " + this.nome + "\n" + "Email: " +  this.email + "\n" + "Senha: " + this.senha + "\n" + "Nível: " + this.matchNivel();
	}

	

	public Boolean getLogado() {
		return logado;
	}

	public void setLogado(Boolean logado) {
		this.logado = logado;
	}

	public int getAdministrador() {
		return ADMINISTRADOR;
	}

	public static int getCliente() {
		return CLIENTE;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
		
		
}
