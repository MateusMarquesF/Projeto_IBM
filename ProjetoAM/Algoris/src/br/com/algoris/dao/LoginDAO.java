package br.com.algoris.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.algoris.beans.Login;
import br.com.algoris.conexao.Conexao;

public class LoginDAO {

	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public LoginDAO() throws Exception{
		con = Conexao.getConnection();
	}
	
	// metodo que cadastra o login do Administrador
	public String gravarAdim(Login log)throws Exception{
		stmt = con.prepareStatement("INSERT INTO T_LOGIN VALUES(sq_login.nextval,?,?,'ADMIN')");
		stmt.setString(1, log.getRm());
		stmt.setString(2, log.getSenha());
		stmt.executeUpdate();
		return "Administrador cadastrado com sucesso!";
	}
	
	// metodo que cadastra o login do Professor
	public String gravarProfessor(Login log)throws Exception{
		stmt = con.prepareStatement("INSERT INTO T_LOGIN VALUES(sq_login.nextval,?,?,'PROF')");
		stmt.setString(1, log.getRm());
		stmt.setString(2, log.getSenha());
		stmt.executeUpdate();
		return "Professor cadastrado com sucesso!";
	}
	
	//metodo que cadastra o login do Aluno
	public String gravarAluno(Login log)throws Exception{
		stmt = con.prepareStatement("INSERT INTO T_LOGIN VALUES(sq_login.nextval,?,?,'ALN')");
		stmt.setString(1, log.getRm());
		stmt.setString(2, log.getSenha());
		stmt.executeUpdate();
		return "Aluno cadastrado com sucesso!";
	}
	
	//metodo que consulta pelo codigo
	public Login consultaPorCodigo(int codigo)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_LOGIN WHERE CD_LOGIN = ?");
		stmt.setInt(1, codigo);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			return new Login(
					rs.getInt("CD_LOGIN"),
					rs.getString("RM"),
					rs.getString("SENHA"),
					rs.getString("TIPO"));
		}else {
			return new Login();
		}
	}
	
	//metodo que consulta pelo rm do login
	public Login consultarPorRm(String rm)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_LOGIN WHERE RM = ?");
		stmt.setString(1, rm);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			return new Login(
					rs.getInt("CD_LOGIN"),
					rs.getString("RM"),
					rs.getString("SENHA"),
					rs.getString("TIPO"));
		}else {
			return new Login();
		}
	}
	
	//metodo que consulta pelo tipo de login retornando uma lista
	public List<Login> consultarPorTipo(String tipo)throws Exception{
		List<Login> lista = new ArrayList<Login>();
		stmt = con.prepareStatement("SELECT * FROM T_LOGIN WHERE TIPO = ?");
		stmt.setString(1, tipo);
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			lista.add(new Login(
					rs.getInt("CD_LOGIN"),
					rs.getString("RM"),
					rs.getString("SENHA"),
					rs.getString("TIPO")));
		}
		
		return lista;
	}
	
	//metodo que Altera a senha
	public String atualizaSenha(Login log)throws Exception{
		stmt = con.prepareStatement("UPDATE T_LOGIN SET SENHA = ? WHERE RM = ?");
		stmt.setString(1, log.getSenha());
		stmt.setString(2, log.getRm());
		stmt.executeUpdate();
		return "Senha Atualizada!";
	}
	
	//metodo que deleta o login pelo codigo
	public String deletarLogin(int codigo)throws Exception{
		stmt = con.prepareStatement("DELETE FROM T_LOGIN WHERE CD_LOGIN = ?");
		stmt.setInt(1, codigo);
		stmt.executeUpdate();
		return "Login Deletado!";
	}
	
	//metodo que fecha a conexão com o Banco de Dados
	public void fechar()throws Exception{
		con.close();
	}
	
}
