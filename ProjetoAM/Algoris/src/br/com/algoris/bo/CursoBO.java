package br.com.algoris.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.algoris.beans.Curso;
import br.com.algoris.dao.CursoDAO;

public class CursoBO {

	private Curso c;
	private CursoDAO dao;
	private String resposta;
	private List<Curso> lista;
	
	public CursoBO() throws Exception{
		c = new Curso();
		dao = new CursoDAO();
		lista = new ArrayList<Curso>();
	}
	
	public String verificaCampos(Curso cur)throws Exception{
		resposta = "OK";
		
		if (cur.getDescricao() == null || cur.getDescricao() == "" || cur.getDescricao().length() > 255)
			return "Descri��o do Curso Inv�lido!";
		if (cur.getCampus() == null || cur.getCampus() == "" || cur.getCampus().length()>20)
			return "Campus Inv�lido!";
		if (cur.getCargaHoraria() == null || cur.getCargaHoraria() == "" || cur.getCargaHoraria().length()>5)
			return "Carga Horaria do Curso Inv�lido!";
		if (cur.getTipoCurso() == null || cur.getTipoCurso() == "" || cur.getTipoCurso().length()>20)
			return "Tipo de Curso Inv�lido!";
		
		cur.setCargaHoraria(cur.getCargaHoraria().toUpperCase());
		cur.setTipoCurso(cur.getTipoCurso().toUpperCase());
		
		return resposta;
	}
	
	public String verificaExiste(Curso cur)throws Exception{
		resposta = "VERIFICADO";
		
		c = dao.consultaPorCodigo(cur.getCodigo());
		if (c.getCodigo() > 0) {
			dao.fechar();
			return "Curso J� Existe!";
		}
		
		return resposta;
	}
	
	public String cadastrarClinica(Curso cur)throws Exception{
		
		 resposta = verificaCampos(cur);
		
		if(resposta.equals("OK")) {
			resposta = verificaExiste(cur);
		}
		
		if(resposta.equals("VERIFICADO")) {
			resposta = dao.gravarCurso(cur);
			dao.fechar();
		}
		
		return resposta;

}

	public String atualizarCurso(Curso cur)throws Exception{
		resposta = verificaCampos(cur);
		
		c = dao.consultaPorCodigo(cur.getCodigo());
		
		if(c.getCodigo() > 0) {
				resposta = dao.atualizaCurso(cur);
				dao.fechar();
		}else {
			dao.fechar();
			return "C�digo Curso Inv�lido!";
		}
		
		dao.fechar();
		return resposta;
	}

	public String deletarCurso(Curso cur)throws Exception{
		c = dao.consultaPorCodigo(cur.getCodigo());
		
		if(c.getCodigo() > 0 ) {
			resposta = dao.deletarCurso(cur);
		}else {
			resposta = "Erro ao Deletar Curso!";
		}
		
		dao.fechar();
		return resposta;
	}
}