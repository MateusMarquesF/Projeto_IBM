package br.com.algoris.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.algoris.beans.Aluno;
import br.com.algoris.beans.Curso;
import br.com.algoris.beans.Login;
import br.com.algoris.conexao.Conexao;

public class AlunoDAO {

	private PreparedStatement stmt;
	private Connection con;
	private ResultSet rs;
	
	public AlunoDAO()throws Exception{
		con = Conexao.getConnection();
	}
	
	//metodo que grava Aluno
	public String gravarAluno(Aluno al)throws Exception{
		stmt = con.prepareStatement("INSERT INTO T_ALUNO VALUES (sq_aluno.nextval, ?, ?, ?, ?, ?)");
		stmt.setString(1, al.getNome());
		stmt.setString(2, al.getEmail());
		stmt.setString(3, al.getCpf());
		stmt.setInt(4, al.getLogin().getCodigo());
		stmt.setInt(5, al.getCurso().getCodigo());
		stmt.executeUpdate();
		return "Aluno cadastrado com Sucesso!";
	}
	
	//metodo que consulta aluno pelo codigo
	public Aluno consultaPorCodigo(int codigo)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_ALUNO WHERE CD_ALUNO = ?");
		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			return new Aluno(
					rs.getInt("CD_ALUNO"),
					rs.getString("NOME"),
					rs.getString("EMAIL"),
					rs.getString("CPF"),
					new LoginDAO().consultaPorCodigo(rs.getInt("CD_LOGIN")),
					new CursoDAO().consultaPorCodigo(rs.getInt("CD_CURSO")));
		}
		else {
			return new Aluno();
		}
	}
	
	//metodo que consulta aluno pelo Nome
	public List<Aluno> consultarPorNome(String nome)throws Exception{
		List<Aluno> lista = new ArrayList<Aluno>();
		stmt = con.prepareStatement("SELECT * FROM T_ALUNO"+
									"INNER JOIN T_CURSO ON T_CURSO.CD_CURSO = T_ALUNO.CD_CURSO"+
									"INNER JOIN T_LOGIN ON T_LOGIN.CD_LOGIN = T_ALUNO.CD_LOGIN"+
									"WHERE NOME LIKE ?");
		stmt.setString(1, "%" + nome + "%");
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			lista.add(new Aluno(
					rs.getInt("CD_ALUNO"),
					rs.getString("NOME"),
					rs.getString("EMAIL"),
					rs.getString("CPF"),
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
	
	//metodo que consulta Aluno pelo Email
	public List<Aluno> consultarPorEmail(String email)throws Exception{
		List<Aluno> lista = new ArrayList<Aluno>();
		stmt = con.prepareStatement("SELECT * FROM T_ALUNO"+
										"INNER JOIN T_CURSO ON T_CURSO.CD_CURSO = T_ALUNO.CD_CURSO"+
										"INNER JOIN T_LOGIN ON T_LOGIN.CD_LOGIN = T_ALUNO.CD_LOGIN"+
										"WHERE EMAIL LIKE ?");
		stmt.setString(1, "%" + email + "%");
		rs = stmt.executeQuery();
			
		while(rs.next()) {
			lista.add(new Aluno(
					rs.getInt("CD_ALUNO"),
					rs.getString("NOME"),
					rs.getString("EMAIL"),
					rs.getString("CPF"),
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
	
	//metodo que consulta aluno pelo cpf
	public Aluno consultarPorCpf(String cpf)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_ALUNO"+
										"INNER JOIN T_CURSO ON T_CURSO.CD_CURSO = T_ALUNO.CD_CURSO"+
										"INNER JOIN T_LOGIN ON T_LOGIN.CD_LOGIN = T_ALUNO.CD_LOGIN"+
										"WHERE CPF = ?");
		stmt.setString(1, cpf);
		rs = stmt.executeQuery();
			
		if(rs.next()) {
			return new Aluno(
						rs.getInt("CD_ALUNO"),
						rs.getString("NOME"),
						rs.getString("EMAIL"),
						rs.getString("CPF"),
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
						);
			}
			else {
				return new Aluno();
			}
		}
	
	//metodo que atualiza o email do aluno
	public String atualizaEmailAluno(Aluno al)throws Exception{
		stmt = con.prepareStatement("UPDATE T_ALUNO SET EMAIL = ? WHERE CD_ALUNO = ?");
		stmt.setString(1, al.getEmail());
		stmt.setInt(2, al.getCodigo());
		stmt.executeUpdate();
		return "E-mail atualizado com Sucesso!";
	}
	
	//metodo que deleta o aluno
	public String apagarAluno(int codigo)throws Exception{
		stmt = con.prepareStatement("DELETE FROM T_ALUNO QHERE CD_ALUNO = ?");
		stmt.setInt(1, codigo);
		stmt.executeUpdate();
		return "Aluno excluido com Sucesso!";
	}
	
	//metodo que fecha conexao com o banco de dados
	public void fechar()throws Exception{
		con.close();
	}
}
