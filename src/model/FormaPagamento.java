package model;

public enum FormaPagamento {
	DINHEIRO("Dinheiro"),
	CARTAO_CREDITO("Cart�o de Credito"),
	CARTAO_DEBITO("Cart�o de d�bito"),
	CHEQUE("Cheque"),
	BOLETO_BANCARIO("Boleto banc�rio"),
	DEPOSITO_BANCARIO("Deposito banc�rio");

	private String descricao;
	
	FormaPagamento(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}
}
