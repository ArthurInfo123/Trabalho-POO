package loja;

public class Usuario {

	private String nome;
	private String email;
	private String senha;
	private int nivel;
	private Boolean logado = false;
	private int userId;

	private static int ultimoIdGerado = 0;

	public static final int ADMINISTRADOR = 1;
	public static final int CLIENTE = 2;

	public Usuario() {
	}

	public Usuario(String email, String senha) {
		this.nome = "N/A";
		this.email = email;
		this.senha = senha;
		this.nivel = CLIENTE;
		this.userId = ++ultimoIdGerado;
	}

	public Usuario(String nome, String email, String senha, int nivel) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.nivel = nivel;
		this.userId = ++ultimoIdGerado;
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

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Boolean getLogado() {
		return logado;
	}

	public void setLogado(Boolean logado) {
		this.logado = logado;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public static int getCliente() {
		return CLIENTE;
	}

	public int getAdministrador() {
		return ADMINISTRADOR;
	}

	public int quantUsuario(Usuario usuarios[]) {
		return usuarios == null ? 0 : usuarios.length;
	}

	public Boolean verificaSeTemUsuarioComEmail(Usuario usuarios[], int idIgnorar) {
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] != null && usuarios[i].email.equals(this.email) && usuarios[i].userId != idIgnorar) {
				return true;
			}
		}
		return false;
	}

	public Boolean validaEmail(String email) {
		return email.contains("@") && email.contains(".com");
	}

	public int ultimaPosicaoVetor(Usuario usuarios[]) {
		for (int i = 0; i < usuarios.length; i++) {
			if (usuarios[i] == null) {
				return i;
			}
		}
		return -1;
	}

	public String matchNivel() {
		switch (this.nivel) {
			case ADMINISTRADOR:
				return "Administrador";
			case CLIENTE:
				return "Cliente";
			default:
				return "Desconhecido";
		}
	}

	public Usuario[] criaUsuario(Usuario usuarios[]) {
		if (usuarios == null) {
			usuarios = new Usuario[30];
		}

		if (this.verificaSeTemUsuarioComEmail(usuarios, 0) || !this.validaEmail(this.email)) {
			return null;
		}

		int posicao = this.ultimaPosicaoVetor(usuarios);
		if (posicao == -1) {
			return null;
		}
		
		if(!this.validaSenha(this.senha))
		{
			return null;
		}

		usuarios[posicao] = this;
		return usuarios;
	}

	public Usuario validaLogin(Usuario usuarios[]) {
		for (int i = 0; i < quantUsuario(usuarios); i++) {
			if (usuarios[i] != null) {
				if (this.email.equals(usuarios[i].email) && this.senha.equals(usuarios[i].senha)) {
					usuarios[i].logado = true;
					return usuarios[i];
				}
			}
		}
		return null;
	}
	
	public Boolean validaSenha(String senha)
	{
		return senha.length() >= 3;
	}

	public Boolean atualizaUsuario(String nome, String email, String senha, int nivel, Usuario[] usuarios) {
		this.nome = nome;

		
		if ((this.email.equals(email) || !this.verificaSeTemUsuarioComEmail(usuarios, this.userId)) && this.validaEmail(email)) {			
			this.email = email;
		}

		if(this.validaSenha(this.senha))
		{
			this.senha = senha;
		}		

		if (this.nivel == CLIENTE && nivel == ADMINISTRADOR) {
			return false;
		} else {
			this.nivel = nivel;
		}
		return true;
	}

	public Boolean deletaUsuario(Usuario[] usuarios) {
		for (int i = 0; i < this.quantUsuario(usuarios); i++) {
			if (usuarios[i] != null && usuarios[i].userId == this.userId) {
				usuarios[i] = null;
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Id: " + this.userId + "\n" +
			   "Nome: " + this.nome + "\n" +
			   "Email: " + this.email + "\n" +
			   "Nível: " + this.matchNivel();
	}
}
