package br.com.cotiinformatica.exceptions;

public class AcessoNegadoException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	
	public String getMessage() {
		
		return "Acesso Negado. Usuário Inválido.";
	}
	
}
