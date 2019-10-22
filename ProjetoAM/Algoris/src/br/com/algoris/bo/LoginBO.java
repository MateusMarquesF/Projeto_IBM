package br.com.algoris.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.algoris.beans.Login;
import br.com.algoris.dao.LoginDAO;


public class LoginBO {

	private Login l;
	private LoginDAO dao;
	private String resposta;
	private List<Login> lista;
	
	public LoginBO() throws Exception{
		l = new Login();
		dao = new LoginDAO();
		lista = new ArrayList<Login>();
	}
	
	public String verificaCampos(Login log)throws Exception{
		resposta = "OK";
		
		if (log.getRm() == null || log.getRm() == "" || log.getRm().length() > 10)
			return "Rm Inv�lido!";
		if (log.getSenha() == null || log.getSenha() == "" || log.getSenha().length()>20)
			return "Senha Inv�lido!";
		if (log.getTipo() == null || log.getTipo() == "" || log.getTipo().length()>5)
			return "Tipo login Inv�lido!";
		
		log.setRm(log.getRm().toUpperCase());
		log.setTipo(log.getTipo().toUpperCase());
		
		return resposta;
	}
	
	public String verificaExiste(Login log)throws Exception{
		resposta = "VERIFICADO";
		
		l = dao.consultaPorCodigo(log.getCodigo());
		if (log.getCodigo() > 0) {
			dao.fechar();
			return "Login J� Existe!";
		}
		
		return resposta;
	}
	
	public String cadastrarLogin(Login log)throws Exception{
		
		 resposta = verificaCampos(log);
		
		if(resposta.equals("OK")) {
			resposta = verificaExiste(log);
		}
		
		if(resposta.equals("VERIFICADO")) {
			if(log.getTipo().equals("ADMIN")) {
				resposta = dao.gravarAdim(log);
				dao.fechar();
			}
			else if(log.getTipo().equals("PROF")) {
				resposta = dao.gravarProfessor(log);
				dao.fechar();
			}
			else {
				resposta = dao.gravarAluno(log);
				dao.fechar();
			}
				
		}
		
		return resposta;

}

	public String atualizarSenha(Login log)throws Exception{
		resposta = verificaCampos(log);
		
		l = dao.consultaPorCodigo(log.getCodigo());
		
		if(log.getCodigo() > 0) {
				resposta = dao.atualizaSenha(log);
				dao.fechar();
		}else {
			dao.fechar();
			return "Login Inv�lido!";
		}
		
		dao.fechar();
		return resposta;
	}

	public String deletarLogin(Login log)throws Exception{
		l = dao.consultaPorCodigo(log.getCodigo());
		
		if(log.getCodigo() > 0 ) {
			resposta = dao.deletarLogin(log);
		}else {
			resposta = "Erro ao Deletar Login!";
		}
		
		dao.fechar();
		return resposta;
	}
}
