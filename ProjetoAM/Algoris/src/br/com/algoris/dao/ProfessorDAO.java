package br.com.algoris.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.algoris.beans.Curso;
import br.com.algoris.beans.Login;
import br.com.algoris.beans.Professor;
import br.com.algoris.conexao.Conexao;

public class ProfessorDAO {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public ProfessorDAO()throws Exception{
		con = Conexao.getConnection();
	}
	
	//metodo que inseri dados do Professor
	public String gravarProfessor(Professor prof)throws Exception{
		stmt = con.prepareStatement("INSERT INTO T_PROFESSOR VALUES(sq_professor.nextval,?,?,?,?)");
		stmt.setString(1, prof.getNome());
		stmt.setString(2, prof.getEmail());
		stmt.setInt(3, prof.getLogin().getCodigo());
		stmt.setInt(4, prof.getCurso().getCodigo());
		stmt.executeUpdate();
		return "Professor cadastrado com Sucesso!";
	}
	
	//metodo que consulta Professor pelo codigo
	public Professor consultaPorCodigo(int codigo)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_PROFESSOR WHERE CD_PROFESSOR = ?");
		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();
			
		if(rs.next()) {
			return new Professor(
					rs.getInt("CD_ALUNO"),
					rs.getString("NOME"),
					rs.getString("EMAIL"),
					new LoginDAO().consultaPorCodigo(rs.getInt("CD_LOGIN")),
					new CursoDAO().consultaPorCodigo(rs.getInt("CD_CURSO")));
		}
		else {
			return new Professor();
		}
	}
		
	//metodo que consulta professor pelo Nome
	public List<Professor> consultarPorNome(String nome)throws Exception{
		List<Professor> lista = new ArrayList<Professor>();
		stmt = con.prepareStatement("SELECT * FROM T_PROFESSOR"+
									"INNER JOIN T_CURSO ON T_CURSO.CD_CURSO = T_PROFESSOR.CD_CURSO"+
									"INNER JOIN T_LOGIN ON T_LOGIN.CD_LOGIN = T_PROFESSOR.CD_LOGIN"+
									"WHERE NOME LIKE ?");
		stmt.setString(1, "%" + nome + "%");
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			lista.add(new Professor(
					rs.getInt("CD_PROFESSOR"),
					rs.getString("NOME"),
					rs.getString("EMAIL"),
					new Login(
							rs.getInt("CD_LOGIN"),
							rs.getString("RM"),
							rs.getString("SENHA"),
							rs.getString("TIPO")
							),
					new Curso(
							rs.getInt("CD_CURSO"),
							rs.getString("DESCRICAO"),
							rs.getString("CAMPUS"),
							rs.getString("CARGAHORARIA"),
							rs.getString("TIPOCURSO"))
					));
		}
	
		return lista;
	}
	
	//metodo que consulta Professor pelo Email
	public List<Professor> consultarPorEmail(String email)throws Exception{
		List<Professor> lista = new ArrayList<Professor>();
		stmt = con.prepareStatement("SELECT * FROM T_PROFESSOR"+
										"INNER JOIN T_CURSO ON T_CURSO.CD_CURSO = T_PROFESSOR.CD_CURSO"+
										"INNER JOIN T_LOGIN ON T_LOGIN.CD_LOGIN = T_PROFESSOR.CD_LOGIN"+
										"WHERE EMAIL LIKE ?");
		stmt.setString(1, "%" + email + "%");
		rs = stmt.executeQuery();
			
		while(rs.next()) {
			lista.add(new Professor(
					rs.getInt("CD_PROFESSOR"),
					rs.getString("NOME"),
					rs.getString("EMAIL"),
					new Login(
							rs.getInt("CD_LOGIN"),
							rs.getString("RM"),
							rs.getString("SENHA"),
							rs.getString("TIPO")
							),
					new Curso(
							rs.getInt("CD_CURSO"),
							rs.getString("DESCRICAO"),
							rs.getString("CAMPUS"),
							rs.getString("CARGAHORARIA"),
							rs.getString("TIPOCURSO"))
					));
		}
	
		return lista;
	}
	
	//metodo que atualiza o email do Professor
	public String atualizaEmailProfessor(Professor prof)throws Exception{
		stmt = con.prepareStatement("UPDATE T_PROFESSOR SET EMAIL = ? WHERE CD_PROFESSOR = ?");
		stmt.setString(1, prof.getEmail());
		stmt.setInt(2, prof.getCodigo());
		stmt.executeUpdate();
		return "E-mail atualizado com Sucesso!";
	}
	
	//metodo que deleta o Professor
	public String apagarProfessor(int codigo)throws Exception{
		stmt = con.prepareStatement("DELETE FROM T_PROFESSOR QHERE CD_PROFESSOR = ?");
		stmt.setInt(1, codigo);
		stmt.executeUpdate();
		return "Professor excluido com Sucesso!";
	}
		
	//metodo que fecha conexao com o banco de dados
	public void fechar()throws Exception{
		con.close();
	}
}