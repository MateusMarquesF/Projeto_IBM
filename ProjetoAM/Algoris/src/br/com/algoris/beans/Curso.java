package br.com.algoris.beans;

public class Curso {

	private int codigo;
	private String descricao;
	private String campus;
	private String cargaHoraria;
	private String tipoCurso;
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getCampus() {
		return campus;
	}
	
	public void setCampus(String campus) {
		this.campus = campus;
	}
	
	public String getCargaHoraria() {
		return cargaHoraria;
	}
	
	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
	public String getTipoCurso() {
		return tipoCurso;
	}
	
	public void setTipoCurso(String tipoCurso) {
		this.tipoCurso = tipoCurso;
	}

	public Curso(int codigo, String descricao, String campus, String cargaHoraria, String tipoCurso) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.campus = campus;
		this.cargaHoraria = cargaHoraria;
		this.tipoCurso = tipoCurso;
	}

	public Curso() {
		super();
	}
	
	public void setAll(int codigo, String descricao, String campus, String cargaHoraria, String tipoCurso) {
		setCodigo(codigo);
		setDescricao(descricao);
		setCampus(campus);
		setCargaHoraria(cargaHoraria);
		setTipoCurso(tipoCurso);
	}
	
	public String getAll() {
		return "C�digo: " + codigo + "\n" +
				"Descri��o: " + descricao + "\n" + 
				"Campus: " + campus + "\n" +
				"Carga Horaria: " + cargaHoraria + "\n" +
				"Tipo Curso: " + tipoCurso;
	}
}
