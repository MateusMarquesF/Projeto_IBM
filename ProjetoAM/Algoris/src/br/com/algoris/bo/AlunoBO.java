package br.com.algoris.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.algoris.beans.Aluno;
import br.com.algoris.dao.AlunoDAO;


public class AlunoBO {

	private Aluno a;
	private AlunoDAO dao;
	private String resposta;
	private List<Aluno> lista;
	
	public AlunoBO() throws Exception{
		a = new Aluno();
		dao = new AlunoDAO();
		lista = new ArrayList<Aluno>();
	}
	
	public String verificaCampos(Aluno al)throws Exception{
		resposta = "OK";
		
		if (al.getNome() == null || al.getNome() == "" || al.getNome().length() > 50)
			return "Nome Inválido!";
		if (al.getEmail() == null || al.getEmail() == "" || al.getEmail().length()>30)
			return "E-mail Inválido!";
		if (al.getCpf() == null || al.getCpf() == "" || al.getCpf().length()>15)
			return "CPF Inválido!";
		
		al.setCpf(al.getCpf().toUpperCase());
		
		return resposta;
	}
	
	public String verificaExiste(Aluno al)throws Exception{
		resposta = "VERIFICADO";
		
		a = dao.consultaPorCodigo(al.getCodigo());
		if (al.getCodigo() > 0) {
			dao.fechar();
			return "Aluno Já Existe!";
		}
		
		return resposta;
	}
	
	public String cadastrarAluno(Aluno al)throws Exception{
		
		 resposta = verificaCampos(al);
		
		if(resposta.equals("OK")) {
			resposta = verificaExiste(al);
		}
		
		if(resposta.equals("VERIFICADO")) {
				resposta = dao.gravarAluno(al);
				dao.fechar();
		}
		
		return resposta;

}

	public String atualizarEmail(Aluno al)throws Exception{
		resposta = verificaCampos(al);
		
		a = dao.consultaPorCodigo(al.getCodigo());
		
		if(al.getCodigo() > 0) {
				resposta = dao.atualizaEmailAluno(al);
				dao.fechar();
		}else {
			dao.fechar();
			return "Aluno Inválido!";
		}
		
		dao.fechar();
		return resposta;
	}

	public String deletarAluno(Aluno al)throws Exception{
		a = dao.consultaPorCodigo(al.getCodigo());
		
		if(al.getCodigo() > 0 ) {
			resposta = dao.apagarAluno(al);
		}else {
			resposta = "Erro ao Deletar Aluno!";
		}
		
		dao.fechar();
		return resposta;
	}
}
