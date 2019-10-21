package br.com.algoris.beans;

public class Professor extends Curso {

	private int codigo;
	private String nome;
	private String email;
	private Login login;
	private Curso curso;
	
	public String getAll() {
		return super.getAll() + "\n"+
				"Código: " + codigo + "\n" +
				"Nome: " + nome + "\n" +
				"E-mail: " + email + "\n" +
				"Login: " + login.getRm() + 
				"Curso: " + curso.getCodigo();
	}
	
	public void setAll(int codigo, String nome, String email, Login login, Curso curso) {
		setCodigo(codigo);
		setNome(nome);
		setEmail(email);
		setLogin(login);
		setCurso(curso);
	}
	
	public Professor() {
		super();
	}

	public Professor(int codigo, String nome, String email, Login login, Curso curso) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.curso = curso;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
	
	public Login getLogin() {
		return login;
	}
	
	public void setLogin(Login login) {
		this.login = login;
	}
	
	public Curso getCurso() {
		return curso;
	}
	
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}
