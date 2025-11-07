package br.com.sefa.pagamentos_api.model.request;

public record FiltroRequest (Integer codigoDebito,
							 String cpfCnpj,
							 String statusPagamento) {};				
	


