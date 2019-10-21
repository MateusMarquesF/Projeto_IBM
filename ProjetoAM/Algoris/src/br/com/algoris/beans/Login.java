package br.com.algoris.beans;

public class Login {

	private int codigo;
	private String rm;
	private String senha;
	private String tipo;
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getRm() {
		return rm;
	}
	
	public void setRm(String rm) {
		this.rm = rm;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Login(int codigo, String rm, String senha, String tipo) {
		super();
		this.codigo = codigo;
		this.rm = rm;
		this.senha = senha;
		this.tipo = tipo;
	}

	public Login() {
		super();
	}
	
	public void setAll(int codigo, String rm, String senha, String tipo) {
		setCodigo(codigo);
		setRm(rm);
		setSenha(senha);
		setTipo(tipo);
	}
	
	public String getAll(int codigo, String rm, String senha, String tipo) {
		return "Código: " + codigo + "\n"+
				"Rm: " + rm + "\n" + 
				"Senha: " + senha + "\n" + 
				"Tipo: " + tipo;
	}
	
	
}
