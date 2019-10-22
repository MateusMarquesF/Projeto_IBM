package br.com.algoris.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.algoris.beans.Professor;
import br.com.algoris.dao.ProfessorDAO;


public class ProfessorBO {

	private Professor p;
	private ProfessorDAO dao;
	private String resposta;
	private List<Professor> lista;
	
	public ProfessorBO() throws Exception{
		p = new Professor();
		dao = new ProfessorDAO();
		lista = new ArrayList<Professor>();
	}
	
	public String verificaCampos(Professor pr)throws Exception{
		resposta = "OK";
		
		if (pr.getNome() == null || pr.getNome() == "" || pr.getNome().length() > 50)
			return "Nome Inválido!";
		if (pr.getEmail() == null || pr.getEmail() == "" || pr.getEmail().length()>30)
			return "E-mail Inválido!";
		
		return resposta;
	}
	
	public String verificaExiste(Professor pr)throws Exception{
		resposta = "VERIFICADO";
		
		p = dao.consultaPorCodigo(pr.getCodigo());
		if (pr.getCodigo() > 0) {
			dao.fechar();
			return "Professor Já Existe!";
		}
		
		return resposta;
	}
	
	public String cadastrarProfrssor(Professor pr)throws Exception{
		
		 resposta = verificaCampos(pr);
		
		if(resposta.equals("OK")) {
			resposta = verificaExiste(pr);
		}
		
		if(resposta.equals("VERIFICADO")) {
				resposta = dao.gravarProfessor(pr);
				dao.fechar();
		}
		
		return resposta;

}

	public String atualizarEmail(Professor pr)throws Exception{
		resposta = verificaCampos(pr);
		
		p = dao.consultaPorCodigo(pr.getCodigo());
		
		if(pr.getCodigo() > 0) {
				resposta = dao.atualizaEmailProfessor(pr);
				dao.fechar();
		}else {
			dao.fechar();
			return "Professor Inválido!";
		}
		
		dao.fechar();
		return resposta;
	}

	public String deletarProfessor(Professor pr)throws Exception{
		p = dao.consultaPorCodigo(pr.getCodigo());
		
		if(pr.getCodigo() > 0 ) {
			resposta = dao.apagarProfessor(pr);
		}else {
			resposta = "Erro ao Deletar Porfessor!";
		}
		
		dao.fechar();
		return resposta;
	}
}
