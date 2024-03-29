package br.com.algoris.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.algoris.beans.Curso;
import br.com.algoris.conexao.Conexao;

public class CursoDAO {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public CursoDAO()throws Exception{
		con = Conexao.getConnection();
	}
	
	//metodo que grava um novo curso
	public String gravarCurso(Curso cur)throws Exception{
		stmt = con.prepareStatement("INSERT INTO T_CUROS VALUES(sq_curso.nextval,?,?,?,?)");
		stmt.setString(1, cur.getDescricao());
		stmt.setString(2, cur.getCampus());
		stmt.setString(3, cur.getCargaHoraria());
		stmt.setString(4, cur.getTipoCurso());
		stmt.executeUpdate();
		return "Curso cadastrado com Sucesso!";
	}
	
	//metodo que faz a consulta do curso atraves do codigo e que retorna somente um curso
	public Curso consultaPorCodigo(int codigo)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_CURSO WHERE CD_CURSO = ?");
		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			return new Curso(
					rs.getInt("CD_CURSO"),
					rs.getString("DESCRICAO"),
					rs.getString("CAMPUS"),
					rs.getString("CARGAHORARIA"),
					rs.getString("TIPOCURSO"));
		}else {
			return new Curso();
		}
	}
	
	//metodo que faz a consulta do curso pela descri��o e retorna uma lista de cursos
	public List<Curso> consultarPorDescricao(String descricao)throws Exception{
		List<Curso> lista = new ArrayList<Curso>();
		stmt = con.prepareStatement("SELECT * FROM T_CURSO WHERE DESCRICAO LIKE ?");
		stmt.setString(1, "%"+descricao+"%");
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			lista.add(new Curso(
					rs.getInt("CD_CURSO"),
					rs.getString("DESCRICAO"),
					rs.getString("CAMPUS"),
					rs.getString("CARGAHORARIA"),
					rs.getString("TIPOCURSO")
					));
		}
		
		return lista;
	}
	
	//metodo que faz a consulta do curso pelo campus e retorna um lista de cursos
	public List<Curso> consultarPorCampus(String campus)throws Exception{
		List<Curso> lista = new ArrayList<Curso>();
		stmt = con.prepareStatement("SELECT * FROM T_CURSO WHERE CAMPUS LIKE ?");
		stmt.setString(1, "%"+campus+"%");
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			lista.add(new Curso(
					rs.getInt("CD_CURSO"),
					rs.getString("DESCRICAO"),
					rs.getString("CAMPUS"),
					rs.getString("CARGAHORARIA"),
					rs.getString("TIPOCURSO")
					));
		}
		
		return lista;
	}
	
	//metodo que faz a consulta do curso pela carga horaria e retorna uma lista de curso
	public List<Curso> consultaPorCargaHoraria(String cargaHoraria)throws Exception{
		List<Curso> lista = new ArrayList<Curso>();
		stmt = con.prepareStatement("SELECT * FROM T_CURSO WHERE CARGAHORARIA LIKE ?");
		stmt.setString(1, "%"+cargaHoraria+"%");
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			lista.add(new Curso(
					rs.getInt("CD_CURSO"),
					rs.getString("DESCRICAO"),
					rs.getString("CAMPUS"),
					rs.getString("CARGAHORARIA"),
					rs.getString("TIPOCURSO")
					));
		}
		
		return lista;
	}
	
	//metodo que faz a consulta de curso pelo tipo de curso e rotorna uma lista de curso
	public List<Curso> consultaPorTipoCurso(String tipoCurso)throws Exception{
		List<Curso> lista = new ArrayList<Curso>();
		stmt = con.prepareStatement("SELECT * FROM T_CURSO WHERE TIPOCURSO LIKE ?");
		stmt.setString(1, "%"+tipoCurso+"%");
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			lista.add(new Curso(
					rs.getInt("CD_CURSO"),
					rs.getString("DESCRICAO"),
					rs.getString("CAMPUS"),
					rs.getString("CARGAHORARIA"),
					rs.getString("TIPOCURSO")
					));
		}
		
		return lista;
	}
	
	//metodo que faz a atualiza��o do curso
	public String atualizaCurso(Curso cur)throws Exception{
		stmt = con.prepareStatement("UPDATE T_CURSO SET DESCRICAO = ?, CAMPUS = ?, CARGAHORARIA = ?, TIPOCURSO = ?" 
				+ "WHERE CD_CURSO = ?");
		stmt.setString(1, cur.getDescricao());
		stmt.setString(2, cur.getCampus());
		stmt.setString(3, cur.getCargaHoraria());
		stmt.setString(4, cur.getTipoCurso());
		stmt.setInt(5, cur.getCodigo());
		stmt.executeUpdate();
		return "Curso Atualizado!";
	}
	
	//metodo que deleta o curso pelo codigo
	public String deletarCurso(Curso cur)throws Exception{
		stmt = con.prepareStatement("DELETE FROM T_CURSO WHERE CD_CURSO = ?");
		stmt.setInt(1, cur.getCodigo());
		stmt.executeUpdate();
		return "Curso Deletado!";
	}
	
	//metodo que fecha a conex�o com o Banco de Dados
	public void fechar()throws Exception{
		con.close();
	}
	
}
