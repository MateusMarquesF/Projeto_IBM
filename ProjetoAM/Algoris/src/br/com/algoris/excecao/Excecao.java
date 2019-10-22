package br.com.algoris.excecao;

public class Excecao extends Exception{

	// ADICIONAR MAIS MÉTODOS  DE ERROS COM BASE NAS EXCEPTION DOS TESTES
		public static String tratarExcecao(Exception ex) {
			if(ex.getClass().getName().equals("java.lang.NumberFormatException")) {
				return("Numero Inválido!");
			}else if(ex.getClass().getName().equals("java.sql.Exception")) {
				return("Falha ao conectar no Banco!");
			}
			else {
				return("Algo deu errado!!");
			}	
		}
}
