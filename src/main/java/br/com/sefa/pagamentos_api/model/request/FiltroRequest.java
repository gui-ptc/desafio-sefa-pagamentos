package br.com.sefa.pagamentos_api.model.request;

import jakarta.validation.constraints.Pattern;

public record FiltroRequest (
		@Pattern(regexp = "\\d+", message = "Campo aceita somente dígitos")
		Integer codigoDebito,
		@Pattern(regexp = "\\d+", message = "Campo aceita somente dígitos")
		String cpfCnpj,
		String statusPagamento) {};				
	


